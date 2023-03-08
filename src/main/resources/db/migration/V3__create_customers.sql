create sequence if not exists customers_seq;
create table if not exists customers(id integer not null default nextval('customers_seq'),
                                     first_name varchar(100) not null,
                                     last_name varchar(100) not null,
                                     surname varchar(100) not null,
                                     phone varchar(15) not null,
                                     email varchar(25) not null,
                                     address varchar(200) not null,
                                     unique (id),
                                     primary key (id));
alter sequence customers_seq owned by customers.id;
