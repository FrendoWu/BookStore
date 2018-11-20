CREATE TABLE `user_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) NOT NULL COMMENT '账户',
  `password` varchar(255) NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户账户表';

insert into `user_account` values (null, 'account1', 'password1');
insert into `user_account` values (null, 'account2', 'password2');

CREATE TABLE `information` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `accountid` int(11) NOT NULL COMMENT '帐号ID',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '姓名',
  `age` int(11) NOT NULL COMMENT '年龄',
  `sex` int(11) NOT NULL COMMENT '性别',
  `address` varchar(255) NOT NULL DEFAULT '' COMMENT '收货地址',
  `balance` int(11) NOT NULL DEFAULT '0' COMMENT '账户余额',
  `introduction` varchar(255) DEFAULT '' COMMENT '个人简介',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';

insert into `information` values (null, '1', '账户1','18','0','南昌大学','1000','这是第一个测试账户');
insert into `information` values (null, '2', '账户2','18','1','南昌大学','1000','这是第二个测试账户');

CREATE TABLE `admin_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) NOT NULL COMMENT '账户',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `adminlevel` int(11) NOT NULL COMMENT '权限',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员账户表';

insert into `admin_account` values (null, 'admin1', 'password1','0');

CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '书籍类别',
  `information` varchar(255) NOT NULL COMMENT '类别信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='书籍类别表';

insert into `category` values (null, '文学类', '这是用于测试的文学类书籍类别信息');
insert into `category` values (null, '科普类', '这是用于测试的科普类书籍类别信息');

CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `categoryid` int(11) NOT NULL COMMENT '类别ID',
  `name` varchar(255) NOT NULL COMMENT '书名',
  `introduction` varchar(255) NOT NULL COMMENT '书籍简介',
  `money` int(11) NOT NULL COMMENT '价格',
  `imgurl` varchar(255) NOT NULL COMMENT '书籍图片路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='书籍信息表';

insert into `books` values (null, '1', '活着','这是《活着》用于测试的书籍简介','50','活着.jpg');
insert into `books` values (null, '2', '宇宙的琴弦','这是《宇宙的琴弦》用于测试的书籍简介','50','宇宙的琴弦.jpg');

CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL COMMENT '用户ID',
  `books` varchar(255) NOT NULL COMMENT '购买的书籍',
  `allmoney` int(11) NOT NULL COMMENT '书本总价',
  `status` int(11) NOT NULL COMMENT '订单状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单信息表';

insert into `orders` values(null, '1', '12', '100', '1');
insert into `orders` values(null, '2', '1', '50', '0');

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