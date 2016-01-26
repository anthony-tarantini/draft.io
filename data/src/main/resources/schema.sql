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

DROP TABLE IF EXISTS draftio_data.created_packs;

CREATE TABLE draftio_data.created_packs (
  id        SERIAL PRIMARY KEY,
  set       INT REFERENCES draftio_data.set (id),
  rares     INT,
  uncommons INT,
  commons   INT,
  lands     INT
);