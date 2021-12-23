Create Database if not exists hibernatetest;
use hibernatetest;
/*多對多表*/ 
create table server (
        id integer not null auto_increment,
        address varchar(255),
        primary key (id)
    );

create table client (
        id integer not null auto_increment,
        name varchar(255),
        primary key (id)
);

create table client_server (
        client_id integer not null,
        server_id integer not null,
        primary key (client_id, server_id)
);