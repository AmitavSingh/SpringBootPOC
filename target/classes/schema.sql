CREATE TABLE IF NOT EXISTS MyPhotos
(
    id           bigint auto_increment not null PRIMARY KEY,
    file_name    VARCHAR(255),
    file_type    VARCHAR(255),
    file_data    BLOB
);