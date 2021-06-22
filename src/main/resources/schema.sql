CREATE TABLE BOOK
(ID INT PRIMARY KEY,
 NAME VARCHAR(100) DEFAULT '',
 RELEASED_ON DATETIME,
 REVIEW_SCORE INT,
 CATEGORY VARCHAR(100),
 RATING VARCHAR(100));


insert into BOOK values (1, 'Harry Potter and the Philosopher''s Stone', '1997-03-01 23:59:59', 99, 'Fantasy', 'Universal');
insert into BOOK values (2, 'The Little Prince', '1943-08-20 23:59:59', 95, 'Novella', 'Children''s Literature');
insert into BOOK values (3, 'Dream of the Red Chamber', '1784-03-22 23:59:59', 98, 'Family Saga', 'General');
insert into BOOK values (4, 'The Hobbit', '1937-07-23 23:59:59', 91, 'Fantasy', 'Children''s Literature');
insert into BOOK values (5, 'And Then There Were None', '1939-11-13 23:59:59', 96, 'Mystery', 'Children''s Literature');
insert into BOOK values (6, 'Lolita', '1955-06-14 23:59:59', 89, 'Family Saga', 'Teenage Literature');
insert into BOOK values (7, 'The Da Vinci Code', '2003-12-05 23:59:59', 77, 'Thriller', 'Mature');
insert into BOOK values (8, 'The Adventures of Pinocchio', '1881-08-13 23:59:59', 88, 'Fantasy', 'Children''s Literature');
insert into BOOK values (9, 'The Catcher in the Rye', '1951-03-22 23:59:59', 97, 'General', 'Teenage Literature');
insert into BOOK values (10, 'The Godfather', '1969-06-24 23:59:59', 90, 'Adventure', 'Mature');