CREATE TABLE IF NOT EXISTS users (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    created TIMESTAMP,
    modified TIMESTAMP,
    last_login TIMESTAMP,
    token VARCHAR(255) UNIQUE,
    is_active BOOLEAN NOT NULL
    );
