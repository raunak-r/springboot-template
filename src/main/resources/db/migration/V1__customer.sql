CREATE TABLE customer
(
    customer_id        VARCHAR(36) NOT NULL,
    created_date       DATETIME(6) NOT NULL,
    email              VARCHAR(50) NOT NULL,
    first_name         VARCHAR(50) NOT NULL,
    last_name          VARCHAR(30),
    one_time_password  VARCHAR(255),
    otp_requested_time DATETIME(6),
    phone              VARCHAR(30),
    updated_date       DATETIME(6) NOT NULL,
    PRIMARY KEY (customer_id)
);

ALTER TABLE customer
  ADD CONSTRAINT uk_dwk6cx0afu8bs9o4t536v1j5v UNIQUE (email)