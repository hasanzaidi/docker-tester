CREATE USER dockeruser WITH PASSWORD 'docker';

GRANT ALL PRIVILEGES ON DATABASE postgres TO dockeruser;

CREATE TABLE account(
 user_id serial PRIMARY KEY,
 username VARCHAR (50) UNIQUE NOT NULL,
 password VARCHAR (50) NOT NULL,
 email VARCHAR (355) UNIQUE NOT NULL
);

GRANT ALL PRIVILEGES ON TABLE account TO dockeruser;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO dockeruser;

-- Some test data
INSERT INTO account (username, password, email)
    VALUES('user1', 'abc123', 'user1@gmail.com');

INSERT INTO account (username, password, email)
    VALUES('user2', 'letmein123', 'user2@yahoo.com');