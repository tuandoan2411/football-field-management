-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema sakila
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema football field management
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema football field management
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `football field management` ;
USE `football field management` ;

-- -----------------------------------------------------
-- Table `football field management`.`pitch_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `football field management`.`pitch_type` (
  `pitch_type_id` INT UNSIGNED NOT NULL,
  `price` DECIMAL(10,3) NOT NULL,
  PRIMARY KEY (`pitch_type_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `football field management`.`pitch`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `football field management`.`pitch` (
  `pitch_id` VARCHAR(45) NOT NULL,
  `pitch_type_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`pitch_id`),
  INDEX `fk_pitch_pitch_type_idx` (`pitch_type_id` ASC),
  CONSTRAINT `fk_pitch_pitch_type`
    FOREIGN KEY (`pitch_type_id`)
    REFERENCES `football field management`.`pitch_type` (`pitch_type_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `football field management`.`customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `football field management`.`customer` (
  `customer_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `phone_number` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`customer_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `football field management`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `football field management`.`role` (
  `role_id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`role_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `football field management`.`account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `football field management`.`account` (
  `user_name` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`user_name`),
  INDEX `fk_account_role1_idx` (`role_id` ASC),
  CONSTRAINT `fk_account_role1`
    FOREIGN KEY (`role_id`)
    REFERENCES `football field management`.`role` (`role_id`)
    ON DELETE CASCADE 
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `football field management`.`staff`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `football field management`.`staff` (
  `staff_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `phone_number` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NULL,
  `user_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`staff_id`),
  INDEX `fk_staff_account1_idx` (`user_name` ASC),
  UNIQUE INDEX `account_user_name_UNIQUE` (`user_name` ASC),
  CONSTRAINT `fk_employee_account1`
    FOREIGN KEY (`user_name`)
    REFERENCES `football field management`.`account` (`user_name`)
    ON DELETE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `football field management`.`match`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `football field management`.`match` (
  `match_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `time_in` DATETIME NOT NULL,
  `minute` INT NOT NULL,
  `pitch_id` VARCHAR(45) NOT NULL,
  `customer_id` INT UNSIGNED NOT NULL,
  `staff_id` INT UNSIGNED NULL,
  `is_paid` TINYINT(1) NOT NULL,
  PRIMARY KEY (`match_id`),
  INDEX `fk_match_pitch1_idx` (`pitch_id` ASC),
  INDEX `fk_match_customer1_idx` (`customer_id` ASC),
  INDEX `fk_match_staff1_idx` (`staff_id` ASC),
  CONSTRAINT `fk_match_pitch1`
    FOREIGN KEY (`pitch_id`)
    REFERENCES `football field management`.`pitch` (`pitch_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_match_customer1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `football field management`.`customer` (`customer_id`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_match_staff1`
    FOREIGN KEY (`staff_id`)
    REFERENCES `football field management`.`staff` (`staff_id`)
    ON DELETE SET NULL)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `football field management`.`drink`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `football field management`.`drink` (
  `drink_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `inventory_number` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`drink_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `football field management`.`bill`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `football field management`.`bill` (
  `bill_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `match_money_total` DECIMAL NULL,
  `drink_money_total` DECIMAL NULL,
  `total` DECIMAL NULL,
  `match_id` INT UNSIGNED NOT NULL,
  `payment_time` DATETIME NULL,
  PRIMARY KEY (`bill_id`),
  INDEX `fk_bill_match1_idx` (`match_id` ASC),
  UNIQUE INDEX `match_id_UNIQUE` (`match_id` ASC),
  CONSTRAINT `fk_bill_match1`
    FOREIGN KEY (`match_id`)
    REFERENCES `football field management`.`match` (`match_id`)
    ON DELETE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `football field management`.`drink_match`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `football field management`.`drink_match` (
  `drink_id` INT UNSIGNED NOT NULL,
  `match_id` INT UNSIGNED NOT NULL,
  `order_number` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`drink_id`, `match_id`),
  INDEX `fk_drink_has_match_match1_idx` (`match_id` ASC),
  INDEX `fk_drink_has_match_drink1_idx` (`drink_id` ASC),
  CONSTRAINT `fk_drink_has_match_drink1`
    FOREIGN KEY (`drink_id`)
    REFERENCES `football field management`.`drink` (`drink_id`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_drink_has_match_match1`
    FOREIGN KEY (`match_id`)
    REFERENCES `football field management`.`match` (`match_id`)
    ON DELETE CASCADE)
ENGINE = InnoDB;

DELIMITER $$
CREATE FUNCTION `football field management`.`get_match_price`(match_id INT) RETURNS DECIMAL(10,3)
	DETERMINISTIC
    READS SQL DATA
BEGIN
  DECLARE v_result DECIMAL(10,3);
  DECLARE v_minute INTEGER;      
  DECLARE v_pitch_id VARCHAR(45); 
  DECLARE v_pitch_type_id INTEGER; 
  DECLARE v_price DECIMAL(10,3);

	SELECT `minute`, pitch_id INTO v_minute, v_pitch_id
    FROM `match`
    WHERE `match`.match_id = match_id;
    
    SELECT pitch_type_id INTO v_pitch_type_id
    FROM pitch
    WHERE pitch_id = v_pitch_id;
    
    SELECT price INTO v_price
    FROM pitch_type
    WHERE pitch_type_id = v_pitch_type_id;

	SET v_result = (v_price / 60) * v_minute;

	RETURN v_result;
END$$

DELIMITER ;

insert into role values(0, 'admin');
insert into role values(1, 'user');

insert into account values('admin', 'admin', 0);
insert into account values('nv1', '123', 1);
insert into account values('nv2', '456', 1);

insert into staff(name, phone_number, address, user_name) values('hung', '093538212', 'ho chi minh', 'nv1');
insert into staff(name, phone_number, address, user_name) values('dung', '0356727933', 'dong nai', 'nv2');

insert into customer(`name`, phone_number) values('tuan', '0354727259');
insert into customer(`name`, phone_number) values('trung', '0779187644');
insert into customer(`name`, phone_number) values('viet', '0358769223');

insert into pitch_type values(5, 200000);
insert into pitch_type values(7, 250000);
insert into pitch_type values(11, 300000);

insert into pitch values('a1', 5);
insert into pitch values('a2', 5);
insert into pitch values('a3', 5);

insert into pitch values('b1', 7);
insert into pitch values('b2', 7);
insert into pitch values('b3', 7);

insert into pitch values('c1', 11);
insert into pitch values('c2', 11);
insert into pitch values('c3', 11);


insert into `match` values(1, '2021-6-9 6:00:00', 60, 'a2', 2, 2, 0);
insert into `match` values(2, '2021-6-15 4:00:00', 90, 'a3', 3, 1, false);
insert into `match` values(3, '2021-6-19 7:00:00', 120, 'a3', 1, 1, true);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
