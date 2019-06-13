-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  jeu. 13 juin 2019 à 19:46
-- Version du serveur :  10.1.37-MariaDB
-- Version de PHP :  7.3.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `polyauto`
--

GRANT ALL PRIVILEGES ON *.* TO 'polyauto'@'localhost' IDENTIFIED BY 'polyauto';
GRANT ALL PRIVILEGES ON *.* TO 'polyautojms'@'localhost' IDENTIFIED BY 'polyautojms';

CREATE DATABASE IF NOT EXISTS `polyauto` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `polyauto`;

-- --------------------------------------------------------

--
-- Structure de la table `bookings`
--

CREATE TABLE `bookings` (
  `idBooking` int(11) NOT NULL,
  `idCar` int(11) NOT NULL,
  `idUser` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0',
  `dateUp` date NOT NULL,
  `dateDown` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `bookings`
--

INSERT INTO `bookings` (`idBooking`, `idCar`, `idUser`, `status`, `dateUp`, `dateDown`) VALUES
(1, 1, 1, 1, '2019-06-14', NULL),
(3, 3, 2, 0, '2019-05-28', '2019-05-30'),
(4, 6, 2, 0, '2019-05-28', '2019-05-30'),
(5, 1, 3, 0, '2019-05-28', '2019-05-30');

-- --------------------------------------------------------

--
-- Structure de la table `cars`
--

CREATE TABLE `cars` (
  `idCar` int(11) NOT NULL,
  `model` int(11) NOT NULL,
  `lat` double NOT NULL,
  `lon` double NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `cars`
--

INSERT INTO `cars` (`idCar`, `model`, `lat`, `lon`, `status`) VALUES
(1, 1, 45.75589542, 4.87421094, 0),
(2, 2, 45.76209157, 4.77712804, 1),
(3, 1, 45.73965628, 4.8037043, 1),
(4, 1, 45.76735786, 4.82275812, 1),
(5, 3, 45.7563179, 4.8646874, 1),
(6, 1, 45.72673037, 4.84042276, 1),
(7, 2, 45.78220555, 4.81869191, 1),
(8, 2, 45.76295016, 4.87354974, 1),
(9, 2, 45.76985644, 4.77283071, 1),
(10, 2, 45.73498713, 4.78586705, 1),
(11, 3, 45.77432256, 4.8539145, 1),
(12, 1, 45.76515405, 4.78798041, 1),
(13, 2, 45.71421192, 4.81771726, 1),
(14, 3, 45.73928876, 4.87603509, 1),
(15, 2, 45.76154415, 4.80863502, 1),
(16, 3, 45.71597041, 4.82900188, 1),
(17, 3, 45.75615414, 4.7890848, 1),
(18, 2, 45.77415352, 4.86805378, 1),
(19, 1, 45.7689912, 4.86125139, 1),
(20, 2, 45.78959908, 4.79771455, 1),
(21, 2, 45.75008547, 4.84158734, 1),
(22, 3, 45.76047375, 4.86692016, 1),
(23, 1, 45.79577124, 4.8266001, 1),
(24, 3, 45.77509627, 4.80794942, 1),
(25, 1, 45.75433744, 4.87940834, 1),
(26, 2, 45.77923908, 4.78928664, 1),
(27, 3, 45.71684176, 4.8086178, 1),
(28, 1, 45.75990185, 4.85637847, 1),
(29, 3, 45.74848061, 4.88142761, 1),
(30, 1, 45.74692093, 4.7709508, 1),
(31, 2, 45.77036741, 4.85056777, 1),
(32, 1, 45.76626667, 4.84017935, 1),
(33, 3, 45.77141752, 4.87280148, 1),
(34, 2, 45.76785639, 4.84850385, 1),
(35, 2, 45.77470773, 4.84418075, 1),
(36, 1, 45.74418001, 4.86743892, 1),
(37, 1, 45.73003086, 4.86355336, 1),
(38, 3, 45.7393473, 4.84937898, 1),
(39, 1, 45.73728948, 4.81433088, 1),
(40, 2, 45.74110698, 4.83535155, 1),
(41, 3, 45.74604647, 4.88368602, 1),
(42, 3, 45.73837677, 4.82921146, 1),
(43, 1, 45.77264447, 4.89281848, 1),
(44, 2, 45.73827627, 4.78404952, 1),
(45, 2, 45.7588672, 4.78921158, 1),
(46, 3, 45.79218411, 4.81291695, 1),
(47, 2, 45.74554788, 4.86833211, 1),
(48, 2, 45.73488919, 4.87140611, 1),
(49, 2, 45.75367431, 4.78323094, 1),
(50, 3, 45.78245103, 4.78616717, 1);

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `idUser` int(11) NOT NULL,
  `login` varchar(32) NOT NULL,
  `password` varchar(64) NOT NULL,
  `firstname` varchar(32) NOT NULL,
  `lastname` varchar(32) NOT NULL,
  `note` int(11) NOT NULL,
  `admin` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`idUser`, `login`, `password`, `firstname`, `lastname`, `note`, `admin`) VALUES
(1, 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'ADMIN', 'DEV', 5, 1),
(2, 'tommy', '044f4b3501cd8e8131d40c057893f4fdff66bf4032ecae159e0c892a28cf6c8e', 'Tom', 'Basil', 3, 0),
(3, 'vial', 'b107fb284c075897508e7f8dff929ac89b3f1067bdce0f25687ab657e5ee7d0d', 'Christian', 'Vial', 7, 0),
(4, 'Elie', '281dc093e8ea1bd931774d7a28dccb50e3a307756b7ff07bad897f10a56bfde0', 'Elie', 'Ensuque', 20, 0);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `bookings`
--
ALTER TABLE `bookings`
  ADD PRIMARY KEY (`idBooking`),
  ADD KEY `FK_Car` (`idCar`),
  ADD KEY `FK_User` (`idUser`);

--
-- Index pour la table `cars`
--
ALTER TABLE `cars`
  ADD PRIMARY KEY (`idCar`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`idUser`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `bookings`
--
ALTER TABLE `bookings`
  MODIFY `idBooking` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `cars`
--
ALTER TABLE `cars`
  MODIFY `idCar` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `idUser` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `bookings`
--
ALTER TABLE `bookings`
  ADD CONSTRAINT `FK_Car` FOREIGN KEY (`idCar`) REFERENCES `cars` (`idCar`),
  ADD CONSTRAINT `FK_User` FOREIGN KEY (`idUser`) REFERENCES `users` (`idUser`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
