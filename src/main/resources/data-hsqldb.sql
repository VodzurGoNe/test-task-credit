INSERT INTO BANK (ID, BANK_TITLE)

values ('11a80fb6-1ca4-435c-8fb3-2215d145db41', 'PRIME'),
       ('21a80fb6-1ca4-435c-8fb3-2215d145db41', 'Second'),
       ('31a80fb6-1ca4-435c-8fb3-2215d145db41', 'JBC'),
       ('41a80fb6-1ca4-435c-8fb3-2215d145db41', 'Gong');

INSERT INTO CREDIT (ID, CREDIT_TITLE, CREDIT_LIMIT, CREDIT_PERCENT, BANK_ID)

values ('51a10fb6-1ca7-435c-8fb3-2215d145db41', 'Mortgage', 15000000, 8,'11a80fb6-1ca4-435c-8fb3-2215d145db41'),
       ('61a20fb6-1ca8-435c-8fb3-2215d135db41', 'Сonsumer', 700000, 30, '11a80fb6-1ca4-435c-8fb3-2215d145db41'),
       ('71a30fb6-1ca3-435c-8fb3-2215d245db41', 'Auto credit', 10000000, 15, '11a80fb6-1ca4-435c-8fb3-2215d145db41'),
       ('81a40fb6-1ca1-435c-8fb3-2215d745db41', '"installment plan"', 150000, 6, '11a80fb6-1ca4-435c-8fb3-2215d145db41'),
       ('51a80fb6-1ca9-425c-8fb3-2315d145db41', 'Mortgage', 15000000, 10,'21a80fb6-1ca4-435c-8fb3-2215d145db41'),
       ('61a80fb6-1ca4-415c-8fb3-2615d145db41', 'Сonsumer', 700000, 32, '21a80fb6-1ca4-435c-8fb3-2215d145db41'),
       ('71a80fb6-1ca4-495c-8fb3-2715d145db41', 'Auto credit', 10000000, 25, '21a80fb6-1ca4-435c-8fb3-2215d145db41'),
       ('81a80fb6-1ca4-435c-8fb3-2815d145db41', '"installment plan"', 150000, 7, '21a80fb6-1ca4-435c-8fb3-2215d145db41'),
       ('51a80fb6-1ca4-435c-8fb3-2215d195db41', 'Mortgage', 15000000, 9,'31a80fb6-1ca4-435c-8fb3-2215d145db41'),
       ('61a80fb6-1ca4-435c-8fb3-2215d155db41', 'Сonsumer', 700000, 27, '31a80fb6-1ca4-435c-8fb3-2215d145db41'),
       ('71a80fb6-1ca4-435c-8fb3-2215d115db41', 'Auto credit', 10000000, 21, '31a80fb6-1ca4-435c-8fb3-2215d145db41'),
       ('81a80fb6-1ca4-435c-8fb3-2215d125db41', '"installment plan"', 150000, 9, '31a80fb6-1ca4-435c-8fb3-2215d145db41'),
       ('51a20fb6-1ca4-435c-8fb3-2215d145db41', 'Mortgage', 15000000, 11,'41a80fb6-1ca4-435c-8fb3-2215d145db41'),
       ('61a30fb6-1ca4-435c-8fb3-2215d145db41', 'Сonsumer', 700000, 28, '41a80fb6-1ca4-435c-8fb3-2215d145db41'),
       ('71a40fb6-1ca4-435c-8fb3-2215d145db41', 'Auto credit', 10000000, 16, '41a80fb6-1ca4-435c-8fb3-2215d145db41'),
       ('81a50fb6-1ca4-435c-8fb3-2215d145db41', '"installment plan"', 150000, 8, '41a80fb6-1ca4-435c-8fb3-2215d145db41');

INSERT INTO CLIENT (ID, CLIENT_FIO, CLIENT_PHONE_NUMBER, CLIENT_EMAIL, CLIENT_PASSPORT_NUMBER, BANK_ID)

