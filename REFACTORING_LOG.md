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
