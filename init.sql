CREATE USER dockeruser WITH PASSWORD 'docker';

GRANT ALL PRIVILEGES ON DATABASE postgres TO dockeruser;

CREATE TABLE account(
 email VARCHAR (355) PRIMARY KEY NOT NULL,
 password VARCHAR (50) NOT NULL
);

GRANT ALL PRIVILEGES ON TABLE account TO dockeruser;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO dockeruser;

-- Some test data
INSERT INTO account (email, password)
    VALUES('user1@gmail.com', 'abc123');

INSERT INTO account (email, password)
    VALUES('user2@yahoo.com', 'letmein123');
