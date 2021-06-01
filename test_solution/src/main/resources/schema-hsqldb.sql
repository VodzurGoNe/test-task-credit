CREATE TABLE PUBLIC.PUBLIC.BANK (
    BANK_ID uuid not null,
    BANK_TITLE varchar(255),
    PRIMARY KEY (BANK_ID)
);

CREATE TABLE PUBLIC.PUBLIC.CLIENT (
    CLIENT_ID uuid not null
    constraint CLIENT_pkey
    primary key,
    CLIENT_EMAIL varchar(255),
    CLIENT_FIO varchar(255),
    CLIENT_PASSPORT_NUMBER varchar(205),
    CLIENT_PHONE_NUMBER varchar(205),
    BANK_ID uuid
    constraint fkim95abd01ot21q2dn9mpxo7nc
    references BANK
);

create table PUBLIC.PUBLIC.CREDIT (
    CREDIT_ID uuid not null
    constraint CREDIT_pkey
    primary key,
    CREDIT_LIMIT numeric(19, 2),
    CREDIT_PERCENT numeric(19, 2),
    CREDIT_TITLE varchar(255),
    BANK_ID uuid
    constraint fk1x1iaf0dsp6s1tjhdihhedgdk
    references BANK
);

create table PUBLIC.PUBLIC.CREDIT_OFFER (
    CREDIT_OFFER_ID uuid not null
    constraint CREDIT_OFFER_pkey
    primary key,
    CREDIT_OFFER_AMOUNT numeric(19, 2),
    CREDIT_OFFER_FIRST_PAY numeric(19, 2),
    CREDIT_OFFER_NAMED varchar(255),
    CREDIT_OFFER_PERCENT_SUM numeric(19, 2),
    CREDIT_OFFER_PERIOD_IN_MONTHS integer,
    BANK_ID uuid
    constraint fkkij8lijqb84vmmkcnhssnpeji
    references BANK,
    CLIENT_ID uuid
    constraint fk18hoi8h4wktsstqg7c6bot11q
    references CLIENT,
    CREDIT_ID uuid
    constraint fk385fjsia5r7avrt8pk7t1hchk
    references CREDIT
);

create table PUBLIC.PUBLIC.PAYMENT_SCHEDULE (
    PAYMENT_SCHEDULE_ID uuid not null
    constraint PAYMENT_SCHEDULE_pkey
    primary key,
    PAYMENT_SCHEDULE_AMOUNT_OF_THE_BODY numeric(19, 2),
    PAYMENT_SCHEDULE_AMOUNT_OF_THE_PERCENT numeric(19, 2),
    PAYMENT_SCHEDULE_PAYMENT_AMOUNT numeric(19, 2),
    PAYMENT_SCHEDULE_PAYMENT_DATE date,
    PAYMENT_SCHEDULE_REMAINS numeric(19, 2),
    CREDIT_OFFER_ID uuid
    constraint fk67sgkjw07dmkvmy1f6invw33k
    references CREDIT_OFFER
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

INSERT INTO CLIENT (CLIENT_ID, CLIENT_FIO, CLIENT_PHONE_NUMBER, CLIENT_EMAIL, CLIENT_PASSPORT_NUMBER, BANK_ID)

values ('91a80fb6-1ca4-435c-8fb3-2215d145db41', 'Gruzdov Vladislav Grigorevich', '907-964-39-63', 'tomcat@random.com', '31-11-502830', '11a80fb6-1ca4-435c-8fb3-2215d145db41'),
       ('62a80fb6-1ca4-435c-8fb3-2215d145db41', 'Gruzdov Vladimir Grigorevich', '907-964-39-63', 'tomcat@random.com', '31-11-502830', '21a80fb6-1ca4-435c-8fb3-2215d145db41'),
       ('73a80fb6-1ca4-435c-8fb3-2215d145db41', 'Gruzdov Valeriy Grigorevich', '907-964-39-63', 'tomcat@random.com', '31-11-502830', '31a80fb6-1ca4-435c-8fb3-2215d145db41'),
       ('84a80fb6-1ca4-435c-8fb3-2215d145db41', 'Gruzdov Miroslav Grigorevich', '907-964-39-63', 'tomcat@random.com', '31-11-502830', '41a80fb6-1ca4-435c-8fb3-2215d145db41');

INSERT INTO CREDIT_OFFER (CREDIT_OFFER_ID, CREDIT_OFFER_NAMED, CREDIT_OFFER_AMOUNT, CREDIT_OFFER_PERIOD_IN_MONTHS, CREDIT_OFFER_FIRST_PAY, CREDIT_OFFER_PERCENT_SUM, BANK_ID, CREDIT_ID, CLIENT_ID)

values ('91a80fb6-1ca4-435c-8fb3-2216d145db41', 'Spring', 100000, 2, 10, 8, '11a80fb6-1ca4-435c-8fb3-2215d145db41', '51a80fb6-1ca4-435c-8fb3-2215d145db41', '91a80fb6-1ca4-435c-8fb3-2215d145db41'),
       ('62a80fb6-1ca4-435c-8fb3-2217d145db41', 'Joy', 150000, 2, 10, 30, '21a80fb6-1ca4-435c-8fb3-2215d145db41', '61a80fb6-1ca4-435c-8fb3-2215d145db41', '62a80fb6-1ca4-435c-8fb3-2215d145db41'),
       ('73a80fb6-1ca4-435c-8fb3-2218d145db41', 'Pull', 150800, 2, 10, 15, '31a80fb6-1ca4-435c-8fb3-2215d145db41', '71a80fb6-1ca4-435c-8fb3-2215d145db41', '73a80fb6-1ca4-435c-8fb3-2215d145db41'),
       ('84a80fb6-1ca4-435c-8fb3-2219d145db41', 'Optimal', 180000, 2, 10, 6, '41a80fb6-1ca4-435c-8fb3-2215d145db41', '81a80fb6-1ca4-435c-8fb3-2215d145db41', '84a80fb6-1ca4-435c-8fb3-2215d145db41');