SELECT * FROM book WHERE id_book = 0;
SELECT * FROM book WHERE id_book = 2;
SELECT * FROM book WHERE id_book = 1;
SELECT * FROM category;
SELECT * FROM user_info;
INSERT INTO user_info (id_user, username, password, role)
VALUES (1,'luis','zelda1234','ADMIN');
INSERT INTO user_info (id_user, username, password, role)
VALUES (2,'user','user1234','USER');

UPDATE user_info 
SET password = '$2a$12$F9rJHyWo3q/iHBSdYCdT0erBwy78E6Rb7LO3RP6/dleliYKbr3AMi'
WHERE id_user = 1;

UPDATE user_info 
SET password = '$2a$12$5b3DtdLR8u9qNUZER68zC.ezFv30CI2YnVRlT/1grhUpa.1G2VWH.'
WHERE id_user = 2;


