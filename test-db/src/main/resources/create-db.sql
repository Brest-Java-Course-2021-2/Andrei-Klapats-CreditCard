DROP TABLE IF EXISTS account;

DROP TABLE IF EXISTS client;

CREATE TABLE client
(
    clientId int NOT NULL auto_increment,
    firstname varchar(50) NOT NULL,
    lastname varchar(50) NOT NULL,
    passport varchar(50) NOT NULL UNIQUE,
    CONSTRAINT client_pk PRIMARY KEY (clientId)
);

CREATE TABLE account (
    accountId int NOT NULL auto_increment,
    info varchar(255) NOT NULL,
    balance decimal NOT NULL,
    date_of_create date NOT NULL,
    clientId int NOT NULL,
        CONSTRAINT account_pk PRIMARY KEY (accountId),
        CONSTRAINT account_client_fk FOREIGN KEY (clientId) REFERENCES client(clientId)
);