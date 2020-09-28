CREATE SCHEMA bookish2;

USE bookish2;

CREATE TABLE books (
	id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    isbn VARCHAR(100) NULL,
    copies_available INT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE book_authors (
    id INT NOT NULL AUTO_INCREMENT,
    book_id INT NOT NULL,
    author_id INT NOT NULL,
     FOREIGN KEY (book_id) REFERENCES books(id),
     FOREIGN KEY (author_id) REFERENCES book_authors(id),
     PRIMARY KEY(id)
);

CREATE TABLE members (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE books_lent (
    id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    book_id INT NOT NULL,
    checked_out_on DATETIME NOT NULL,
    due_back_on DATETIME NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (user_id) REFERENCES members(id),
    FOREIGN KEY (book_id) REFERENCES books(id)
);



------------------------------------------------------


CREATE SCHEMA `bookish`;

CREATE TABLE `bookish`.`books` (
	`id` INT NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(100) NOT NULL,
    `isbn` VARCHAR(100) NULL,
    `copies_available` INT NULL,
	PRIMARY KEY(`id`)
);

CREATE TABLE `bookish`.`book_authors` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `book_id` INT NOT NULL,
    `author_id` INT NOT NULL,
     FOREIGN KEY (`book_id`) REFERENCES `books`(`id`),
     FOREIGN KEY (`author_id`) REFERENCES `book_authors`(`id`)
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














