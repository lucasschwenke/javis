CREATE TABLE users(
    id varchar(100) NOT NULL PRIMARY KEY,
    name varchar(100) NOT NULL,
    birthdate date NOT NULL,
    age int NOT NULL,
    gender varchar(100) NOT NULL,
    created_at timestamp NOT NULL,
    updated_at timestamp
);
