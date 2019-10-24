-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Oct 24, 2019 at 12:40 PM
-- Server version: 10.1.40-MariaDB
-- PHP Version: 7.3.5

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
  `candidato` tinyint(1) NOT NULL DEFAULT '0',
  `miembromesa` tinyint(1) DEFAULT '0',
  `usuario` varchar(64) COLLATE utf8_spanish_ci NOT NULL,
  `clave` varchar(64) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `ciudadano`
--

INSERT INTO `ciudadano` (`id`, `dni`, `apellidos`, `nombres`, `ubigeo`, `direccion`, `sexo`, `estadocivil`, `candidato`, `miembromesa`, `usuario`, `clave`) VALUES
(1, 0, '', 'admin', 1, NULL, 'M', 'S', 0, NULL, 'admin', 'admin'),
(2, 40205952, 'ABAD VILLACREZ', 'CESAR HUMBERTO', 1, 'Príncipe de Vergara 36, 5º derecha', 'M', 'S', 0, 1, '40205952', '40205952'),
(3, 45106970, 'ABAD SEGURA', 'MARIA DEL PILAR', 1, 'Calle Cristobal Bordiú 49', 'F', 'S', 0, 0, '45106970', '45106970'),
(4, 46177490, 'ABAD SERNAQUE', 'FIORELLA YVONNE', 2, 'Avenida de Roma 157, 1ra planta', 'F', 'C', 0, 1, '46177490', '46177490'),
(5, 73572529, 'ABAD RAZURI', 'ANALIA', 3, 'Jorge Basadre 498', 'F', 'S', 0, 0, '73572529', '73572529'),
(6, 71535753, 'ABAD MUÑOZ', 'YUTSIAM JANDIRE', 2, 'Calle Los Pinos, 490, San Isidro', 'F', 'C', 0, 0, '71535753', '71535753'),
(7, 43465561, 'ABAD ALBERCA', 'JOSE CELIS', 5, 'Avenida Arequipa 3415', 'M', 'S', 0, 0, '43465561', '43465561'),
(8, 42854895, 'ABAD CAUTI', 'JANNETTE CAROL', 4, 'av MARISCAL URETA 1420 COSTADO DE MI BANCO', 'F', 'S', 0, 0, '42854895', '42854895'),
(9, 70466504, 'ABAD VILLACREZ', 'DESSIRE MILAGROS', 3, 'Urb. El Pacífico Mz. E – Lote 1', 'F', 'D', 0, 1, '70466504', '70466504'),
(10, 71799383, 'ABAD VILLACREZ', 'ANAROSS ROSARIO', 1, 'Av. Universitaria 4265, San Martín de Porres 15109', 'F', 'C', 0, 0, '71799383', '71799383');

-- --------------------------------------------------------

--
-- Table structure for table `mesa`
--

CREATE TABLE `mesa` (
  `id` int(11) NOT NULL,
  `ubigeo` int(11) DEFAULT NULL,
  `miembrouno` int(11) DEFAULT NULL,
  `miembrodos` int(11) DEFAULT NULL,
  `miembrotres` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `mesa`
--

INSERT INTO `mesa` (`id`, `ubigeo`, `miembrouno`, `miembrodos`, `miembrotres`) VALUES
(1, 1, NULL, NULL, NULL),
(2, 2, NULL, NULL, NULL),
(3, 3, NULL, NULL, NULL),
(4, 4, NULL, NULL, NULL),
(5, 5, NULL, NULL, NULL),
(6, 6, NULL, NULL, NULL),
(7, 7, NULL, NULL, NULL),
(8, 8, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `partido`
--

CREATE TABLE `partido` (
  `id` int(11) NOT NULL,
  `nombre` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `imagen` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL,
  `ciudadano` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

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
-- Indexes for table `mesa`
--
ALTER TABLE `mesa`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_ubigeo` (`ubigeo`),
  ADD KEY `fk_miembrouno` (`miembrouno`),
  ADD KEY `fk_miembrodos` (`miembrodos`),
  ADD KEY `fk_miembrotres` (`miembrotres`);

--
-- Indexes for table `partido`
--
ALTER TABLE `partido`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_ciudadano` (`ciudadano`);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `mesa`
--
ALTER TABLE `mesa`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `partido`
--
ALTER TABLE `partido`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

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

--
-- Constraints for table `mesa`
--
ALTER TABLE `mesa`
  ADD CONSTRAINT `fk_miembrodos` FOREIGN KEY (`miembrodos`) REFERENCES `ciudadano` (`id`),
  ADD CONSTRAINT `fk_miembrotres` FOREIGN KEY (`miembrotres`) REFERENCES `ciudadano` (`id`),
  ADD CONSTRAINT `fk_miembrouno` FOREIGN KEY (`miembrouno`) REFERENCES `ciudadano` (`id`),
  ADD CONSTRAINT `fk_ubigeo` FOREIGN KEY (`ubigeo`) REFERENCES `ubigeo` (`id`);

--
-- Constraints for table `partido`
--
ALTER TABLE `partido`
  ADD CONSTRAINT `fk_ciudadano` FOREIGN KEY (`ciudadano`) REFERENCES `ciudadano` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
