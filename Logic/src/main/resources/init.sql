START TRANSACTION;

CREATE TABLE IF NOT EXISTS Student(
    id VARCHAR PRIMARY KEY,
    name VARCHAR NOT NULL
);
CREATE TABLE IF NOT EXISTS PhoneContact(
    studentId VARCHAR NOT NULL REFERENCES Student(id),
    country VARCHAR NOT NULL,
    countryCode VARCHAR NOT NULL,
    number VARCHAR NOT NULL,
    type INTEGER NOT NULL,
    PRIMARY KEY(countryCode, number)
);

CREATE TABLE IF NOT EXISTS CourseEvent(
    id VARCHAR PRIMARY KEY,
    studentId VARCHAR NOT NULL REFERENCES Student(id),
    type VARCHAR NOT NULL,
    passed INTEGER DEFAULT '-1'
);

COMMIT;