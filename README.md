# public_spring-angular-video_api

# Database - Videos API

	DROP database IF EXISTS youtube;

	CREATE database youtube;

	USE youtube;
	DROP TABLE IF EXISTS authority;
	DROP TABLE IF EXISTS customer;
	DROP TABLE IF EXISTS orders;
	DROP TABLE IF EXISTS order_item;
	DROP TABLE IF EXISTS role;
	DROP TABLE IF EXISTS users;
	DROP TABLE IF EXISTS videos_cat;
	DROP TABLE IF EXISTS videos_playlist;
	DROP TABLE IF EXISTS videos_rows;
	DROP TABLE IF EXISTS videos_playlist_cat;
	DROP TABLE IF EXISTS videos_transiction;
	--
	-- Dumping data for table `authority`
	--
CREATE TABLE `authority` (
  `id` int(11) NOT NULL,
  `privilege` varchar(250) COLLATE utf8mb4_vietnamese_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

	--
	-- Dumping data for table `authority`
	--

INSERT INTO `authority` (`id`, `privilege`) VALUES
(1, 'user:read'),
(2, 'user:update'),
(3, 'user:create'),
(4, 'user:delete');

-- --------------------------------------------------------

	--
	-- Table structure for table `customer`
	--

CREATE TABLE `customer` (
  `id` bigint(20) NOT NULL,
  `first_name` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `last_name` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

-- --------------------------------------------------------

	--
	-- Table structure for table `orders`
	--

CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL,
  `order_tracking_number` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `total_price` decimal(19,2) DEFAULT NULL,
  `billing_address_id` bigint(20) DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  `status` varchar(128) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `date_created` datetime(6) DEFAULT NULL,
  `last_updated` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

-- --------------------------------------------------------

	--
	-- Table structure for table `order_item`
	--

CREATE TABLE `order_item` (
  `id` bigint(20) NOT NULL,
  `image_url` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `unit_price` decimal(19,2) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `playlist_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

-- --------------------------------------------------------

	--
	-- Table structure for table `role`
	--

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `name` varchar(100) COLLATE utf8mb4_vietnamese_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

	--
	-- Dumping data for table `role`
	--

INSERT INTO `role` (`id`, `name`) VALUES
(1, 'ROLE_USER_READ'),
(2, 'ROLE_USER_EDIT'),
(3, 'ROLE_USER_CREATE'),
(4, 'ROLE_USER_DELETE');

-- --------------------------------------------------------

	--
	-- Table structure for table `role_authority`
	--

CREATE TABLE `role_authority` (
  `role_id` int(11) NOT NULL,
  `authority_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

	--
	-- Dumping data for table `role_authority`
	--

INSERT INTO `role_authority` (`role_id`, `authority_id`) VALUES
(1, 1),
(2, 1),
(2, 2),
(3, 1),
(3, 2),
(3, 3),
(4, 1),
(4, 2),
(4, 3),
(4, 4);

-- --------------------------------------------------------

	--
	-- Table structure for table `users`
	--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `user_id` varchar(50) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `first_name` varchar(150) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `last_name` varchar(150) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `username` varchar(50) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `password` varchar(150) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `email` varchar(250) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `profile_image_url` varchar(1250) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `last_login_date` datetime DEFAULT NULL,
  `last_login_date_display` datetime DEFAULT NULL,
  `join_date` datetime DEFAULT NULL,
  `is_active` tinyint(1) NOT NULL,
  `is_not_locked` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

	--
	-- Dumping data for table `users`
	--

INSERT INTO `users` (`id`, `user_id`, `first_name`, `last_name`, `username`, `password`, `email`, `profile_image_url`, `last_login_date`, `last_login_date_display`, `join_date`, `is_active`, `is_not_locked`) VALUES
(1, '1048752279', 'loi', 'dac', 'test', '$2a$10$Jzh4rDqCJbErWzIxFeR4N.HAtLmvrcuPCG4nuLV4KEq4OrPsJsQ2G', 'tomy@gmail.com', 'http://localhost:8080/api/user/image/profile/test', '2022-02-07 11:05:57', NULL, '2022-02-07 11:00:06', 1, 1);

-- --------------------------------------------------------

	--
	-- Table structure for table `user_authority`
	--

CREATE TABLE `user_authority` (
  `user_id` int(11) NOT NULL,
  `authority_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

	--
	-- Dumping data for table `user_authority`
	--

INSERT INTO `user_authority` (`user_id`, `authority_id`) VALUES
(1, 1);

-- --------------------------------------------------------

	--
	-- Table structure for table `user_role`
	--

CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

	--
	-- Dumping data for table `user_role`
	--

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
(1, 1);

-- --------------------------------------------------------

	--
	-- Table structure for table `videos_cat`
	--

CREATE TABLE `videos_cat` (
  `id` int(11) NOT NULL,
  `title` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `alias` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `image` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `weight` smallint(5) UNSIGNED NOT NULL DEFAULT 0,
  `keywords` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `add_time` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

	--
	-- Dumping data for table `videos_cat`
	--

INSERT INTO `videos_cat` (`id`, `title`, `alias`, `description`, `image`, `weight`, `keywords`, `add_time`) VALUES
(1, 'Thời sự', 'thoi-su', 'Phim thời sự', 'http://localhost:8080/api/user/image/Th%E1%BB%9Di%20s%E1%BB%B1/Th%E1%BB%9Di%20s%E1%BB%B1.jpg', 1, NULL, '2022-02-07');

-- --------------------------------------------------------

	--
	-- Table structure for table `videos_logs`
	--

CREATE TABLE `videos_logs` (
  `id` int(11) NOT NULL,
  `sid` mediumint(9) NOT NULL DEFAULT 0,
  `user_id` int(11) DEFAULT NULL,
  `status` tinyint(4) NOT NULL DEFAULT 0,
  `note` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `add_time` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

-- --------------------------------------------------------

	--
	-- Table structure for table `videos_playlist`
	--

CREATE TABLE `videos_playlist` (
  `video_id` int(11) NOT NULL,
  `playlist_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

-- --------------------------------------------------------

	--
	-- Table structure for table `videos_playlist_cat`
	--

CREATE TABLE `videos_playlist_cat` (
  `id` int(11) NOT NULL,
  `status` smallint(5) UNSIGNED NOT NULL DEFAULT 1,
  `private_mode` smallint(5) UNSIGNED NOT NULL DEFAULT 1,
  `numbers` smallint(6) NOT NULL DEFAULT 10,
  `price` decimal(19,2) DEFAULT 0.00,
  `title` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `alias` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `image` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `description` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `weight` smallint(6) NOT NULL DEFAULT 0,
  `keywords` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `hitstotal` mediumint(8) UNSIGNED NOT NULL DEFAULT 0,
  `favorite` mediumint(8) UNSIGNED NOT NULL DEFAULT 0,
  `add_time` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

	--
	-- Dumping data for table `videos_playlist_cat`
	--

INSERT INTO `videos_playlist_cat` (`id`, `status`, `private_mode`, `numbers`, `price`, `title`, `alias`, `image`, `description`, `weight`, `keywords`, `hitstotal`, `favorite`, `add_time`) VALUES
(2, 1, 1, 1, '0.00', 'Hót Tháng', 'hot-thang', 'http://localhost:8080/api/video/image/H%C3%B3t%20Th%C3%A1ng', 'Tuyển tập video nhiều lượt xem nhất tháng', 1, 'hót tháng', 1, 1, '2022-02-07');

-- --------------------------------------------------------

	--
	-- Table structure for table `videos_rows`
	--

CREATE TABLE `videos_rows` (
  `id` int(11) NOT NULL,
  `catid` int(11) NOT NULL,
  `author` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `artist` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `sourceid` mediumint(9) NOT NULL DEFAULT 0,
  `add_time` date DEFAULT NULL,
  `status` tinyint(4) NOT NULL DEFAULT 1,
  `archive` tinyint(3) UNSIGNED NOT NULL DEFAULT 0,
  `title` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `alias` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `hometext` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `vid_path` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT 'đường dẫn video',
  `vid_type` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `vid_duration` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT 'số lượng phút',
  `homeimgfile` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `homeimgalt` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `allowed_comm` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `allowed_rating` tinyint(3) UNSIGNED NOT NULL DEFAULT 0,
  `hitstotal` mediumint(8) UNSIGNED NOT NULL DEFAULT 0,
  `hitscm` mediumint(8) UNSIGNED NOT NULL DEFAULT 0,
  `total_rating` int(11) NOT NULL DEFAULT 0,
  `click_rating` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

	--
	-- Dumping data for table `videos_rows`
	--

INSERT INTO `videos_rows` (`id`, `catid`, `author`, `artist`, `sourceid`, `add_time`, `status`, `archive`, `title`, `alias`, `hometext`, `vid_path`, `vid_type`, `vid_duration`, `homeimgfile`, `homeimgalt`, `allowed_comm`, `allowed_rating`, `hitstotal`, `hitscm`, `total_rating`, `click_rating`) VALUES
(4, 1, 'admin', 'sontungmtp', 1, '2022-02-07', 0, 1, 'Em cua ngay hom qua', 'em-cua-ngay-hom-qua', 'Em cua ngay hom qua', 'https://www.youtube.com/watch?v=ltSJi2lJ6x4', '0', '0', 'http://localhost:8080/api/video/image/Em%20cua%20ngay%20hom%20qua', 'anh dai dien', '0', 0, 0, 0, 0, 0);

-- --------------------------------------------------------

	--
	-- Table structure for table `videos_rows_favourite`
	--

CREATE TABLE `videos_rows_favourite` (
  `user_id` int(11) NOT NULL COMMENT 'user id',
  `video_id` int(11) NOT NULL COMMENT 'video id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

-- --------------------------------------------------------

	--
	-- Table structure for table `videos_rows_report`
	--

CREATE TABLE `videos_rows_report` (
  `user_id` int(11) NOT NULL,
  `id` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

-- --------------------------------------------------------

	--
	-- Table structure for table `videos_sources`
	--

CREATE TABLE `videos_sources` (
  `id` int(11) NOT NULL,
  `title` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `link` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `logo` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `weight` mediumint(8) UNSIGNED NOT NULL DEFAULT 0,
  `add_time` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

-- --------------------------------------------------------

	--
	-- Table structure for table `videos_tags`
	--

CREATE TABLE `videos_tags` (
  `id` int(11) NOT NULL,
  `numnews` mediumint(9) NOT NULL DEFAULT 0,
  `alias` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `image` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `keywords` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

-- --------------------------------------------------------

	--
	-- Table structure for table `videos_tags_id`
	--

CREATE TABLE `videos_tags_id` (
  `id` int(11) NOT NULL,
  `tid` mediumint(9) NOT NULL,
  `keyword` varchar(65) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

	--
	-- Indexes for dumped tables
	--

	--
	-- Indexes for table `authority`
	--
ALTER TABLE `authority`
  ADD PRIMARY KEY (`id`);

	--
	-- Indexes for table `customer`
	--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

	--
	-- Indexes for table `orders`
	--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_billing_address_id` (`billing_address_id`),
  ADD KEY `K_customer_id` (`customer_id`);

	--
	-- Indexes for table `order_item`
	--
ALTER TABLE `order_item`
  ADD PRIMARY KEY (`id`),
  ADD KEY `K_order_id` (`order_id`),
  ADD KEY `playlist_id` (`playlist_id`);

	--
	-- Indexes for table `role`
	--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

	--
	-- Indexes for table `role_authority`
	--
ALTER TABLE `role_authority`
  ADD PRIMARY KEY (`role_id`,`authority_id`),
  ADD KEY `FK_USER_idx` (`role_id`),
  ADD KEY `authority_id` (`authority_id`);

	--
	-- Indexes for table `users`
	--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

	--
	-- Indexes for table `user_authority`
	--
ALTER TABLE `user_authority`
  ADD PRIMARY KEY (`user_id`,`authority_id`),
  ADD KEY `FK_USER_idx` (`user_id`),
  ADD KEY `authority_id` (`authority_id`);

	--
	-- Indexes for table `user_role`
	--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`user_id`,`role_id`),
  ADD KEY `FK_USER_idx` (`user_id`),
  ADD KEY `role_id` (`role_id`);

	--
	-- Indexes for table `videos_cat`
	--
ALTER TABLE `videos_cat`
  ADD PRIMARY KEY (`id`);

	--
	-- Indexes for table `videos_logs`
	--
ALTER TABLE `videos_logs`
  ADD PRIMARY KEY (`id`);

	--
	-- Indexes for table `videos_playlist`
	--
ALTER TABLE `videos_playlist`
  ADD PRIMARY KEY (`video_id`,`playlist_id`),
  ADD KEY `FK_VIDEO_idx` (`video_id`),
  ADD KEY `FK_videos_playlist_02` (`playlist_id`);

	--
	-- Indexes for table `videos_playlist_cat`
	--
ALTER TABLE `videos_playlist_cat`
  ADD PRIMARY KEY (`id`);

	--
	-- Indexes for table `videos_rows`
	--
ALTER TABLE `videos_rows`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `idx_videos_rows_alias` (`alias`),
  ADD KEY `FK_videos_rows_01` (`catid`);

	--
	-- Indexes for table `videos_rows_favourite`
	--
ALTER TABLE `videos_rows_favourite`
  ADD PRIMARY KEY (`user_id`,`video_id`),
  ADD KEY `FK_USER_idx` (`user_id`),
  ADD KEY `FK_videos_rows_favourite_02` (`video_id`);

	--
	-- Indexes for table `videos_sources`
	--
ALTER TABLE `videos_sources`
  ADD PRIMARY KEY (`id`);

	--
	-- Indexes for table `videos_tags`
	--
ALTER TABLE `videos_tags`
  ADD PRIMARY KEY (`id`);

	--
	-- AUTO_INCREMENT for dumped tables
	--

	--
	-- AUTO_INCREMENT for table `authority`
	--
ALTER TABLE `authority`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

	--
	-- AUTO_INCREMENT for table `customer`
	--
ALTER TABLE `customer`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

	--
	-- AUTO_INCREMENT for table `orders`
	--
ALTER TABLE `orders`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

	--
	-- AUTO_INCREMENT for table `order_item`
	--
ALTER TABLE `order_item`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

	--
	-- AUTO_INCREMENT for table `role`
	--
ALTER TABLE `role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

	--
	-- AUTO_INCREMENT for table `users`
	--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

	--
	-- AUTO_INCREMENT for table `videos_cat`
	--
ALTER TABLE `videos_cat`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

	--
	-- AUTO_INCREMENT for table `videos_logs`
	--
ALTER TABLE `videos_logs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

		--
		-- AUTO_INCREMENT for table `videos_playlist_cat`
		--
ALTER TABLE `videos_playlist_cat`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

	--
	-- AUTO_INCREMENT for table `videos_rows`
	--
ALTER TABLE `videos_rows`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

	--
	-- AUTO_INCREMENT for table `videos_sources`
	--
ALTER TABLE `videos_sources`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

	--
	-- AUTO_INCREMENT for table `videos_tags`
	--
ALTER TABLE `videos_tags`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

	--
	-- Constraints for dumped tables
	--

	--
	-- Constraints for table `orders`
	--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`id`) REFERENCES `order_item` (`order_id`) ON DELETE CASCADE ON UPDATE CASCADE;

	--
	-- Constraints for table `order_item`
	--
ALTER TABLE `order_item`
  ADD CONSTRAINT `order_item_ibfk_1` FOREIGN KEY (`playlist_id`) REFERENCES `videos_playlist_cat` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

	--
	-- Constraints for table `role_authority`
	--
ALTER TABLE `role_authority`
  ADD CONSTRAINT `role_authority_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `role_authority_ibfk_2` FOREIGN KEY (`authority_id`) REFERENCES `authority` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

	--
	-- Constraints for table `user_authority`
	--
ALTER TABLE `user_authority`
  ADD CONSTRAINT `user_authority_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `user_authority_ibfk_2` FOREIGN KEY (`authority_id`) REFERENCES `authority` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

	--
	-- Constraints for table `user_role`
	--
ALTER TABLE `user_role`
  ADD CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

	--
	-- Constraints for table `videos_playlist`
	--
ALTER TABLE `videos_playlist`
  ADD CONSTRAINT `videos_playlist_ibfk_1` FOREIGN KEY (`video_id`) REFERENCES `videos_rows` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `videos_playlist_ibfk_2` FOREIGN KEY (`playlist_id`) REFERENCES `videos_playlist_cat` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

	--
	-- Constraints for table `videos_rows`
	--
ALTER TABLE `videos_rows`
  ADD CONSTRAINT `videos_rows_ibfk_3` FOREIGN KEY (`catid`) REFERENCES `videos_cat` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;
