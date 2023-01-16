----=================DDL===============----
CREATE TABLE file(
	id serial,
	file_codes text not null,
	extensions varchar(4) not null,
	created_by int not null,
	created_at timestamp without time zone not null,
	updated_by int,
	updated_at timestamp without time zone,
	ver int not null default 0,
	is_active boolean not null default true
);
ALTER TABLE file
	ADD CONSTRAINT file_pk PRIMARY KEY(id);

CREATE TABLE user_role(
	id serial,
	role_name varchar(30) not null,
	role_code varchar(5) not null,
	created_by int not null,
	created_at timestamp without time zone not null,
	updated_by int,
	updated_at timestamp without time zone,
	ver int not null default 0,
	is_active boolean not null default true
);
ALTER TABLE user_role
	ADD CONSTRAINT user_role_pk PRIMARY KEY(id);
ALTER TABLE user_role
	ADD CONSTRAINT role_code_bk UNIQUE(role_code);
ALTER TABLE user_role
	ADD CONSTRAINT role_name_code_ck UNIQUE(role_name, role_code);

CREATE TABLE users (
	id serial,
	fullname varchar(50) not null,
	email varchar(30) not null,
	pass text not null,
	user_role_id int not null,
	file_id int not null,
	created_by int not null,
	created_at timestamp without time zone not null,
	updated_by int,
	updated_at timestamp without time zone,
	ver int not null default 0,
	is_active boolean not null default true
);
ALTER TABLE users
	ADD CONSTRAINT user_data_pk PRIMARY KEY(id);
ALTER TABLE users
	ADD CONSTRAINT user_role_fk FOREIGN KEY(user_role_id)
	REFERENCES user_role(id);
ALTER TABLE users
	ADD CONSTRAINT file_users_fk FOREIGN KEY(file_id)
	REFERENCES file(id);
ALTER TABLE users
	ADD CONSTRAINT email_bk UNIQUE(email);
ALTER TABLE users
	ADD CONSTRAINT fullname_email_ck UNIQUE(fullname, email);

CREATE TABLE store (
	id serial,
	store_name varchar(50) not null,
	store_code varchar(5) not null,
	file_id int not null,
	created_by int not null,
	created_at timestamp without time zone not null,
	updated_by int,
	updated_at timestamp without time zone,
	ver int not null default 0,
	is_active boolean not null default true
);
ALTER TABLE store
	ADD CONSTRAINT store_pk PRIMARY KEY(id);
ALTER TABLE store
	ADD CONSTRAINT file_store_fk FOREIGN KEY(file_id)
	REFERENCES file(id);
ALTER TABLE store
	ADD CONSTRAINT store_code_bk UNIQUE(store_code);
ALTER TABLE store
	ADD CONSTRAINT store_name_code_ck UNIQUE(store_name, store_code);

CREATE TABLE provider (
	id serial,
	provider_name varchar(50) not null,
	provider_code varchar(5) not null,
	file_id int not null,
	store_id int not null,
	created_by int not null,
	created_at timestamp without time zone not null,
	updated_by int,
	updated_at timestamp without time zone,
	ver int not null default 0,
	is_active boolean not null default true
);
ALTER TABLE provider
	ADD CONSTRAINT provider_pk PRIMARY KEY(id);
ALTER TABLE provider
	ADD CONSTRAINT file_provider_fk FOREIGN KEY(file_id)
	REFERENCES file(id);
ALTER TABLE provider
	ADD CONSTRAINT store_fk FOREIGN KEY(store_id)
	REFERENCES store(id);
ALTER TABLE provider
	ADD CONSTRAINT provider_code_bk UNIQUE(provider_code);
ALTER TABLE provider
	ADD CONSTRAINT provider_name_code_ck UNIQUE(provider_name, provider_code);

CREATE TABLE company (
	id serial,
	company_name varchar(50) not null,
	company_code varchar(5) not null,
	file_id int not null,
	created_by int not null,
	created_at timestamp without time zone not null,
	updated_by int,
	updated_at timestamp without time zone,
	ver int not null default 0,
	is_active boolean not null default true
);
ALTER TABLE company
	ADD CONSTRAINT company_pk PRIMARY KEY(id);
