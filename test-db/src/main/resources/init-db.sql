INSERT INTO CLIENT (client_id , firstname , lastname , passport) VALUES ( 1, 'IVAN', 'IVANOV' , 'AB1234567' );
INSERT INTO CLIENT (client_id , firstname , lastname , passport) VALUES ( 2, 'PETR', 'PETROV' , 'AB1234511' );
INSERT INTO CLIENT (client_id , firstname , lastname , passport) VALUES ( 3, 'ANTON', 'SIDOROV' , 'AB1234522');

INSERT INTO ACCOUNT (account_id, accountData, accountBalance,  client_id) VALUES (1, 'BY01QWERTY3014124583883000', 10000,  1);
INSERT INTO ACCOUNT (account_id, accountData, accountBalance, client_id) VALUES (2, 'BY02QWERTY3014124583883000', 1000,  1);
INSERT INTO ACCOUNT (account_id, accountData, accountBalance,   client_id) VALUES (3, 'BY03QWERTY30141245838821323', 30000,  2);
INSERT INTO ACCOUNT (account_id, accountData, accountBalance,   client_id) VALUES (4, 'BY04QWERTY30141245822434210', 30000,  2);
INSERT INTO ACCOUNT (account_id, accountData, accountBalance,  client_id) VALUES (5, 'BY05QWERTY3014124583883000', 2131,  2);
