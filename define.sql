
    create table product (
        stock integer not null,
        id bigint not null auto_increment,
        brand varchar(255) not null,
        description varchar(255) not null,
        model varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;
