--<ScriptOptions statementTerminator=";"/>

ALTER TABLE `database`.`users` DROP PRIMARY KEY;

ALTER TABLE `database`.`article` DROP PRIMARY KEY;

ALTER TABLE `database`.`articlefile` DROP PRIMARY KEY;

ALTER TABLE `database`.`user` DROP PRIMARY KEY;

DROP TABLE `database`.`user`;

DROP TABLE `database`.`users`;

DROP TABLE `database`.`article`;

DROP TABLE `database`.`articlefile`;

CREATE TABLE `database`.`user` (
	`ID` INTEGER UNSIGNED NOT NULL,
	`USER_ID` VARCHAR(50) NOT NULL,
	`EMAIL_ID` VARCHAR(50) NOT NULL,
	`FIRST_NAME` VARCHAR(45),
	`LAST_NAME` VARCHAR(45),
	`INITIALS` VARCHAR(15),
	`SUPERVISOR_ID` INT,
	`ACTIVE_IND` BIT NOT NULL,
	`Dob` DATETIME,
	`Last_Login_Date` DATETIME,
	`MO_ID` INT,
	`pwd` VARCHAR(40),
	PRIMARY KEY (`ID`)
) ENGINE=InnoDB;

CREATE TABLE `database`.`users` (
	`USER_ID` INTEGER UNSIGNED NOT NULL,
	`EMAIL_ID` VARCHAR(50) NOT NULL,
	`FIRST_NAME` VARCHAR(45),
	`LAST_NAME` VARCHAR(45),
	`INITIALS` VARCHAR(15),
	`SUPERVISOR_ID` INT,
	`ACTIVE_IND` BIT NOT NULL,
	`Dob` DATETIME,
	`Last_Login_Date` DATETIME,
	`MO_ID` INT,
	`pwd` VARCHAR(40),
	PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB;

CREATE TABLE `database`.`article` (
	`ArticleId` INTEGER UNSIGNED NOT NULL,
	`Title` VARCHAR(200) NOT NULL,
	`Teaser` VARCHAR(200),
	`BodyContent` TEXT,
	PRIMARY KEY (`ArticleId`)
) ENGINE=InnoDB;

CREATE TABLE `database`.`articlefile` (
	`ArticleFileId` INTEGER UNSIGNED NOT NULL,
	`fileName` VARCHAR(200) NOT NULL,
	`fileDescription` VARCHAR(200),
	`fileType` VARCHAR(45),
	`fileSize` INT,
	`domain` VARCHAR(45),
	`filePath` VARCHAR(250),
	`fileUrl` VARCHAR(250),
	PRIMARY KEY (`ArticleFileId`)
) ENGINE=InnoDB;

