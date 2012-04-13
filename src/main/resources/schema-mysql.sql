-- phpMyAdmin SQL Dump
-- version 3.4.5deb1
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le : Jeu 12 Avril 2012 à 17:16
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
-- Structure de la table `personnephysique`
--

CREATE TABLE IF NOT EXISTS `personnephysique` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `codePostal` char(5) NOT NULL,
  `pays` varchar(255) NOT NULL,
  `dateNaissance` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `nomPrenom` (`nom`,`prenom`),
  KEY `nomPrenomCodePostal` (`nom`,`prenom`,`codePostal`),
  KEY `nomPays` (`nom`,`pays`),
  KEY `nomCodePostal` (`nom`,`codePostal`),
  KEY `nomPrenomPays` (`nom`,`prenom`,`pays`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10000051 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;