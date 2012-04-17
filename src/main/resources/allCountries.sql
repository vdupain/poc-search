-- phpMyAdmin SQL Dump
-- version 3.4.5deb1
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le : Ven 13 Avril 2012 à 11:13
-- Version du serveur: 5.1.61
-- Version de PHP: 5.3.6-13ubuntu3.6

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `poc-search`
--

-- --------------------------------------------------------

--
-- Structure de la table `allCountries`
--

CREATE TABLE IF NOT EXISTS `allCountries` (
  `geo_id` int(11) unsigned NOT NULL,
  `geo_name` varchar(200) NOT NULL DEFAULT '',
  `geo_ansiname` varchar(200) NOT NULL DEFAULT '',
  `geo_alternate_names` varchar(6000) NOT NULL DEFAULT '',
  `geo_latitude` double(11,7) NOT NULL DEFAULT '0.0000000',
  `geo_longitude` double(11,7) NOT NULL DEFAULT '0.0000000',
  `geo_feature_class` char(1) DEFAULT NULL,
  `geo_feature_code` varchar(10) DEFAULT NULL,
  `geo_country_code` char(2) DEFAULT NULL,
  `geo_country_code2` varchar(60) DEFAULT NULL,
  `geo_admin1_code` varchar(20) DEFAULT '',
  `geo_admin2_code` varchar(80) DEFAULT '',
  `geo_admin3_code` varchar(20) DEFAULT '',
  `geo_admin4_code` varchar(20) DEFAULT '',
  `geo_population` bigint(11) DEFAULT '0',
  `geo_elevation` int(11) DEFAULT '0',
  `geo_gtopo30` int(11) DEFAULT '0',
  `geo_timezone` varchar(40) DEFAULT NULL,
  `geo_mod_date` date DEFAULT '0000-00-00',
  PRIMARY KEY (`geo_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