values ('91a80fb6-1ca4-435c-8fb3-3215d145db41', 'Gruzdov Vladislav Grigorevich', '907-964-39-63', 'tomcat@random.com', '31-11-502830', '11a80fb6-1ca4-435c-8fb3-2215d145db41'),
       ('91a80fb6-1ca4-435c-8fb3-1215d145db41', 'Gruzdov Alexander Grigorevich', '907-964-39-63', 'tomcat@random.com', '31-11-502830', '11a80fb6-1ca4-435c-8fb3-2215d145db41'),
       ('91a80fb6-1ca4-435c-8fb3-2215d145db41', 'Gruzdov Alexei Grigorevich', '907-964-39-63', 'tomcat@random.com', '31-11-502830', '11a80fb6-1ca4-435c-8fb3-2215d145db41'),
       ('91a80fb6-1ca4-435c-8fb3-4214d145db41', 'Gruzdov Kirill Grigorevich', '907-964-39-63', 'tomcat@random.com', '31-11-502830', '11a80fb6-1ca4-435c-8fb3-2215d145db41'),
       ('91a80fb6-1ca4-435c-8fb3-8213d145db41', 'Gruzdov Artur Grigorevich', '907-964-39-63', 'tomcat@random.com', '31-11-502830', '11a80fb6-1ca4-435c-8fb3-2215d145db41'),
       ('62a80fb6-1ca4-435c-8fb3-2115d145db41', 'Gruzdov Vladimir Grigorevich', '907-964-39-63', 'tomcat@random.com', '31-11-502830', '21a80fb6-1ca4-435c-8fb3-2215d145db41'),
       ('62a80fb6-1ca4-435c-8fb3-2815d145db41', 'Gruzdov Nikolaj Grigorevich', '907-964-39-63', 'tomcat@random.com', '31-11-502830', '21a80fb6-1ca4-435c-8fb3-2215d145db41'),
       ('62a80fb6-1ca4-435c-8fb3-2715d145db41', 'Gruzdov Oleg Grigorevich', '907-964-39-63', 'tomcat@random.com', '31-11-502830', '21a80fb6-1ca4-435c-8fb3-2215d145db41'),
       ('62a80fb6-1ca4-435c-8fb3-2315d145db41', 'Gruzdov Andrej Grigorevich', '907-964-39-63', 'tomcat@random.com', '31-11-502830', '21a80fb6-1ca4-435c-8fb3-2215d145db41'),
       ('62a80fb6-1ca4-435c-8fb3-1215d145db41', 'Gruzdov Sergej Grigorevich', '907-964-39-63', 'tomcat@random.com', '31-11-502830', '21a80fb6-1ca4-435c-8fb3-2215d145db41'),
       ('73a90fb6-1ca4-435c-8fb3-9215d145db41', 'Gruzdov Anton Grigorevich', '907-964-39-63', 'tomcat@random.com', '31-11-502830', '31a80fb6-1ca4-435c-8fb3-2215d145db41'),
       ('73a10fb6-1ca4-435c-8fb3-4215d145db41', 'Gruzdov Maxim Grigorevich', '907-964-39-63', 'tomcat@random.com', '31-11-502830', '31a80fb6-1ca4-435c-8fb3-2215d145db41'),
       ('73a30fb6-1ca4-435c-8fb3-3215d145db41', 'Gruzdov Anatolij Grigorevich', '907-964-39-63', 'tomcat@random.com', '31-11-502830', '31a80fb6-1ca4-435c-8fb3-2215d145db41'),
       ('73a20fb6-1ca4-435c-8fb3-1215d145db41', 'Gruzdov Ruslan Grigorevich', '907-964-39-63', 'tomcat@random.com', '31-11-502830', '31a80fb6-1ca4-435c-8fb3-2215d145db41'),
       ('73a50fb6-1ca4-435c-6fb3-9215d145db41', 'Gruzdov Egor Grigorevich', '907-964-39-63', 'tomcat@random.com', '31-11-502830', '31a80fb6-1ca4-435c-8fb3-2215d145db41'),
       ('73a60fb6-1ca4-435c-8fb3-8215d145db41', 'Gruzdov Elesej Grigorevich', '907-964-39-63', 'tomcat@random.com', '31-11-502830', '31a80fb6-1ca4-435c-8fb3-2215d145db41'),
       ('84a80fb6-2ca4-435c-8fb3-2215d145db41', 'Gruzdov Evgenij Vasilievich', '907-964-39-63', 'tomcat@random.com', '31-11-502830', '41a80fb6-1ca4-435c-8fb3-2215d145db41'),
       ('84a10fb6-2ca4-435c-8fb3-2215d145db41', 'Gruzdov Dmitrij YUrievich', '907-964-39-63', 'tomcat@random.com', '31-11-502830', '41a80fb6-1ca4-435c-8fb3-2215d145db41'),
       ('84a20fb6-2ca4-435c-8fb3-2215d145db41', 'Gruzdov Denis Vasilievich', '907-964-39-63', 'tomcat@random.com', '31-11-502830', '41a80fb6-1ca4-435c-8fb3-2215d145db41'),
       ('84a80fb6-2ca4-435c-8fb3-5215d145db41', 'Gruzdov Mihail Grigorevich', '907-964-39-63', 'tomcat@random.com', '31-11-502830', '41a80fb6-1ca4-435c-8fb3-2215d145db41'),
       ('84a80fb6-3ca4-435c-8fb3-7215d145db41', 'Gruzdov YArolsav Grigorevich', '907-964-39-63', 'tomcat@random.com', '31-11-502830', '41a80fb6-1ca4-435c-8fb3-2215d145db41'),
       ('84a80fb6-2ca4-435c-8fb3-8215d145db41', 'Gruzdov Innokentij Grigorevich', '907-964-39-63', 'tomcat@random.com', '31-11-502830', '41a80fb6-1ca4-435c-8fb3-2215d145db41');

