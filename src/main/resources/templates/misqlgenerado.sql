create table categoria
(
    id_categoria         tinyint unsigned auto_increment
        primary key,
    nombre               varchar(25)                         not null,
    ultima_actualizacion timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP
)
    charset = utf8mb3;

create table idioma
(
    id_idioma            tinyint unsigned auto_increment
        primary key,
    nombre               char(20)                            not null,
    ultima_actualizacion timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP
)
    charset = utf8mb3;

create table pelicula
(
    id_pelicula          smallint unsigned auto_increment
        primary key,
    titulo               varchar(255)                               not null,
    descripcion          text                                       null,
    fecha_lanzamiento    date                                       null,
    id_idioma            tinyint unsigned                           not null,
    duracion_alquiler    tinyint unsigned default '3'               not null,
    rental_rate          decimal(4, 2)    default 4.99              not null,
    duracion             smallint unsigned                          null,
    replacement_cost     decimal(5, 2)    default 19.99             not null,
    ultima_actualizacion timestamp        default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    constraint fk_film_idioma
        foreign key (id_idioma) references idioma (id_idioma)
            on update cascade
)
    charset = utf8mb3;

create index idx_fk_id_idioma
    on pelicula (id_idioma);

create table pelicula_categoria
(
    id_pelicula          smallint unsigned                   not null,
    id_categoria         tinyint unsigned                    not null,
    ultima_actualizacion timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    primary key (id_pelicula, id_categoria),
    constraint fk_film_category_category
        foreign key (id_categoria) references categoria (id_categoria)
            on update cascade,
    constraint fk_film_category_film
        foreign key (id_pelicula) references pelicula (id_pelicula)
            on update cascade
)
    charset = utf8mb3;

