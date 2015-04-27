create database kartotek;

use kartotek;

drop table if exists operatoer;

create table operatoer
(
   id int not null primary key auto_increment,
   navn varchar(20) NOT NULL,
   ini varchar(3) NOT NULL,
   cpr varchar(11) NOT NULL,
   pass varchar(8) NOT NULL
);       

# start data
insert into operatoerer (navn, ini, cpr, pass) values 
	('Hans Jensen', 'HJ', '111111-1111', 'opera1'),
	('Ulla Jacobsen', 'UJ', '222222-2222', 'opera2'),
	('Peter Hansen', 'PH', '333333-3333', 'opera3');
	
	
select * from operator;

# queries to be used in program

select * from operator limit 1,1;

