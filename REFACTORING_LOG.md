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

## [RF-004] DTO 변환 방식 통일 — static factory method (`from()`)

- **Before**: Entity → Response DTO 변환 방식이 3가지 혼재
  - MapStruct 매퍼: 9개 도메인 (mission, quest, shop, praise, missionGroup, missionTask, user, admin)
  - DTO 내 static factory (`from()`, `of()`): 10개 도메인 (notification, recruitment, bookmark 등)
  - 서비스에서 빌더 직접 호출: 12개 도메인 (AWS, admin, attendance 등)
- **After**: 모든 Entity → Response DTO 변환을 `ResponseDTO.from(Entity)` 정적 팩토리 메서드로 통일
- **Why**: MapStruct는 이 프로젝트에서 `default` 메서드에 수동 코드를 작성하는 형태로, 자동 매핑 이점을 못 누리고 있었음. `from()`은 DTO 안에 변환 로직이 있어 코드 추적이 쉽고 외부 의존성 없음
- **Category**: 코드 일관성, 의존성 제거
- **범위**: Entity → Response DTO 변환만 대상. Request DTO → Entity는 서비스에서 빌더 호출 유지. AWS 도메인은 대상 외 (단순 Entity→DTO 변환이 아님)
- **변경 도메인**: praise, shop, mission, quest, missionGroup, missionTask, user, admin (8개 도메인)
- **변경 내용**:
  - Response DTO에 `public static XxxResponse from(Entity)` 메서드 추가 (16개 DTO)
  - 서비스에서 `mapper.toResponse(entity)` → `XxxResponse.from(entity)` 또는 `XxxResponse::from`
  - 서비스에서 `mapper.toEntity(request)` → `Entity.builder()...build()` 인라인
  - MapStruct 매퍼 클래스 12개 삭제, 독립 정적 매퍼 유틸리티 3개 삭제
  - 테스트 8개 파일에서 매퍼 Mock 제거 및 검증 방식 수정
- **삭제된 매퍼 파일**:
  - praise: `PraiseMapper`, `CommentMapper`, `CommentEmojiReactionMapper`, `PraiseEmojiReactionMapper`
  - mission: `MissionMapper`, `UserMissionMapper`, `UserMissionStateLogMapper`, `UserMissionSubmissionMapper`
  - missionGroup: `MissionGroupMapper`, `UserMissionGroupMapper`
  - missionTask: `MissionTaskMapper`
  - shop: `ProductMapper`
  - user: `UserResponseMapper`, `AdminUserResponseMapper`, `ProfileImageMapper`

---

## PraiseService 코드 중복 제거

- **Before**: 이모지 반응 그룹핑 로직이 4곳에서 동일하게 반복, 댓글 프로필 변환도 2곳에서 `new UserProfileResponse(...)` 직접 생성
- **After**: `groupEmojiReactions()`, `toCommentProfiles()` private 메서드로 추출. `UserProfileResponse.from()` 활용
- **Why**: 동일 로직 반복 제거, 이전 RF-004에서 추가한 `from()` 패턴과 일관성 확보
- **Category**: 코드 중복 제거
- **변경 파일**: PraiseService.java (363줄 → 332줄)
- **변경 내용**:
  - `groupEmojiReactions(List<PraiseEmojiReaction>)` — 이모지 그룹핑 4곳 → 1곳
  - `toCommentProfiles(List<PraiseComment>)` — 댓글 프로필 변환 2곳 → 1곳
  - `getRecentPraiseSenders` — `new UserProfileResponse(...)` → `UserProfileResponse.from()` 통일

---

## 앞으로 할 리팩토링 (TODO)

### 매직 넘버 상수 추출
- **우선순위**: 낮음
- **현재 문제**: 서비스 내 비즈니스 로직 값이 하드코딩 (예: 출석 EXP `31`, 포인트 `1`)
- **목표**: 상수 또는 설정값으로 추출
- **영향 범위**: 3~5개 서비스 파일
