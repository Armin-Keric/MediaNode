-- Adminer 5.4.2 PostgreSQL 17.8 dump

SET session_replication_role = 'replica';

DROP TABLE IF EXISTS "book_details";
CREATE TABLE "public"."book_details" (
    "id" integer NOT NULL,
    "pagecount" integer,
    "publisher" character varying(255),
    "writer" character varying(255),
    "languages" character varying(255),
    CONSTRAINT "book_details_pkey" PRIMARY KEY ("id")
)
WITH (oids = false);


DROP TABLE IF EXISTS "game_details";
CREATE TABLE "public"."game_details" (
    "id" integer NOT NULL,
    "playtime" integer,
    "platform" character varying(50),
    "publisher" character varying(100),
    "multiplayer" boolean,
    CONSTRAINT "game_details_pkey" PRIMARY KEY ("id")
)
WITH (oids = false);

INSERT INTO "game_details" ("id", "playtime", "platform", "publisher", "multiplayer") VALUES
(1,	118800,	'PS4, PC',	'Square Enix',	'0'),
(2,	90000,	'PC, PS5',	'CD Projekt Red',	'0'),
(4,	288000,	'PC, PS3',	'07th Expansion',	'0'),
(7,	180000,	'PC, Mobile',	'Re-Logic',	'1'),
(9,	36000,	'PC, PS4',	'Gearbox',	'1'),
(10,	43200,	'PS1, PC',	'Konami',	'0'),
(11,	46800,	'PS2, PC',	'Konami',	'0'),
(12,	57600,	'PS2, PC',	'Konami',	'0'),
(13,	64800,	'PS3',	'Konami',	'0'),
(14,	64800,	'PSP, PS3',	'Konami',	'1'),
(15,	7200,	'PC, PS4',	'Konami',	'0'),
(16,	165600,	'PC, PS4',	'Konami',	'1'),
(17,	25200,	'PS3, Xbox 360',	'Capcom',	'0'),
(18,	144000,	'PC, PS4',	'Sony Interactive',	'1'),
(19,	162000,	'PS5',	'Sony Interactive',	'0'),
(20,	108000,	'PC, PS5',	'Kepler Interactive',	'0'),
(21,	154800,	'PC, PS3',	'Bandai Namco',	'1'),
(22,	180000,	'PC, PS3',	'Bandai Namco',	'1'),
(23,	115200,	'PC, PS4',	'Bandai Namco',	'1'),
(24,	108000,	'PS3, PS5',	'Sony Interactive',	'1'),
(25,	126000,	'PS4',	'Sony Interactive',	'1'),
(26,	104400,	'PC, PS4',	'Activision',	'0'),
(27,	201600,	'PC, PS5',	'Bandai Namco',	'1'),
(28,	54000,	'GC, PC, PS4',	'Capcom',	'0'),
(30,	57600,	'PC, PS5, Nintendo Switch 2, Xbox Series X',	'Capcom',	NULL);

DROP TABLE IF EXISTS "genres";
DROP SEQUENCE IF EXISTS "public".genres_genre_id_seq;
CREATE SEQUENCE "public".genres_genre_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1;

CREATE TABLE "public"."genres" (
    "genre_id" integer DEFAULT nextval('genres_genre_id_seq') NOT NULL,
    "type" character varying(50),
    CONSTRAINT "genres_pkey" PRIMARY KEY ("genre_id")
)
WITH (oids = false);

