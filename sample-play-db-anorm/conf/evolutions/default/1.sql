# --- !Ups
create sequence sample_anorm_id_seq;
create table sample_anorm(
	id integer not null default nextval('sample_anorm_id_seq'),
	message varchar(148) not null
);
insert into sample_anorm(message)values('sample1')
# --- !Downs
drop table sample_anorm;
drop sequence sample_anorm_id_seq;
