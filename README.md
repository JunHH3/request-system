# Request System

사내에서 발생하는 업무 요청을 등록하고 처리 과정을 관리하기 위한  
Java / Spring Boot 기반 백엔드 프로젝트입니다.

현재 개발 진행 중이며, 기능을 작은 단위로 구현하면서
Spring MVC, JPA, SQL, 테스트, 인증/인가, 배포까지 단계적으로 적용할 예정입니다.

## 현재 구현

- 요청 등록
- DTO를 통한 입력값 전달
- JPA Entity 저장
- MySQL 연동

## 기술 스택

- Java 21
- Spring Boot
- Spring MVC
- Spring Data JPA
- MySQL
- Mustache
- Gradle
- Git / GitHub

## 개발 목표

단순히 기능을 많이 추가하는 것이 아니라,

- 요구사항을 API / DB / 도메인 규칙으로 분해
- Controller / Service / Repository 책임 분리
- JPA와 실제 SQL 동작 이해
- 실패 케이스 및 테스트 작성
- Git Commit / PR을 통한 개발 과정 기록
- Docker / CI/CD / 배포