CREATE TABLE IF NOT EXISTS collector
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    timestamp     DATETIME NOT NULL,
    base_currency VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS rate
(
    id                 BIGINT AUTO_INCREMENT PRIMARY KEY,
    collector_id BIGINT         NOT NULL,
    currency_code      VARCHAR(255)   NOT NULL,
    rate_to_euro       DECIMAL(19, 4) NOT NULL,
    CONSTRAINT fk_rates_collector FOREIGN KEY (collector_id) REFERENCES collector (id)
);

CREATE TABLE IF NOT EXISTS currency_state
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    service_name VARCHAR(255) NOT NULL,
    request_id   VARCHAR(255) NOT NULL UNIQUE,
    timestamp    DATETIME,
    client_id    VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS currency_history
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    service_name VARCHAR(255) NOT NULL,
    request_id   VARCHAR(255) NOT NULL UNIQUE,
    client_id    VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS historical_rate
(
    id                   BIGINT AUTO_INCREMENT PRIMARY KEY,
    currency             VARCHAR(255)          NOT NULL,
    rate                 DECIMAL(19, 4)        NOT NULL,
    timestamp            DATETIME,
    currency_history_id  BIGINT                NOT NULL,
    CONSTRAINT fk_history_rates FOREIGN KEY (currency_history_id) REFERENCES currency_history (id)
);