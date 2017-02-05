DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL DEFAULT '',
  `password` varchar(128) NOT NULL DEFAULT '',
  `salt` varchar(32) NOT NULL DEFAULT '',
  `head_url` varchar(256) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(128) NOT NULL DEFAULT '',
  `link` varchar(256) NOT NULL DEFAULT '',
  `image` varchar(256) NOT NULL DEFAULT '',
  `like_count` int NOT NULL,
  `comment_count` int NOT NULL,
  `created_date` datetime NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `login_ticket`;
CREATE TABLE `login_ticket` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `ticket` VARCHAR(45) NOT NULL,
  `expired` DATETIME NOT NULL,
  `status` INT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `ticket_UNIQUE` (`ticket` ASC)
  );


DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
`id` INT NOT NULL AUTO_INCREMENT,
`user_id` INT(11) NOT NULL,
`entity_id` INT(11) NOT NULL,
`created_date` DATETIME NOT NULL,
`entity_type` INT NOT NULL,
`content` TEXT,
`status` INT NOT NULL DEFAULT 0,
PRIMARY KEY (`id`),
INDEX `comment_index` (`entity_id` DESC, `entity_type` DESC)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `message`;
CREATE TABLE `news`.`message` (
`id` INT NOT NULL AUTO_INCREMENT,
`from_id` INT NOT NULL,
`to_id` INT NOT NULL,
`created_date` DATETIME NOT NULL,
`content` VARCHAR(225) NOT NULL,
`has_read` INT NOT NULL DEFAULT 0,
`conversation_id` VARCHAR(45) NOT NULL,
PRIMARY KEY (`id`),
INDEX `conversation` (`conversation_id` DESC)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

