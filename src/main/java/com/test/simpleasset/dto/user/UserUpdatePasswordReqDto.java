package com.test.simpleasset.dto.user;

import javax.validation.constraints.NotBlank;

public class UserUpdatePasswordReqDto {
	private Long userId;
	private Integer version;
	@NotBlank(message = "new password Harus diisi!")
	private String newPassword;
	@NotBlank(message = "old password Harus diisi!")
	private String oldPassword;
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}

}
