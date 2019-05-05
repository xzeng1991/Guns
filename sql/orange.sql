USE guns;

DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `avatar` varchar(64) COMMENT '作者头像',
  `author` varchar(64) COMMENT '作者',
  `title` varchar(128)  COMMENT '标题',
  `img` varchar(64) COMMENT '图片',
  `brife` varchar(255) COMMENT '简介',
  `content` text COMMENT '内容',
  `collect_num` int(11) DEFAULT 0 COMMENT '收藏数',
  `read_num` int(11) DEFAULT 0 COMMENT '阅读数',
  `comment_num` int(11) DEFAULT 0 COMMENT '评论数',
  `up_num` int(11) DEFAULT 0 COMMENT '点赞数',
  `create_time` datetime DEFAULT current_timestamp COMMENT '创建时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章表' ROW_FORMAT = Dynamic;

INSERT INTO `article` VALUES (1, '/images/avatar/avatar-5.png', '林白衣', '小时候的冰棍儿与雪糕', '/images/article/post-4.jpg', '冰棍与雪糕绝对不是同一个东西。3到5毛钱的雪糕犹如现在的哈根达斯，而5分1毛的冰棍儿就像现在的老冰棒。时过境迁...', '冰棍与雪糕绝对不是同一个东西。3到5毛钱的雪糕犹如现在的哈根达斯，而5分1毛的 冰棍儿就像现在的老冰棒。时过境迁，当年的老冰棍也随着童年的记忆消失不见踪影。记得小时候，每当傍晚时分，总有一个老人推着一辆小车，小车的后架上放着一个大大的白色泡沫盒子。老人一边推着车，一边叫喊着：雪糕、冰棍...', 0, 0, 0, 0, current_timestamp);
INSERT INTO `article` VALUES (2, '/images/avatar/avatar-1.png', '林黑衣', '从童年呼啸而过的火车', '/images/article/post-5.jpg', '小时候，家的后面有一条铁路。听说从南方北上的火车都必须经过这条铁路。火车大多在晚上经过，但也不定是只有在夜深人静的时候，火车的声音才能从远方传来...', '小时候，家的后面有一条铁路。听说从南方北上的火车都必须经过这条铁路。火车大多在晚上经过，可呜呜的汽笛声，往往却被淹没在傍晚小院儿里散步的人群声中。只有在夜深人静的时候，火车的声音才能清晰的从远处飘过来。虽然日日听见火车的汽笛声，可说也奇怪，我竟从来不知道铁路在哪里。在每个夏日午后，我都会有一种去找寻找铁路的冲动，去看看这条铁路究竟是从哪里来，又将通向哪里去', 0, 0, 0, 0, current_timestamp);
INSERT INTO `article` VALUES (3, '/images/avatar/avatar-3.png', '林黑衣', '记忆里的春节', '/images/article/post-1.jpg', '年少时，有几样东西，是春节里必不可少的：烟花、新衣、凉菜、压岁钱、饺子。年分大小年，有的地方是腊月二十三过小年，有的地方是腊月二十四...', '年少时，有几样东西，是春节里必不可少的：烟花、新衣、凉菜、压岁钱、饺子。年分大小年，有的地方是腊月二十三过小年，而有的地方是腊月二十四。童年的春节都是在小县城里度过，那时候的冬天还很冷，池塘的水会结冰，房屋上总是倒挂着一条条的冰棱，菜地里的白菜被厚厚的积雪覆盖着，只露出一小撮白绿相间的菜头，而茎部，竟然像是没有了一般...', 0, 0, 0, 0, current_timestamp);

DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `article_id` bigint(20) COMMENT '文章ID',
  `avatar` varchar(64) COMMENT '用户头像',
  `name` varchar(64) COMMENT '用户名称',
  `txt` text  COMMENT '文字评论',
  `img` varchar(512) COMMENT '评论图片',
  `audio` varchar(512) COMMENT '语音评论',
  `create_time` datetime DEFAULT current_timestamp COMMENT '创建时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '评论表' ROW_FORMAT = Dynamic;

INSERT INTO `comment` VALUES (1, 1, '/images/avatar/avatar-3.png', '青石', '那一年的毕业季，我们挥挥手，来不及说再见，就踏上了远行的火车。', '/images/comment/train-1.jpg,/images/comment/train-2.jpg,/images/comment/train-3.jpg', '', current_timestamp);
INSERT INTO `comment` VALUES (2, 1, '/images/avatar/avatar-2.png', '水清', '夏日的蝉鸣与夜晚的火车，时长会在未来无数的日子里不断的在我耳边响起，难以忘怀', '', '', current_timestamp);
INSERT INTO `comment` VALUES (3, 1, '/images/avatar/avatar-1.png', '赤墨', '时光的湮染，自然的吞噬，让太多的老火车站也消失得无影无踪', '/images/comment/train-4.jpg', '', current_timestamp);
INSERT INTO `comment` VALUES (4, 1, '/images/avatar/avatar-4.png', '林白', '', '', '{ url: "http://123", timeLen: 8 }', current_timestamp);
