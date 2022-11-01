 CREATE TABLE CUSTOMER (CUST_ID NUMBER (6) NOT NULL AUTO_INCREMENT, CUST_NAME VARCHAR2(30) NOT NULL, PRIMARY KEY (CUST_ID));
 CREATE TABLE TRANSACTION (TRANS_ID NUMBER (10) NOT NULL AUTO_INCREMENT, CUST_ID NUMBER (6) NOT NULL, TRANS_AMT NUMBER (10,2) NOT NULL, TRANS_TIME DATE NOT NULL, PRIMARY KEY(TRANS_ID), FOREIGN KEY (CUST_ID) REFERENCES CUSTOMER(CUST_ID));

INSERT INTO CUSTOMER(CUST_NAME) VALUES ('Raja');
INSERT INTO CUSTOMER(CUST_NAME) VALUES ('Anie');
INSERT INTO CUSTOMER(CUST_NAME) VALUES ('Juvin');
INSERT INTO CUSTOMER(CUST_NAME) VALUES ('Jacob');
INSERT INTO CUSTOMER(CUST_NAME) VALUES ('David');
INSERT INTO CUSTOMER(CUST_NAME) VALUES ('Maria');


INSERT INTO TRANSACTION (CUST_ID, TRANS_AMT, TRANS_TIME) VALUES (1,52.10, CURRENT_TIMESTAMP);
INSERT INTO TRANSACTION (CUST_ID, TRANS_AMT, TRANS_TIME) VALUES (1,150, '2022-08-10');
INSERT INTO TRANSACTION (CUST_ID, TRANS_AMT, TRANS_TIME) VALUES (1,200, '2022-09-10');
INSERT INTO TRANSACTION (CUST_ID, TRANS_AMT, TRANS_TIME) VALUES (1,115, '2022-10-17');
INSERT INTO TRANSACTION (CUST_ID, TRANS_AMT, TRANS_TIME) VALUES (1,102, '2022-08-23');
INSERT INTO TRANSACTION (CUST_ID, TRANS_AMT, TRANS_TIME) VALUES (1,60.75, CURRENT_TIMESTAMP);
INSERT INTO TRANSACTION (CUST_ID, TRANS_AMT, TRANS_TIME) VALUES (2,52.10, CURRENT_TIMESTAMP);
INSERT INTO TRANSACTION (CUST_ID, TRANS_AMT, TRANS_TIME) VALUES (2,150, '2022-08-10');
INSERT INTO TRANSACTION (CUST_ID, TRANS_AMT, TRANS_TIME) VALUES (3,140, '2022-09-10');
INSERT INTO TRANSACTION (CUST_ID, TRANS_AMT, TRANS_TIME) VALUES (2,115, '2022-10-17');
INSERT INTO TRANSACTION (CUST_ID, TRANS_AMT, TRANS_TIME) VALUES (2,102, '2022-08-23');
INSERT INTO TRANSACTION (CUST_ID, TRANS_AMT, TRANS_TIME) VALUES (2,60.75, CURRENT_TIMESTAMP);
INSERT INTO TRANSACTION (CUST_ID, TRANS_AMT, TRANS_TIME) VALUES (2,60.20, CURRENT_TIMESTAMP) ;
INSERT INTO TRANSACTION (CUST_ID, TRANS_AMT, TRANS_TIME) VALUES (3,'52.10', CURRENT_TIMESTAMP) ;
INSERT INTO TRANSACTION (CUST_ID, TRANS_AMT, TRANS_TIME) VALUES (4,'52.10', CURRENT_TIMESTAMP);
INSERT INTO TRANSACTION (CUST_ID, TRANS_AMT, TRANS_TIME) VALUES (5,'52.10', CURRENT_TIMESTAMP);

commit;