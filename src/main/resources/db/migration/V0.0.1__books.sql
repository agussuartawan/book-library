CREATE TABLE books (
    BOOK_ID bigint PRIMARY KEY,
    NAME varchar(255),
    DESCRIPTION varchar(255),
    AUTHOR varchar(255),
    CREATED_AT DATETIME,
    UPDATED_AT DATETIME
);