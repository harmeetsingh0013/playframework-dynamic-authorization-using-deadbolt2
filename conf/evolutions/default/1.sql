
# --- !Ups

CREATE TABLE PUBLIC.USER_PERMISSIONS(
    ID VARCHAR(70) NOT NULL,
    PERMISSION_NAME VARCHAR(50)
);

ALTER TABLE PUBLIC.USER_PERMISSIONS ADD CONSTRAINT PUBLIC.CONSTRAINT_E PRIMARY KEY(ID);

INSERT INTO PUBLIC.USER_PERMISSIONS(ID, PERMISSION_NAME) VALUES
('1', 'CHANNEL'),
('2', 'VIDEO');


CREATE TABLE PUBLIC.SECURITY_ROLES(
    ID VARCHAR(70) NOT NULL,
    ROLE_NAME VARCHAR(50)
);

ALTER TABLE PUBLIC.SECURITY_ROLES ADD CONSTRAINT PUBLIC.CONSTRAINT_9 PRIMARY KEY(ID);

INSERT INTO PUBLIC.SECURITY_ROLES(ID, ROLE_NAME) VALUES
('1', 'ADMIN'),
('2', 'USER');



CREATE TABLE PUBLIC.USER_SECURITY_ROLES(
    USER_ID VARCHAR(70) NOT NULL,
    SECURITY_ID VARCHAR(70)
);

ALTER TABLE PUBLIC.USER_SECURITY_ROLES ADD CONSTRAINT PUBLIC.CONSTRAINT_F PRIMARY KEY(USER_ID);

INSERT INTO PUBLIC.USER_SECURITY_ROLES(USER_ID, SECURITY_ID) VALUES
('553e3c982c00002d00c4fd12', '1');



CREATE TABLE PUBLIC.USER_USER_PERMISSION(
    USER_ID VARCHAR(70) NOT NULL,
    PERMISSION_ID VARCHAR(70)
);

ALTER TABLE PUBLIC.USER_USER_PERMISSION ADD CONSTRAINT PUBLIC.CONSTRAINT_2 PRIMARY KEY(USER_ID);

INSERT INTO PUBLIC.USER_USER_PERMISSION(USER_ID, PERMISSION_ID) VALUES
('553e3c982c00002d00c4fd12', '1');



CREATE TABLE PUBLIC.GROUP_ONE(
    USER_ID VARCHAR(70) NOT NULL
);

ALTER TABLE PUBLIC.GROUP_ONE ADD CONSTRAINT PUBLIC.CONSTRAINT_4 PRIMARY KEY(USER_ID);

INSERT INTO PUBLIC.GROUP_ONE(USER_ID) VALUES
('553e3c982c00002d00c4fd12');



CREATE TABLE PUBLIC.GROUP_TWO(
    USER_ID VARCHAR(70) NOT NULL
);

ALTER TABLE PUBLIC.GROUP_TWO ADD CONSTRAINT PUBLIC.CONSTRAINT_4D PRIMARY KEY(USER_ID);



ALTER TABLE PUBLIC.USER_SECURITY_ROLES ADD CONSTRAINT PUBLIC.CONSTRAINT_FB FOREIGN KEY(SECURITY_ID) REFERENCES PUBLIC.SECURITY_ROLES(ID) NOCHECK;

ALTER TABLE PUBLIC.USER_USER_PERMISSION ADD CONSTRAINT PUBLIC.CONSTRAINT_2D FOREIGN KEY(PERMISSION_ID) REFERENCES PUBLIC.USER_PERMISSIONS(ID) NOCHECK;


# --- !Downs

DROP TABLE if EXISTS USER_PERMISSIONS;

DROP TABLE if EXISTS SECURITY_ROLES;

DROP TABLE if EXISTS USER_SECURITY_ROLES;

DROP TABLE if EXISTS USER_USER_PERMISSION;

DROP TABLE if EXISTS GROUP_ONE;

DROP TABLE if EXISTS GROUP_TWO;
