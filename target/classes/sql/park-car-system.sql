CREATE DATABASE park-car-system CHARSET=utf8;

USE park-car-system;

CREATE TABLE car_owner(
  id INT AUTO_INCREMENT COMMENT "id",
  name VARCHAR(20) COMMENT "姓名",
  phone VARCHAR(11) NOT NULL COMMENT "手机号码",
  password VARCHAR(32) NOT NULL COMMENT "密码",
  gender INT DEFAULT 0 NOT NULL COMMENT "性别， 0男 1女",
  photo VARCHAR(30) COMMENT "照片",
  CONSTRAINT car_owner_id_pk PRIMARY KEY (id)
)engine=InnoDB, DEFAULT CHARSET=utf8, COMMENT "车主数据表" ;

CREATE TABLE car(
  id int AUTO_INCREMENT COMMENT "id",
  number VARCHAR(8) NOT NULL COMMENT "车牌号",
  brank VARCHAR(10) COMMENT "品牌",
  photo VARCHAR(30) COMMENT "照片",
  car_owner_id int NOT NULL COMMENT "车主的Id",
  CONSTRAINT car_id_pk PRIMARY KEY (id),
  CONSTRAINT car_car_owner_id_fk FOREIGN KEY (car_owner_id) REFERENCES car_owner(id)
)engine=InnoDB, DEFAULT CHARSET=utf8, COMMENT "车辆数据表" ;

CREATE TABLE parking_place(
  id INT AUTO_INCREMENT COMMENT "id",
  name VARCHAR(20) NOT NULL COMMENT "名字",
  location VARCHAR(20) NOT NULL COMMENT "地点",
  CONSTRAINT parking_place_id_pk PRIMARY KEY (id)
)engine=InnoDB, DEFAULT CHARSET=utf8, COMMENT "停车场数据表" ;

CREATE TABLE parking_seat(
  id INT AUTO_INCREMENT COMMENT "id",
  parking_place_id INT NOT NULL COMMENT "停车场的id",
  car_id INT COMMENT "车的id",
  CONSTRAINT park_seat_id_pk PRIMARY KEY (id),
  CONSTRAINT parking_seat_parking_place_id_fk FOREIGN KEY (parking_place_id) REFERENCES parking_place(id),
  CONSTRAINT parking_seat_car_id_fk FOREIGN KEY (car_id) REFERENCES car(id)
)engine=InnoDB, DEFAULT CHARSET=utf8, COMMENT "停车位数据表" ;

CREATE TABLE parking(
  id INT AUTO_INCREMENT COMMENT "id",
  parking_seat_id INT NOT NULL COMMENT "停车位id",
  car_id INT NOT NULL COMMENT "车id",
  in_time TIMESTAMP DEFAULT 0 NOT NULL COMMENT "计时开始时间",
  out_time TIMESTAMP DEFAULT 0 NOT NULL COMMENT "计时结束时间",
  price DOUBLE NOT NULL COMMENT "停车花费",
  CONSTRAINT parking_id_pk PRIMARY KEY (id),
  CONSTRAINT parking_parking_seat_id_fk FOREIGN KEY (parking_seat_id) REFERENCES parking_seat(id),
  CONSTRAINT parking_car_id_fk FOREIGN KEY (car_id) REFERENCES car(id)
)engine=InnoDB, DEFAULT CHARSET=utf8, COMMENT "停车数据表";

ALTER TABLE car_owner ADD balance DOUBLE NOT NULL DEFAULT 0 COMMENT "余额";
ALTER TABLE parking_place ADD money_per_hour DOUBLE NOT NULL DEFAULT 2 COMMENT "停车场的每小时收费";

ALTER TABLE parking ADD car_owner_id INT ;
ALTER TABLE parking ADD CONSTRAINT parking_car_owner_id_fk FOREIGN KEY (car_owner_id) REFERENCES car_owner(id);

CREATE TABLE deposit_interest(
  id INT AUTO_INCREMENT COMMENT "id",
  rate DOUBLE NOT NULL COMMENT "利息",
  duration INT NOT NULL DEFAULT 1 COMMENT "存款时长（单位天）",
  bank_id INT NOT NULL COMMENT "银行id",
  CONSTRAINT deposit_interest_id_pk PRIMARY KEY (id),
  CONSTRAINT deposit_interest_bank_id_fk FOREIGN KEY (bank_id) REFERENCES bank(id)
)engine=InnoDB, DEFAULT CHARSET=utf8, COMMENT "存款利率数据表";

CREATE TABLE loan_interest(
  id INT AUTO_INCREMENT COMMENT "id",
  rate DOUBLE NOT NULL COMMENT "利息",
  duration INT NOT NULL DEFAULT 1 COMMENT "贷款时长（单位天）",
  bank_id INT NOT NULL COMMENT "银行id",
  CONSTRAINT loan_interest_id_pk PRIMARY KEY (id),
  CONSTRAINT loan_interest_bank_id_fk FOREIGN KEY (bank_id) REFERENCES bank(id)
)engine=InnoDB, DEFAULT CHARSET=utf8, COMMENT "贷款利率数据表";

