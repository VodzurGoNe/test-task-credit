CREATE TABLE PUBLIC.PUBLIC.bank
(
    bank_id               uuid not null,

    bank_title varchar(255),

    PRIMARY KEY (bank_id)
);

CREATE TABLE PUBLIC.PUBLIC.client
(
    client_id             uuid not null
        constraint client_pkey
            primary key,
    client_email          varchar(205),
    client_fio            varchar(205),
    client_passportnumber varchar(205),
    client_phonenumber    varchar(205),
    bank_id               uuid
        constraint fkim95abd01ot21q2dn9mpxo7nc
            references bank
);

create table PUBLIC.PUBLIC.credit
(
    credit_id      uuid not null
        constraint credit_pkey
            primary key,
    credit_limit   numeric(19, 2)
        constraint credit_credit_limit_check,

    credit_percent numeric(19, 2)
        constraint credit_credit_percent_check,

    credit_title   varchar(255),
    bank_id        uuid
        constraint fk1x1iaf0dsp6s1tjhdihhedgdk
            references bank
);

create table PUBLIC.PUBLIC.creditoffer
(
    creditoffer_id               uuid not null
        constraint creditoffer_pkey
            primary key,
    creditoffer_amount           numeric(19, 2),


    creditoffer_firstpay         numeric(19, 2),


    creditoffer_named            varchar(255),
    creditoffer_percentsum       numeric(19, 2),
    creditoffer_period_in_months integer,


    bank_id                      uuid
        constraint fkkij8lijqb84vmmkcnhssnpeji
            references bank,
    client_id                    uuid
        constraint fk18hoi8h4wktsstqg7c6bot11q
            references client,
    credit_id                    uuid
        constraint fk385fjsia5r7avrt8pk7t1hchk
            references credit
);

create table PUBLIC.PUBLIC.paymentschedule
(
    paymentschedule_id                    uuid not null
        constraint paymentschedule_pkey
            primary key,
    paymentschedule_amount_of_the_body    numeric(19, 2),
    paymentschedule_amount_of_the_percent numeric(19, 2),
    paymentschedule_payment_amount        numeric(19, 2),
    paymentschedule_payment_date          date,
    paymentschedule_remains               numeric(19, 2),
    creditoffer_id                        uuid
        constraint fk67sgkjw07dmkvmy1f6invw33k
            references creditoffer
);

INSERT INTO bank
(bank_id, bank_title) values ('31a80fb6-1ca4-435c-8fb3-2215d145db41', 'OP');