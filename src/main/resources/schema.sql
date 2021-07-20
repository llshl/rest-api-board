DROP TABLE BOARD,COMMENT,MEMBER IF EXISTS;
CREATE TABLE BOARD(
    board_id INT AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    author VARCHAR(100) NOT NULL,
    content VARCHAR(500) NOT NULL,
    like_count INT NOT NULL,
    dislike_count INT NOT NULL,
    date DATETIME NOT NULL,
    PRIMARY KEY (board_id)
);
CREATE TABLE MEMBER(
    member_id INT AUTO_INCREMENT,
    login_id VARCHAR(100) NOT NULL,
    login_password VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    name VARCHAR(100) NOT NULL,
    nickname VARCHAR(500) NOT NULL,
    PRIMARY KEY (member_id)
);
CREATE TABLE COMMENT(
    comment_id INT AUTO_INCREMENT,
    board_id INT NOT NULL,
    member_id INT NOT NULL,
    content VARCHAR(500) NOT NULL,
    like_count INT NOT NULL,
    dislike_count INT NOT NULL,
    date DATETIME NOT NULL,
    PRIMARY KEY (comment_id)
);