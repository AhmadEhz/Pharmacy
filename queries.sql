create table admin
(
    id        serial primary key,
    name      varchar,
    user_name varchar unique,
    password  varchar
);

create table patient
(
    id        serial primary key,
    name      varchar,
    user_name varchar unique,
    password  varchar
);

create type prescription_status as enum (
    'PENDING' , 'CONFIRMED' , 'REJECTED'
    );
create table prescription
(
    id     serial primary key,
    name   varchar,
    status prescription_status
);

create table item
(
    id        serial primary key,
    name      varchar,
    price     int,
    does_exit boolean
);

create table prescription_item
(
    prescription_id    int references prescription (id),
    item_id            int references item (id),
    current_price      int,
    current_does_exist boolean,
    constraint prescription_item_pk primary key (prescription_id, item_id)
);
