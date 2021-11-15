DROP TABLE IF EXISTS account;

DROP TABLE IF EXISTS client;

CREATE TABLE client
(
    client_id int NOT NULL auto_increment,
    firstname varchar(50) NOT NULL
    lastname varchar(50) NOT NULL,
    passport varchar(50) NOT NULL UNIQUE,
    CONSTRAINT client_pk PRIMARY KEY (client_id)
);

CREATE TABLE account (
    account_id int NOT NULL auto_increment,
    accountData varchar(255) NOT NULL,
    accountBalance int NOT NULL,
    client_id int NOT NULL,
        CONSTRAINT account_pk PRIMARY KEY (account_id),
        CONSTRAINT account_client_fk FOREIGN KEY (client_id) REFERENCES client(client_id)
);
