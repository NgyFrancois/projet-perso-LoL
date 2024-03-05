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
    winner VARCHAR(100) NOT NULL
);

CREATE TABLE classement_popularite(
    champion VARCHAR(100) NOT NULL,
    apparitions INTEGER,
    pick FLOAT
);

CREATE TABLE classement_winrate(
    champion VARCHAR(100) NOT NULL,
    winrate FLOAT
);

\copy matchs(id,champion1_team1,champion2_team1,champion3_team1,champion4_team1,champion5_team1,champion1_team2,champion2_team2,champion3_team2,champion4_team2,champion5_team2,winner) FROM CSV/bdd.csv DELIMITER ',' CSV HEADER;

INSERT INTO classement_popularite (champion, apparitions, pick)
SELECT champion, COUNT(*) AS apparitions, ROUND((COUNT(*) * 100.0) / (SELECT COUNT(*) FROM matchs), 2) AS pick
FROM (
    SELECT champion1_team1 AS champion FROM matchs
    UNION ALL
    SELECT champion2_team1 FROM matchs
    UNION ALL
    SELECT champion3_team1 FROM matchs
    UNION ALL
    SELECT champion4_team1 FROM matchs
    UNION ALL
    SELECT champion5_team1 FROM matchs
    UNION ALL
    SELECT champion1_team2 FROM matchs
    UNION ALL
    SELECT champion2_team2 FROM matchs
    UNION ALL
    SELECT champion3_team2 FROM matchs
    UNION ALL
    SELECT champion4_team2 FROM matchs
    UNION ALL
    SELECT champion5_team2 FROM matchs
) AS champions
GROUP BY champion
ORDER BY apparitions DESC
LIMIT 1;


