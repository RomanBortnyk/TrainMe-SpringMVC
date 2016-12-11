-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema TrainMe
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema TrainMe
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `TrainMe` DEFAULT CHARACTER SET utf8 ;
USE `TrainMe` ;

-- -----------------------------------------------------
-- Table `TrainMe`.`avatar`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TrainMe`.`avatar` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `image` MEDIUMBLOB NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `TrainMe`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TrainMe`.`user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) CHARACTER SET 'utf8' NOT NULL,
  `last_name` VARCHAR(45) CHARACTER SET 'utf8' NOT NULL,
  `birthday_date` VARCHAR(45) CHARACTER SET 'utf8' NOT NULL,
  `login` VARCHAR(45) CHARACTER SET 'utf8' NOT NULL,
  `password` VARCHAR(45) CHARACTER SET 'utf8' NOT NULL,
  `email` VARCHAR(45) CHARACTER SET 'utf8' NOT NULL,
  `user_type` VARCHAR(45) CHARACTER SET 'utf8' NOT NULL,
  `description` TEXT CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `avatar_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  INDEX `fk_coach_avatar1_idx` (`avatar_id` ASC),
  CONSTRAINT `fk_user_avatar`
    FOREIGN KEY (`avatar_id`)
    REFERENCES `TrainMe`.`avatar` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `TrainMe`.`chat`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TrainMe`.`chat` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user1_id` INT(11) NOT NULL,
  `user2_id` INT(11) NOT NULL,
  `name` VARCHAR(45) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_chat_user1_idx` (`user1_id` ASC),
  INDEX `fk_chat_user2_idx` (`user2_id` ASC),
  CONSTRAINT `fk_chat_user1`
    FOREIGN KEY (`user1_id`)
    REFERENCES `TrainMe`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_chat_user2`
    FOREIGN KEY (`user2_id`)
    REFERENCES `TrainMe`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 16
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `TrainMe`.`discipline`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TrainMe`.`discipline` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) CHARACTER SET 'utf8' NOT NULL,
  `icon` BLOB NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 20
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `TrainMe`.`discipline_user_link`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TrainMe`.`discipline_user_link` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `discipline_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_discipline_user_link_user_idx` (`user_id` ASC),
  INDEX `fk_discipline_user_link_discipline_idx` (`discipline_id` ASC),
  CONSTRAINT `fk_discipline_user_link_discipline`
    FOREIGN KEY (`discipline_id`)
    REFERENCES `TrainMe`.`discipline` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_discipline_user_link_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `TrainMe`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `TrainMe`.`feedback`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TrainMe`.`feedback` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `author_id` INT(11) NOT NULL,
  `user_id` INT(11) NOT NULL,
  `text` TEXT CHARACTER SET 'utf8' NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_deal_author_idx` (`author_id` ASC),
  INDEX `fk_deal_user_idx` (`user_id` ASC),
  CONSTRAINT `fk_feedback_author`
    FOREIGN KEY (`author_id`)
    REFERENCES `TrainMe`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_feedback_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `TrainMe`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 19
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `TrainMe`.`message`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TrainMe`.`message` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `text` TEXT CHARACTER SET 'utf8' NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `chat_id` INT(11) NOT NULL,
  `author_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_message_chat1_idx` (`chat_id` ASC),
  INDEX `fk_message_user_idx` (`author_id` ASC),
  CONSTRAINT `fk_message_chat`
    FOREIGN KEY (`chat_id`)
    REFERENCES `TrainMe`.`chat` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_message_user`
    FOREIGN KEY (`author_id`)
    REFERENCES `TrainMe`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 292
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

