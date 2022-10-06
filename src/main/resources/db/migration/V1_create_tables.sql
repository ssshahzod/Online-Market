CREATE TABLE IF NOT EXISTS users(
    id  BIGINT NOT NULL PRIMARY KEY,
    firstName VARCHAR(20) NOT NULL,
    secondName VARCHAR(20) NOT NULL,
    email VARCHAR(20) NOT NULL,
    archive BOOLEAN,
    password VARCHAR(20) NOT NULL,
    appUserRole INT,
    locked BOOLEAN,
    enabled BOOLEAN
);
