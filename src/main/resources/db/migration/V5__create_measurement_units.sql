create sequence if not exists measurement_units_seq;
create table if not exists measurement_units(id integer not null default nextval('measurement_units_seq'),
                                             name varchar not null,
                                             unique (id),
                                             primary key (id));
alter sequence measurement_units_seq owned by measurement_units.id;