INSERT INTO "genres" ("genre_id", "type") VALUES
(1,	'Fantasy'),
(2,	'Sci-Fi'),
(3,	'Action'),
(4,	'Romance'),
(5,	'Adventure'),
(6,	'Drama'),
(7,	'Psychological'),
(8,	'Mystery'),
(9,	'Supernatural'),
(10,	'Comedy'),
(11,	'Horror'),
(12,	'Thriller'),
(13,	'RPG'),
(14,	'FPS'),
(15,	'Platformer'),
(16,	'Strategy'),
(17,	'Simulation'),
(18,	'Sports'),
(19,	'Soulslike'),
(20,	'Indie'),
(21,	'Pop'),
(22,	'Rock'),
(23,	'Hip-Hop'),
(24,	'Rap'),
(25,	'Electronic'),
(26,	'EDM'),
(27,	'Jazz'),
(28,	'Classical'),
(29,	'Metal'),
(30,	'Lo-Fi'),
(31,	'Non-Fiction'),
(32,	'Biography'),
(33,	'Crime'),
(34,	'Historical Fiction'),
(35,	'Cyberpunk'),
(36,	'Slice of Life'),
(37,	'Survival'),
(38,	'Documentary');

DROP TABLE IF EXISTS "media";
DROP SEQUENCE IF EXISTS "public".media_id_seq;
CREATE SEQUENCE "public".media_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1;

CREATE TABLE "public"."media" (
    "id" integer DEFAULT nextval('media_id_seq') NOT NULL,
    "title" character varying(100),
    "release_date" date,
    "type" character varying(50),
    "description" character varying(5000),
    "img_url" character varying(255),
    CONSTRAINT "media_pkey" PRIMARY KEY ("id")
)
WITH (oids = false);