ALTER TABLE company
	ADD CONSTRAINT company_code_bk UNIQUE(company_code);
ALTER TABLE company
	ADD CONSTRAINT company_name_code_ck UNIQUE(company_name, company_code);
ALTER TABLE provider
	ADD CONSTRAINT file_company_fk FOREIGN KEY(file_id)
	REFERENCES file(id);

CREATE TABLE asset_type (
	id serial,
	asset_type_name varchar(30) not null,
	asset_type_code varchar(5) not null,
	created_by int not null,
	created_at timestamp without time zone not null,
	updated_by int,
	updated_at timestamp without time zone,
	ver int not null default 0,
	is_active boolean not null default true
);
ALTER TABLE asset_type
	ADD CONSTRAINT asset_type_pk PRIMARY KEY(id);
ALTER TABLE asset_type
	ADD CONSTRAINT asset_type_name_code_ck UNIQUE(asset_type_name, asset_type_code);

CREATE TABLE asset_status (
	id serial,
	asset_status_name varchar(30) not null,
	asset_status_code varchar(5) not null,
	created_by int not null,
	created_at timestamp without time zone not null,
	updated_by int,
	updated_at timestamp without time zone,
	ver int not null default 0,
	is_active boolean not null default true
);
ALTER TABLE asset_status
	ADD CONSTRAINT asset_status_pk PRIMARY KEY(id);
ALTER TABLE asset_status
	ADD CONSTRAINT asset_status_code_ck UNIQUE(asset_status_name, asset_status_code);

CREATE TABLE employee (
	id serial,
	employee_name varchar(50) not null,
	employee_code varchar(5) not null,
	created_by int not null,
	created_at timestamp without time zone not null,
	updated_by int,
	updated_at timestamp without time zone,
	ver int not null default 0,
	is_active boolean not null default true
);
ALTER TABLE employee
	ADD CONSTRAINT employee_pk PRIMARY KEY(id);
ALTER TABLE employee
	ADD CONSTRAINT employee_name_code_ck UNIQUE(employee_name, employee_code);

CREATE TABLE asset (
	id serial,
	asset_name varchar(50) not null,
	invoice_number varchar(30) not null,
	serial_number varchar(30) not null,
	expired_date date,
	file_id int not null,
	company_id int not null,
	asset_status_id int not null,
	asset_type_id int not null,
	provider_id int not null,
	created_by int not null,
	created_at timestamp without time zone not null,
	updated_by int,
	updated_at timestamp without time zone,
	ver int not null default 0,
	is_active boolean not null default true
);
ALTER TABLE asset
	ADD CONSTRAINT asset_pk PRIMARY KEY(id);
ALTER TABLE asset
	ADD CONSTRAINT file_asset_fk FOREIGN KEY(file_id)
	REFERENCES file(id);
ALTER TABLE asset
	ADD CONSTRAINT company_fk FOREIGN KEY(company_id)
	REFERENCES company(id);
ALTER TABLE asset
	ADD CONSTRAINT asset_type_fk FOREIGN KEY(asset_type_id)
	REFERENCES asset_type(id);
ALTER TABLE asset
	ADD CONSTRAINT asset_status_fk FOREIGN KEY(asset_status_id)
	REFERENCES asset_status(id);
ALTER TABLE asset
	ADD CONSTRAINT provider_fk FOREIGN KEY(provider_id)
	REFERENCES provider(id);
ALTER TABLE asset
	ADD CONSTRAINT serial_number_bk UNIQUE(serial_number);
ALTER TABLE asset
	ADD CONSTRAINT asset_name_serial_number_ck UNIQUE(asset_name, serial_number);

CREATE TABLE check_out (
	id serial,
	time_check_out timestamp without time zone not null,
	trx_code varchar(5) not null,
	check_out_location text,
	employee_id int,
	asset_general_id int,
	created_by int not null,
	created_at timestamp without time zone not null,
	updated_by int,
	updated_at timestamp without time zone,
	ver int not null default 0,
	is_active boolean not null default true
);
ALTER TABLE check_out
	ADD CONSTRAINT check_out_pk PRIMARY KEY(id);
