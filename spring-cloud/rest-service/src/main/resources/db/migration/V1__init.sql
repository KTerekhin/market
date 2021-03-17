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