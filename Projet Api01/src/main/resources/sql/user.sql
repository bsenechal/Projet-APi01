CREATE DATABASE `projet_api01` /*!40100 DEFAULT CHARACTER SET latin1 */;

CREATE TABLE `user` (
  `idUser` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(60) NOT NULL,
  `enabled` tinyint(4) NOT NULL DEFAULT '1',
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `telephone` int(11) NOT NULL,
  `creation_date` date DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idUser`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

CREATE TABLE `autor` (
	`idAutor` int(11) NOT NULL AUTO_INCREMENT,
	`name` varchar(45) NOT NULL,
    PRIMARY KEY (`idAutor`)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `type` (
	`idType` int(11) NOT NULL AUTO_INCREMENT,
	`name` varchar(45) NOT NULL,
    PRIMARY KEY (`idType`)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `book` (
	`idBook` int(11) NOT NULL AUTO_INCREMENT,
	`title` varchar(45) NOT NULL,
	`idAutor` varchar(45) NOT NULL REFERENCES autor(idAutor),
    `idType` varchar(45) NOT NULL REFERENCES type(idType),
    PRIMARY KEY (`idBook`)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;