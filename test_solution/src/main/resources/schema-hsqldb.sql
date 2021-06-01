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