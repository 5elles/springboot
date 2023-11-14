use bank;
alter table wage_rate
    drop foreign key wage_rate_ibfk_2;

alter table wage_rate
    add foreign key (employee_id) references employee(id);