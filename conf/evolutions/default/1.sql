# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table cust (
  id                        serial not null,
  cusname                   varchar(20),
  mno                       bigint,
  pass                      varchar(255),
  totalcoast                bigint,
  noofitemsbuy              integer,
  constraint pk_cust primary key (id))
;

create table items (
  id                        serial not null,
  itemname                  varchar(10),
  price                     integer,
  noofitems                 integer,
  constraint pk_items primary key (id))
;


create table cus_item (
  cust_id                        integer not null,
  items_id                       integer not null,
  constraint pk_cus_item primary key (cust_id, items_id))
;



alter table cus_item add constraint fk_cus_item_cust_01 foreign key (cust_id) references cust (id);

alter table cus_item add constraint fk_cus_item_items_02 foreign key (items_id) references items (id);

# --- !Downs

drop table if exists cust cascade;

drop table if exists cus_item cascade;

drop table if exists items cascade;

