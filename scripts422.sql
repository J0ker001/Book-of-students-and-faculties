CREATE TABLE cars (id INTEGER PRIMARY KEY , name TEXT, model TEXT, price INTEGER);
CREATE TABLE people ( id INTEGER, name  TEXT, age INTEGER, drivingLicense BOOLEAN, cars_id INTEGER REFERENCES cars (id) );
