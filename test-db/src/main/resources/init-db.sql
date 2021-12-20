INSERT INTO CLIENT (clientId , firstname , lastname , passport)
VALUES ( 1, 'IVAN', 'IVANOV' , 'AB1234567' );
INSERT INTO CLIENT (clientId , firstname , lastname , passport)
VALUES ( 2, 'PETR', 'PETROV' , 'AB1234511' );
INSERT INTO CLIENT (clientId , firstname , lastname , passport)
VALUES ( 3, 'ANTON', 'SIDOROV' , 'AB1234522');

INSERT INTO ACCOUNT (accountId, info, balance, date_of_create, clientId)
VALUES (1, 'BY01QWERTY3014124583883000', 10000, '2015-07-04' ,1);
INSERT INTO ACCOUNT (accountId, info, balance, date_of_create, clientId)
VALUES (2, 'BY02QWERTY3014124583883000', 1000,'2021-01-02' ,  1);
INSERT INTO ACCOUNT (accountId, info, balance, date_of_create, clientId)
VALUES (3, 'BY03QWERTY30141245838821323', 30000,  '2020-07-02' ,2);
INSERT INTO ACCOUNT (accountId, info, balance, date_of_create, clientId)
VALUES (4, 'BY04QWERTY30141245822434210', 30000, '2019-01-02' , 2);
INSERT INTO ACCOUNT (accountId, info, balance, date_of_create, clientId)
VALUES (5, 'BY05QWERTY3014124583883000', 2131,  '2018-01-02' ,2);

