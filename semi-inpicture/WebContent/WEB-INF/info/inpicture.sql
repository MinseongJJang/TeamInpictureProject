create table inpicture_member(
	id varchar2(100) primary key,
	password varchar2(100) not null,
	name varchar2(100) not null,
	address varchar2(100) not null,
	ssn varchar2(100) not null,
	email varchar2(100) not null,
	point number default 1000000,
	member_type number default 1
)
select * from inpicture_member;

insert into inpicture_member values('test', '1234', '테스트', '서울', '90', 'test@test', 10,1);
insert into inpicture_member values('artist', '1234', '테스트', '서울', '90', 'test@test', 100,2);
insert into inpicture_member values('admin', '1234', '테스트', '서울', '90', 'test@test', 1000,3);

create table artist(
	id varchar2(100) not null,
	artist_intro clob not null,
	constraint fk_artist_id foreign key(id) references inpicture_member(id) on delete cascade 
)
select * from artist;

insert into artist values('test', '나는 일반회원이다');
insert into artist values('artist', '나는 artist다');
insert into artist values('admin', '나는 관리자다');

create table auction_apply(
	auction_no number primary key,
	auction_title varchar2(100) not null,
	auction_content clob not null,
	auction_begin_time date not null,
	auction_end_time date not null,
	auction_main_pic varchar2(100) not null,
	auction_promptly_price number not null,
	id varchar2(100) not null,
	constraint fk_auction_apply_id foreign key(id) references inpicture_member(id) on delete cascade
)

create sequence auction_apply_seq 
start with 1
nocache;

create table artist_apply_board(
	artist_post_no number primary key,
	artist_apply_title varchar2(100) not null,
	artist_apply_content clob not null,
	reg_date date not null,
	artist_main_pic varchar2(100) not null,
	id varchar2(100) not null,
	constraint fk_artist_apply_board_id foreign key(id) references inpicture_member(id) on delete cascade
)


create sequence artist_apply_board_seq
start with 1
nocache
insert into artist_apply_board values(artist_apply_board_seq.nextval, '작가 신청5', '작가 신청 합니다.', sysdate, '사진', 'test'); select artist_apply_board_seq.currval from dual;
insert into artist_apply_board values(artist_apply_board_seq.nextval,'1','1',sysdate,'1','test');
select artist_apply_board_seq.currval from dual;

select * from artist_apply_board;

create table art(
	art_no number primary key,
	art_title varchar2(100) not null,
	art_content clob not null,
	art_main_pic varchar2(100) not null,
	id varchar2(100) not null,
	constraint fk_art_id foreign key(id) references inpicture_member(id) on delete cascade
)

insert into art values(art_seq.nextval, '테스트 작품', '사회의 비판이 주제다', '사진', 'artist');


create sequence art_seq
start with 1
nocache

create table artist_attachment_path(
	artist_attachment_no number primary key,
	artist_attachment_path varchar2(100) not null,
	artist_post_no number not null,
	constraint fk_artist_path_no foreign key(artist_post_no) references artist_apply_board(artist_post_no) on delete cascade
)
select *from artist_attachment_path

create sequence artist_attachment_no_seq
start with 1
nocache

create table art_attachment_path(
	art_attachment_no number primary key,
	art_attachment_path varchar2(100) not null,
	art_no number not null,
	constraint fk_art_attachment_path_art_no foreign key(art_no) references art(art_no) on delete cascade
)

create sequence art_attachment_path_seq
start with 1
nocache

create table auction_attachment_path(
	auction_attachment_no number primary key,
	auction_no number not null,
	auction_attachment_path varchar2(100) not null,
	constraint fk_auction_path_auction_no foreign key(auction_no) references auction_apply(auction_no) on delete cascade
)

create sequence auction_attachment_path_seq 
start with 1
nocache

create table auction(
	auction_no number primary key,
	auction_title varchar2(100) not null,
	auction_begin_time date not null,
	auction_end_time date not null,
	auction_final_bid_price number not null,
	auction_seller varchar2(100) not null,
	auction_promptly_price number not null,
	auction_state number default 0,
	auction_main_pic varchar2(100) not null,
	auction_begin_price number not null,
	auction_final_bidder varchar2(100) not null,
	constraint fk_auction_auction_no foreign key(auction_no) references auction_apply(auction_no) on delete cascade
)

create sequence auction_seq 
start with 1
nocache

create table bid(
	id varchar2(100) not null,
	auction_no number not null,
	auction_bid_price number not null,
	constraint fk_bid_id foreign key(id) references inpicture_member(id) on delete cascade,
	constraint fk_bid_auction_no foreign key(auction_no) references auction(auction_no) on delete cascade,
	constraint pk_bid primary key(id,auction_no)
	)

alter table auction add(auction_final_bidder varchar2(100) not null)
alter table auction drop(auction_final_bidder)



select * from inpicture_member;




