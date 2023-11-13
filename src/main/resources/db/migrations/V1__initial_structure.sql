use bank;

create table person
(
    id              int primary key not null auto_increment,
    first_name      char(50)        not null,
    middle_name     char(50),
    last_name       char(50)        not null,
    dob             date            not null,
    citizenID       char(50) unique not null,
    passport_number char(50) unique not null,
    login           char(30),
    password        char(30)
);

create table role
(
    id        int not null auto_increment,
    role      char(30),
    person_id int not null,

    primary key (id),
    foreign key (person_id) references person (id)
);


create table country
(
    id           int primary key auto_increment not null,
    country_name char(100)                      not null unique
);

create table region
(
    id          int auto_increment not null,
    country_id  int,
    region_name char(100)          not null unique,

    primary key (id),
    foreign key (country_id) references country (id)
);

create table settlement_type
(
    id              int primary key auto_increment not null,
    settlement_type char(20)                       not null unique,
    short_name      char(10)                       not null unique
);

create table settlement
(
    id                 int auto_increment not null,
    region_id          int,
    settlement_type_id int,
    settlement_name    char(50),

    primary key (id),
    foreign key (region_id) references region (id),
    foreign key (settlement_type_id) references settlement_type (id)
);

create table street_type
(
    id          int primary key auto_increment not null,
    street_type char(20)                       not null unique,
    short_name  char(10)                       not null unique
);

create table address
(
    id               int auto_increment not null,
    settlement_id    int,
    street_type_id   int,
    street_name      char(50),
    house_number     char(20),
    apartment_number char(10),
    person_id        int,

    primary key (id),
    foreign key (settlement_id) references settlement (id),
    foreign key (street_type_id) references street_type (id),
    foreign key (person_id) references person (id)
);

create table contact
(
    id        int auto_increment not null,
    person_id int,

    primary key (id),
    foreign key (person_id) references person (id)
);

create table phone
(
    id           int primary key not null auto_increment,
    phone_number char(15) unique not null,
    contact_id   int             not null,

    foreign key (contact_id) references contact (id)
);

create table email
(
    id         int primary key not null auto_increment,
    email      char(30) unique not null,
    contact_id int             not null,

    foreign key (contact_id) references contact (id)
);


create table employee
(
    id        int not null auto_increment,
    person_id int not null,

    primary key (id),
    foreign key (person_id) references person (id)
);

create table position
(
    id            int             not null auto_increment,
    position_name char(50) unique not null,
    salary        decimal(20, 2),

    primary key (id)
);

create table wage_rate
(
    id          int  not null auto_increment,
    position_id int  not null,
    start_date  date not null,
    finish_date date,
    rate        decimal(20, 2),
    employee_id int,

    primary key (id),
    foreign key (position_id) references bank.position (id),
    foreign key (employee_id) references bank.position (id)
);



create table customer
(
    id               int  not null auto_increment,
    person_id        int  not null,
    agreement_number char(20) unique,
    agreement_date   date not null,
    closure_date     date,

    primary key (id),
    foreign key (person_id) references person (id)
);

create table currency
(
    id                    int      not null auto_increment,
    currency_name         char(20) not null,
    currency_abbreviation char(3)  not null,
    currency_code         char(3)  not null unique,
    currency_rate         decimal(20, 2),

    primary key (id)
);

create table bank_account
(
    id              int      not null auto_increment,
    customer_id     int      not null,
    account_number  char(30) not null unique,
    currency_id     int      not null,
    openning_date   date     not null,
    closure_date    date,
    current_balance decimal(20, 2)    default 0.00,

    primary key (id),
    foreign key (customer_id) references customer (id),
    foreign key (currency_id) references currency (id)
);

create table payment_order
(
    id              int                                      not null auto_increment,
    date_and_time   timestamp      default current_timestamp not null,
    amount          decimal(20, 2) default 0.00              not null,
    from_account_id int                                      not null,
    to_account_id   int                                      not null,
    primary key (id),
    foreign key (from_account_id) references bank_account (id),
    foreign key (to_account_id) references bank_account (id)
);


