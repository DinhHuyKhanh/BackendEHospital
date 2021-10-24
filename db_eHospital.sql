CREATE DATABASE db_eHospital;

USE db_eHospital;

CREATE TABLE IF NOT EXISTS `Account`(
	`username`  		VARCHAR(255) PRIMARY KEY,
    `password`			NVARCHAR(255) NOT NULL,
    `role` 				enum("Admin","User") NOT NULL DEFAULT "User"
);

-- các khoa
CREATE TABLE IF NOT EXISTS `Department`( 
	id 			INT AUTO_INCREMENT PRIMARY KEY,
    department	NVARCHAR(255)
);
 
 -- danh sách bác sỹ 
CREATE TABLE IF NOT EXISTS `Doctor`(
	id 			INT AUTO_INCREMENT PRIMARY KEY,
    fullName 	NVARCHAR(255) NOT NULL,
	birthday		DATE, -- ngày sinh của người đặt
    gender 			ENUM("Male","Female"), -- giới tínH
    address			NVARCHAR(255), -- địa chỉ người đặt 
    numberPhone		INT(10) UNIQUE NOT NULL, -- số điện thoại của người đặt
    department_id	INT NOT NULL,
	CONSTRAINT dt_department	FOREIGN KEY(department_id)	REFERENCES `Department`(id)
);

-- Danh sách lịch hẹn
CREATE TABLE IF NOT EXISTS `Appointment`(
	id				INT AUTO_INCREMENT PRIMARY KEY, -- truong dinh danh
	fullName 		NVARCHAR(255), -- họ và tên đầy đủ của người hẹn
    birthday		DATE, -- ngày sinh của người đặt
    gender 			ENUM("Male","Female"), -- giới tính
    address			NVARCHAR(255), -- địa chỉ người đặt 
    numberPhone		INT(10) NOT NULL, -- số điện thoại của người đặt
    `start` 		DATETIME NOT NULL, -- thời gian khám bệnh
	`status`		ENUM("SUCCESS","APPROVAL","ABORT") NOT NULL DEFAULT "APPROVAL"
);








