create table if not exists tokens(user_id integer not null,
                                  value varchar(255) not null,
                                  killed bool,
                                  unique (user_id),
                                  unique (value),
                                  primary key(user_id),
                                  constraint fk_user_tokens foreign key (user_id)
                                      references users(id) on delete cascade);
