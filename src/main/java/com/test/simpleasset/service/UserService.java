package com.test.simpleasset.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.test.simpleasset.constant.Message;
import com.test.simpleasset.constant.UserType;
import com.test.simpleasset.dao.FileDao;
import com.test.simpleasset.dao.RoleDao;
import com.test.simpleasset.dao.UserDao;
import com.test.simpleasset.dto.user.UserDataDto;
import com.test.simpleasset.dto.user.UserDataResDto;
import com.test.simpleasset.dto.user.UserDeleteReqDto;
import com.test.simpleasset.dto.user.UserDeleteResDto;
import com.test.simpleasset.dto.user.UserInsertDataResDto;
import com.test.simpleasset.dto.user.UserInsertReqDto;
import com.test.simpleasset.dto.user.UserInsertResDto;
import com.test.simpleasset.dto.user.UserUpdatePasswordReqDto;
import com.test.simpleasset.dto.user.UserUpdatePasswordResDto;
import com.test.simpleasset.dto.user.UserUpdateReqDto;
import com.test.simpleasset.dto.user.UserUpdateResDto;
import com.test.simpleasset.dto.user.UsersDto;
import com.test.simpleasset.model.File;
import com.test.simpleasset.model.Role;
import com.test.simpleasset.model.User;
import com.test.simpleasset.pojo.EmailPojo;
import com.test.simpleasset.util.RandomNumberGeneratorUtil;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private FileDao fileDao;
	@Autowired
	private EmailService emailService;
	@Autowired
	private PrincipalService principalService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional(rollbackOn = Exception.class)
	public UserInsertResDto insert(final UserInsertReqDto data) {

//		Uncomment principalId = 1l and comment getPrinciple.getId() to create super admin for the first time. Then comment and uncomment back when it's been created
//		final Long principalId = 1l;
		final Long principalId = principalService.getPrinciple().getId();

		final UserInsertResDto userInsertResDto = new UserInsertResDto();
		final UserInsertDataResDto userInsertDataResDto = new UserInsertDataResDto();
		final User user = new User();
		user.setFullname(data.getFullname());
		user.setEmail(data.getEmail());
		user.setCreatedBy(principalId);
		user.setUpdatedBy(principalId);

		final String plainPassword = RandomNumberGeneratorUtil
				.givenUsingJava8_whenGeneratingRandomAlphanumericString_thenCorrect();
		final String hashPassword = passwordEncoder.encode(plainPassword);
		user.setPass(hashPassword);

		File file = new File();
		file.setFileCodes(data.getFileCodes());
		file.setExtensions(data.getExtensions());
		file.setCreatedBy(principalId);
		file.setUpdatedBy(principalId);
		file = fileDao.insert(file);
		user.setFile(file);

//		Uncomment the ADMIN code and Comment the NON_ADMIN code to create super admin for the first time. Then undo everything when it's been created
//		final Role role = roleDao.getCodeId(UserType.ADMIN.getRoleCode()).get();
		final Role role = roleDao.getCodeId(UserType.NON_ADMIN.getRoleCode()).get();

		user.setRole(role);

		final User userInsert = userDao.insert(user);

		final EmailPojo emailPojo = new EmailPojo();
		emailPojo.setEmail(data.getEmail());
		emailPojo.setSubject("Password Anda");
		emailPojo.setBody("Berikut adalah password anda : " + plainPassword);
		final Runnable r = () -> emailService.sendMail(emailPojo);
		final Thread thread = new Thread(r);
		thread.start();

		userInsertDataResDto.setId(userInsert.getId());
		userInsertResDto.setData(userInsertDataResDto);
		userInsertResDto.setMessage(Message.INSERTED.getMessage());
		return userInsertResDto;
	}

	@Transactional(rollbackOn = Exception.class)
	public UserUpdateResDto update(final UserUpdateReqDto data) {
		final Optional<User> userOptional = userDao.getById(data.getUserId());
		final UserUpdateResDto userUpdateResDto = new UserUpdateResDto();
		User userUpdate = null;
		if (userOptional.isPresent()) {
			userUpdate = userOptional.get();
			userUpdate.setFullname(data.getFullname());
			userUpdate.setIsActive(data.getIsActive());

			final long oldFileId = userUpdate.getFile().getId();
			File file = new File();
			file.setFileCodes(data.getFileCodes());
			file.setExtensions(data.getExtensions());
			file.setCreatedBy(principalService.getPrinciple().getId());
			file.setUpdatedBy(principalService.getPrinciple().getId());
			file = fileDao.insert(file);
			userUpdate.setFile(file);

			userUpdate = userDao.update(userUpdate);
			fileDao.deleteById(oldFileId);
			data.setVersion(userUpdate.getVersion());
			userUpdateResDto.setData(data);
			userUpdateResDto.setMessage(Message.UPDATED.getMessage());
		}
		return userUpdateResDto;
	}

	@Transactional(rollbackOn = Exception.class)
	public UserUpdatePasswordResDto updatePassword(final UserUpdatePasswordReqDto data) {
		final Long principalId = principalService.getPrinciple().getId();
		final User user = userDao.getById(principalId).get();

		if (passwordEncoder.matches(data.getOldPassword(), user.getPass())) {
			final String hashPassword = passwordEncoder.encode(data.getNewPassword());
			user.setPass(hashPassword);
			user.setUpdatedBy(principalId);
		} else {
			throw new RuntimeException("Password lama salah!");
		}
		final User userUpdated = userDao.update(user);
		data.setVersion(userUpdated.getVersion());

		final UserUpdatePasswordResDto updatePasswordResDto = new UserUpdatePasswordResDto();
		updatePasswordResDto.setData(data);
		updatePasswordResDto.setMessage("Password Updated");
		return updatePasswordResDto;
	}

	public UserDataResDto getById(final Long id) {
		final Optional<User> userOptional = userDao.getById(id);
		final UserDataDto userDataDto = new UserDataDto();
		if (userOptional.isPresent()) {
			final User user = userOptional.get();
			userDataDto.setFullname(user.getFullname());
			userDataDto.setEmail(user.getEmail());
			userDataDto.setRoleName(user.getRole().getRoleName());
			userDataDto.setFileId(user.getFile().getId());
			userDataDto.setUserId(user.getId());
			userDataDto.setVersion(user.getVersion());
			userDataDto.setIsActive(user.getIsActive());
		}
		final UserDataResDto userDataResDto = new UserDataResDto();
		userDataResDto.setData(userDataDto);
		return userDataResDto;
	}

	public UsersDto getAll() {
		final List<User> users = userDao.getAll();

		final Comparator<User> compareByFullnameThenEmail = Comparator.comparing(User::getFullname)
				.thenComparing(User::getEmail);
		final Stream<User> sortedUsers = users.stream().sorted(compareByFullnameThenEmail);

		final List<UserDataDto> dataDtos = new ArrayList<>();
		sortedUsers.forEach(user -> {
			final UserDataDto userDataDto = new UserDataDto();
			userDataDto.setFullname(user.getFullname());
			userDataDto.setEmail(user.getEmail());
			userDataDto.setRoleName(user.getRole().getRoleName());
			userDataDto.setFileId(user.getFile().getId());
			userDataDto.setUserId(user.getId());
			userDataDto.setVersion(user.getVersion());
			userDataDto.setIsActive(user.getIsActive());
			dataDtos.add(userDataDto);
		});
		final UsersDto usersDto = new UsersDto();
		usersDto.setData(dataDtos);

		return usersDto;
	}

	public UsersDto findByFullname(final String name) {
		final List<User> users = userDao.getAll();

		final Comparator<User> compareByFullnameThenEmail = Comparator.comparing(User::getFullname)
				.thenComparing(User::getEmail);
		final Stream<User> sortedUsers = users.stream().sorted(compareByFullnameThenEmail)
				.filter(user -> user.getFullname().toLowerCase().contains(name.toLowerCase()));

		final List<UserDataDto> dataDtos = new ArrayList<>();
		sortedUsers.forEach(user -> {
			final UserDataDto userDataDto = new UserDataDto();
			userDataDto.setFullname(user.getFullname());
			userDataDto.setEmail(user.getEmail());
			userDataDto.setRoleName(user.getRole().getRoleName());
			userDataDto.setFileId(user.getFile().getId());
			userDataDto.setUserId(user.getId());
			userDataDto.setVersion(user.getVersion());
			userDataDto.setIsActive(user.getIsActive());
			dataDtos.add(userDataDto);
		});
		final UsersDto usersDto = new UsersDto();
		usersDto.setData(dataDtos);

		return usersDto;
	}

	@Transactional(rollbackOn = Exception.class)
	public UserDeleteResDto deleteById(final Long id) {
		final Optional<User> userOptional = userDao.getById(id);
		final UserDeleteResDto userDeleteResDto = new UserDeleteResDto();
		if (userOptional.isPresent()) {
			final UserDeleteReqDto userDeleteReqDto = new UserDeleteReqDto();
			userDeleteReqDto.setUserId(id);
			userDeleteResDto.setMessage(Message.DELETED.getMessage());
			userDao.deleteById(id);
		}
		return userDeleteResDto;
	}

	public Optional<User> getByEmail(final String email) {
		return userDao.getByEmail(email);
	}

	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		final Optional<User> userOptional = getByEmail(username);
		if (userOptional.isPresent()) {
			return new org.springframework.security.core.userdetails.User(username, userOptional.get().getPass(),
					new ArrayList<>());
		}
		throw new UsernameNotFoundException("Wrong username or password");
	}
}
