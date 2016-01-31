DROP SCHEMA IF EXISTS card_data CASCADE;

CREATE SCHEMA card_data;

DROP TABLE IF EXISTS card_data.pack_composition;

CREATE TABLE card_data.pack_composition (
  id        SERIAL PRIMARY KEY,
  rares     INT,
  uncommons INT,
  commons   INT,
  lands     INT
);

DROP TABLE IF EXISTS card_data.set;

CREATE TABLE card_data.set (
  id             SERIAL PRIMARY KEY,
  name           VARCHAR,
  code           CHAR(3) NOT NULL UNIQUE,
  composition_id INT REFERENCES card_data.pack_composition (id)
);

DROP TABLE IF EXISTS card_data.created_pack;

CREATE TABLE card_data.created_pack (
  id     SERIAL PRIMARY KEY,
  set_id INT REFERENCES card_data.set (id)
);

DROP TABLE IF EXISTS card_data.card;

CREATE TABLE card_data.card (
  id     SERIAL PRIMARY KEY,
  name   VARCHAR,
  rarity VARCHAR,
  set_code CHAR(3) REFERENCES card_data.set (code)
)