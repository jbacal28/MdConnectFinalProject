DROP TABLE IF EXISTS doctor_patient;
DROP TABLE IF EXISTS patient;
DROP TABLE IF EXISTS doctor;
DROP TABLE IF EXISTS specialties;

CREATE TABLE specialties (
	license_id int NOT NULL AUTO_INCREMENT,
	specialty_name varchar(256) NOT NULL,
	PRIMARY KEY (license_id)	
);

CREATE TABLE doctor (
	doctor_id int NOT NULL AUTO_INCREMENT,
	license_id int NOT NULL,
	doctor_first_name varchar(128),
	doctor_last_name varchar(128),
	age int,
	gender varchar(60),
	PRIMARY KEY (doctor_id),
	FOREIGN KEY (license_id) REFERENCES specialties (license_id) ON DELETE CASCADE
);

CREATE TABLE patient (
	patient_id int NOT NULL AUTO_INCREMENT,
	patient_first_name varchar(128),
	patient_last_name varchar(128),
	date_of_birth varchar(256),
	phone_number varchar(64),
	email_address varchar(40),
	PRIMARY KEY (patient_id)
);

CREATE TABLE doctor_patient (
	doctor_id int NOT NULL,
	patient_id int NOT NULL,
	FOREIGN KEY (doctor_id) REFERENCES doctor (doctor_id) ON DELETE CASCADE,
	FOREIGN KEY (patient_id) REFERENCES patient (patient_id) ON DELETE CASCADE
);