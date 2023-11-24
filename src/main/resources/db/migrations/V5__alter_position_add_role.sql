use bank;
alter table position
add column role varchar(50) not null default 'ROLE_USER';