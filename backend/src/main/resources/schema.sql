CREATE TABLE IF NOT EXISTS user_account (
    id          VARCHAR(36),
    username    VARCHAR(255) NOT NULL,
    email       VARCHAR(255) NOT NULL UNIQUE,
    password    VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS vault_account (
    id          VARCHAR(36),
    owner       VARCHAR(36),
    name        VARCHAR(255) NOT NULL,
    username    VARCHAR(255) NOT NULL,
    password    VARCHAR(60) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (owner)
        REFERENCES user_account(id)
        ON DELETE CASCADE
);