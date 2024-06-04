--liquibase formatted sql

--changeset r.pavel:2024-05-05
create table orders
(
    id                 BIGINT primary key not null,
    customer_id        bigint             not null,
    car_service_id     bigint             not null,
    car_id             bigint             not null,
    reserved_window_id bigint             not null,
    status             varchar,
    description        text
);

create table order_audit
(
    id          bigint primary key not null,
    status      varchar            not null,
    user_action varchar            not null,
    user_id     bigint             not null,
    description text,
    order_id    bigint             not null
);

create table order_detail
(
    id        bigint primary key not null,
    detail_id bigint             not null,
    price     NUMERIC(19, 4)     not null,
    order_id  bigint             not null
);

create table order_employee
(
    id          bigint primary key not null,
    employee_id bigint             not null,
    order_id    bigint             not null
);

create table order_work
(
    id       bigint primary key not null,
    work_id  bigint             not null,
    price    NUMERIC(19, 4),
    order_id BIGINT             not null
)