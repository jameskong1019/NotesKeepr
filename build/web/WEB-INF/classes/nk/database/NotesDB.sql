DROP DATABASE if exists NotesDB;
CREATE DATABASE NotesDB;

USE NotesDB;

CREATE TABLE users (
  username				VARCHAR(30) NOT NULL,
  password				VARCHAR(25) NOT NULL,
  email					VARCHAR(50) NOT NULL,
  firstname				VARCHAR(20),
  lastname				VARCHAR(20),
  phonenumber           VARCHAR(15),
  gender				CHAR(1),
  activate int(1),
  
  PRIMARY KEY (username),
  UNIQUE (email)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

CREATE TABLE valid_email (
  username VARCHAR(30) NOT NULL,
  validUserUUID VARCHAR(50) NOT NULL,

  PRIMARY KEY (username),
  KEY `valid_username` (`username`),
  CONSTRAINT `valid_username` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE reset_password(
  username VARCHAR(30) NOT NULL,
  resetTime datetime NOT NULL,
  resetPasswordUUID VARCHAR(50) NOT NULL,

  PRIMARY KEY (username),
  KEY `reset_username` (`username`),
  CONSTRAINT `reset_username` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DELIMITER $$

CREATE TRIGGER before_insert_check_gender
     BEFORE INSERT ON users FOR EACH ROW
     BEGIN
          IF New.gender NOT IN ('M', 'F', 'U', 'N')
          THEN
          		signal sqlstate '45000' set message_text = 'Cannot add user: gender must be M, F, U, or N.';
#               CALL 'Cannot add user: gender must be M, F, U, or N.';
          END IF;
     END;
$$

DELIMITER ;

INSERT INTO `users` VALUES
	('admin','password','jameskongcprg352+admin@gmail.com','Admin','Admin','403-555-1212','U', 1),
	('abe','password','jameskongcprg352+abe@gmail.com','Abe','Aberly','403-555-3434','M', 1),
	('barb','password','jameskongcprg352+barb@gmail.com','Barb','Barker','403-555-5656','F', 1);

CREATE TABLE `notes` (
  `noteid` int(11) NOT NULL AUTO_INCREMENT,
  `datecreated` datetime NOT NULL,
  `title` varchar(20) NOT NULL,
  `contents` LONGTEXT NOT NULL,
  `owner` varchar(25) NOT NULL,
  PRIMARY KEY (`noteid`),
  KEY `note_owner` (`owner`),
  CONSTRAINT `note_owner` FOREIGN KEY (`owner`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

INSERT INTO `notes` VALUES (1,'2015-10-28 22:52:47','title1', 'test','abe'), (2,'2015-10-28 22:52:48', 'title2', 'test2','abe'),
(0, SYSDATE(), 'test1', 'test', 'admin'),
(0, SYSDATE(), 'test2', 'test', 'admin'),
(0, SYSDATE(), 'test3', 'test', 'admin'),
(0, SYSDATE(), 'test4', 'test', 'admin'),
(0, SYSDATE(), 'test5', 'test', 'admin'),
(0, SYSDATE(), 'test6', 'test', 'admin'),
(0, SYSDATE(), 'test7', 'test', 'admin'),
(0, SYSDATE(), 'test8', 'test', 'admin'),
(0, SYSDATE(), 'test9', 'test', 'admin'),
(0, SYSDATE(), 'test10', 'test', 'admin');

CREATE TABLE `notecollaborators` (
  `noteid` int(11) NOT NULL,
  `username` varchar(25) NOT NULL,
  PRIMARY KEY (`noteid`,`username`),
  KEY `fk_NoteCollaborator_user1_idx` (`username`),
  CONSTRAINT `fk_NoteCollaborator_note1` FOREIGN KEY (`noteid`) REFERENCES `notes` (`noteid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_NoteCollaborator_user1` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `notecollaborators` VALUES (1, 'barb');

CREATE TABLE `roles` (
  `roleid` int(11) NOT NULL,
  `rolename` varchar(25) NOT NULL,
  PRIMARY KEY (`roleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `roles` VALUES (1,'admin');

CREATE TABLE `userroles` (
  `username` varchar(25) NOT NULL,
  `roleid` int(11) NOT NULL,
  PRIMARY KEY (`username`,`roleid`),
  KEY `userrole_role_idx` (`roleid`),
  CONSTRAINT `userrole_role` FOREIGN KEY (`roleid`) REFERENCES `roles` (`roleid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `userrole_user` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `userroles` VALUES ('admin',1);
