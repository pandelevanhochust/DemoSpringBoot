CREATE TABLE IF NOT EXISTS account (
                                       id SERIAL PRIMARY KEY,
                                       fullname VARCHAR(250),
    username VARCHAR(250),
    password VARCHAR(250),
    message VARCHAR(250),
    birthday DATE,
    updated_at TIMESTAMP
    );

ALTER TABLE account ADD COLUMN email VARCHAR(50);


