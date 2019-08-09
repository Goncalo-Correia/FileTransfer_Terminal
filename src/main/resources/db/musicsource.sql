DELETE FROM USER ;
INSERT INTO user(ID, VERSION, USERNAME, PASSWORD) VALUES
  (1, 0, 'Goncalo', 'ginasio1'),
  (2, 0, 'gg', 'gg');


DELETE FROM TRACK ;
INSERT INTO track(TRACKNAME, USER_ID) VALUES
  ('Bro', 1),
  ('Agualva101', 2);


