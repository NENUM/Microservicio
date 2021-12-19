INSERT INTO mic_categories (id, name) VALUES (1, 'manga');
INSERT INTO mic_categories (id, name) VALUES (2, 'novela ligera');
INSERT INTO mic_categories (id, name) VALUES (3, 'novela');

INSERT INTO mic_products (id, name, description, stock, price, status, create_at, category_id) VALUES (1, 'berserk', 'manga seinen', 10, 160000, 'disponible', '2021-08-13', 1);
INSERT INTO mic_products (id, name, description, stock, price, status, create_at, category_id) VALUES (2, 'SAO', 'novela shonen', 10, 240000, 'agotado', '2021-09-24', 2);
INSERT INTO mic_products (id, name, description, stock, price, status, create_at, category_id) VALUES (3, 'JoJos', 'historias surrealistas', 10, 240000, 'disponible', '2021-02-05', 1);

