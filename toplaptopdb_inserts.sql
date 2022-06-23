INSERT INTO `user` (`id`, `dni`, `latitude`, `longitude`, `name`, `surname`, `user_image`, `vip`) VALUES (NULL, '76973468N', '0', '0', 'Joserra', 'Gimeno', NULL, b'0');
INSERT INTO `user` (`id`, `dni`, `latitude`, `longitude`, `name`, `surname`, `user_image`, `vip`) VALUES (NULL, '67876543U', '0', '0', 'Santiago', 'Ballestín', NULL, b'1');
INSERT INTO `user` (`id`, `dni`, `latitude`, `longitude`, `name`, `surname`, `user_image`, `vip`) VALUES (NULL, '25473819P', '0', '0', 'Fran', 'Muñoz', NULL, b'1');
INSERT INTO `user` (`id`, `dni`, `latitude`, `longitude`, `name`, `surname`, `user_image`, `vip`) VALUES (NULL, '58493059I', '0', '0', 'Santi', 'Faci', NULL, b'1');
INSERT INTO `user` (`id`, `dni`, `latitude`, `longitude`, `name`, `surname`, `user_image`, `vip`) VALUES (NULL, '59385767Y', '0', '0', 'Jose Luis', 'Meléndez', NULL, b'0');

INSERT INTO `computer` (`id`, `brand`, `computer_image`, `model`, `ram`, `user_id`) VALUES (NULL, 'Lenovo', NULL, 'L-540', '16GB', '1');
INSERT INTO `computer` (`id`, `brand`, `computer_image`, `model`, `ram`, `user_id`) VALUES (NULL, 'Asus', NULL, 'R-221', '32GB', '2');
INSERT INTO `computer` (`id`, `brand`, `computer_image`, `model`, `ram`, `user_id`) VALUES (NULL, 'Toshiba', NULL, 'T-321', '8GB', '3');
INSERT INTO `computer` (`id`, `brand`, `computer_image`, `model`, `ram`, `user_id`) VALUES (NULL, 'Mac', NULL, '1200', '64GB', '4');
INSERT INTO `computer` (`id`, `brand`, `computer_image`, `model`, `ram`, `user_id`) VALUES (NULL, 'HP', NULL, 'O-245', '4GB', '5');

INSERT INTO `technical` (`id`, `dni`, `is_available`, `name`, `surname`) VALUES (NULL, '76865432J', b'1', 'Rubén', 'Gálvez');
INSERT INTO `technical` (`id`, `dni`, `is_available`, `name`, `surname`) VALUES (NULL, '85435609R', b'0', 'Raúl', 'Martín');
INSERT INTO `technical` (`id`, `dni`, `is_available`, `name`, `surname`) VALUES (NULL, '09561452Z', b'1', 'Sandra', 'Victoria');

INSERT INTO `work_order` (`id`, `description`, `order_date`, `computer_id`, `technical_id`) VALUES (NULL, 'Placa base', '2022-06-21', '1', '1');
INSERT INTO `work_order` (`id`, `description`, `order_date`, `computer_id`, `technical_id`) VALUES (NULL, 'Batería', '2022-06-22', '2', '3');
INSERT INTO `work_order` (`id`, `description`, `order_date`, `computer_id`, `technical_id`) VALUES (NULL, 'USB-A Port', '2022-06-19', '3', '1');
INSERT INTO `work_order` (`id`, `description`, `order_date`, `computer_id`, `technical_id`) VALUES (NULL, 'Tarjeta gráfica', '2022-06-30', '4', '2');
INSERT INTO `work_order` (`id`, `description`, `order_date`, `computer_id`, `technical_id`) VALUES (NULL, 'Recuperar HDD', '2022-07-02', '5', '2');

INSERT INTO `receipt` (`id`, `date`, `discount`, `price`, `order_id`) VALUES (NULL, '2022-06-21', '0', '59.99', '1');
INSERT INTO `receipt` (`id`, `date`, `discount`, `price`, `order_id`) VALUES (NULL, '2022-06-22', '0', '34.99', '2');
INSERT INTO `receipt` (`id`, `date`, `discount`, `price`, `order_id`) VALUES (NULL, '2022-06-19', '9.99', '10.0', '3');




