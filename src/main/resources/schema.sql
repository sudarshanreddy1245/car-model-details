CREATE DATABASE IF NOT EXISTS `fincity-database`;

DROP TABLE IF EXISTS `car_details`;

CREATE TABLE `fincity-database`.`car_details` (
  `id` bigint NOT NULL,
  `color` varchar(255) DEFAULT NULL,
  `manufacture_name` varchar(255) DEFAULT NULL,
  `manufacture_year` varchar(40) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `lastupdated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) 

