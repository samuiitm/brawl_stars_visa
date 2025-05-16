DROP DATABASE IF EXISTS db_brawl_visa;

CREATE DATABASE db_brawl_visa;

USE db_brawl_visa;

CREATE TABLE rarities (
    id_rarity       INT UNSIGNED,
    nom             VARCHAR(30),
    CONSTRAINT pk_rarities PRIMARY KEY (id_rarity)
);

CREATE TABLE classes (
    id_class        INT UNSIGNED,
    nom             VARCHAR(30),
    CONSTRAINT pk_classes PRIMARY KEY (id_class)
);

CREATE TABLE brawlers (
    id_brawler      INT UNSIGNED,
    nom             VARCHAR(30),
    descripcio      VARCHAR(700),
    id_rarity       INT UNSIGNED,
    id_class        INT UNSIGNED,
    CONSTRAINT pk_brawlers PRIMARY KEY (id_brawler),
    CONSTRAINT fk_brawlers_rarities FOREIGN KEY (id_rarity)
        REFERENCES rarities(id_rarity),
    CONSTRAINT fk_brawlers_classes FOREIGN KEY (id_class)
        REFERENCES classes(id_class)
);

CREATE TABLE starpowers (
    id_starpower    INT UNSIGNED,
    nom             VARCHAR(30),
    descripcio      VARCHAR(700),
    id_brawler      INT UNSIGNED,
    CONSTRAINT pk_starpowers PRIMARY KEY (id_starpower),
    CONSTRAINT fk_starpowers_brawlers FOREIGN KEY (id_brawler)
        REFERENCES brawlers(id_brawler)
);

CREATE TABLE gadgets (
    id_gadget       INT UNSIGNED,
    nom             VARCHAR(30),
    descripcio      VARCHAR(700),
    id_brawler      INT UNSIGNED,
    CONSTRAINT pk_gadgets PRIMARY KEY (id_gadget),
    CONSTRAINT fk_gadgets_brawlers FOREIGN KEY (id_brawler)
        REFERENCES brawlers(id_brawler)
);