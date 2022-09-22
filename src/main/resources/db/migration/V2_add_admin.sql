INSERT INTO users (id, archive, email, first_name, password, app_user_role, bucket_id)
VALUES (1, false, 'test@mail.ru', 'admin', 'pass', 'ADMIN', null);

ALTER SEQUENCE user_seq RESTART WITH 2;