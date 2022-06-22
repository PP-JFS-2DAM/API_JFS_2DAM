-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 23-06-2022 a las 00:25:52
-- Versión del servidor: 10.4.24-MariaDB
-- Versión de PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `toplaptopdb`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `computer`
--

CREATE TABLE `computer` (
  `id` bigint(20) NOT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `computer_image` longtext DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `ram` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `computer`
--

INSERT INTO `computer` (`id`, `brand`, `computer_image`, `model`, `ram`, `user_id`) VALUES
(1, 'Lenovo', NULL, 'L-540', '16GB', 1),
(2, 'Asus', NULL, 'R-221', '32GB', 2),
(3, 'Toshiba', NULL, 'T-321', '8GB', 3),
(4, 'Mac', NULL, '1200', '64GB', 4),
(5, 'HP', NULL, 'O-245', '4GB', 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `receipt`
--

CREATE TABLE `receipt` (
  `id` bigint(20) NOT NULL,
  `date` date DEFAULT NULL,
  `discount` double DEFAULT NULL,
  `price` double DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `receipt`
--

INSERT INTO `receipt` (`id`, `date`, `discount`, `price`, `order_id`) VALUES
(1, '2022-06-21', 0, 59.99, 1),
(2, '2022-06-22', 0, 34.99, 2),
(3, '2022-06-19', 9.99, 10, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `technical`
--

CREATE TABLE `technical` (
  `id` bigint(20) NOT NULL,
  `dni` varchar(255) DEFAULT NULL,
  `is_available` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `technical`
--

INSERT INTO `technical` (`id`, `dni`, `is_available`, `name`, `surname`) VALUES
(1, '76865432J', b'1', 'Rubén', 'Gálvez'),
(2, '85435609R', b'0', 'Raúl', 'Martín'),
(3, '09561452Z', b'1', 'Sandra', 'Victoria');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `dni` varchar(255) DEFAULT NULL,
  `latitude` float DEFAULT NULL,
  `longitude` float DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `user_image` longtext DEFAULT NULL,
  `vip` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `user` (`id`, `dni`, `latitude`, `longitude`, `name`, `surname`, `user_image`, `vip`) VALUES
(1, '76973468N', 0, 0, 'Joserra', 'Gimeno', NULL, b'0'),
(2, '67876543U', 0, 0, 'Santiago', 'Ballestín', NULL, b'1'),
(3, '25473819P', 0, 0, 'Fran', 'Muñoz', NULL, b'1'),
(4, '58493059I', 0, 0, 'Santi', 'Faci', NULL, b'1'),
(5, '59385767Y', 0, 0, 'Jose Luis', 'Meléndez', NULL, b'0');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `work_order`
--

CREATE TABLE `work_order` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `order_date` date NOT NULL,
  `computer_id` bigint(20) DEFAULT NULL,
  `technical_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `work_order`
--

INSERT INTO `work_order` (`id`, `description`, `order_date`, `computer_id`, `technical_id`) VALUES
(1, 'Placa base', '2022-06-21', 1, 1),
(2, 'Batería', '2022-06-22', 2, 3),
(3, 'USB-A Port', '2022-06-19', 3, 1),
(5, 'Tarjeta gráfica', '2022-06-30', 4, 2),
(6, 'Recuperar HDD', '2022-07-02', 5, 2);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `computer`
--
ALTER TABLE `computer`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKa5w2s4lhu7ngssecaictcr06a` (`user_id`);

--
-- Indices de la tabla `receipt`
--
ALTER TABLE `receipt`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKrilxwxbymwyc3rvxxnrhficjt` (`order_id`);

--
-- Indices de la tabla `technical`
--
ALTER TABLE `technical`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `work_order`
--
ALTER TABLE `work_order`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK2qxbspduuum1menkql3huelh4` (`computer_id`),
  ADD KEY `FKbgdk3x2o7ulvhct0s8xxpoubd` (`technical_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `computer`
--
ALTER TABLE `computer`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `receipt`
--
ALTER TABLE `receipt`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `technical`
--
ALTER TABLE `technical`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `work_order`
--
ALTER TABLE `work_order`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `computer`
--
ALTER TABLE `computer`
  ADD CONSTRAINT `FKa5w2s4lhu7ngssecaictcr06a` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE;

--
-- Filtros para la tabla `receipt`
--
ALTER TABLE `receipt`
  ADD CONSTRAINT `FKrilxwxbymwyc3rvxxnrhficjt` FOREIGN KEY (`order_id`) REFERENCES `work_order` (`id`);

--
-- Filtros para la tabla `work_order`
--
ALTER TABLE `work_order`
  ADD CONSTRAINT `FK2qxbspduuum1menkql3huelh4` FOREIGN KEY (`computer_id`) REFERENCES `computer` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FKbgdk3x2o7ulvhct0s8xxpoubd` FOREIGN KEY (`technical_id`) REFERENCES `technical` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
