CREATE TABLE IF NOT EXISTS rates_collector
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    timestamp     DATETIME NOT NULL,
    base_currency VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS currency_rates
(
    id                 BIGINT AUTO_INCREMENT PRIMARY KEY,
    rates_collector_id BIGINT         NOT NULL,
    currency_code      VARCHAR(255)   NOT NULL,
    rate_to_euro       DECIMAL(19, 4) NOT NULL,
    CONSTRAINT fk_rates_collector FOREIGN KEY (rates_collector_id) REFERENCES rates_collector (id)
);

CREATE TABLE IF NOT EXISTS statistics_collector
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    service_name VARCHAR(255),
    request_id   VARCHAR(255) NOT NULL UNIQUE,
    timestamp    DATETIME,
    client_id    VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS history_collector
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    service_name VARCHAR(255),
    request_id   VARCHAR(255) NOT NULL UNIQUE,
    client_id    VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS rate_history
(
    id                   BIGINT AUTO_INCREMENT PRIMARY KEY,
    currency             VARCHAR(255),
    rate                 DECIMAL(19, 4),
    timestamp            DATETIME,
    history_collector_id BIGINT,
    CONSTRAINT fk_history_collector FOREIGN KEY (history_collector_id) REFERENCES history_collector (id)
);