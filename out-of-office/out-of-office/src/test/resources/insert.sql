insert into role(code, display_name, name)
values('ADMIN', 'Admin', 'admin');
insert into role(code, display_name, name)
values('EMPLOYEE', 'Employee', 'employe');

insert into department(id,code,display_name,emp_allowed_num,name) 
values(1,'SALES','Sales',5,'sales');
insert into department(code,display_name,emp_allowed_num,name) 
values('DEPARTMENT','Department',7,'department');
insert into department(code,display_name,emp_allowed_num,name)
values('HR','Human Resources',4,'human resources');
insert into department(code,display_name,emp_allowed_num,name) 
values('MARKETING','Marketing',3,'marketing');
insert into department(code,display_name,emp_allowed_num,name) 
values('PRODUCTION','Production',2,'production');

insert into employee(id,allowance,email,firstname_last_name,hire_date,jmbg,job_role,phone_number,remaining_days,department_id,role_id)
values (1,'21','email1@gmail.com','First employee', '2001-01-01 00:00:01','1234567891011', 'First_role', '062000111',21,1,2);
insert into employee(id,allowance,email,firstname_last_name,hire_date,jmbg,job_role,phone_number,remaining_days,department_id,role_id)
values (2,'21','email2@gmail.com','Second employee', '2002-01-01 00:00:01','1234567891012', 'Second_role', '062000222',21,1,2);
insert into  employee(id,allowance,email,firstname_last_name,hire_date,jmbg,job_role,phone_number,remaining_days,department_id,role_id)
values (3,'21','email3@gmail.com','Third employee', '2003-01-01 00:00:01','1234567891013', 'Third_role', '062000333',21,1,2);

insert into auth (id,password, username,employee_id)
values(1,'password1','username1',1);
insert into auth (id,password, username,employee_id)
values(2,'password2','username2',2);
insert into auth (id,password, username,employee_id)
values(3,'password3','username3',3);