ALTER TABLE check_out
	ADD CONSTRAINT employee_fk FOREIGN KEY(employee_id)
	REFERENCES employee(id);
ALTER TABLE check_out
	ADD CONSTRAINT asset_general_id_fk FOREIGN KEY(asset_general_id)
	REFERENCES asset(id);
ALTER TABLE check_out
	ADD CONSTRAINT trx_code_bk UNIQUE(trx_code);

CREATE TABLE check_out_detail(
	id serial,
	return_date date,
	asset_id int not null,
	check_out_id int not null,
	created_by int not null,
	created_at timestamp without time zone not null,
	updated_by int,
	updated_at timestamp without time zone,
	ver int not null default 0,
	is_active boolean not null default true
);
ALTER TABLE check_out_detail
	ADD CONSTRAINT check_out_detail_pk PRIMARY KEY(id);
ALTER TABLE check_out_detail
	ADD CONSTRAINT check_out_fk FOREIGN KEY(check_out_id)
	REFERENCES check_out(id);
ALTER TABLE check_out_detail
	ADD CONSTRAINT asset_fk FOREIGN KEY(asset_id)
	REFERENCES asset(id);

CREATE TABLE check_in (
	id serial,
	check_out_id int not null,
	created_by int not null,
	created_at timestamp without time zone not null,
	updated_by int,
	updated_at timestamp without time zone,
	ver int not null default 0,
	is_active boolean not null default true
);
ALTER TABLE check_in
	ADD CONSTRAINT check_in_pk PRIMARY KEY(id);
ALTER TABLE check_in
	ADD CONSTRAINT check_out_fk FOREIGN KEY(check_out_id)
	REFERENCES check_out(id);

CREATE TABLE check_in_detail(
	id serial,
	check_in_time timestamp without time zone not null,
	check_out_detail_id int not null,
	asset_status_id int not null,
	check_in_id int not null,
	created_by int not null,
	created_at timestamp without time zone not null,
	updated_by int,
	updated_at timestamp without time zone,
	ver int not null default 0,
	is_active boolean not null default true
);
ALTER TABLE check_in_detail
	ADD CONSTRAINT check_in_detail_pk PRIMARY KEY(id);
ALTER TABLE check_in_detail
	ADD CONSTRAINT check_out_detail_fk FOREIGN KEY(check_out_detail_id)
	REFERENCES check_out_detail(id);
ALTER TABLE check_in_detail
	ADD CONSTRAINT asset_status_fk FOREIGN KEY(asset_status_id)
	REFERENCES asset_status(id);
ALTER TABLE check_in_detail
	ADD CONSTRAINT check_in_fk FOREIGN KEY(check_in_id)
	REFERENCES check_in(id);

----=================DML===============----
INSERT INTO user_role (role_name, role_code, created_by, created_at) VALUES 
	('super admin', 'SA', 1, now()),
	('non-admin', 'NA', 1, now());

INSERT INTO asset_type (asset_type_name, asset_type_code, created_by, created_at) VALUES
	('general', 'gen', 1, now()),
	('licenses', 'lic', 1, now()),
	('components', 'com', 1, now()),
	('consumable', 'con', 1, now());

INSERT INTO asset_status (asset_status_name, asset_status_code, created_by, created_at) VALUES
	('deployable', 'dep', 1, now()),
	('undeployable', 'und', 1, now()),
	('archived', 'arc', 1, now()),
	('pending', 'pen', 1, now());

INSERT INTO file (file_codes, extensions, created_by, created_at) VALUES 
	('auorhqu9ehqwhce12848912y932141', '.png', 1, now()),
	('90cmruem89weru09c23r9m390cru90', '.jpg', 1, now());

INSERT INTO users (fullname, email, pass, user_role_id, file_id, created_by, created_at) VALUES 
	('andi sentosa', 'a', 'a', 1, 1, 1, now()),
	('budi sentosa', 'b', 'b', 2, 2, 1, now()),
	('coki sentosa', 'coki@email.com', 'coki123', 2, 1, 1, now()),
	('dodi sentosa', 'dodi@email.com', 'dodi123', 2, 1, 1, now()),
	('edi sentosa', 'edi@email.com', 'edi123', 2, 1, 1, now());

