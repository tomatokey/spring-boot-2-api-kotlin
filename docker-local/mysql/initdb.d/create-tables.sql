drop table if exists user_t;
create table if not exists user_t(
    `user_id` int not null auto_increment,
    `user_name` varchar(255) not null,
    primary key (user_id)
);

drop table if exists user_role_t;
create table if not exists user_role_t(
     `user_id` int not null,
     `role_type` varchar(255) not null,
     primary key (user_id, role_type)
--      unique ui_user_role_t_01 (user_id, role_type)
);