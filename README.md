# EnjoyTrip

## 프로젝트 개요

---

### 개요

- 코로나 발생 이후 국내 여행 수요증가
- 사람들과 여행 계획 공유
- 사용자에게 더 편리한 여행지 찾기 서비스 제공

### 프로젝트 기간

2023.04.27 ~ 2023.05.25

### 프로젝트 목표

- 여행 계획을 효과적으로 관리
- 여행 계획 공유
- 장소 검색
  - 북마크
  - 리뷰
- 자유게시판

### ERD

![image](https://github.com/JoonHyug/EnjoyTrip/assets/86625001/4d395b5d-d894-4186-a2ff-849a13f0dd88)



## 기술 스택

---

BD

- MySQL

Back-End

- Spring Boot

Front-End

- vue@2.7.14
- tailwindcss@3.3.2
- vue-pagination-2@3.1.0
- vue-router@3.6.5
- vuex-persistedstate@4.1.0
- vuex@3.6.2



## 프로젝트 빌드

---

DB

- DB 폴더 아래의 쿼리문 실행

Back-End

- IntelliJ로 빌드

Font-End

1. npm install
2. .env 생성
   - .env 파일을 vue 프로젝트의 root directory에 만들고 VUE_APP_API_KAKAO_KEY= 의 뒤에 카카오맵 서비스키를 입력
3. 공공 데이터 포털 서비스 키 입력
   - vuex의 module의 detail.js에 공공데이터 포털 서비스키를 입력해야 함



## 개선점

---

- 백엔드
  - 스프링 시큐리티 적용 X
  - 계정 보안 부족
  - 인증 방식(JWT)
- 프론트 엔드
  - 컴포넌트 분리 부족(코드 정리)
  - 게시판 댓글 기능
  - 로그인 관련 기능



## 화면 설계

---

메인 페이지
![image](https://github.com/JoonHyug/EnjoyTrip/assets/86625001/d902b736-5046-4347-8670-e3004e8fb96d)

로그인
![image](https://github.com/JoonHyug/EnjoyTrip/assets/86625001/1db0660d-9383-4da8-a8f1-aadfc63704f2)

검색
![image](https://github.com/JoonHyug/EnjoyTrip/assets/86625001/90cf2f21-c7bf-44ee-a37d-c3f760595ebe)

상세정보
![image](https://github.com/JoonHyug/EnjoyTrip/assets/86625001/e5ae677a-3896-4209-a755-c8c892afcff7)

게시판
![image](https://github.com/JoonHyug/EnjoyTrip/assets/86625001/873767d3-f4de-4a99-b959-e031b93e28f2)
![image](https://github.com/JoonHyug/EnjoyTrip/assets/86625001/74bd0112-92fb-4abf-bf2b-07800bdd7b33)
![image](https://github.com/JoonHyug/EnjoyTrip/assets/86625001/a9aa807a-ec57-48e3-aef4-7e171de5804c)

여행 계획
![image](https://github.com/JoonHyug/EnjoyTrip/assets/86625001/e3f91d9d-346a-4b93-99f8-cc0185769781)
![image](https://github.com/JoonHyug/EnjoyTrip/assets/86625001/86ca4924-fded-493d-9ddb-5d18e5101dec)

마이페이지
![image](https://github.com/JoonHyug/EnjoyTrip/assets/86625001/4e9178c6-bbaa-4cb1-8c4f-ff92b83b4658)
![image](https://github.com/JoonHyug/EnjoyTrip/assets/86625001/718c106f-c8f9-40e1-821f-11c955d2cd6c)
![image](https://github.com/JoonHyug/EnjoyTrip/assets/86625001/e29409a8-d782-4e72-9010-a6fc63e7f305)
![image](https://github.com/JoonHyug/EnjoyTrip/assets/86625001/b6eed85a-aa52-49f4-99a7-c5dc151b7886)

---
