DROP SCHEMA IF EXISTS draftio_data CASCADE;

CREATE SCHEMA draftio_data;

DROP TABLE IF EXISTS draftio_data.pack_composition;

CREATE TABLE draftio_data.pack_composition (
  id        SERIAL PRIMARY KEY,
  rares     INT,
  uncommons INT,
  commons   INT,
  lands     INT
);

DROP TABLE IF EXISTS draftio_data.set;

CREATE TABLE draftio_data.set (
  id             SERIAL PRIMARY KEY,
  name           VARCHAR,
  code           CHAR(3),
  composition_id INT REFERENCES draftio_data.pack_composition (id)
);

DROP TABLE IF EXISTS draftio_data.created_pack;

CREATE TABLE draftio_data.created_pack (
  id     SERIAL PRIMARY KEY,
  set_id INT REFERENCES draftio_data.set (id)
);

DROP TABLE IF EXISTS draftio_data.card;

CREATE TABLE draftio_data.card (
  id     SERIAL PRIMARY KEY,
  name   VARCHAR,
  rarity VARCHAR
)