use purchase_historic;

create table users_table(
users varchar(12) not null,
primary key(users)
);

create table user_pwd_table(
users varchar(15) not null,
user_pwd varchar(15) not null,
primary key(users), 
foreign key(users) references users_table(users)
);

create table expense_control(
id integer(5) not null,
aplicacao varchar(30) not null,
valor numeric(6,2) not null,
mes varchar(10) not null,
ano integer(4) not null,
observacao varchar(20),
primary key(id)
)auto_increment = 0;