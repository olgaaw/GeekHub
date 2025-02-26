INSERT INTO user_entity (id, username, email, password, name, surname, phone, address, cp, gender, birthday, profile_picture, bio, enabled, created_at)
VALUES ('a7c449e4-1316-4ffc-a218-7a585fa128f4', 'admin', 'jdoe@example.com', '{noop}admin', 'John', 'Doe', '1234567890', '123 Elm St', 90210, 'Male', '1990-01-01', 'profile1.jpg', 'Biography example',  true, NOW());

INSERT INTO user_entity (id, username, email, password, name, surname, phone, address, cp, gender, birthday, profile_picture, bio, enabled, created_at)
VALUES ('a7c449e4-1316-4ffc-a218-7a585fa128f3','user', 'asmith@example.com', '{noop}1234', 'Alice', 'Smith', '0987654321', '456 Oak St', 90211, 'Female', '1992-02-02', 'profile2.jpg', 'Biography example',  true, NOW());

INSERT INTO user_entity (id, username, email, password, name, surname, phone, address, cp, gender, birthday, profile_picture, bio, enabled, created_at)
VALUES ('a7c449e4-1316-4ffc-a218-7a585fa128f8','bwhite', 'bwhite@example.com', '{noop}hashed_password_3', 'Bob', 'White', '5555555555', '789 Pine St', 90212, 'Male', '1988-03-03', 'profile3.jpg', 'Biography example',  true, NOW());

INSERT INTO user_entity (id, username, email, password, name, surname, phone, address, cp, gender, birthday, profile_picture, bio, enabled, created_at)
VALUES ('a7c449e4-1316-4ffc-a218-7a585fa128f9','dlee', 'dlee@example.com', '{noop}hashed_password_5', 'Diana', 'Lee', '3333333333', '654 Cedar St', 90214, 'Female', '1985-05-05', 'profile5.jpg', 'Biography example',  true, NOW());

INSERT INTO user_entity (id, username, email, password, name, surname, phone, address, cp, gender, birthday, profile_picture, bio, enabled, created_at)
VALUES ('b7c449e4-1316-4ffc-a218-7a585fa128f8','emartinez', 'emartinez@example.com', '{noop}hashed_password_6', 'Ethan', 'Martinez', '2222222222', '987 Birch St', 90215, 'Male', '1993-06-06', 'profile6.jpg', 'Biography example',  true, NOW());

INSERT INTO user_entity (id, username, email, password, name, surname, phone, address, cp, gender, birthday, profile_picture, bio, enabled, created_at)
VALUES ('a7c449e4-1316-4ffc-a218-7a585fa128f0','fgarcia', 'fgarcia@example.com', '{noop}hashed_password_7', 'Fiona', 'Garcia', '1111111111', '135 Willow St', 90216, 'Female', '1991-07-07', 'profile7.jpg', 'Biography example',  true, NOW());

INSERT INTO user_entity (id, username, email, password, name, surname, phone, address, cp, gender, birthday, profile_picture, bio, enabled, created_at)
VALUES ('a7c449e4-1316-4ffc-a218-7a585fa128f7','gthompson', 'gthompson@example.com', '{noop}hashed_password_8', 'George', 'Thompson', '6666666666', '246 Spruce St', 90217, 'Male', '1989-08-08', 'profile8.jpg', 'Biography example',  true, NOW());

INSERT INTO user_entity (id, username, email, password, name, surname, phone, address, cp, gender, birthday, profile_picture, bio, enabled, created_at)
VALUES ('a7c449e4-1316-4ffc-a218-7a585fa128f6','hrodriguez', 'hrodriguez@example.com', '{noop}hashed_password_9', 'Hannah', 'Rodriguez', '7777777777', '357 Fir St', 90218, 'Female', '1994-09-09', 'profile9.jpg', 'Biography example',  true, NOW());

INSERT INTO user_entity (id, username, email, password, name, surname, phone, address, cp, gender, birthday, profile_picture, bio, enabled, created_at)
VALUES ('a7c449e4-1316-4ffc-a218-7a585fa128f5','ijones', 'ijones@example.com', '{noop}hashed_password_10', 'Isaac', 'Jones', '8888888888', '468 Cherry St', 90219, 'Male', '1996-10-10', 'profile10.jpg', 'Biography example',  true, NOW());



