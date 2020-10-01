CREATE SCHEMA bookish;

USE bookish;

CREATE TABLE books (
	id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    isbn VARCHAR(13) NULL UNIQUE,
    copies_total INT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE authors (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE author_books (
    id INT NOT NULL AUTO_INCREMENT,
    book_id INT NOT NULL,
    author_id INT NOT NULL,
     FOREIGN KEY (book_id) REFERENCES books(id),
     FOREIGN KEY (author_id) REFERENCES authors(id),
     PRIMARY KEY(id)
);

CREATE TABLE members (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE checked_out_books (
    id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    book_id INT NOT NULL,
    checked_out_on DATE NOT NULL,
    due_back_on DATE NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (user_id) REFERENCES members(id),
    FOREIGN KEY (book_id) REFERENCES books(id)
);


---------------------------------------

ALTER TABLE `bookish`.`author_books`
DROP FOREIGN KEY `author_books_ibfk_1`,
DROP FOREIGN KEY `author_books_ibfk_2`;
ALTER TABLE `bookish`.`author_books`
ADD CONSTRAINT `author_books_ibfk_1`
  FOREIGN KEY (`book_id`)
  REFERENCES `bookish`.`books` (`id`)
  ON DELETE CASCADE,
ADD CONSTRAINT `author_books_ibfk_2`
  FOREIGN KEY (`author_id`)
  REFERENCES `bookish`.`authors` (`id`)
  ON DELETE CASCADE;











