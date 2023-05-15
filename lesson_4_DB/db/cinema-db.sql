use start_for_interview;

drop table if exists tickets;
drop table if exists sessions;
drop table if exists films;
drop table if exists durations;
drop table if exists prices;
drop table if exists time_intervals;
drop table if exists days;



CREATE TABLE `durations` (
  `id` int NOT NULL AUTO_INCREMENT,
  `duration` time DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='длительность фильма';

CREATE TABLE `prices` (
  `id` int NOT NULL AUTO_INCREMENT,
  `price` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `price_UNIQUE` (`price`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='цена билета';

CREATE TABLE `films` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `duration_id` int NOT NULL,
  `base_price_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `titel_UNIQUE` (`title`),
  KEY `duration_id_idx` (`duration_id`),
  KEY `base_price_idx` (`base_price_id`),
  CONSTRAINT `base_price_id` FOREIGN KEY (`base_price_id`) REFERENCES `prices` (`id`),
  CONSTRAINT `duration_id` FOREIGN KEY (`duration_id`) REFERENCES `durations` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `time_intervals` (
  `id` int NOT NULL AUTO_INCREMENT,
  `time_begin` time NOT NULL,
  `time_end` time NOT NULL,
  `time_rate` int NOT NULL COMMENT 'коэффициент на изменение цены в этот период времени в процентах',
  PRIMARY KEY (`id`),
  UNIQUE KEY `time_begin_UNIQUE` (`time_begin`),
  UNIQUE KEY `time_end_UNIQUE` (`time_end`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='интервалы времени, по которым устанавливается цена билета';

CREATE TABLE `days` (
  `id` int NOT NULL AUTO_INCREMENT,
  `day_name` varchar(45) NOT NULL,
  `day_rate` int NOT NULL COMMENT 'коэффициент на изменение цены в этот день',
  PRIMARY KEY (`id`),
  UNIQUE KEY `day_UNIQUE` (`day_name`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='дни недели';

CREATE TABLE `sessions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `film_id` int NOT NULL,
  `day_id` int NOT NULL,
  `start_time` time NOT NULL,
  PRIMARY KEY (`id`),
  KEY `film_id_idx` (`film_id`),
  KEY `day_id_idx` (`day_id`),
  CONSTRAINT `day_id` FOREIGN KEY (`day_id`) REFERENCES `days` (`id`),
  CONSTRAINT `film_id` FOREIGN KEY (`film_id`) REFERENCES `films` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='информация о сеансах';

CREATE TABLE `tickets` (
  `id` int NOT NULL AUTO_INCREMENT,
  `session_id` int NOT NULL,
  `sale_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `session_id_idx` (`session_id`),
  CONSTRAINT `session_id` FOREIGN KEY (`session_id`) REFERENCES `sessions` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;




insert into durations(duration) values('01:00:00'), ('01:30:00'), ('02:00:00');

insert into prices(price) values(300),(400),(500),(600),(700),(800),(900),(1000);

-- заносим названия фильмов и длительность
insert into films(title, duration_id, base_price_id) values
	('Film_1', 1, 2),
    ('Film_2', 2, 3),
    ('Film_3', 3, 1),
    ('Film_4', 1, 2),
    ('Film_5', 2, 4),
    ('Film_6', 3, 2),
    ('Film_7', 1, 5);
    
insert into time_intervals(time_begin, time_end, time_rate) values
	('09:00:00', '15:00:00', -10),
    ('15:00:00', '18:00:00', 0),
    ('18:00:00', '21:00:00', +20),
    ('21:00:00', '23:59:59', +10);

insert into days(day_name, day_rate) values
	('Monday', -20),
	('Tuesday', 0),
    ('Wednesday', 0),
    ('Thursday', 0),
    ('Friday', 10),
    ('Saturday', 20),
    ('Sunday', 30);
    
insert into sessions(film_id, day_id, start_time) values
	(1, 1, '09:00:00'), -- 10+24+13=47
    (2, 1, '10:00:00'), -- 21++16=37
  --  (3, 1, '11:30:00'),
    (5, 1, '13:00:00'), -- 34
    (1, 1, '14:00:00'),
    (7, 1, '15:30:00'), -- 11+30=41
    (1, 1, '16:00:00'),
    (4, 1, '17:30:00'), -- 22+24=46
    (2, 1, '18:00:00'),
    (3, 1, '19:30:00'), -- 40
    (4, 1, '20:30:00'),
    (7, 1, '22:00:00');
    
insert into tickets(session_id) values
	(1),(1),(1),(1),(1),(1),(1),(1),(1),(1), 	-- 10
    (2),(2),(2),(2),(2),(2),(2),(2),(2),(2),(2),(2),(2),(2),(2),(2),(2),(2),(2),(2),(2), -- 21
    (3),(3),(3),(3),(3),(3),(3),(3),(3),(3),(3),(3),(3),(3),(3),(3),(3),(3),(3),(3),(3),(3),(3),(3),(3),(3),(3),(3),(3),(3),(3),(3),(3),(3), -- 34
    (4),(4),(4),(4),(4),(4),(4),(4),(4),(4),(4),(4),(4),(4),(4),(4),(4),(4),(4),(4),(4),(4),(4),(4), -- 24
    (5),(5),(5),(5),(5),(5),(5),(5),(5),(5),(5), -- 11
    (6),(6),(6),(6),(6),(6),(6),(6),(6),(6),(6),(6),(6), -- 13
    (7),(7),(7),(7),(7),(7),(7),(7),(7),(7),(7),(7),(7),(7),(7),(7),(7),(7),(7),(7),(7),(7), -- 22
    (8),(8),(8),(8),(8),(8),(8),(8),(8),(8),(8),(8),(8),(8),(8),(8), -- 16
    (9),(9),(9),(9),(9),(9),(9),(9),(9),(9),(9),(9),(9),(9),(9),(9),(9),(9),(9),(9),(9),(9),(9),(9),(9),(9),(9),(9),(9),(9),(9),(9),(9),(9),(9),(9),(9),(9),(9),(9), -- 40
    (10),(10),(10),(10),(10),(10),(10),(10),(10),(10),(10),(10),(10),(10),(10),(10),(10),(10),(10),(10),(10),(10),(10),(10), -- 24
    (11),(11),(11),(11),(11),(11),(11),(11),(11),(11),(11),(11),(11),(11),(11),(11),(11),(11),(11),(11),(11),(11),(11),(11),(11),(11),(11),(11),(11),(11) -- 30
   -- (12),(12),(12),(12),(12),(12),(12),(12),(12),(12),(12),(12),(12),(12),(12),(12),(12),(12),(12),(12),(12),(12),(12),(12),(12),(12),(12),(12),(12),(12),(12),(12),(12),(12)
    ;
