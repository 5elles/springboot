use bank;
alter table user
    modify account_non_expired bit(1) not null default 1,
    modify account_non_locked bit(1) not null default 1,
    modify credentials_non_expired bit(1) not null default 1,
    modify enabled bit(1) not null default 1,
    modify person_id int not null unique;
