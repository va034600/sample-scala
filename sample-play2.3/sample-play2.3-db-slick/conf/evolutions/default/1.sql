# --- !Ups
create sequence sample_id_seq;

create table "SAMPLE"(
	"ID" integer not null default nextval('sample_id_seq'),
	"NAME" varchar(148) not null
);

insert into "SAMPLE"("NAME")values('sample1');
insert into "SAMPLE"("NAME")values('sample2');

# --- !Downs
drop table "SAMPLE";

drop sequence sample_id_seq;
