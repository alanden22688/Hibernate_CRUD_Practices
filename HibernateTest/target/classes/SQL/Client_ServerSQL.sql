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
        primary key (client_id, server_id),
        constraint fk_client_id foreign key(client_id) references client(id),
		constraint fk_server_id foreign key(server_id) references server(id)
);

/*個人測試區
select * from client;
select * from server;
select * from client_server;

select * from (client as `c` inner join client_server as `cs` on c.id = cs.client_id)inner join server as s on cs.server_id = s.id;

alter table client auto_increment =1;
alter table server auto_increment =1;
*/