INSERT INTO user_roles(user_id, roles) VALUES ('a7c449e4-1316-4ffc-a218-7a585fa128f4', 0);
INSERT INTO user_roles(user_id, roles) VALUES ('a7c449e4-1316-4ffc-a218-7a585fa128f3', 1);
INSERT INTO user_roles(user_id, roles) VALUES ('a7c449e4-1316-4ffc-a218-7a585fa128f8', 1);
INSERT INTO user_roles(user_id, roles) VALUES ('a7c449e4-1316-4ffc-a218-7a585fa128f9', 1);
INSERT INTO user_roles(user_id, roles) VALUES ('b7c449e4-1316-4ffc-a218-7a585fa128f8', 1);
INSERT INTO user_roles(user_id, roles) VALUES ('a7c449e4-1316-4ffc-a218-7a585fa128f0', 1);
INSERT INTO user_roles(user_id, roles) VALUES ('a7c449e4-1316-4ffc-a218-7a585fa128f7', 1);
INSERT INTO user_roles(user_id, roles) VALUES ('a7c449e4-1316-4ffc-a218-7a585fa128f6', 1);
INSERT INTO user_roles(user_id, roles) VALUES ('a7c449e4-1316-4ffc-a218-7a585fa128f5', 1);

-- random_uuid() para h2, gen_random_uuid() para postgres
INSERT INTO interest_entity(id, name, picture) VALUES ('d1c449e4-1316-4ffc-a218-7a585fa128f0', 'Anime', 'anime_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('d1c449e4-1316-4ffc-a218-7a585fa128f1', 'Videojuegos', 'videojuegos_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('d1c449e4-1316-4ffc-a218-7a585fa128f2', 'Música', 'musica_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('d1c449e4-1316-4ffc-a218-7a585fa128f3', 'Deportes', 'deportes_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('d1c449e4-1316-4ffc-a218-7a585fa128f4', 'Cine', 'cine_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('d1c449e4-1316-4ffc-a218-7a585fa128f5', 'Libros', 'libros_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('d1c449e4-1316-4ffc-a218-7a585fa128f6', 'Viajes', 'viajes_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('d1c449e4-1316-4ffc-a218-7a585fa128f7', 'Fotografía', 'fotografia_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('d1c449e4-1316-4ffc-a218-7a585fa128f8', 'Cocina', 'cocina_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('d1c449e4-1316-4ffc-a218-7a585fa128f9', 'Tecnología', 'tecnologia_picture.jpg');

INSERT INTO interest_entity(id, name, picture) VALUES ('d1c449e4-1316-4ffc-a218-7a585fa129a0', 'Naruto', 'naruto_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('d1c449e4-1316-4ffc-a218-7a585fa129a1', 'Attack on Titan', 'attack_on_titan_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('d1c449e4-1316-4ffc-a218-7a585fa129a2', 'My Hero Academia', 'my_hero_academia_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('d1c449e4-1316-4ffc-a218-7a585fa129a3', 'One Piece', 'one_piece_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('d1c449e4-1316-4ffc-a218-7a585fa129a4', 'Demon Slayer', 'demon_slayer_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('d1c449e4-1316-4ffc-a218-7a585fa129a5', 'Death Note', 'death_note_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('d1c449e4-1316-4ffc-a218-7a585fa129a6', 'Fullmetal Alchemist', 'fullmetal_alchemist_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('d1c449e4-1316-4ffc-a218-7a585fa129a7', 'Sword Art Online', 'sword_art_online_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('d1c449e4-1316-4ffc-a218-7a585fa129a8', 'Dragon Ball Z', 'dragon_ball_z_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('d1c449e4-1316-4ffc-a218-7a585fa129a9', 'Tokyo Ghoul', 'tokyo_ghoul_picture.jpg');


INSERT INTO interest_entity(id, name, picture) VALUES ('6d2e8f63-bc3e-4f4c-a99d-3f51a678c8a1', 'The Legend of Zelda: Breath of the Wild', 'zelda_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('91a3f24e-7b22-4e94-b5de-9b0e682b4f32', 'Final Fantasy VII', 'final_fantasy_vii_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('c7e6d9a4-36f1-4e7a-bb61-b8a8f3c5e78b', 'Minecraft', 'minecraft_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('db59fd1b-12a6-4f47-bf07-6c497e38e052', 'Overwatch', 'overwatch_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('e5d12fa3-7f98-4a5c-9182-dc5f22c68b5a', 'Dark Souls', 'dark_souls_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('2a896f07-79c5-4018-9b35-10d79e0b4f68', 'Among Us', 'among_us_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('836f5c2d-d591-4b6b-a4b2-03d2cb9bfa5c', 'The Witcher 3: Wild Hunt', 'witcher_3_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('c4e8d1f2-6572-4be3-aad7-e9fd5db5ea1f', 'God of War', 'god_of_war_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('51fef827-daf9-4036-b3d8-4b8e85cf29c6', 'Resident Evil 2', 'resident_evil_2_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('e9cb6f3b-d3c8-46e8-b98a-09e7dba452bf', 'Cyberpunk 2077', 'cyberpunk_2077_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('3f72bc94-4d58-4566-9d2a-fbc9165b6c8b', 'The Last of Us', 'the_last_of_us_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('a6f1b7e1-ef88-45c5-888b-5f60b1c7dfd3', 'Halo: Combat Evolved', 'halo_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('b92a1ea8-6037-4952-9b0d-3746b5e7a6e1', 'Red Dead Redemption 2', 'red_dead_redemption_2_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('f5d4f089-9673-4a56-92e5-4f70f23b6f49', 'Assassins Creed Valhalla', 'assassins_creed_valhalla_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('c38d8b9d-bf16-4b8a-9bcb-8e1d9e837bfa', 'Fortnite', 'fortnite_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('67fb2a5c-1eb9-4a90-8d61-df68e3b8c79e', 'Apex Legends', 'apex_legends_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('5f8e6c33-f7de-4d0f-8a7a-d29b5f645e9a', 'Call of Duty: Warzone', 'call_of_duty_warzone_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('19cf97e8-41bf-46cc-bc5b-741d1c6b5a7f', 'Genshin Impact', 'genshin_impact_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('2c48e14d-cba8-4966-985b-3e05e1b48d29', 'Animal Crossing: New Horizons', 'animal_crossing_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('ac1df96a-5cfb-41bc-988e-57f93d2564c7', 'Hollow Knight', 'hollow_knight_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('8f973a1d-9c65-414b-86f3-bf692b5a9c3e', 'Stardew Valley', 'stardew_valley_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('d6b08e53-4e0a-4d5e-92ec-173e76348b69', 'Overcooked!', 'overcooked_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('e31c5a58-fb92-4fae-9bde-4a0b3f5c9e71', 'Celeste', 'celeste_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('7f4cdb8e-2bfc-4f6b-b5d8-0b29c9e73d8a', 'Hades', 'hades_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('9b7e3a61-2c6f-4d7d-b03f-34c48a3d1e3b', 'Sekiro: Shadows Die Twice', 'sekiro_picture.jpg');

INSERT INTO interest_entity(id, name, picture) VALUES ('b0eec00e-d8d9-4cc0-bd8a-f1cd1da6f9b2', 'Inception', 'inception_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('550dc7be-dbe5-474d-91b9-b9c07bc3c6a9', 'The Matrix', 'the_matrix_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('b7d31f12-dbe5-4f3d-9639-bf5d256211f5', 'Interstellar', 'interstellar_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('6e17b832-b8b8-47f4-b0f5-65d10c56f6be', 'The Dark Knight', 'the_dark_knight_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('98d38d3e-7764-46b1-b218-4b0901572f35', 'Pulp Fiction', 'pulp_fiction_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('b71d8b22-c225-4294-80ab-99d9b2297a6f', 'Forrest Gump', 'forrest_gump_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('56c42f32-b9bc-4a1f-bff0-3f0e62fc9d6d', 'The Shawshank Redemption', 'shawshank_redemption_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('8e3f30f2-b18a-4c8d-bb27-d2a08a468a61', 'Fight Club', 'fight_club_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('3c67f8e7-c3c9-4770-b0ae-804e7a6f4cc4', 'The Godfather', 'the_godfather_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('d498e5c7-9fbb-49f1-b78d-1b1e1c2b003b', 'Gladiator', 'gladiator_picture.jpg');

INSERT INTO interest_entity(id, name, picture) VALUES ('58d0399e-3f2b-4210-a92d-d17d4c7bcdd5', 'Catan', 'catan_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('b256d01d-bd08-46b2-b1bb-7cfb763bb9ba', 'Carcassonne', 'carcassonne_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('ce87439e-95c8-4c31-9a8d-73bb3e7d9a47', 'Ticket to Ride', 'ticket_to_ride_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('69f98a3b-7202-4017-8cc5-3a1b1e6c2679', 'Pandemic', 'pandemic_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('d05515b7-d68d-48f1-a56c-d67ab5128a23', '7 Wonders', '7_wonders_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('21a5f810-0a99-424b-9c0f-c9e0b0d126e7', 'Dixit', 'dixit_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('4e3c240b-9b99-4a84-8d35-2f34e388fc72', 'Splendor', 'splendor_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('ce94e58f-68d7-47b1-9b56-bc2e9a5e94f1', 'Azul', 'azul_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('7f232941-c5ab-4aeb-bbbb-b92be385ce2b', 'Terraforming Mars', 'terraforming_mars_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('80a1b4e5-b10d-41a9-96be-bb36a77b1b92', 'Gloomhaven', 'gloomhaven_picture.jpg');

INSERT INTO interest_entity(id, name, picture) VALUES ('13bb8bc2-e175-4e3f-9a56-62b4312a4b6c', 'Magic: The Gathering', 'magic_the_gathering_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('47c8b7fe-9738-47f9-8779-4f7e1db7b7d3', 'Yu-Gi-Oh!', 'yu_gi_oh_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('ec631b53-0d98-44d5-a124-e2764b3d09a1', 'Pokémon Trading Card Game', 'pokemon_card_game_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('4fd3db3e-0de5-4fa3-9137-440df7e6e0f7', 'Uno', 'uno_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('f8ac7d83-1b39-4b1c-84b1-0cd42a706169', 'Exploding Kittens', 'exploding_kittens_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('d4ab64c9-89e7-4dfe-8d8c-f9c08a3032a9', 'Cards Against Humanity', 'cards_against_humanity_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('5b1e46e4-e238-49b3-bb5e-1a54e76c6ea2', 'The Mind', 'the_mind_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('6fe3e569-e6da-4f58-8a53-b601fd8201c5', 'Coup', 'coup_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('60394c6d-3d56-4b51-b197-b4d945dca931', 'The Game', 'the_game_picture.jpg');
INSERT INTO interest_entity(id, name, picture) VALUES ('2a98ec7b-4c58-4f10-946b-7360e22b6d45', 'Dominion', 'dominion_picture.jpg');


INSERT INTO user_entity_interests (user_id, interest_id) VALUES ('a7c449e4-1316-4ffc-a218-7a585fa128f4', '13bb8bc2-e175-4e3f-9a56-62b4312a4b6c'); -- John Doe - Magic: The Gathering
INSERT INTO user_entity_interests (user_id, interest_id) VALUES ('a7c449e4-1316-4ffc-a218-7a585fa128f3', '47c8b7fe-9738-47f9-8779-4f7e1db7b7d3'); -- Alice Smith - Yu-Gi-Oh!
INSERT INTO user_entity_interests (user_id, interest_id) VALUES ('a7c449e4-1316-4ffc-a218-7a585fa128f8', 'ec631b53-0d98-44d5-a124-e2764b3d09a1'); -- Bob White - Pokémon Trading Card Game
INSERT INTO user_entity_interests (user_id, interest_id) VALUES ('a7c449e4-1316-4ffc-a218-7a585fa128f9', '4fd3db3e-0de5-4fa3-9137-440df7e6e0f7'); -- Diana Lee - Uno
INSERT INTO user_entity_interests (user_id, interest_id) VALUES ('b7c449e4-1316-4ffc-a218-7a585fa128f8', 'f8ac7d83-1b39-4b1c-84b1-0cd42a706169'); -- Ethan Martinez - Exploding Kittens
INSERT INTO user_entity_interests (user_id, interest_id) VALUES ('a7c449e4-1316-4ffc-a218-7a585fa128f0', 'd4ab64c9-89e7-4dfe-8d8c-f9c08a3032a9'); -- Fiona Garcia - Cards Against Humanity
INSERT INTO user_entity_interests (user_id, interest_id) VALUES ('a7c449e4-1316-4ffc-a218-7a585fa128f7', '5b1e46e4-e238-49b3-bb5e-1a54e76c6ea2'); -- George Thompson - The Mind
INSERT INTO user_entity_interests (user_id, interest_id) VALUES ('a7c449e4-1316-4ffc-a218-7a585fa128f6', '6fe3e569-e6da-4f58-8a53-b601fd8201c5'); -- Hannah Rodriguez - Coup
INSERT INTO user_entity_interests (user_id, interest_id) VALUES ('a7c449e4-1316-4ffc-a218-7a585fa128f5', '60394c6d-3d56-4b51-b197-b4d945dca931'); -- Isaac Jones - The Game
INSERT INTO user_entity_interests (user_id, interest_id) VALUES ('a7c449e4-1316-4ffc-a218-7a585fa128f4', '2a98ec7b-4c58-4f10-946b-7360e22b6d45'); -- John Doe - Dominion


INSERT INTO post_entity (id, user_id, description, dateP)
VALUES ('b8f8c53b-6a1b-4a56-bdcd-e2fbb34e1c77', 'a7c449e4-1316-4ffc-a218-7a585fa128f4', 'Este es un post de prueba 2', '2025-02-22T12:00:00');

INSERT INTO image_entity (id, post_id, image_url) VALUES
('123e4567-e89b-12d3-a456-426614174000', 'b8f8c53b-6a1b-4a56-bdcd-e2fbb34e1c77', 'https://example.com/imagen1.jpg'),
('123e4567-e89b-12d3-a456-426614174001', 'b8f8c53b-6a1b-4a56-bdcd-e2fbb34e1c77', 'https://example.com/imagen2.jpg');


INSERT INTO post_entity (id, user_id, description, dateP)
VALUES ('f1e8c11e-7890-48fc-9ad6-d9b1cf9819f2', 'a7c449e4-1316-4ffc-a218-7a585fa128f4', 'Este es un post de prueba de John Doe', '2025-02-23T10:00:00');

INSERT INTO image_entity (id, post_id, image_url) VALUES
('ce8e6c8a-47a2-4cfb-8b9a-948c9c1ca2e4', 'f1e8c11e-7890-48fc-9ad6-d9b1cf9819f2', 'https://example.com/jdoe_post_image.jpg');


INSERT INTO post_entity (id, user_id, description, dateP)
VALUES ('b9c58d6c-5bb7-4967-97cb-0b0b9d01876c', 'a7c449e4-1316-4ffc-a218-7a585fa128f3', 'Este es un post de prueba de Alice Smith', '2025-02-23T12:30:00');

INSERT INTO image_entity (id, post_id, image_url) VALUES
('89c598db-35c9-450d-91ff-6347f1acdf2f', 'b9c58d6c-5bb7-4967-97cb-0b0b9d01876c', 'https://example.com/asmith_post_image.jpg');


INSERT INTO post_entity (id, user_id, description, dateP)
VALUES ('dca6c6d5-5d91-479b-9fa6-eaa96d87060f', 'a7c449e4-1316-4ffc-a218-7a585fa128f8', 'Este es un post de prueba de Bob White', '2025-02-23T14:45:00');

INSERT INTO image_entity (id, post_id, image_url) VALUES
('dbb56fcb-8724-44b2-b15b-8f2188a38f30', 'dca6c6d5-5d91-479b-9fa6-eaa96d87060f', 'https://example.com/bwhite_post_image.jpg');


INSERT INTO post_entity (id, user_id, description, dateP)
VALUES ('aeb6a582-8d9b-45c3-8a4b-9084823d2431', 'a7c449e4-1316-4ffc-a218-7a585fa128f9', 'Este es un post de prueba de Diana Lee', '2025-02-23T16:00:00');

INSERT INTO image_entity (id, post_id, image_url) VALUES
('3722a2b4-b372-4ca4-a9b1-d824295dc244', 'aeb6a582-8d9b-45c3-8a4b-9084823d2431', 'https://example.com/dlee_post_image.jpg');


INSERT INTO post_entity (id, user_id, description, dateP)
VALUES ('eb94e0c1-9264-48b4-bb79-ffaf759ac6ba', 'b7c449e4-1316-4ffc-a218-7a585fa128f8', 'Este es un post de prueba de Ethan Martinez', '2025-02-23T18:15:00');

INSERT INTO image_entity (id, post_id, image_url) VALUES
('6f264004-2674-48d6-bc83-15c7b915b429', 'eb94e0c1-9264-48b4-bb79-ffaf759ac6ba', 'https://example.com/emartinez_post_image.jpg');


INSERT INTO comment_entity(id, post_id, user_id, content, created_at)
VALUES ('6f264004-2674-48d6-bc83-15c7b915b418', 'aeb6a582-8d9b-45c3-8a4b-9084823d2431', 'a7c449e4-1316-4ffc-a218-7a585fa128f8', 'comentario de ejemplo', '2025-02-23T18:15:00');

INSERT INTO comment_entity (id, post_id, user_id, content, created_at) VALUES
('6f264004-2674-48d6-bc83-15c7b915b419', 'aeb6a582-8d9b-45c3-8a4b-9084823d2431', 'a7c449e4-1316-4ffc-a218-7a585fa128f4', '¡Muy buen post, Diana!', '2025-02-23T16:05:00');

INSERT INTO comment_entity (id, post_id, user_id, content, created_at) VALUES
('7b3d2291-5c4b-4d56-b89e-91547f8b123c', 'eb94e0c1-9264-48b4-bb79-ffaf759ac6ba', 'b7c449e4-1316-4ffc-a218-7a585fa128f8', 'Gran contenido, Ethan.', '2025-02-23T18:20:00');

INSERT INTO comment_entity (id, post_id, user_id, content, created_at) VALUES
('3fa85f64-5717-4562-b3fc-2c963f66afa6', 'b9c58d6c-5bb7-4967-97cb-0b0b9d01876c', 'a7c449e4-1316-4ffc-a218-7a585fa128f3', '¡Me encanta este post, Alice!', '2025-02-23T12:35:00');

INSERT INTO comment_entity (id, post_id, user_id, content, created_at) VALUES
('f28d36a9-9c4a-4d3a-94c2-678c93cf4b3d', 'dca6c6d5-5d91-479b-9fa6-eaa96d87060f', 'a7c449e4-1316-4ffc-a218-7a585fa128f8', 'Buena información, Bob.', '2025-02-23T14:50:00');

INSERT INTO comment_entity (id, post_id, user_id, content, created_at) VALUES
('9ab48e6b-82c8-4f6a-9405-714e872945cb', 'f1e8c11e-7890-48fc-9ad6-d9b1cf9819f2', 'a7c449e4-1316-4ffc-a218-7a585fa128f4', 'Excelente aporte, John.', '2025-02-23T10:05:00');

INSERT INTO comment_entity (id, post_id, user_id, content, created_at) VALUES
('c8a2e8eb-4fcb-4d6f-b8c3-b68a91b8c123', 'b8f8c53b-6a1b-4a56-bdcd-e2fbb34e1c77', 'a7c449e4-1316-4ffc-a218-7a585fa128f4', 'Interesante perspectiva.', '2025-02-22T12:05:00');

INSERT INTO like_entity(id, post_id, user_id, is_liked, created_at)
VALUES ('6795a345-622e-4e96-971e-4f826a2b1707', 'b8f8c53b-6a1b-4a56-bdcd-e2fbb34e1c77', 'a7c449e4-1316-4ffc-a218-7a585fa128f8', true ,'2025-02-22T12:05:00');

INSERT INTO like_entity(id, post_id, user_id, is_liked, created_at)
VALUES ('6795a345-622e-4e96-971e-4f826a2b1706', 'b8f8c53b-6a1b-4a56-bdcd-e2fbb34e1c77', 'b7c449e4-1316-4ffc-a218-7a585fa128f8', true ,'2025-02-22T12:05:00');

INSERT INTO like_entity(id, post_id, user_id, is_liked, created_at)
VALUES ('6795a345-622e-4e96-971e-4f826a2b1705', 'b8f8c53b-6a1b-4a56-bdcd-e2fbb34e1c77', 'a7c449e4-1316-4ffc-a218-7a585fa128f0', true ,'2025-02-22T12:05:00');









