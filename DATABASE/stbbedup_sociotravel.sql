-- phpMyAdmin SQL Dump
-- version 4.0.8
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Feb 12, 2014 at 04:19 AM
-- Server version: 5.5.34-MariaDB-cll-lve
-- PHP Version: 5.3.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `stbbedup_sociotravel`
--

-- --------------------------------------------------------

--
-- Table structure for table `city`
--

CREATE TABLE IF NOT EXISTS `city` (
  `CITY_ID` int(11) NOT NULL AUTO_INCREMENT,
  `COUNTRY_ID` int(11) NOT NULL,
  `CITY_NAME` varchar(100) NOT NULL,
  `REMARKS` varchar(100) NOT NULL,
  PRIMARY KEY (`CITY_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=30 ;

--
-- Dumping data for table `city`
--

INSERT INTO `city` (`CITY_ID`, `COUNTRY_ID`, `CITY_NAME`, `REMARKS`) VALUES
(1, 1, 'HYDERABAD', ''),
(2, 1, 'KARACHI', ''),
(3, 1, 'BADIN', ''),
(4, 1, 'SUKKHUR', ''),
(5, 1, 'LARKANA', ''),
(6, 1, 'MIRPUR', ''),
(7, 1, 'SANGHAR', ''),
(8, 1, 'UMERKOT', ''),
(9, 1, 'TANDOALLAHYAR', ''),
(10, 1, 'LAHORE', ''),
(11, 1, 'MULTAN', ''),
(12, 1, 'PISHAWAR', ''),
(13, 1, 'TANDOJAM', ''),
(14, 1, 'MATYARI', ''),
(15, 1, 'KHIPRO', ''),
(16, 1, 'SHEHDADPUR', ''),
(17, 1, 'NAWABSHAH', ''),
(18, 1, 'ROHRI', ''),
(19, 1, 'MITHI', ''),
(20, 3, 'DHAKA', ''),
(21, 3, 'CHITTAGONG', ''),
(22, 3, 'KHULNA', ''),
(23, 3, 'BAISAL', ''),
(24, 2, 'Mumbai', ''),
(25, 2, 'Delhi', ''),
(26, 2, 'Agra', ''),
(27, 2, 'Jaipur', ''),
(28, 2, 'Nagpur', ''),
(29, 2, 'Ahmedabad', '');

-- --------------------------------------------------------

--
-- Table structure for table `country`
--

CREATE TABLE IF NOT EXISTS `country` (
  `COUNTRY_ID` int(11) NOT NULL AUTO_INCREMENT,
  `COUNTRY_NAME` varchar(60) NOT NULL,
  `COUNTRY_CODE` varchar(4) NOT NULL,
  `REMARKS` varchar(100) NOT NULL,
  PRIMARY KEY (`COUNTRY_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `country`
--

INSERT INTO `country` (`COUNTRY_ID`, `COUNTRY_NAME`, `COUNTRY_CODE`, `REMARKS`) VALUES
(1, 'PAKISTAN', '0092', ''),
(2, 'INDIA', '0091', ''),
(3, 'BANGLADESH', '880', '');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE IF NOT EXISTS `customer` (
  `CUSTOMER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `FIRST_NAME` varchar(100) NOT NULL,
  `LAST_NAME` varchar(100) NOT NULL,
  `CONTACT_NO` varchar(100) NOT NULL,
  `ADDRESS` varchar(200) NOT NULL,
  `EMAIL` varchar(100) NOT NULL,
  `USER_PASSWORD` varchar(100) NOT NULL,
  `IS_ACTIVATED` tinyint(1) NOT NULL,
  `CODE` varchar(30) NOT NULL,
  PRIMARY KEY (`CUSTOMER_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=22 ;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`CUSTOMER_ID`, `FIRST_NAME`, `LAST_NAME`, `CONTACT_NO`, `ADDRESS`, `EMAIL`, `USER_PASSWORD`, `IS_ACTIVATED`, `CODE`) VALUES
(1, 'JAY', 'KUMAR', '03332836704', 'HYDERABAD', 'JAY', 'JAY', 1, ''),
(10, 'jibran', 'joyo', '123456', 'yyyy', 'SACHALJOYO@YAHOO.COM', '123456', 1, ''),
(19, 'Raja', 'Kumar', '03332836705', 'hyderabad', 'PERFECTMASTER123@GMAIL.COM', 'perfectmaster', 1, ''),
(21, 'Muhammad', 'Ameen', '03312049101', 'hyderabad', 'AMIN.PANHWER2010@GMAIL.COM', 'amin', 1, '');

-- --------------------------------------------------------

--
-- Table structure for table `sharing`
--

CREATE TABLE IF NOT EXISTS `sharing` (
  `SHARING_ID` int(11) NOT NULL AUTO_INCREMENT,
  `VEHICLE_ID` int(11) NOT NULL,
  `CUSTOMER_ID` int(11) NOT NULL,
  `ARIVAL_CITY_ID` int(11) NOT NULL,
  `DESTINATION_CITY_ID` int(11) NOT NULL,
  `ARIVAL_DATE` date NOT NULL,
  `ARIVAL_TIME` time NOT NULL,
  `FEATURE` varchar(100) NOT NULL,
  PRIMARY KEY (`SHARING_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=17 ;

--
-- Dumping data for table `sharing`
--

INSERT INTO `sharing` (`SHARING_ID`, `VEHICLE_ID`, `CUSTOMER_ID`, `ARIVAL_CITY_ID`, `DESTINATION_CITY_ID`, `ARIVAL_DATE`, `ARIVAL_TIME`, `FEATURE`) VALUES
(1, 2, 1, 14, 1, '2013-09-23', '19:32:00', ''),
(2, 2, 1, 5, 1, '2013-09-23', '19:37:00', 'AC'),
(5, 2, 11, 3, 1, '2013-09-28', '13:13:00', ''),
(6, 2, 11, 2, 1, '2013-09-28', '13:16:00', ''),
(7, 2, 11, 1, 15, '2013-09-28', '13:17:00', ''),
(8, 1, 1, 3, 2, '2013-11-25', '09:11:00', ''),
(9, 2, 1, 1, 2, '2013-11-25', '09:11:00', ''),
(10, 2, 1, 1, 2, '2013-11-25', '09:10:00', ''),
(11, 2, 1, 1, 2, '2013-11-25', '09:10:00', ''),
(12, 1, 1, 1, 2, '2014-01-07', '12:45:00', ''),
(13, 2, 21, 23, 23, '2014-03-10', '12:05:00', ''),
(14, 3, 21, 2, 5, '2014-01-07', '17:18:00', ''),
(15, 4, 1, 27, 28, '2014-02-10', '18:11:00', 'ax'),
(16, 1, 21, 23, 23, '2014-01-10', '18:08:00', 'ac');

-- --------------------------------------------------------

--
-- Table structure for table `vehicle`
--

CREATE TABLE IF NOT EXISTS `vehicle` (
  `VEHICLE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `VEHICLE_NAME` varchar(50) NOT NULL,
  `REMARKS` varchar(100) NOT NULL,
  PRIMARY KEY (`VEHICLE_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `vehicle`
--

INSERT INTO `vehicle` (`VEHICLE_ID`, `VEHICLE_NAME`, `REMARKS`) VALUES
(1, 'CAR', ''),
(2, 'BUS', ''),
(3, 'VAN', ''),
(4, 'HI ROOF', ''),
(5, 'COACH', ''),
(6, 'TRUCK', '');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
