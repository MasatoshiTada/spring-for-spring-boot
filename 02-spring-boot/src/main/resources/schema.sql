CREATE SEQUENCE hibernate_sequence START WITH 6 INCREMENT BY 1;

CREATE TABLE employee (
  id INTEGER PRIMARY KEY,
  first_name VARCHAR(40),
  last_name VARCHAR(40),
  joined_date DATE
);