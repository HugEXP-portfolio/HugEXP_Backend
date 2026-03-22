# HugmeEXP Backend 리팩토링 로그

팀 프로젝트에서 발생한 코드 스타일 불일치와 구조적 문제를 개선하기 위한 리팩토링 기록입니다.

---

## [RF-001] 응답 래퍼 ApiResponse\<T\>로 통일

- **Before**: `Response<T>`(성공 응답)와 `ApiResponse<T>`(에러 응답) 두 가지 래퍼가 혼재. 컨트롤러 34개는 `Response<T>`, 예외 핸들러는 `ApiResponse<T>` 사용. 프론트엔드에서 `response.data` vs `response.data.data` 혼란 발생
- **After**: `ApiResponse<T>` 하나로 통일. 성공/실패 모두 동일한 JSON 구조 (`isSuccess`, `message`, `data`) 반환
- **Why**: 프론트엔드에서 일관된 응답 파싱이 가능하도록. `Response`는 일반적인 이름이라 다른 라이브러리와 충돌 가능성이 있고, `ApiResponse`가 API 전용 응답 객체라는 의미가 명확
- **Category**: 코드 일관성, API 설계
- **변경 파일**: 35+ 컨트롤러, GlobalExceptionHandler, ApiResponse 클래스 신규 생성
- **변경 내용**:
  - `Response.<T>builder().message("...").data(data).build()` → `ApiResponse.success("...", data)`
  - `Response.<Void>builder().message("...").build()` → `ApiResponse.success("...")`
  - 빌더 패턴 → 정적 팩토리 메서드 (`success()`, `failure()`)
  - 패키지 위치를 `global.common.response`로 통일
  - Swagger `@ApiResponse`와의 네이밍 충돌은 fully qualified name으로 해결

---

## [RF-002] DTO 네이밍 통일 및 패키지 구조 request/response 분리

- **Before**: DTO 클래스가 도메인별 `dto/` 패키지에 혼재. 네이밍도 `~DTO`, `~Dto`, `~Response`, `~Request` 등 불일치
- **After**: `dto/request/`와 `dto/response/` 하위 패키지로 분리. 네이밍에서 `DTO`/`Dto` 접미사 제거하고 `~Request`/`~Response`로 통일
- **Why**: 패키지 구조만으로 요청/응답 DTO를 구분 가능. 네이밍 일관성 확보
- **Category**: 코드 일관성, 패키지 구조
- **변경 도메인**: notification, praise, recruitment, attendance, quest, shop, auth, AWS (8개 도메인)
- **변경 파일**: 95+ 파일 (DTO, 컨트롤러, 서비스, 매퍼, 리포지토리, 테스트)
- **변경 내용**:
  - `dto/~DTO.java` → `dto/request/~Request.java` 또는 `dto/response/~Response.java`
  - Entity와 이름 충돌하는 경우 Response 접미사 추가 (예: `TagDTO` → `TagResponse`, `TechStackDTO` → `TechStackResponse`)
  - 모든 import, 참조, 생성자 호출 일괄 수정

---

## 예외 클래스 패턴 통일 + ErrorStatus 중앙화

- **Before**: 68개 예외 클래스가 `BaseCustomException`을 상속하며 4가지 패턴 혼재 (MESSAGE/CODE 상수, 인라인 하드코딩, ErrorStatus 참조, ErrorCode 참조). `HttpStatus.NOT_FOUND.value()` vs 커스텀 숫자 코드(3001, 3002) 혼재. `ErrorStatus` enum에는 8개만 등록
- **After**: 모든 예외가 `BaseException` + `ErrorStatus` 참조. 에러 코드/메시지가 `ErrorStatus` enum에 중앙 집중 (65개 추가)
- **Why**: 에러 정보를 한 곳에서 관리하여 일관성 확보. 예외 클래스는 어떤 에러인지만 지정하고, HTTP 상태/메시지는 enum이 담당
- **Category**: 코드 일관성, 예외 처리
- **변경 파일**: 91개 (68개 예외, ErrorStatus, BaseException, GlobalExceptionHandler, JWT 필터, 테스트 6개)
- **변경 내용**:
  - `extends BaseCustomException` → `extends BaseException`
  - `super(HttpStatus.NOT_FOUND, "메시지", 404)` → `super(ErrorStatus.XXX)`
  - 동적 메시지: `super(ErrorStatus.XXX, args...)` (format string 지원)
  - 커스텀 메시지 오버라이드: `super(message, ErrorStatus.XXX)` (파라미터 순서 반전)
  - 레거시 삭제: `BaseCustomException`, `ErrorCode`, `ExceptionController`, `ErrorResponse`

---

## 앞으로 할 리팩토링 (TODO)

### DTO 변환 방식 통일
- **우선순위**: 중간
- **현재 문제**: 도메인별로 DTO 변환 방식이 다름
  - praise, mission, shop → MapStruct 매퍼 사용
  - bookmark, attendance, notification → DTO 내 static factory 메서드 (`from()`, `fromEntity()`)
  - recruitment → 서비스에서 빌더 직접 호출
- **목표**: 한 가지 방식으로 통일 (MapStruct 또는 static factory)
- **영향 범위**: 5~8개 도메인

### PraiseService 코드 중복 제거
- **우선순위**: 중간
- **현재 문제**: 이모지 반응 그룹핑 로직이 4곳에서 동일하게 반복, 댓글 프로필 변환도 2곳 중복
- **목표**: 중복 로직을 private 메서드로 추출
- **영향 범위**: PraiseService (361줄)

### 매직 넘버 상수 추출
- **우선순위**: 낮음
- **현재 문제**: 서비스 내 비즈니스 로직 값이 하드코딩 (예: 출석 EXP `31`, 포인트 `1`)
- **목표**: 상수 또는 설정값으로 추출
- **영향 범위**: 3~5개 서비스 파일
