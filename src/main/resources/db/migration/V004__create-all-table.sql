create table payment_type (
	id bigint not null auto_increment,
	description varchar(60) not null,
	primary key (id)
) engine=InnoDB default charset=utf8;

create table group_type (
	id bigint not null auto_increment,
	name varchar(60) not null,
	primary key (id)
) engine=InnoDB default charset=utf8;

create table group_permission (
	group_id bigint not null,
	permission_id bigint not null,

	primary key (group_id, permission_id)
) engine=InnoDB default charset=utf8;

create table permission (
	id bigint not null auto_increment,
	description varchar(60) not null,
	name varchar(100) not null,

	primary key (id)
) engine=InnoDB default charset=utf8;

create table product (
	id bigint not null auto_increment,
	restaurant_id bigint not null,
	name varchar(80) not null,
	description text not null,
	price decimal(10,2) not null,
	active tinyint(1) not null,

	primary key (id)
) engine=InnoDB default charset=utf8;

create table restaurant (
	id bigint not null auto_increment,
	kitchen_id bigint not null,
	name varchar(80) not null,
	shipping_fee decimal(10,2) not null,
	changed_at datetime not null,
	created_at datetime not null,

	address_city_id bigint,
	address_zip_code varchar(9),
	address_street varchar(100),
	address_number varchar(20),
	address_complement varchar(60),
	address_neighborhood varchar(60),

	primary key (id)
) engine=InnoDB default charset=utf8;

create table restaurant_payment_type (
	restaurant_id bigint not null,
	payment_type_id bigint not null,

	primary key (restaurant_id, payment_type_id)
) engine=InnoDB default charset=utf8;

create table user (
	id bigint not null auto_increment,
	name varchar(80) not null,
	email varchar(255) not null,
	password varchar(255) not null,
	created_at datetime not null,

	primary key (id)
) engine=InnoDB default charset=utf8;

create table user_group (
	user_id bigint not null,
	group_id bigint not null,

	primary key (user_id, group_id)
) engine=InnoDB default charset=utf8;



alter table group_permission add constraint fk_group_permission_permission
foreign key (permission_id) references permission (id);

alter table group_permission add constraint fk_group_permission_group
foreign key (group_id) references group_type (id);

alter table product add constraint fk_product_restaurant
foreign key (restaurant_id) references restaurant (id);

alter table restaurant add constraint fk_restaurant_kitchen
foreign key (kitchen_id) references kitchen (id);

alter table restaurant add constraint fk_restaurant_city
foreign key (address_city_id) references city (id);

alter table restaurant_payment_type add constraint fk_rest_pay_type_payment_type
foreign key (payment_type_id) references payment_type (id);

alter table restaurant_payment_type add constraint fk_rest_pay_type_restaurant
foreign key (restaurant_id) references restaurant (id);

alter table user_group add constraint fk_user_group_group
foreign key (group_id) references group_type (id);

alter table user_group add constraint fk_user_group_user
foreign key (user_id) references user (id);
