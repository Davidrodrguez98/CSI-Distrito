-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 20-11-2019 a las 12:12:09
-- Versión del servidor: 10.4.8-MariaDB
-- Versión de PHP: 7.3.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `distrito`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `juego`
--

CREATE TABLE `juego` (
  `id` int(11) NOT NULL,
  `Id_TipoMapa` int(11) NOT NULL,
  `codigo` varchar(20) NOT NULL,
  `nParticipantes` int(11) NOT NULL,
  `fechaInicio` date DEFAULT NULL,
  `fechaFin` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `juego`
--

INSERT INTO `juego` (`id`, `Id_TipoMapa`, `codigo`, `nParticipantes`, `fechaInicio`, `fechaFin`) VALUES
(1, 1, 'JuegoEditado1', 20, '2019-10-01', '2019-10-05'),
(2, 3, 'Juego2Editado', 2, '2019-10-14', NULL),
(17, 3, 'Juego34', 50, NULL, NULL),
(18, 1, 'Juego23', 58, NULL, NULL),
(19, 2, 'Juego27', 14, NULL, NULL),
(31, 3, 'Juego3', 12, NULL, NULL),
(33, 3, 'Juego77', 34, NULL, NULL),
(34, 3, 'JuegoNuevo3000', 2, NULL, NULL),
(35, 1, 'Juego1234', 78, NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipomapa`
--

CREATE TABLE `tipomapa` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tipomapa`
--

INSERT INTO `tipomapa` (`id`, `nombre`) VALUES
(1, 'Jungla'),
(2, 'Desierto'),
(3, 'Ciudad');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `juego`
--
ALTER TABLE `juego`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `codigo` (`codigo`),
  ADD KEY `Id_TipoMapa` (`Id_TipoMapa`);

--
-- Indices de la tabla `tipomapa`
--
ALTER TABLE `tipomapa`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `juego`
--
ALTER TABLE `juego`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT de la tabla `tipomapa`
--
ALTER TABLE `tipomapa`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `juego`
--
ALTER TABLE `juego`
  ADD CONSTRAINT `juego_ibfk_1` FOREIGN KEY (`Id_TipoMapa`) REFERENCES `tipomapa` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
