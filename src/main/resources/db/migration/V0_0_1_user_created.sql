CREATE TABLE users(
                      id char(12) not null primary key,
                      user_id VARCHAR(191),
                      enabled boolean,
                      username varchar(255) ,
                      firstname varchar(255),
                      lastname varchar(255) ,
                      max_request int ,
                      window_time_ms int,
                      event varchar(191),
                    created_at date
);
