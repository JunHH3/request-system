
<div align="center">

# 📋 Request System

### 업무 요청 등록 및 상태 관리 웹 애플리케이션

![Type](https://img.shields.io/badge/TYPE-BACKEND_PRACTICE-2563EB?style=for-the-badge)
![Java](https://img.shields.io/badge/Java_21-007396?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)

</div>

## 🎯 Overview

업무 요청을 등록·조회·수정·삭제하고 요청 상태를 관리하는 Spring Boot 웹 애플리케이션입니다.

Spring MVC, JPA, REST API 및 테스트 코드 학습을 목적으로 개발했습니다.

## ✨ Features

- 업무 요청 등록·조회·수정·삭제
- 요청 상태 관리
- 입력값 검증 (Bean Validation)
- REST API 및 Mustache 화면
- 예외 처리 및 HTTP 응답 관리
- JUnit 5 / MockMvc 테스트

### Request Status

```text
RECEIVED
   ↓
IN_PROGRESS
   ↓
COMPLETED
```

## 🛠 Tech Stack

`Java 21` · `Spring Boot 4` · `Spring MVC` · `Spring Data JPA` · `MySQL` · `Mustache` · `Gradle` · `JUnit 5` · `MockMvc`

## 🔌 REST API

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/requests` | 요청 등록 |
| PATCH | `/api/requests/{id}` | 요청 수정 |
| DELETE | `/api/requests/{id}` | 요청 삭제 |
| GET | `/api/ping` | 상태 확인 |

## 🧱 Architecture

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
MySQL
```

## 🧪 Testing

- 요청 등록 성공 시 `201 Created` 검증
- 필수 입력값 누락 시 `400 Bad Request` 검증
- 존재하지 않는 요청 수정 시 `404 Not Found` 검증

```bash
./gradlew test
```

## ▶️ Getting Started

MySQL에 `request_system` 데이터베이스를 생성하고 `DB_PASSWORD` 환경 변수를 설정합니다.

```bash
git clone https://github.com/JunHH3/request-system.git
cd request-system
./gradlew bootRun
```

Windows에서는 `gradlew.bat bootRun`을 사용할 수 있습니다.

[🔗 GitHub Repository](https://github.com/JunHH3/request-system)
