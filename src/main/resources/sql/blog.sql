/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : blog

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2019-09-24 23:32:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_artical
-- ----------------------------
DROP TABLE IF EXISTS `tb_artical`;
CREATE TABLE `tb_artical` (
  `artical_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) NOT NULL DEFAULT '1',
  `status` int(11) DEFAULT '0',
  `postTime` varchar(255) DEFAULT NULL,
  `viewCount` int(11) DEFAULT '0',
  `userId` int(11) DEFAULT '1',
  `articalTitle` varchar(255) DEFAULT NULL,
  `articalIntroduce` varchar(255) DEFAULT NULL,
  `picIntroduceUpload` varchar(255) DEFAULT NULL,
  `picIntroduceUploadUrl` varchar(255) DEFAULT NULL,
  `articalContent` longtext,
  `isDenyComment` int(11) DEFAULT '0',
  `isLock` int(11) DEFAULT '0',
  `isSubmitTop` int(11) DEFAULT '0',
  PRIMARY KEY (`artical_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_artical
-- ----------------------------
INSERT INTO `tb_artical` VALUES ('10', '1', '1', '2019-09-21 08:56:35', '3', '1', '震惊！Java竟然可以做这种事！', '近些年的技术圈，单以计算机语言界来说，稳坐第一把太师椅的 Java “或将被取代”、迎接转折点、Java 项目工程师风光不再等言论不绝于耳。在焦虑的大环境下，所有人好像都看起来很焦虑不安。', 'D:/upload/articalHeader/震惊Java竟然可以做这种事/小希4.jpg', 'http://localhost/articalHeader/震惊Java竟然可以做这种事/小希4.jpg', '<p><span style=\"font-family: &quot;Helvetica Neue&quot;, Helvetica, &quot;Microsoft Yahei&quot;, &quot;Hiragino Sans GB&quot;, &quot;WenQuanYi Micro Hei&quot;, sans-serif;\">Java是一门面向对象编程语言，不仅吸收了C++语言的各种优点，还摒弃了C++里难以理解的多继承、指针等概念， 因此Java语言具有功能强大和简单易用两个特征。Java语言作为静态面向对象编程语言的代表，极好地实现了面向对象理论， 允许程序员以优雅的思维方式进行复杂的编程 。 Java具有简单性、面向对象、分布式、健壮性、安全性、平台独立与可移植性、多线程、动态性等特点。 Java可以编写桌面应用程序、Web应用程序、分布式系统和嵌入式系统应用程序等。 20世纪90年代，硬件领域出现了单片式计算机系统，这种价格低廉的系统一出现就立即引起了自动控制领域人员的注意， 因为使用它可以大幅度提升消费类电子产品（如电视机顶盒、面包烤箱、移动电话等）的智能化程度。Sun公司为了抢占市场先机， 在1991年成立了一个称为Green的项目小组， 帕特里克、詹姆斯·高斯林、麦克·舍林丹和其他几个工程师一起组成的工作小组在加利福尼亚州门洛帕克市沙丘路的一个小工作室里面研究开发新技术， 专攻计算机在家电产品上的嵌入式应用。 由于C++所具有的优势，该项目组的研究人员首先考虑采用C++来编写程序。但对于硬件资源极其匮乏的单片式系统来说，C++程序过于复杂和庞大。 另外由于消费电子产品所采用的嵌入式处理器芯片的种类繁杂，如何让编写的程序跨平台运行也是个难题。为了解决困难，他们首先着眼于语言的开发， 假设了一种结构简单、符合嵌入式应用需要的硬件平台体系结构并为其制定了相应的规范， 其中就定义了这种硬件平台的二进制机器码指令系统（即后来成为“字节码”的指令系统），以待语言开发成功后， 能有半导体芯片生产商开发和生产这种硬件平台。对于新语言的设计，Sun公司研发人员并没有开发一种全新的语言， 而是根据嵌入式软件的要求，对C++进行了改造，去除了留在C++的一些不太实用及影响安全的成分，并结合嵌入式系统的实时性要求， 开发了一种称为Oak的面向对象语言。</span>&nbsp;</p><p><img src=\"http://localhost/articalContent/2019-09-21 16-56-24/小希8.jpg\" style=\"max-width: 100%;\">&nbsp;<img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/40/pcmoren_tian_org.png\" alt=\"[舔屏]\" data-w-e=\"1\"><br></p>', '0', '0', '0');
INSERT INTO `tb_artical` VALUES ('11', '3', '1', '2019-09-21 09:50:10', '1', '1', 'PHP介绍', 'PHP（外文名:PHP: Hypertext Preprocessor，中文名：“超文本预处理器”）是一种通用开源脚本语言。语法吸收了C语言、Java和Perl的特点，利于学习，使用广泛，主要适用于Web开发领域。', 'D:/upload/articalHeader/PHP介绍/小希6.jpg', 'http://localhost/articalHeader/PHP介绍/小希6.jpg', '<div label-module=\"para\" style=\"overflow-wrap: break-word; margin-bottom: 15px; text-indent: 2em; line-height: 24px; zoom: 1; font-family: arial, 宋体, sans-serif;\">PHP原始为Personal Home Page的缩写，已经正式更名为 \"PHP: Hypertext Preprocessor\"。自20世纪90年代国内互联网开始发展到现在，互联网信息几乎覆盖了我们日常活动所有知识范畴，并逐渐成为我们生活、学习、工作中必不可少的一部分。据统计，从2003 年开始，我国的网页规模基本保持了翻番的增长速度，并且呈上升趋势。PHP 语言作为当今最热门的网站程序开发语言，它具有成本低、速度快、可移植性好、 内置丰富的函数库等优点，因此被越来越多的企业应用于<a target=\"_blank\" href=\"https://baike.baidu.com/item/%E7%BD%91%E7%AB%99%E5%BC%80%E5%8F%91/1294506\" style=\"color: rgb(19, 110, 194);\">网站开发</a>中。但随着互联网的不断更新换代，PHP语言也出现了不少问题。<span style=\"font-size: 12px; line-height: 0; position: relative; vertical-align: baseline; top: -0.5em; margin-left: 2px; color: rgb(51, 102, 204); cursor: pointer; padding-right: 2px; padding-left: 2px;\">&nbsp;[1]</span><a name=\"ref_[1]_5828265\" style=\"color: rgb(19, 110, 194); position: relative; top: -50px; font-size: 0px; line-height: 0;\">&nbsp;</a></div><div label-module=\"para\" style=\"overflow-wrap: break-word; margin-bottom: 15px; text-indent: 2em; line-height: 24px; zoom: 1; font-family: arial, 宋体, sans-serif;\">根据动态网站要求，PHP语言作为一种语言程序，其专用性逐渐在应用过程中显现，其技术水平的优劣与否将直接影响网站的运行效率。其特点是具有公开的源代码， 在程序设计上与通用型语言，如C语言相似性较高，因此在操作过程中简单易懂，可操作性强。同时，PHP语言具有较高的<a target=\"_blank\" href=\"https://baike.baidu.com/item/%E6%95%B0%E6%8D%AE%E4%BC%A0%E9%80%81/500685\" style=\"color: rgb(19, 110, 194);\">数据传送</a>处理水平和输出水平，可以广泛应用在<a target=\"_blank\" href=\"https://baike.baidu.com/item/Windows/165458\" style=\"color: rgb(19, 110, 194);\">Windows</a>系统及各类<a target=\"_blank\" href=\"https://baike.baidu.com/item/Web/150564\" style=\"color: rgb(19, 110, 194);\">Web</a>服务器中。如果数据量较大，PHP语言还可以拓宽链接面，与各种数据库相连，缓解数据存储、检索及维护压力。随着技术的发展，PHP 语言<a target=\"_blank\" href=\"https://baike.baidu.com/item/%E6%90%9C%E7%B4%A2%E5%BC%95%E6%93%8E/104812\" style=\"color: rgb(19, 110, 194);\">搜索引擎</a>还可以量体裁衣，实行个性化服务，如根据客户的喜好进行分类收集储存，极大提高了数据运行效率</div>', '0', '0', '0');
INSERT INTO `tb_artical` VALUES ('12', '2', '1', '2019-09-21 18:16:03', '0', '1', 'Python介绍', 'Python是一种跨平台的计算机程序设计语言。是一种面向对象的动态类型语言，最初被设计用于编写自动化脚本(shell)，随着版本的不断更新和语言新功能的添加，越来越多被用于独立的、大型项目的开发。', 'D:/upload/articalHeader/Python介绍/小希5.jpg', 'http://localhost/articalHeader/Python介绍/小希5.jpg', '<p>输入文章内容...</p><div label-module=\"para\" style=\"overflow-wrap: break-word; margin-bottom: 15px; text-indent: 2em; line-height: 24px; zoom: 1; font-family: arial, 宋体, sans-serif;\">Python是一种解释型脚本语言，可以应用于以下领域：<span style=\"font-size: 12px; line-height: 0; position: relative; vertical-align: baseline; top: -0.5em; margin-left: 2px; color: rgb(51, 102, 204); cursor: pointer; padding-right: 2px; padding-left: 2px;\">&nbsp;[1]</span><a name=\"ref_[1]_21087\" style=\"color: rgb(19, 110, 194); position: relative; top: -50px; font-size: 0px; line-height: 0;\">&nbsp;</a></div><ul style=\"margin-top: 0px; margin-bottom: 15px; margin-left: 2em; list-style: none; font-family: arial, 宋体, sans-serif; font-size: 12px;\"><li style=\"list-style: none;\"><div label-module=\"para\" style=\"font-size: 14px; overflow-wrap: break-word; line-height: 24px; zoom: 1; margin-left: 20px;\">Web 和 Internet开发</div></li><li style=\"list-style: none;\"><div label-module=\"para\" style=\"font-size: 14px; overflow-wrap: break-word; line-height: 24px; zoom: 1; margin-left: 20px;\">科学计算和统计</div></li><li style=\"list-style: none;\"><div label-module=\"para\" style=\"font-size: 14px; overflow-wrap: break-word; line-height: 24px; zoom: 1; margin-left: 20px;\">人工智能</div></li><li style=\"list-style: none;\"><div label-module=\"para\" style=\"font-size: 14px; overflow-wrap: break-word; line-height: 24px; zoom: 1; margin-left: 20px;\">教育</div></li><li style=\"list-style: none;\"><div label-module=\"para\" style=\"font-size: 14px; overflow-wrap: break-word; line-height: 24px; zoom: 1; margin-left: 20px;\">桌面界面开发</div></li><li style=\"list-style: none;\"><div label-module=\"para\" style=\"font-size: 14px; overflow-wrap: break-word; line-height: 24px; zoom: 1; margin-left: 20px;\">软件开发</div></li><li style=\"list-style: none;\"><div label-module=\"para\" style=\"font-size: 14px; overflow-wrap: break-word; line-height: 24px; zoom: 1; margin-left: 20px;\">后端开发</div></li></ul>', '0', '0', '0');

-- ----------------------------
-- Table structure for tb_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_category`;
CREATE TABLE `tb_category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) NOT NULL,
  `category_url` varchar(255) NOT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_category