INSERT INTO CREDIT_OFFER (ID, CREDIT_OFFER_NAMED, CREDIT_OFFER_AMOUNT, CREDIT_OFFER_PERIOD_IN_MONTHS, CREDIT_OFFER_FIRST_PAY, CREDIT_OFFER_PERCENT_SUM, BANK_ID, CREDIT_ID, CLIENT_ID)

values ('91a80fb6-1ca4-435c-8fb3-2216d145db41', 'Spring', 100000, 2, 10, 8, '11a80fb6-1ca4-435c-8fb3-2215d145db41', '51a10fb6-1ca7-435c-8fb3-2215d145db41', '91a80fb6-1ca4-435c-8fb3-3215d145db41'),
       ('62a80fb6-1ca4-435c-8fb3-2217d145db41', 'Joy', 150000, 2, 10, 30, '21a80fb6-1ca4-435c-8fb3-2215d145db41', '81a80fb6-1ca4-435c-8fb3-2815d145db41', '62a80fb6-1ca4-435c-8fb3-2815d145db41'),
       ('73a80fb6-1ca4-435c-8fb3-2218d145db41', 'Pull', 150800, 2, 10, 15, '31a80fb6-1ca4-435c-8fb3-2215d145db41', '71a80fb6-1ca4-435c-8fb3-2215d115db41', '73a60fb6-1ca4-435c-8fb3-8215d145db41'),
       ('84a80fb6-1ca4-435c-8fb3-2219d145db41', 'Optimal', 180000, 2, 10, 6, '41a80fb6-1ca4-435c-8fb3-2215d145db41', '81a50fb6-1ca4-435c-8fb3-2215d145db41', '84a80fb6-2ca4-435c-8fb3-8215d145db41');


INSERT INTO PAYMENT_SCHEDULE (ID, PAYMENT_SCHEDULE_AMOUNT_OF_THE_BODY, PAYMENT_SCHEDULE_AMOUNT_OF_THE_PERCENT, PAYMENT_SCHEDULE_PAYMENT_AMOUNT, PAYMENT_SCHEDULE_PAYMENT_DATE, PAYMENT_SCHEDULE_REMAINS, CREDIT_OFFER_ID)

values ('91a70fb6-1ca4-435c-8fb3-2216d145db41', 10000, 5000, 15000, DATE '2021-09-01', 15000, '91a80fb6-1ca4-435c-8fb3-2216d145db41'),
       ('91a70fb6-1ca4-435c-8fb3-2216d115db42', 10000, 5000, 15000, DATE '2021-09-02', 0, '91a80fb6-1ca4-435c-8fb3-2216d145db41'),
       ('62a70fb6-1ca4-435c-8fb3-2217d195db41', 10000, 5000, 15000, DATE '2021-09-01', 15000, '62a80fb6-1ca4-435c-8fb3-2217d145db41'),
       ('62a70fb6-1ca4-435c-8fb3-2217d175db44', 10000, 5000, 15000, DATE '2021-09-02', 0, '62a80fb6-1ca4-435c-8fb3-2217d145db41'),
       ('73a70fb6-1ca4-435c-8fb3-2218d145db41', 10000, 5000, 15000, DATE '2021-09-01', 15000, '73a80fb6-1ca4-435c-8fb3-2218d145db41'),
       ('73a70fb6-1ca4-435c-8fb3-2218d155db49', 10000, 5000, 15000, DATE '2021-09-02', 0, '73a80fb6-1ca4-435c-8fb3-2218d145db41'),
       ('84a30fb6-1ca4-435c-8fb3-2219d135db41', 10000, 5000, 15000, DATE '2021-09-01', 15000, '84a80fb6-1ca4-435c-8fb3-2219d145db41'),
       ('84a30fb6-1ca4-435c-8fb3-2219d145db44', 10000, 5000, 15000, DATE '2021-09-02', 0, '84a80fb6-1ca4-435c-8fb3-2219d145db41');

