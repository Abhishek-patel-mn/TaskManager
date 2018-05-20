drop table users;
drop table roles;
drop table config_param_param_values;
drop table param_value;
drop table cp_value;
drop table config_param;

drop sequence user_seq;
drop sequence role_seq;
drop sequence cp_seq;
drop sequence cp_value_seq;
drop sequence role_seq;


select * from users;
select * from roles;
select * from config_param;
select * from cp_value;
select * from config_param_param_values;


CREATE TABLE users 
(	
    user_id NUMBER(*,0) NOT NULL, 
    user_name VARCHAR2(20 BYTE) NOT NULL, 
	password VARCHAR2(20 BYTE), 
	email VARCHAR2(20 BYTE), 
	phone_number NUMBER(*,0), 
	created_date DATE,
    PRIMARY KEY (user_id)
);
   
CREATE TABLE roles
(	
    role_id NUMBER NOT NULL, 
	role_name VARCHAR2(20 BYTE) NOT NULL, 
	created_date DATE,
    PRIMARY KEY (role_id),
	REFERENCES users (user_id)
);

CREATE SEQUENCE  role_seq  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 7 NOCACHE  NOORDER  NOCYCLE ;
CREATE SEQUENCE  user_seq  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 7 NOCACHE  NOORDER  NOCYCLE ;

commit;



