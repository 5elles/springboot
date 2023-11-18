use bank;

drop table role;

alter table person
    drop column login,
    drop column password;


create table user
(
    id                      bigint primary key not null auto_increment,
    account_non_expired     bit(1)             not null,
    account_non_locked      bit(1)             not null,
    credentials_non_expired bit(1)             not null,
    enabled                 bit(1)             not null,
    password                varchar(255) default null,
    username                varchar(255) default null,
    person_id               int                not null,
    foreign key (person_id) references person (id)
);

create table user_roles
(
    id      bigint not null primary key auto_increment,
    user_id bigint not null,
    roles   varchar(255) default null,
    foreign key (user_id) references user (id)
);