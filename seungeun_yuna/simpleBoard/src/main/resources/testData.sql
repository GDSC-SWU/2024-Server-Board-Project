# 기본 카테고리 생성
insert into category(category_id, category_name, access_level)
values(1, '게시판_D', '10');
insert into category(category_id, category_name, access_level)
values(2, '게시판_C', '20');
insert into category(category_id, category_name, access_level)
values(3, '게시판_B', '30');
insert into category(category_id, category_name, access_level)
values(4, '게시판_A', '40');

# 테스트용 유저 생성
insert into user(user_id, email, password, nickname, grade,
                 profile_img, created_at, updated_at, post_count, comment_count, like_count)
values(0,'example@naver.com','123456','sample_user','40',
       'profile_img.jpg',now(), now(), 0, 0, 0);