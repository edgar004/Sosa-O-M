-- phpMyAdmin SQL Dump
-- version 2.10.3
-- http://www.phpmyadmin.net
-- 
-- Servidor: localhost
-- Tiempo de generación: 20-08-2020 a las 19:49:25
-- Versión del servidor: 5.0.51
-- Versión de PHP: 5.2.6

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

-- 
-- Base de datos: `proyectofinalangel`
-- 
CREATE DATABASE `proyectofinalangel` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `proyectofinalangel`;

-- --------------------------------------------------------

-- 
-- Estructura de tabla para la tabla `articulo`
-- 

CREATE TABLE `articulo` (
  `idarticulo` int(11) NOT NULL auto_increment,
  `desart` varchar(99) NOT NULL,
  `cantidad` float(11,2) NOT NULL,
  `precom` float(11,2) NOT NULL,
  `preven` float(11,2) NOT NULL,
  `reorden` float(11,2) NOT NULL,
  `tipoarticulo` varchar(8) NOT NULL,
  PRIMARY KEY  (`idarticulo`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=10 ;

-- 
-- Volcar la base de datos para la tabla `articulo`
-- 

INSERT INTO `articulo` VALUES (1, 'sopita dona gallina', 4941.00, 3.50, 5.00, 300.00, '1');
INSERT INTO `articulo` VALUES (2, 'libra de azucar', 236.00, 29.00, 31.00, 35.00, '4');
INSERT INTO `articulo` VALUES (3, 'sobre de cafe tostao', 210.00, 8.20, 9.99, 50.00, '3');
INSERT INTO `articulo` VALUES (4, 'ibra de arroz', 250.00, 25.00, 28.00, 25.00, '5');
INSERT INTO `articulo` VALUES (5, 'sobre de sopa', 180.00, 18.00, 20.00, 40.00, '6');
INSERT INTO `articulo` VALUES (7, 'a', 86.00, 1.00, 1.00, 1.00, '1');
INSERT INTO `articulo` VALUES (8, 'pote de aceite', 435.00, 39.00, 42.00, 35.00, '8');

-- --------------------------------------------------------

-- 
-- Estructura de tabla para la tabla `cliente`
-- 

CREATE TABLE `cliente` (
  `idcliente` int(11) NOT NULL auto_increment,
  `limcre` float(18,2) NOT NULL,
  `nomcli` varchar(45) NOT NULL,
  `rnccedula` varchar(12) NOT NULL,
  `telefono` varchar(12) NOT NULL,
  `sexo` char(1) NOT NULL,
  PRIMARY KEY  (`idcliente`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=12 ;

-- 
-- Volcar la base de datos para la tabla `cliente`
-- 

INSERT INTO `cliente` VALUES (1, 15000.00, 'Jorge Luis Guzman', '40212345678', '8098217083', 'M');
INSERT INTO `cliente` VALUES (2, 10000.00, 'jorge guzman', '40211111111', '809111111', 'M');
INSERT INTO `cliente` VALUES (3, 0.00, 'abc def', '11111111111', '1111111111', 'M');
INSERT INTO `cliente` VALUES (4, 0.00, 'juan antonio', '123456789', '829123456', 'm');
INSERT INTO `cliente` VALUES (6, 0.00, 'juan antonio', '42211111111', '8291234567', 'm');
INSERT INTO `cliente` VALUES (7, 0.00, 'andres ortiz', '987654321', '8491234567', 'm');
INSERT INTO `cliente` VALUES (8, 0.00, 'lola mento', '789456123', '8495555555', 'm');
INSERT INTO `cliente` VALUES (11, 0.00, 'N/A', 'N/A', 'N/A', 'N');

-- --------------------------------------------------------

-- 
-- Estructura de tabla para la tabla `contador`
-- 

CREATE TABLE `contador` (
  `idfactura` int(11) NOT NULL default '0'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- Volcar la base de datos para la tabla `contador`
-- 

INSERT INTO `contador` VALUES (91);

-- --------------------------------------------------------

-- 
-- Estructura de tabla para la tabla `contadorsesion`
-- 

CREATE TABLE `contadorsesion` (
  `idsesiones` int(11) NOT NULL default '0'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- Volcar la base de datos para la tabla `contadorsesion`
-- 

INSERT INTO `contadorsesion` VALUES (21);

-- --------------------------------------------------------

-- 
-- Estructura de tabla para la tabla `detallefactura`
-- 

CREATE TABLE `detallefactura` (
  `idfactura` varchar(45) NOT NULL,
  `idarticulo` varchar(45) NOT NULL,
  `desart` varchar(45) NOT NULL,
  `cantidad` varchar(12) NOT NULL,
  `precio` varchar(12) NOT NULL,
  `itbis` varchar(12) NOT NULL,
  `importe` varchar(12) NOT NULL,
  `subtotal` varchar(22) NOT NULL,
  `registro` int(14) NOT NULL auto_increment,
  PRIMARY KEY  (`registro`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=173 ;

-- 
-- Volcar la base de datos para la tabla `detallefactura`
-- 

INSERT INTO `detallefactura` VALUES ('PC1-1', '2', 'libra de azucar', '1', '31.00', '4,73', '26,27', '31', 1);
INSERT INTO `detallefactura` VALUES ('PC1-1', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 2);
INSERT INTO `detallefactura` VALUES ('PC1-1', '3', 'sobre de cafe tostao', '3', '9.99', '4,57', '25,4', '29,97', 3);
INSERT INTO `detallefactura` VALUES ('PC1-1', '4', 'ibra de arroz', '4', '28.00', '17,08', '94,92', '112', 4);
INSERT INTO `detallefactura` VALUES ('PC1-1', '4', 'ibra de arroz', '3', '28.00', '12,81', '71,19', '84', 5);
INSERT INTO `detallefactura` VALUES ('PC1-4', '7', 'a', '2', '1.00', '0,31', '1,69', '2', 6);
INSERT INTO `detallefactura` VALUES ('PC1-6', '3', 'sobre de cafe tostao', '6', '9.99', '9,14', '50,8', '59,94', 7);
INSERT INTO `detallefactura` VALUES ('PC1-6', '8', 'pote de aceite', '6', '42.00', '38,44', '213,56', '252', 8);
INSERT INTO `detallefactura` VALUES ('PC1-6', '4', 'ibra de arroz', '1', '28.00', '4,27', '23,73', '28', 9);
INSERT INTO `detallefactura` VALUES ('PC1-7', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 10);
INSERT INTO `detallefactura` VALUES ('PC1-8', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 11);
INSERT INTO `detallefactura` VALUES ('PC1-9', '1', 'sopita dona gallina', '10', '5.00', '7,63', '42,37', '50', 12);
INSERT INTO `detallefactura` VALUES ('PC1-10', '1', 'sopita dona gallina', '10', '5.00', '7,63', '42,37', '50', 13);
INSERT INTO `detallefactura` VALUES ('PC1-10', '1', 'sopita dona gallina', '90', '5.00', '68,64', '381,36', '450', 14);
INSERT INTO `detallefactura` VALUES ('PC1-11', '1', 'sopita dona gallina', '100', '5.00', '76,27', '423,73', '500', 15);
INSERT INTO `detallefactura` VALUES ('PC1-12', '8', 'pote de aceite', '9', '42.00', '57,66', '320,34', '378', 16);
INSERT INTO `detallefactura` VALUES ('PC1-13', '1', 'sopita dona gallina', '100', '5.00', '76,27', '423,73', '500', 17);
INSERT INTO `detallefactura` VALUES ('PC1-14', '1', 'sopita dona gallina', '100', '5.00', '76,27', '423,73', '500', 18);
INSERT INTO `detallefactura` VALUES ('PC1-15', '1', 'sopita dona gallina', '10', '5.00', '7,63', '42,37', '50', 19);
INSERT INTO `detallefactura` VALUES ('PC1-16', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 20);
INSERT INTO `detallefactura` VALUES ('PC1-16', '2', 'libra de azucar', '2', '31.00', '9,46', '52,54', '62', 21);
INSERT INTO `detallefactura` VALUES ('PC1-16', '3', 'sobre de cafe tostao', '3', '9.99', '4,57', '25,4', '29,97', 22);
INSERT INTO `detallefactura` VALUES ('PC1-16', '4', 'ibra de arroz', '4', '28.00', '17,08', '94,92', '112', 23);
INSERT INTO `detallefactura` VALUES ('PC1-16', '5', 'sobre de sopa', '5', '20.00', '15,25', '84,75', '100', 24);
INSERT INTO `detallefactura` VALUES ('PC1-16', '7', 'a', '7', '1.00', '1,07', '5,93', '7', 25);
INSERT INTO `detallefactura` VALUES ('PC1-17', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 26);
INSERT INTO `detallefactura` VALUES ('PC1-18', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 27);
INSERT INTO `detallefactura` VALUES ('PC1-19', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 28);
INSERT INTO `detallefactura` VALUES ('PC1-20', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 29);
INSERT INTO `detallefactura` VALUES ('PC1-21', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 30);
INSERT INTO `detallefactura` VALUES ('PC1-22', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 31);
INSERT INTO `detallefactura` VALUES ('PC1-23', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 32);
INSERT INTO `detallefactura` VALUES ('PC1-24', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 33);
INSERT INTO `detallefactura` VALUES ('PC1-23', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 34);
INSERT INTO `detallefactura` VALUES ('PC1-26', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 35);
INSERT INTO `detallefactura` VALUES ('PC1-26', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 36);
INSERT INTO `detallefactura` VALUES ('PC1-28', '8', 'pote de aceite', '8', '42.00', '51,25', '284,75', '336', 37);
INSERT INTO `detallefactura` VALUES ('PC1-28', '8', 'pote de aceite', '8', '42.00', '51,25', '284,75', '336', 38);
INSERT INTO `detallefactura` VALUES ('PC1-28', '8', 'pote de aceite', '8', '42.00', '51,25', '284,75', '336', 39);
INSERT INTO `detallefactura` VALUES ('PC1-29', '8', 'pote de aceite', '8', '42.00', '51,25', '284,75', '336', 40);
INSERT INTO `detallefactura` VALUES ('PC1-30', '2', 'libra de azucar', '2', '31.00', '9,46', '52,54', '62', 41);
INSERT INTO `detallefactura` VALUES ('PC1-30', '8', 'pote de aceite', '88', '42.00', '563,8', '3132,2', '3696', 42);
INSERT INTO `detallefactura` VALUES ('PC1-31', '8', 'pote de aceite', '8', '42.00', '51,25', '284,75', '336', 43);
INSERT INTO `detallefactura` VALUES ('PC1-32', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 44);
INSERT INTO `detallefactura` VALUES ('PC1-32', '8', 'pote de aceite', '8', '42.00', '51,25', '284,75', '336', 45);
INSERT INTO `detallefactura` VALUES ('PC1-33', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 46);
INSERT INTO `detallefactura` VALUES ('PC1-33', '8', 'pote de aceite', '8', '42.00', '51,25', '284,75', '336', 47);
INSERT INTO `detallefactura` VALUES ('PC1-34', '8', 'pote de aceite', '8', '42.00', '51,25', '284,75', '336', 48);
INSERT INTO `detallefactura` VALUES ('PC1-35', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 49);
INSERT INTO `detallefactura` VALUES ('PC1-36', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 50);
INSERT INTO `detallefactura` VALUES ('PC1-36', '8', 'pote de aceite', '4', '42.00', '25,63', '142,37', '168', 51);
INSERT INTO `detallefactura` VALUES ('PC1-37', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 52);
INSERT INTO `detallefactura` VALUES ('PC1-37', '2', 'libra de azucar', '1', '31.00', '4,73', '26,27', '31', 53);
INSERT INTO `detallefactura` VALUES ('PC1-37', '3', 'sobre de cafe tostao', '1', '9.99', '1,52', '8,47', '9,99', 54);
INSERT INTO `detallefactura` VALUES ('PC1-38', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 55);
INSERT INTO `detallefactura` VALUES ('PC1-38', '2', 'libra de azucar', '1', '31.00', '4,73', '26,27', '31', 56);
INSERT INTO `detallefactura` VALUES ('PC1-38', '3', 'sobre de cafe tostao', '1', '9.99', '1,52', '8,47', '9,99', 57);
INSERT INTO `detallefactura` VALUES ('PC1-39', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 58);
INSERT INTO `detallefactura` VALUES ('PC1-39', '2', 'libra de azucar', '1', '31.00', '4,73', '26,27', '31', 59);
INSERT INTO `detallefactura` VALUES ('PC1-39', '3', 'sobre de cafe tostao', '1', '9.99', '1,52', '8,47', '9,99', 60);
INSERT INTO `detallefactura` VALUES ('PC1-39', '4', 'ibra de arroz', '1', '28.00', '4,27', '23,73', '28', 61);
INSERT INTO `detallefactura` VALUES ('PC1-40', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 62);
INSERT INTO `detallefactura` VALUES ('PC1-40', '7', 'a', '7', '1.00', '1,07', '5,93', '7', 63);
INSERT INTO `detallefactura` VALUES ('PC1-41', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 64);
INSERT INTO `detallefactura` VALUES ('PC1-41', '7', 'a', '7', '1.00', '1,07', '5,93', '7', 65);
INSERT INTO `detallefactura` VALUES ('PC1-42', '1', 'sopita dona gallina', '2', '5.00', '1,53', '8,47', '10', 66);
INSERT INTO `detallefactura` VALUES ('PC1-42', '1', 'sopita dona gallina', '2', '5.00', '1,53', '8,47', '10', 67);
INSERT INTO `detallefactura` VALUES ('PC1-42', '3', 'sobre de cafe tostao', '3', '9.99', '4,57', '25,4', '29,97', 68);
INSERT INTO `detallefactura` VALUES ('PC1-42', '4', 'ibra de arroz', '4', '28.00', '17,08', '94,92', '112', 69);
INSERT INTO `detallefactura` VALUES ('PC1-43', '2', 'libra de azucar', '1', '31.00', '4,73', '26,27', '31', 70);
INSERT INTO `detallefactura` VALUES ('PC1-44', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 71);
INSERT INTO `detallefactura` VALUES ('PC1-45', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 72);
INSERT INTO `detallefactura` VALUES ('PC1-46', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 73);
INSERT INTO `detallefactura` VALUES ('PC1-47', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 74);
INSERT INTO `detallefactura` VALUES ('PC1-48', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 75);
INSERT INTO `detallefactura` VALUES ('PC1-49', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 76);
INSERT INTO `detallefactura` VALUES ('PC1-50', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 77);
INSERT INTO `detallefactura` VALUES ('PC1-51', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 78);
INSERT INTO `detallefactura` VALUES ('PC1-52', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 79);
INSERT INTO `detallefactura` VALUES ('PC1-52', '2', 'libra de azucar', '2', '31.00', '9,46', '52,54', '62', 80);
INSERT INTO `detallefactura` VALUES ('PC1-52', '3', 'sobre de cafe tostao', '3', '9.99', '4,57', '25,4', '29,97', 81);
INSERT INTO `detallefactura` VALUES ('PC1-53', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 82);
INSERT INTO `detallefactura` VALUES ('PC1-53', '1', 'sopita dona gallina', '10', '5.00', '7,63', '42,37', '50', 83);
INSERT INTO `detallefactura` VALUES ('PC1-54', '8', 'pote de aceite', '88', '42.00', '563,8', '3132,2', '3696', 84);
INSERT INTO `detallefactura` VALUES ('PC1-55', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 85);
INSERT INTO `detallefactura` VALUES ('PC1-56', '8', 'pote de aceite', '8', '42.00', '51,25', '284,75', '336', 86);
INSERT INTO `detallefactura` VALUES ('PC1-57', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 87);
INSERT INTO `detallefactura` VALUES ('PC1-57', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 88);
INSERT INTO `detallefactura` VALUES ('PC1-57', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 89);
INSERT INTO `detallefactura` VALUES ('PC1-57', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 90);
INSERT INTO `detallefactura` VALUES ('PC1-57', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 91);
INSERT INTO `detallefactura` VALUES ('PC1-57', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 92);
INSERT INTO `detallefactura` VALUES ('PC1-57', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 93);
INSERT INTO `detallefactura` VALUES ('PC1-57', '8', 'pote de aceite', '8', '42.00', '51,25', '284,75', '336', 94);
INSERT INTO `detallefactura` VALUES ('PC1-57', '8', 'pote de aceite', '8', '42.00', '51,25', '284,75', '336', 95);
INSERT INTO `detallefactura` VALUES ('PC1-57', '8', 'pote de aceite', '8', '42.00', '51,25', '284,75', '336', 96);
INSERT INTO `detallefactura` VALUES ('PC1-57', '8', 'pote de aceite', '8', '42.00', '51,25', '284,75', '336', 97);
INSERT INTO `detallefactura` VALUES ('PC1-57', '8', 'pote de aceite', '8', '42.00', '51,25', '284,75', '336', 98);
INSERT INTO `detallefactura` VALUES ('PC1-57', '8', 'pote de aceite', '8', '42.00', '51,25', '284,75', '336', 99);
INSERT INTO `detallefactura` VALUES ('PC1-57', '8', 'pote de aceite', '8', '42.00', '51,25', '284,75', '336', 100);
INSERT INTO `detallefactura` VALUES ('PC1-57', '8', 'pote de aceite', '88', '42.00', '563,8', '3132,2', '3696', 101);
INSERT INTO `detallefactura` VALUES ('PC1-58', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 102);
INSERT INTO `detallefactura` VALUES ('PC1-58', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 103);
INSERT INTO `detallefactura` VALUES ('PC1-59', '2', 'libra de azucar', '2', '31.00', '9,46', '52,54', '62', 104);
INSERT INTO `detallefactura` VALUES ('PC1-59', '2', 'libra de azucar', '2', '31.00', '9,46', '52,54', '62', 105);
INSERT INTO `detallefactura` VALUES ('PC1-59', '2', 'libra de azucar', '2', '31.00', '9,46', '52,54', '62', 106);
INSERT INTO `detallefactura` VALUES ('PC1-59', '8', 'pote de aceite', '8', '42.00', '51,25', '284,75', '336', 107);
INSERT INTO `detallefactura` VALUES ('PC1-59', '8', 'pote de aceite', '8', '42.00', '51,25', '284,75', '336', 108);
INSERT INTO `detallefactura` VALUES ('PC1-59', '8', 'pote de aceite', '8', '42.00', '51,25', '284,75', '336', 109);
INSERT INTO `detallefactura` VALUES ('PC1-59', '8', 'pote de aceite', '8', '42.00', '51,25', '284,75', '336', 110);
INSERT INTO `detallefactura` VALUES ('PC1-60', '8', 'pote de aceite', '8', '42.00', '51,25', '284,75', '336', 111);
INSERT INTO `detallefactura` VALUES ('PC1-60', '8', 'pote de aceite', '8', '42.00', '51,25', '284,75', '336', 112);
INSERT INTO `detallefactura` VALUES ('PC1-60', '8', 'pote de aceite', '8', '42.00', '51,25', '284,75', '336', 113);
INSERT INTO `detallefactura` VALUES ('PC1-60', '8', 'pote de aceite', '8', '42.00', '51,25', '284,75', '336', 114);
INSERT INTO `detallefactura` VALUES ('PC1-60', '8', 'pote de aceite', '150', '42.00', '961,02', '5338,98', '6300', 115);
INSERT INTO `detallefactura` VALUES ('PC1-71', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 116);
INSERT INTO `detallefactura` VALUES ('PC1-77', '3', 'sobre de cafe tostao', '3', '9.99', '4,57', '25,4', '29,97', 117);
INSERT INTO `detallefactura` VALUES ('PC1-77', '3', 'sobre de cafe tostao', '3', '9.99', '4,57', '25,4', '29,97', 118);
INSERT INTO `detallefactura` VALUES ('PC1-77', '3', 'sobre de cafe tostao', '3', '9.99', '4,57', '25,4', '29,97', 119);
INSERT INTO `detallefactura` VALUES ('PC1-78', '3', 'sobre de cafe tostao', '3', '9.99', '4,57', '25,4', '29,97', 120);
INSERT INTO `detallefactura` VALUES ('PC1-78', '3', 'sobre de cafe tostao', '3', '9.99', '4,57', '25,4', '29,97', 121);
INSERT INTO `detallefactura` VALUES ('PC1-78', '3', 'sobre de cafe tostao', '3', '9.99', '4,57', '25,4', '29,97', 122);
INSERT INTO `detallefactura` VALUES ('PC1-78', '3', 'sobre de cafe tostao', '3', '9.99', '4,57', '25,4', '29,97', 123);
INSERT INTO `detallefactura` VALUES ('PC1-78', '3', 'sobre de cafe tostao', '33', '9.99', '50,29', '279,38', '329,67', 124);
INSERT INTO `detallefactura` VALUES ('PC1-81', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 125);
INSERT INTO `detallefactura` VALUES ('PC1-81', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 126);
INSERT INTO `detallefactura` VALUES ('PC1-81', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 127);
INSERT INTO `detallefactura` VALUES ('PC1-81', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 128);
INSERT INTO `detallefactura` VALUES ('PC1-81', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 129);
INSERT INTO `detallefactura` VALUES ('PC1-81', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 130);
INSERT INTO `detallefactura` VALUES ('PC1-82', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 131);
INSERT INTO `detallefactura` VALUES ('PC1-82', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 132);
INSERT INTO `detallefactura` VALUES ('PC1-82', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 133);
INSERT INTO `detallefactura` VALUES ('PC1-82', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 134);
INSERT INTO `detallefactura` VALUES ('PC1-82', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 135);
INSERT INTO `detallefactura` VALUES ('PC1-82', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 136);
INSERT INTO `detallefactura` VALUES ('PC1-82', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 137);
INSERT INTO `detallefactura` VALUES ('PC1-83', '8', 'pote de aceite', '8', '42.00', '51,25', '284,75', '336', 138);
INSERT INTO `detallefactura` VALUES ('PC1-83', '8', 'pote de aceite', '8', '42.00', '51,25', '284,75', '336', 139);
INSERT INTO `detallefactura` VALUES ('PC1-83', '8', 'pote de aceite', '8', '42.00', '51,25', '284,75', '336', 140);
INSERT INTO `detallefactura` VALUES ('PC1-83', '8', 'pote de aceite', '8', '42.00', '51,25', '284,75', '336', 141);
INSERT INTO `detallefactura` VALUES ('PC1-83', '8', 'pote de aceite', '8', '42.00', '51,25', '284,75', '336', 142);
INSERT INTO `detallefactura` VALUES ('PC1-83', '8', 'pote de aceite', '8', '42.00', '51,25', '284,75', '336', 143);
INSERT INTO `detallefactura` VALUES ('PC1-83', '8', 'pote de aceite', '8', '42.00', '51,25', '284,75', '336', 144);
INSERT INTO `detallefactura` VALUES ('PC1-83', '8', 'pote de aceite', '8', '42.00', '51,25', '284,75', '336', 145);
INSERT INTO `detallefactura` VALUES ('PC1-84', '3', 'sobre de cafe tostao', '3', '9.99', '4,57', '25,4', '29,97', 146);
INSERT INTO `detallefactura` VALUES ('PC1-84', '3', 'sobre de cafe tostao', '33', '9.99', '50,29', '279,38', '329,67', 147);
INSERT INTO `detallefactura` VALUES ('PC1-84', '3', 'sobre de cafe tostao', '3', '9.99', '4,57', '25,4', '29,97', 148);
INSERT INTO `detallefactura` VALUES ('PC1-84', '3', 'sobre de cafe tostao', '3', '9.99', '4,57', '25,4', '29,97', 149);
INSERT INTO `detallefactura` VALUES ('PC1-84', '3', 'sobre de cafe tostao', '3', '9.99', '4,57', '25,4', '29,97', 150);
INSERT INTO `detallefactura` VALUES ('PC1-84', '3', 'sobre de cafe tostao', '3', '9.99', '4,57', '25,4', '29,97', 151);
INSERT INTO `detallefactura` VALUES ('PC1-84', '3', 'sobre de cafe tostao', '3', '9.99', '4,57', '25,4', '29,97', 152);
INSERT INTO `detallefactura` VALUES ('PC1-84', '3', 'sobre de cafe tostao', '3', '9.99', '4,57', '25,4', '29,97', 153);
INSERT INTO `detallefactura` VALUES ('PC1-84', '3', 'sobre de cafe tostao', '3', '9.99', '4,57', '25,4', '29,97', 154);
INSERT INTO `detallefactura` VALUES ('PC1-84', '3', 'sobre de cafe tostao', '3', '9.99', '4,57', '25,4', '29,97', 155);
INSERT INTO `detallefactura` VALUES ('PC1-84', '3', 'sobre de cafe tostao', '3', '9.99', '4,57', '25,4', '29,97', 156);
INSERT INTO `detallefactura` VALUES ('PC1-84', '3', 'sobre de cafe tostao', '3', '9.99', '4,57', '25,4', '29,97', 157);
INSERT INTO `detallefactura` VALUES ('PC1-84', '3', 'sobre de cafe tostao', '3', '9.99', '4,57', '25,4', '29,97', 158);
INSERT INTO `detallefactura` VALUES ('PC1-84', '3', 'sobre de cafe tostao', '3', '9.99', '4,57', '25,4', '29,97', 159);
INSERT INTO `detallefactura` VALUES ('PC1-84', '3', 'sobre de cafe tostao', '3', '9.99', '4,57', '25,4', '29,97', 160);
INSERT INTO `detallefactura` VALUES ('PC1-84', '3', 'sobre de cafe tostao', '3', '9.99', '4,57', '25,4', '29,97', 161);
INSERT INTO `detallefactura` VALUES ('PC1-85', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 162);
INSERT INTO `detallefactura` VALUES ('PC1-87', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 163);
INSERT INTO `detallefactura` VALUES ('PC1-87', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 164);
INSERT INTO `detallefactura` VALUES ('PC1-87', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 165);
INSERT INTO `detallefactura` VALUES ('PC1-87', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 166);
INSERT INTO `detallefactura` VALUES ('PC1-88', '2', 'libra de azucar', '2', '31.00', '9,46', '52,54', '62', 167);
INSERT INTO `detallefactura` VALUES ('PC1-88', '2', 'libra de azucar', '2', '31.00', '9,46', '52,54', '62', 168);
INSERT INTO `detallefactura` VALUES ('PC1-88', '2', 'libra de azucar', '2', '31.00', '9,46', '52,54', '62', 169);
INSERT INTO `detallefactura` VALUES ('PC1-88', '2', 'libra de azucar', '2', '31.00', '9,46', '52,54', '62', 170);
INSERT INTO `detallefactura` VALUES ('PC1-89', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 171);
INSERT INTO `detallefactura` VALUES ('PC1-90', '1', 'sopita dona gallina', '1', '5.00', '0,76', '4,24', '5', 172);

-- --------------------------------------------------------

-- 
-- Estructura de tabla para la tabla `empleados`
-- 

CREATE TABLE `empleados` (
  `idempleado` int(11) NOT NULL auto_increment,
  `nombre` varchar(45) NOT NULL,
  `cedula` varchar(12) NOT NULL,
  `sueldo` float NOT NULL,
  `fecnac` varchar(14) NOT NULL,
  `sexo` varchar(4) NOT NULL,
  PRIMARY KEY  (`idempleado`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=10 ;

-- 
-- Volcar la base de datos para la tabla `empleados`
-- 

INSERT INTO `empleados` VALUES (1, 'yo', '1234', 2134, '0000-00-00', 'm');
INSERT INTO `empleados` VALUES (2, 'yola', '12345', 19999, '0000-00-00', 'm');
INSERT INTO `empleados` VALUES (3, 'yolanda', '123456', 19999, '0000-00-00', 'f');
INSERT INTO `empleados` VALUES (4, 'yo', '1234', 2134, '2020/4/1', 'm');
INSERT INTO `empleados` VALUES (5, 'yola', '12345', 19999, '2020/4/1', 'm');
INSERT INTO `empleados` VALUES (6, 'yo', '1234', 2134, '2020/4/7', 'm');
INSERT INTO `empleados` VALUES (9, 'yola', '12345', 19999, '2020/4/7', 'm');

-- --------------------------------------------------------

-- 
-- Estructura de tabla para la tabla `factura`
-- 

CREATE TABLE `factura` (
  `numerofactura` varchar(45) NOT NULL,
  `tipofactura` varchar(22) NOT NULL,
  `fecha` date NOT NULL,
  `subtotal` float(11,2) NOT NULL,
  `itbis` float(11,2) NOT NULL,
  `total` float(11,2) NOT NULL,
  `idcliente` varchar(22) NOT NULL,
  `nomcli` varchar(45) NOT NULL,
  `registro` int(12) NOT NULL auto_increment,
  `balance` float(11,2) NOT NULL,
  PRIMARY KEY  (`registro`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=96 ;

-- 
-- Volcar la base de datos para la tabla `factura`
-- 

INSERT INTO `factura` VALUES ('PC1-1', 'contado', '2020-04-08', 26.27, 4.73, 31.00, '1', 'Jorge Luis Guzman', 1, 0.00);
INSERT INTO `factura` VALUES ('PC1-1', 'contado', '2020-04-08', 349.15, 62.84, 411.99, '3', 'abc def', 2, 0.00);
INSERT INTO `factura` VALUES ('PC1-1', 'contado', '2020-04-08', 150.83, 27.14, 177.97, '1', 'Jorge Luis Guzman', 3, 0.00);
INSERT INTO `factura` VALUES ('PC1-1', 'credito', '2020-04-08', 71.19, 12.81, 84.00, '0', 'N/A', 4, 0.00);
INSERT INTO `factura` VALUES ('PC1-4', 'contado', '2020-04-08', 1.69, 0.31, 2.00, '', '', 5, 0.00);
INSERT INTO `factura` VALUES ('PC1-6', 'contado', '2020-04-08', 288.09, 51.85, 339.94, '0', 'N/A', 6, 0.00);
INSERT INTO `factura` VALUES ('PC1-7', 'credito', '2020-04-09', 4.24, 0.76, 5.00, '0', 'N/A', 7, 0.00);
INSERT INTO `factura` VALUES ('PC1-8', 'contado', '2020-04-09', 4.24, 0.76, 5.00, '0', 'N/A', 8, 0.00);
INSERT INTO `factura` VALUES ('PC1-9', 'credito', '2020-04-09', 42.37, 7.63, 50.00, '1', 'Jorge Luis Guzman', 9, 0.00);
INSERT INTO `factura` VALUES ('PC1-10', 'credito', '2020-04-09', 423.73, 76.27, 500.00, '1', 'Jorge Luis Guzman', 10, 0.00);
INSERT INTO `factura` VALUES ('PC1-11', 'credito', '2020-04-09', 423.73, 76.27, 500.00, '1', 'Jorge Luis Guzman', 11, 0.00);
INSERT INTO `factura` VALUES ('PC1-12', 'credito', '2020-04-09', 320.34, 57.66, 378.00, '1', 'Jorge Luis Guzman', 12, 0.00);
INSERT INTO `factura` VALUES ('PC1-13', 'credito', '2020-04-09', 423.73, 76.27, 500.00, '1', 'Jorge Luis Guzman', 13, 500.00);
INSERT INTO `factura` VALUES ('PC1-14', 'credito', '2020-04-09', 423.73, 76.27, 500.00, '2', 'jorge guzman', 14, 500.00);
INSERT INTO `factura` VALUES ('PC1-15', 'credito', '2020-04-09', 42.37, 7.63, 50.00, '2', 'jorge guzman', 15, 50.00);
INSERT INTO `factura` VALUES ('PC1-16', 'credito', '2020-04-09', 267.78, 48.19, 315.97, '2', 'jorge guzman', 16, 315.97);
INSERT INTO `factura` VALUES ('PC1-17', 'contado', '2020-04-09', 4.24, 0.76, 5.00, '0', 'N/A', 17, 5.00);
INSERT INTO `factura` VALUES ('PC1-18', 'contado', '2020-04-09', 4.24, 0.76, 5.00, '0', 'N/A', 18, 5.00);
INSERT INTO `factura` VALUES ('PC1-19', 'contado', '2020-04-09', 4.24, 0.76, 5.00, '2', 'jorge guzman', 19, 5.00);
INSERT INTO `factura` VALUES ('PC1-20', 'contado', '2020-04-09', 4.24, 0.76, 5.00, '2', 'jorge guzman', 20, 5.00);
INSERT INTO `factura` VALUES ('PC1-21', 'contado', '2020-04-09', 4.24, 0.76, 5.00, '2', 'jorge guzman', 21, 5.00);
INSERT INTO `factura` VALUES ('PC1-22', 'contado', '2020-04-09', 4.24, 0.76, 5.00, '2', 'jorge guzman', 22, 5.00);
INSERT INTO `factura` VALUES ('PC1-23', 'contado', '2020-04-09', 4.24, 0.76, 5.00, '2', 'jorge guzman', 23, 5.00);
INSERT INTO `factura` VALUES ('PC1-24', 'contado', '2020-04-09', 4.24, 0.76, 5.00, '2', 'jorge guzman', 24, 5.00);
INSERT INTO `factura` VALUES ('PC1-23', 'contado', '2020-04-09', 4.24, 0.76, 5.00, '2', 'jorge guzman', 25, 5.00);
INSERT INTO `factura` VALUES ('PC1-26', 'contado', '2020-04-09', 4.24, 0.76, 5.00, '0', 'N/A', 26, 0.00);
INSERT INTO `factura` VALUES ('PC1-26', 'contado', '2020-04-09', 4.24, 0.76, 5.00, '0', 'N/A', 27, 0.00);
INSERT INTO `factura` VALUES ('PC1-28', 'credito', '2020-04-09', 854.25, 153.75, 1008.00, '2', 'jorge guzman', 28, 1008.00);
INSERT INTO `factura` VALUES ('PC1-29', 'credito', '2020-04-09', 284.75, 51.25, 336.00, '2', 'jorge guzman', 29, 336.00);
INSERT INTO `factura` VALUES ('PC1-30', 'credito', '2020-04-09', 3184.74, 573.26, 3758.00, '2', 'jorge guzman', 30, 3758.00);
INSERT INTO `factura` VALUES ('PC1-31', 'credito', '2020-04-09', 284.75, 51.25, 336.00, '1', 'Jorge Luis Guzman', 31, 336.00);
INSERT INTO `factura` VALUES ('PC1-32', 'credito', '2020-04-09', 288.99, 52.01, 341.00, '1', 'Jorge Luis Guzman', 32, 341.00);
INSERT INTO `factura` VALUES ('PC1-33', 'credito', '2020-04-09', 288.99, 52.01, 341.00, '1', 'Jorge Luis Guzman', 33, 341.00);
INSERT INTO `factura` VALUES ('PC1-34', 'credito', '2020-04-09', 284.75, 51.25, 336.00, '1', 'Jorge Luis Guzman', 34, 336.00);
INSERT INTO `factura` VALUES ('PC1-35', 'credito', '2020-04-09', 4.24, 0.76, 5.00, '1', 'Jorge Luis Guzman', 35, 5.00);
INSERT INTO `factura` VALUES ('PC1-37', 'contado', '2020-04-10', 38.98, 7.01, 45.99, '0', 'N/A', 36, 0.00);
INSERT INTO `factura` VALUES ('PC1-38', 'contado', '2020-04-10', 38.98, 7.01, 45.99, '0', 'N/A', 37, 0.00);
INSERT INTO `factura` VALUES ('PC1-39', 'contado', '2020-04-10', 62.71, 11.28, 73.99, '0', 'N/A', 38, 0.00);
INSERT INTO `factura` VALUES ('PC1-40', 'contado', '2020-04-10', 10.17, 1.83, 12.00, '0', 'N/A', 39, 0.00);
INSERT INTO `factura` VALUES ('PC1-41', 'contado', '2020-04-10', 10.17, 1.83, 12.00, '0', 'N/A', 40, 0.00);
INSERT INTO `factura` VALUES ('PC1-42', 'contado', '2020-04-10', 137.26, 24.71, 161.97, '0', 'N/A', 41, 0.00);
INSERT INTO `factura` VALUES ('PC1-43', 'contado', '2020-04-10', 26.27, 4.73, 31.00, '0', 'N/A', 42, 0.00);
INSERT INTO `factura` VALUES ('PC1-44', 'contado', '2020-04-10', 4.24, 0.76, 5.00, '0', 'N/A', 43, 0.00);
INSERT INTO `factura` VALUES ('PC1-45', 'contado', '2020-04-10', 4.24, 0.76, 5.00, '0', 'N/A', 44, 0.00);
INSERT INTO `factura` VALUES ('PC1-46', 'credito', '2020-04-10', 4.24, 0.76, 5.00, '1', 'Jorge Luis Guzman', 45, 5.00);
INSERT INTO `factura` VALUES ('PC1-47', 'contado', '2020-04-10', 4.24, 0.76, 5.00, '0', 'N/A', 46, 0.00);
INSERT INTO `factura` VALUES ('PC1-48', 'credito', '2020-04-10', 4.24, 0.76, 5.00, '1', 'Jorge Luis Guzman', 47, 5.00);
INSERT INTO `factura` VALUES ('PC1-49', 'contado', '2020-04-10', 4.24, 0.76, 5.00, '0', 'N/A', 48, 0.00);
INSERT INTO `factura` VALUES ('PC1-50', 'credito', '2020-04-10', 4.24, 0.76, 5.00, '1', 'Jorge Luis Guzman', 49, 5.00);
INSERT INTO `factura` VALUES ('PC1-51', 'contado', '2020-04-10', 4.24, 0.76, 5.00, '0', 'N/A', 50, 0.00);
INSERT INTO `factura` VALUES ('PC1-52', 'contado', '2020-04-10', 82.18, 14.79, 96.97, '0', 'N/A', 51, 0.00);
INSERT INTO `factura` VALUES ('PC1-53', 'contado', '2020-04-10', 46.61, 8.39, 55.00, '0', 'N/A', 52, 0.00);
INSERT INTO `factura` VALUES ('PC1-54', 'contado', '2020-04-10', 3132.20, 563.80, 3696.00, '0', 'N/A', 53, 0.00);
INSERT INTO `factura` VALUES ('PC1-55', 'contado', '2020-04-10', 4.24, 0.76, 5.00, '0', 'N/A', 54, 0.00);
INSERT INTO `factura` VALUES ('PC1-56', 'contado', '2020-04-10', 284.75, 51.25, 336.00, '0', 'N/A', 55, 0.00);
INSERT INTO `factura` VALUES ('PC1-57', 'contado', '2020-04-10', 5155.13, 927.87, 6083.00, '0', 'N/A', 56, 0.00);
INSERT INTO `factura` VALUES ('PC1-58', 'contado', '2020-04-10', 8.48, 1.52, 10.00, '0', 'N/A', 57, 0.00);
INSERT INTO `factura` VALUES ('PC1-59', 'contado', '2020-04-10', 1296.62, 233.38, 1530.00, '0', 'N/A', 58, 0.00);
INSERT INTO `factura` VALUES ('PC1-60', 'contado', '2020-04-10', 6477.98, 1166.02, 7644.00, '0', 'N/A', 59, 0.00);
INSERT INTO `factura` VALUES ('PC1-61', 'pago de cuentas', '2020-04-10', 0.00, 0.00, 1800.00, '1', 'Jorge Luis Guzman', 60, -1800.00);
INSERT INTO `factura` VALUES ('PC1-61', 'pago de cuentas', '2020-04-10', 0.00, 0.00, 2.06, '2', 'jorge guzman', 61, -2.06);
INSERT INTO `factura` VALUES ('PC1-61', 'pago de cuentas', '2020-04-10', 0.00, 0.00, 0.91, '2', 'jorge guzman', 62, -0.91);
INSERT INTO `factura` VALUES ('PC1-61', 'pago de cuentas', '2020-04-10', 0.00, 0.00, 1000.00, '2', 'jorge guzman', 63, -1000.00);
INSERT INTO `factura` VALUES ('PC1-61', 'pago de cuentas', '2020-04-10', 0.00, 0.00, 4.00, '1', 'Jorge Luis Guzman', 64, -4.00);
INSERT INTO `factura` VALUES ('PC1-61', 'pago de cuentas', '2020-04-11', 0.00, 0.00, 874.00, '1', 'Jorge Luis Guzman', 65, -874.00);
INSERT INTO `factura` VALUES ('PC1-62', 'pago de cuentas', '2020-04-11', 0.00, 0.00, 874.00, '1', 'Jorge Luis Guzman', 66, -874.00);
INSERT INTO `factura` VALUES ('PC1-63', 'pago de cuentas', '2020-04-11', 0.00, 0.00, 1002.97, '2', 'jorge guzman', 67, -1002.97);
INSERT INTO `factura` VALUES ('PC1-64', 'pago de cuentas', '2020-04-11', 0.00, 0.00, 5.00, '1', 'Jorge Luis Guzman', 68, -5.00);
INSERT INTO `factura` VALUES ('PC1-65', 'pago de cuentas', '2020-04-11', 0.00, 0.00, 2700.00, '2', 'jorge guzman', 69, -2700.00);
INSERT INTO `factura` VALUES ('PC1-66', 'pago de cuentas', '2020-04-11', 0.00, 0.00, 0.03, '2', 'jorge guzman', 70, -0.03);
INSERT INTO `factura` VALUES ('PC1-67', 'pago de cuentas', '2020-04-11', 0.00, 0.00, 297.00, '2', 'jorge guzman', 71, -297.00);
INSERT INTO `factura` VALUES ('PC1-68', 'pago de cuentas', '2020-04-11', 0.00, 0.00, 500.00, '2', 'jorge guzman', 72, -500.00);
INSERT INTO `factura` VALUES ('PC1-69', 'pago de cuentas', '2020-04-11', 0.00, 0.00, 15.00, '2', 'jorge guzman', 73, -15.00);
INSERT INTO `factura` VALUES ('PC1-70', 'pago de cuentas', '2020-04-11', 0.00, 0.00, 35.00, '2', 'jorge guzman', 74, -35.00);
INSERT INTO `factura` VALUES ('PC1-71', 'contado', '2020-04-11', 4.24, 0.76, 5.00, '0', 'N/A', 75, 0.00);
INSERT INTO `factura` VALUES ('PC1-72', 'pago de cuentas', '2020-04-11', 0.00, 0.00, 5.00, '0', '', 76, -5.00);
INSERT INTO `factura` VALUES ('PC1-73', 'pago de cuentas', '2020-04-11', 0.00, 0.00, 5.00, '2', 'jorge guzman', 77, -5.00);
INSERT INTO `factura` VALUES ('PC1-74', 'pago de cuentas', '2020-04-11', 0.00, 0.00, 5.00, '2', '', 78, -5.00);
INSERT INTO `factura` VALUES ('PC1-75', 'pago de cuentas', '2020-04-11', 0.00, 0.00, 5.00, '2', 'jorge guzman', 79, -5.00);
INSERT INTO `factura` VALUES ('PC1-76', 'pago de cuentas', '2020-04-11', 0.00, 0.00, 5.00, '2', '', 80, -5.00);
INSERT INTO `factura` VALUES ('PC1-77', 'contado', '2020-04-11', 76.20, 13.71, 89.91, '0', 'N/A', 81, 0.00);
INSERT INTO `factura` VALUES ('PC1-78', 'contado', '2020-04-11', 380.98, 68.57, 449.55, '0', 'N/A', 82, 0.00);
INSERT INTO `factura` VALUES ('PC1-79', 'pago de cuentas', '2020-04-13', 0.00, 0.00, 25.00, '2', 'jorge guzman', 83, -25.00);
INSERT INTO `factura` VALUES ('PC1-80', 'pago de cuentas', '2020-04-13', 0.00, 0.00, 5.00, '2', 'jorge guzman', 84, -5.00);
INSERT INTO `factura` VALUES ('PC1-81', 'contado', '2020-04-13', 25.44, 4.56, 30.00, '0', 'N/A', 85, 0.00);
INSERT INTO `factura` VALUES ('PC1-82', 'contado', '2020-04-13', 29.68, 5.32, 35.00, '1', 'Jorge Luis Guzman', 86, 0.00);
INSERT INTO `factura` VALUES ('PC1-83', 'credito', '2020-04-13', 2278.00, 410.00, 2688.00, '1', 'Jorge Luis Guzman', 87, 2688.00);
INSERT INTO `factura` VALUES ('PC1-84', 'contado', '2020-04-13', 660.38, 118.84, 779.22, '1', 'Jorge Luis Guzman', 88, 0.00);
INSERT INTO `factura` VALUES ('PC1-85', 'contado', '2020-04-21', 4.24, 0.76, 5.00, '0', 'N/A', 89, 0.00);
INSERT INTO `factura` VALUES ('PC1-86', 'pago de cuentas', '2020-04-28', 0.00, 0.00, 5.00, '1', 'Jorge Luis Guzman', 90, -5.00);
INSERT INTO `factura` VALUES ('PC1-87', 'contado', '2020-04-28', 16.96, 3.04, 20.00, '0', 'N/A', 91, 0.00);
INSERT INTO `factura` VALUES ('PC1-88', 'contado', '2020-04-28', 210.16, 37.84, 248.00, '0', 'N/A', 92, 0.00);
INSERT INTO `factura` VALUES ('PC1-89', 'contado', '2020-04-28', 4.24, 0.76, 5.00, '0', 'N/A', 93, 0.00);
INSERT INTO `factura` VALUES ('PC1-90', 'contado', '2020-04-28', 4.24, 0.76, 5.00, '0', 'N/A', 94, 0.00);
INSERT INTO `factura` VALUES ('PC1-91', 'pago de cuentas', '2020-04-28', 0.00, 0.00, 5.00, '1', 'Jorge Luis Guzman', 95, -5.00);

-- --------------------------------------------------------

-- 
-- Estructura de tabla para la tabla `sesion`
-- 

CREATE TABLE `sesion` (
  `idsesion` int(5) NOT NULL,
  `numerofactura` varchar(9) NOT NULL,
  `total` float(11,2) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- Volcar la base de datos para la tabla `sesion`
-- 

INSERT INTO `sesion` VALUES (0, 'pc1-29', 0.00);
INSERT INTO `sesion` VALUES (0, 'PC1-42', 161.97);
INSERT INTO `sesion` VALUES (1, 'PC1-45', 5.00);
INSERT INTO `sesion` VALUES (1, 'PC1-46', 0.00);
INSERT INTO `sesion` VALUES (3, 'PC1-47', 5.00);
INSERT INTO `sesion` VALUES (3, 'PC1-48', 0.00);
INSERT INTO `sesion` VALUES (4, 'PC1-49', 5.00);
INSERT INTO `sesion` VALUES (4, 'PC1-50', 0.00);
INSERT INTO `sesion` VALUES (4, 'PC1-51', 5.00);
INSERT INTO `sesion` VALUES (5, 'PC1-52', 96.97);
INSERT INTO `sesion` VALUES (5, 'PC1-53', 55.00);
INSERT INTO `sesion` VALUES (5, 'PC1-54', 3696.00);
INSERT INTO `sesion` VALUES (6, 'PC1-55', 5.00);
INSERT INTO `sesion` VALUES (6, 'PC1-56', 336.00);
INSERT INTO `sesion` VALUES (6, 'PC1-57', 6083.00);
INSERT INTO `sesion` VALUES (7, 'PC1-58', 10.00);
INSERT INTO `sesion` VALUES (7, 'PC1-59', 1530.00);
INSERT INTO `sesion` VALUES (7, 'PC1-60', 7644.00);
INSERT INTO `sesion` VALUES (0, 'PC1-71', 5.00);
INSERT INTO `sesion` VALUES (0, 'PC1-77', 89.91);
INSERT INTO `sesion` VALUES (0, 'PC1-78', 449.55);
INSERT INTO `sesion` VALUES (0, 'PC1-81', 30.00);
INSERT INTO `sesion` VALUES (13, 'PC1-82', 35.00);
INSERT INTO `sesion` VALUES (13, 'PC1-83', 0.00);
INSERT INTO `sesion` VALUES (0, 'PC1-84', 779.22);
INSERT INTO `sesion` VALUES (15, 'PC1-85', 5.00);
INSERT INTO `sesion` VALUES (18, 'PC1-87', 20.00);
INSERT INTO `sesion` VALUES (18, 'PC1-88', 248.00);
INSERT INTO `sesion` VALUES (19, 'PC1-89', 5.00);
INSERT INTO `sesion` VALUES (19, 'PC1-90', 5.00);

-- --------------------------------------------------------

-- 
-- Estructura de tabla para la tabla `tipoarticulo`
-- 

CREATE TABLE `tipoarticulo` (
  `idtipo` int(11) NOT NULL auto_increment,
  `destipo` varchar(45) NOT NULL,
  PRIMARY KEY  (`idtipo`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=9 ;

-- 
-- Volcar la base de datos para la tabla `tipoarticulo`
-- 

INSERT INTO `tipoarticulo` VALUES (1, 'sopita');
INSERT INTO `tipoarticulo` VALUES (4, 'azucar');
INSERT INTO `tipoarticulo` VALUES (3, 'cafe');
INSERT INTO `tipoarticulo` VALUES (5, 'arroz');
INSERT INTO `tipoarticulo` VALUES (6, 'sopa');
INSERT INTO `tipoarticulo` VALUES (7, 'sin definir');
INSERT INTO `tipoarticulo` VALUES (8, 'aceite');

-- --------------------------------------------------------

-- 
-- Estructura de tabla para la tabla `usuario`
-- 

CREATE TABLE `usuario` (
  `idempleado` varchar(11) NOT NULL,
  `usuario` varchar(22) NOT NULL,
  `contrasena` varchar(45) NOT NULL,
  `estatus` varchar(12) NOT NULL,
  `registro` int(11) NOT NULL auto_increment,
  PRIMARY KEY  (`registro`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

-- 
-- Volcar la base de datos para la tabla `usuario`
-- 

INSERT INTO `usuario` VALUES ('2', 'pan', 'efd18c35cc5f1ef7280a0a8bee5fbbd3', 'Activo', 5);
INSERT INTO `usuario` VALUES ('2', 'correo', '202cb962ac59075b964b07152d234b70', 'Activo', 4);
