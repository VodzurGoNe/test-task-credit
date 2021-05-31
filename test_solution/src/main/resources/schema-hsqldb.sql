CREATE TABLE PUBLIC.PUBLIC.BANK
(
    BANK_ID               uuid not null,

    BANK_TITLE varchar(255),

    PRIMARY KEY (BANK_ID)
);

CREATE TABLE PUBLIC.PUBLIC.CLIENT
(
    CLIENT_ID             uuid not null
        constraint CLIENT_pkey
            primary key,
    CLIENT_EMAIL          varchar(205),
    CLIENT_FIO            varchar(205),
    CLIENT_PASSPORTNUMBER varchar(205),
    CLIENT_PHONENUMBER    varchar(205),
    BANK_ID               uuid
        constraint fkim95abd01ot21q2dn9mpxo7nc
            references BANK
);

create table PUBLIC.PUBLIC.CREDIT
(
    CREDIT_ID      uuid not null
        constraint CREDIT_pkey
            primary key,
    CREDIT_LIMIT   numeric(19, 2),


    CREDIT_PERCENT numeric(19, 2),


    CREDIT_TITLE   varchar(255),
    BANK_ID        uuid
        constraint fk1x1iaf0dsp6s1tjhdihhedgdk
            references BANK
);

create table PUBLIC.PUBLIC.CREDITOFFER
(
    CREDITOFFER_ID               uuid not null
        constraint CREDITOFFER_pkey
            primary key,
    CREDITOFFER_AMOUNT           numeric(19, 2),


    CREDITOFFER_FIRSTPAY         numeric(19, 2),


    CREDITOFFER_NAMED            varchar(255),
    CREDITOFFER_PERCENTSUM       numeric(19, 2),
    CREDITOFFER_PERIOD_IN_MONTHS integer,


    BANK_ID                      uuid
        constraint fkkij8lijqb84vmmkcnhssnpeji
            references BANK,
    CLIENT_ID                    uuid
        constraint fk18hoi8h4wktsstqg7c6bot11q
            references CLIENT,
    CREDIT_ID                    uuid
        constraint fk385fjsia5r7avrt8pk7t1hchk
            references CREDIT
);

create table PUBLIC.PUBLIC.PAYMENTSCHEDULE
(
    PAYMENTSCHEDULE_ID                    uuid not null
        constraint PAYMENTSCHEDULE_pkey
            primary key,
    PAYMENTSCHEDULE_AMOUNT_OF_THE_BODY    numeric(19, 2),
    PAYMENTSCHEDULE_AMOUNT_OF_THE_PERCENT numeric(19, 2),
    PAYMENTSCHEDULE_PAYMENT_AMOUNT        numeric(19, 2),
    PAYMENTSCHEDULE_PAYMENT_DATE          date,
    PAYMENTSCHEDULE_REMAINS               numeric(19, 2),
    CREDITOFFER_ID                        uuid
        constraint fk67sgkjw07dmkvmy1f6invw33k
            references CREDITOFFER
);

INSERT INTO BANK (BANK_ID, BANK_TITLE)

values ('11a80fb6-1ca4-435c-8fb3-2215d145db41', 'PRIME'),
       ('21a80fb6-1ca4-435c-8fb3-2215d145db41', 'Second'),
       ('31a80fb6-1ca4-435c-8fb3-2215d145db41', 'JBC'),
       ('41a80fb6-1ca4-435c-8fb3-2215d145db41', ';)');

INSERT INTO CREDIT (CREDIT_ID, CREDIT_TITLE, CREDIT_LIMIT, CREDIT_PERCENT, BANK_ID)

values ('51a80fb6-1ca4-435c-8fb3-2215d145db41', 'Mortgage', 15000000, 8,'11a80fb6-1ca4-435c-8fb3-2215d145db41'),
       ('61a80fb6-1ca4-435c-8fb3-2215d145db41', 'Сonsumer', 700000, 30, '21a80fb6-1ca4-435c-8fb3-2215d145db41'),
       ('71a80fb6-1ca4-435c-8fb3-2215d145db41', 'Auto credit', 10000000, 15, '31a80fb6-1ca4-435c-8fb3-2215d145db41'),
       ('81a80fb6-1ca4-435c-8fb3-2215d145db41', '"installment plan"', 150000, 6, '41a80fb6-1ca4-435c-8fb3-2215d145db41');

INSERT INTO CLIENT (CLIENT_ID, CLIENT_FIO, CLIENT_PHONENUMBER, CLIENT_EMAIL, CLIENT_PASSPORTNUMBER, BANK_ID)

values ('91a80fb6-1ca4-435c-8fb3-2215d145db41', 'Gruzdov Vladislav Grigorevich', '907-964-39-63', 'tomcat@random.com', '31-11-502830', '11a80fb6-1ca4-435c-8fb3-2215d145db41'),
       ('62a80fb6-1ca4-435c-8fb3-2215d145db41', 'Gruzdov Vladimir Grigorevich', '907-964-39-63', 'tomcat@random.com', '31-11-502830', '21a80fb6-1ca4-435c-8fb3-2215d145db41'),
       ('73a80fb6-1ca4-435c-8fb3-2215d145db41', 'Gruzdov Valeriy Grigorevich', '907-964-39-63', 'tomcat@random.com', '31-11-502830', '31a80fb6-1ca4-435c-8fb3-2215d145db41'),
       ('84a80fb6-1ca4-435c-8fb3-2215d145db41', 'Gruzdov =P Grigorevich', '907-964-39-63', 'tomcat@random.com', '31-11-502830', '41a80fb6-1ca4-435c-8fb3-2215d145db41');