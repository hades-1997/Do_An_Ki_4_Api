use Youtube;
DROP TABLE IF EXISTS customer;
CREATE TABLE customer (
id bigint NOT NULL AUTO_INCREMENT,
first_name varchar(255) DEFAULT NULL,
last_name varchar(255) DEFAULT NULL,
email varchar(255) DEFAULT NULL,
PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

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
role_id int(11) not null,
authority_id int(11) not null,

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
user_id int(11) not null,
role_id int(11) not null,

    PRIMARY KEY (user_id, role_id),
    KEY FK_USER_idx (user_id),

	CONSTRAINT FK_user_role_01 FOREIGN KEY (user_id) REFERENCES user (id) on delete no action on update no action,
    CONSTRAINT FK_user_role_02 FOREIGN KEY (role_id) REFERENCES role (id) on delete no action on update no action
) engine=InnoDB auto_increment=1 default charset=utf8mb4;


drop table if exists user_authority;
CREATE TABLE user_authority (
user_id int(11) not null,
authority_id int(11) not null,

    PRIMARY KEY (user_id, authority_id),
    KEY FK_USER_idx (user_id),

	CONSTRAINT FK_user_authority_01 FOREIGN KEY (user_id) REFERENCES user (id) on delete no action on update no action,
    CONSTRAINT FK_user_authority_02 FOREIGN KEY (authority_id) REFERENCES authority (id) on delete no action on update no action
) engine=InnoDB auto_increment=1 default charset=utf8mb4;


