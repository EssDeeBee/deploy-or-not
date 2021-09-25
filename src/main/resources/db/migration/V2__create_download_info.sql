CREATE TABLE download_info
(
    id            numeric PRIMARY KEY,
    file_name     text      NULL,
    browser       text      NULL,
    country       text      NULL,
    city          text      NULL,
    download_date timestamp NULL,
    ip_address    text      NULL
);