INSERT INTO store (store_name, store_code, file_id, created_by, created_at) VALUES
	('toko makmur', 'tm', 1, 1, now()),
	('toko jaya', 'tj', 2, 1, now()),
	('toko berkah', 'tb', 1, 1, now()),
	('toko hikmah', 'th', 1, 1, now()),
	('toko sukses', 'ts', 1, 1, now());

INSERT INTO provider (provider_name, provider_code, store_id, file_id, created_by, created_at) VALUES
	('apple', 'ap', 1, 1, 1, now()),
	('samsung', 'sa', 2, 2, 1, now()),
	('xiaomi', 'xi', 3, 1, 1, now()),
	('hp', 'hp', 4, 1, 1, now()),
	('acer', 'ac', 5, 1, 1, now()),
	('microsoft', 'ms', 5, 1, 1, now());

INSERT INTO company (company_name, company_code, file_id, created_by, created_at) VALUES
	('pt makmur', 'ptm', 1, 1, now()),
	('pt jaya', 'ptj', 2, 1, now()),
	('pt berkah', 'ptb', 1, 1, now()),
	('pt hikmah', 'pth', 1, 1, now()),
	('pt sukses', 'pts', 1, 1, now());

INSERT INTO employee (employee_name, employee_code, created_by, created_at) VALUES
	('andi cahyadi', 'ac', 1, now()),
	('budi cahyadi', 'bc', 1, now()),
	('coki cahyadi', 'cc', 1, now()),
	('dodi cahyadi', 'dc', 1, now()),
	('edi cahyadi', 'ec', 1, now());

INSERT INTO asset (asset_name, invoice_number, serial_number, expired_date, company_id, 
	asset_status_id, asset_type_id, provider_id, file_id, created_by, created_at) VALUES
	('iphone pro mag', 'a001', 'ipm001', null, 1, 1, 1, 1, 1, 1, now()),
	('samsung d3', 'a002', 'sd3001', null, 2, 1, 1, 2, 2, 1, now()),
	('xiaomi chic', 'a003', 'xmc001', null, 3, 1, 1, 3, 1, 1, now()),
	('hp notebook x720', 'a004', 'hnx7001', null, 4, 1, 1, 4, 1, 1, now()),
	('acer legion commander', 'a005', 'alc001', null, 5, 1, 1, 5, 1, 1, now()),
	('ms.word 2030', 'a001', 'mw3001', '2021-03-05', 5, 3, 2, 6, 1, 1, now());


-- Mulai transaksi
INSERT INTO check_out (trx_code, asset_general_id, check_out_location, time_check_out, employee_id,
	created_by, created_at) VALUES
	('AJIIS', null, null, now(), 1, 1, now()),
	('ADFES', null, null, now(), 2, 1, now()),
	('AXCVS', 1, null, now(), null, 1, now()),
	('AWADS', null, 'Jakarta Timur', now(), null, 1, now()),
	('AWASS', null, null, now(), 5, 1, now());
	
INSERT INTO check_out_detail (asset_id, return_date, check_out_id, created_by, created_at) VALUES
	(1, now(), 1, 1, now()),
	(2, now(), 2, 1, now()),
	(3, now(), 3, 1, now()),
	(4, now(), 4, 1, now()),
	(5, now(), 5, 1, now());

INSERT INTO check_in (check_out_id, created_by, created_at) VALUES
	(1, 1, now()),
	(2, 1, now()),
	(3, 1, now()),
	(4, 1, now()),
	(5, 1, now());

INSERT INTO check_in_detail (check_in_time, check_out_detail_id, asset_status_id, check_in_id, created_by, created_at) VALUES
	(now(), 1, 1, 1, 1, now()),
	(now(), 2, 1, 2, 1, now()),
	(now(), 3, 1, 3, 1, now()),
	(now(), 4, 1, 4, 1, now()),
	(now(), 5, 1, 5, 1, now());
	