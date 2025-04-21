/*==============================================================*/
/* Database: store                                              */
/*==============================================================*/


drop database if exists store;

create database store character set utf8mb4 collate utf8mb4_unicode_520_ci;

use store;


drop table if exists Customers;

drop table if exists Goods;

drop table if exists OrderLineItems;

drop table if exists Orders;


/*==============================================================*/
/* Table: Customers                                             */
/*==============================================================*/
create table Customers
(
   id                   varchar(20) not null,
   name                 varchar(50) not null,
   password             varchar(20) not null,
   address              varchar(100),
   phone                varchar(20),
   birthday             bigint,
   primary key (id)
);

alter table Customers comment '使用者';


/*==============================================================*/
/* Table: BookType                                           */
/*==============================================================*/

drop table if exists BookType;

create table BookType(
book_type_id    int not null auto_increment comment '書本類型ID',
book_type_name  varchar(20) default null comment '類型名稱',
primary key(book_type_id));


insert into BookType(book_type_name) values
('文學小說'),
('商業理財'),
('藝術設計'),
('人文社科'),
('心理勵志'),
('宗教命理'),
('其他');

alter table BookType comment '書本類型';


/*==============================================================*/
/* Table: Goods                                                 */
/*==============================================================*/
create table Goods
(
   id                   int not null auto_increment,
   bookName             varchar(300) not null comment '書名',
   price                float comment '商品單價',
   author               varchar(50) comment '作者',
   translator           varchar(30) comment '譯者',
   publisher            varchar(30) comment '出版社',
   isbn                 bigint comment '國際標準書號',
   language             varchar(30) comment '語言',
   image                varchar(100) comment '圖片',
   book_type_id         int DEFAULT NULL COMMENT '類型',
   primary key (id),
   unique key uk_isbn (isbn)
);

alter table Goods comment '商品';


/*==============================================================*/
/* Table: OrderLineItems                                        */
/*==============================================================*/
create table OrderLineItems
(
   id                   int not null auto_increment,
   goodsid              int not null,
   orderid              varchar(20) not null,
   quantity             int,
   sub_total            float,
   primary key (id)
);

alter table OrderLineItems comment '購物車品項';


/*==============================================================*/
/* Table: Orders                                                */
/*==============================================================*/
create table Orders
(
   id                   varchar(20) not null,
   order_date           bigint default NULL,
   status               int default 1 comment '1代表未確認，0代表已確認',
   total                float,
   primary key (id)
);

alter table OrderLineItems comment '訂單';


alter table OrderLineItems add constraint FK_Relationship_3 foreign key (orderid)
      references Orders (id) on delete restrict on update restrict;

alter table OrderLineItems add constraint FK_Relationship_4 foreign key (goodsid)
      references Goods (id) on delete restrict on update restrict;

alter table Goods add constraint FK_Relationship_5 foreign key(book_type_id)
	references bookType(book_type_id) on delete restrict on update restrict;


