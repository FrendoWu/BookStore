CREATE TABLE `user_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) NOT NULL COMMENT '账户',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `adminlevel` int(11) NOT NULL COMMENT '权限',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账户表';

insert into `user_account` values (null, 'account1', 'password1','0');
insert into `user_account` values (null, 'account2', 'password2','1');

CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(255) NOT NULL COMMENT '类别',
  `name` varchar(255) NOT NULL COMMENT '书名',
  `introduction` varchar(255) NOT NULL COMMENT '书籍简介',
  `money` int(11) NOT NULL COMMENT '价格',
  `imgurl` varchar(255) NOT NULL COMMENT '书籍图片路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='书籍信息表';

insert into `book` values (null, '文学', '活着','这是《活着》用于测试的书籍简介','50','活着.jpg');
insert into `book` values (null, '生活', '宇宙的琴弦','这是《宇宙的琴弦》用于测试的书籍简介','50','宇宙的琴弦.jpg');

CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL COMMENT '用户ID',
  `books` varchar(255) NOT NULL COMMENT '购买的书籍',
  `allmoney` int(11) NOT NULL COMMENT '书本总价',
  `status` int(11) NOT NULL COMMENT '订单状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单信息表';

insert into `orders` values(null, '1', '12', '100', '1');
insert into `orders` values(null, '2', '13', '50', '0');

CREATE TABLE `trolley` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL COMMENT '用户ID',
  `bookid` int(11) NOT NULL COMMENT '书籍ID',
  `number` int(11) NOT NULL COMMENT '书籍数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='购物车表';

insert into `trolley` values(null, '1', '1','1');
insert into `trolley` values(null, '1', '2','2');
insert into `trolley` values(null, '2', '2','2');