--liquibase formatted sql
--changeset viktor:4
insert into roles (name)
values ('ROLE_ADMIN');

insert into roles (name)
values ('ROLE_USER');

insert into roles (name)
values ('ROLE_CUSTOMER');

insert into users (user_name, password)
values ('admin', 'admin');

insert into user_roles (user_id,role_id)
values (1,1);
