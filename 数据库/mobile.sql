-----------------------------------------------------
-- Export file for user SCOTT                      --
-- Created by Administrator on 2016-2-21, 17:31:00 --
-----------------------------------------------------

spool mobile.log

prompt
prompt Creating table T_ACCOUNT
prompt ========================
prompt
create table SCOTT.T_ACCOUNT
(
  ACCOUNT_ID      NUMBER,
  TEL_NUMBER      VARCHAR2(11),
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

prompt
prompt Creating table T_BUSINESS_FEE
prompt =============================
prompt
create table SCOTT.T_BUSINESS_FEE
(
  BUSINESS_ID     NUMBER,
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

prompt
prompt Creating table T_CUSTOMER
prompt =========================
prompt
create table SCOTT.T_CUSTOMER
(
  CUSTOMER_ID       NUMBER,
  CUSTOMER_USERNAME VARCHAR2(20),
  CUSTOMER_NAME     VARCHAR2(20),
  ID_CARD_NUMB      VARCHAR2(18),
  TEL_NUMB          VARCHAR2(11),
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

prompt
prompt Creating table T_MOBILE
prompt =======================
prompt
create table SCOTT.T_MOBILE
(
  TEL_NUMB        VARCHAR2(11),
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

prompt
prompt Creating table T_PACKAGE_BUSINESS
prompt =================================
prompt
create table SCOTT.T_PACKAGE_BUSINESS
(
  PP_ID       NUMBER,
  BUSINESS_ID NUMBER
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
prompt Creating table T_PHONE_PACKAGE
prompt ==============================
prompt
create table SCOTT.T_PHONE_PACKAGE
(
  PP_ID        NUMBER,
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

prompt
prompt Creating table T_PREFERENTIAL_INFOR
prompt ===================================
prompt
create table SCOTT.T_PREFERENTIAL_INFOR
(
  PREFERENTIAL_ID   NUMBER,
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

prompt
prompt Creating table T_RECHARGE_CARD
prompt ==============================
prompt
create table SCOTT.T_RECHARGE_CARD
(
  CARD_ID      NUMBER,
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

prompt
prompt Creating table T_RECHARGE_TYPE
prompt ==============================
prompt
create table SCOTT.T_RECHARGE_TYPE
(
  RECHARGE_TYPE_ID   NUMBER,
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


spool off
