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
