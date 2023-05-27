-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 27, 2023 at 02:04 PM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `uzsakymovaldymas`
--

-- --------------------------------------------------------

--
-- Table structure for table `darbuotojas`
--

CREATE TABLE `darbuotojas` (
  `DARBUOTOJOID` int(6) NOT NULL,
  `VARDAS` char(30) COLLATE utf32_lithuanian_ci DEFAULT NULL,
  `PAVARDE` char(30) COLLATE utf32_lithuanian_ci DEFAULT NULL,
  `SLAPTAZODIS` char(30) COLLATE utf32_lithuanian_ci DEFAULT NULL,
  `POZICIJA` char(20) COLLATE utf32_lithuanian_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_lithuanian_ci;

--
-- Dumping data for table `darbuotojas`
--

INSERT INTO `darbuotojas` (`DARBUOTOJOID`, `VARDAS`, `PAVARDE`, `SLAPTAZODIS`, `POZICIJA`) VALUES
(274, 'buh', 'Einingis', '123', 'Buhalteris'),
(275, 'san', 'delis', '1234', 'Sandėlio darbuotojas'),
(276, 'kon', 'sultantas', '321', 'Konsultantas'),
(277, 'test', NULL, 'test', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `grazina`
--

CREATE TABLE `grazina` (
  `PREKESID` int(11) NOT NULL,
  `GRAZINIMOID` int(11) NOT NULL,
  `KIEKIS` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_lithuanian_ci;

-- --------------------------------------------------------

--
-- Table structure for table `grazinimas`
--

CREATE TABLE `grazinimas` (
  `GRAZINIMOID` int(6) NOT NULL,
  `UZSAKYMOID` int(11) NOT NULL,
  `GRAZINIMODATA` date DEFAULT NULL,
  `GRAZINIMOSUMA` decimal(10,0) DEFAULT NULL,
  `GRAZINIMOBUSENA` char(30) COLLATE utf32_lithuanian_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_lithuanian_ci;

-- --------------------------------------------------------

--
-- Table structure for table `klientas`
--

CREATE TABLE `klientas` (
  `KLIENTOID` int(6) NOT NULL,
  `VARDAS` char(30) COLLATE utf32_lithuanian_ci DEFAULT NULL,
  `PAVARDE` char(30) COLLATE utf32_lithuanian_ci DEFAULT NULL,
  `TELNUMERIS` char(12) COLLATE utf32_lithuanian_ci DEFAULT NULL,
  `MIESTAS` char(40) COLLATE utf32_lithuanian_ci DEFAULT NULL,
  `ADRESAS` char(100) COLLATE utf32_lithuanian_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_lithuanian_ci;

--
-- Dumping data for table `klientas`
--

INSERT INTO `klientas` (`KLIENTOID`, `VARDAS`, `PAVARDE`, `TELNUMERIS`, `MIESTAS`, `ADRESAS`) VALUES
(271, 'Jonas', 'Jonaitis', '860350957', 'Vilnius', 'gatve 1123'),
(272, 'Petras', 'Petraitis', '+37069888', 'Siauliai', 'Kauno 1'),
(273, 'Onute', 'Onyte', '86995599', 'Grybiskes', 'Grybiskiu 13'),
(303, 'testas', 'testukas', NULL, 'Vilnius', 'gatve 12');

-- --------------------------------------------------------

--
-- Table structure for table `preke`
--

CREATE TABLE `preke` (
  `PREKESID` int(6) NOT NULL,
  `RUSIESID` int(11) NOT NULL,
  `PAVADINIMAS` char(40) COLLATE utf32_lithuanian_ci DEFAULT NULL,
  `SPALVA` char(40) COLLATE utf32_lithuanian_ci DEFAULT NULL,
  `ISMATAVIMAS` char(30) COLLATE utf32_lithuanian_ci DEFAULT NULL,
  `SAVIKAINA` decimal(10,2) DEFAULT NULL,
  `KAINA` decimal(10,2) DEFAULT NULL,
  `KIEKIS` int(11) DEFAULT NULL,
  `sum` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_lithuanian_ci;

--
-- Dumping data for table `preke`
--

INSERT INTO `preke` (`PREKESID`, `RUSIESID`, `PAVADINIMAS`, `SPALVA`, `ISMATAVIMAS`, `SAVIKAINA`, `KAINA`, `KIEKIS`, `sum`) VALUES
(288, 277, '3D', 'Paveiksliukai', '200x220', '8.00', '10.50', 2, NULL),
(289, 277, 'Pliušas', 'Žalia', '200x220', '25.00', '32.50', 7, NULL),
(290, 278, 'Vyriškos', 'pilka', '40-42', '3.50', '5.00', 18, NULL),
(291, 278, 'Kalėdinės', 'Raudona', '38-40', '5.00', '9.50', 10, NULL),
(292, 279, 'Paprasti', 'Geltona', '180x200', '15.30', '18.50', 15, NULL),
(293, 279, 'Gauruoti', 'Ruda', '200x220', '23.50', '30.40', 6, NULL),
(294, 280, 'Vilnos', 'Marga', '200x220', '20.50', '28.00', 0, NULL),
(295, 280, 'Vasarinės', 'Marga', '180x200', '10.50', '15.00', 12, NULL),
(296, 281, 'N11', 'Juoda', 'L', '8.00', '10.50', 0, NULL),
(297, 281, 'N15', 'Raudona', 'M', '7.00', '10.00', 3, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `prekiurusis`
--

CREATE TABLE `prekiurusis` (
  `RUSIESID` int(6) NOT NULL,
  `RUSIESPAVADINIMAS` char(40) COLLATE utf32_lithuanian_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_lithuanian_ci;

--
-- Dumping data for table `prekiurusis`
--

INSERT INTO `prekiurusis` (`RUSIESID`, `RUSIESPAVADINIMAS`) VALUES
(277, 'Patalynė'),
(278, 'Kojinės'),
(279, 'Pledai'),
(280, 'Antklodės'),
(281, 'G11');

-- --------------------------------------------------------

--
-- Table structure for table `sandeliuojama`
--

CREATE TABLE `sandeliuojama` (
  `PREKESID` int(11) NOT NULL,
  `VIETOSID` int(11) NOT NULL,
  `KIEKIS` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_lithuanian_ci;

--
-- Dumping data for table `sandeliuojama`
--

INSERT INTO `sandeliuojama` (`PREKESID`, `VIETOSID`, `KIEKIS`) VALUES
(288, 286, 5),
(288, 287, 11),
(289, 283, 3),
(289, 287, 1);

-- --------------------------------------------------------

--
-- Table structure for table `turi`
--

CREATE TABLE `turi` (
  `PREKESID` int(11) NOT NULL,
  `UZSAKYMOID` int(11) NOT NULL,
  `KIEKIS` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_lithuanian_ci;

--
-- Dumping data for table `turi`
--

INSERT INTO `turi` (`PREKESID`, `UZSAKYMOID`, `KIEKIS`) VALUES
(288, 298, 1),
(288, 299, 5),
(288, 304, 10),
(288, 311, 5),
(288, 312, 2),
(288, 314, 2),
(288, 316, 2),
(289, 309, 1),
(290, 299, 1),
(290, 310, 2),
(291, 298, 2),
(293, 299, 3),
(294, 311, 2),
(294, 312, 1),
(294, 314, 1),
(296, 304, 1),
(297, 310, 1);

-- --------------------------------------------------------

--
-- Table structure for table `uzsakymas`
--

CREATE TABLE `uzsakymas` (
  `UZSAKYMOID` int(6) NOT NULL,
  `KLIENTOID` int(11) NOT NULL,
  `DARBUOTOJOID` int(11) NOT NULL,
  `GRAZINIMOID` int(11) DEFAULT NULL,
  `BUSENA` char(20) COLLATE utf32_lithuanian_ci DEFAULT NULL,
  `APMOKETAS` tinyint(1) DEFAULT NULL,
  `PRISTATYMOADRESAS` char(100) COLLATE utf32_lithuanian_ci DEFAULT NULL,
  `PRISTATYMOBUDAS` char(30) COLLATE utf32_lithuanian_ci DEFAULT NULL,
  `DATA` date DEFAULT NULL,
  `UZRASAI` char(100) COLLATE utf32_lithuanian_ci DEFAULT NULL,
  `SUMA` decimal(10,2) DEFAULT NULL,
  `MIESTAS` char(25) COLLATE utf32_lithuanian_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_lithuanian_ci;

--
-- Dumping data for table `uzsakymas`
--

INSERT INTO `uzsakymas` (`UZSAKYMOID`, `KLIENTOID`, `DARBUOTOJOID`, `GRAZINIMOID`, `BUSENA`, `APMOKETAS`, `PRISTATYMOADRESAS`, `PRISTATYMOBUDAS`, `DATA`, `UZRASAI`, `SUMA`, `MIESTAS`) VALUES
(298, 271, 276, NULL, 'pristatytas', 0, 'gatve1', 'kurjeris', '2023-03-01', NULL, '25.50', 'Telsiai'),
(299, 272, 276, NULL, 'sukurtas', 0, 'gatve2', 'Atvaziuos', '2023-03-05', NULL, '50.00', 'Vilnius'),
(300, 272, 276, NULL, 'sukurtas', 1, 'gatve3', 'kurjeris', '2023-04-15', NULL, '25.00', 'Vilnius'),
(301, 273, 276, NULL, 'pristatytas', 1, 'gatve3', 'kurjeris', '2023-01-21', NULL, '100.00', 'Siauliai'),
(304, 303, 275, NULL, 'sukurtas', 0, 'gatve 12123', 'pastu', '2023-04-28', NULL, '115.50', 'Vilnius123'),
(309, 272, 277, NULL, NULL, 0, '', '', '2023-05-01', NULL, '32.50', ''),
(310, 273, 277, NULL, NULL, 0, '', 'Paštu', '2023-05-10', NULL, '20.00', 'Vilnius'),
(311, 273, 276, NULL, NULL, 0, '', 'Paštu', '2023-05-10', NULL, '108.50', 'Vilnius'),
(312, 272, 276, NULL, NULL, 0, '', 'Paštu', '2023-05-10', NULL, '49.00', 'Telšiai'),
(314, 272, 276, NULL, NULL, 0, 'Žirmūnai 131', '', '2023-05-10', NULL, '49.00', 'Vilnius'),
(316, 271, 277, NULL, NULL, 0, '', '', '2023-05-27', NULL, '21.00', '');

-- --------------------------------------------------------

--
-- Table structure for table `vieta`
--

CREATE TABLE `vieta` (
  `VIETOSID` int(6) NOT NULL,
  `SANDELIS` char(40) COLLATE utf32_lithuanian_ci DEFAULT NULL,
  `LENTYNA` char(30) COLLATE utf32_lithuanian_ci DEFAULT NULL,
  `VIETA` char(30) COLLATE utf32_lithuanian_ci DEFAULT NULL,
  `PILNA` tinyint(1) DEFAULT NULL,
  `Quantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_lithuanian_ci;

--
-- Dumping data for table `vieta`
--

INSERT INTO `vieta` (`VIETOSID`, `SANDELIS`, `LENTYNA`, `VIETA`, `PILNA`, `Quantity`) VALUES
(282, 'nr2', 'k15', '1', 0, NULL),
(283, 'nr2', 'k15', '2', 0, NULL),
(284, 'nr2', 'k15', '3', 1, NULL),
(285, 'nr2', 'g5', '1', 0, NULL),
(286, 'nr2', 'k15', '5', 1, NULL),
(287, 'nr2', 'k15', '9', 0, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `darbuotojas`
--
ALTER TABLE `darbuotojas`
  ADD PRIMARY KEY (`DARBUOTOJOID`);

--
-- Indexes for table `grazina`
--
ALTER TABLE `grazina`
  ADD PRIMARY KEY (`PREKESID`,`GRAZINIMOID`),
  ADD KEY `FK_GRAZINA2` (`GRAZINIMOID`);

--
-- Indexes for table `grazinimas`
--
ALTER TABLE `grazinimas`
  ADD PRIMARY KEY (`GRAZINIMOID`),
  ADD KEY `FK_ATLIEKAMAS2` (`UZSAKYMOID`);

--
-- Indexes for table `klientas`
--
ALTER TABLE `klientas`
  ADD PRIMARY KEY (`KLIENTOID`);

--
-- Indexes for table `preke`
--
ALTER TABLE `preke`
  ADD PRIMARY KEY (`PREKESID`),
  ADD KEY `FK_PRIKLAUSO` (`RUSIESID`);

--
-- Indexes for table `prekiurusis`
--
ALTER TABLE `prekiurusis`
  ADD PRIMARY KEY (`RUSIESID`);

--
-- Indexes for table `sandeliuojama`
--
ALTER TABLE `sandeliuojama`
  ADD PRIMARY KEY (`PREKESID`,`VIETOSID`),
  ADD KEY `FK_SANDELIUOJAMA2` (`VIETOSID`);

--
-- Indexes for table `turi`
--
ALTER TABLE `turi`
  ADD PRIMARY KEY (`PREKESID`,`UZSAKYMOID`),
  ADD KEY `FK_TURI2` (`UZSAKYMOID`);

--
-- Indexes for table `uzsakymas`
--
ALTER TABLE `uzsakymas`
  ADD PRIMARY KEY (`UZSAKYMOID`),
  ADD KEY `FK_ATLIEKAMAS` (`GRAZINIMOID`),
  ADD KEY `FK_PATEIKE` (`KLIENTOID`),
  ADD KEY `FK_SUKURE` (`DARBUOTOJOID`);

--
-- Indexes for table `vieta`
--
ALTER TABLE `vieta`
  ADD PRIMARY KEY (`VIETOSID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `darbuotojas`
--
ALTER TABLE `darbuotojas`
  MODIFY `DARBUOTOJOID` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=278;

--
-- AUTO_INCREMENT for table `grazinimas`
--
ALTER TABLE `grazinimas`
  MODIFY `GRAZINIMOID` int(6) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `klientas`
--
ALTER TABLE `klientas`
  MODIFY `KLIENTOID` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=304;

--
-- AUTO_INCREMENT for table `preke`
--
ALTER TABLE `preke`
  MODIFY `PREKESID` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=298;

--
-- AUTO_INCREMENT for table `prekiurusis`
--
ALTER TABLE `prekiurusis`
  MODIFY `RUSIESID` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=282;

--
-- AUTO_INCREMENT for table `uzsakymas`
--
ALTER TABLE `uzsakymas`
  MODIFY `UZSAKYMOID` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=317;

--
-- AUTO_INCREMENT for table `vieta`
--
ALTER TABLE `vieta`
  MODIFY `VIETOSID` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=288;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `grazina`
--
ALTER TABLE `grazina`
  ADD CONSTRAINT `FK_GRAZINA` FOREIGN KEY (`PREKESID`) REFERENCES `preke` (`PREKESID`),
  ADD CONSTRAINT `FK_GRAZINA2` FOREIGN KEY (`GRAZINIMOID`) REFERENCES `grazinimas` (`GRAZINIMOID`);

--
-- Constraints for table `grazinimas`
--
ALTER TABLE `grazinimas`
  ADD CONSTRAINT `FK_ATLIEKAMAS2` FOREIGN KEY (`UZSAKYMOID`) REFERENCES `uzsakymas` (`UZSAKYMOID`);

--
-- Constraints for table `preke`
--
ALTER TABLE `preke`
  ADD CONSTRAINT `FK_PRIKLAUSO` FOREIGN KEY (`RUSIESID`) REFERENCES `prekiurusis` (`RUSIESID`);

--
-- Constraints for table `sandeliuojama`
--
ALTER TABLE `sandeliuojama`
  ADD CONSTRAINT `FK_SANDELIUOJAMA` FOREIGN KEY (`PREKESID`) REFERENCES `preke` (`PREKESID`),
  ADD CONSTRAINT `FK_SANDELIUOJAMA2` FOREIGN KEY (`VIETOSID`) REFERENCES `vieta` (`VIETOSID`);

--
-- Constraints for table `turi`
--
ALTER TABLE `turi`
  ADD CONSTRAINT `FK_TURI` FOREIGN KEY (`PREKESID`) REFERENCES `preke` (`PREKESID`),
  ADD CONSTRAINT `FK_TURI2` FOREIGN KEY (`UZSAKYMOID`) REFERENCES `uzsakymas` (`UZSAKYMOID`);

--
-- Constraints for table `uzsakymas`
--
ALTER TABLE `uzsakymas`
  ADD CONSTRAINT `FK_ATLIEKAMAS` FOREIGN KEY (`GRAZINIMOID`) REFERENCES `grazinimas` (`GRAZINIMOID`),
  ADD CONSTRAINT `FK_PATEIKE` FOREIGN KEY (`KLIENTOID`) REFERENCES `klientas` (`KLIENTOID`),
  ADD CONSTRAINT `FK_SUKURE` FOREIGN KEY (`DARBUOTOJOID`) REFERENCES `darbuotojas` (`DARBUOTOJOID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
