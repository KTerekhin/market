CREATE TABLE products (
    id                      bigserial primary key,
    title                   varchar(255),
    price                   int,
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);

INSERT INTO products (title, price)
VALUES
('Coffee', '300'),
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

CREATE TABLE users (
    id                      bigserial primary key,
    username                varchar(30) not null unique,
    password                varchar(80) not null,
    email                   varchar(50) unique,
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);

CREATE TABLE orders (
    id                      bigserial primary key,
    owner_id                bigint references users (id),
    price                   int,
    address                 varchar(255),
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);

CREATE TABLE order_items (
    id                      bigserial primary key,
    order_id                bigint references orders (id),
    product_id              bigint references products (id),
    title                   varchar(255),
    quantity                int,
    price_per_product       int,
    price                   int,
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);

CREATE TABLE roles (
    id                      bigserial primary key,
    name                    varchar(50) not null unique,
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);

CREATE TABLE users_roles (
    user_id                 bigint not null references users (id),
    role_id                 bigint not null references roles (id),
    primary key (user_id, role_id)
);

INSERT INTO roles (name)
VALUES
('ROLE_USER'),
('ROLE_ADMIN');

INSERT INTO users (username, password, email)
VALUES
('user', '$2y$12$IAieIJUHqfTJseESXekFc..ejWs2QrXIGOssXWHLolAVMhyPUvxGy', 'user_johnson@gmail.com'),
('admin', '$2y$12$JbKq5NgIkhY/zrfwRHJiZuqALkKYZzLW4HrApex/Ja6Vfb43VS8wq', 'admin_johnson@gmail.com');

INSERT INTO users_roles (user_id, role_id)
VALUES
(1, 1),
(2, 2);

create table carts (
    id                      UUID primary key,
    owner_id                bigint references users (id),
    price                   int
);

create table cart_items (
    id                bigserial primary key,
    cart_id           UUID references carts (id),
    product_id        bigint references products (id),
    title             varchar(255),
    quantity          int,
    price_per_product int,
    price             int,
    created_at        timestamp default current_timestamp,
    updated_at        timestamp default current_timestamp
);