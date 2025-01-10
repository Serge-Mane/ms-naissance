use  `db-naissances`;

drop table if exists profiles;
drop table if exists companies;
drop table if exists cityHalls;
drop table if exists addresses;


create table addresses(
id int auto_increment primary key,
zip varchar(30),
street varchar(255),
city varchar(255),
country varchar(100),
description text,
dateCreation datetime default current_timestamp
);

create table companies(
id int auto_increment primary key,
name varchar(255),
description text,
dateCreation datetime default current_timestamp,
addresses_id int,
constraint addresses_companies_fk foreign key(addresses_id) references addresses(id)
);

create table cityHalls(
id int auto_increment primary key,
name varchar(255),
description text,
dateCreation datetime default current_timestamp,
addresses_id int,
constraint addresses_cityHalls_fk foreign key(addresses_id) references addresses(id)
);


create table profiles(
id int auto_increment primary key,
civility varchar(100),
first_name varchar(100),
last_name varchar(100),
password varchar(255),
email varchar(100)unique,
phone varchar(100) unique,
dateCreation datetime default current_timestamp,
addresses_id int,
constraint addresses_profiles_fk foreign key(addresses_id) references addresses(id)
);