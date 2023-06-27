CREATE DATABASE hospital;

CREATE TABLE doctor(
id INT,
NAME VARCHAR(15),
hospital_name VARCHAR(15),
age INT,
sex VARCHAR(5),
office_name VARCHAR(15),
picture MEDIUMBLOB,
degree VARCHAR(5),
specialty VARCHAR(50),
title VARCHAR(15) 
)

CREATE TABLE h_user(
id INT PRIMARY KEY AUTO_INCREMENT,
user_idef VARCHAR(18),
NAME VARCHAR(6),
sex VARCHAR(3),
PASSWORD VARCHAR(20),
email VARCHAR(20)
)

	

CREATE TABLE hospital (
  id INT PRIMARY KEY AUTO_INCREMENT,
  hospital_name VARCHAR(50) NOT NULL,
  hospital_area VARCHAR(50) NOT NULL,
  hospital_img `hospital`,
  hospital_dean_name VARCHAR(50),
  hospital_year VARCHAR(20),
  hospital_nature VARCHAR(20),
  hospital_grade VARCHAR(20),`favorite`
  is_medical_insurance VARCHAR(10),
  hospital_equipment VARCHAR(200),
  hospital_about TEXT,
  hospital_honor TEXT,
  hospital_url VARCHAR(100),
  hospital_phone VARCHAR(20),
  hospital_address VARCHAR(100),
  hospital_offices_num INT,
  medical_insurance_num INT,
  hospital_bed_num INT,
  outpatient_num INT,
  is_open INT
);



CREATE TABLE office (
  id INT PRIMARY KEY AUTO_INCREMENT,
  offices_name VARCHAR(50) NOT NULL,
  hospital_name VARCHAR(50) NOT NULL,
  doctor_num VARCHAR(20),
  offices_honor VARCHAR(200),
  offices_equipment VARCHAR(200),
  offices_about TEXT,
);


CREATE TABLE order_records (
  id INT PRIMARY KEY AUTO_INCREMENT,
  user_id INT NOT NULL,
  hospital_name VARCHAR(50) NOT NULL,
  offices_name VARCHAR(50) NOT NULL,
  doctor_name VARCHAR(50) NOT NULL,
  transact_date VARCHAR(20) NOT NULL,
  transact_time VARCHAR(20) NOT NULL
);
