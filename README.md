# Request System

사내에서 발생하는 다양한 업무 요청을 등록하고 처리 과정을 관리하는  
**Spring Boot 기반 사내 요청 관리 시스템**입니다.

요청 등록부터 담당자 배정, 상태 변경, 처리 이력 관리까지 하나의 시스템에서 관리할 수 있도록 구현했습니다.

---

## 주요 기능

### 요청 관리
- 업무 요청 등록
- 요청 목록 조회
- 요청 상세 조회
- 요청 수정 및 삭제
- 요청 상태 변경

### 요청 처리
- 요청 담당자 지정
- 요청 처리 상태 관리
- 요청 처리 이력 관리
- 승인 및 반려 처리

### 사용자 및 권한
- 사용자 인증
- 역할별 권한 관리
- 요청자 / 담당자 권한 분리
- Spring Security 기반 접근 제어

### 검색 및 조회
- 제목 및 내용 검색
- 요청 상태별 필터링
- 담당자별 조회
- 페이징 처리

---

## 기술 스택

### Backend
- Java 21
- Spring Boot
- Spring MVC
- Spring Data JPA
- Spring Security
- Bean Validation

### Database
- MySQL 8

### View
- Mustache

### Build & Version Control
- Gradle
- Git
- GitHub

---

## 프로젝트 구조

```text
src/main/java/dev/request/system
├── controller
├── service
├── repository
├── entity
├── dto
├── exception
├── config
└── RequestSystemApplication.java
```

Controller, Service, Repository 계층을 분리하여 각 계층의 역할을 명확하게 구성했습니다.

---

## 요청 처리 흐름

```text
Client
  ↓
Controller
  ↓
DTO
  ↓
Service
  ↓
Repository
  ↓
MySQL
```

Controller에서 요청을 전달받고 DTO를 통해 데이터를 전달합니다.

비즈니스 로직은 Service 계층에서 처리하며, Spring Data JPA Repository를 통해 MySQL에 데이터를 저장하고 조회합니다.

---

## 주요 구현 내용

- DTO와 Entity 역할 분리
- Service 계층을 통한 비즈니스 로직 분리
- Spring Data JPA 기반 CRUD
- 요청 상태 변경 로직
- 담당자 배정 기능
- 요청 처리 이력 관리
- Bean Validation 기반 입력값 검증
- 전역 예외 처리
- 검색 및 페이징
- Spring Security 기반 인증 및 인가
- REST API 구현
- 테스트 코드 작성

---

## Database

MySQL의 `request_system` 데이터베이스를 사용합니다.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/request_system
spring.datasource.username=root
spring.datasource.password=${DB_PASSWORD}
```

DB 비밀번호와 같은 민감 정보는 환경 변수로 관리합니다.

---

## 프로젝트 목적

단순 CRUD를 넘어 실제 업무에서 사용할 수 있는 요청 관리 시스템을 구현하면서 다음 내용을 직접 적용했습니다.

- Spring MVC 기반 웹 요청 처리
- 계층형 애플리케이션 설계
- DTO와 Entity 분리
- JPA 기반 데이터베이스 처리
- 비즈니스 로직 설계
- 인증 및 권한 관리
- Validation 및 예외 처리
- REST API 설계
- 검색 및 페이징
- 테스트 코드 작성
