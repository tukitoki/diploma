--liquibase formatted sql

--changeset tukitoki:202405191857_customers

create sequence customer_seq;

create table customer
(
    id      BIGINT primary key not null
        default nextval('customer_seq'),
    user_id BIGINT             not null
);

create unique index on customer (user_id);