# public_spring-angular-video_api

# Database - Videos API

```
DROP database IF EXISTS youtube;

CREATE database youtube;

USE youtube;

 #Database - Orders & Customer
 USE spring_angular_ecommerce_2;

 SET FOREIGN_KEY_CHECKS=0;
 DROP TABLE IF EXISTS order_item;
 DROP TABLE IF EXISTS orders;
 DROP TABLE IF EXISTS customer;
 DROP TABLE IF EXISTS address;
 SET FOREIGN_KEY_CHECKS=1;


 CREATE TABLE address (
   id bigint NOT NULL AUTO_INCREMENT,
   city varchar(255) DEFAULT NULL,
   country varchar(255) DEFAULT NULL,
   state varchar(255) DEFAULT NULL,
   street varchar(255) DEFAULT NULL,
   zip_code varchar(255) DEFAULT NULL,
   PRIMARY KEY (id)
 ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;


 CREATE TABLE customer (
   id bigint NOT NULL AUTO_INCREMENT,
   first_name varchar(255) DEFAULT NULL,
   last_name varchar(255) DEFAULT NULL,
   email varchar(255) DEFAULT NULL,
   PRIMARY KEY (id)
 ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;


 CREATE TABLE orders (
   id bigint NOT NULL AUTO_INCREMENT,
   order_tracking_number varchar(255) DEFAULT NULL,
   total_price decimal(19,2) DEFAULT NULL,
   total_quantity int DEFAULT NULL,
   billing_address_id bigint DEFAULT NULL,
   customer_id bigint DEFAULT NULL,
   shipping_address_id bigint DEFAULT NULL,
   status varchar(128) DEFAULT NULL,
   date_created datetime(6) DEFAULT NULL,
   last_updated datetime(6) DEFAULT NULL,
   PRIMARY KEY (id),
   UNIQUE KEY UK_billing_address_id (billing_address_id),
   UNIQUE KEY UK_shipping_address_id (shipping_address_id),
   KEY K_customer_id (customer_id),
   CONSTRAINT FK_customer_id FOREIGN KEY (customer_id) REFERENCES customer (id),
   CONSTRAINT FK_billing_address_id FOREIGN KEY (billing_address_id) REFERENCES address (id),
   CONSTRAINT FK_shipping_address_id FOREIGN KEY (shipping_address_id) REFERENCES address (id)
 ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;


 CREATE TABLE order_item (
   id bigint NOT NULL AUTO_INCREMENT,
   image_url varchar(255) DEFAULT NULL,
   quantity int DEFAULT NULL,
   unit_price decimal(19,2) DEFAULT NULL,
   order_id bigint DEFAULT NULL,
   product_id bigint DEFAULT NULL,
   PRIMARY KEY (id),
   KEY K_order_id (order_id),
   CONSTRAINT FK_order_id FOREIGN KEY (order_id) REFERENCES orders (id),
   CONSTRAINT FK_product_id FOREIGN KEY (product_id) REFERENCES product (id)
 ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;
 
#Database User, Role, Privilegeuse Video;
set foreign_key_checks = 0;

drop table if exists authority;
create table authority (
    id int(11) NOT NULL AUTO_INCREMENT,
    privilege varchar(250) NOT NULL,
    PRIMARY KEY (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
insert into authority(privilege) values ('user:read'),('user:update'),('user:create'),('user:delete');


drop table if exists role;
create table role (
    id int(11) NOT NULL AUTO_INCREMENT,
    name varchar(100) NOT NULL,
    PRIMARY KEY (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
    
insert into role(name) values ('ROLE_USER_READ'),('ROLE_USER_EDIT'),('ROLE_USER_CREATE'),('ROLE_USER_DELETE');


drop table if exists role_authority;
CREATE TABLE role_authority (
    role_id int(11) default null,
    authority_id int(11) default null,
    
    PRIMARY KEY (role_id, authority_id),
    KEY FK_USER_idx (role_id),
    
	CONSTRAINT FK_role_authority_01 FOREIGN KEY (role_id) REFERENCES role (id) on delete no action on update no action,
    CONSTRAINT FK_role_authority_02 FOREIGN KEY (authority_id) REFERENCES authority (id) on delete no action on update no action
) engine=InnoDB auto_increment=1 default charset=utf8mb4;

insert into role_authority(role_id,authority_id) values (1,1),(2,1),(2,2),(3,1),(3,2),(3,3),(4,1),(4,2),(4,3),(4,4);


drop table if exists user;
create table user (
    id int(11) NOT NULL AUTO_INCREMENT,
    user_id varchar(50) NOT NULL,
    first_name varchar(150) NOT NULL,
    last_name varchar(150) NOT NULL,
    username varchar(50) NOT NULL, 
    password varchar(150) NOT NULL,
    email varchar(250) NULL,
    profile_image_url varchar(1250) NULL,
    last_login_date DATETIME NULL,
    last_login_date_display DATETIME NULL,
    join_date DATETIME NULL,
    is_active tinyint(1) NOT NULL,
    is_not_locked tinyint(1) NOT NULL,
    PRIMARY KEY (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


drop table if exists user_role;
CREATE TABLE user_role (
    user_id int(11) default null,
    role_id int(11) default null,
    
    PRIMARY KEY (user_id, role_id),
    KEY FK_USER_idx (user_id),

	CONSTRAINT FK_user_role_01 FOREIGN KEY (user_id) REFERENCES user (id) on delete no action on update no action,
    CONSTRAINT FK_user_role_02 FOREIGN KEY (role_id) REFERENCES role (id) on delete no action on update no action
) engine=InnoDB auto_increment=1 default charset=utf8mb4;


drop table if exists user_authority;
CREATE TABLE user_authority (
    user_id int(11) default null,
    authority_id int(11) default null,
    
    PRIMARY KEY (user_id, authority_id),
    KEY FK_USER_idx (user_id),

	CONSTRAINT FK_user_authority_01 FOREIGN KEY (user_id) REFERENCES user (id) on delete no action on update no action,
    CONSTRAINT FK_user_authority_02 FOREIGN KEY (authority_id) REFERENCES authority (id) on delete no action on update no action
) engine=InnoDB auto_increment=1 default charset=utf8mb4;


set foreign_key_checks = 1;
