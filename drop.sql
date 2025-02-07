
    alter table address 
       drop 
       foreign key FK93c3js0e22ll1xlu21nvrhqgg;

    alter table cart 
       drop 
       foreign key FKdebwvad6pp1ekiqy5jtixqbaj;

    alter table cart_item 
       drop 
       foreign key FK1uobyhgl1wvgt1jpccia8xxs3;

    alter table cart_item 
       drop 
       foreign key FKjcyd5wv4igqnw413rgxbfu4nv;

    alter table cases 
       drop 
       foreign key FKp8w1e0ew1fq73j8wi8ekyx57n;

    alter table cases 
       drop 
       foreign key FKrpyp796vgnfvldx5w4k6kttbn;

    alter table cpu 
       drop 
       foreign key FKhjvorea8fb1lfyck60aq56vo5;

    alter table gpu 
       drop 
       foreign key FKd1s9c36icv8t7qicpoq81uqnw;

    alter table motherboard 
       drop 
       foreign key FK426q3lkgc49elfrqh30ywmq7j;

    alter table order_item 
       drop 
       foreign key FKt4dc2r9nbvbujrljv3e23iibt;

    alter table order_item 
       drop 
       foreign key FK551losx9j75ss5d6bfsqvijna;

    alter table orders 
       drop 
       foreign key FKf5464gxwc32ongdvka2rtvw96;

    alter table orders 
       drop 
       foreign key FK624gtjin3po807j3vix093tlf;

    alter table psu 
       drop 
       foreign key FKa2fj0ush945rntfhw7j4kka4w;

    alter table ram 
       drop 
       foreign key FK8esh81pbxnajqa2a7uscu2kqx;

    alter table storage 
       drop 
       foreign key FK3mtiskwkqmppgesxyft7r2kqg;

    alter table wishlist 
       drop 
       foreign key FKb6xak0rjui1rsok8ll7ln59cs;

    alter table wishlist_item 
       drop 
       foreign key FK5s5jxai41c8tqklyy111ngqh7;

    alter table wishlist_item 
       drop 
       foreign key FK5iw5sajivrxnt4qjxqlgo8yb1;

    drop table if exists address;

    drop table if exists administrator;

    drop table if exists cart;

    drop table if exists cart_item;

    drop table if exists cases;

    drop table if exists color;

    drop table if exists cpu;

    drop table if exists customer;

    drop table if exists gpu;

    drop table if exists motherboard;

    drop table if exists order_item;

    drop table if exists orders;

    drop table if exists product;

    drop table if exists psu;

    drop table if exists ram;

    drop table if exists storage;

    drop table if exists system_messages;

    drop table if exists wishlist;

    drop table if exists wishlist_item;
