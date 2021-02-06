CREATE TABLE products
(
    `id`        BIGINT NOT NULL AUTO_INCREMENT,
    `title`     VARCHAR(255) NOT NULL,
    `price`     INT NOT NULL,
    PRIMARY KEY (`id`)
);

INSERT INTO products (`title`, `price`)
VALUES ('Coffee', '300'),
       ('Meat', '350'),
       ('Pizza', '500'),
       ('Juice', '80'),
       ('Ice-Cream', '40'),
       ('Apple', '90'),
       ('Orange', '120'),
       ('Banana', '60'),
       ('Milk', '40'),
       ('Fish', '160'),
       ('Potato', '45'),
       ('Cake', '320'),
       ('Tomato', '200'),
       ('Chicken', '260'),
       ('Sausage', '330'),
       ('Cucumber', '30'),
       ('Ketchup', '100'),
       ('Pepper', '360'),
       ('Water', '20'),
       ('Cheese', '600');

CREATE TABLE users
(
    `id`          BIGINT NOT NULL AUTO_INCREMENT,
    `username`    VARCHAR(30) NOT NULL UNIQUE,
    `password`    VARCHAR(80) NOT NULL,
    `email`       VARCHAR(50) UNIQUE,
    PRIMARY KEY (`id`)
);

CREATE TABLE cart_items
(
    `user_id`           BIGINT NOT NULL,
    `product_id`        BIGINT NOT NULL,
    `title`             VARCHAR(255) NOT NULL,
    `quantity`          INT DEFAULT NULL,
    `price_per_item`    INT NOT NULL,
    `price`             INT NOT NULL,
    PRIMARY KEY (`user_id`, `product_id`),

    CONSTRAINT `fk_product_id` FOREIGN KEY (`product_id`) REFERENCES products (`id`),

    CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES users (`id`)

);

CREATE TABLE orders
(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `user_id` BIGINT NOT NULL,
    `total_price` INT,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_orders_user_id` FOREIGN KEY (`user_id`) REFERENCES users (`id`)
);

CREATE TABLE order_items
(
    `id`                 BIGINT NOT NULL AUTO_INCREMENT,
    `order_id`           BIGINT NOT NULL,
    `product_id`         BIGINT NOT NULL,
    `quantity`           INT NOT NULL,
    `price_per_product`  INT NOT NULL,
    `price`              INT NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_oi_product_id` FOREIGN KEY (`product_id`) REFERENCES products (`id`)
);

CREATE TABLE roles
(
    `id`            BIGINT NOT NULL AUTO_INCREMENT,
    `name`          varchar(50) NOT NULL UNIQUE,
    PRIMARY KEY (`id`)
);

CREATE TABLE users_roles
(
    `user_id`   BIGINT NOT NULL,
    `role_id`   BIGINT NOT NULL,
    PRIMARY KEY (`user_id`, `role_id`),
    CONSTRAINT `fk_ur_user_id` FOREIGN KEY (`user_id`) REFERENCES users (`id`),
    CONSTRAINT `fk_ur_role_id` FOREIGN KEY (`role_id`) REFERENCES roles (`id`)
);

INSERT INTO roles (`name`)
VALUES ('ROLE_USER'),
       ('ROLE_ADMIN');

INSERT INTO users (`username`, `password`, `email`)
VALUES ('user', '$2y$12$IAieIJUHqfTJseESXekFc..ejWs2QrXIGOssXWHLolAVMhyPUvxGy', 'user_johnson@gmail.com'),
       ('admin', '$2y$12$JbKq5NgIkhY/zrfwRHJiZuqALkKYZzLW4HrApex/Ja6Vfb43VS8wq', 'admin_johnson@gmail.com');

INSERT INTO users_roles (`user_id`, `role_id`)
VALUES (1, 1),
       (2, 2);