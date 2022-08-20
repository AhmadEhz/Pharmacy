create database pharmacy;
create type user_access as enum (
    'PATIENT', 'ADMIN'
    );
create type prescription_status as enum (
    'PENDING', 'CONFIRMED', 'REJECTED'
    );

create table if not exists users
(
    id       bigserial primary key,
    name     varchar,
    username varchar,
    password varchar,
    access   user_access
);

create table if not exists prescription
(
    id          bigserial primary key,
    status      prescription_status,
    total_price int default 0,
    patient_id  bigint references users (id)
);

create table if not exists drug
(
    id              bigserial primary key,
    name            varchar,
    price           int     default 0,
    does_exist      boolean default false,
    prescription_id bigint references prescription (id)
);