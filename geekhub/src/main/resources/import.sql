INSERT INTO user_entity (id, username, email, password, name, surname, phone, address, cp, gender, birthday, profile_picture, bio, enabled, created_at)
VALUES (random_uuid(), 'jdoe', 'jdoe@example.com', 'hashed_password_1', 'John', 'Doe', '1234567890', '123 Elm St', 90210, 'Male', '1990-01-01', 'profile1.jpg', 'Biography example',  true, NOW());

INSERT INTO user_entity (id, username, email, password, name, surname, phone, address, cp, gender, birthday, profile_picture, bio, enabled, created_at)
VALUES (random_uuid(),'asmith', 'asmith@example.com', 'hashed_password_2', 'Alice', 'Smith', '0987654321', '456 Oak St', 90211, 'Female', '1992-02-02', 'profile2.jpg', 'Biography example',  true, NOW());

INSERT INTO user_entity (id, username, email, password, name, surname, phone, address, cp, gender, birthday, profile_picture, bio, enabled, created_at)
VALUES (random_uuid(),'bwhite', 'bwhite@example.com', 'hashed_password_3', 'Bob', 'White', '5555555555', '789 Pine St', 90212, 'Male', '1988-03-03', 'profile3.jpg', 'Biography example',  true, NOW());

INSERT INTO user_entity (id, username, email, password, name, surname, phone, address, cp, gender, birthday, profile_picture, bio, enabled, created_at)
VALUES (random_uuid(),'dlee', 'dlee@example.com', 'hashed_password_5', 'Diana', 'Lee', '3333333333', '654 Cedar St', 90214, 'Female', '1985-05-05', 'profile5.jpg', 'Biography example',  true, NOW());

INSERT INTO user_entity (id, username, email, password, name, surname, phone, address, cp, gender, birthday, profile_picture, bio, enabled, created_at)
VALUES (random_uuid(),'emartinez', 'emartinez@example.com', 'hashed_password_6', 'Ethan', 'Martinez', '2222222222', '987 Birch St', 90215, 'Male', '1993-06-06', 'profile6.jpg', 'Biography example',  true, NOW());

INSERT INTO user_entity (id, username, email, password, name, surname, phone, address, cp, gender, birthday, profile_picture, bio, enabled, created_at)
VALUES (random_uuid(),'fgarcia', 'fgarcia@example.com', 'hashed_password_7', 'Fiona', 'Garcia', '1111111111', '135 Willow St', 90216, 'Female', '1991-07-07', 'profile7.jpg', 'Biography example',  true, NOW());

INSERT INTO user_entity (id, username, email, password, name, surname, phone, address, cp, gender, birthday, profile_picture, bio, enabled, created_at)
VALUES (random_uuid(),'gthompson', 'gthompson@example.com', 'hashed_password_8', 'George', 'Thompson', '6666666666', '246 Spruce St', 90217, 'Male', '1989-08-08', 'profile8.jpg', 'Biography example',  true, NOW());

INSERT INTO user_entity (id, username, email, password, name, surname, phone, address, cp, gender, birthday, profile_picture, bio, enabled, created_at)
VALUES (random_uuid(),'hrodriguez', 'hrodriguez@example.com', 'hashed_password_9', 'Hannah', 'Rodriguez', '7777777777', '357 Fir St', 90218, 'Female', '1994-09-09', 'profile9.jpg', 'Biography example',  true, NOW());

INSERT INTO user_entity (id, username, email, password, name, surname, phone, address, cp, gender, birthday, profile_picture, bio, enabled, created_at)
VALUES (random_uuid(),'ijones', 'ijones@example.com', 'hashed_password_10', 'Isaac', 'Jones', '8888888888', '468 Cherry St', 90219, 'Male', '1996-10-10', 'profile10.jpg', 'Biography example',  true, NOW());

-- random_uuid() para h2, gen_random_uuid() para postgres

INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Anime', 'anime_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Videojuegos', 'videojuegos_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Música', 'musica_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Deportes', 'deportes_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Cine', 'cine_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Libros', 'libros_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Viajes', 'viajes_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Fotografía', 'fotografia_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Cocina', 'cocina_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Tecnología', 'tecnologia_picture.jpg');


INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Naruto', 'naruto_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Attack on Titan', 'attack_on_titan_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'My Hero Academia', 'my_hero_academia_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'One Piece', 'one_piece_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Demon Slayer', 'demon_slayer_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Death Note', 'death_note_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Fullmetal Alchemist', 'fullmetal_alchemist_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Sword Art Online', 'sword_art_online_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Dragon Ball Z', 'dragon_ball_z_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Tokyo Ghoul', 'tokyo_ghoul_picture.jpg');

INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'The Legend of Zelda: Breath of the Wild', 'zelda_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Final Fantasy VII', 'final_fantasy_vii_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Minecraft', 'minecraft_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Overwatch', 'overwatch_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Dark Souls', 'dark_souls_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Among Us', 'among_us_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'The Witcher 3: Wild Hunt', 'witcher_3_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'God of War', 'god_of_war_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Resident Evil 2', 'resident_evil_2_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Cyberpunk 2077', 'cyberpunk_2077_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'The Last of Us', 'the_last_of_us_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Halo: Combat Evolved', 'halo_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Red Dead Redemption 2', 'red_dead_redemption_2_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Assassins Creed Valhalla', 'assassins_creed_valhalla_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Fortnite', 'fortnite_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Apex Legends', 'apex_legends_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Call of Duty: Warzone', 'call_of_duty_warzone_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Genshin Impact', 'genshin_impact_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Animal Crossing: New Horizons', 'animal_crossing_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Hollow Knight', 'hollow_knight_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Stardew Valley', 'stardew_valley_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Overcooked!', 'overcooked_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Celeste', 'celeste_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Hades', 'hades_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Sekiro: Shadows Die Twice', 'sekiro_picture.jpg');


INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Inception', 'inception_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'The Matrix', 'the_matrix_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Interstellar', 'interstellar_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'The Dark Knight', 'the_dark_knight_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Pulp Fiction', 'pulp_fiction_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Forrest Gump', 'forrest_gump_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'The Shawshank Redemption', 'shawshank_redemption_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Fight Club', 'fight_club_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'The Godfather', 'the_godfather_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Gladiator', 'gladiator_picture.jpg');

INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Catan', 'catan_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Carcassonne', 'carcassonne_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Ticket to Ride', 'ticket_to_ride_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Pandemic', 'pandemic_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), '7 Wonders', '7_wonders_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Dixit', 'dixit_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Splendor', 'splendor_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Azul', 'azul_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Terraforming Mars', 'terraforming_mars_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Gloomhaven', 'gloomhaven_picture.jpg');

INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Magic: The Gathering', 'magic_the_gathering_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Yu-Gi-Oh!', 'yu_gi_oh_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Pokémon Trading Card Game', 'pokemon_card_game_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Uno', 'uno_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Exploding Kittens', 'exploding_kittens_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Cards Against Humanity', 'cards_against_humanity_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'The Mind', 'the_mind_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Coup', 'coup_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'The Game', 'the_game_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES (random_uuid(), 'Dominion', 'dominion_picture.jpg');
