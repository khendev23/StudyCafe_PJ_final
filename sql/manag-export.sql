--------------------------------------------------------
--  파일이 생성됨 - 화요일-5월-16-2023   
--------------------------------------------------------
DROP SEQUENCE "MANAG"."SEQ_NON_USER_NO";
DROP SEQUENCE "MANAG"."SEQ_SC_ORDERLIST";
DROP SEQUENCE "MANAG"."SEQ_SC_ORDERLIST_NO";
DROP SEQUENCE "MANAG"."SEQ_USER_LOG_NO";
DROP SEQUENCE "MANAG"."SEQ_USER_NO";
DROP TABLE "MANAG"."SC_MANAGER" cascade constraints;
DROP TABLE "MANAG"."SC_MENU" cascade constraints;
DROP TABLE "MANAG"."SC_ORDERLIST" cascade constraints;
DROP TABLE "MANAG"."SC_USER" cascade constraints;
DROP TABLE "MANAG"."SC_USER_DEL" cascade constraints;
DROP TABLE "MANAG"."SC_USER_LOG" cascade constraints;
--------------------------------------------------------
--  DDL for Sequence SEQ_NON_USER_NO
--------------------------------------------------------

   CREATE SEQUENCE  "MANAG"."SEQ_NON_USER_NO"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence SEQ_SC_ORDERLIST
--------------------------------------------------------

   CREATE SEQUENCE  "MANAG"."SEQ_SC_ORDERLIST"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence SEQ_SC_ORDERLIST_NO
--------------------------------------------------------

   CREATE SEQUENCE  "MANAG"."SEQ_SC_ORDERLIST_NO"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence SEQ_USER_LOG_NO
--------------------------------------------------------

   CREATE SEQUENCE  "MANAG"."SEQ_USER_LOG_NO"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence SEQ_USER_NO
--------------------------------------------------------

   CREATE SEQUENCE  "MANAG"."SEQ_USER_NO"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Table SC_MANAGER
