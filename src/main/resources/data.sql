INSERT INTO board (title)
VALUES ('자유 게시판'),
       ('개발 게시판'),
       ('일상 게시판'),
       ('사건사고 게시판');

INSERT INTO article(board_id, title, content)
VALUES (1, '오늘은 목요일', '프로젝트 마감일'),
       (2, '미션프로젝트', '익명 의견 교환 웹 페이지 만들기');