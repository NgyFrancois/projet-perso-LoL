DROP TABLE IF EXISTS matchs cascade;
DROP TABLE IF EXISTS classement_popularite cascade;
DROP TABLE IF EXISTS classement_winrate cascade;

CREATE TABLE matchs(
    id INTEGER PRIMARY KEY NOT NULL,
    champion1_team1 VARCHAR(100) NOT NULL,
    champion2_team1 VARCHAR(100) NOT NULL,
    champion3_team1 VARCHAR(100) NOT NULL,
    champion4_team1 VARCHAR(100) NOT NULL,
    champion5_team1 VARCHAR(100) NOT NULL,
    champion1_team2 VARCHAR(100) NOT NULL,
    champion2_team2 VARCHAR(100) NOT NULL,
    champion3_team2 VARCHAR(100) NOT NULL,
    champion4_team2 VARCHAR(100) NOT NULL,
    champion5_team2 VARCHAR(100) NOT NULL,
    winner VARCHAR(100) NOT NULL,
);

\copy matchs(id,champion1_team1,champion2_team1,champion3_team1,champion4_team1,champion4_team1,champion5_team1,champion1_team2,champion2_team2,champion3_team2,champion4_team2,champion5_team2,winner) FROM bdd.csv DELIMITER ',' CSV HEADER;