--------------------------------------------------------

  CREATE TABLE "MANAG"."SC_MANAGER" 
   (	"USER_NAME" VARCHAR2(20 BYTE), 
	"USER_ID" VARCHAR2(50 BYTE), 
	"USER_PW" VARCHAR2(50 BYTE), 
	"USER_PHONE" VARCHAR2(20 BYTE), 
	"USER_BIRTHDAY" DATE, 
	"USER_CHECK_IN" DATE, 
	"USER_CHECK_OUT" DATE, 
	"DEL_DATE" TIMESTAMP (6) DEFAULT systimestamp, 
	"SALES" NUMBER DEFAULT 0
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table SC_MENU
--------------------------------------------------------

  CREATE TABLE "MANAG"."SC_MENU" 
   (	"ORDER_NO" NUMBER, 
	"ORDER_NAME" VARCHAR2(50 BYTE), 
	"CHARGE" NUMBER, 
	"NON_USER_CHARGE" NUMBER
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table SC_ORDERLIST
--------------------------------------------------------

  CREATE TABLE "MANAG"."SC_ORDERLIST" 
   (	"OR_NUM" NUMBER, 
	"USER_ID" VARCHAR2(20 BYTE), 
	"USER_PHONE" VARCHAR2(30 BYTE), 
	"OR_PASSNAME" VARCHAR2(50 BYTE), 
	"OR_BEVNAME" VARCHAR2(20 BYTE), 
	"OR_CHARGE" NUMBER, 
	"OR_NON_USER_CHARGE" NUMBER, 
	"OR_BEVCHARGE" NUMBER, 
	"OR_TOTAL_PAY" NUMBER, 
	"OR_ORDERTIME" TIMESTAMP (6) DEFAULT systimestamp
--    constraints fk_user_phone foreign key(user_phone) references sc_user(user_phone)
--    on delete cascade

   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table SC_USER
--------------------------------------------------------

  CREATE TABLE "MANAG"."SC_USER" 
   (	"USER_NO" NUMBER DEFAULT 1, 
	"USER_NAME" VARCHAR2(20 BYTE), 
	"USER_ID" VARCHAR2(50 BYTE), 
	"USER_PW" VARCHAR2(50 BYTE), 
	"USER_PHONE" VARCHAR2(20 BYTE), 
	"USER_BIRTHDAY" DATE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table SC_USER_DEL
--------------------------------------------------------

  CREATE TABLE "MANAG"."SC_USER_DEL" 
   (	"USER_NO" NUMBER, 
	"USER_NAME" VARCHAR2(20 BYTE), 
	"USER_ID" VARCHAR2(50 BYTE), 
	"USER_PW" VARCHAR2(50 BYTE), 
	"USER_PHONE" VARCHAR2(20 BYTE), 
	"USER_BIRTHDAY" DATE, 
	"USER_DEL_DATE" TIMESTAMP (6) DEFAULT systimestamp
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table SC_USER_LOG
--------------------------------------------------------

  CREATE TABLE "MANAG"."SC_USER_LOG" 
   (	"USER_LOG_NO" NUMBER, 
	"USER_LOG_NAME" VARCHAR2(20 BYTE), 
	"USER_LOG_ID" VARCHAR2(50 BYTE), 
	"USER_LOG_PHONE" VARCHAR2(50 BYTE), 
	"USER_CHECK_IN" TIMESTAMP (6) DEFAULT systimestamp, 
	"USER_CHECK_OUT" TIMESTAMP (6) DEFAULT systimestamp
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
REM INSERTING into MANAG.SC_MANAGER
SET DEFINE OFF;
REM INSERTING into MANAG.SC_MENU
SET DEFINE OFF;
Insert into MANAG.SC_MENU (ORDER_NO,ORDER_NAME,CHARGE,NON_USER_CHARGE) values (1,'2시간 당일권',5000,7000);
Insert into MANAG.SC_MENU (ORDER_NO,ORDER_NAME,CHARGE,NON_USER_CHARGE) values (2,'3시간 당일권',6000,8000);
Insert into MANAG.SC_MENU (ORDER_NO,ORDER_NAME,CHARGE,NON_USER_CHARGE) values (3,'6시간 당일권',9000,10000);
Insert into MANAG.SC_MENU (ORDER_NO,ORDER_NAME,CHARGE,NON_USER_CHARGE) values (4,'12시간 당일권',13000,15000);
Insert into MANAG.SC_MENU (ORDER_NO,ORDER_NAME,CHARGE,NON_USER_CHARGE) values (5,'아메리카노',2000,2000);
Insert into MANAG.SC_MENU (ORDER_NO,ORDER_NAME,CHARGE,NON_USER_CHARGE) values (6,'아이스티',3000,3000);
Insert into MANAG.SC_MENU (ORDER_NO,ORDER_NAME,CHARGE,NON_USER_CHARGE) values (7,'쿨커피믹스',3000,3000);
Insert into MANAG.SC_MENU (ORDER_NO,ORDER_NAME,CHARGE,NON_USER_CHARGE) values (8,'초코라떼',4000,4000);
REM INSERTING into MANAG.SC_ORDERLIST
SET DEFINE OFF;
Insert into MANAG.SC_ORDERLIST (OR_NUM,USER_ID,USER_PHONE,OR_PASSNAME,OR_BEVNAME,OR_CHARGE,OR_NON_USER_CHARGE,OR_BEVCHARGE,OR_TOTAL_PAY,OR_ORDERTIME) values (1,'sky0331','01096319546','12시간 당일권','초코라떼',13000,15000,4000,null,null);
REM INSERTING into MANAG.SC_USER
SET DEFINE OFF;
Insert into MANAG.SC_USER (USER_NO,USER_NAME,USER_ID,USER_PW,USER_PHONE,USER_BIRTHDAY) values (1,'관리자','admin','admin','01012345678',null);
Insert into MANAG.SC_USER (USER_NO,USER_NAME,USER_ID,USER_PW,USER_PHONE,USER_BIRTHDAY) values (2,'유성근','sky0331','0000','01096319546',to_date('96/03/31','RR/MM/DD'));
REM INSERTING into MANAG.SC_USER_DEL
SET DEFINE OFF;
REM INSERTING into MANAG.SC_USER_LOG
SET DEFINE OFF;
--------------------------------------------------------
--  DDL for Index PK_ORDER_NO
--------------------------------------------------------

  CREATE UNIQUE INDEX "MANAG"."PK_ORDER_NO" ON "MANAG"."SC_MENU" ("ORDER_NO") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index PK_USER_PHONE
--------------------------------------------------------

  CREATE UNIQUE INDEX "MANAG"."PK_USER_PHONE" ON "MANAG"."SC_USER" ("USER_PHONE") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index PK_ORDER_NO
--------------------------------------------------------

  CREATE UNIQUE INDEX "MANAG"."PK_ORDER_NO" ON "MANAG"."SC_MENU" ("ORDER_NO") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index PK_USER_PHONE
--------------------------------------------------------

  CREATE UNIQUE INDEX "MANAG"."PK_USER_PHONE" ON "MANAG"."SC_USER" ("USER_PHONE") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Trigger TRIG_SC_USER_DEL
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TRIGGER "MANAG"."TRIG_SC_USER_DEL" 
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
ALTER TRIGGER "MANAG"."TRIG_SC_USER_DEL" ENABLE;
--------------------------------------------------------
--  DDL for Trigger TRIG_SC_USER_LOG_NAME_UPDATE
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TRIGGER "MANAG"."TRIG_SC_USER_LOG_NAME_UPDATE" 
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
ALTER TRIGGER "MANAG"."TRIG_SC_USER_LOG_NAME_UPDATE" ENABLE;
--------------------------------------------------------
--  DDL for Trigger TRIG_SC_USER_DEL
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TRIGGER "MANAG"."TRIG_SC_USER_DEL" 
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
ALTER TRIGGER "MANAG"."TRIG_SC_USER_DEL" ENABLE;
--------------------------------------------------------
--  DDL for Trigger TRIG_SC_USER_LOG_NAME_UPDATE
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TRIGGER "MANAG"."TRIG_SC_USER_LOG_NAME_UPDATE" 
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
ALTER TRIGGER "MANAG"."TRIG_SC_USER_LOG_NAME_UPDATE" ENABLE;
--------------------------------------------------------
--  Constraints for Table SC_MANAGER
--------------------------------------------------------

  ALTER TABLE "MANAG"."SC_MANAGER" MODIFY ("USER_NAME" NOT NULL ENABLE);
  ALTER TABLE "MANAG"."SC_MANAGER" MODIFY ("USER_PW" NOT NULL ENABLE);
  ALTER TABLE "MANAG"."SC_MANAGER" MODIFY ("USER_PHONE" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table SC_MENU
--------------------------------------------------------

  ALTER TABLE "MANAG"."SC_MENU" MODIFY ("ORDER_NO" NOT NULL ENABLE);
  ALTER TABLE "MANAG"."SC_MENU" MODIFY ("ORDER_NAME" NOT NULL ENABLE);
  ALTER TABLE "MANAG"."SC_MENU" ADD CONSTRAINT "PK_ORDER_NO" PRIMARY KEY ("ORDER_NO")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table SC_USER
--------------------------------------------------------

  ALTER TABLE "MANAG"."SC_USER" MODIFY ("USER_NAME" NOT NULL ENABLE);
  ALTER TABLE "MANAG"."SC_USER" ADD CONSTRAINT "PK_USER_PHONE" PRIMARY KEY ("USER_PHONE")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table SC_USER_DEL
--------------------------------------------------------

  ALTER TABLE "MANAG"."SC_USER_DEL" MODIFY ("USER_NAME" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table SC_USER_LOG
--------------------------------------------------------

  ALTER TABLE "MANAG"."SC_USER_LOG" MODIFY ("USER_LOG_NAME" NOT NULL ENABLE);
