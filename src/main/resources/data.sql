
INSERT INTO addresses (`tag`,`street`,`zip`,`city`,`country`)
VALUES
    ('CITY_HALL','432 Fleet Walk',686,'Osage','Kansas'),
    ('COMPANY','923 Prospect Street',112,'Watrous','Connecticut'),
    ('COMPANY','504 Harbor Lane',434,'Alafaya','Guam'),
    ('CITY_HALL','366 Cobek Court',666,'Gallina','New Hampshire'),
    ('COMPANY','162 Cass Place',657,'Winston','Virgin Islands'),
    ('CITY_HALL','539 Strickland Avenue',182,'Abrams','Puerto Rico'),
    ('COMPANY','212 Village Court',677,'Hilltop','North Dakota');


INSERT INTO companies (`name`, `addresses_id`)
VALUES
    ('exercitation dolor ea', (SELECT ID FROM  addresses WHERE tag = 'COMPANY' LIMIT 1)),
    ('adipisicing qui duis', (SELECT ID FROM  addresses WHERE tag = 'COMPANY' LIMIT 1)),
    ('adipisicing qui duis', (SELECT ID FROM  addresses WHERE tag = 'COMPANY' LIMIT 1));

INSERT INTO cityhalls (`name`,`addresses_id`)
VALUES
    ('irure aute non', (SELECT ID FROM  addresses WHERE tag = 'CITY_HALL' LIMIT 1)),
    ('amet et veniam', (SELECT ID FROM  addresses WHERE tag = 'CITY_HALL' LIMIT 1));