INSERT INTO "media" ("id", "title", "release_date", "type", "description", "img_url") VALUES
(2,	'Cyberpunk 2077',	'2020-12-10',	'Game',	'Cyberpunk 2077 is an open-world action-adventure game set in Night City, a sprawling metropolis driven by power, glamour, and body modification. Players assume the role of V, a mercenary outlaw in pursuit of a unique implant that holds the key to immortality. The game allows extensive customization of cyberware, skills, and playstyle. Choices made throughout the journey influence both the narrative and the world.',	'https://images.igdb.com/igdb/image/upload/t_cover_big_2x/coaih8.jpg'),
(7,	'Terraria',	'2011-05-16',	'Game',	'Dig, Fight, Explore, Build: The very world is at your fingertips as you fight for survival, fortune, and glory. Will you delve deep into cavernous expanses in search of treasure and raw materials with which to craft ever-evolving gear, machinery, and aesthetics? Perhaps you will choose instead to seek out ever-greater foes to test your mettle in combat? Maybe you will decide to construct your own city to house the host of mysterious allies you may encounter along your travels? In the World of Terraria, the choice is yours!',	'https://images.igdb.com/igdb/image/upload/t_cover_big_2x/coaamg.jpg'),
(8,	'Owarimonogatari',	'2015-10-03',	'Anime',	'Koyomi wakes up to see Mayoi Hachikuji before him, the girl who supposedly had gone to the afterlife. She tells Koyomi that they are currently in Avichi, the lowest levels of hell. Koyomi is dubious of how Mayoi knew about the exact timing of his death and which hell he would drop into, when Mayoi tells him that she was there to pick him up by the request of a certain individual.',	'https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx21745-VrhhJjZNdBXV.png'),
(10,	'Metal Gear Solid',	'1998-09-03',	'Game',	'Solid Snake, a retired soldier, infiltrates a nuclear weapons disposal facility to neutralize the threat of FOXHOUND, a renegade special forces unit.',	'https://images.igdb.com/igdb/image/upload/t_cover_big_2x/cobpak.jpg'),
(11,	'Metal Gear Solid 2: Sons of Liberty',	'2001-11-13',	'Game',	'Raiden, a rookie agent, must infiltrate the Big Shell to rescue the US President and stop the Sons of Liberty. A meta-narrative masterpiece about information control.',	'https://images.igdb.com/igdb/image/upload/t_cover_big_2x/co5e1c.jpg'),
(12,	'Metal Gear Solid 3: Snake Eater',	'2004-11-17',	'Game',	'An origin story set in the 1960s. Naked Snake must navigate the Soviet jungle to take down his former mentor, The Boss, and prevent nuclear war.',	'https://images.igdb.com/igdb/image/upload/t_cover_big_2x/co5ei5.jpg'),
(13,	'Metal Gear Solid 4: Guns of the Patriots',	'2008-06-12',	'Game',	'Old Snakes final mission in a world dominated by nanomachines and private military companies. He must stop Liquid Ocelot one last time.',	'https://images.igdb.com/igdb/image/upload/t_cover_big_2x/co5eju.jpg'),
(14,	'Metal Gear Solid: Peace Walker',	'2010-04-29',	'Game',	'Big Boss builds his own mercenary army, Militaires Sans Frontières, in 1970s Costa Rica while investigating a mysterious nuclear threat.',	'https://images.igdb.com/igdb/image/upload/t_cover_big_2x/coawb5.jpg'),
(15,	'Metal Gear Solid V: Ground Zeroes',	'2014-03-18',	'Game',	'The prologue to The Phantom Pain. Big Boss infiltrates Camp Omega to rescue his allies Paz and Chico in a rain-soaked tactical mission.',	'https://images.igdb.com/igdb/image/upload/t_cover_big_2x/co1v88.jpg'),
(16,	'Metal Gear Solid V: The Phantom Pain',	'2015-09-01',	'Game',	'Venom Snake awakens from a coma to build Diamond Dogs and hunt down the mysterious Cipher in a massive open-world tactical sandbox.',	'https://images.igdb.com/igdb/image/upload/t_cover_big_2x/co1v85.jpg'),
(17,	'Asuras Wrath',	'2012-02-21',	'Game',	'Betrayed by his fellow deities and stripped of all his powers, Asura must channel his inner rage to take vengeance on those who wronged him.',	'https://images.igdb.com/igdb/image/upload/t_cover_big_2x/co4961.jpg'),
(18,	'Death Stranding',	'2019-11-08',	'Game',	'Sam Bridges must brave a world utterly transformed by the Death Stranding. Carrying the disconnected remnants of our future, he embarks on a journey to reconnect the shattered world.',	'https://images.igdb.com/igdb/image/upload/t_cover_big_2x/cobksf.jpg'),
(19,	'Death Stranding 2: On The Beach',	'2025-12-31',	'Game',	'Embark on an inspiring mission of human connection beyond the UCA. Sam and his companions set out on a new journey to save humanity from extinction.',	'https://images.igdb.com/igdb/image/upload/t_cover_big_2x/co9ipx.jpg'),
(20,	'Clair Obscur: Expedition 33',	'2025-12-31',	'Game',	'A turn-based RPG where you lead a desperate mission to destroy the Paintress so she can never paint death again.',	'https://images.igdb.com/igdb/image/upload/t_cover_big_2x/co9gam.jpg'),
(21,	'Dark Souls',	'2011-09-22',	'Game',	'A dark fantasy RPG where players explore a vast, interconnected world filled with ancient ruins, deadly traps, and colossal bosses.',	'https://images.igdb.com/igdb/image/upload/t_cover_big_2x/co1x78.jpg'),
(22,	'Dark Souls II',	'2014-03-11',	'Game',	'Journey to the fallen kingdom of Drangleic to find a cure for the curse of the Undead, facing relentless enemies and punishing trials.',	'https://images.igdb.com/igdb/image/upload/t_cover_big_2x/co2eoo.jpg'),
(23,	'Dark Souls III',	'2016-03-24',	'Game',	'As the fires fade and the world falls into ruin, players must travel across Lothric to seek out the Lords of Cinder and link the First Flame.',	'https://images.igdb.com/igdb/image/upload/t_cover_big_2x/cob9ed.jpg'),
(24,	'Demons Souls',	'2009-02-05',	'Game',	'In his quest for power, the King of Boletaria channeled the ancient Soul Arts, awakening a demon as old as time itself.',	'https://images.igdb.com/igdb/image/upload/t_cover_big_2x/co27sk.jpg'),
(25,	'Bloodborne',	'2015-03-24',	'Game',	'Face your fears as you search for answers in the ancient city of Yharnam, now cursed with a strange endemic illness spreading through the streets.',	'https://images.igdb.com/igdb/image/upload/t_cover_big_2x/cob99l.jpg'),
(1,	'NieR Replicant ver.1.22474487139...',	'2021-04-23',	'Game',	'The upgraded prequel of NieR:Automata. A kind young man sets out with Grimoire Weiss, a strange talking book, to search for the "Sealed verses" in order to save his sister Yonah, who fell terminally ill to the Black Scrawl.',	'https://images.igdb.com/igdb/image/upload/t_cover_big_2x/co2h4a.jpg'),
(9,	'Risk of Rain 2',	'2020-08-11',	'Game',	'Escape a chaotic alien planet by fighting through hordes of frenzied monsters – with your friends, or on your own. Combine loot in surprising ways and master each character until you become the havoc you feared upon your first crash landing.',	'https://images.igdb.com/igdb/image/upload/t_cover_big_2x/coaavb.jpg'),
(3,	'Requiem for a Dream',	'2003-08-20',	'Movie',	'The drug-induced utopias of four Coney Island people are shattered when their addictions run deep.',	'https://image.tmdb.org/t/p/w1280/nOd6vjEmzCT0k4VYqsA2hwyi87C.jpg'),
(5,	'Twin Peaks',	'1990-04-08',	'TVshow',	'An idiosyncratic FBI agent investigates the murder of a young woman in the even more idiosyncratic town of Twin Peaks.',	'https://image.tmdb.org/t/p/w1280/lA9CNSdo50iQPZ8A2fyVpMvJZAf.jpg'),
(26,	'Sekiro: Shadows Die Twice',	'2019-03-22',	'Game',	'Explore late 1500s Sengoku Japan as you come face to face with larger than life foes in a dark and twisted world.',	'https://images.igdb.com/igdb/image/upload/t_cover_big_2x/co2a23.jpg'),
(27,	'Elden Ring',	'2022-02-25',	'Game',	'Rise, Tarnished, and be guided by grace to brandish the power of the Elden Ring and become an Elden Lord in the Lands Between.',	'https://images.igdb.com/igdb/image/upload/t_cover_big_2x/co4jni.jpg'),
(28,	'Resident Evil 4',	'2005-01-11',	'Game',	'Resident Evil 4 is a 2005 survival horror game developed and published by Capcom for the GameCube. Players control the special agent Leon S. Kennedy on a mission to rescue the president of the United Statess daughter, Ashley Graham, who has been abducted by a religious cult in rural Spain. Leon fights hordes of enemies infected by a mind-controlling parasite and reunites with the female spy and mercenary Ada Wong. In a departure from the fixed camera angles and slower gameplay of previous Resident Evil games, Resident Evil 4 features a dynamic camera system and action-oriented gameplay.',	'https://images.igdb.com/igdb/image/upload/t_cover_big_2x/co2wk8.jpg'),
(4,	'Umineko no Naku Koro ni',	'2007-08-17',	'Game',	'A family reunion is held on a secluded island. The dying patriarch wants to see his lost love one last time, so he tries summoning her with black magic and thats when the murders begin.',	'https://images.igdb.com/igdb/image/upload/t_cover_big_2x/co78jq.jpg'),
(6,	'Blood Type',	'1988-01-05',	'Music',	'Gruppa krovi (translated: Blood Type) is the sixth studio album by Soviet post-punk band Kino, first released in 1988. Released at the height of Perestroika and Glasnost, together with a crime thriller titled The Needle released in the same year starring lead vocalist Viktor Tsoi, it would go on to be the bands most popular album both inside and eventually outside the Soviet Union, with songs from the album, including the title track [ru], commonly being listed among top 100 lists of Russian music',	'https://ia903101.us.archive.org/32/items/mbid-3496da05-4a90-4aad-b939-7754086fe1d5/mbid-3496da05-4a90-4aad-b939-7754086fe1d5-17690643827.jpg'),
(30,	'Resident Evil Requiem',	'2026-02-27',	'Game',	'Resident Evil Requiem is the ninth entry in the Resident Evil series. Experience terrifying survival horror with FBI analyst Grace Ashcroft, and dive into pulse-pounding action with legendary agent Leon S. Kennedy. Both of their journeys and unique gameplay styles intertwine into a heart-stopping, emotional experience that will chill you to your core.',	'https://images.igdb.com/igdb/image/upload/t_cover_big_2x/cobmj0.jpg');

