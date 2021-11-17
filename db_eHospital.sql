CREATE DATABASE db_eHospital;
USE db_eHospital;

CREATE TABLE IF NOT EXISTS `Account`(
	id					INT AUTO_INCREMENT PRIMARY KEY,
	`username`  		VARCHAR(255) UNIQUE NOT NULL ,
    `password`			NVARCHAR(255) NOT NULL
);
CREATE TABLE IF NOT EXISTS `role`(
	id				INT AUTO_INCREMENT PRIMARY KEY,
    role_name 		ENUM("USER","ADMIN")
);

CREATE TABLE IF NOT EXISTS `user_roles`(
    user_id 		INT NOT NULL,
    role_id		INT NOT NULL,
    CONSTRAINT fk_user	 FOREIGN KEY(user_id) REFERENCES `Account`(id)
);

-- các khoa
CREATE TABLE IF NOT EXISTS `Department`(
	id 			INT AUTO_INCREMENT PRIMARY KEY,
    department	NVARCHAR(255)
);
 
 -- danh sách bác sỹ 
CREATE TABLE IF NOT EXISTS `Doctor`(
	id 			INT AUTO_INCREMENT PRIMARY KEY,
    fullName 	NVARCHAR(255) DEFAULT NULL,
    experience		INT,
    price			INT,
    degree			NVARCHAR(255),			
	birthday		DATE, -- ngày sinh của người đặt
    gender 			ENUM("Male","Female"), -- giới tínH
    address			NVARCHAR(255), -- địa chỉ người đặt 
    numberPhone		VARCHAR(15) UNIQUE, -- số điện thoại của người đặt
    department_id	INT NOT NULL,
	CONSTRAINT dt_department	FOREIGN KEY(department_id)	REFERENCES `Department`(id)
);

-- Danh sách lịch hẹn
CREATE TABLE IF NOT EXISTS `Appointment`(
	id				INT AUTO_INCREMENT PRIMARY KEY, -- truong dinh danh
	fullName 		NVARCHAR(255) DEFAULT NULL, -- họ và tên đầy đủ của người hẹn
    birthday		VARCHAR(50), -- ngày sinh của người đặt
    gender 			ENUM("Male","Female"), -- giới tính
    address			NVARCHAR(255), -- địa chỉ người đặt
    numberPhone		VARCHAR(15) NOT NULL DEFAULT '', -- số điện thoại của người đặt
    accountId		INT NOT NULL, -- tài khoản đặt lịch
    doctorId		INT,
    departmentId	INT,
    dateAppointment 		DATETIME NOT NULL, -- thời gian khám bệnh
	`status`		ENUM("SUCCESS","PENDING","ABORT") NOT NULL DEFAULT "PENDING",
	CONSTRAINT pk_appointment	FOREIGN KEY(departmentId)	REFERENCES `Department`(id),
    CONSTRAINT pk_doctor		FOREIGN KEY(doctorId) REFERENCES `Doctor`(id)
);

INSERT INTO `role`
VALUES (1, "ADMIN"),
		(2,"USER");
        
        SELECT * FROM appointment;
        select * from account;
        
-- ALTER TABLE `appointment`
-- MODIFY COLUMN	numberPhone varchar(15) not null  default "unknow";

-- ALTER TABLE `Doctor`
-- ALTER fullName SET DEFAULT 'Unknown';

-- chế độ nghiêm ngặt Strict_trains_table 
-- set global sql_mode=''; 
--  để tắt 
-- set global sql_mode='STRICT_TRANS_TABLES'; 
-- để bật




