<!--Header-->
# 1. Project
- ProjectName : **Movie Reserve**
- DevelopPeriod : *2023.06.12 ~ 2023.06.25*
---
# 2. Info
- 목표
  네카라쿠배 8년차 현직 개발자와 함께하는 공연 예매 서비스 클론코딩 딥다이브
  API 가이드라인을 따라 영화 예매 서비스 서버를 구현한다.
  
  | **시스템 구조도 아키텍쳐** |
  
  | **REST APIs** |  
  ![REST](https://user-images.githubusercontent.com/74723818/230903512-52c8f8ea-540b-4067-9daa-2048a8d6319d.png)

  | **FLOW** |
  ![아키텍처흐름도](https://github.com/CordHouse/movieReserve/assets/74723818/af0b06de-204d-4bd8-8187-2ca72bc91b4d)

  | **API specification** |
  ![api 명세서](https://github.com/CordHouse/movieReserve/assets/74723818/c0a0436f-0f94-455c-9b0c-f9326d926d4a)

  | **ERD** |
  ![moviesReserveERD](https://github.com/CordHouse/movieReserve/assets/74723818/31838360-b736-4ddf-8674-75e34d4448b9)
  
- API 스펙
  - 공연 정보 조회
  - 공연 잔여 좌석 조회
  - 공연 예매
  - 회원가입 <사업자, 사용자>
  - 로그인
  - 공연장 등록
  - 공연 등록
---
# 3. Team Building
- **BackEnd**
 - [이지우](https://github.com/CordHouse)
 - [홍승근](https://github.com/hongseungkeun)
 - [조윤지](https://github.com/sCrystalCave)
 - [김주형](https://github.com/BnDC)
---
# 4. Skill
```text
- Jwt 
- Security
```
---
# 5. Config
```text
1. Jwt access_token = 120분
2. Security whiteList = 회원가입, 로그인, 공연 정보 조회, 공연 잔여 좌석 조회
```
---
# 6. Memoir(회고록) & 가이드라인
- [회고록](https://basalt-kidney-f2e.notion.site/Spring-Boot-704e3199d7504715b7d59cdf7b393901?pvs=4)
- [가이드라인](https://thoughtful-arch-8c2.notion.site/Springboot-8a05e95236704a26b24e4008f7a8be99)

---
# 7. Convention
| **Git Convention** |
```text
Git Convention
feat : 기능추가
fix : 버그 수정
refactor : 리팩토링, 기능은 그대로 두고 코드를 수정
style : formatting, 세미콜론 추가 / 코드 변경은 없음
chore : 라이브러리 설치, 빌드 작업 업데이트
docs : 주석 추가 삭제, 문서 변경
```
