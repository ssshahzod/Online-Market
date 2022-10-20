CREATE TABLE IF NOT EXISTS users(
    user_id  BIGINT NOT NULL PRIMARY KEY,
    first_name VARCHAR(20) NOT NULL,
    second_name VARCHAR(20) NOT NULL,
    email VARCHAR(20) NOT NULL,
    archive BOOLEAN,
    password VARCHAR(20) NOT NULL,
    app_user_role VARCHAR(255)
);

INSERT INTO users VALUES (100, 'Alex', 'Bl', 'email@mail.com', 'false', 'asd', 'user');

CREATE SEQUENCE IF NOT EXISTS user_seq
AS BIGINT
START WITH 100;

CREATE TABLE IF NOT EXISTS orders(
    order_id BIGINT NOT NULL PRIMARY KEY,
    created date NOT NULL,
    updated date NOT NULL,
    sum INT,
    address VARCHAR(40) NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS order_seq
    AS BIGINT
    START WITH 100;

CREATE TABLE IF NOT EXISTS products(
    product_id  BIGINT NOT NULL PRIMARY KEY,
    title VARCHAR(20) NOT NULL,
    description VARCHAR(200) NOT NULL,
    price FLOAT
);

CREATE SEQUENCE IF NOT EXISTS product_seq
    AS BIGINT
    START WITH 100;

CREATE TABLE IF NOT EXISTS categories(
    category_id BIGINT NOT NULL PRIMARY KEY ,
    category_name VARCHAR(20)
);

CREATE SEQUENCE IF NOT EXISTS category_seq
    AS BIGINT
    START WITH 100;

CREATE TABLE IF NOT EXISTS buckets(
    bucket_id BIGINT NOT NULL PRIMARY KEY,
    user_id  BIGINT NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS bucket_seq
    AS BIGINT
    START WITH 100;
