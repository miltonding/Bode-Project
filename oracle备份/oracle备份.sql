---------------------------------------------
-- Export file for user SCOTT              --
-- Created by dell1 on 2016/5/28, 12:59:09 --
---------------------------------------------

spool oracle备份.log

prompt
prompt Creating table BONUS
prompt ====================
prompt
create table SCOTT.BONUS
(
  ENAME VARCHAR2(10),
  JOB   VARCHAR2(9),
  SAL   NUMBER,
  COMM  NUMBER
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table COURSES
prompt ======================
prompt
create table SCOTT.COURSES
(
  CNO      NUMBER(3) not null,
  CNAME    VARCHAR2(20) not null,
  CTEACHER VARCHAR2(20)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table SCOTT.COURSES
  add constraint CNO primary key (CNO)
  disable;

prompt
prompt Creating table DEPT
prompt ===================
prompt
create table SCOTT.DEPT
(
  DEPTNO NUMBER(2) not null,
  DNAME  VARCHAR2(14),
  LOC    VARCHAR2(13)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table SCOTT.DEPT
  add constraint PK_DEPT primary key (DEPTNO)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table EMP
prompt ==================
prompt
create table SCOTT.EMP
(
  EMPNO    NUMBER(4) not null,
  ENAME    VARCHAR2(10),
  JOB      VARCHAR2(9),
  MGR      NUMBER(4),
  HIREDATE DATE,
  SAL      NUMBER(7,2),
  COMM     NUMBER(7,2),
  DEPTNO   NUMBER(2)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table SCOTT.EMP
  add constraint PK_EMP primary key (EMPNO)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table SCOTT.EMP
  add constraint FK_DEPTNO foreign key (DEPTNO)
  references SCOTT.DEPT (DEPTNO);
create index SCOTT.INDEX_ENAME on SCOTT.EMP (ENAME)
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table MY_EMPLOYEE
prompt ==========================
prompt
create table SCOTT.MY_EMPLOYEE
(
  ID         VARCHAR2(20) not null,
  FIRST_NAME VARCHAR2(20),
  LAST_NAME  VARCHAR2(20),
  USERID     VARCHAR2(20),
  SALARY     VARCHAR2(20)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table SCOTT.MY_EMPLOYEE
  add primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table READER
prompt =====================
prompt
create table SCOTT.READER
(
  LIBRAY_CARD_ID NUMBER(3) not null,
  COMPANY        NUMBER(3),
  RNAME          VARCHAR2(20),
  SEX            CHAR(2),
  LEVER          VARCHAR2(20),
  ADDRESS        VARCHAR2(20)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table SCOTT.READER
  add primary key (LIBRAY_CARD_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table SCOTT.READER
  add check (sex IN('男','女'));

prompt
prompt Creating table SELECT_COURSE
prompt ============================
prompt
create table SCOTT.SELECT_COURSE
(
  SNO     NUMBER(3) not null,
  CNO     NUMBER(3) not null,
  SCGRADE NUMBER(3)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table SCOTT.SELECT_COURSE
  add constraint SELECT_CNO foreign key (CNO)
  references SCOTT.COURSES (CNO) on delete set null
  disable;

prompt
prompt Creating table STUDENT
prompt ======================
prompt
create table SCOTT.STUDENT
(
  SNO  NUMBER(3) not null,
  NAME VARCHAR2(20) not null,
  SEX  CHAR(2),
  AGE  NUMBER(3)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table SCOTT.STUDENT
  add primary key (SNO)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table SCOTT.STUDENT
  add check (sex IN('男','女'));
alter table SCOTT.STUDENT
  add check (age>10 AND age<30 );

prompt
prompt Creating table T_MOBILE
prompt =======================
prompt
create table SCOTT.T_MOBILE
(
  TEL_NUMB        VARCHAR2(11) not null,
  TEL_TYPE        VARCHAR2(20),
  TEL_ADD         VARCHAR2(20),
  ACC_INIT_AMOUNT NUMBER,
  IS_SALE         CHAR(1)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table SCOTT.T_MOBILE
  add constraint PK_TEL_MOBILE primary key (TEL_NUMB)
  disable;

prompt
prompt Creating table T_ACCOUNT
prompt ========================
prompt
create table SCOTT.T_ACCOUNT
(
  ACCOUNT_ID      NUMBER not null,
  TEL_NUMB        VARCHAR2(11) not null,
  ACCOUNT_BALANCE NUMBER
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table SCOTT.T_ACCOUNT
  add constraint PK_ID_ACCOUNT primary key (ACCOUNT_ID)
  disable;
alter table SCOTT.T_ACCOUNT
  add constraint FK_FGJNIOEFGJH foreign key (TEL_NUMB)
  references SCOTT.T_MOBILE (TEL_NUMB) on delete set null
  disable;

prompt
prompt Creating table T_BUSINESS_FEE
prompt =============================
prompt
create table SCOTT.T_BUSINESS_FEE
(
  BUSINESS_ID     NUMBER not null,
  BUSINESS_NAME   VARCHAR2(20),
  SHORT_NAME      VARCHAR2(20),
  BUSINESS_CHARGE NUMBER,
  IS_OPTIONAL     CHAR(1),
  IS_LARGESS      CHAR(1),
  EFFECTIVE_TIME  NUMBER(1),
  END_TIME        NUMBER(2)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table SCOTT.T_BUSINESS_FEE
  add constraint BUSINEE_FEE_ID primary key (BUSINESS_ID)
  disable;

prompt
prompt Creating table T_CUSTOMER
prompt =========================
prompt
create table SCOTT.T_CUSTOMER
(
  CUSTOMER_ID       NUMBER not null,
  CUSTOMER_USERNAME VARCHAR2(20),
  CUSTOMER_NAME     VARCHAR2(20),
  ID_CARD_NUMB      VARCHAR2(18),
  TEL_NUMB          VARCHAR2(11) not null,
  CUSTOMER_BIRTHDAY DATE,
  CUSTOMER_PWD      VARCHAR2(20)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table SCOTT.T_CUSTOMER
  add constraint PK_ID_CUSTOMER primary key (CUSTOMER_ID)
  disable;

prompt
prompt Creating table T_PHONE_PACKAGE
prompt ==============================
prompt
create table SCOTT.T_PHONE_PACKAGE
(
  PP_ID        NUMBER not null,
  PP_NAME      VARCHAR2(20),
  PP_FEE       NUMBER,
  IS_AVALIABLE CHAR(1)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table SCOTT.T_PHONE_PACKAGE
  add constraint PACK_PHONE_PP_ID primary key (PP_ID)
  disable;

prompt
prompt Creating table T_MOBILE_PACKAGE
prompt ===============================
prompt
create table SCOTT.T_MOBILE_PACKAGE
(
  TEL_PACKAGE_ID NUMBER,
  TEL_NUMB       VARCHAR2(11),
  BUSINESS_ID    NUMBER,
  PP_ID          NUMBER,
  START_TIME     DATE,
  END_TIME       DATE,
  STATUS         NUMBER(1)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table SCOTT.T_MOBILE_PACKAGE
  add constraint T_MOBILE_PACKAGE_1 primary key (TEL_PACKAGE_ID)
  disable;
alter table SCOTT.T_MOBILE_PACKAGE
  add constraint T_MOBILE_PACKAGE_2 foreign key (PP_ID)
  references SCOTT.T_PHONE_PACKAGE (PP_ID) on delete set null
  disable;
alter table SCOTT.T_MOBILE_PACKAGE
  add constraint T_MOBILE_PACKAGE_3 foreign key (BUSINESS_ID)
  references SCOTT.T_BUSINESS_FEE (BUSINESS_ID) on delete set null
  disable;

prompt
prompt Creating table T_PACKAGE_BUSINESS
prompt =================================
prompt
create table SCOTT.T_PACKAGE_BUSINESS
(
  PP_ID       NUMBER not null,
  BUSINESS_ID NUMBER not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table SCOTT.T_PACKAGE_BUSINESS
  add constraint PK_BUSINESS_ID primary key (PP_ID, BUSINESS_ID)
  disable;
alter table SCOTT.T_PACKAGE_BUSINESS
  add constraint PK_BUSINESS_BUS_ID foreign key (BUSINESS_ID)
  references SCOTT.T_BUSINESS_FEE (BUSINESS_ID) on delete set null
  disable;
alter table SCOTT.T_PACKAGE_BUSINESS
  add constraint PK_BUSINESS_PP_ID foreign key (PP_ID)
  references SCOTT.T_PHONE_PACKAGE (PP_ID) on delete set null
  disable;

prompt
prompt Creating table T_PREFERENTIAL_INFOR
prompt ===================================
prompt
create table SCOTT.T_PREFERENTIAL_INFOR
(
  PREFERENTIAL_ID   NUMBER not null,
  PREFERENTIAL_NAME VARCHAR2(20),
  TEL_CHARGE        NUMBER,
  DISCOUNT_AMOUNT   NUMBER,
  START_TIME        DATE,
  ENDTIME           DATE,
  IS_AVAILABLE      CHAR(1)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table SCOTT.T_PREFERENTIAL_INFOR
  add constraint PREFERENTIAL_ID_111 primary key (PREFERENTIAL_ID)
  disable;

prompt
prompt Creating table T_RECHARGE_CARD
prompt ==============================
prompt
create table SCOTT.T_RECHARGE_CARD
(
  CARD_ID      NUMBER not null,
  CARD_PWD     VARCHAR2(20),
  CARD_CHARGE  NUMBER(3),
  IS_AVAILABLE CHAR(1)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table SCOTT.T_RECHARGE_CARD
  add constraint CARD_ID_1111 primary key (CARD_ID)
  disable;

prompt
prompt Creating table T_RECHARGE_TYPE
prompt ==============================
prompt
create table SCOTT.T_RECHARGE_TYPE
(
  RECHARGE_TYPE_ID   NUMBER not null,
  RECHARGE_TYPE_NAME VARCHAR2(20)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table SCOTT.T_RECHARGE_TYPE
  add constraint RECHARGE_TYPE_ID_1111 primary key (RECHARGE_TYPE_ID)
  disable;

prompt
prompt Creating table T_RECHARGE_INFOR
prompt ===============================
prompt
create table SCOTT.T_RECHARGE_INFOR
(
  RECHARGE_INFOR_ID NUMBER,
  TEL_NUMB          VARCHAR2(11),
  RECHARGE_TIME     DATE,
  RECHARGE_TYPE_ID  NUMBER,
  CARD_ID           NUMBER,
  BANK_CARD_NUMB    NUMBER,
  PREFERENTIAL_ID   NUMBER,
  DISCOUNT_AMOUNT   NUMBER,
  RECHARGE_MONEY    NUMBER
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table SCOTT.T_RECHARGE_INFOR
  add constraint RECHARGE_TYPE_ID_111 primary key (RECHARGE_INFOR_ID)
  disable;
alter table SCOTT.T_RECHARGE_INFOR
  add constraint RECHARGE_TYPE_ID_2222 foreign key (CARD_ID)
  references SCOTT.T_RECHARGE_CARD (CARD_ID) on delete set null
  disable;
alter table SCOTT.T_RECHARGE_INFOR
  add constraint RECHARGE_TYPE_ID_3333 foreign key (RECHARGE_TYPE_ID)
  references SCOTT.T_RECHARGE_TYPE (RECHARGE_TYPE_ID) on delete set null
  disable;
alter table SCOTT.T_RECHARGE_INFOR
  add constraint RECHARGE_TYPE_ID_4444 foreign key (PREFERENTIAL_ID)
  references SCOTT.T_PREFERENTIAL_INFOR (PREFERENTIAL_ID) on delete set null
  disable;
alter table SCOTT.T_RECHARGE_INFOR
  add constraint RECHARGE_TYPE_ID_5555 foreign key (TEL_NUMB)
  references SCOTT.T_MOBILE (TEL_NUMB) on delete set null
  disable;

prompt
prompt Creating table T_USER
prompt =====================
prompt
create table SCOTT.T_USER
(
  ID   VARCHAR2(20) not null,
  PWD  VARCHAR2(20),
  NAME VARCHAR2(20)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table SCOTT.T_USER
  add constraint PK_ID primary key (ID)
  disable;

prompt
prompt Creating sequence CUSTOMER_SEQ
prompt ==============================
prompt
create sequence SCOTT.CUSTOMER_SEQ
minvalue 1000
maxvalue 2000
start with 1060
increment by 1
cache 20;

prompt
prompt Creating sequence EMPNO_SEQ
prompt ===========================
prompt
create sequence SCOTT.EMPNO_SEQ
minvalue 8000
maxvalue 9000
start with 8002
increment by 2
nocache;

prompt
prompt Creating sequence EMP_SEQ
prompt =========================
prompt
create sequence SCOTT.EMP_SEQ
minvalue 1
maxvalue 1000
start with 21
increment by 1
cache 20;

prompt
prompt Creating sequence MOBILE_PACK_SEQ
prompt =================================
prompt
create sequence SCOTT.MOBILE_PACK_SEQ
minvalue 1200
maxvalue 3000
start with 1320
increment by 1
cache 20;

prompt
prompt Creating sequence PAYMOBILE_SEQ
prompt ===============================
prompt
create sequence SCOTT.PAYMOBILE_SEQ
minvalue 1200
maxvalue 2000
start with 1280
increment by 1
cache 20;

prompt
prompt Creating sequence T_MOBILE_SEQ
prompt ==============================
prompt
create sequence SCOTT.T_MOBILE_SEQ
minvalue 1300
maxvalue 2000
start with 1360
increment by 1
cache 20;

prompt
prompt Creating sequence T_RECCHARGE_INFOR_SEQ
prompt =======================================
prompt
create sequence SCOTT.T_RECCHARGE_INFOR_SEQ
minvalue 1200
maxvalue 2500
start with 1320
increment by 1
cache 20;

prompt
prompt Creating view NOTSAL_MOBILE
prompt ===========================
prompt
CREATE OR REPLACE VIEW SCOTT.NOTSAL_MOBILE AS
SELECT tel_numb,tel_type,tel_add,acc_init_amount FROM t_mobile WHERE is_sale='0'
WITH READ ONLY;

prompt
prompt Creating view T_ALLBUSINESS
prompt ===========================
prompt
CREATE OR REPLACE VIEW SCOTT.T_ALLBUSINESS AS
SELECT p.pp_id,p.pp_name,p.pp_fee,f.short_name
FROM (SELECT *
     FROM t_package_business b
      WHERE b.business_id NOT IN
                         (SELECT f.business_id
                          FROM t_business_fee f
                          WHERE f.is_largess=0)
                          ORDER BY pp_id) b
JOIN  t_phone_package p
ON b.pp_id=p.pp_id
JOIN t_business_fee f
ON b.business_id=f.business_id
WHERE p.is_avaliable=1
AND f.is_largess=1
ORDER BY p.pp_id;

prompt
prompt Creating view T_BUSINESS
prompt ========================
prompt
CREATE OR REPLACE VIEW SCOTT.T_BUSINESS AS
SELECT p.pp_id,p.pp_name,p.pp_fee,f.short_name
FROM (SELECT *
     FROM t_package_business b
      WHERE b.business_id NOT IN
                         (SELECT f.business_id
                          FROM t_business_fee f
                          WHERE f.is_largess=0)
                          ORDER BY pp_id) b
JOIN  t_phone_package p
ON b.pp_id=p.pp_id
JOIN t_business_fee f
ON b.business_id=f.business_id
WHERE p.is_avaliable=1
AND f.is_largess=1
ORDER BY p.pp_id;

prompt
prompt Creating view T_FREEBUSINESS
prompt ============================
prompt
CREATE OR REPLACE VIEW SCOTT.T_FREEBUSINESS AS
SELECT m.tel_numb,f.business_name,f.business_charge,TO_CHAR(m.start_time,'yyyy-mm-dd') start_time,TO_CHAR(m.end_time,'YYYY-MM-DD') end_time,f.is_optional,f.is_largess
FROM t_mobile_package m
 JOIN t_package_business b
ON m.pp_id=b.pp_id
JOIN t_business_fee f
ON b.business_id=f.business_id
AND is_optional=0
ORDER BY f.business_id DESC;

prompt
prompt Creating view T_PERFERENTIAL
prompt ============================
prompt
CREATE OR REPLACE VIEW SCOTT.T_PERFERENTIAL AS
SELECT t.tel_charge,t.discount_amount ,(t.tel_charge+t.discount_amount) sum_amount,t.preferential_name,t.preferential_id
  FROM t_preferential_infor t
   WHERE t.is_available=1
   AND SYSDATE BETWEEN t.start_time AND t.endtime;

prompt
prompt Creating package PACKAGE_DELETE
prompt ===============================
prompt
CREATE OR REPLACE PACKAGE SCOTT.package_delete
IS
FUNCTION fn_emp_number(v_deptno emp.deptno%TYPE) RETURN NUMBER;

 PROCEDURE delete_deptno(v_deptno  IN emp.deptno%TYPE,
   v_result  NUMBER);

END package_delete;
/

prompt
prompt Creating package PACK_DELE_EMP
prompt ==============================
prompt
CREATE OR REPLACE PACKAGE SCOTT.pack_dele_emp
IS
--记录类型
TYPE emp_record_type  IS RECORD(
v_empno emp.empno%TYPE,
v_ename emp.ename%TYPE,
v_job emp.job%TYPE,
v_sal emp.sal%TYPE
);

--游标类型
TYPE ref_cursor_type IS REF CURSOR RETURN  emp_record_type ;


PROCEDURE pro_dele_emp(v_empno IN emp.empno%TYPE,
ref_cursor OUT  ref_cursor_type ,   
v_result OUT NUMBER
  
  );


END pack_dele_emp;
/

prompt
prompt Creating package PACK_LOGIN
prompt ===========================
prompt
create or replace package scott.pack_Login is


  function fn_login(v_username IN t_user.id%TYPE,
  v_pwd IN t_user.pwd%TYPE,
  v_name OUT  t_user.name%TYPE)
  RETURN  NUMBER;

end ;
/

prompt
prompt Creating package PACK_ONE
prompt =========================
prompt
create or replace package scott.pack_one is

TYPE emp_cursor_type IS REF CURSOR;


PROCEDURE pro_one( emp_cursor OUT emp_cursor_type);

end ;
/

prompt
prompt Creating package PACK_SELMOBILE
prompt ===============================
prompt
CREATE OR REPLACE PACKAGE SCOTT.pack_selMobile
IS
--记录类型
TYPE mobile_record_type  IS RECORD(
v_telno notsal_mobile.tel_numb%TYPE,
v_add notsal_mobile.tel_add%TYPE,
v_account  notsal_mobile.acc_init_amount%TYPE
);


--游标类型
TYPE ref_cursor_type IS REF CURSOR RETURN  mobile_record_type ;


PROCEDURE pro_dele_emp(
 v_add IN  notsal_mobile.tel_add%TYPE,
 v_account IN notsal_mobile.acc_init_amount%TYPE,
 v_begin IN VARCHAR2,
 v_type IN notsal_mobile.tel_type%TYPE,
  v_number IN VARCHAR2,
  v_startindex IN NUMBER,
  v_endindex IN NUMBER,
 --返回游标
 v_resultset OUT ref_cursor_type
  );
  
  --查找记录数
  PROCEDURE pro_count_mobile(
 v_add IN  notsal_mobile.tel_add%TYPE,
 v_account IN notsal_mobile.acc_init_amount%TYPE,
 v_begin IN VARCHAR2,
 v_type IN notsal_mobile.tel_type%TYPE,
  v_number IN VARCHAR2,
 v_count OUT NUMBER

  );


END pack_selMobile;
/

prompt
prompt Creating function FN_CUSTOMER_LOGIN
prompt ===================================
prompt
CREATE OR REPLACE FUNCTION SCOTT.fn_customer_login(
v_id  IN t_user.id%TYPE,
v_pwd IN t_user.pwd%TYPE,
v_name OUT t_user.name%TYPE

)
RETURN NUMBER
IS
v_count NUMBER(3);
BEGIN
  --账号是否存在
SELECT  COUNT(1) INTO v_count
FROM t_customer
WHERE Customer_username=v_id;
--账号不存在
IF v_count=0 THEN   
RETURN -2;
--账号存在,查出姓名
ELSE 
SELECT t_customer.customer_name INTO v_name
FROM t_customer
WHERE Customer_username=v_id
AND Customer_pwd=v_pwd;
 RETURN 1;
END IF;



EXCEPTION
  WHEN NO_DATA_FOUND
    THEN 
RETURN 0; 
WHEN 
  OTHERS 
  THEN 
RETURN -1; 

END;
/

prompt
prompt Creating function FN_EMPNO_COUNT
prompt ================================
prompt
CREATE OR REPLACE FUNCTION SCOTT.fn_empno_count(
v_deptno  IN emp.deptno%TYPE,
v_sum OUT NUMBER
)
RETURN NUMBER
IS


BEGIN

SELECT   COUNT(1) INTO v_sum
FROM emp
WHERE deptno=v_deptno;

RETURN 1;

EXCEPTION
WHEN OTHERS 
THEN RETURN -1;
  
END;
/

prompt
prompt Creating function FN_EMP_ANNUAL_COMM
prompt ====================================
prompt
CREATE OR REPLACE FUNCTION SCOTT.fn_emp_annual_comm(
v_empno  IN emp.empno%TYPE,
v_ename  OUT emp.ename%TYPE,
v_job OUT emp.job%TYPE,
v_annualsal OUT  emp.sal%TYPE,
v_comm  OUT emp.comm%TYPE 
)
RETURN NUMBER

IS 

BEGIN
SELECT ename,job,sal*12,NVL(comm,0)  INTO v_ename,v_job,v_annualsal,v_comm
FROM emp 
WHERE empno=v_empno;
RETURN 1;


EXCEPTION
WHEN OTHERS THEN
  RETURN -1;  

END;
/

prompt
prompt Creating function FN_EMP_NUMBER
prompt ===============================
prompt
CREATE OR REPLACE FUNCTION SCOTT.fn_emp_number(
  v_deptno IN emp.deptno%TYPE)
 RETURN NUMBER
 IS
 v_count NUMBER;
BEGIN
SELECT COUNT(1) INTO v_count
FROM emp
WHERE deptno= v_deptno;
RETURN  v_count;
 
END fn_emp_number;
/

prompt
prompt Creating function FN_LOGIN
prompt ==========================
prompt
CREATE OR REPLACE FUNCTION SCOTT.fn_login(
v_id  IN t_user.id%TYPE,
v_pwd IN t_user.pwd%TYPE,
v_name OUT t_user.name%TYPE

)
RETURN NUMBER
IS
v_count NUMBER(3);
BEGIN
  --账号是否存在
SELECT  COUNT(1) INTO v_count
FROM t_customer
WHERE Customer_username=v_id;
--账号不存在
IF v_count=0 THEN   
RETURN -2;
--账号存在,查出姓名
ELSE 
SELECT t_customer.customer_name INTO v_name
FROM t_customer
WHERE Customer_username=v_id
AND Customer_pwd=v_pwd;
 RETURN 1;
END IF;



EXCEPTION
  WHEN NO_DATA_FOUND
    THEN 
RETURN 0; 
WHEN 
  OTHERS 
  THEN 
RETURN -1; 

END;
/

prompt
prompt Creating function FN_SAL
prompt ========================
prompt
CREATE OR REPLACE FUNCTION SCOTT.fn_sal(
v_deptno  IN emp.deptno%TYPE,
v_sum OUT NUMBER,
v_avg OUT NUMBER
)

RETURN  NUMBER
IS


BEGIN
SELECT SUM(sal),AVG(sal) INTO v_sum,v_avg
FROM emp
WHERE deptno=  v_deptno;

RETURN 1;

EXCEPTION
WHEN OTHERS 
THEN RETURN -1;
END;
/

prompt
prompt Creating procedure DELETE1_DEPTNO
prompt =================================
prompt
CREATE OR REPLACE PROCEDURE SCOTT.delete1_deptno(v_deptno  IN emp.deptno%TYPE)
  
IS
v_deptno emp.deptno%TYPE;
v_count NUMBER;
NOT_FOUND_DEPTNO EXCEPTION;
BEGIN
  
v_count:=fn_emp_number(v_deptno);
--部门下有员工
IF v_count!=0 THEN 
 --将员工的部门编号设置为null
UPDATE emp
SET deptno=NULL
WHERE empno IN  (SELECT empno
                FROM emp
                WHERE deptno=v_deptno);
END IF;
--然后再删
  DELETE 
  FROM dept
  WHERE deptno=v_deptno;
  IF
  SQL%NOTFOUND
  THEN
   RAISE  NOT_FOUND_DEPTNO;
  
  END IF;
  COMMIT;
    
 EXCEPTION
   WHEN  NOT_FOUND_DEPTNO
     THEN ROLLBACK;
     WHEN OTHERS 
       THEN dbms_output.put_line(SQLCODE||SQLERRM);
       ROLLBACK;

END delete1_deptno;
/

prompt
prompt Creating procedure DELETE_DEPTNO
prompt ================================
prompt
CREATE OR REPLACE PROCEDURE SCOTT.delete_deptno(v_deptno  IN emp.deptno%TYPE)
  
IS
v_count NUMBER;
NOT_FOUND_DEPTNO EXCEPTION;
BEGIN
  
v_count:=fn_emp_number(v_deptno);
--部门下有员工
IF v_count!=0 THEN 
 --将员工的部门编号设置为null
UPDATE emp
SET deptno=NULL
WHERE empno IN  (SELECT empno
                FROM emp
                WHERE deptno=v_deptno);
END IF;
--然后再删
  DELETE 
  FROM dept
  WHERE deptno=v_deptno;
  
  IF
  SQL%NOTFOUND
  THEN
   RAISE  NOT_FOUND_DEPTNO;
  
  END IF;
  
  COMMIT;
    
 EXCEPTION
   WHEN  NOT_FOUND_DEPTNO
     THEN ROLLBACK;
     WHEN OTHERS 
       THEN dbms_output.put_line(SQLCODE||SQLERRM);
       ROLLBACK;

END;
/

prompt
prompt Creating procedure PRO_ADDCUSTOMER
prompt ==================================
prompt
create or replace procedure scott.pro_addCustomer
(
v_username IN t_customer.customer_username%TYPE,
v_dname IN  t_customer.customer_name%TYPE,
v_cardno IN  t_customer.id_card_numb%TYPE,
v_telnumb IN t_customer.tel_numb%TYPE,
v_birth IN VARCHAR2,
v_pwd IN t_customer.customer_pwd%TYPE,
v_result OUT NUMBER
)
 is
 
begin
  
 INSERT INTO T_CUSTOMER VALUES(CUSTOMER_SEQ.NEXTVAL,v_username,v_dname,v_cardno,v_telnumb,TO_DATE(v_birth,'yyyy-mm-dd'),v_pwd);
 
 IF
  SQL%NOTFOUND 
   THEN v_result:=0;
  ELSE
  v_result:=1;
 END IF;  


end ;
/

prompt
prompt Creating procedure PRO_CARDPAY
prompt ==============================
prompt
create or replace procedure scott.pro_cardPay
(
v_tel_numb IN t_mobile.tel_numb%TYPE,
v_card_id IN t_recharge_card.card_id%TYPE,
v_card_pwd IN t_recharge_card.card_pwd%TYPE,
v_result OUT NUMBER
) 

is
v_count NUMBER :=0;
v_card_charge NUMBER:=0;

begin
  
--验证充值卡
SELECT COUNT(1) INTO v_count
FROM t_recharge_card t
WHERE t.card_id=v_card_id
AND t.card_pwd=v_card_pwd
AND Is_available=1;


  --验证失败
IF v_count=0
THEN v_result:=-1;
 --验证成功,修改Is_availbale
ELSE 
UPDATE t_recharge_card t
SET t.is_available=0
WHERE t.card_id=v_card_id;


--查出t_recharge_card 的card_charge
SELECT  t.card_charge INTO  v_card_charge
FROM t_recharge_card t
WHERE t.card_id=v_card_id;

--写入t_recharge_infor表
INSERT INTO t_recharge_infor
VALUES(T_RECCHARGE_INFOR_SEQ.NEXTVAL,v_tel_numb,SYSDATE,1002,
        v_card_id,123456,NULL,0,v_card_charge);
        
--修改t_account的余额
UPDATE t_account t
SET account_balance=account_balance+v_card_charge
WHERE tel_numb=v_tel_numb;

COMMIT;
v_result:=1;

END IF;

  
end pro_cardPay;
/

prompt
prompt Creating procedure PRO_CLOSEBUSINESS
prompt ====================================
prompt
create or replace procedure scott.pro_closebusiness
( v_business_id IN t_mobile_package.business_id%TYPE,
  v_tel_numb IN t_mobile_package.tel_numb%TYPE,
  v_result OUT NUMBER
) 

is
v_start_time t_mobile_package.start_time%TYPE:=NULL;
v_end_time  t_mobile_package.end_time% TYPE:=NULL;
v_calcute_end t_business_fee.end_time%TYPE:='0';
begin
  
--看是否能关闭业务
SELECT m.start_time,m.end_time INTO v_start_time,v_end_time
FROM t_mobile_package  m
WHERE m.business_id=v_business_id
AND m.tel_numb=v_tel_numb;

--可修改
IF v_end_time=NULL
THEN
 --计算end_time 
SELECT f.end_time INTO v_calcute_end
FROM t_business_fee f
WHERE f.business_id=v_business_id;

 v_end_time:=add_months(v_start_time,1);

--更新
UPDATE t_mobile_package m
SET m.status=0,m.end_time=v_end_time
WHERE m.business_id=v_business_id
AND m.tel_numb=v_tel_numb;

COMMIT;
v_result:=1;
 ELSE  
  UPDATE t_mobile_package m
SET m.status=0
WHERE m.business_id=v_business_id
AND m.tel_numb=v_tel_numb;
v_result:=-1;
END IF;

end pro_closebusiness;
/

prompt
prompt Creating procedure PRO_FINDALLBYID
prompt ==================================
prompt
create or replace procedure scott.pro_findAllBYID
( v_username IN t_customer.customer_username%TYPE,
  v_telno OUT t_customer.tel_numb%TYPE,
  v_balance OUT t_account.account_balance%TYPE
)

 is
 
 
begin
  SELECT  c.tel_numb,a.account_balance INTO  v_telno ,v_balance
FROM t_mobile m
JOIN t_customer c
ON  m.tel_numb=c.tel_numb
JOIN t_account a
ON m.tel_numb=a.tel_numb
WHERE c.customer_username=v_username;
end ;
/

prompt
prompt Creating procedure PRO_ONLINERECHARGE
prompt =====================================
prompt
create or replace procedure scott.pro_onlineRecharge
( 
v_type_name IN VARCHAR2,
v_tel_numb IN VARCHAR2,
v_preferential_id IN NUMBER,
v_sum_amount IN NUMBER,
v_tel_charge IN NUMBER,
v_result OUT NUMBER
)
is
 v_recharge_type_id  NUMBER;

begin
 --查出在线充值的编号 
SELECT recharge_type_id INTO v_recharge_type_id
FROM t_recharge_type t
WHERE t.recharge_type_name=v_type_name;

--插入t_recharge_infor
INSERT INTO t_recharge_infor
VALUES(T_RECCHARGE_INFOR_SEQ.NEXTVAL,v_tel_numb,SYSDATE,v_recharge_type_id,
        NULL,123456,v_preferential_id,(v_sum_amount-v_tel_charge),v_tel_charge);
        
--更新t_account的信息
UPDATE t_account t
SET account_balance=account_balance+v_tel_charge
WHERE tel_numb=v_tel_numb;
        
        
  IF SQL%FOUND
    THEN v_result:=1;
    COMMIT;
    ELSE 
      v_result:=-1;
      ROLLBACK;
      END IF;

end ;
/

prompt
prompt Creating procedure PRO_OPENBUSINESS
prompt ===================================
prompt
create or replace procedure scott.pro_openbusiness
(--业务编号
v_business_id  IN t_mobile_package.business_id%TYPE ,
--电话号码
v_tel_numb IN t_mobile_package.tel_numb%TYPE,

v_result OUT NUMBER
)

 is
 v_effective_time  t_business_fee.effective_time%TYPE:=0;
 v_end_time  t_business_fee.end_time%TYPE:=0;
 
 v_business_start  t_mobile_package.start_time%TYPE:=SYSDATE;
 v_business_end  t_mobile_package.end_time%TYPE:=NULL;
 
begin
 --查询该业务有效时间，结束时间
 SELECT f.effective_time,f.end_time INTO   v_effective_time ,v_end_time
 FROM t_business_fee f
 WHERE f.business_id=v_business_id;
 
 
 --分情况1立即生效
 IF v_effective_time=1
 THEN v_business_start:=SYSDATE;
 
--2隔日生效
 ELSIF v_effective_time=2
 THEN v_business_start:=SYSDATE+1;
 
 --3下月生效
 ELSE
 v_business_start:=add_months(SYSDATE,1); 
 END IF;
 
 --计算关闭时间
  --1一个月，-1永久生效
 IF v_end_time=1
 THEN  v_business_end:=add_months(SYSDATE,1);
 ELSIF v_end_time=-1
 THEN v_business_end:=NULL;
  ELSE
v_business_end:=NULL;
  END IF;
  
 --插入t_mobile_package
 INSERT INTO  t_mobile_package m
VALUES(T_MOBILE_SEQ.NEXTVAL,v_tel_numb,v_business_id,NULL,v_business_start,v_business_end,1);

IF SQL%FOUND
 THEN v_result:=1;
   COMMIT;
   ELSE
 v_result:=-1;
 ROLLBACK;
 END IF;


end pro_openbusiness;
/

prompt
prompt Creating procedure PRO_PAYMOBILE
prompt ================================
prompt
create or replace procedure scott.pro_payMobile
(
v_mobile IN VARCHAR2,
v_balance IN NUMBER,
v_pp_id IN NUMBER,
v_result OUT NUMBER
)

 is
 v_flag VARCHAR2(5) :='false';
begin
--更新，号码变为不可卖
UPDATE t_mobile t
SET t.is_sale=1
WHERE  t.tel_numb= v_mobile;


--更新ACCOUNT的信息
INSERT INTO t_account
VALUES(PAYMOBILE_SEQ.NEXTVAL,v_mobile,v_balance);


--更新t_mobile_package

INSERT INTO t_mobile_package m
VALUES(MOBILE_PACK_SEQ.NEXTVAL,v_mobile,NULL,v_pp_id,SYSDATE,NULL,1);

COMMIT;
v_flag:='true';

IF
 v_flag= 'true'
 THEN v_result:=1;
 ELSE v_result:=2;
 END IF;  

end ;
/

prompt
prompt Creating package body PACKAGE_DELETE
prompt ====================================
prompt
CREATE OR REPLACE PACKAGE BODY SCOTT.package_delete

IS 


--函数
FUNCTION fn_emp_number(
  v_deptno IN emp.deptno%TYPE)
 RETURN NUMBER
 IS
 v_count NUMBER;
BEGIN
SELECT COUNT(1) INTO v_count
FROM emp
WHERE deptno= v_deptno;
RETURN  v_count;
 
END fn_emp_number;

--存储过程
--要求如果该部门下没有员工则将该部门删除，
--如果该部门下有员工，将员工的部门编号设置为null，然后再删
 PROCEDURE delete_deptno(v_deptno  IN emp.deptno%TYPE,
   v_result OUT NUMBER)
  
IS
v_count NUMBER;
NOT_FOUND_DEPTNO EXCEPTION;
BEGIN
  
v_count:=fn_emp_number(v_deptno);
--部门下有员工
IF v_count!=0 THEN 
 --将员工的部门编号设置为null
UPDATE emp
SET deptno=NULL
WHERE empno IN  (SELECT empno
                FROM emp
              WHERE deptno=v_deptno);
END IF;
--然后再删
  DELETE 
  FROM dept
  WHERE deptno=v_deptno;
  
  IF
  SQL%NOTFOUND
  THEN
   RAISE  NOT_FOUND_DEPTNO;
  
  END IF;
  v_result:=1;
  COMMIT;
    
 EXCEPTION
   WHEN  NOT_FOUND_DEPTNO
     THEN 
       v_result:=-1;
       ROLLBACK;
     WHEN OTHERS 
       THEN 
         v_result:=-2;
       ROLLBACK;

END delete_deptno;

END package_delete;
/

prompt
prompt Creating package body PACK_DELE_EMP
prompt ===================================
prompt
CREATE OR REPLACE PACKAGE BODY SCOTT.pack_dele_emp
IS

PROCEDURE pro_dele_emp(v_empno IN emp.empno%TYPE,
ref_cursor OUT  ref_cursor_type ,   
v_result OUT NUMBER
  )
  IS
 dele_false EXCEPTION;
  v_deptno emp.deptno%TYPE;
  BEGIN
     --找deptno
   SELECT deptno INTO v_deptno
   FROM emp
   WHERE empno= v_empno;
   --删除员工信息
 DELETE 
 FROM emp
 WHERE empno= v_empno;

 COMMIT;
  --删除成功
  --返回该员工所在部门的其他所有员工”
  OPEN  ref_cursor FOR SELECT empno,ename,job,sal
                       FROM emp
                       WHERE deptno=v_deptno;
                       
    v_result:=1;
 
 EXCEPTION
  WHEN 
    --没有部门编号
    NO_DATA_FOUND 
    THEN  v_result:=-2;
 WHEN 
   --不存在该员工
   dele_false
   THEN v_result:=-1;
   ROLLBACK;
   WHEN OTHERS
   THEN v_result:=-3;
    ROLLBACK;
 
  END pro_dele_emp;



END pack_dele_emp;
/

prompt
prompt Creating package body PACK_LOGIN
prompt ================================
prompt
create or replace package body scott.pack_Login
is


 function fn_login(v_username IN t_user.id%TYPE,
  v_pwd IN t_user.pwd%TYPE,
  v_name OUT  t_user.name%TYPE)
  RETURN  NUMBER
  IS
   v_count NUMBER;
  BEGIN 
   SELECT COUNT(1) INTO v_count
   FROM t_user 
   WHERE id=v_username;
   IF v_count=0
     THEN RETURN -1;
    ELSE
    SELECT name INTO v_name
   FROM t_user 
   WHERE id=v_username AND pwd=v_pwd;
    RETURN 1; 
   END IF;
   
  EXCEPTION
   WHEN NO_DATA_FOUND
     THEN RETURN -2;
   WHEN OTHERS 
     THEN RETURN -3;
   
  END ;
end pack_Login;
/

prompt
prompt Creating package body PACK_ONE
prompt ==============================
prompt
create or replace package body scott.pack_one is


PROCEDURE pro_one( emp_cursor OUT emp_cursor_type)
IS

BEGIN  
OPEN emp_cursor FOR SELECT  empno,ename
                    FROM emp;
    
END ;  

end ;
/

prompt
prompt Creating package body PACK_SELMOBILE
prompt ====================================
prompt
create or replace package body scott.pack_selMobile is

PROCEDURE pro_dele_emp(
 v_add IN  notsal_mobile.tel_add%TYPE,
 v_account IN notsal_mobile.acc_init_amount%TYPE,
 v_begin IN VARCHAR2,
 v_type IN notsal_mobile.tel_type%TYPE,
 v_number IN VARCHAR2,
 v_startindex IN NUMBER,
  v_endindex IN NUMBER,
 --返回游标
 v_resultset OUT ref_cursor_type
  )
  IS
   v_keyword VARCHAR2(6) :='%';
  begin
  --拼接like关键字
 v_keyword:=CONCAT(CONCAT(v_keyword,v_number),'%');
  
OPEN v_resultset  FOR           
                       SELECT t.tel_numb,t.tel_add,t.acc_init_amount
                  FROM(SELECT  tel_numb,tel_add,acc_init_amount,ROWNUM r
                                         FROM notsal_mobile n
                                         WHERE n.tel_add=v_add 
                                         AND n.acc_init_amount=v_account
                                         AND SUBSTR(n.tel_numb,1,2)=v_begin
                                         AND n.tel_type=v_type
                                         AND n.tel_numb LIKE   v_keyword )t
                  WHERE t.r BETWEEN v_startindex AND v_endindex;
                              
                               
  end;

 --查找记录数
  PROCEDURE pro_count_mobile(
 v_add IN  notsal_mobile.tel_add%TYPE,
 v_account IN notsal_mobile.acc_init_amount%TYPE,
 v_begin IN VARCHAR2,
 v_type IN notsal_mobile.tel_type%TYPE,
 v_number IN VARCHAR2,
 v_count OUT NUMBER

  )
  IS
  v_keyword VARCHAR2(6) :='%';
  begin
  --拼接like关键字
 v_keyword:=CONCAT(CONCAT(v_keyword,v_number),'%');
     
  SELECT  COUNT(1) INTO v_count
   FROM notsal_mobile n
 WHERE n.tel_add=v_add 
  AND n.acc_init_amount=v_account
 AND SUBSTR(n.tel_numb,1,2)=v_begin
  AND n.tel_type=v_type
  AND n.tel_numb LIKE  v_keyword;
                              
                               
  end;
end pack_selMobile;
/


spool off
