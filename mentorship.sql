--  Creating Database
create database mentorship encoding='UTF8';

--  Creating in Role table

create table                   Role
(
  roleoid                            varchar(128)                                                not null,
  roleName                       varchar(128)                                                not null,
  description                    varchar(128),
  status                         varchar(32)                                                 not null,
  createdBy                      varchar(128)                                                not null       default 'System',
  createdOn                      timestamp                                                   not null       default current_timestamp,
  updatedBy                      varchar(128),
  updatedOn                      timestamp,
constraint                       pk_Role                                                     primary key    (roleoid)
);

--  Creating in Login table

create table                   Login(
oid                            varchar(128)                                                not null,
userId                         varchar(128)                                                not null,
password                       varchar(128),
role                           varchar(128),
status                         varchar(32)                                                 not null,
createdOn                      timestamp                                                         default current_timestamp,
updatedOn                      timestamp,
constraint                     pk_Login                                                   primary key    (oid)
);

--  Creating in UserRole table

create table                   UserRole
(
  loginOid                      varchar(128)                                                not null,
  roleOid                        varchar(128)                                                not null,
constraint                     fk_loginOid_UserRole                                     foreign key    (loginOid)
references     Login(oid),
constraint                     fk_roleOid_UserRole                                       foreign key    (roleOid)
references     role(roleOid)
);

--  Creating in Permission table

create table                   Permission
(
  permissionOid                 varchar(128)                                                not null,
  permissionName                varchar(128)                                                not null,
  url                           varchar(128)                                                 NULL,
  method                        varchar(128)                                                    NULL,
  createdOn                     timestamp                                                       NOT NULL                 DEFAULT CURRENT_TIMESTAMP,
 constraint                     pk_Permission                                               primary key    (permissionOid)
);

--  Creating in RolePermission table

create table                   RolePermission
(
  oid                            varchar(128)                                                not null,
  roleOid                        varchar(128)                                                not null,
  permissionOid                  varchar(128)                                                not null,
  constraint                     pk_RolePermission                                           primary key    (oid),
  constraint                     fk_roleOid_RolePermission                                   foreign key    (roleOid)
  references     role(roleOid),
  constraint                     fk_permissionOid_RolePermission                             foreign key    (permissionOid)
  references     permission(permissionOid)
);

--  Creating in UserProfile table

create table                   UserProfile
(
  oid                            varchar(128)                                                not null,
  loginOid                       varchar(128)                                                not null,
  name                           varchar(128),
  Address                        varchar(128),
  email                          varchar(128),
  phone			       varchar(128),
  createdBy                      varchar(128),
  createdOn                      timestamp                                                    default current_timestamp,
  updatedBy                      varchar(128),
  updatedOn                      timestamp,
  constraint                     pk_UserProfile                                               primary key (oid),
  constraint                     fk_loginOid_UserProfile                                      foreign key (loginOid)
  references     login(oid)
);

--  Creating in Assesment table

create table                   Assesment(
  oid                            varchar(128)                                                not null,
  title                          varchar(128)                                                not null,
  description                    varchar(128),
  mentorId                       varchar(128),
  deadline                       timestamp,
  createdBy                      varchar(128),
  createdOn                      timestamp                                                         default current_timestamp,
  updatedBy                      varchar(128),
  updatedOn                      timestamp,
  constraint                     pk_Assesment                                                   primary key    (oid),
  constraint                     fk_mentorId_Assesment                                      foreign key (mentorId)
  references     Login(oid)
);

--  Creating in Assignment table

create table                   Assignment(
  oid                            varchar(128)                                                not null,
  studentId                      varchar(128)                                                not null,
  assesmentId                    varchar(128)                                                not null,
  constraint                     pk_Assignment                                              primary key (oid),
  constraint                     fk_studentId_Assignment                                    foreign key  (studentId)
  references     Login(oid),
  constraint                     fk_assesmentId_Assignment                             foreign key (assesmentId)
  references     Assesment(oid)
);

--  Creating in AssesmentSubmission table

create table                   AssesmentSubmission(
  oid                            varchar(128)                                                not null,
  filePath                       varchar(128)                                                not null,
  assignmentId                   varchar(128)                                                not null,
  submissionDate                 timestamp                                                   not null,
  constraint                     pk_AssesmentSubmission                                      primary key (oid),
  constraint                     fk_assignmentId_AssesmentSubmission                                   foreign key (assignmentId)
  references                     Assignment(oid)
);

--  Creating in Grade table

