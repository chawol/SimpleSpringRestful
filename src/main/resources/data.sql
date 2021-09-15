DROP TABLE IF EXISTS EMPLOYEES;
DROP TABLE IF EXISTS DEPARTMENT;

CREATE TABLE EMPLOYEES
(
    EMPID         int(10) AUTO_INCREMENT PRIMARY KEY,
    NAME          VARCHAR(250) NOT NULL,
    DEPID         VARCHAR(20)  NOT NULL,
    GENDER        VARCHAR(10)  NOT NULL,
    PHONENUMBER   VARCHAR(20)  NOT NULL,
    ADDRESS       VARCHAR(250) NOT NULL,
    AGE           INT(3)       NOT NULL,
    ESTABLISHTIME datetime     NOT NULL,
    LASTUPDATE    datetime     NOT NULL
);
CREATE TABLE DEPARTMENT
(
    DEPID   VARCHAR(20) PRIMARY KEY,
    DEPNAME varchar(250)
);

INSERT INTO EMPLOYEES (NAME, DEPID, GENDER, PHONENUMBER, ADDRESS, AGE, ESTABLISHTIME, LASTUPDATE)
VALUES ('First', '100', 'male', '0977011111', 'First Address1', 19, '2021-09-13', '2021-09-13'),
       ('Second', '110', 'male', '0977011112', 'Second Address2', 30, '2021-09-13', '2021-09-13'),

       ('Third', '120', 'male', '0977011113', 'Third Address', 20, '2021-09-13', '2021-09-13'),
       ('Third1', '120', 'male', '0977011122', 'Third1 Address', 20, '2021-09-13', '2021-09-13'),
       ('Third2', '120', 'male', '0977011114', 'Third2 Address', 50, '2021-09-13', '2021-09-13'),
       ('Third3', '120', 'male', '0977011114', 'Third2 Address', 50, '2021-09-13', '2021-09-13');



INSERT INTO DEPARTMENT (DEPID, DEPNAME)
values ('100', '測試部門100'),
       ('110', '測試部門110'),
       ('120', '測試部門120');