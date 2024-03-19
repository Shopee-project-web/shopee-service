-- Insert User
INSERT INTO `shoppify_data`.`user` (`PHONE_NUMBER`, `USERNAME`, `EMAIL`, `PASSWORD`, `IS_DELETED`) VALUES ('0909123456', 'daonguyen123', 'daonguyen123@gmail.com', '$2a$10$Q1lkb6N6eS7VGcstaXsy1eVHRnXMY0SRqqOepSiHXR7iutXtEFPHq', '0');
INSERT INTO `shoppify_data`.`user` (`PHONE_NUMBER`, `USERNAME`, `EMAIL`, `PASSWORD`, `IS_DELETED`) VALUES ('0909778899', 'tiennguyen123', 'tiennguyen123@gmail.com', '$2a$10$UVtRMGvkXi6zpJ5j19Lv2eyYt78YHXjRfOxajy8v0TH8hYMAUIgvK', '0');


-- Insert User_Role
INSERT INTO `shoppify_data`.`user_role` (`USER_ID`, `ROLE_ID`) VALUES ('1', '1');
INSERT INTO `shoppify_data`.`user_role` (`USER_ID`, `ROLE_ID`) VALUES ('1', '2');
INSERT INTO `shoppify_data`.`user_role` (`USER_ID`, `ROLE_ID`) VALUES ('1', '3');
INSERT INTO `shoppify_data`.`user_role` (`USER_ID`, `ROLE_ID`) VALUES ('2', '1');
INSERT INTO `shoppify_data`.`user_role` (`USER_ID`, `ROLE_ID`) VALUES ('2', '2');
INSERT INTO `shoppify_data`.`user_role` (`USER_ID`, `ROLE_ID`) VALUES ('2', '3');
