-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.7.39-log


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema inventory_manager
--

CREATE DATABASE IF NOT EXISTS inventory_manager;
USE inventory_manager;

--
-- Definition of table `tblcategory`
--

DROP TABLE IF EXISTS `tblcategory`;
CREATE TABLE `tblcategory` (
  `category_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `category_name` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `category_notes` text COLLATE utf8mb4_unicode_ci,
  `category_author_id` int(10) unsigned NOT NULL,
  `category_created_date` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `category_modified_date` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`category_id`),
  KEY `FK_tblcategory_1` (`category_author_id`),
  CONSTRAINT `FK_tblcategory_1` FOREIGN KEY (`category_author_id`) REFERENCES `tbluser` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `tblcategory`
--

/*!40000 ALTER TABLE `tblcategory` DISABLE KEYS */;
INSERT INTO `tblcategory` (`category_id`,`category_name`,`category_notes`,`category_author_id`,`category_created_date`,`category_modified_date`) VALUES 
 (1,'M&#7865; v&agrave; b&eacute;','',750,'14/05/2024','14/05/2024'),
 (2,'Th&#7901;i trang','',750,'14/05/2024','14/05/2024'),
 (3,'Th&#7901;i trang nam','',750,'14/05/2024','14/05/2024'),
 (4,'Th&#7901;i trang n&#7919;','',750,'14/05/2024','14/05/2024'),
 (5,'Qu&#7847;n &aacute;o nam','',750,'14/05/2024','14/05/2024'),
 (6,'Qu&#7847;n &aacute;o n&#7919;','',750,'14/05/2024','14/05/2024'),
 (7,'&#272;&#7891;ng h&#7891;','',750,'14/05/2024','14/05/2024'),
 (8,'T&uacute;i v&iacute;','',750,'14/05/2024','14/05/2024'),
 (9,'Gi&agrave;y d&eacute;p','',750,'14/05/2024','14/05/2024'),
 (10,'Nh&agrave; b&#7871;p','B&aacute;t, &#273;&#361;a, n&#7891;i, ni&ecirc;u, soong, ch&#7843;o, ...',750,'14/05/2024','19/05/2024'),
 (11,'&Ocirc; t&ocirc; &amp; Ph&#7909; ki&#7879;n','',750,'14/05/2024','14/05/2024'),
 (12,'Xe &#273;&#7841;p &amp; Xe m&aacute;y','',750,'14/05/2024','14/05/2024'),
 (13,'&#272;i&#7879;n tho&#7841;i &amp; Ph&#7909; ki&#7879;n','',750,'14/05/2024','14/05/2024'),
 (14,'&#272;i&#7879;n t&#7917;','',750,'14/05/2024','14/05/2024'),
 (15,'B&aacute;ch h&oacute;a online','',750,'14/05/2024','14/05/2024'),
 (16,'&#272;&#7891; gia d&#7909;ng','',750,'14/05/2024','14/05/2024'),
 (17,'B&#7871;p n&uacute;c','',750,'14/05/2024','14/05/2024'),
 (18,'&#272;&#7891; ch&#417;i','',750,'14/05/2024','19/05/2024'),
 (19,'Nh&agrave; c&#7917;a &amp; Trang tr&iacute;','&eacute;dsdsdsdc',750,'14/05/2024','04/06/2024'),
 (20,'Th&#7875; thao','',755,'18/05/2024','18/05/2024'),
 (22,'Balo &amp; T&uacute;i x&aacute;ch','',755,'19/05/2024','19/05/2024');
/*!40000 ALTER TABLE `tblcategory` ENABLE KEYS */;


--
-- Definition of table `tblproduct`
--

DROP TABLE IF EXISTS `tblproduct`;
CREATE TABLE `tblproduct` (
  `product_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `product_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `product_quantity` int(10) unsigned DEFAULT '0',
  `product_price` double DEFAULT '0',
  `product_category_id` int(10) unsigned NOT NULL,
  `product_author_id` int(10) unsigned NOT NULL,
  `product_details` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `product_created_date` varchar(45) DEFAULT NULL,
  `product_modified_date` varchar(45) DEFAULT NULL,
  `product_size` varchar(200) DEFAULT NULL,
  `product_unit` varchar(200) DEFAULT NULL,
  `product_image` text,
  PRIMARY KEY (`product_id`),
  KEY `FK_tblproduct_1` (`product_author_id`),
  KEY `FK_tblproduct_2` (`product_category_id`),
  CONSTRAINT `FK_tblproduct_1` FOREIGN KEY (`product_author_id`) REFERENCES `tbluser` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_tblproduct_2` FOREIGN KEY (`product_category_id`) REFERENCES `tblcategory` (`category_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tblproduct`
--

/*!40000 ALTER TABLE `tblproduct` DISABLE KEYS */;
INSERT INTO `tblproduct` (`product_id`,`product_name`,`product_quantity`,`product_price`,`product_category_id`,`product_author_id`,`product_details`,`product_created_date`,`product_modified_date`,`product_size`,`product_unit`,`product_image`) VALUES 
 (1,'M&ocirc; h&igrave;nh gundam xxt',106,1200000,18,750,'','30/05/2024','30/05/2024','30 x 30 x 30 cm','B&#7897;','1717042787656_985.png'),
 (3,'G&#7841;ch &#7889;p l&aacute;t m&#7897;c lan G30',7300,446000,19,750,'','30/05/2024','06/06/2024','90 x 15 cm','Vi&ecirc;n',NULL),
 (4,'Th&#7843;m h&#7913;ng c&aacute;t m&egrave;o',323,139000,15,750,'','06/06/2024','06/06/2024','50x40 cm','chi&#7871;c','1717659621550_344.png'),
 (5,'T&#7893;ng h&#7907;p L&#7848;U, c&#417;m t&#7921; s&ocirc;i, c&#417;m tr&#7897;n &#259;n li&#7873;n',940,74000,15,750,'','06/06/2024','06/06/2024','350g','Chi&#7871;c','1717660160451_500.png'),
 (6,'M&Igrave; L&#7848;U N&#7844;M CHUA CAY REEVA',9501,239000,15,750,'','06/06/2024','06/06/2024','30 g&oacute;i x 85 g','Th&ugrave;ng','1717661337029_38.png'),
 (7,'&Aacute;o Thun Polo Nam Th&ecirc;u Ch&#7919; U',45181,43000,3,750,'&Aacute;O THUN POLO\n\n&Aacute;o thun Cotton 100% co d&atilde;n 4 chi&#7873;u\n\n\n\n&#10004;&#65039;Size sz M L XL XXL\n\n\n\nSize M 35-45kg\n\nSize L 45-55kg\n\nSize XL 55-65kg\n\nSize XXL 65-75kg\n\nTu&#7923; chi&#7873;u cao nh&iacute;ch size cho ph&ugrave; h&#7907;p gi&uacute;p em nha. B&#7843;ng c&acirc;n n&#7863;ng ch&#7881; l&agrave; t&#432;&#417;ng &#273;&#7889;i &#7841;\n\n\n\n=========================================\n\nCAM K&#7870;T - &#272;&#7842;M B&#7842;O:\n\n- &#272;&#7843;m b&#7843;o v&#7843;i chu&#7849;n cotton ch&#7845;t l&#432;&#7907;ng cao.\n\n- H&agrave;ng c&oacute; s&#7861;n, giao h&agrave;ng ngay khi nh&#7853;n &#273;&#432;&#7907;c &#273;&#417;n &#273;&#7863;t h&agrave;ng .\n\n- Ho&agrave;n ti&#7873;n 100% n&#7871;u s&#7843;n ph&#7849;m l&#7895;i, nh&#7847;m ho&#7863;c kh&ocirc;ng gi&#7889;ng v&#7899;i m&ocirc; t&#7843;.\n\n- Ch&#7845;p nh&#7853;n &#273;&#7893;i h&agrave;ng khi size kh&ocirc;ng v&#7915;a (vui l&ograve;ng nh&#7855;n tin ri&ecirc;ng cho shop).\n\n- Giao h&agrave;ng to&agrave;n qu&#7889;c, thanh to&aacute;n khi nh&#7853;n h&agrave;ng.\n\n- H&#7895; tr&#7907; &#273;&#7893;i tr&#7843; theo quy &#273;&#7883;nh c&#7911;a Shopee.\n\n\n\n&#272;I&#7872;U KI&#7878;N &#272;&#7892;I TR&#7842;:\n\n- H&#7895; tr&#7907; trong v&ograve;ng 03 ng&agrave;y t&#7915; khi nh&#7853;n h&agrave;ng.\n\n- H&agrave;ng ho&aacute; v&#7851;n c&ograve;n m&#7899;i nguy&ecirc;n tem m&aacute;c, ch&#432;a qua s&#7917; d&#7909;ng.\n\n- H&agrave;ng ho&aacute; b&#7883; l&#7895;i ho&#7863;c h&#432; h&#7887;ng do v&#7853;n chuy&#7875;n ho&#7863;c do nh&agrave; s&#7843;n xu&#7845;t.','06/06/2024','06/06/2024','M, L, XL, XXL','Chi&#7871;c','1717661482444_55.png'),
 (8,'Usb 2.0 Dung L&#432;&#7907;ng 1TB Ch&#7845;t L&#432;&#7907;ng Cao',1300000,95000,14,750,'Th&#7901;i gian giao h&agrave;ng d&#7921; ki&#7871;n cho s&#7843;n ph&#7849;m n&agrave;y l&agrave; t&#7915; 7-9 ng&agrave;y\n  \n  Ch&agrave;o m&#7915;ng b&#7841;n &#273;&#7871;n v&#7899;i c&#7917;a h&agrave;ng c&#7911;a ch&uacute;ng t&ocirc;i, ch&uacute;ng t&ocirc;i c&oacute; th&#7875; cung c&#7845;p cho b&#7841;n nh&#7919;ng s&#7843;n ph&#7849;m ch&#7845;t l&#432;&#7907;ng cao v&agrave; gi&aacute; c&#7843; ph&#7843;i ch&#259;ng!\n  N&#7871;u c&#7847;n c&aacute;c s&#7843;n ph&#7849;m kh&aacute;c, b&#7841;n c&oacute; th&#7875; gh&eacute; th&#259;m trang ch&#7911; c&#7911;a c&#7917;a h&agrave;ng!\n  \n  H&#7895; tr&#7907; in logo laze!','06/06/2024','06/06/2024','1TB','Chi&#7871;c','1717661764002_212.png'),
 (9,'K&#7879; M&aacute;y T&iacute;nh &#272;&#7875; B&agrave;n 1 t&#7847;ng',12003,3000,16,750,'','06/06/2024','06/06/2024','50x18cm','Chi&#7871;c','1717661956459_950.png'),
 (10,'Mi&#7871;ng L&oacute;t Chu&#7897;t M&aacute;y T&iacute;nh Ch&#7889;ng Tr&#432;&#7907;t',82196,16000,16,750,'','06/06/2024','06/06/2024','25x25cm','Chi&#7871;c','1717662068386_256.png'),
 (11,'Tai nghe c&oacute; d&acirc;y 3,5mm 4d Bass D&acirc;y t&oacute;c cao Dual Est',1779,22000,14,750,'','06/06/2024','06/06/2024','','Chi&#7871;c','1717662186160_6.png'),
 (12,'Bodymist Nam N&#7919; To&agrave;n Th&acirc;n L&#432;u H&#432;&#417;ng 6 Gi&#7901;',3014000,46000,15,750,'','06/06/2024','06/06/2024','120ml','Chai','1717662344767_470.png');
/*!40000 ALTER TABLE `tblproduct` ENABLE KEYS */;


--
-- Definition of table `tbluser`
--

DROP TABLE IF EXISTS `tbluser`;
CREATE TABLE `tbluser` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_password` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_email` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_phone` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_role` tinyint(3) unsigned DEFAULT '0',
  `user_logined` int(10) unsigned DEFAULT '0',
  `user_fullname` varchar(100) CHARACTER SET utf8mb4 DEFAULT NULL,
  `user_created_at` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_modified_at` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_notes` text COLLATE utf8mb4_unicode_ci,
  `user_address` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_image` text COLLATE utf8mb4_unicode_ci,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1010 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `tbluser`
--

/*!40000 ALTER TABLE `tbluser` DISABLE KEYS */;
INSERT INTO `tbluser` (`user_id`,`user_name`,`user_password`,`user_email`,`user_phone`,`user_role`,`user_logined`,`user_fullname`,`user_created_at`,`user_modified_at`,`user_notes`,`user_address`,`user_image`) VALUES 
 (750,'admin','21232f297a57a5a743894a0e4a801fc3','admin@test.com','0337212814',5,426,'Admin','09/05/2024','22/05/2024','Administrator','L&#7853;p Th&#7841;ch - V&#297;nh Ph&uacute;c','1716361110510_295.png'),
 (751,'huynq','600af65e7f95e2e38fc9e3a4121cfc24','nguyenquanghuylt2002@gmail.com','0337212815',3,23,'Nguy&#7877;n Quang Huy','09/05/2024','20/05/2024',' A student can code.','T&#7917; Du - L&#7853;p Th&#7841;ch - V&#297;nh Ph&uacute;c','1715772897239_371.png'),
 (753,'hieuchuai','e10adc3949ba59abbe56e057f20f883e','hieuchuai1325@gmail.com','0335929272',0,2,'Nguy&#7877;n Minh Hi&#7871;u','10/05/2024','20/05/2024','Marketer','V&#297;nh Ph&uacute;c','1715605535995_281.png'),
 (755,'thankk','600af65e7f95e2e38fc9e3a4121cfc24','khongthihoaithanhlt2003@gmail.com','0382819281',3,40,'Kh&#7893;ng Th&#7883; Ho&agrave;i Thanh','13/05/2024','21/05/2024','','Xu&acirc;n H&ograve;a - L&#7853;p Th&#7841;ch - V&#297;nh Ph&uacute;c','1715605608927_464.png'),
 (756,'tatoli990','e10adc3949ba59abbe56e057f20f883e','huneyo856@gmail.com','0152003375',0,41,'V&#361; Ng&#7885;c H&#432;ng','14/05/2024','14/05/2024',NULL,'Ninh Thu&#7853;n',NULL),
 (757,'tomuka491','e10adc3949ba59abbe56e057f20f883e','ritoka459@gmail.com','0908818363',0,33,'Cao &#272;&#7913;c Th&ocirc;ng','14/05/2024','14/05/2024',NULL,'Qu&#7843;ng Ninh',NULL),
 (758,'muanhu22','e10adc3949ba59abbe56e057f20f883e','sanyto297@gmail.com','0590598400',0,0,'Ma Vi&#7879;t Ho&agrave;ng','14/05/2024','14/05/2024',NULL,'Tp. H&#7891; Ch&iacute; Minh',NULL),
 (759,'tohiya125','e10adc3949ba59abbe56e057f20f883e','yonorj637@gmail.com','0776535454',0,94,'L&#7841;i B&#7843;o &Acirc;n','14/05/2024','14/05/2024',NULL,'Ninh Thu&#7853;n',NULL),
 (760,'toyahu391','e10adc3949ba59abbe56e057f20f883e','hiyale77@gmail.com','0820455127',0,10,'Tr&#7847;n Tu&#7845;n Anh','14/05/2024','14/05/2024',NULL,'B&igrave;nh D&#432;&#417;ng',NULL),
 (761,'lakayu39','e10adc3949ba59abbe56e057f20f883e','anrjhi129@gmail.com','0952638530',0,82,'&#272;&#7841;i Vi&#7879;t Ho&agrave;ng','14/05/2024','14/05/2024',NULL,'H&agrave; N&#7897;i',NULL),
 (762,'nysata869','e10adc3949ba59abbe56e057f20f883e','makurj167@gmail.com','0738081862',0,7,'L&#7841;i Ng&#7885;c Th&uacute;y','14/05/2024','14/05/2024',NULL,'H&#7843;i Ph&ograve;ng',NULL),
 (763,'muraku469','e10adc3949ba59abbe56e057f20f883e','nynera302@gmail.com','0373173000',0,96,'Tr&#7883;nh Gia &Acirc;n','14/05/2024','14/05/2024',NULL,'Ph&uacute; Th&#7885;',NULL),
 (764,'yamuta109','e10adc3949ba59abbe56e057f20f883e','rjyuhu851@gmail.com','0933043324',0,27,'Ho&agrave;ng Th&aacute;i Ph&uacute;c','14/05/2024','14/05/2024',NULL,'L&#7841;ng S&#417;n',NULL),
 (765,'mayumu952','e10adc3949ba59abbe56e057f20f883e','lenota521@gmail.com','0552589395',0,39,'Tri&#7879;u B&#7843;o &Acirc;n','14/05/2024','14/05/2024',NULL,'H&agrave; Nam',NULL),
 (766,'husayu610','e10adc3949ba59abbe56e057f20f883e','huanka328@gmail.com','0301746472',0,46,'&#272;&#7895; Th&aacute;i Ph&uacute;c','14/05/2024','14/05/2024',NULL,'L&#7841;ng S&#417;n',NULL),
 (767,'mukaku552','e10adc3949ba59abbe56e057f20f883e','ekyahu579@gmail.com','0341265314',0,43,'Cao Anh','14/05/2024','14/05/2024',NULL,'Qu&#7843;ng Ninh',NULL),
 (768,'kueksa725','e10adc3949ba59abbe56e057f20f883e','yuanmu504@gmail.com','0113364574',0,35,'Tr&#7847;n Vi&#7879;t H&ugrave;ng','14/05/2024','14/05/2024',NULL,'V&#297;nh Ph&uacute;c',NULL),
 (769,'tarino551','e10adc3949ba59abbe56e057f20f883e','anliko50@gmail.com','0195494131',0,58,'Tr&#7883;nh Minh Anh','14/05/2024','14/05/2024',NULL,'Qu&#7843;ng Ninh',NULL),
 (770,'rilemu715','e10adc3949ba59abbe56e057f20f883e','toanne730@gmail.com','0148601105',0,93,'Ma V&acirc;n Anh','14/05/2024','14/05/2024',NULL,'V&#297;nh Ph&uacute;c',NULL),
 (771,'rjhuyo832','e10adc3949ba59abbe56e057f20f883e','leyohi810@gmail.com','0629105531',0,82,'Kh&#7893;ng Ph&#432;&#417;ng Anh','14/05/2024','14/05/2024',NULL,'Ph&uacute; Th&#7885;',NULL),
 (772,'yotoma737','e10adc3949ba59abbe56e057f20f883e','sayuku460@gmail.com','0952643332',0,7,'&#272;&#7841;i Kh&aacute;nh Linh','14/05/2024','14/05/2024',NULL,'Ninh Thu&#7853;n',NULL),
 (773,'rakama901','e10adc3949ba59abbe56e057f20f883e','karala66@gmail.com','0298210548',0,1,'Tr&#7883;nh Qu&#7889;c Huy','14/05/2024','14/05/2024',NULL,'H&agrave; Nam',NULL),
 (774,'nonyne8','e10adc3949ba59abbe56e057f20f883e','ektori414@gmail.com','0944041763',0,3,'Kh&#7893;ng Mai H&#432;&#417;ng','14/05/2024','14/05/2024',NULL,'B&igrave;nh D&#432;&#417;ng',NULL),
 (775,'yoniya35','e10adc3949ba59abbe56e057f20f883e','noyane265@gmail.com','0336985014',0,30,'V&otilde; Quang Ph&uacute;c','14/05/2024','14/05/2024',NULL,'L&#7841;ng S&#417;n',NULL),
 (776,'lenyra246','e10adc3949ba59abbe56e057f20f883e','konomu87@gmail.com','0587041248',0,77,'Ph&#7841;m Thanh Ho&agrave;i','14/05/2024','14/05/2024',NULL,'B&#7855;c Ninh',NULL),
 (777,'nymahi166','e10adc3949ba59abbe56e057f20f883e','yohusa900@gmail.com','0629336820',0,59,'V&otilde; Mai H&#432;&#417;ng','14/05/2024','14/05/2024',NULL,'V&#297;nh Ph&uacute;c',NULL),
 (778,'nonohu53','e10adc3949ba59abbe56e057f20f883e','sayaka781@gmail.com','0820657774',0,6,'L&#7841;i Th&aacute;i Ph&uacute;c','14/05/2024','14/05/2024',NULL,'B&igrave;nh Thu&#7853;n',NULL),
 (779,'tohine786','e10adc3949ba59abbe56e057f20f883e','yanyla663@gmail.com','0964355860',0,73,'V&otilde; V&#259;n D&#361;ng','14/05/2024','14/05/2024',NULL,'Ngh&#7879; An',NULL),
 (780,'sarjhi344','e10adc3949ba59abbe56e057f20f883e','lasane373@gmail.com','0204897862',0,35,'Ph&#7841;m Th&#7883; Thanh Ho&agrave;i','14/05/2024','14/05/2024',NULL,'B&#7855;c Ninh',NULL),
 (781,'huanrj677','e10adc3949ba59abbe56e057f20f883e','ekhuni718@gmail.com','0197015340',0,70,'Nguy&#7877;n V&#259;n H&ugrave;ng','14/05/2024','14/05/2024',NULL,'Ph&uacute; Th&#7885;',NULL),
 (782,'anekyo203','e10adc3949ba59abbe56e057f20f883e','kutama755@gmail.com','0405539134',0,53,'L&#7841;i Thanh Qu&#7923;nh','14/05/2024','14/05/2024',NULL,'Qu&#7843;ng Ninh',NULL),
 (783,'yaanla333','e10adc3949ba59abbe56e057f20f883e','letomu54@gmail.com','0555714270',0,38,'&#272;&#7841;i V&#259;n D&#361;ng','14/05/2024','14/05/2024',NULL,'Th&aacute;i Nguy&ecirc;n',NULL),
 (784,'nelato846','e10adc3949ba59abbe56e057f20f883e','takoni73@gmail.com','0392265021',0,25,'&#272;&#7895; Vi&#7879;t Huy','14/05/2024','14/05/2024',NULL,'B&igrave;nh Thu&#7853;n',NULL),
 (785,'hinyan356','e10adc3949ba59abbe56e057f20f883e','yutoto544@gmail.com','0419294617',0,9,'Tr&#7847;n Thanh Ho&agrave;i','14/05/2024','14/05/2024',NULL,'H&agrave; N&#7897;i',NULL),
 (786,'hileta456','e10adc3949ba59abbe56e057f20f883e','kaekri224@gmail.com','0800424361',0,35,'V&#361; Kim Anh','14/05/2024','14/05/2024',NULL,'H&agrave; Nam',NULL),
 (787,'ekmaka518','e10adc3949ba59abbe56e057f20f883e','nekurj322@gmail.com','0686011927',0,40,'Mai Th&#7883; Thanh Ho&agrave;i','14/05/2024','14/05/2024',NULL,'H&agrave; N&#7897;i',NULL),
 (788,'nerito731','e10adc3949ba59abbe56e057f20f883e','nomato468@gmail.com','0800439479',0,56,'V&otilde; V&#259;n H&ugrave;ng','14/05/2024','14/05/2024',NULL,'Tp. H&#7891; Ch&iacute; Minh',NULL),
 (789,'ranyny665','e10adc3949ba59abbe56e057f20f883e','rasarj393@gmail.com','0345627962',0,26,'Mai Quang Huy','14/05/2024','14/05/2024',NULL,'B&#7855;c Ninh',NULL),
 (790,'larano581','e10adc3949ba59abbe56e057f20f883e','rirani781@gmail.com','0499802603',0,78,'Kh&#7893;ng Minh Anh','14/05/2024','14/05/2024',NULL,'Ph&uacute; Th&#7885;',NULL),
 (791,'muanto836','e10adc3949ba59abbe56e057f20f883e','hiekta807@gmail.com','0543684723',0,43,'Tr&#432;&#417;ng Th&ugrave;y Dung','14/05/2024','14/05/2024',NULL,'B&#7855;c Ninh',NULL),
 (792,'lalali834','e10adc3949ba59abbe56e057f20f883e','toyoko845@gmail.com','0889718146',0,69,'Ho&agrave;ng Mai H&#432;&#417;ng','14/05/2024','14/05/2024',NULL,'B&igrave;nh Thu&#7853;n',NULL),
 (793,'kumayu428','e10adc3949ba59abbe56e057f20f883e','kuleto619@gmail.com','0523657010',0,14,'Ma Ph&#432;&#417;ng Anh','14/05/2024','14/05/2024',NULL,'Tp. H&#7891; Ch&iacute; Minh',NULL),
 (794,'nolako222','e10adc3949ba59abbe56e057f20f883e','notohi284@gmail.com','0657961857',0,16,'L&#432;&#417;ng Vi&#7879;t Ho&agrave;ng','14/05/2024','14/05/2024',NULL,'B&#7855;c Ninh',NULL),
 (795,'lemuri314','e10adc3949ba59abbe56e057f20f883e','sayumu945@gmail.com','0808582056',0,1,'V&#361; Ng&#7885;c H&#432;ng','14/05/2024','14/05/2024',NULL,'L&#7841;ng S&#417;n',NULL),
 (796,'sakuan884','e10adc3949ba59abbe56e057f20f883e','yomuan118@gmail.com','0566875103',0,88,'Ph&#7841;m Anh','14/05/2024','14/05/2024',NULL,'Ngh&#7879; An',NULL),
 (797,'malani658','e10adc3949ba59abbe56e057f20f883e','yuneta463@gmail.com','0274719590',0,27,'Tr&#7883;nh Th&#7883; Ly','14/05/2024','14/05/2024',NULL,'Th&aacute;i Nguy&ecirc;n',NULL),
 (798,'mulile815','e10adc3949ba59abbe56e057f20f883e','yokomu199@gmail.com','0606987708',0,16,'L&#7841;i H&#7919;u Ph&uacute;c','14/05/2024','14/05/2024',NULL,'H&#7843;i Ph&ograve;ng',NULL),
 (799,'larjri954','e10adc3949ba59abbe56e057f20f883e','kutoni385@gmail.com','0991031119',0,2,'Ho&agrave;ng V&acirc;n Anh','14/05/2024','14/05/2024',NULL,'Th&aacute;i Nguy&ecirc;n',NULL),
 (800,'lerjhu954','e10adc3949ba59abbe56e057f20f883e','riekma795@gmail.com','0734750159',0,25,'Tri&#7879;u V&#259;n D&#361;ng','14/05/2024','14/05/2024',NULL,'Th&aacute;i Nguy&ecirc;n',NULL),
 (801,'koekhu33','e10adc3949ba59abbe56e057f20f883e','tatako261@gmail.com','0645021439',0,0,'Nguy&#7877;n V&#259;n H&ugrave;ng','14/05/2024','14/05/2024',NULL,'B&igrave;nh Thu&#7853;n',NULL),
 (802,'lakora222','e10adc3949ba59abbe56e057f20f883e','nyekko992@gmail.com','0116592050',0,83,'Ma Gia &Acirc;n','14/05/2024','14/05/2024',NULL,'H&#7843;i Ph&ograve;ng',NULL),
 (803,'satole679','e10adc3949ba59abbe56e057f20f883e','ekyuta750@gmail.com','0441895701',0,90,'V&otilde; Vi&#7879;t Ho&agrave;ng','14/05/2024','14/05/2024',NULL,'H&agrave; N&#7897;i',NULL),
 (804,'kaanto767','e10adc3949ba59abbe56e057f20f883e','yoekny637@gmail.com','0364687826',0,87,'Ma Ng&#7885;c Quang','14/05/2024','14/05/2024',NULL,'B&igrave;nh D&#432;&#417;ng',NULL),
 (805,'ansata791','e10adc3949ba59abbe56e057f20f883e','manela980@gmail.com','0516196369',0,18,'Ma Vi&#7879;t H&ugrave;ng','14/05/2024','14/05/2024',NULL,'B&igrave;nh D&#432;&#417;ng',NULL),
 (806,'sayara920','e10adc3949ba59abbe56e057f20f883e','tokusa93@gmail.com','0803911477',0,42,'V&otilde; Minh Anh','14/05/2024','14/05/2024',NULL,'H&agrave; Nam',NULL),
 (807,'anrara29','e10adc3949ba59abbe56e057f20f883e','huyoto75@gmail.com','0934527510',0,49,'Tr&#432;&#417;ng Kim Anh','14/05/2024','14/05/2024',NULL,'V&#297;nh Ph&uacute;c',NULL),
 (808,'taekle85','e10adc3949ba59abbe56e057f20f883e','nirjyu905@gmail.com','0243096723',0,26,'&#272;&#7841;i Gia &Acirc;n','14/05/2024','14/05/2024',NULL,'Ph&uacute; Th&#7885;',NULL),
 (809,'murjya578','e10adc3949ba59abbe56e057f20f883e','ritaya529@gmail.com','0820136853',0,12,'Mai Minh Hi&#7871;u','14/05/2024','14/05/2024',NULL,'L&#7841;ng S&#417;n',NULL),
 (810,'muekya454','e10adc3949ba59abbe56e057f20f883e','yuhuyo86@gmail.com','0537786637',0,53,'Tri&#7879;u V&acirc;n Anh','14/05/2024','14/05/2024',NULL,'Tp. H&#7891; Ch&iacute; Minh',NULL),
 (811,'rayoto382','e10adc3949ba59abbe56e057f20f883e','ekkule988@gmail.com','0897896846',0,0,'&#272;&#7895; Qu&#7889;c Huy','14/05/2024','14/05/2024',NULL,'H&agrave; Nam',NULL),
 (812,'kotoya602','e10adc3949ba59abbe56e057f20f883e','lalile93@gmail.com','0382131463',0,64,'Ph&#7841;m Quang &#272;&#7841;o','14/05/2024','14/05/2024',NULL,'H&agrave; N&#7897;i',NULL),
 (813,'tohuyo277','e10adc3949ba59abbe56e057f20f883e','yuhila801@gmail.com','0389048142',0,89,'L&#7841;i Th&aacute;i Ph&uacute;c','14/05/2024','14/05/2024',NULL,'L&#7841;ng S&#417;n',NULL),
 (814,'koekrj629','e10adc3949ba59abbe56e057f20f883e','lamuek837@gmail.com','0943810573',0,97,'&#272;&#7895; V&acirc;n Anh','14/05/2024','14/05/2024',NULL,'B&igrave;nh Thu&#7853;n',NULL),
 (815,'tomaek100','e10adc3949ba59abbe56e057f20f883e','rimuya870@gmail.com','0477662313',0,95,'V&#361; Ph&#432;&#417;ng Anh','14/05/2024','14/05/2024',NULL,'H&agrave; Nam',NULL),
 (816,'nitoma330','e10adc3949ba59abbe56e057f20f883e','kahuyo390@gmail.com','0866729875',0,70,'Nguy&#7877;n Quang Huy','14/05/2024','14/05/2024',NULL,'Tp. H&#7891; Ch&iacute; Minh',NULL),
 (817,'eknyto899','e10adc3949ba59abbe56e057f20f883e','yuhiku519@gmail.com','0131841839',0,3,'Kh&#7893;ng Minh &#272;&#259;ng','14/05/2024','14/05/2024',NULL,'Th&aacute;i Nguy&ecirc;n',NULL),
 (818,'nyanra584','e10adc3949ba59abbe56e057f20f883e','nokano200@gmail.com','0113790190',0,95,'Tr&#432;&#417;ng Th&aacute;i H&#432;ng','14/05/2024','14/05/2024',NULL,'Th&aacute;i Nguy&ecirc;n',NULL),
 (819,'yonoyu816','e10adc3949ba59abbe56e057f20f883e','anhuli575@gmail.com','0673786271',0,20,'V&otilde; Vi&#7879;t Huy','14/05/2024','14/05/2024',NULL,'H&agrave; Nam',NULL),
 (820,'toyuan512','e10adc3949ba59abbe56e057f20f883e','yayuku918@gmail.com','0556800833',0,64,'Ho&agrave;ng Minh Hi&#7871;u','14/05/2024','14/05/2024',NULL,'L&#7841;ng S&#417;n',NULL),
 (821,'mutoyu419','e10adc3949ba59abbe56e057f20f883e','tokasa747@gmail.com','0946463929',0,86,'Tr&#7847;n Tu&#7845;n Anh','14/05/2024','14/05/2024',NULL,'Tp. H&#7891; Ch&iacute; Minh',NULL),
 (822,'sarane477','e10adc3949ba59abbe56e057f20f883e','muanrj851@gmail.com','0563215944',0,30,'&#272;&#7895; &#272;&#7913;c Th&ocirc;ng','14/05/2024','14/05/2024',NULL,'Th&aacute;i Nguy&ecirc;n',NULL),
 (823,'kaeksa40','e10adc3949ba59abbe56e057f20f883e','rjkohu779@gmail.com','0389742027',0,53,'Cao Vi&#7879;t Ho&agrave;ng','14/05/2024','14/05/2024',NULL,'L&#7841;ng S&#417;n',NULL),
 (824,'kurima459','e10adc3949ba59abbe56e057f20f883e','ankany966@gmail.com','0260427378',0,34,'&#272;&#7841;i V&#259;n Huy','14/05/2024','14/05/2024',NULL,'Tp. H&#7891; Ch&iacute; Minh',NULL),
 (825,'riyuto813','e10adc3949ba59abbe56e057f20f883e','torito400@gmail.com','0833057719',0,18,'Cao H&#7919;u Ph&uacute;c','14/05/2024','14/05/2024',NULL,'B&#7855;c Ninh',NULL),
 (826,'salala988','e10adc3949ba59abbe56e057f20f883e','tayoek499@gmail.com','0697152741',0,46,'&#272;&#7895; Quang &#272;&#7841;o','14/05/2024','14/05/2024',NULL,'Qu&#7843;ng Ninh',NULL),
 (827,'tanita371','e10adc3949ba59abbe56e057f20f883e','muyama684@gmail.com','0148221255',0,94,'L&#7841;i Kh&aacute;nh Linh','14/05/2024','14/05/2024',NULL,'B&igrave;nh D&#432;&#417;ng',NULL),
 (828,'nerato563','e10adc3949ba59abbe56e057f20f883e','yakoek45@gmail.com','0809759540',0,27,'Tr&#7847;n V&#259;n Quang','14/05/2024','14/05/2024',NULL,'H&agrave; Nam',NULL),
 (829,'lirjla668','e10adc3949ba59abbe56e057f20f883e','lirato143@gmail.com','0568074872',0,44,'Tri&#7879;u V&acirc;n Anh','14/05/2024','14/05/2024',NULL,'V&#297;nh Ph&uacute;c',NULL),
 (830,'hinela245','e10adc3949ba59abbe56e057f20f883e','yaraya465@gmail.com','0678856249',0,39,'V&otilde; Th&ugrave;y Dung','14/05/2024','14/05/2024',NULL,'Ninh Thu&#7853;n',NULL),
 (831,'yuanko894','e10adc3949ba59abbe56e057f20f883e','marahu592@gmail.com','0624848025',0,50,'Nguy&#7877;n V&#259;n Quang','14/05/2024','14/05/2024',NULL,'H&agrave; N&#7897;i',NULL),
 (832,'neanko906','e10adc3949ba59abbe56e057f20f883e','noyoko586@gmail.com','0691044276',0,92,'Kh&#7893;ng Quang &#272;&#7841;o','14/05/2024','14/05/2024',NULL,'H&agrave; N&#7897;i',NULL),
 (833,'lianmu415','e10adc3949ba59abbe56e057f20f883e','marako873@gmail.com','0359978131',0,52,'&#272;&#7841;i Ng&#7885;c H&#432;ng','14/05/2024','14/05/2024',NULL,'H&agrave; Nam',NULL),
 (834,'yumuto70','e10adc3949ba59abbe56e057f20f883e','katoko149@gmail.com','0141456921',0,70,'Tr&#7847;n Anh','14/05/2024','14/05/2024',NULL,'H&agrave; N&#7897;i',NULL),
 (835,'limato744','e10adc3949ba59abbe56e057f20f883e','ekneny854@gmail.com','0762645305',0,65,'L&#432;&#417;ng Vi&#7879;t Ho&agrave;ng','14/05/2024','14/05/2024',NULL,'Ninh Thu&#7853;n',NULL),
 (836,'anyali577','e10adc3949ba59abbe56e057f20f883e','komamu510@gmail.com','0905613345',0,78,'Tri&#7879;u Gia &Acirc;n','14/05/2024','14/05/2024',NULL,'Tp. H&#7891; Ch&iacute; Minh',NULL),
 (837,'riekli681','e10adc3949ba59abbe56e057f20f883e','lerihi369@gmail.com','0133859617',0,78,'Tr&#432;&#417;ng Th&agrave;nh Thu&#7853;n','14/05/2024','14/05/2024',NULL,'V&#297;nh Ph&uacute;c',NULL),
 (838,'neriny653','e10adc3949ba59abbe56e057f20f883e','likuhu408@gmail.com','0162222974',0,8,'Ho&agrave;ng Anh','14/05/2024','14/05/2024',NULL,'Ninh Thu&#7853;n',NULL),
 (839,'nineyo449','e10adc3949ba59abbe56e057f20f883e','talika781@gmail.com','0730785249',0,75,'L&#432;&#417;ng Th&ugrave;y D&#432;&#417;ng','14/05/2024','14/05/2024',NULL,'H&#7843;i Ph&ograve;ng',NULL),
 (840,'ekhirj777','e10adc3949ba59abbe56e057f20f883e','tatohu717@gmail.com','0151472192',0,7,'&#272;&#7841;i Mai H&#432;&#417;ng','14/05/2024','14/05/2024',NULL,'H&agrave; N&#7897;i',NULL),
 (841,'tonyri218','e10adc3949ba59abbe56e057f20f883e','nokuto769@gmail.com','0379202203',0,36,'&#272;&#7895; Kh&aacute;nh Linh','14/05/2024','14/05/2024',NULL,'Th&aacute;i Nguy&ecirc;n',NULL),
 (842,'lihile866','e10adc3949ba59abbe56e057f20f883e','tanyno272@gmail.com','0369572041',0,85,'Nguy&#7877;n Minh Hi&#7871;u','14/05/2024','14/05/2024',NULL,'B&#7855;c Ninh',NULL),
 (843,'yukony348','e10adc3949ba59abbe56e057f20f883e','rayali413@gmail.com','0205813767',0,95,'Ma V&#259;n Huy','14/05/2024','14/05/2024',NULL,'B&#7855;c Ninh',NULL),
 (844,'yosaku271','e10adc3949ba59abbe56e057f20f883e','nimuno745@gmail.com','0190950126',0,45,'Ph&#7841;m Quang &#272;&#7841;o','14/05/2024','14/05/2024',NULL,'Th&aacute;i Nguy&ecirc;n',NULL),
 (845,'tayuri644','e10adc3949ba59abbe56e057f20f883e','rahiyu268@gmail.com','0633518199',0,56,'Cao Quang Minh','14/05/2024','14/05/2024',NULL,'B&igrave;nh Thu&#7853;n',NULL),
 (846,'ekrjma447','e10adc3949ba59abbe56e057f20f883e','noneka694@gmail.com','0621727094',0,88,'Cao Th&#7883; Ly','14/05/2024','14/05/2024',NULL,'Ph&uacute; Th&#7885;',NULL),
 (847,'humane346','e10adc3949ba59abbe56e057f20f883e','kayule453@gmail.com','0462210420',0,94,'Ma Gia &Acirc;n','14/05/2024','14/05/2024',NULL,'B&igrave;nh D&#432;&#417;ng',NULL),
 (848,'nonoya583','e10adc3949ba59abbe56e057f20f883e','nokono505@gmail.com','0830192687',0,88,'V&#361; Quang Ph&uacute;c','14/05/2024','14/05/2024',NULL,'Th&aacute;i Nguy&ecirc;n',NULL),
 (849,'sanony518','e10adc3949ba59abbe56e057f20f883e','yasaan800@gmail.com','0894281054',0,88,'Mai Ho&agrave;i Thanh','14/05/2024','14/05/2024',NULL,'Qu&#7843;ng Ninh',NULL),
 (850,'yanyli211','e10adc3949ba59abbe56e057f20f883e','taekmu268@gmail.com','0683137319',0,9,'Ma Ng&#7885;c H&#432;ng','14/05/2024','14/05/2024',NULL,'Ninh Thu&#7853;n',NULL),
 (851,'rjrale263','e10adc3949ba59abbe56e057f20f883e','nykuri260@gmail.com','0170286048',0,62,'V&otilde; V&acirc;n Anh','14/05/2024','14/05/2024',NULL,'V&#297;nh Ph&uacute;c',NULL),
 (852,'annoli565','e10adc3949ba59abbe56e057f20f883e','kuhuta999@gmail.com','0722111966',0,78,'&#272;&#7841;i Th&ugrave;y D&#432;&#417;ng','14/05/2024','14/05/2024',NULL,'Th&aacute;i Nguy&ecirc;n',NULL),
 (853,'yonito327','e10adc3949ba59abbe56e057f20f883e','nyyaan536@gmail.com','0878522355',0,42,'Tri&#7879;u V&acirc;n Anh','14/05/2024','14/05/2024',NULL,'V&#297;nh Ph&uacute;c',NULL),
 (854,'rjrjyo274','e10adc3949ba59abbe56e057f20f883e','rahule927@gmail.com','0901842471',0,66,'V&#361; B&#7843;o &Acirc;n','14/05/2024','14/05/2024',NULL,'Ph&uacute; Th&#7885;',NULL),
 (855,'tomayu286','e10adc3949ba59abbe56e057f20f883e','nonyka544@gmail.com','0755416573',0,24,'Mai Mai H&#432;&#417;ng','14/05/2024','14/05/2024',NULL,'H&agrave; N&#7897;i',NULL),
 (856,'munesa648','e10adc3949ba59abbe56e057f20f883e','muanrj389@gmail.com','0833896183',0,13,'Ma Nh&#432; Qu&#7923;nh','14/05/2024','14/05/2024',NULL,'Tp. H&#7891; Ch&iacute; Minh',NULL),
 (858,'linema991','e10adc3949ba59abbe56e057f20f883e','leyula669@gmail.com','0269880404',0,81,'L&#432;&#417;ng Ph&#432;&#417;ng Anh','14/05/2024','14/05/2024',NULL,'Th&aacute;i Nguy&ecirc;n',NULL),
 (859,'nymale393','e10adc3949ba59abbe56e057f20f883e','ektoku314@gmail.com','0655280182',0,87,'L&#7841;i V&#259;n H&ugrave;ng','14/05/2024','14/05/2024',NULL,'B&igrave;nh Thu&#7853;n',NULL),
 (860,'rakoyu140','e10adc3949ba59abbe56e057f20f883e','neyuka615@gmail.com','0927626286',0,19,'Tr&#7883;nh Qu&#7889;c Huy','14/05/2024','14/05/2024',NULL,'B&igrave;nh Thu&#7853;n',NULL),
 (861,'liniyo631','e10adc3949ba59abbe56e057f20f883e','mamuto457@gmail.com','0743142268',0,79,'V&otilde; Vi&#7879;t Ho&agrave;ng','14/05/2024','14/05/2024',NULL,'B&igrave;nh Thu&#7853;n',NULL),
 (862,'eknoya997','e10adc3949ba59abbe56e057f20f883e','tosama291@gmail.com','0746484914',0,14,'&#272;&#7895; Minh Hi&#7871;u','14/05/2024','14/05/2024',NULL,'H&#7843;i Ph&ograve;ng',NULL),
 (863,'kunole120','e10adc3949ba59abbe56e057f20f883e','mamayo661@gmail.com','0111314008',0,43,'Nguy&#7877;n Quang Minh','14/05/2024','14/05/2024',NULL,'H&agrave; Nam',NULL),
 (864,'rjmuno398','e10adc3949ba59abbe56e057f20f883e','yorihu20@gmail.com','0906187448',0,76,'Ho&agrave;ng Th&aacute;i H&#432;ng','14/05/2024','14/05/2024',NULL,'Tp. H&#7891; Ch&iacute; Minh',NULL),
 (865,'nohila563','e10adc3949ba59abbe56e057f20f883e','sasako124@gmail.com','0240150202',0,10,'Kh&#7893;ng V&acirc;n Anh','14/05/2024','14/05/2024',NULL,'Ngh&#7879; An',NULL),
 (866,'muliko234','e10adc3949ba59abbe56e057f20f883e','korale896@gmail.com','0226748261',0,60,'Ho&agrave;ng Anh','14/05/2024','14/05/2024',NULL,'Qu&#7843;ng Ninh',NULL),
 (867,'hitoyu762','e10adc3949ba59abbe56e057f20f883e','anyato187@gmail.com','0156312781',0,36,'Nguy&#7877;n Th&#7883; Thu H&#432;&#7901;ng','14/05/2024','14/05/2024',NULL,'H&agrave; N&#7897;i',NULL),
 (868,'noniyu583','e10adc3949ba59abbe56e057f20f883e','yumane100@gmail.com','0523421790',0,62,'V&#361; Minh Hi&#7871;u','14/05/2024','14/05/2024',NULL,'H&agrave; N&#7897;i',NULL),
 (869,'tokaka592','e10adc3949ba59abbe56e057f20f883e','liyora587@gmail.com','0832007323',0,55,'Nguy&#7877;n Vi&#7879;t Ho&agrave;ng','14/05/2024','14/05/2024',NULL,'B&igrave;nh Thu&#7853;n',NULL),
 (870,'kohuto335','e10adc3949ba59abbe56e057f20f883e','yayony745@gmail.com','0267810894',0,27,'&#272;&#7895; &#272;&#7913;c D&#361;ng','14/05/2024','14/05/2024',NULL,'H&agrave; N&#7897;i',NULL),
 (871,'maleri892','e10adc3949ba59abbe56e057f20f883e','latala272@gmail.com','0273673509',0,80,'Tr&#7847;n Minh Anh','14/05/2024','14/05/2024',NULL,'V&#297;nh Ph&uacute;c',NULL),
 (872,'makasa998','e10adc3949ba59abbe56e057f20f883e','manyek542@gmail.com','0410580789',0,7,'&#272;&#7841;i Th&aacute;i H&#432;ng','14/05/2024','14/05/2024',NULL,'Th&aacute;i Nguy&ecirc;n',NULL),
 (873,'satoya133','e10adc3949ba59abbe56e057f20f883e','maliek435@gmail.com','0792688893',0,35,'&#272;&#7895; B&#7843;o &Acirc;n','14/05/2024','14/05/2024',NULL,'B&igrave;nh Thu&#7853;n',NULL),
 (874,'nesaan88','e10adc3949ba59abbe56e057f20f883e','tasane582@gmail.com','0616464541',0,30,'Tr&#7847;n Vi&#7879;t H&ugrave;ng','14/05/2024','14/05/2024',NULL,'Qu&#7843;ng Ninh',NULL),
 (875,'yurian119','e10adc3949ba59abbe56e057f20f883e','kuhurj857@gmail.com','0774658744',0,88,'L&#432;&#417;ng Th&ugrave;y D&#432;&#417;ng','14/05/2024','14/05/2024',NULL,'Th&aacute;i Nguy&ecirc;n',NULL),
 (876,'kamuya682','e10adc3949ba59abbe56e057f20f883e','yorjne210@gmail.com','0332671442',0,82,'V&otilde; Th&aacute;i Ph&uacute;c','14/05/2024','14/05/2024',NULL,'Th&aacute;i Nguy&ecirc;n',NULL),
 (877,'tataan104','e10adc3949ba59abbe56e057f20f883e','tohuya259@gmail.com','0664402970',0,63,'Ho&agrave;ng Anh','14/05/2024','14/05/2024',NULL,'L&#7841;ng S&#417;n',NULL),
 (878,'noline973','e10adc3949ba59abbe56e057f20f883e','rjanyo194@gmail.com','0386325885',0,75,'Nguy&#7877;n V&#259;n Quang','14/05/2024','14/05/2024',NULL,'V&#297;nh Ph&uacute;c',NULL),
 (879,'ekmuhi114','e10adc3949ba59abbe56e057f20f883e','tomala147@gmail.com','0517832148',0,22,'Kh&#7893;ng Quang Minh','14/05/2024','14/05/2024',NULL,'H&agrave; N&#7897;i',NULL),
 (880,'rileli33','e10adc3949ba59abbe56e057f20f883e','kolari589@gmail.com','0556323225',0,65,'Cao Vi&#7879;t H&ugrave;ng','14/05/2024','14/05/2024',NULL,'B&igrave;nh D&#432;&#417;ng',NULL),
 (881,'totata405','e10adc3949ba59abbe56e057f20f883e','hiekla704@gmail.com','0705480606',0,81,'L&#432;&#417;ng B&#7843;o &Acirc;n','14/05/2024','14/05/2024',NULL,'Ninh Thu&#7853;n',NULL),
 (882,'nohule919','e10adc3949ba59abbe56e057f20f883e','nikula347@gmail.com','0428997731',0,48,'Tr&#7883;nh Minh Hi&#7871;u','14/05/2024','14/05/2024',NULL,'H&#7843;i Ph&ograve;ng',NULL),
 (883,'kukorj278','e10adc3949ba59abbe56e057f20f883e','nyleny428@gmail.com','0107400961',0,86,'&#272;&#7895; V&#259;n Huy','14/05/2024','14/05/2024',NULL,'B&#7855;c Ninh',NULL),
 (884,'hutoko737','e10adc3949ba59abbe56e057f20f883e','anlisa498@gmail.com','0336906126',0,45,'Tri&#7879;u Th&#7883; Thu H&#432;&#7901;ng','14/05/2024','14/05/2024',NULL,'Tp. H&#7891; Ch&iacute; Minh',NULL),
 (885,'huleek825','e10adc3949ba59abbe56e057f20f883e','huyurj23@gmail.com','0466608557',0,67,'Kh&#7893;ng Minh Hi&#7871;u','14/05/2024','14/05/2024',NULL,'H&agrave; N&#7897;i',NULL),
 (886,'ririmu545','e10adc3949ba59abbe56e057f20f883e','himuto654@gmail.com','0391339068',0,35,'L&#7841;i V&#259;n D&#361;ng','14/05/2024','14/05/2024',NULL,'B&#7855;c Ninh',NULL),
 (887,'konihu846','e10adc3949ba59abbe56e057f20f883e','nyrjko241@gmail.com','0729891211',0,17,'Nguy&#7877;n Quang &#272;&#7841;o','14/05/2024','14/05/2024',NULL,'Tp. H&#7891; Ch&iacute; Minh',NULL),
 (888,'nonean194','e10adc3949ba59abbe56e057f20f883e','hutoma565@gmail.com','0832068254',0,14,'Nguy&#7877;n Vi&#7879;t Huy','14/05/2024','14/05/2024',NULL,'Ngh&#7879; An',NULL),
 (889,'tonele553','e10adc3949ba59abbe56e057f20f883e','rjtarj833@gmail.com','0267144848',0,59,'Ho&agrave;ng Quang &#272;&#7841;o','14/05/2024','14/05/2024',NULL,'B&#7855;c Ninh',NULL),
 (890,'neanle84','e10adc3949ba59abbe56e057f20f883e','nikany170@gmail.com','0804107642',0,2,'Nguy&#7877;n Quang &#272;&#7841;o','14/05/2024','14/05/2024',NULL,'B&igrave;nh D&#432;&#417;ng',NULL),
 (891,'rjkako881','e10adc3949ba59abbe56e057f20f883e','latoka241@gmail.com','0507201817',0,6,'Kh&#7893;ng H&#7919;u Ph&uacute;c','14/05/2024','14/05/2024',NULL,'B&igrave;nh Thu&#7853;n',NULL),
 (892,'lesato925','e10adc3949ba59abbe56e057f20f883e','korjyo40@gmail.com','0907959174',0,8,'Ho&agrave;ng Th&ugrave;y D&#432;&#417;ng','14/05/2024','14/05/2024',NULL,'B&igrave;nh Thu&#7853;n',NULL),
 (893,'kunehu29','e10adc3949ba59abbe56e057f20f883e','muhurj377@gmail.com','0406697854',0,7,'Kh&#7893;ng Th&agrave;nh Thu&#7853;n','14/05/2024','14/05/2024',NULL,'H&#7843;i Ph&ograve;ng',NULL),
 (894,'tamuni131','e10adc3949ba59abbe56e057f20f883e','lataya682@gmail.com','0993658769',0,83,'V&#361; V&#259;n D&#361;ng','14/05/2024','14/05/2024',NULL,'L&#7841;ng S&#417;n',NULL),
 (895,'kutola360','e10adc3949ba59abbe56e057f20f883e','rjekrj435@gmail.com','0515833003',0,63,'Tri&#7879;u Gia &Acirc;n','14/05/2024','14/05/2024',NULL,'B&igrave;nh D&#432;&#417;ng',NULL),
 (896,'konohi180','e10adc3949ba59abbe56e057f20f883e','huhima51@gmail.com','0549004568',0,17,'Nguy&#7877;n &#272;&#7913;c Th&ocirc;ng','14/05/2024','14/05/2024',NULL,'H&#7843;i Ph&ograve;ng',NULL),
 (897,'huliya107','e10adc3949ba59abbe56e057f20f883e','ekmuhu873@gmail.com','0877940620',0,40,'V&#361; Th&#7883; Thanh Ho&agrave;i','14/05/2024','14/05/2024',NULL,'B&#7855;c Ninh',NULL),
 (898,'nirira119','e10adc3949ba59abbe56e057f20f883e','mulima759@gmail.com','0810214927',0,88,'Nguy&#7877;n Anh','14/05/2024','14/05/2024',NULL,'H&agrave; N&#7897;i',NULL),
 (899,'hukuyu486','e10adc3949ba59abbe56e057f20f883e','munesa914@gmail.com','0473694804',0,96,'Ma Ng&#7885;c Quang','14/05/2024','14/05/2024',NULL,'B&igrave;nh Thu&#7853;n',NULL),
 (900,'yurirj96','e10adc3949ba59abbe56e057f20f883e','humane285@gmail.com','0878818576',0,79,'V&otilde; Ho&agrave;i Thanh','14/05/2024','29/05/2024','','B&igrave;nh Thu&#7853;nnnnn','1716996907750_17.png'),
 (901,'yatoyo370','e10adc3949ba59abbe56e057f20f883e','hisasa405@gmail.com','0708997682',0,26,'Tr&#7883;nh Ho&agrave;i Thanh','14/05/2024','14/05/2024',NULL,'Ngh&#7879; An',NULL),
 (902,'rjliyu148','e10adc3949ba59abbe56e057f20f883e','nelisa588@gmail.com','0435500898',0,56,'L&#432;&#417;ng Th&ugrave;y Dung','14/05/2024','14/05/2024',NULL,'Th&aacute;i Nguy&ecirc;n',NULL),
 (903,'norayo460','e10adc3949ba59abbe56e057f20f883e','neyano822@gmail.com','0816819897',0,31,'L&#7841;i Quang Ph&uacute;c','14/05/2024','14/05/2024',NULL,'Th&aacute;i Nguy&ecirc;n',NULL),
 (904,'noyoek266','e10adc3949ba59abbe56e057f20f883e','linira406@gmail.com','0683650743',0,23,'Tr&#7847;n Nh&#432; Qu&#7923;nh','14/05/2024','14/05/2024',NULL,'Ngh&#7879; An',NULL),
 (905,'himata5','e10adc3949ba59abbe56e057f20f883e','netoyo433@gmail.com','0560963750',0,72,'Ph&#7841;m Th&ugrave;y Dung','14/05/2024','14/05/2024',NULL,'Ph&uacute; Th&#7885;',NULL),
 (906,'lihuek388','e10adc3949ba59abbe56e057f20f883e','kulako546@gmail.com','0616149371',0,16,'V&#361; &#272;&#7913;c Th&ocirc;ng','14/05/2024','14/05/2024',NULL,'H&agrave; N&#7897;i',NULL),
 (907,'tokohi181','e10adc3949ba59abbe56e057f20f883e','niekni102@gmail.com','0671565810',0,80,'Ph&#7841;m Quang &#272;&#7841;o','14/05/2024','14/05/2024',NULL,'Ninh Thu&#7853;n',NULL),
 (908,'nyanrj547','e10adc3949ba59abbe56e057f20f883e','yutaek247@gmail.com','0989643519',0,97,'V&#361; Minh &#272;&#259;ng','14/05/2024','14/05/2024',NULL,'H&agrave; Nam',NULL),
 (909,'lihuhu810','e10adc3949ba59abbe56e057f20f883e','liyari191@gmail.com','0524802107',0,2,'&#272;&#7895; Qu&#7889;c Huy','14/05/2024','14/05/2024',NULL,'Qu&#7843;ng Ninh',NULL),
 (910,'riyato813','e10adc3949ba59abbe56e057f20f883e','yunyni203@gmail.com','0888379381',0,14,'Ma Anh','14/05/2024','14/05/2024',NULL,'B&igrave;nh D&#432;&#417;ng',NULL),
 (911,'katomu739','e10adc3949ba59abbe56e057f20f883e','nomara447@gmail.com','0271433183',0,27,'L&#7841;i Mai H&#432;&#417;ng','14/05/2024','14/05/2024',NULL,'H&agrave; N&#7897;i',NULL),
 (912,'litono248','e10adc3949ba59abbe56e057f20f883e','yorihu910@gmail.com','0615666368',0,28,'Kh&#7893;ng V&#259;n Quang','14/05/2024','14/05/2024',NULL,'L&#7841;ng S&#417;n',NULL),
 (913,'saleka774','e10adc3949ba59abbe56e057f20f883e','riyurj128@gmail.com','0903952077',0,80,'Tri&#7879;u Gia &Acirc;n','14/05/2024','14/05/2024',NULL,'B&igrave;nh Thu&#7853;n',NULL),
 (914,'yarala435','e10adc3949ba59abbe56e057f20f883e','maleya231@gmail.com','0788565305',0,92,'&#272;&#7895; V&#259;n Quang','14/05/2024','14/05/2024',NULL,'B&igrave;nh Thu&#7853;n',NULL),
 (915,'nilany133','e10adc3949ba59abbe56e057f20f883e','tokuek19@gmail.com','0479247886',0,42,'Mai &#272;&#7913;c Th&ocirc;ng','14/05/2024','14/05/2024',NULL,'Tp. H&#7891; Ch&iacute; Minh',NULL),
 (916,'muyuya625','e10adc3949ba59abbe56e057f20f883e','sayuto491@gmail.com','0281025898',0,53,'Nguy&#7877;n Th&#7883; Thanh Ho&agrave;i','14/05/2024','14/05/2024',NULL,'B&igrave;nh D&#432;&#417;ng',NULL),
 (917,'yanene198','e10adc3949ba59abbe56e057f20f883e','tomuyu118@gmail.com','0590007305',0,6,'Mai V&#259;n Ho&agrave;ng','14/05/2024','14/05/2024',NULL,'B&#7855;c Ninh',NULL),
 (918,'lilayu529','e10adc3949ba59abbe56e057f20f883e','nolamu218@gmail.com','0894099190',0,62,'&#272;&#7841;i Vi&#7879;t H&ugrave;ng','14/05/2024','14/05/2024',NULL,'B&#7855;c Ninh',NULL),
 (919,'katoya341','e10adc3949ba59abbe56e057f20f883e','rjekri696@gmail.com','0644351576',0,22,'Tri&#7879;u Th&aacute;i H&#432;ng','14/05/2024','14/05/2024',NULL,'B&igrave;nh Thu&#7853;n',NULL),
 (920,'toriny726','e10adc3949ba59abbe56e057f20f883e','tanyla21@gmail.com','0643269606',0,26,'Ma Trung Hi&#7871;u','14/05/2024','14/05/2024',NULL,'Ph&uacute; Th&#7885;',NULL),
 (921,'neneni437','e10adc3949ba59abbe56e057f20f883e','yanoyo311@gmail.com','0819142384',0,87,'Nguy&#7877;n Gia &Acirc;n','14/05/2024','14/05/2024',NULL,'Qu&#7843;ng Ninh',NULL),
 (922,'latali588','e10adc3949ba59abbe56e057f20f883e','marjri987@gmail.com','0501746984',0,52,'&#272;&#7841;i Th&ugrave;y Dung','14/05/2024','14/05/2024',NULL,'L&#7841;ng S&#417;n',NULL),
 (923,'ekkono755','e10adc3949ba59abbe56e057f20f883e','toyayo44@gmail.com','0344407077',0,12,'Mai Th&agrave;nh Thu&#7853;n','14/05/2024','14/05/2024',NULL,'B&#7855;c Ninh',NULL),
 (924,'kuhine237','e10adc3949ba59abbe56e057f20f883e','ratoma621@gmail.com','0319034504',0,63,'Ph&#7841;m Kim Anh','14/05/2024','14/05/2024',NULL,'V&#297;nh Ph&uacute;c',NULL),
 (925,'kunihi232','e10adc3949ba59abbe56e057f20f883e','sakayu407@gmail.com','0920374345',0,24,'Ho&agrave;ng Th&#7883; Ly','14/05/2024','14/05/2024',NULL,'B&#7855;c Ninh',NULL),
 (926,'noanko883','e10adc3949ba59abbe56e057f20f883e','talera365@gmail.com','0290168362',0,18,'V&#361; Nh&#432; Qu&#7923;nh','14/05/2024','14/05/2024',NULL,'Ninh Thu&#7853;n',NULL),
 (927,'ekrato12','e10adc3949ba59abbe56e057f20f883e','mukohi746@gmail.com','0951812241',0,96,'L&#432;&#417;ng H&#7919;u Ph&uacute;c','14/05/2024','14/05/2024',NULL,'B&igrave;nh Thu&#7853;n',NULL),
 (928,'nynoka918','e10adc3949ba59abbe56e057f20f883e','kolato578@gmail.com','0974824774',0,92,'L&#7841;i Minh Anh','14/05/2024','14/05/2024',NULL,'Ninh Thu&#7853;n',NULL),
 (929,'kuyola24','e10adc3949ba59abbe56e057f20f883e','hianyo584@gmail.com','0700213805',0,14,'Tri&#7879;u V&#259;n Huy','14/05/2024','14/05/2024',NULL,'Tp. H&#7891; Ch&iacute; Minh',NULL),
 (930,'kokone555','e10adc3949ba59abbe56e057f20f883e','ansasa744@gmail.com','0625437344',0,39,'&#272;&#7841;i Th&aacute;i Ph&uacute;c','14/05/2024','14/05/2024',NULL,'Ninh Thu&#7853;n',NULL),
 (931,'hiyota416','e10adc3949ba59abbe56e057f20f883e','yomuli935@gmail.com','0584433293',0,33,'&#272;&#7895; Quang Ph&uacute;c','14/05/2024','14/05/2024',NULL,'B&#7855;c Ninh',NULL),
 (932,'kakomu596','e10adc3949ba59abbe56e057f20f883e','laneto222@gmail.com','0218633333',0,47,'Nguy&#7877;n Minh Hi&#7871;u','14/05/2024','14/05/2024',NULL,'Ph&uacute; Th&#7885;',NULL),
 (933,'yuyato798','e10adc3949ba59abbe56e057f20f883e','nilamu529@gmail.com','0584083150',0,18,'Tr&#7847;n B&#7843;o &Acirc;n','14/05/2024','14/05/2024',NULL,'Tp. H&#7891; Ch&iacute; Minh',NULL),
 (934,'yahine110','e10adc3949ba59abbe56e057f20f883e','hikale961@gmail.com','0193126132',0,27,'Tr&#7883;nh Minh Anh','14/05/2024','14/05/2024',NULL,'H&agrave; N&#7897;i',NULL),
 (935,'rayoek514','e10adc3949ba59abbe56e057f20f883e','rjrihu756@gmail.com','0586056061',0,67,'Cao &#272;&#7913;c Th&ocirc;ng','14/05/2024','14/05/2024',NULL,'Tp. H&#7891; Ch&iacute; Minh',NULL),
 (936,'likota486','e10adc3949ba59abbe56e057f20f883e','karjta44@gmail.com','0144002993',0,63,'Nguy&#7877;n Mai H&#432;&#417;ng','14/05/2024','14/05/2024',NULL,'H&agrave; N&#7897;i',NULL),
 (937,'rimuto803','e10adc3949ba59abbe56e057f20f883e','tokoma815@gmail.com','0125689227',0,21,'V&#361; V&#259;n H&ugrave;ng','14/05/2024','14/05/2024',NULL,'Ph&uacute; Th&#7885;',NULL),
 (938,'tokamu58','e10adc3949ba59abbe56e057f20f883e','tanoan189@gmail.com','0713862626',0,45,'Ph&#7841;m H&#7919;u Ph&uacute;c','14/05/2024','14/05/2024',NULL,'L&#7841;ng S&#417;n',NULL),
 (939,'letoka863','e10adc3949ba59abbe56e057f20f883e','hinyan481@gmail.com','0125708488',0,78,'Kh&#7893;ng V&#259;n D&#361;ng','14/05/2024','14/05/2024',NULL,'Ninh Thu&#7853;n',NULL),
 (940,'mulehu781','e10adc3949ba59abbe56e057f20f883e','kahila474@gmail.com','0603534458',0,3,'Ho&agrave;ng V&#259;n D&#361;ng','14/05/2024','14/05/2024',NULL,'B&igrave;nh Thu&#7853;n',NULL),
 (941,'katato921','e10adc3949ba59abbe56e057f20f883e','larjni334@gmail.com','0257041451',0,14,'Tr&#432;&#417;ng &#272;&#7913;c D&#361;ng','14/05/2024','14/05/2024',NULL,'H&agrave; Nam',NULL),
 (942,'muyumu202','e10adc3949ba59abbe56e057f20f883e','nynele585@gmail.com','0290908878',0,71,'V&otilde; Ng&#7885;c H&#432;ng','14/05/2024','14/05/2024',NULL,'H&#7843;i Ph&ograve;ng',NULL),
 (943,'kunyri668','e10adc3949ba59abbe56e057f20f883e','liekma728@gmail.com','0488837237',0,4,'Kh&#7893;ng Ng&#7885;c H&#432;ng','14/05/2024','14/05/2024',NULL,'B&igrave;nh D&#432;&#417;ng',NULL),
 (944,'rjnimu48','e10adc3949ba59abbe56e057f20f883e','nosaya98@gmail.com','0766091626',0,95,'Tr&#7883;nh &#272;&#7913;c Th&ocirc;ng','14/05/2024','14/05/2024',NULL,'Th&aacute;i Nguy&ecirc;n',NULL),
 (945,'lahuto575','e10adc3949ba59abbe56e057f20f883e','lanika665@gmail.com','0435499808',0,19,'Mai Gia &Acirc;n','14/05/2024','14/05/2024',NULL,'H&#7843;i Ph&ograve;ng',NULL),
 (946,'tatoan753','e10adc3949ba59abbe56e057f20f883e','yurjto368@gmail.com','0452924884',0,62,'&#272;&#7841;i Quang Ph&uacute;c','14/05/2024','14/05/2024',NULL,'B&igrave;nh Thu&#7853;n',NULL),
 (947,'kaekyo797','e10adc3949ba59abbe56e057f20f883e','ririsa612@gmail.com','0782136551',0,44,'Kh&#7893;ng Th&ugrave;y Dung','14/05/2024','14/05/2024',NULL,'Th&aacute;i Nguy&ecirc;n',NULL),
 (948,'mulika966','e10adc3949ba59abbe56e057f20f883e','leanya884@gmail.com','0146182127',0,62,'Ph&#7841;m Th&ugrave;y Dung','14/05/2024','14/05/2024',NULL,'Tp. H&#7891; Ch&iacute; Minh',NULL),
 (949,'yatale819','e10adc3949ba59abbe56e057f20f883e','rikuri17@gmail.com','0130120260',0,32,'Tri&#7879;u V&#259;n &#272;&#7841;i','14/05/2024','14/05/2024',NULL,'H&#7843;i Ph&ograve;ng',NULL),
 (950,'nymuni29','e10adc3949ba59abbe56e057f20f883e','letorj899@gmail.com','0848181449',0,4,'Cao Gia &Acirc;n','14/05/2024','14/05/2024',NULL,'H&#7843;i Ph&ograve;ng',NULL),
 (951,'nikumu620','e10adc3949ba59abbe56e057f20f883e','tokane207@gmail.com','0486283669',0,54,'V&#361; Quang Ph&uacute;c','14/05/2024','14/05/2024',NULL,'H&#7843;i Ph&ograve;ng',NULL),
 (952,'katayu373','e10adc3949ba59abbe56e057f20f883e','rarama185@gmail.com','0745278766',0,28,'Nguy&#7877;n Vi&#7879;t Huy','14/05/2024','14/05/2024',NULL,'Th&aacute;i Nguy&ecirc;n',NULL),
 (953,'mumaek667','e10adc3949ba59abbe56e057f20f883e','yarani837@gmail.com','0162993443',0,58,'V&otilde; Quang Minh','14/05/2024','14/05/2024',NULL,'V&#297;nh Ph&uacute;c',NULL),
 (954,'ekyura972','e10adc3949ba59abbe56e057f20f883e','yaleny53@gmail.com','0742898088',0,18,'&#272;&#7895; Quang &#272;&#7841;o','14/05/2024','14/05/2024',NULL,'Ninh Thu&#7853;n',NULL),
 (955,'sayoli259','e10adc3949ba59abbe56e057f20f883e','letoto298@gmail.com','0249749752',0,45,'Ma Trung Hi&#7871;u','14/05/2024','14/05/2024',NULL,'L&#7841;ng S&#417;n',NULL),
 (956,'lianno537','e10adc3949ba59abbe56e057f20f883e','katoli696@gmail.com','0908667181',0,65,'L&#432;&#417;ng V&#259;n Huy','14/05/2024','14/05/2024',NULL,'V&#297;nh Ph&uacute;c',NULL),
 (957,'sakayu912','e10adc3949ba59abbe56e057f20f883e','latani531@gmail.com','0792953566',0,63,'&#272;&#7841;i Anh','14/05/2024','14/05/2024',NULL,'Ph&uacute; Th&#7885;',NULL),
 (958,'sanile934','e10adc3949ba59abbe56e057f20f883e','laneto323@gmail.com','0643758291',0,56,'Kh&#7893;ng Th&#7883; Thu H&#432;&#7901;ng','14/05/2024','14/05/2024',NULL,'Ngh&#7879; An',NULL),
 (959,'norale542','e10adc3949ba59abbe56e057f20f883e','rirjma998@gmail.com','0687787252',0,71,'Nguy&#7877;n Anh','14/05/2024','14/05/2024',NULL,'H&agrave; N&#7897;i',NULL),
 (960,'nyyayo701','e10adc3949ba59abbe56e057f20f883e','korjne282@gmail.com','0682952251',0,99,'L&#432;&#417;ng V&#259;n Quang','14/05/2024','14/05/2024',NULL,'Qu&#7843;ng Ninh',NULL),
 (961,'hiyany836','e10adc3949ba59abbe56e057f20f883e','anlayo605@gmail.com','0223362994',0,86,'V&otilde; Qu&#7889;c Huy','14/05/2024','14/05/2024',NULL,'V&#297;nh Ph&uacute;c',NULL),
 (962,'rasale280','e10adc3949ba59abbe56e057f20f883e','samuno248@gmail.com','0196153545',0,10,'Cao Vi&#7879;t Huy','14/05/2024','14/05/2024',NULL,'B&igrave;nh Thu&#7853;n',NULL),
 (963,'kunorj364','e10adc3949ba59abbe56e057f20f883e','toleto856@gmail.com','0784150587',0,48,'Tr&#432;&#417;ng Th&aacute;i H&#432;ng','14/05/2024','14/05/2024',NULL,'H&#7843;i Ph&ograve;ng',NULL),
 (964,'maraka150','e10adc3949ba59abbe56e057f20f883e','hirjka432@gmail.com','0158430938',0,6,'Mai Thanh Qu&#7923;nh','14/05/2024','14/05/2024',NULL,'Th&aacute;i Nguy&ecirc;n',NULL),
 (965,'kanehi467','e10adc3949ba59abbe56e057f20f883e','matomu811@gmail.com','0527233893',0,20,'L&#7841;i Th&ugrave;y D&#432;&#417;ng','14/05/2024','14/05/2024',NULL,'H&#7843;i Ph&ograve;ng',NULL),
 (966,'mahuko941','e10adc3949ba59abbe56e057f20f883e','rileno4@gmail.com','0266111707',0,91,'Tr&#432;&#417;ng Thu H&#432;&#417;ng','14/05/2024','14/05/2024',NULL,'B&igrave;nh Thu&#7853;n',NULL),
 (967,'mukula304','e10adc3949ba59abbe56e057f20f883e','ektomu799@gmail.com','0186060542',0,22,'&#272;&#7895; Th&aacute;i H&#432;ng','14/05/2024','14/05/2024',NULL,'V&#297;nh Ph&uacute;c',NULL),
 (968,'yurale989','e10adc3949ba59abbe56e057f20f883e','sayano23@gmail.com','0983443239',0,82,'Ph&#7841;m Th&agrave;nh Thu&#7853;n','14/05/2024','14/05/2024',NULL,'V&#297;nh Ph&uacute;c',NULL),
 (969,'nilele739','e10adc3949ba59abbe56e057f20f883e','ektoya621@gmail.com','0696891121',0,70,'Ma Kh&aacute;nh Linh','14/05/2024','14/05/2024',NULL,'Qu&#7843;ng Ninh',NULL),
 (970,'nyleku510','e10adc3949ba59abbe56e057f20f883e','netoan794@gmail.com','0560898970',0,87,'L&#432;&#417;ng V&#259;n Quang','14/05/2024','14/05/2024',NULL,'B&#7855;c Ninh',NULL),
 (971,'mutoli595','e10adc3949ba59abbe56e057f20f883e','takuli863@gmail.com','0874566562',0,46,'Nguy&#7877;n Th&ugrave;y D&#432;&#417;ng','14/05/2024','14/05/2024',NULL,'Ninh Thu&#7853;n',NULL),
 (972,'likoli0','e10adc3949ba59abbe56e057f20f883e','lilahu602@gmail.com','0305364999',0,21,'L&#7841;i Kh&aacute;nh Linh','14/05/2024','14/05/2024',NULL,'B&igrave;nh Thu&#7853;n',NULL),
 (974,'nekumu635','e10adc3949ba59abbe56e057f20f883e','annema893@gmail.com','0183888152',0,53,'Tr&#7847;n Thanh Ho&agrave;i','14/05/2024','14/05/2024',NULL,'B&igrave;nh Thu&#7853;n',NULL),
 (975,'yunito13','e10adc3949ba59abbe56e057f20f883e','tokuyu797@gmail.com','0659552946',0,33,'Ma Th&#7883; Ly','14/05/2024','14/05/2024',NULL,'Th&aacute;i Nguy&ecirc;n',NULL),
 (976,'huanri132','e10adc3949ba59abbe56e057f20f883e','mulato168@gmail.com','0867606220',0,46,'L&#7841;i Ph&#432;&#417;ng Anh','14/05/2024','14/05/2024',NULL,'Qu&#7843;ng Ninh',NULL),
 (977,'mayarj673','e10adc3949ba59abbe56e057f20f883e','hukuto66@gmail.com','0653392433',0,69,'Ma Vi&#7879;t Huy','14/05/2024','14/05/2024',NULL,'Th&aacute;i Nguy&ecirc;n',NULL),
 (978,'yohisa405','e10adc3949ba59abbe56e057f20f883e','ankuta387@gmail.com','0789452245',0,66,'Tr&#7883;nh &#272;&#7913;c Th&ocirc;ng','14/05/2024','14/05/2024',NULL,'V&#297;nh Ph&uacute;c',NULL),
 (979,'nyyuli861','e10adc3949ba59abbe56e057f20f883e','rasala271@gmail.com','0884574400',0,18,'Tr&#7883;nh Nh&#432; Qu&#7923;nh','14/05/2024','14/05/2024',NULL,'H&#7843;i Ph&ograve;ng',NULL),
 (980,'torali837','e10adc3949ba59abbe56e057f20f883e','letomu409@gmail.com','0267415826',0,88,'Tr&#7847;n Ho&agrave;i Thanh','14/05/2024','14/05/2024',NULL,'L&#7841;ng S&#417;n',NULL),
 (981,'ekhiek54','e10adc3949ba59abbe56e057f20f883e','hiyuhu330@gmail.com','0851320289',0,27,'&#272;&#7841;i Qu&#7889;c Huy','14/05/2024','14/05/2024',NULL,'Ngh&#7879; An',NULL),
 (982,'konine109','e10adc3949ba59abbe56e057f20f883e','torita615@gmail.com','0642566020',0,41,'Tr&#7847;n Kim Anh','14/05/2024','14/05/2024',NULL,'L&#7841;ng S&#417;n',NULL),
 (983,'nonoma531','e10adc3949ba59abbe56e057f20f883e','saniyo734@gmail.com','0410453637',0,5,'V&#361; Kim Anh','14/05/2024','14/05/2024',NULL,'Qu&#7843;ng Ninh',NULL),
 (984,'yutaka990','e10adc3949ba59abbe56e057f20f883e','hukoan649@gmail.com','0535239536',0,36,'L&#432;&#417;ng Kh&aacute;nh Linh','14/05/2024','14/05/2024',NULL,'Ngh&#7879; An',NULL),
 (985,'makoto93','e10adc3949ba59abbe56e057f20f883e','rakoku124@gmail.com','0627107167',0,89,'Ph&#7841;m Tu&#7845;n Anh','14/05/2024','14/05/2024',NULL,'H&#7843;i Ph&ograve;ng',NULL),
 (986,'kusani281','e10adc3949ba59abbe56e057f20f883e','takone943@gmail.com','0591806741',0,63,'Tr&#7847;n Tu&#7845;n Anh','14/05/2024','14/05/2024',NULL,'V&#297;nh Ph&uacute;c',NULL),
 (987,'kutoto348','e10adc3949ba59abbe56e057f20f883e','rihuhi372@gmail.com','0917913980',0,86,'Mai Thu H&#432;&#417;ng','14/05/2024','14/05/2024',NULL,'H&agrave; N&#7897;i',NULL),
 (988,'rihuli220','e10adc3949ba59abbe56e057f20f883e','lisara63@gmail.com','0489668876',0,73,'&#272;&#7841;i Th&#7883; Ly','14/05/2024','14/05/2024',NULL,'B&#7855;c Ninh',NULL),
 (989,'yotori114','e10adc3949ba59abbe56e057f20f883e','yorama393@gmail.com','0686263133',0,97,'Tr&#432;&#417;ng Thu H&#432;&#417;ng','14/05/2024','14/05/2024',NULL,'L&#7841;ng S&#417;n',NULL),
 (990,'kaleku198','e10adc3949ba59abbe56e057f20f883e','husaku28@gmail.com','0805496376',0,87,'V&otilde; Trung Hi&#7871;u','14/05/2024','14/05/2024',NULL,'Ngh&#7879; An',NULL),
 (991,'tomuri627','e10adc3949ba59abbe56e057f20f883e','yunola213@gmail.com','0155044543',0,22,'Ho&agrave;ng Th&aacute;i H&#432;ng','14/05/2024','14/05/2024',NULL,'Qu&#7843;ng Ninh',NULL),
 (992,'lahuno236','e10adc3949ba59abbe56e057f20f883e','totama775@gmail.com','0569644317',0,46,'Nguy&#7877;n V&#259;n Quang','14/05/2024','14/05/2024',NULL,'Ninh Thu&#7853;n',NULL),
 (993,'karato310','e10adc3949ba59abbe56e057f20f883e','kaneta454@gmail.com','0798798205',0,34,'Ma V&#259;n Huy','14/05/2024','14/05/2024',NULL,'Ph&uacute; Th&#7885;',NULL),
 (994,'nytola81','e10adc3949ba59abbe56e057f20f883e','tanosa192@gmail.com','0170225680',0,75,'Mai Minh Anh','14/05/2024','14/05/2024',NULL,'Tp. H&#7891; Ch&iacute; Minh',NULL),
 (995,'rjyuni207','e10adc3949ba59abbe56e057f20f883e','lirihu876@gmail.com','0660252848',0,8,'&#272;&#7895; Ng&#7885;c Quang','14/05/2024','14/05/2024',NULL,'B&#7855;c Ninh',NULL),
 (996,'satoto292','e10adc3949ba59abbe56e057f20f883e','antali67@gmail.com','0766849336',0,75,'&#272;&#7841;i V&#259;n Quang','14/05/2024','14/05/2024',NULL,'H&agrave; N&#7897;i',NULL),
 (998,'yuneyu280','e10adc3949ba59abbe56e057f20f883e','yarayu393@gmail.com','0452977964',0,39,'Nguy&#7877;n Vi&#7879;t Huy','14/05/2024','21/05/2024','','H&agrave; Nam','1716295146968_325.png'),
 (999,'nirale350','e10adc3949ba59abbe56e057f20f883e','leanri748@gmail.com','0811923145',0,32,'L&#7841;i V&#259;n &#272;&#7841;i','14/05/2024','21/05/2024','','B&igrave;nh D&#432;&#417;ng','1716295086999_41.png'),
 (1000,'nyhule103','e10adc3949ba59abbe56e057f20f883e','hiraan48@gmail.com','0906252721',0,7,'L&#7841;i V&#259;n Quang','14/05/2024','30/05/2024','','B&#7855;c Ninh','1717051828050_448.png'),
 (1001,'eknyyo728','e10adc3949ba59abbe56e057f20f883e','letaya497@gmail.com','0326287705',0,75,'Tr&#7883;nh H&#7919;u Ph&uacute;c','14/05/2024','21/05/2024','','B&igrave;nh Thu&#7853;n','1716295023186_947.png'),
 (1003,'huyale949','e10adc3949ba59abbe56e057f20f883e','niliku928@gmail.com','0105856783',0,30,'L&#7841;i V&#259;n Quang','14/05/2024','20/05/2024','','B&#7855;c Ninh','1716201752078_997.png'),
 (1004,'nyrihi730','e10adc3949ba59abbe56e057f20f883e','likari933@gmail.com','0850951895',0,38,'V&#361; Trung Hi&#7871;u','14/05/2024','20/05/2024','','Ngh&#7879; An','1716201823389_319.png'),
 (1006,'user123','e10adc3949ba59abbe56e057f20f883e','nguyenquanghuylt2002@gmail.com','0337219182',0,1,'Nguy&#7877;n Quang Huy','16/05/2024','16/05/2024','','V&#297;nh Ph&uacute;c','1715840867802_968.png'),
 (1007,'linhnhi123','25d55ad283aa400af464c76d713c07ad','linh@123.com','0228291819',0,4,'Tri&#7879;u Th&#7883; Kh&aacute;nh Linh','16/05/2024','20/05/2024','','H&agrave; N&#7897;i','1716201879174_818.png'),
 (1009,'hieu','e10adc3949ba59abbe56e057f20f883e','hieuchuai1325@fff.vn','0281918291',0,0,'Nguy&#7877;n Minh Hi&#7871;u','20/05/2024','20/05/2024','','H&#7843;i Ph&ograve;ng','1716145238535_476.png');
/*!40000 ALTER TABLE `tbluser` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
