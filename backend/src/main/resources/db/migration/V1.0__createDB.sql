create sequence hibernate_sequence start with 1 increment by 1;



create table user_roles (user_username varchar(255) not null, roles varchar(255));
create table users (username varchar(255) not null, enabled boolean not null, password varchar(255) not null, primary key (username));
create table movie (id bigint not null, director varchar(255), plot varchar(255), title varchar(255), primary key (id));
create table review (rating integer not null check (rating>=1 AND rating<=5), review_text varchar(255), user_username varchar(255) not null, movie_id bigint not null, primary key (user_username, movie_id));
alter table user_roles add constraint FKs9rxtuttxq2ln7mtp37s4clce foreign key (user_username) references users;
alter table review add constraint FKg7yvpb2r266roakbx9ts80fg foreign key (user_username) references users;
alter table review add constraint FK8378dhlpvq0e9tnkn9mx0r64i foreign key (movie_id) references movie;