create table                   Grade(
  oid                            varchar(128)                                                not null,
  marks                          numeric(4,2)                                                not null,
  mentorRemarks                  text                                                        not null,
  assessmentSubmissionId         varchar(128)                                                not null,
  createdBy                      varchar(128),
  createdOn                      timestamp                                                   default current_timestamp,
  updatedBy                      varchar(128),
  updatedOn                      timestamp,
  constraint                     pk_Grade                                                     primary key (oid),
  constraint                     fk_assessmentSubmissionId_Grade                          foreign key (assessmentSubmissionId)
  references                     AssesmentSubmission (oid)
);


select * from login;
select * from role;
select * from assesment;
select * from assesmentsubmission;
select * from assignment;
select * from rolepermission;
select * from permission;
select * from userprofile;
select * from userrole;

  

--  Inserting in role table

  INSERT INTO public."role"
("oid", rolename, description, status, createdby, createdon, updatedby, updatedon)
VALUES('12', 'Student', 'Student', 'Active', 'System', '2022-04-16 12:20:12.422', NULL, NULL);
INSERT INTO public."role"
("oid", rolename, description, status, createdby, createdon, updatedby, updatedon)
VALUES('11', 'Mentor', 'Mentor', 'Active', 'System', '2022-04-16 12:20:50.823', NULL, NULL);
INSERT INTO public."role"
("oid", rolename, description, status, createdby, createdon, updatedby, updatedon)
VALUES('10', 'Admin', 'Admin', 'Active', 'System', '2022-04-16 12:21:11.206', NULL, NULL);

  --  Inserting in Login table
  
  INSERT INTO public.login
("oid", userid, password, "role", status, createdon, updatedon)
VALUES('100', 'milon', '$2a$10$LPs8K90.lBwbsO3gLWIykO8NZ7HKGuEbT2ekWvHwYoT/UhBfAwAta', 'Student', 'Active', '2022-04-16 12:18:13.674', NULL);
INSERT INTO public.login
("oid", userid, password, "role", status, createdon, updatedon)
VALUES('99', 'rich', '$2a$10$LPs8K90.lBwbsO3gLWIykO8NZ7HKGuEbT2ekWvHwYoT/UhBfAwAta', 'Mentor', 'Active', '2022-04-16 12:18:13.000', NULL);
INSERT INTO public.login
("oid", userid, password, "role", status, createdon, updatedon)
VALUES('98', 'admin', '$2a$10$LPs8K90.lBwbsO3gLWIykO8NZ7HKGuEbT2ekWvHwYoT/UhBfAwAta', 'Admin', 'Active', '2022-04-16 12:19:28.643', NULL);

  
  INSERT INTO public.userprofile
("oid", loginoid, "name", address, email, phone, createdby, createdon, updatedby, updatedon)
VALUES('1000', '100', 'Istiyak Ahamed Milon', 'Dhaka', 'milon.istiyak@gmail.com', '01989198991', 'System', '2022-04-16 12:25:34.338', NULL, NULL);
INSERT INTO public.userprofile
("oid", loginoid, "name", address, email, phone, createdby, createdon, updatedby, updatedon)
VALUES('999', '99', 'Kayn Rich', 'New York', 'kayn.rich@gmail.com', '77894462', 'System', '2022-04-16 12:27:05.910', NULL, NULL);
INSERT INTO public.userprofile
("oid", loginoid, "name", address, email, phone, createdby, createdon, updatedby, updatedon)
VALUES('998', '98', 'Admin', 'Algorizin', 'contact@algorizin.com', '987456321', 'System', '2022-04-16 12:27:56.987', NULL, NULL);

  
  INSERT INTO public.userrole
(loginoid, roleoid)
VALUES('100', '12');
INSERT INTO public.userrole
(loginoid, roleoid)
VALUES('99', '11');
INSERT INTO public.userrole
(loginoid, roleoid)
VALUES('98', '10');

  
--  Inserting in Assesment table

  INSERT INTO public.assesment
("oid", title, "description", mentorid, deadline, createdby)
VALUES('1111', 'JavaScript Basic Problem solving', '1. Problem 1, 2. Problem 2, 3. Problem 3', '99', '2022-05-16 12:27:56.987', 'Admin');
  
 
 --  Inserting in Permission table 

  INSERT INTO public.permission
("permissionoid", permissionname, "url", method)
VALUES('authenticate_user', 'USER.AUTHENTICATE', '/authenticate', 'POST');

INSERT INTO public.permission
("permissionoid", permissionname, "url", method)
VALUES('register_user', 'USER.REGISTER', '/register', 'POST');

--  Inserting in rolepermission table

INSERT INTO public.rolepermission
("oid", roleoid, "permissionoid")
VALUES('1', '11', 'authenticate_user');

INSERT INTO public.rolepermission
("oid", roleoid, "permissionoid")
VALUES('2', '10', 'authenticate_user');

INSERT INTO public.rolepermission
("oid", roleoid, "permissionoid")
VALUES('3', '12', 'authenticate_user');





  


