--liquibase formatted sql
--changeset viktor:5
insert into roles (name)
values ('ROLE_ADMIN');

--changeset viktor:6
insert into roles (name)
values ('ROLE_USER');

--changeset viktor:7
insert into roles (name)
values ('ROLE_CUSTOMER');

--changeset viktor:8
insert into users (user_name, password)
values ('admin', 'admin')

--changeset viktor:9
insert into user_roles (user_id,role_id)
values (1,1)
