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

![image]([https://github.com/JoonHyug/EnjoyTrip/assets/86625001/287e9026-08fb-45e6-b9f5-95f1d46911eb](https://private-user-images.githubusercontent.com/86625001/242175445-287e9026-08fb-45e6-b9f5-95f1d46911eb.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJrZXkiOiJrZXkxIiwiZXhwIjoxNjg1NTkyOTg4LCJuYmYiOjE2ODU1OTI2ODgsInBhdGgiOiIvODY2MjUwMDEvMjQyMTc1NDQ1LTI4N2U5MDI2LTA4ZmItNDVlNi1iOWY1LTk1ZjFkNDY5MTFlYi5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBSVdOSllBWDRDU1ZFSDUzQSUyRjIwMjMwNjAxJTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDIzMDYwMVQwNDExMjhaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT0zOWUwODY1YzU5OWY4NTc2YjI3NWE3N2UzMzRmODlhZGU2ZDgxNjhkNTEyZmRiY2EyMjI3MDk0MmU5NmExMzhmJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.ys1kGV9NanKcClesCOTObAubimwVxNUuEtyIGGKMYks))

---

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

---

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

---

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

---

## 화면 설계

---

메인 페이지
![image](https://github.com/JoonHyug/EnjoyTrip/assets/86625001/1a258ad6-7dc5-484e-9536-eec78cfb3d22)

로그인
![image](https://github.com/JoonHyug/EnjoyTrip/assets/86625001/fba95284-1f1a-49ec-8b19-d366c8111375)

검색
![image](https://github.com/JoonHyug/EnjoyTrip/assets/86625001/ad7d340e-a0c5-48a2-8bdb-531db3d04454)

상세정보
![image](https://github.com/JoonHyug/EnjoyTrip/assets/86625001/f5d7d197-08ad-40dd-8e86-5b5169b87a46)

게시판
![image](https://github.com/JoonHyug/EnjoyTrip/assets/86625001/4c43adba-9a46-4c9f-b65a-779bf151865d)
![image](https://github.com/JoonHyug/EnjoyTrip/assets/86625001/304796ff-c398-47ed-bfd9-a5150a9b0f68)
![image](https://github.com/JoonHyug/EnjoyTrip/assets/86625001/66d0dff0-382f-4aad-af0a-0c6303ec10f8)

여행 계획
![image](https://github.com/JoonHyug/EnjoyTrip/assets/86625001/1a3a5902-fb68-4b51-abee-202bdf1c2aaf)
![image](https://github.com/JoonHyug/EnjoyTrip/assets/86625001/23d145d4-f86c-4f03-9f86-f5a60961b3c6)

마이페이지
![image](https://github.com/JoonHyug/EnjoyTrip/assets/86625001/32acf7fa-a7ba-4cbc-981a-ead1a281d817)
![image](https://github.com/JoonHyug/EnjoyTrip/assets/86625001/18af2d65-0d19-4ef9-bb78-4795c04c8320)
![image](https://github.com/JoonHyug/EnjoyTrip/assets/86625001/8f63078d-f5a7-4839-9fc5-c00e0db48492)
![image](https://github.com/JoonHyug/EnjoyTrip/assets/86625001/7e9463ce-d67a-404b-beb1-d473337c1bc9)

---
