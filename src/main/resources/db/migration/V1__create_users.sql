CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

/*---------------------------------------------------------------------------------------------*/

create sequence if not exists user_states_seq;
create table if not exists user_states(id integer not null default nextval('user_states_seq'),
                                       name varchar(100) not null,
                                       unique (id),
                                       primary key (id));
alter sequence user_states_seq owned by user_states.id;

/*---------------------------------------------------------------------------------------------*/

create sequence if not exists user_posts_seq;
create table if not exists user_posts(id integer not null default nextval('user_posts_seq'),
                                      name varchar(100) not null,
                                      unique (id),
                                      primary key (id));
alter sequence user_posts_seq owned by user_posts.id;

/*---------------------------------------------------------------------------------------------*/

create sequence if not exists users_seq;
create table if not exists users(id integer not null default nextval('users_seq'),
                                 first_name varchar(100) not null,
                                 last_name varchar(100) not null,
                                 surname varchar(100) not null,
                                 phone varchar(15) not null,
                                 email varchar(25)  not null unique,
                                 password varchar(120) not null,
                                 enabled boolean default true,
                                 state integer,
                                 post integer,
                                 unique (id),
                                 primary key (id),
                                 constraint fk_user_states foreign key (state)
                                     references user_states(id),
                                 constraint fk_user_posts foreign key (post)
                                     references user_posts(id));
alter sequence users_seq owned by users.id;
