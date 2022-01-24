--liquibase formatted sql
--changeset viktor:1

create table if not exists roles
(
	id bigserial not null
		constraint roles_pkey
			primary key,
	name varchar(255)
);

alter table roles owner to postgres;

create unique index if not exists roles_name_uindex
	on roles (name);

--changeset viktor:2

create table if not exists users
(
	id bigserial not null
		constraint users_pkey
			primary key,
	password varchar(255),
	user_name varchar(255)
);

alter table users owner to postgres;

--changeset viktor:3

create table if not exists user_roles
(
	user_id bigint not null
		constraint fkhfh9dx7w3ubf1co1vdev94g3f
			references users,
	role_id bigint not null
		constraint fkh8ciramu9cc9q3qcqiv4ue8a6
			references roles
);

alter table user_roles owner to postgres;

