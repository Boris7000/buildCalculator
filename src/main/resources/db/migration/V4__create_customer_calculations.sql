create sequence if not exists calculation_states_seq;
create table if not exists calculation_states(id integer not null default nextval('calculation_states_seq'),
                                              name varchar not null,
                                              unique (id),
                                              primary key (id));
alter sequence calculation_states_seq owned by calculation_states.id;


/*---------------------------------------------------------------------------------------------*/

create sequence if not exists calculations_seq;
create table if not exists calculations(id integer not null default nextval('calculations_seq'),
                                        manager integer not null,
                                        customer integer not null,
                                        date bigint not null,
                                        address varchar(200) not null,
                                        state integer,
                                        /*result integer,*/
                                        unique (id),
                                        primary key (id),
                                        constraint fk_customer_calculations foreign key (customer)
                                            references customers(id) on delete cascade,
                                        constraint fk_calculation_states foreign key (state)
                                            references calculation_states(id));
alter sequence calculations_seq owned by calculations.id;


/*---------------------------------------------------------------------------------------------*/

create table if not exists calculation_results(calculation_id integer not null,
                                               unique (calculation_id),
                                               primary key (calculation_id),
                                               constraint fk_calculation_results foreign key (calculation_id)
                                                   references calculations(id) on delete cascade);

/*---------------------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION calculation_insert_trigger_fnc()
    RETURNS trigger AS
$$
    BEGIN
        INSERT INTO "calculation_results" ("calculation_id") VALUES(new.id);
        UPDATE "calculations" set result=new.id WHERE id=new.id;
        RETURN NEW;
    END
$$
LANGUAGE 'plpgsql';


CREATE TRIGGER calculation_insert_trigger
    AFTER INSERT ON calculations
    FOR ROW
        EXECUTE PROCEDURE calculation_insert_trigger_fnc();
*/
