
    create table address (
        house_number integer not null,
        customer_id bigint not null,
        id bigint not null auto_increment,
        postal_code varchar(20) not null,
        city varchar(255) not null,
        country varchar(255) not null,
        street varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    create table administrator (
        id bigint not null auto_increment,
        email varchar(255),
        password varchar(255),
        username varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table cart (
        total_price float(53),
        customer_id bigint,
        id bigint not null auto_increment,
        primary key (id)
    ) engine=InnoDB;

    create table cart_item (
        quantity integer,
        cart_id bigint not null,
        id bigint not null auto_increment,
        product_id bigint not null,
        primary key (id)
    ) engine=InnoDB;

    create table cases (
        size tinyint not null check (size between 0 and 2),
        color_id bigint not null,
        id bigint not null,
        primary key (id)
    ) engine=InnoDB;

    create table color (
        id bigint not null auto_increment,
        color varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    create table cpu (
        core integer not null,
        ghz float(53) not null,
        id bigint not null,
        primary key (id)
    ) engine=InnoDB;

    create table customer (
        id bigint not null auto_increment,
        tax_id varchar(16) not null,
        email varchar(255) not null,
        name varchar(255) not null,
        password varchar(255) not null,
        surname varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    create table gpu (
        ghz float(53),
        vram integer,
        id bigint not null,
        primary key (id)
    ) engine=InnoDB;

    create table motherboard (
        id bigint not null,
        cpu_compatibility varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    create table order_item (
        quantity integer,
        unit_price float(53),
        id bigint not null auto_increment,
        order_id bigint not null,
        product_id bigint not null,
        primary key (id)
    ) engine=InnoDB;

    create table orders (
        total_price float(53),
        address_id bigint not null,
        customer_id bigint not null,
        id bigint not null auto_increment,
        order_date datetime(6),
        primary key (id)
    ) engine=InnoDB;

    create table product (
        price float(53) not null,
        stock integer not null,
        id bigint not null auto_increment,
        brand varchar(255) not null,
        image_url varchar(255) not null,
        model varchar(255) not null,
        type varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table `product-description` (
        id bigint not null auto_increment,
        product_id bigint not null,
        description VARCHAR(2500) not null,
        lang varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    create table psu (
        watt integer not null,
        id bigint not null,
        primary key (id)
    ) engine=InnoDB;

    create table ram (
        mhz integer not null,
        size integer not null,
        id bigint not null,
        primary key (id)
    ) engine=InnoDB;

    create table storage (
        size integer not null,
        stype tinyint not null check (stype between 0 and 2),
        id bigint not null,
        primary key (id)
    ) engine=InnoDB;

    create table system_messages (
        code varchar(255) not null,
        lang varchar(255) not null,
        message varchar(255),
        primary key (code, lang)
    ) engine=InnoDB;

    create table wishlist (
        customer_id bigint,
        id bigint not null auto_increment,
        primary key (id)
    ) engine=InnoDB;

    create table wishlist_item (
        id bigint not null auto_increment,
        product_id bigint,
        wishlist_id bigint,
        primary key (id)
    ) engine=InnoDB;

    alter table cart 
       add constraint UK867x3yysb1f3jk41cv3vsoejj unique (customer_id);

    alter table customer 
       add constraint UKnk6rtmr3efn32q2uxuwb9bdxa unique (tax_id);

    alter table customer 
       add constraint UKdwk6cx0afu8bs9o4t536v1j5v unique (email);

    alter table `product-description` 
       add constraint UKgelb8od8w72k9fcrmjg02xjmf unique (lang, product_id);

    alter table wishlist 
       add constraint UKi62hn96gwmmykqrbf8j2heo6b unique (customer_id);

    alter table wishlist_item 
       add constraint UKpolca2lkiuhjp2pc9qkswu5og unique (product_id, wishlist_id);

    alter table address 
       add constraint FK93c3js0e22ll1xlu21nvrhqgg 
       foreign key (customer_id) 
       references customer (id);

    alter table cart 
       add constraint FKdebwvad6pp1ekiqy5jtixqbaj 
       foreign key (customer_id) 
       references customer (id);

    alter table cart_item 
       add constraint FK1uobyhgl1wvgt1jpccia8xxs3 
       foreign key (cart_id) 
       references cart (id);

    alter table cart_item 
       add constraint FKjcyd5wv4igqnw413rgxbfu4nv 
       foreign key (product_id) 
       references product (id);

    alter table cases 
       add constraint FKp8w1e0ew1fq73j8wi8ekyx57n 
       foreign key (color_id) 
       references color (id);

    alter table cases 
       add constraint FKrpyp796vgnfvldx5w4k6kttbn 
       foreign key (id) 
       references product (id);

    alter table cpu 
       add constraint FKhjvorea8fb1lfyck60aq56vo5 
       foreign key (id) 
       references product (id);

    alter table gpu 
       add constraint FKd1s9c36icv8t7qicpoq81uqnw 
       foreign key (id) 
       references product (id);

    alter table motherboard 
       add constraint FK426q3lkgc49elfrqh30ywmq7j 
       foreign key (id) 
       references product (id);

    alter table order_item 
       add constraint FKt4dc2r9nbvbujrljv3e23iibt 
       foreign key (order_id) 
       references orders (id);

    alter table order_item 
       add constraint FK551losx9j75ss5d6bfsqvijna 
       foreign key (product_id) 
       references product (id);

    alter table orders 
       add constraint FKf5464gxwc32ongdvka2rtvw96 
       foreign key (address_id) 
       references address (id);

    alter table orders 
       add constraint FK624gtjin3po807j3vix093tlf 
       foreign key (customer_id) 
       references customer (id);

    alter table `product-description` 
       add constraint FKtdnlth5lpoqijt5ui8fbv2hs8 
       foreign key (product_id) 
       references product (id);

    alter table psu 
       add constraint FKa2fj0ush945rntfhw7j4kka4w 
       foreign key (id) 
       references product (id);

    alter table ram 
       add constraint FK8esh81pbxnajqa2a7uscu2kqx 
       foreign key (id) 
       references product (id);

    alter table storage 
       add constraint FK3mtiskwkqmppgesxyft7r2kqg 
       foreign key (id) 
       references product (id);

    alter table wishlist 
       add constraint FKb6xak0rjui1rsok8ll7ln59cs 
       foreign key (customer_id) 
       references customer (id);

    alter table wishlist_item 
       add constraint FK5s5jxai41c8tqklyy111ngqh7 
       foreign key (product_id) 
       references product (id);

    alter table wishlist_item 
       add constraint FK5iw5sajivrxnt4qjxqlgo8yb1 
       foreign key (wishlist_id) 
       references wishlist (id);
