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

alter table Customers comment '�ϥΪ�';


/*==============================================================*/
/* Table: BookType                                           */
/*==============================================================*/

drop table if exists BookType;

create table BookType(
book_type_id    int not null auto_increment comment '�ѥ�����ID',
book_type_name  varchar(20) default null comment '�����W��',
primary key(book_type_id));


insert into BookType(book_type_name) values
('��Ǥp��'),
('�ӷ~�z�]'),
('���N�]�p'),
('�H�����'),
('�߲z�y��'),
('�v�ЩR�z'),
('��L');

alter table BookType comment '�ѥ�����';


/*==============================================================*/
/* Table: Goods                                                 */
/*==============================================================*/
create table Goods
(
   id                   int not null auto_increment,
   bookName             varchar(300) not null comment '�ѦW',
   price                float comment '�ӫ~���',
   author               varchar(50) comment '�@��',
   translator           varchar(30) comment 'Ķ��',
   publisher            varchar(30) comment '�X����',
   isbn                 bigint comment '��ڼзǮѸ�',
   language             varchar(30) comment '�y��',
   image                varchar(100) comment '�Ϥ�',
   book_type_id         int DEFAULT NULL COMMENT '����',
   primary key (id),
   unique key uk_isbn (isbn)
);

alter table Goods comment '�ӫ~';


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

alter table OrderLineItems comment '�ʪ����~��';


/*==============================================================*/
/* Table: Orders                                                */
/*==============================================================*/
create table Orders
(
   id                   varchar(20) not null,
   order_date           bigint default NULL,
   status               int default 1 comment '1�N���T�{�A0�N��w�T�{',
   total                float,
   primary key (id)
);

alter table OrderLineItems comment '�q��';


alter table OrderLineItems add constraint FK_Relationship_3 foreign key (orderid)
      references Orders (id) on delete restrict on update restrict;

alter table OrderLineItems add constraint FK_Relationship_4 foreign key (goodsid)
      references Goods (id) on delete restrict on update restrict;

alter table Goods add constraint FK_Relationship_5 foreign key(book_type_id)
	references bookType(book_type_id) on delete restrict on update restrict;