DROP TABLE IF EXISTS videos_cat;
CREATE TABLE  videos_cat  (
catid  smallint(5) UNSIGNED NOT NULL,
title  varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL,
alias  varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
description  text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
image  varchar(250) COLLATE utf8mb4_unicode_ci DEFAULT '',
weight  smallint(5) UNSIGNED NOT NULL DEFAULT 0,
keywords  text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
add_time  date
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

DROP TABLE IF EXISTS videos_detail;
CREATE TABLE  videos_detail  (
id  int(11) UNSIGNED NOT NULL,
bodyhtml  longtext COLLATE utf8mb4_unicode_ci NOT NULL,
sourcetext  varchar(250) COLLATE utf8mb4_unicode_ci DEFAULT '',
copyright  tinyint(1) NOT NULL DEFAULT 0,
allowed_send  tinyint(1) NOT NULL DEFAULT 0,
allowed_save  tinyint(1) NOT NULL DEFAULT 0,
gid  mediumint(8) NOT NULL DEFAULT 0
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

DROP TABLE IF EXISTS videos_logs;
CREATE TABLE  videos_logs  (
id  mediumint(8) UNSIGNED NOT NULL,
sid  mediumint(8) NOT NULL DEFAULT 0,
user_id int(11) default null,
status  tinyint(4) NOT NULL DEFAULT 0,
note  varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL,
add_time  date
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
--
-- ket noi giua bang video playlist cat và video
--
DROP TABLE IF EXISTS videos_playlist;
CREATE TABLE  videos_playlist  (
playlist_id  smallint(5) UNSIGNED NOT NULL,
id  int(11) UNSIGNED NOT NULL,
playlist_sort  int(11) UNSIGNED NOT NULL COMMENT 'thứ tự hiển thị'
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

DROP TABLE IF EXISTS videos_playlist_cat;
CREATE TABLE  videos_playlist_cat  (
playlist_id  smallint(5) UNSIGNED NOT NULL,
user_id  int(11) UNSIGNED NOT NULL,
status  smallint(5) UNSIGNED NOT NULL DEFAULT 1,
private_mode  smallint(5) UNSIGNED NOT NULL DEFAULT 1,
numbers  smallint(5) NOT NULL DEFAULT 10,
title  varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
alias  varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
image  varchar(250) COLLATE utf8mb4_unicode_ci DEFAULT '',
description  varchar(250) COLLATE utf8mb4_unicode_ci DEFAULT '',
weight  smallint(5) NOT NULL DEFAULT 0,
keywords  text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
hitstotal  mediumint(8) UNSIGNED NOT NULL DEFAULT 0,
favorite  mediumint(8) UNSIGNED NOT NULL DEFAULT 0,
add_time  date
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

DROP TABLE IF EXISTS videos_rows;
CREATE TABLE  videos_rows  (
id  int(11) UNSIGNED NOT NULL,
catid  smallint(5) UNSIGNED NOT NULL DEFAULT 0,
author  varchar(250) COLLATE utf8mb4_unicode_ci DEFAULT '',
artist  varchar(250) COLLATE utf8mb4_unicode_ci DEFAULT '',
sourceid  mediumint(8) NOT NULL DEFAULT 0,
add_time  date,
status  tinyint(4) NOT NULL DEFAULT 1,
archive  tinyint(1) UNSIGNED NOT NULL DEFAULT 0,
title  varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
alias  varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
hometext  text COLLATE utf8mb4_unicode_ci NOT NULL,
vid_path  varchar(250) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT 'đường dẫn video',
vid_type  varchar(250) COLLATE utf8mb4_unicode_ci DEFAULT '',
vid_duration  varchar(250) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT 'số lượng phút',
homeimgfile  varchar(250) COLLATE utf8mb4_unicode_ci DEFAULT '',
homeimgalt  varchar(250) COLLATE utf8mb4_unicode_ci DEFAULT '',
allowed_comm  varchar(250) COLLATE utf8mb4_unicode_ci DEFAULT '',
allowed_rating  tinyint(1) UNSIGNED NOT NULL DEFAULT 0,
hitstotal  mediumint(8) UNSIGNED NOT NULL DEFAULT 0,
hitscm  mediumint(8) UNSIGNED NOT NULL DEFAULT 0,
total_rating  int(11) NOT NULL DEFAULT 0,
click_rating  int(11) NOT NULL DEFAULT 0
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


DROP TABLE IF EXISTS videos_rows_favourite;
CREATE TABLE  videos_rows_favourite  (
fid  smallint(5) UNSIGNED NOT NULL COMMENT 'user id',
id  int(11) UNSIGNED NOT NULL COMMENT 'video id'
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

DROP TABLE IF EXISTS videos_rows_report;
CREATE TABLE  videos_rows_report  (
rid  smallint(5) UNSIGNED NOT NULL,
id  int(11) UNSIGNED NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

DROP TABLE IF EXISTS videos_sources;
CREATE TABLE  videos_sources  (
sourceid  mediumint(8) UNSIGNED NOT NULL,
title  varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
link  varchar(250) COLLATE utf8mb4_unicode_ci DEFAULT '',
logo  varchar(250) COLLATE utf8mb4_unicode_ci DEFAULT '',
weight  mediumint(8) UNSIGNED NOT NULL DEFAULT 0,
add_time  date
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

DROP TABLE IF EXISTS videos_tags;
CREATE TABLE  videos_tags  (
tid  mediumint(8) UNSIGNED NOT NULL,
numnews  mediumint(8) NOT NULL DEFAULT 0,
alias  varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
image  varchar(250) COLLATE utf8mb4_unicode_ci DEFAULT '',
description  text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
keywords  varchar(250) COLLATE utf8mb4_unicode_ci DEFAULT ''
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

DROP TABLE IF EXISTS videos_tags_id;
CREATE TABLE  videos_tags_id  (
id  int(11) NOT NULL,
tid  mediumint(9) NOT NULL,
keyword  varchar(65) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

DROP TABLE IF EXISTS videos_orders;
CREATE TABLE  videos_orders  (
id  int(11) NOT NULL,
user_id  int(11) NOT NULL,
transactions_id  int(11) NOT NULL,
playlist_id smallint(5) NOT NULL,
price decimal(10,2) NOT NULL,
add_time  date
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

DROP TABLE IF EXISTS videos_transactions;
CREATE TABLE  videos_transactions  (
transactions_id  int(11) NOT NULL,
user_id  int(11) NOT NULL,
playlist_id  int(11) NOT NULL,
phone varchar(15),
status  tinyint(4) NOT NULL DEFAULT 1
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

ALTER TABLE  videos_cat
ADD PRIMARY KEY ( catid ),
ADD UNIQUE KEY  alias  ( alias );

ALTER TABLE  videos_detail
ADD PRIMARY KEY ( id );

ALTER TABLE  videos_logs
ADD PRIMARY KEY ( id ),
ADD KEY  sid  ( sid ),
ADD KEY  user_id  ( user_id );

ALTER TABLE  videos_playlist
ADD UNIQUE KEY  playlist_id  ( playlist_id , id );

ALTER TABLE  videos_playlist_cat
ADD PRIMARY KEY ( playlist_id ),
ADD UNIQUE KEY  title  ( title ),
ADD UNIQUE KEY  alias  ( alias );

ALTER TABLE  videos_rows
ADD PRIMARY KEY ( id ),
ADD KEY  catid  ( catid ),
ADD KEY  author  ( author ),
ADD KEY  title  ( title ),
ADD KEY  status  ( status );

ALTER TABLE  videos_rows_favourite
ADD UNIQUE KEY  fid  ( fid , id );
ALTER TABLE  videos_rows_report
ADD UNIQUE KEY  rid  ( rid , id );
ALTER TABLE  videos_sources
ADD PRIMARY KEY ( sourceid ),
ADD UNIQUE KEY  title  ( title );
ALTER TABLE  videos_tags
ADD PRIMARY KEY ( tid ),
ADD UNIQUE KEY  alias  ( alias );
ALTER TABLE  videos_tags_id
ADD UNIQUE KEY  sid  ( id , tid );

ALTER TABLE  videos_cat
MODIFY  catid  smallint(5) UNSIGNED NOT NULL AUTO_INCREMENT;
ALTER TABLE  videos_logs
MODIFY  id  mediumint(8) UNSIGNED NOT NULL AUTO_INCREMENT;
ALTER TABLE  videos_playlist_cat
MODIFY  playlist_id  smallint(5) UNSIGNED NOT NULL AUTO_INCREMENT;
ALTER TABLE  videos_rows
MODIFY  id  int(11) UNSIGNED NOT NULL AUTO_INCREMENT;
ALTER TABLE  videos_tags
MODIFY  tid  mediumint(8) UNSIGNED NOT NULL AUTO_INCREMENT;

ALTER TABLE  videos_orders
ADD PRIMARY KEY ( id ),
ADD UNIQUE KEY  user_id  ( user_id ),
ADD UNIQUE KEY  transactions_id(transactions_id),
ADD  KEY  playlist_id (playlist_id);

ALTER TABLE  videos_transactions
ADD PRIMARY KEY ( transactions_id ),
ADD UNIQUE KEY  user_id  ( user_id ),
ADD KEY  playlist_id  ( playlist_id );

ALTER TABLE  videos_orders
MODIFY  id  int NOT NULL AUTO_INCREMENT;  
COMMIT;use Youtube;
DROP TABLE IF EXISTS customer;
CREATE TABLE customer (
id bigint NOT NULL AUTO_INCREMENT,
first_name varchar(255) DEFAULT NULL,
last_name varchar(255) DEFAULT NULL,
email varchar(255) DEFAULT NULL,
PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

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
role_id int(11) not null,
authority_id int(11) not null,

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
user_id int(11) not null,
role_id int(11) not null,

    PRIMARY KEY (user_id, role_id),
    KEY FK_USER_idx (user_id),

	CONSTRAINT FK_user_role_01 FOREIGN KEY (user_id) REFERENCES user (id) on delete no action on update no action,
    CONSTRAINT FK_user_role_02 FOREIGN KEY (role_id) REFERENCES role (id) on delete no action on update no action
) engine=InnoDB auto_increment=1 default charset=utf8mb4;


drop table if exists user_authority;
CREATE TABLE user_authority (
user_id int(11) not null,
authority_id int(11) not null,

    PRIMARY KEY (user_id, authority_id),
    KEY FK_USER_idx (user_id),

	CONSTRAINT FK_user_authority_01 FOREIGN KEY (user_id) REFERENCES user (id) on delete no action on update no action,
    CONSTRAINT FK_user_authority_02 FOREIGN KEY (authority_id) REFERENCES authority (id) on delete no action on update no action
) engine=InnoDB auto_increment=1 default charset=utf8mb4;


DROP TABLE IF EXISTS videos_cat;
CREATE TABLE  videos_cat  (
catid  smallint(5) UNSIGNED NOT NULL,
title  varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL,
alias  varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
description  text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
image  varchar(250) COLLATE utf8mb4_unicode_ci DEFAULT '',
weight  smallint(5) UNSIGNED NOT NULL DEFAULT 0,
keywords  text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
add_time  date
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

DROP TABLE IF EXISTS videos_detail;
CREATE TABLE  videos_detail  (
id  int(11) UNSIGNED NOT NULL,
bodyhtml  longtext COLLATE utf8mb4_unicode_ci NOT NULL,
sourcetext  varchar(250) COLLATE utf8mb4_unicode_ci DEFAULT '',
copyright  tinyint(1) NOT NULL DEFAULT 0,
allowed_send  tinyint(1) NOT NULL DEFAULT 0,
allowed_save  tinyint(1) NOT NULL DEFAULT 0,
gid  mediumint(8) NOT NULL DEFAULT 0
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

DROP TABLE IF EXISTS videos_logs;
CREATE TABLE  videos_logs  (
id  mediumint(8) UNSIGNED NOT NULL,
sid  mediumint(8) NOT NULL DEFAULT 0,
user_id int(11) default null,
status  tinyint(4) NOT NULL DEFAULT 0,
note  varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL,
add_time  date
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
--
-- ket noi giua bang video playlist cat và video
--
DROP TABLE IF EXISTS videos_playlist;
CREATE TABLE  videos_playlist  (
playlist_id  smallint(5) UNSIGNED NOT NULL,
id  int(11) UNSIGNED NOT NULL,
playlist_sort  int(11) UNSIGNED NOT NULL COMMENT 'thứ tự hiển thị'
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

DROP TABLE IF EXISTS videos_playlist_cat;
CREATE TABLE  videos_playlist_cat  (
playlist_id  smallint(5) UNSIGNED NOT NULL,
user_id  int(11) UNSIGNED NOT NULL,
status  smallint(5) UNSIGNED NOT NULL DEFAULT 1,
private_mode  smallint(5) UNSIGNED NOT NULL DEFAULT 1,
numbers  smallint(5) NOT NULL DEFAULT 10,
title  varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
alias  varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
image  varchar(250) COLLATE utf8mb4_unicode_ci DEFAULT '',
description  varchar(250) COLLATE utf8mb4_unicode_ci DEFAULT '',
weight  smallint(5) NOT NULL DEFAULT 0,
keywords  text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
hitstotal  mediumint(8) UNSIGNED NOT NULL DEFAULT 0,
favorite  mediumint(8) UNSIGNED NOT NULL DEFAULT 0,
add_time  date
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

DROP TABLE IF EXISTS videos_rows;
CREATE TABLE  videos_rows  (
id  int(11) UNSIGNED NOT NULL,
catid  smallint(5) UNSIGNED NOT NULL DEFAULT 0,
author  varchar(250) COLLATE utf8mb4_unicode_ci DEFAULT '',
artist  varchar(250) COLLATE utf8mb4_unicode_ci DEFAULT '',
sourceid  mediumint(8) NOT NULL DEFAULT 0,
add_time  date,
status  tinyint(4) NOT NULL DEFAULT 1,
archive  tinyint(1) UNSIGNED NOT NULL DEFAULT 0,
title  varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
alias  varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
hometext  text COLLATE utf8mb4_unicode_ci NOT NULL,
vid_path  varchar(250) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT 'đường dẫn video',
vid_type  varchar(250) COLLATE utf8mb4_unicode_ci DEFAULT '',
vid_duration  varchar(250) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT 'số lượng phút',
homeimgfile  varchar(250) COLLATE utf8mb4_unicode_ci DEFAULT '',
homeimgalt  varchar(250) COLLATE utf8mb4_unicode_ci DEFAULT '',
allowed_comm  varchar(250) COLLATE utf8mb4_unicode_ci DEFAULT '',
allowed_rating  tinyint(1) UNSIGNED NOT NULL DEFAULT 0,
hitstotal  mediumint(8) UNSIGNED NOT NULL DEFAULT 0,
hitscm  mediumint(8) UNSIGNED NOT NULL DEFAULT 0,
total_rating  int(11) NOT NULL DEFAULT 0,
click_rating  int(11) NOT NULL DEFAULT 0
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


DROP TABLE IF EXISTS videos_rows_favourite;
CREATE TABLE  videos_rows_favourite  (
fid  smallint(5) UNSIGNED NOT NULL COMMENT 'user id',
id  int(11) UNSIGNED NOT NULL COMMENT 'video id'
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

DROP TABLE IF EXISTS videos_rows_report;
CREATE TABLE  videos_rows_report  (
rid  smallint(5) UNSIGNED NOT NULL,
id  int(11) UNSIGNED NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

DROP TABLE IF EXISTS videos_sources;
CREATE TABLE  videos_sources  (
sourceid  mediumint(8) UNSIGNED NOT NULL,
title  varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
link  varchar(250) COLLATE utf8mb4_unicode_ci DEFAULT '',
logo  varchar(250) COLLATE utf8mb4_unicode_ci DEFAULT '',
weight  mediumint(8) UNSIGNED NOT NULL DEFAULT 0,
add_time  date
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

DROP TABLE IF EXISTS videos_tags;
CREATE TABLE  videos_tags  (
tid  mediumint(8) UNSIGNED NOT NULL,
numnews  mediumint(8) NOT NULL DEFAULT 0,
alias  varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
image  varchar(250) COLLATE utf8mb4_unicode_ci DEFAULT '',
description  text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
keywords  varchar(250) COLLATE utf8mb4_unicode_ci DEFAULT ''
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

DROP TABLE IF EXISTS videos_tags_id;
CREATE TABLE  videos_tags_id  (
id  int(11) NOT NULL,
tid  mediumint(9) NOT NULL,
keyword  varchar(65) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

DROP TABLE IF EXISTS videos_orders;
CREATE TABLE  videos_orders  (
id  int(11) NOT NULL,
user_id  int(11) NOT NULL,
transactions_id  int(11) NOT NULL,
playlist_id smallint(5) NOT NULL,
price decimal(10,2) NOT NULL,
add_time  date
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

DROP TABLE IF EXISTS videos_transactions;
CREATE TABLE  videos_transactions  (
transactions_id  int(11) NOT NULL,
user_id  int(11) NOT NULL,
playlist_id  int(11) NOT NULL,
phone varchar(15),
status  tinyint(4) NOT NULL DEFAULT 1
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

ALTER TABLE  videos_cat
ADD PRIMARY KEY ( catid ),
ADD UNIQUE KEY  alias  ( alias );

ALTER TABLE  videos_detail
ADD PRIMARY KEY ( id );

ALTER TABLE  videos_logs
ADD PRIMARY KEY ( id ),
ADD KEY  sid  ( sid ),
ADD KEY  user_id  ( user_id );

ALTER TABLE  videos_playlist
ADD UNIQUE KEY  playlist_id  ( playlist_id , id );

ALTER TABLE  videos_playlist_cat
ADD PRIMARY KEY ( playlist_id ),
ADD UNIQUE KEY  title  ( title ),
ADD UNIQUE KEY  alias  ( alias );

ALTER TABLE  videos_rows
ADD PRIMARY KEY ( id ),
ADD KEY  catid  ( catid ),
ADD KEY  author  ( author ),
ADD KEY  title  ( title ),
ADD KEY  status  ( status );

ALTER TABLE  videos_rows_favourite
ADD UNIQUE KEY  fid  ( fid , id );
ALTER TABLE  videos_rows_report
ADD UNIQUE KEY  rid  ( rid , id );
ALTER TABLE  videos_sources
ADD PRIMARY KEY ( sourceid ),
ADD UNIQUE KEY  title  ( title );
ALTER TABLE  videos_tags
ADD PRIMARY KEY ( tid ),
ADD UNIQUE KEY  alias  ( alias );
ALTER TABLE  videos_tags_id
ADD UNIQUE KEY  sid  ( id , tid );

ALTER TABLE  videos_cat
MODIFY  catid  smallint(5) UNSIGNED NOT NULL AUTO_INCREMENT;
ALTER TABLE  videos_logs
MODIFY  id  mediumint(8) UNSIGNED NOT NULL AUTO_INCREMENT;
ALTER TABLE  videos_playlist_cat
MODIFY  playlist_id  smallint(5) UNSIGNED NOT NULL AUTO_INCREMENT;
ALTER TABLE  videos_rows
MODIFY  id  int(11) UNSIGNED NOT NULL AUTO_INCREMENT;
ALTER TABLE  videos_tags
MODIFY  tid  mediumint(8) UNSIGNED NOT NULL AUTO_INCREMENT;

ALTER TABLE  videos_orders
ADD PRIMARY KEY ( id ),
ADD UNIQUE KEY  user_id  ( user_id ),
ADD UNIQUE KEY  transactions_id(transactions_id),
ADD  KEY  playlist_id (playlist_id);

ALTER TABLE  videos_transactions
ADD PRIMARY KEY ( transactions_id ),
ADD UNIQUE KEY  user_id  ( user_id ),
ADD KEY  playlist_id  ( playlist_id );

ALTER TABLE  videos_orders
MODIFY  id  int NOT NULL AUTO_INCREMENT;  
COMMIT;