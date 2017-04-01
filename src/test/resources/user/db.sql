DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(12) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `roles` varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8mb4