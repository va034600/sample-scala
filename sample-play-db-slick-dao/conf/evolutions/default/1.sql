# --- !Ups
create sequence sample_id_seq;

create table "sample"(
	"id" INTEGER PRIMARY KEY AUTO_INCREMENT,
	"name" varchar(148) not null
);

insert into "sample"("name")values('sample1');
insert into "sample"("name")values('sample2');

# --- !Downs
drop table "sample";
