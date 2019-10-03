-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 04, 2019 at 01:25 AM
-- Server version: 10.4.6-MariaDB
-- PHP Version: 7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `elecciones`
--

-- --------------------------------------------------------

--
-- Table structure for table `ciudadano`
--

CREATE TABLE `ciudadano` (
  `id` int(11) NOT NULL,
  `dni` int(11) NOT NULL,
  `apellidos` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `nombres` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `ubigeo` int(11) NOT NULL,
  `direccion` varchar(128) COLLATE utf8_spanish_ci DEFAULT NULL,
  `sexo` char(1) COLLATE utf8_spanish_ci DEFAULT NULL,
  `estadocivil` char(1) COLLATE utf8_spanish_ci DEFAULT NULL,
  `candidato` tinyint(1) NOT NULL DEFAULT 0,
  `miembromesa` tinyint(1) DEFAULT 0,
  `usuario` varchar(64) COLLATE utf8_spanish_ci NOT NULL,
  `clave` varchar(64) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `ciudadano`
--

INSERT INTO `ciudadano` (`id`, `dni`, `apellidos`, `nombres`, `ubigeo`, `direccion`, `sexo`, `estadocivil`, `candidato`, `miembromesa`, `usuario`, `clave`) VALUES
(1, 0, '', 'admin', 1, NULL, 'M', 'S', 0, NULL, 'admin', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `ubigeo`
--

CREATE TABLE `ubigeo` (
  `id` int(11) NOT NULL,
  `nombre` varchar(64) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `ubigeo`
--

INSERT INTO `ubigeo` (`id`, `nombre`) VALUES
(1, 'Arequipa'),
(2, 'Camaná'),
(3, 'Caravelí'),
(4, 'Castilla'),
(5, 'Caylloma'),
(6, 'Condesuyos'),
(7, 'Islay'),
(8, 'La Unión');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `ciudadano`
--
ALTER TABLE `ciudadano`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `dni` (`dni`),
  ADD KEY `ubigeo` (`ubigeo`);

--
-- Indexes for table `ubigeo`
--
ALTER TABLE `ubigeo`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `ciudadano`
--
ALTER TABLE `ciudadano`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `ubigeo`
--
ALTER TABLE `ubigeo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `ciudadano`
--
ALTER TABLE `ciudadano`
  ADD CONSTRAINT `ciudadano_ibfk_1` FOREIGN KEY (`ubigeo`) REFERENCES `ubigeo` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
