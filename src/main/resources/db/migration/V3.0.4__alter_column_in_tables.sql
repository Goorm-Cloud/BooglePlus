ALTER TABLE E2E_BOOGLE.BOOK CHANGE BOOK_ID ID bigint auto_increment NOT NULL;
ALTER TABLE E2E_BOOGLE.BOOK CHANGE BOOK_TITLE TITLE varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL;

ALTER TABLE E2E_BOOGLE.BOOK_REPORT CHANGE BOOK_REPORT_ID ID bigint auto_increment NOT NULL;
ALTER TABLE E2E_BOOGLE.BOOK_REPORT CHANGE BOOK_REPORT_TITLE TITLE varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL;
ALTER TABLE E2E_BOOGLE.BOOK_REPORT CHANGE BOOK_REPORT_CONTENT CONTENT text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL;

ALTER TABLE E2E_BOOGLE.`MEMBER` CHANGE MEMBER_ID ID bigint auto_increment NOT NULL;
