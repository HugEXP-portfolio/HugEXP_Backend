# 🌟 Please-Hug_BackEnd 🌟

<div align="center">
  <img src="https://via.placeholder.com/500x200?text=Please-Hug+LMS" alt="Please-Hug Logo" width="500"/>
  <br>
  <br>

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue.svg)](https://www.mysql.com/)
[![Redis](https://img.shields.io/badge/Redis-Latest-red.svg)](https://redis.io/)
</div>

## 📋 목차

- [소개](#-소개)
- [주요 기능](#-주요-기능)
- [담당 역할](#-담당-역할)
- [기술 스택](#-기술-스택)
- [시스템 아키텍처](#-시스템-아키텍처)
- [프로젝트 구조](#-프로젝트-구조)
- [설치 및 실행 방법](#-설치-및-실행-방법)
- [API 문서](#-api-문서)
- [테스트](#-테스트)
## 📚 소개

**Please-Hug_BackEnd**는 Goorm EXP를 클론 코딩하여 만든 Spring Boot 기반의 LMS(Learning Management System) 백엔드 서버입니다. LMS에 필요한 미션 관리, 출석 체크, 칭찬 시스템, 알림, 상점 등 다양한 기능을 제공하여 학습자들의 성장을 지원합니다.

<div align="center">
  <img src="https://via.placeholder.com/800x400?text=System+Overview" alt="System Overview" width="800"/>
</div>

## ✨ 주요 기능

| 기능 | 설명 |
|------|------|
| 🎯 **미션 시스템** | 미션 생성, 진행, 완료, 리뷰 등 전체 미션 라이프사이클 관리 |
| 📝 **학습 태스크** | 미션별 세부 태스크 관리 및 진행 상황 추적 |
| 📊 **그룹 관리** | 학습 그룹 생성 및 관리, 그룹별 진행 상황 모니터링 |
| ✅ **출석 체크** | 일일 출석 체크 및 통계, 출석 보상 시스템 |
| 🌟 **칭찬 시스템** | 사용자 간 칭찬 주고받기, 칭찬 포인트 적립 |
| 🔔 **알림 시스템** | 중요 이벤트 및 활동에 대한 실시간 알림 |
| 🛒 **포인트 상점** | 획득한 포인트로 아이템 구매, 보상 시스템 |
| 📖 **학습 일지** | 개인 학습 기록 및 일지 작성 기능 |

## 담당 역할
- 출석/북마크 시스템 구현
- 관리자 페이지 개발
- 카카오맵 API 연동 및 위치 기반 서비스 구현
- Redis Geo를 활용한 거리 계산 및 정렬
- Redis Trie 기반 자동완성 검색
- Native Query 폴백 로직 구현

## 🛠 기술 스택

### 백엔드
- **언어 & 프레임워크**: Java 17, Spring Boot 3.x
- **ORM & 데이터 접근**: Spring Data JPA, JPQL
- **보안**: Spring Security, JWT
- **캐싱**: Redis
- **검증**: Spring Validation
- **문서화**: Swagger(OpenAPI)

### 데이터베이스
- **주 데이터베이스**: MySQL 8.0
- **캐시 & 세션**: Redis

### 인프라 & 배포
- **빌드 도구**: Gradle
- **컨테이너화**: Docker, Docker Compose
- **CI/CD**: GitHub Actions

## 🏗 시스템 아키텍처

<div align="center">
  <img src="https://via.placeholder.com/800x500?text=System+Architecture" alt="System Architecture" width="800"/>
</div>

## 📂 프로젝트 구조

```
src/
  main/
    java/org/example/hugmeexp/
      HugmeExpApplication.java
      domain/
        attendance/      # 출석 관련 (Controller, Service, Repository, Entity 등)
        bookmark/        # 북마크 관련 기능
        mission/         # 미션 관리 핵심 도메인
        missionGroup/    # 미션 그룹 관리
        missionTask/     # 미션 세부 태스크 관리
        notification/    # 알림 시스템
        praise/          # 칭찬 시스템
        quest/           # 퀘스트 관리
        shop/            # 포인트 상점 시스템
        studydiary/      # 학습 일지 관리
        user/            # 사용자 관리 및 인증
      global/
        common/          # 공통 유틸리티, 상수, 열거형 등
        config/          # 애플리케이션 설정
        error/           # 예외 처리 및 에러 응답
        security/        # 보안 설정 및 JWT 관련
    resources/
      application.yml    # 기본 설정
      application-dev.yml # 개발 환경 설정
      application-prod.yml # 운영 환경 설정
  test/
    java/org/example/hugmeexp/  # 단위/통합 테스트
```

## 🚀 설치 및 실행 방법

### 사전 요구사항
- JDK 17 이상
- MySQL 8.0
- Redis
- Docker & Docker Compose (선택사항)

### 로컬 개발 환경 설정

1. **저장소 클론**
   ```bash
   git clone https://github.com/Please-Hug/Please-Hug_BackEnd.git
   cd Please-Hug_BackEnd
   ```

2. **데이터베이스 설정**
   ```bash
   # Docker를 사용하는 경우
   docker-compose -f docker-compose.infra.yml up
   
   # 또는 로컬 MySQL과 Redis 사용
   # application-local.yml에서 데이터베이스 설정 확인
   ```

3. **애플리케이션 빌드 및 실행**
   ```bash
   ./gradlew clean build
   java -jar build/libs/hugmeEXP-0.0.1-SNAPSHOT.jar --spring.profiles.active=local
   ```

4. **Docker로 실행** (선택사항)
   ```bash
   docker-compose -f docker-compose.yml up
   ```

### 환경별 실행
- **개발 환경**: `--spring.profiles.active=dev`
- **테스트 환경**: `--spring.profiles.active=test`
- **운영 환경**: `--spring.profiles.active=prod`

## 📝 API 문서

애플리케이션이 실행된 후 Swagger UI를 통해 API 문서에 접근할 수 있습니다:

- **Swagger UI**: `http://localhost:8080/swagger-ui/index.html`
- **OpenAPI 문서**: `http://localhost:8080/v3/api-docs`

<div align="center">
  <img src="https://github.com/user-attachments/assets/dc11b8b1-feee-409e-99cc-405789ebf647" alt="API Documentation" width="800"/>
</div>

## 🧪 테스트

```bash
# 전체 테스트 실행
./gradlew test

# 특정 도메인 테스트만 실행
./gradlew test --tests "org.example.hugmeexp.domain.mission.*"
```
