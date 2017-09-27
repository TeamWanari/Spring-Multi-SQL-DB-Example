CREATE TABLE sys_user (
  id    BIGSERIAL PRIMARY KEY    NOT NULL,
  email VARCHAR(256)             NOT NULL
);

INSERT INTO sys_user VALUES (1, 'example@wanari.com');