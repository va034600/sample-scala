# --- !Ups
create sequence sample_id_seq;

create table "sample"(
	"id" integer not null default nextval('sample_id_seq'),
	"name" varchar(148) not null
);

insert into "sample"("name")values('sample1');
insert into "sample"("name")values('sample2');

# --- !Downs
drop table "sample";

drop sequence sample_id_seq;
