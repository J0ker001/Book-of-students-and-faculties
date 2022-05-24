--liquibase formatted sql

-- changeset aLodochnikov:1
CREATE INDEX student_name_index ON student (name);
CREATE INDEX faculty_nameAndColor_index ON faculty (name,color);
