-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 07, 2021 at 08:06 PM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 7.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `warehouse`
--

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `name` varchar(255) NOT NULL,
  `updated_date` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `updated_by` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`name`, `updated_date`, `description`, `updated_by`) VALUES
('food', '2021-11-07 15:05:15', 'food', 'admin1');

-- --------------------------------------------------------

--
-- Table structure for table `comodity`
--

CREATE TABLE `comodity` (
  `comodity_name` varchar(255) NOT NULL,
  `updated_date` datetime NOT NULL,
  `unit` varchar(255) DEFAULT NULL,
  `updated_by` varchar(255) NOT NULL,
  `category` varchar(255) NOT NULL,
  `measurement_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `comodity`
--

INSERT INTO `comodity` (`comodity_name`, `updated_date`, `unit`, `updated_by`, `category`, `measurement_name`) VALUES
('Indomie', '2021-11-07 22:31:52', 'Indomie', 'admin1', 'food', 'pieces'),
('Sari Roti', '2021-11-07 22:32:38', 'Sari Roti', 'admin1', 'food', 'pieces');

-- --------------------------------------------------------

--
-- Table structure for table `measurement`
--

CREATE TABLE `measurement` (
  `name` varchar(255) NOT NULL,
  `updated_date` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `nick_name` varchar(5) NOT NULL,
  `updated_by` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `measurement`
--

INSERT INTO `measurement` (`name`, `updated_date`, `description`, `nick_name`, `updated_by`) VALUES
('kilogram', '2021-11-07 17:39:16', 'kilogram', 'kg', 'admin1'),
('pieces', '2021-11-07 15:05:36', 'pieces', 'pcs', 'admin1');

-- --------------------------------------------------------

--
-- Table structure for table `privilege`
--

CREATE TABLE `privilege` (
  `name` varchar(255) NOT NULL,
  `updated_date` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `updated_by` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `privilege`
--

INSERT INTO `privilege` (`name`, `updated_date`, `description`, `updated_by`) VALUES
('cashier', '2021-11-08 01:47:45', 'cashier privilege', 'admin1'),
('modify', '2021-11-08 01:47:45', 'modify privilege', 'admin1'),
('staff', '2021-11-08 01:47:45', 'staff privilege', 'admin1');

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`name`, `description`) VALUES
('admin', NULL),
('cashier', NULL),
('staff', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `role_privilege`
--

CREATE TABLE `role_privilege` (
  `updated_date` datetime NOT NULL,
  `privilege` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `updated_by` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `role_privilege`
--

INSERT INTO `role_privilege` (`updated_date`, `privilege`, `role`, `updated_by`) VALUES
('2021-11-08 01:47:45', 'cashier', 'admin', 'admin1'),
('2021-11-08 01:47:45', 'cashier', 'cashier', 'admin1'),
('2021-11-08 01:47:45', 'modify', 'admin', 'admin1'),
('2021-11-08 01:47:45', 'staff', 'admin', 'admin1'),
('2021-11-08 01:47:45', 'staff', 'staff', 'admin1');

-- --------------------------------------------------------

--
-- Table structure for table `suplier`
--

CREATE TABLE `suplier` (
  `name` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `updated_date` datetime NOT NULL,
  `contact_number` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `updated_by` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `suplier`
--

INSERT INTO `suplier` (`name`, `address`, `updated_date`, `contact_number`, `phone_number`, `updated_by`) VALUES
('wings', 'Jalan M. Hasibuan, Margahayu, Bekasi Timur', '2021-11-07 22:08:20', '089675470804', '089675470804', 'admin1');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `password`, `role`) VALUES
('admin1', '$2a$10$Xtigz79EoF7DGbRIz4FSW.Tl0EWOLR3ZV5gAGNonhwJdfDK9VOyWK', 'admin'),
('admin2', '$2a$10$yP5XB1Lc/MPhioSnu9DjiOynGBMEwgJAgosroXPM0mPr09mPEwmb6', 'admin'),
('cashier1', '$2a$10$kqfv/djsnRyctjBm0VZ5ceE9rV14PNkA5vnMA.PFmCVDCoadgm9Cu', 'cashier'),
('staff1', '$2a$10$S5P7vo/yiN.8oWg21jthP.yh5apMDxVhHJHAacP0/yUvrYxkIEuUC', 'staff');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`name`),
  ADD KEY `FKjho6rkc8456ho2u9myv172can` (`updated_by`);

--
-- Indexes for table `comodity`
--
ALTER TABLE `comodity`
  ADD PRIMARY KEY (`comodity_name`),
  ADD KEY `FK4up5xk2iyxp0kr79py2xufdxe` (`updated_by`),
  ADD KEY `FK57mggl4l4ccw02842jrgexmon` (`category`),
  ADD KEY `FKgknbac36w8gy0morm2dw6hvut` (`measurement_name`);

--
-- Indexes for table `measurement`
--
ALTER TABLE `measurement`
  ADD PRIMARY KEY (`name`),
  ADD UNIQUE KEY `UK_8k5glwsj8qy97wbn9t6o8vxt1` (`nick_name`),
  ADD KEY `FK9nna0dk7dfupa3pgkyumq33ni` (`updated_by`);

--
-- Indexes for table `privilege`
--
ALTER TABLE `privilege`
  ADD PRIMARY KEY (`name`),
  ADD KEY `FK1vk27ohyf4wv7f2sao23ht6ka` (`updated_by`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`name`);

--
-- Indexes for table `role_privilege`
--
ALTER TABLE `role_privilege`
  ADD PRIMARY KEY (`privilege`,`role`),
  ADD KEY `FKgowd4s56fs716luj2rdccpb6r` (`role`),
  ADD KEY `FKoo794u2q4qs350guvls8ctd6n` (`updated_by`);

--
-- Indexes for table `suplier`
--
ALTER TABLE `suplier`
  ADD PRIMARY KEY (`name`),
  ADD KEY `FKpia4vb2rui2kuyciosxit5e3e` (`updated_by`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`username`),
  ADD KEY `FKl5alypubd40lwejc45vl35wjb` (`role`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `category`
--
ALTER TABLE `category`
  ADD CONSTRAINT `FKjho6rkc8456ho2u9myv172can` FOREIGN KEY (`updated_by`) REFERENCES `user` (`username`);

--
-- Constraints for table `comodity`
--
ALTER TABLE `comodity`
  ADD CONSTRAINT `FK4up5xk2iyxp0kr79py2xufdxe` FOREIGN KEY (`updated_by`) REFERENCES `user` (`username`),
  ADD CONSTRAINT `FK57mggl4l4ccw02842jrgexmon` FOREIGN KEY (`category`) REFERENCES `category` (`name`),
  ADD CONSTRAINT `FKgknbac36w8gy0morm2dw6hvut` FOREIGN KEY (`measurement_name`) REFERENCES `measurement` (`name`);

--
-- Constraints for table `measurement`
--
ALTER TABLE `measurement`
  ADD CONSTRAINT `FK9nna0dk7dfupa3pgkyumq33ni` FOREIGN KEY (`updated_by`) REFERENCES `user` (`username`);

--
-- Constraints for table `privilege`
--
ALTER TABLE `privilege`
  ADD CONSTRAINT `FK1vk27ohyf4wv7f2sao23ht6ka` FOREIGN KEY (`updated_by`) REFERENCES `user` (`username`);

--
-- Constraints for table `role_privilege`
--
ALTER TABLE `role_privilege`
  ADD CONSTRAINT `FKgowd4s56fs716luj2rdccpb6r` FOREIGN KEY (`role`) REFERENCES `role` (`name`),
  ADD CONSTRAINT `FKkj8uj7l23835dvadco3ulcov` FOREIGN KEY (`privilege`) REFERENCES `privilege` (`name`),
  ADD CONSTRAINT `FKoo794u2q4qs350guvls8ctd6n` FOREIGN KEY (`updated_by`) REFERENCES `user` (`username`);

--
-- Constraints for table `suplier`
--
ALTER TABLE `suplier`
  ADD CONSTRAINT `FKpia4vb2rui2kuyciosxit5e3e` FOREIGN KEY (`updated_by`) REFERENCES `user` (`username`);

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FKl5alypubd40lwejc45vl35wjb` FOREIGN KEY (`role`) REFERENCES `role` (`name`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