-- ----------------------------
INSERT INTO `tb_category` VALUES ('1', 'JAVA', '/java');
INSERT INTO `tb_category` VALUES ('2', 'Python', '/Python');
INSERT INTO `tb_category` VALUES ('3', 'PHP', '/PHP');
INSERT INTO `tb_category` VALUES ('4', 'GO', '/GO');
INSERT INTO `tb_category` VALUES ('5', 'CPP', '/CPP');

-- ----------------------------
-- Table structure for tb_comment
-- ----------------------------
DROP TABLE IF EXISTS `tb_comment`;
CREATE TABLE `tb_comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `artical_id` int(11) NOT NULL,
  `comment_content` varchar(255) NOT NULL,
  `comment_status` int(10) unsigned zerofill NOT NULL,
  `comment_postTime` varchar(20) NOT NULL,
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_comment
-- ----------------------------
INSERT INTO `tb_comment` VALUES ('3', '0', '10', '写的真好！赞一个！！！', '0000000001', '2019-09-21 23:38:33');
INSERT INTO `tb_comment` VALUES ('4', '0', '10', '加油啊，博主，支持你~！', '0000000001', '2019-09-21 23:43:28');
INSERT INTO `tb_comment` VALUES ('5', '0', '10', '撒打算', '0000000001', '2019-09-21 23:44:58');
INSERT INTO `tb_comment` VALUES ('6', '0', '12', '测试一下~！', '0000000001', '2019-09-22 08:19:35');

-- ----------------------------
-- Table structure for tb_file
-- ----------------------------
DROP TABLE IF EXISTS `tb_file`;
CREATE TABLE `tb_file` (
  `file_id` int(11) NOT NULL AUTO_INCREMENT,
  `file_path` varchar(255) NOT NULL,
  `file_path_location` varchar(255) DEFAULT NULL,
  `file_type` varchar(255) NOT NULL,
  `file_description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_file
