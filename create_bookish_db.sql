CREATE SCHEMA `bookish`;

CREATE TABLE `bookish`.`books` (
  `id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`));

ALTER TABLE `bookish`.`books`
ADD COLUMN `title` VARCHAR(45) NULL AFTER `id`;

ALTER TABLE `bookish`.`books`
ADD COLUMN `author` VARCHAR(45) NULL AFTER `title`;

ALTER TABLE `bookish`.`books`
ADD COLUMN `isbn` VARCHAR(45) NULL AFTER `author`,
ADD UNIQUE INDEX `bookscol_UNIQUE` (`isbn` ASC) VISIBLE;

ALTER TABLE `bookish`.`books`
ADD COLUMN `copies_available` INT NULL AFTER `isbn`;













CREATE SCHEMA `bookish`;

CREATE TABLE `bookish`.`books` (
	`id` INT NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(100) NOT NULL,
    `author` VARCHAR(100) NOT NULL,
    `isbn` VARCHAR(100) NULL,
    `copies_available` INT NULL,
	PRIMARY KEY(`id`)
);

CREATE TABLE `bookish`.`members` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL,
    PRIMARY KEY(`id`)
);

CREATE TABLE `bookish`.`books_lent` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `user_id` INT NOT NULL,
    `book_id` INT NOT NULL,
    `checked_out_on` DATETIME NOT NULL,
    `due_back_on` DATETIME NOT NULL,
    PRIMARY KEY(`id`),
    FOREIGN KEY (`user_id`) REFERENCES `members`(`id`),
    FOREIGN KEY (`book_id`) REFERENCES `books`(`id`)
);