DROP TABLE IF EXISTS "media_genres";
CREATE TABLE "public"."media_genres" (
    "genre_id" integer NOT NULL,
    "id" integer NOT NULL,
    CONSTRAINT "media_genres_pkey" PRIMARY KEY ("genre_id", "id")
)
WITH (oids = false);

INSERT INTO "media_genres" ("genre_id", "id") VALUES
(13,	1),
(3,	1),
(35,	2),
(13,	2),
(14,	2),
(6,	3),
(7,	3),
(8,	5),
(7,	5),
(6,	5),
(9,	8),
(8,	8),
(3,	10),
(3,	11),
(3,	12),
(3,	13),
(3,	14),
(3,	15),
(3,	16),
(19,	21),
(19,	22),
(19,	23),
(19,	24),
(19,	25),
(19,	27),
(11,	28),
(3,	28),
(37,	28),
(1,	1),
(2,	2),
(1,	27),
(3,	27),
(1,	21),
(3,	21),
(37,	30),
(11,	30),
(3,	30);

DROP TABLE IF EXISTS "movie_details";
CREATE TABLE "public"."movie_details" (
    "id" integer NOT NULL,
    "length" integer,
    "director" character varying(50),
    "actors" character varying(500),
    "actors_img" character varying(100),
    CONSTRAINT "movie_details_pkey" PRIMARY KEY ("id")
)
WITH (oids = false);

