/*
SQLyog Community v13.1.5  (64 bit)
MySQL - 5.5.62 : Database - vtrack
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`vtrack` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `vtrack`;

/*Table structure for table `account_transaction` */

DROP TABLE IF EXISTS `account_transaction`;

CREATE TABLE `account_transaction` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `user_Id` int(11) NOT NULL,
  `AMOUNT` int(11) NOT NULL,
  `REMARKS` varchar(200) DEFAULT NULL,
  `STATUS` tinyint(1) NOT NULL,
  `TDATE` date NOT NULL,
  `CURRENCY` varchar(11) NOT NULL,
  `ORDERID` varchar(36) NOT NULL,
  `order_rcptid_id` varchar(36) DEFAULT NULL,
  `transection_id` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Table structure for table `article` */

DROP TABLE IF EXISTS `article`;

CREATE TABLE `article` (
  `Article_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Title` varchar(200) NOT NULL,
  `Teaser` varchar(200) DEFAULT NULL,
  `Body_Content` text,
  PRIMARY KEY (`Article_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

/*Table structure for table `articlefile` */

DROP TABLE IF EXISTS `articlefile`;

CREATE TABLE `articlefile` (
  `ArticleFileId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fileName` varchar(200) NOT NULL,
  `fileDescription` varchar(200) DEFAULT NULL,
  `fileType` varchar(45) DEFAULT NULL,
  `fileSize` int(11) DEFAULT NULL,
  `domain` varchar(45) DEFAULT NULL,
  `filePath` varchar(250) DEFAULT NULL,
  `fileUrl` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`ArticleFileId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Password` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `Phone` varchar(20) DEFAULT NULL,
  `emailid` varchar(45) NOT NULL,
  `Address` varchar(45) DEFAULT NULL,
  `Pincode` varchar(10) DEFAULT NULL,
  `Dob` datetime DEFAULT NULL,
  `first_name` varchar(100) DEFAULT NULL,
  `vendor_establishment_id` varchar(100) DEFAULT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `INITIALS` varchar(10) DEFAULT NULL,
  `SUPERVISOR_ID` int(11) DEFAULT NULL,
  `ACTIVE_IND` tinyint(1) DEFAULT NULL,
  `device_token` varchar(40) DEFAULT NULL,
  `Last_Login_Date` date DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `vendor_establishment_name` varchar(200) DEFAULT NULL,
  `country_code` varchar(5) DEFAULT NULL,
  `country` varchar(100) DEFAULT NULL,
  `activation_code` varchar(50) DEFAULT NULL,
  `validation_date` date DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

/*Table structure for table `visitor` */

DROP TABLE IF EXISTS `visitor`;

CREATE TABLE `visitor` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `city` varchar(45) DEFAULT NULL,
  `Phone` varchar(20) NOT NULL,
  `emailid` varchar(45) DEFAULT NULL,
  `Address` varchar(45) DEFAULT NULL,
  `Pincode` varchar(10) DEFAULT NULL,
  `Dob` date DEFAULT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `INITIALS` varchar(10) DEFAULT NULL,
  `visit_Date` datetime DEFAULT NULL,
  `country_code` varchar(5) DEFAULT NULL,
  `country` varchar(100) DEFAULT NULL,
  `created_by` int(10) NOT NULL,
  `vendor_establishment_id` varchar(100) NOT NULL,
  `vendor_establishment_name` varchar(200) NOT NULL,
  `vendor_establishment_location` varchar(300) NOT NULL,
  `profession` varchar(200) DEFAULT NULL,
  `remarks` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
