/* 
    創建資料庫和使用資料庫
*/
CREATE DATABASE book_admin; -- 創建名為 book_admin 的資料庫
USE book_admin;          -- 切換到 book_admin 資料庫

/* 
    創建 USERS 表 (用戶表)
*/
CREATE TABLE USERS (
    UID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,  -- 用戶 ID，自增主鍵
    USERNAME VARCHAR(30),                         -- 用戶名
    PASSWORD VARCHAR(30),                         -- 密碼
    ADMIN BOOLEAN                                 -- 是否為管理員
);

/* 
    創建 BOOKS 表 (書籍表)
*/
CREATE TABLE BOOKS (
    BID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,  -- 書籍 ID，自增主鍵
    BNAME VARCHAR(50),                            -- 書名
    GENRE VARCHAR(20),                            -- 書籍類型
    PRICE INT                                     -- 價格
);

/* 
    創建 ISSUED 表 (借閱記錄表)
*/
CREATE TABLE ISSUED (
    IID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,  -- 借閱記錄 ID，自增主鍵
    UID INT,                                      -- 用戶 ID
    BID INT,                                      -- 書籍 ID
    ISSUED_DATE VARCHAR(20),                      -- 借出日期
    RETURN_DATE VARCHAR(20),                      -- 返還日期
    PERIOD INT,                                   -- 借閱期限
    FINE INT                                      -- 罰款
);

/* 
    插入 USERS 表中的資料 (用戶數據)
*/
INSERT INTO USERS (USERNAME, PASSWORD, ADMIN) 
VALUES ('admin', 'admin', TRUE); -- 插入管理員用戶

INSERT INTO USERS (USERNAME, PASSWORD, ADMIN) 
VALUES
('test', '1234', FALSE),      -- 插入普通用戶
('user2', 'password2', FALSE), 
('admin', 'admin', TRUE);

/* 
    插入 BOOKS 表中的資料 (書籍數據)
*/
INSERT INTO BOOKS (BNAME, GENRE, PRICE) 
VALUES
('War and Peace', 'Mystery', 200),
('The Guest Book', 'Fiction', 300),
('The Perfect Murder', 'Mystery', 150),
('Accidental Presidents', 'Biography', 250),
('The Wicked King', 'Fiction', 350);

/* 
    插入 ISSUED 表中的資料 (借閱記錄數據)
*/
INSERT INTO issued (UID, BID, ISSUED_DATE, RETURN_DATE, PERIOD, FINE) 
VALUES
(1, 1, '2024-01-01', '2024-01-15', 14, 0),
(2, 2, '2024-01-05', '2024-01-20', 15, 0),
(1, 3, '2024-01-10', '2024-01-25', 15, 10), -- 包含罰款的借閱記錄
(3, 4, '2024-01-15', '2024-01-30', 15, 0),
(2, 5, '2024-01-20', '2024-02-04', 15, 0);


/* 
    查詢表資料
*/
SHOW TABLES FROM book_admin;                               -- 顯示 book_admin 資料庫中的表
SELECT * FROM USERS;                                       -- 查詢 USERS 表的所有資料
SELECT * FROM books;                                       -- 查詢 BOOKS 表的所有資料
SELECT * FROM issued;                                      -- 查詢 ISSUED 表的所有資料

/* 
	查詢表的格式
*/
DESCRIBE USERS; 
DESCRIBE books;
DESCRIBE issued