INSERT INTO "movie_details" ("id", "length", "director", "actors", "actors_img") VALUES
(3,	6120,	'Darren Aronofsky',	'Jared Leto, Jennifer Connelly',	NULL);

DROP TABLE IF EXISTS "music_details";
CREATE TABLE "public"."music_details" (
    "id" integer NOT NULL,
    "length" integer,
    "artist" character varying(50),
    "album" character varying(50),
    CONSTRAINT "music_details_pkey" PRIMARY KEY ("id")
)
WITH (oids = false);

INSERT INTO "music_details" ("id", "length", "artist", "album") VALUES
(6,	2352,	'Kino',	'Gruppa Krovi');

DROP TABLE IF EXISTS "tvshow_details";
CREATE TABLE "public"."tvshow_details" (
    "id" integer NOT NULL,
    "episodecount" integer,
    "director" character varying(50),
    "seasons" integer,
    "actors" character varying(500),
    "actors_img" character varying(100),
    CONSTRAINT "tvshow_details_pkey" PRIMARY KEY ("id")
)
WITH (oids = false);

INSERT INTO "tvshow_details" ("id", "episodecount", "director", "seasons", "actors", "actors_img") VALUES
(5,	48,	'David Lynch',	3,	'Kyle MacLachlan',	NULL),
(8,	13,	'Akiyuki Shinbo',	1,	'Hiroshi Kamiya',	NULL);

DROP TABLE IF EXISTS "user_library";
CREATE TABLE "public"."user_library" (
    "user_id" integer NOT NULL,
    "id" integer NOT NULL,
    "status" character varying(20),
    "score" integer,
    "created_at" date DEFAULT CURRENT_DATE,
    CONSTRAINT "user_library_pkey" PRIMARY KEY ("user_id", "id")
)
WITH (oids = false);

