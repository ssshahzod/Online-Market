CREATE TABLE IF NOT EXISTS users(
    user_id  BIGINT NOT NULL PRIMARY KEY,
    firstName VARCHAR(20) NOT NULL,
    secondName VARCHAR(20) NOT NULL,
    email VARCHAR(20) NOT NULL,
    archive BOOLEAN,
    password VARCHAR(20) NOT NULL,
    appUserRole INT,
    locked BOOLEAN,
    enabled BOOLEAN
);

CREATE TABLE IF NOT EXISTS orders(
    order_id BIGINT NOT NULL PRIMARY KEY,
    created date NOT NULL,
    updated date NOT NULL,
    sum INT,
    address VARCHAR(40) NOT NULL
);

CREATE TABLE IF NOT EXISTS products(
    product_id  BIGINT NOT NULL PRIMARY KEY,
    title VARCHAR(20) NOT NULL,
    description VARCHAR(200) NOT NULL,
    price FLOAT
);

CREATE TABLE IF NOT EXISTS categories(
    category_id BIGINT NOT NULL PRIMARY KEY ,
    categoryName VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS buckets(
    bucket_id BIGINT NOT NULL PRIMARY KEY

);