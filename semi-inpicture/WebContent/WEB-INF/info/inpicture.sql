create table inpicture_member(
	id varchar2(100) primary key,
	password varchar2(100) not null,
	name varchar2(100) not null,
	address varchar2(100) not null,
	ssn varchar2(100) not null,
	email varchar2(100) not null,
	point number default 50000000,
	member_type number default 1
)
select * from inpicture_member;

insert into inpicture_member values('test', '1234', '테스트', '서울', '90', 'test@test', 10,1);
insert into inpicture_member values('artist', '1234', '테스트', '서울', '90', 'test@test', 100,2);
insert into inpicture_member values('admin', '1234', '테스트', '서울', '90', 'test@test', 1000,3);

delete from inpicture_member where id='admin';

create table artist(
	id varchar2(100) not null,
	artist_intro clob not null,
	constraint fk_artist_id foreign key(id) references inpicture_member(id) on delete cascade 
)

select * from artist;

delete from artist;
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

select a.id, a.artist_intro, a.artist_main_pic, im.id
from ( select id, artist_intro, artist_main_pic row_number() over(order by id desc) as rnum from artist
) a, inpicture_member im where a.id=im.id and rnum between 1 and 5



select a.id, im.name, a.artist_main_pic
from ( select id, artist_main_pic, row_number() over(order by id desc) as rnum from artist
) a, inpicture_member im where im.id=a.id and rnum between 1 and 5


select im.id, im.name, aab.artist_main_pic
from inpicture_member im, artist_apply_board aab
where im.id=aab.id
and im.member_type='1'

SELECT e.emp_no, e.name, e.salary, d.dept_no, d.dname, d.location
FROM department d, emp e
WHERE d.dept_no=e.dept_no
AND e.emp_no='2'



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

select * from artist_apply_board

create sequence artist_apply_board_seq
start with 1
nocache
insert into artist_apply_board values(artist_apply_board_seq.nextval, '작가 신청5', '작가 신청 합니다.', sysdate, '사진', 'test'); select artist_apply_board_seq.currval from dual;
insert into artist_apply_board values(artist_apply_board_seq.nextval,'1','1',sysdate,'1','test');
select artist_apply_board_seq.currval from dual;

select * from artist_apply_board;
delete from artist_apply_board where id='java';
create table art(
	art_no number primary key,
	art_title varchar2(100) not null,
	art_content clob not null,
	art_main_pic varchar2(100) not null,
	id varchar2(100) not null,
	constraint fk_art_id foreign key(id) references inpicture_member(id) on delete cascade
)

insert into art values(art_seq.nextval, '테스트 작품', '사회의 비판이 주제다', '사진', 'artist');

select artist_main_pic from artist_apply_board where id='yy';

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


drop table artist;
create table artist(
   id varchar2(100) not null,
   artist_intro clob not null,
   artist_main_pic varchar2(100) not null,
   constraint fk_artist_id foreign key(id) references inpicture_member(id) on delete cascade 
)
alter table artist add(artist_main_pic varchar2(100));


select a.auction_no,a.auction_title,a.id,m.name,a.auction_state from 
(select auction_no,auction_title,id,row_number() over(order by auction_no desc) as rnum,
auction_state from auction_apply) a 
, inpicture_member m 
where a.id=m.id and rnum between 1 and 5 and a.auction_state = 1 order by a.auction_no desc

delete from auction_apply 


select a.auction_no,a.auction_title,a.auction_main_pic,a.auction_state from
(select auction_no,auction_title,auction_main_pic,row_number() over(order by auction_no desc) as rnum, 
auction_state from auction) a where a.rnum between 1 and 25 and a.auction_state='0' order by a.auction_no desc

select a.art_no,a.art_title,a.art_main_pic,b.id 
	               from( 
	               select row_number() over(order by art_no desc) as rnum,art_no, 
	               art_title,art_main_pic,id from art 
	               )a , artist b where a.id=b.id and rnum between 1 and 5
	               order by a.art_no desc

	               
select a.auction_no,a.auction_title,a.auction_main_pic,a.auction_state from 
						  (select auction_no,auction_title,auction_main_pic,row_number() over(order by auction_no desc) as rnum, 
					      auction_state from auction) a where a.rnum between 1 and 25 and a.auction_state='0' order by a.auction_no desc	               
	        
					      delete from auction;
	               
create table point_history(
	point_history_no number primary key,
	reg_date date not null,
	id varchar2(100) not null,
	payment_method varchar2(100) not null,
	constraint fk_point_id foreign key(id) references inpicture_member(id) on delete cascade
)				
alter table point_history add(point number not null)
create sequence point_history_seq

select point_history_no,to_char(reg_date,'YYYY-MM-DD HH24:MI'),id,payment_method,point from point_history
	               
select point_history_no,to_char(reg_date,'YYYY-MM-DD HH24:MI'),id,payment_method,point from point_history where id ='minseong'	               
	               

delete from art;
delete from auction;
delete from auction_apply;
delete from artist
delete from artist_apply
delete from artist_apply_board
delete from point_history
delete from art
delete from inpicture_member



select a.auction_title,a.auction_content, 
to_char(a.auction_begin_time,'YYYY-MM-DD HH24:MI'), 
to_char(a.auction_end_time,'YYYY-MM-DD HH24:MI'),a.auction_seller,
a.auction_promptly_price,a.auction_state,a.auction_main_pic, 
a.auction_begin_price,m.id,m.name,a.auction_final_bid_price 
from auction a , inpicture_member m 
where a.auction_seller_id = m.id and a.auction_no = 119