-- ----------------------------
INSERT INTO `tb_file` VALUES ('13', 'http://localhost/articalContent/2019-09-21 16-56-24/小希8.jpg', 'D:/upload/articalContent/2019-09-21 16-56-24/小希8.jpg', '.jpg', '文章内部图片');
INSERT INTO `tb_file` VALUES ('14', 'http://localhost/articalHeader/震惊Java竟然可以做这种事/小希4.jpg', 'D:/upload/articalHeader/震惊Java竟然可以做这种事/小希4.jpg', '.jpg', '文章介绍图片');
INSERT INTO `tb_file` VALUES ('15', 'http://localhost/articalHeader/PHP介绍/小希7.jpg', 'D:/upload/articalHeader/PHP介绍/小希7.jpg', '.jpg', '文章介绍图片');
INSERT INTO `tb_file` VALUES ('16', 'http://localhost/articalHeader/PHP介绍/小希6.jpg', 'D:/upload/articalHeader/PHP介绍/小希6.jpg', '.jpg', '文章介绍图片');
INSERT INTO `tb_file` VALUES ('17', 'http://localhost/articalHeader/Python介绍/小希5.jpg', 'D:/upload/articalHeader/Python介绍/小希5.jpg', '.jpg', '文章介绍图片');

-- ----------------------------
-- Table structure for tb_permission
-- ----------------------------
DROP TABLE IF EXISTS `tb_permission`;
CREATE TABLE `tb_permission` (
  `permission_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `url` varchar(255) NOT NULL,
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_permission
-- ----------------------------
INSERT INTO `tb_permission` VALUES ('1', 'index', '/');
INSERT INTO `tb_permission` VALUES ('2', 'background', '/background');
INSERT INTO `tb_permission` VALUES ('3', 'login', '/login');

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `authName` varchar(255) NOT NULL,
  `authDescription` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES ('1', 'root', '全站管理员');
INSERT INTO `tb_role` VALUES ('2', 'admin', '普通管理员');
INSERT INTO `tb_role` VALUES ('3', 'writer', '作者');
INSERT INTO `tb_role` VALUES ('4', 'commer', '评论者');
INSERT INTO `tb_role` VALUES ('5', 'reader', '读者');

-- ----------------------------
-- Table structure for tb_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_permission`;
CREATE TABLE `tb_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role_permission
-- ----------------------------
INSERT INTO `tb_role_permission` VALUES ('1', '1', '1');
INSERT INTO `tb_role_permission` VALUES ('2', '1', '2');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `registDate` varchar(255) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `QQ` varchar(255) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `lastSignInIP` varchar(255) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `userIconLocation` varchar(255) DEFAULT NULL,
  `isDenySignIn` int(11) DEFAULT NULL,
  `activationCode` varchar(255) DEFAULT NULL,
  `isActived` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('0', '匿名用户', '未设置', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `tb_user` VALUES ('1', 'wills', 'ed7943a54225adc707128a438aab9fe6', '2019-08-06', '1', '17607113011', '978090490', 'loveing490@qq.com', '山东省龙口市西城区', '9999', '127.0.0.1', '1997-02-08 12:40:05', '珍稀的东西永远抓不住，封闭自己疯狂努力吧。', null, '0', '78563', '1');

-- ----------------------------
-- Table structure for tb_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_role
-- ----------------------------
INSERT INTO `tb_user_role` VALUES ('1', '1', '1');
