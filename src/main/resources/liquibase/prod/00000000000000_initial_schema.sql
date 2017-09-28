CREATE TABLE detonation_code (
  id    BIGSERIAL PRIMARY KEY    NOT NULL,
  code  VARCHAR(256)             NOT NULL,
  usage VARCHAR(256)             NOT NULL
);

INSERT INTO detonation_code (id, code, usage) VALUES
  (1, '12345', 'Totally useless'),
  (2, 'e2591529-755b-486c-9850-af0ef5e55ef4', 'To detonate printers');