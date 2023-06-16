alter session set "_oracle_script" = true;

create user manag
identified by manag
default tablespace users;


grant create session, create table to manag;
grant connect, resource to manag; 

alter user manag quota unlimited on users;

-- 회원 테이블
create table sc_user (
    user_no number default 1,
    user_name varchar2(20) not null,
    user_id varchar2(50),
    user_pw varchar2(50),
    user_phone varchar2(20),
    user_birthday date,
    constraints pk_user_phone primary key(user_phone)
);
create sequence seq_user_no;

insert into sc_user values (seq_user_no.nextval, '관리자', 'admin', 'admin', '01012345678', null);

-- drop table sc_user;
-- drop sequence seq_user_no;
--delete from sc_non_user where non_user_name like ('%1');

-- user_log 테이블
create table sc_user_log (
    user_log_no number,
    user_log_name varchar2(20) not null,
    user_log_id varchar2(50),
    user_log_phone varchar2(50),
    user_check_in timestamp default systimestamp,
    user_check_out timestamp default systimestamp
);

create sequence seq_user_log_no;
--drop sequence seq_user_log_no;

-- user - user_name 수정되면 user_log - user_log_name도 수정되는 트리거
create or replace trigger trig_sc_user_log_name_update
    before
    update on sc_user
    for each row
begin
    if updating then
        update
            sc_user_log
        set
            user_log_name = :new.user_name
        where
            user_log_id = :old.user_id;
    end if;
end;
/

-- drop table sc_user_log;

-- 회원 탈퇴 테이블
create table sc_user_del 
 as
    select 
        u.*, 
        localtimestamp user_del_date  
    from 
        sc_user u
    where 
        1 = 0;
        
-- drop table sc_user_del;
-- 탈퇴테이블 기본값 추가
 alter table sc_user_del
modify user_del_date default systimestamp;
    
select * from sc_user_del;

-- 탈퇴 테이블 트리거
create or replace trigger trig_sc_user_del
    before
    delete on sc_user
    for each row
begin
    if deleting then
        insert into
            sc_user_del
        values(
        :old.user_no, :old.user_id, :old.user_name, :old.user_pw, :old.user_phone, :old.user_birthday, default
        );
    end if;
end;
/

--  메뉴 테이블
create table sc_menu (
    order_no number not null,
    order_name varchar2(50) not null,
    charge number,
    non_user_charge number  , 
    constraints pk_order_no primary key(order_no)
);
select * from sc_menu;
--drop table sc_order;

insert into sc_menu
values (
    '1',
    '2시간 당일권', '5000', '7000'
);

insert into sc_menu
values (
    '2', '3시간 당일권', '6000', '8000'
);

insert into sc_menu
values (
    '3', '6시간 당일권', '9000', '10000'
);

insert into sc_menu
values (
    '4', '12시간 당일권', '13000', '15000'
);

insert into sc_menu
values (
    '5', '아메리카노', '2000', '2000'
);

insert into sc_menu
values (
    '6', '아이스티', '3000','3000'
);

insert into sc_menu
values (
    '7', '쿨커피믹스', '3000','3000'
);

insert into sc_menu
values (
    '8', '초코라떼', '4000','4000'
);

-- 주문내역 테이블
create table sc_orderlist(
    or_num number,
    user_id varchar(20),
    user_phone varchar(30),
    or_passname varchar(50),
    or_bevname varchar(20),
    or_charge number,
    or_non_user_charge number,
    or_bevcharge number,
    or_total_pay number,
    or_ordertime timestamp default systimestamp
);
create sequence seq_sc_orderlist_no;
--drop table sc_orderlist;
-- drop sequence seq_sc_orderlist_no;


select * from sc_user;
select * from sc_user_del;
select * from sc_menu;
select * from sc_orderlist;
select * from sc_user_log;