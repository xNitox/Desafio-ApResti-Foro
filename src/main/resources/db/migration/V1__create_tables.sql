CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE courses (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    category VARCHAR(50) NOT NULL
);

CREATE TABLE topics (
    id SERIAL PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    message VARCHAR(300) NOT NULL,
    creation_date TIMESTAMP NOT NULL,
    status VARCHAR(20) NOT NULL,
    author_id BIGINT NOT NULL,
    course_id BIGINT NOT NULL,
    FOREIGN KEY (author_id) REFERENCES users(id),
    FOREIGN KEY (course_id) REFERENCES courses(id)
);

CREATE TABLE answers (
    id SERIAL PRIMARY KEY,
    message VARCHAR(300) NOT NULL,
    creation_date TIMESTAMP NOT NULL,
    topic_id BIGINT NOT NULL,
    author_id BIGINT NOT NULL,
    FOREIGN KEY (topic_id) REFERENCES topics(id),
    FOREIGN KEY (author_id) REFERENCES users(id)
);