INSERT INTO "user_library" ("user_id", "id", "status", "score", "created_at") VALUES
(1,	27,	'COMPLETED',	10,	'2026-04-03'),
(1,	10,	'COMPLETED',	9,	'2026-04-03'),
(1,	3,	'COMPLETED',	8,	'2026-04-03'),
(1,	21,	'COMPLETED',	9,	'2026-04-03'),
(1,	5,	'CONSUMING',	9,	'2026-04-03'),
(1,	26,	'CONSUMING',	8,	'2026-04-03'),
(3,	20,	'COMPLETED',	10,	'2026-04-03'),
(7,	8,	'COMPLETED',	10,	'2026-04-03'),
(7,	10,	'PLANNING',	0,	'2026-04-03'),
(7,	11,	'PLANNING',	0,	'2026-04-03'),
(7,	6,	'COMPLETED',	10,	'2026-04-03'),
(1,	1,	'COMPLETED',	10,	'2026-04-03'),
(1,	17,	'COMPLETED',	5,	'2026-04-03'),
(7,	25,	'PLANNING',	0,	'2026-04-03'),
(1,	22,	'PLANNING',	0,	'2026-04-03'),
(1,	23,	'PLANNING',	0,	'2026-04-03'),
(1,	24,	'PLANNING',	0,	'2026-04-03'),
(1,	19,	'CONSUMING',	8,	'2026-04-03'),
(1,	4,	'PLANNING',	0,	'2026-04-03'),
(1,	28,	'COMPLETED',	10,	'2026-04-03'),
(1,	8,	'COMPLETED',	10,	'2026-04-03'),
(8,	6,	'COMPLETED',	10,	'2026-04-03'),
(8,	23,	'COMPLETED',	9,	'2026-04-03'),
(8,	7,	'CONSUMING',	10,	'2026-04-03'),
(1,	2,	'CONSUMING',	10,	'2026-04-03'),
(7,	20,	'CONSUMING',	5,	'2026-04-03'),
(1,	7,	'CONSUMING',	7,	'2026-04-04'),
(1,	25,	'COMPLETED',	10,	'2026-04-04'),
(7,	27,	'COMPLETED',	10,	'2026-04-04');

DROP TABLE IF EXISTS "users";
DROP SEQUENCE IF EXISTS "public".users_user_id_seq;
CREATE SEQUENCE "public".users_user_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1;

CREATE TABLE "public"."users" (
    "user_id" integer DEFAULT nextval('users_user_id_seq') NOT NULL,
    "name" character varying(50),
    "password" character varying(255),
    "biography" character varying(100),
    "img_url" character varying(255),
    CONSTRAINT "users_pkey" PRIMARY KEY ("user_id")
)
WITH (oids = false);

CREATE UNIQUE INDEX unique_username ON public.users USING btree (name);

INSERT INTO "users" ("user_id", "name", "password", "biography", "img_url") VALUES
(1,	'admin',	'admin',	'admin account',	NULL),
(2,	'diddy',	'epstein',	'mango mango six seven',	NULL),
(3,	'Luhukas',	'Moizeit',	NULL,	NULL),
(4,	'Ganaa',	'Banana',	NULL,	NULL),
(5,	'Armin',	'BBC',	NULL,	NULL),
(6,	'a',	'b',	NULL,	NULL),
(7,	'PhonkyNick',	'mango',	NULL,	NULL),
(8,	'shopee',	'abc',	NULL,	NULL);

ALTER TABLE ONLY "public"."book_details" ADD CONSTRAINT "fk_book_media" FOREIGN KEY (id) REFERENCES media(id) ON DELETE CASCADE;

ALTER TABLE ONLY "public"."game_details" ADD CONSTRAINT "game_details_id_fkey" FOREIGN KEY (id) REFERENCES media(id) ON DELETE CASCADE;

ALTER TABLE ONLY "public"."media_genres" ADD CONSTRAINT "media_genres_genre_id_fkey" FOREIGN KEY (genre_id) REFERENCES genres(genre_id) ON DELETE CASCADE;

ALTER TABLE ONLY "public"."user_library" ADD CONSTRAINT "user_library_user_id_fkey" FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE;

SET session_replication_role = 'origin';

-- 2026-04-04 12:23:34 UTC
