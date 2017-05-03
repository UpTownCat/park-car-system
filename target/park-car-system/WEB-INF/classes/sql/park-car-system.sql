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

