INSERT INTO  BOARD(TITLE, AUTHOR, CONTENT, ISUPDATED, DATE)
VALUES ('1등', 'yum', '1등이당ㅎㅎ',  false, CURRENT_TIME());

INSERT INTO  BOARD(TITLE, AUTHOR, CONTENT, ISUPDATED, DATE)
VALUES ('개덥다오늘', 'heum', '날씨왜이래', false, CURRENT_TIME());

INSERT INTO  BOARD(TITLE, AUTHOR, CONTENT, ISUPDATED, DATE)
VALUES ('시험끝!', 'llshl', '다들 시험 잘봤니', false, CURRENT_TIME());

INSERT INTO  BOARD(TITLE, AUTHOR, CONTENT, ISUPDATED, DATE)
VALUES ('허허..', 'dogun', '취업길이 막막하구나', false, CURRENT_TIME());

INSERT INTO  BOARD(TITLE, AUTHOR, CONTENT, ISUPDATED, DATE)
VALUES ('축구할사람?', 'ohgreen', '선착1명 ㄱ',  false, CURRENT_TIME());




INSERT INTO MEMBER(LOGIN_ID, LOGIN_PASSWORD, EMAIL, NAME, NICKNAME)
VALUES ('qwer', 'pass', 'lsh970708@naver.com', '이승현', 'llshl');

INSERT INTO MEMBER(LOGIN_ID, LOGIN_PASSWORD, EMAIL, NAME, NICKNAME)
VALUES ('asdf', 'pass1', 'asdf@naver.com', '조영흠', 'heum');

INSERT INTO MEMBER(LOGIN_ID, LOGIN_PASSWORD, EMAIL, NAME, NICKNAME)
VALUES ('zxcv', 'pass2', 'zxcv@naver.com', '염찬영', 'yum');

INSERT INTO MEMBER(LOGIN_ID, LOGIN_PASSWORD, EMAIL, NAME, NICKNAME)
VALUES ('rr', 'pass3', 'rr@naver.com', '김도근', 'dogun');

INSERT INTO MEMBER(LOGIN_ID, LOGIN_PASSWORD, EMAIL, NAME, NICKNAME)
VALUES ('ff', 'pass4', 'ff@naver.com', '오해찬', 'ohgreen');

INSERT INTO MEMBER(LOGIN_ID, LOGIN_PASSWORD, EMAIL, NAME, NICKNAME)
VALUES ('vv', 'pass5', 'vv@naver.com', '박찬환', 'chanan');




INSERT INTO COMMENT(BOARD_ID, MEMBER_ID, CONTENT, ISUPDATED, DATE, MEMBER_NICKNAME)
VALUES (3, 1, '인정 인정', false, CURRENT_TIME(), 'yum');

INSERT INTO COMMENT(BOARD_ID, MEMBER_ID, CONTENT, ISUPDATED, DATE, MEMBER_NICKNAME)
VALUES (3, 2, '노잼',  false, CURRENT_TIME(), 'heum');

INSERT INTO COMMENT(BOARD_ID, MEMBER_ID, CONTENT, ISUPDATED, DATE, MEMBER_NICKNAME)
VALUES (2, 3, '아근데 그런경우가 좀 있어', false, CURRENT_TIME(), 'llshl');

INSERT INTO COMMENT(BOARD_ID, MEMBER_ID, CONTENT, ISUPDATED, DATE, MEMBER_NICKNAME)
VALUES (1, 4, '여긴 왜 댓글이 없냐', false, CURRENT_TIME(), 'dogun');

INSERT INTO COMMENT(BOARD_ID, MEMBER_ID, CONTENT, ISUPDATED, DATE, MEMBER_NICKNAME)
VALUES (4, 5, 'ㅋㅋㅋㅋ', false, CURRENT_TIME(), 'ohgreen');

INSERT INTO COMMENT(BOARD_ID, MEMBER_ID, CONTENT, ISUPDATED, DATE, MEMBER_NICKNAME)
VALUES (5, 1, '오우', false, CURRENT_TIME(),'yum');

INSERT INTO COMMENT(BOARD_ID, MEMBER_ID, CONTENT, ISUPDATED, DATE, MEMBER_NICKNAME)
VALUES (5, 2, 'ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ', false, CURRENT_TIME(),'heum');


INSERT INTO _LIKE(MEMBER_ID, PARENT_ID,  LIKE_TYPE)
VALUES (1, 2,  false);

INSERT INTO _LIKE(MEMBER_ID, PARENT_ID,  LIKE_TYPE)
VALUES (2, 5,  true);

INSERT INTO _LIKE(MEMBER_ID, PARENT_ID,  LIKE_TYPE)
VALUES (2, 1,  true);

INSERT INTO _LIKE(MEMBER_ID, PARENT_ID,  LIKE_TYPE)
VALUES (3, 1,  false);

INSERT INTO _LIKE(MEMBER_ID, PARENT_ID,  LIKE_TYPE)
VALUES (4, 2,  true);

INSERT INTO _LIKE(MEMBER_ID, PARENT_ID,  LIKE_TYPE)
VALUES (4, 3,  false);

