CREATE TABLE IF NOT EXISTS `USER_PROFILE`(
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    USER_NAME VARCHAR(25) NOT NULL,
    FULL_NAME VARCHAR(40),
    EMAIL VARCHAR(40),
    PHONE_NUMBER VARCHAR(12),
    GENDER VARCHAR(4),
    DATE_OF_BIRTH DATE,
    AVATAR_URL VARCHAR(255),
    CREATE_ON DATETIME,
    UPDATE_ON DATETIME,
    IS_DELETE BOOLEAN DEFAULT 0,
    USER_ID BIGINT,
    FOREIGN KEY (USER_ID) REFERENCES USER(id) ON DELETE CASCADE
)