CREATE TABLE deposit(
  id INT AUTO_INCREMENT COMMENT "id",
  create_time DATETIME NOT NULL COMMENT "创建时间",
  update_time DATETIME NOT NULL COMMENT "更新时间",
  money DOUBLE NOT NULL COMMENT "金额",
  is_take_out INT DEFAULT 0 NOT NULL COMMENT "是否取出",
  user_id INT NOT NULL COMMENT "用户id",
  card_id INT NOT NULL COMMENT "银行卡id",
  deposit_interest_id INT NOT NULL COMMENT "存款利率id",
  CONSTRAINT deposit_id_pk PRIMARY KEY (id),
  CONSTRAINT deposit_user_id_fk FOREIGN KEY (user_id) REFERENCES user(id),
  CONSTRAINT deposit_card_id_fk FOREIGN KEY (card_id) REFERENCES card(id),
  CONSTRAINT deposit_deposit_interest_id_fk FOREIGN KEY (deposit_interest_id) REFERENCES deposit_interest(id)
)engine=InnoDB, DEFAULT CHARSET=utf8, COMMENT "存款数据表";

CREATE TABLE loan(
  id INT AUTO_INCREMENT COMMENT "id",
  create_time DATETIME NOT NULL COMMENT "创建时间",
  update_time DATETIME NOT NULL COMMENT "更新时间",
  money DOUBLE NOT NULL COMMENT "金额",
  is_pay_back INT DEFAULT 0 NOT NULL COMMENT "是否归还",
  user_id INT NOT NULL COMMENT "用户id",
  card_id INT NOT NULL COMMENT "银行卡id",
  loan_interest_id INT NOT NULL COMMENT "贷款利率id",
  CONSTRAINT loan_id_pk PRIMARY KEY (id),
  CONSTRAINT loan_user_id_fk FOREIGN KEY (user_id) REFERENCES user(id),
  CONSTRAINT loan_card_id_fk FOREIGN KEY (card_id) REFERENCES card(id),
  CONSTRAINT loan_deposit_interest_id_fk FOREIGN KEY (loan_interest_id) REFERENCES loan_interest(id)
)engine=InnoDB, DEFAULT CHARSET=utf8, COMMENT "贷款数据表";

CREATE  table credit_card(
  id int auto_increment comment "id",
  number varchar(30) not null comment "卡号",
  balance double not null comment "余额",
  credit  int not null default 0 comment "信用度",
  bank_id int not null comment "所属银行的id",
  user_id int comment "所属用户的id",
  CONSTRAINT credit_card_id_pk PRIMARY KEY (id) comment "主键",
  CONSTRAINT credit_card_bank_id_fk FOREIGN  KEY (bank_id) REFERENCES bank(id) ,
  CONSTRAINT credit_card_user_id_fk FOREIGN KEY (user_id) REFERENCES user(id)
)engine=InnoDB, default charset=utf8, comment="信用卡表";

CREATE TABLE credit_card_deposit(
  id INT AUTO_INCREMENT COMMENT "id",
  update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT "更新时间",
  create_time DATETIME NOT NULL COMMENT "创建时间",
  money DOUBLE NOT NULL COMMENT "金额",
  is_take_out INT DEFAULT 0 NOT NULL COMMENT "是否取出",
  user_id INT NOT NULL COMMENT "用户id",
  card_id INT NOT NULL DEFAULT 1 COMMENT "银行卡id",
  CONSTRAINT credit_card_deposit_id PRIMARY KEY (id),
  CONSTRAINT credit_card_deposit_user_id_fk FOREIGN KEY (user_id) REFERENCES user(id),
  CONSTRAINT credit_card_deposit_card_id_fk FOREIGN KEY (card_id) REFERENCES card(id)
)engine=InnoDB, default charset=utf8, comment="信用卡存款表";

CREATE TABLE credit_card_deposit(
  id INT AUTO_INCREMENT COMMENT "id",
  update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT "更新时间",
  create_time DATETIME NOT NULL COMMENT "创建时间",
  money DOUBLE NOT NULL COMMENT "金额",
  is_take_out INT DEFAULT 0 NOT NULL COMMENT "是否取出",
  user_id INT NOT NULL COMMENT "用户id",
  card_id INT NOT NULL DEFAULT 1 COMMENT "银行卡id",
  CONSTRAINT credit_card_deposit_id PRIMARY KEY (id),
  CONSTRAINT credit_card_deposit_user_id_fk FOREIGN KEY (user_id) REFERENCES user(id),
  CONSTRAINT credit_card_deposit_card_id_fk FOREIGN KEY (card_id) REFERENCES card(id)
)engine=InnoDB, default charset=utf8, comment="信用卡贷款表";


