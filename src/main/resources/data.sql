INSERT INTO users (name) VALUES ('CPU');
INSERT INTO users (name) VALUES ('ほんだ');
INSERT INTO users (name) VALUES ('いがき');

INSERT INTO matches (user1, user2, user1Hand, user2Hand, isActive) VALUES (2, 1, 'gu', 'choki', 'FALSE');
INSERT INTO matches (user1, user2, user1Hand, user2Hand, isActive) VALUES (2, 1, 'gu', 'gu', 'FALSE');
INSERT INTO matches (user1, user2, user1Hand, user2Hand, isActive) VALUES (2, 1, 'gu', 'pa', 'FALSE');

INSERT INTO matchinfo(user1, user2, user1Hand, isActive) VALUES (1, 2, 'gu', 'FALSE');
INSERT INTO matchinfo(user1, user2, user1Hand, isActive) VALUES (2, 3, 'choki', 'FALSE');
