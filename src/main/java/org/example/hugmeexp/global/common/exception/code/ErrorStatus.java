package org.example.hugmeexp.global.common.exception.code;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * API 요청 처리 중 발생하는 오류에 대한 상태 코드를 관리하는 Enum
 */
@RequiredArgsConstructor
public enum ErrorStatus implements BaseCode {

    // ── Common ──
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류가 발생했습니다."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "유효성 검사 실패"),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "지원하지 않은 HTTP Method 입니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "권한이 없습니다."),

    // ── Attendance ──
    ATTENDANCE_ALREADY_CHECKED(HttpStatus.CONFLICT, "이미 출석체크가 완료되었습니다."),
    ATTENDANCE_USER_NOT_FOUND(HttpStatus.NOT_FOUND, "출석 사용자를 찾을 수 없습니다."),

    // ── Bookmark ──
    BOOKMARK_NOT_FOUND(HttpStatus.NOT_FOUND, "북마크를 찾을 수 없습니다."),
    BOOKMARK_USER_NOT_FOUND(HttpStatus.NOT_FOUND, "북마크 사용자를 찾을 수 없습니다."),

    // ── StudyDiary ──
    STUDY_DIARY_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 배움일기입니다."),
    STUDY_DIARY_LIKE_NOT_FOUND(HttpStatus.NOT_FOUND, "좋아요를 찾을 수 없습니다."),
    STUDY_DIARY_COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "댓글을 찾을 수 없습니다."),
    STUDY_DIARY_UNAUTHORIZED_ACCESS(HttpStatus.FORBIDDEN, "접근 권한이 없습니다."),
    STUDY_DIARY_USER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 유저입니다."),

    // ── StudyRoom ──
    STUDY_ROOM_CAPACITY_EXCEEDED(HttpStatus.BAD_REQUEST, "스터디룸 수용 인원을 초과했습니다."),
    INVALID_RESERVATION_TIME(HttpStatus.BAD_REQUEST, "유효하지 않은 예약 시간입니다."),
    STUDY_ROOM_NOT_FOUND(HttpStatus.NOT_FOUND, "ID %d에 해당하는 스터디 룸을 찾을 수 없습니다."),
    RESERVATION_ALREADY_STARTED(HttpStatus.BAD_REQUEST, "이미 시작된 예약은 취소할 수 없습니다."),
    UNAUTHORIZED_RESERVATION_ACCESS(HttpStatus.FORBIDDEN, "예약에 대한 접근 권한이 없습니다."),
    STUDY_HALL_NOT_FOUND(HttpStatus.NOT_FOUND, "ID %d에 해당하는 스터디 홀을 찾을 수 없습니다."),
    LOCATION_SERVICE_ERROR(HttpStatus.BAD_REQUEST, "위치 서비스 오류: %s"),
    STUDY_ROOM_RESERVATION_NOT_FOUND(HttpStatus.NOT_FOUND, "스터디룸 예약을 찾을 수 없습니다."),
    RESERVATION_CONFLICT(HttpStatus.CONFLICT, "예약 시간이 다른 예약과 겹칩니다."),

    // ── Praise ──
    MISMATCHED_COMMENT_REACTION(HttpStatus.BAD_REQUEST, "반응이 해당 댓글에 속하지 않습니다."),
    PRAISE_EMOJI_REACTION_NOT_FOUND(HttpStatus.NOT_FOUND, "칭찬 게시물에 반응이 없습니다."),
    PRAISE_NOT_FOUND(HttpStatus.NOT_FOUND, "칭찬 게시물을 찾을 수 없습니다"),
    COMMENT_EMOJI_REACTION_NOT_FOUND(HttpStatus.NOT_FOUND, "댓글 반응을 찾을 수 없습니다"),
    INVALID_EMOJI(HttpStatus.BAD_REQUEST, "이모지 형식이 유효하지 않습니다"),
    PRAISE_COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "댓글을 찾을 수 없습니다"),
    MISMATCHED_PRAISE_COMMENT(HttpStatus.BAD_REQUEST, "댓글이 해당 칭찬에 속하지 않습니다."),
    UNAUTHORIZED_EMOJI_DELETE(HttpStatus.FORBIDDEN, "이모지 삭제 권한이 없습니다"),
    INVALID_PRAISE_EMOJI_ACCESS(HttpStatus.BAD_REQUEST, "칭찬 게시물의 실제 반응과 일치하지 않습니다."),
    USER_NOT_FOUND_IN_PRAISE(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다"),
    DUPLICATE_EMOJI_REACTION(HttpStatus.CONFLICT, "이미 반응을 한 이모지 입니다"),
    FORBIDDEN_COMMENT_ACCESS(HttpStatus.FORBIDDEN, "댓글 삭제 권한이 없습니다"),

    // ── Notification ──
    NOTIFICATION_NOT_FOUND(HttpStatus.NOT_FOUND, "알림을 찾을 수 없습니다"),
    FORBIDDEN_NOTIFICATION_ACCESS(HttpStatus.FORBIDDEN, "본인의 알림만 읽을 수 있습니다."),

    // ── Recruitment ──
    RECRUITMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 모집 공고를 찾을 수 없습니다."),
    DUPLICATE_RECRUITMENT_BOOKMARK(HttpStatus.BAD_REQUEST, "이미 즐겨찾기에 등록된 공고입니다."),
    RECRUITMENT_BOOKMARK_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 즐겨찾기 정보를 찾을 수 없습니다."),

    // ── Shop ──
    OUT_OF_QUANTITY(HttpStatus.BAD_REQUEST, "The product is out of stock."),
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "There is no product with ID: %d"),
    PRODUCT_DELETED(HttpStatus.BAD_REQUEST, "Already deleted product."),
    USER_NOT_FOUND_IN_PURCHASE(HttpStatus.NOT_FOUND, "There is no user."),
    NOT_ENOUGH_POINT(HttpStatus.BAD_REQUEST, "Not enough point to purchase product."),

    // ── User ──
    PHONE_NUMBER_DUPLICATED(HttpStatus.CONFLICT, "이미 존재하는 휴대폰 번호입니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."),
    USERNAME_DUPLICATED(HttpStatus.CONFLICT, "이미 존재하는 사용자입니다."),
    PROFILE_IMAGE_NOT_FOUND(HttpStatus.BAD_REQUEST, "삭제할 프로필 이미지가 없습니다."),
    UNSUPPORTED_IMAGE_EXTENSION(HttpStatus.BAD_REQUEST, "지원하지 않는 이미지 확장자 : %s"),
    INVALID_VALUE(HttpStatus.BAD_REQUEST, "%s"),

    // ── Mission ──
    SUBMISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "제출 정보를 찾을 수 없습니다."),
    ALREADY_EXISTS_USER_MISSION(HttpStatus.CONFLICT, "이미 존재하는 유저 미션입니다."),
    USER_MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "유저 미션을 찾을 수 없습니다."),
    ALREADY_RECEIVED_REWARD(HttpStatus.BAD_REQUEST, "이미 보상을 수령했습니다."),
    SUB_MISSION_INTERNAL(HttpStatus.INTERNAL_SERVER_ERROR, "서브미션 처리 중 내부 오류가 발생했습니다."),
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 미션을 찾을 수 없습니다."),
    ALREADY_EXISTS_USER_MISSION_SUBMISSION(HttpStatus.CONFLICT, "이미 제출한 미션입니다."),
    INVALID_USER_MISSION_STATE(HttpStatus.BAD_REQUEST, "보상을 수령하기 위해서는 피드백이 완료되어야 합니다."),
    SUBMISSION_FILE_UPLOAD(HttpStatus.BAD_REQUEST, "파일 업로드에 실패했습니다. 파일 형식이 올바른지 확인해주세요."),

    // ── Quest ──
    QUEST_NOT_FOUND(HttpStatus.NOT_FOUND, "There is no quest with ID: %d"),
    QUEST_NOT_COMPLETABLE(HttpStatus.BAD_REQUEST, "This quest is not completable."),
    NO_SUCH_QUEST(HttpStatus.NOT_FOUND, "%s has not been assigned quest #%d."),
    ALREADY_COMPLETED_QUEST(HttpStatus.BAD_REQUEST, "Already completed quest #%d."),
    QUEST_DELETED(HttpStatus.BAD_REQUEST, "Already deleted quest."),
    USER_NOT_FOUND_IN_QUEST(HttpStatus.NOT_FOUND, "There is no user with username %s"),

    // ── MissionGroup ──
    MISSION_GROUP_USER_NOT_FOUND(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다."),
    TEACHER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 강사를 찾을 수 없습니다."),
    USER_MISSION_GROUP_NOT_FOUND(HttpStatus.NOT_FOUND, "유저 미션 그룹을 찾을 수 없습니다."),
    NOT_EXISTS_USER_MISSION_GROUP(HttpStatus.NOT_FOUND, "유저가 해당 미션 그룹에 참여하고 있지 않습니다."),
    ALREADY_EXISTS_USER_MISSION_GROUP(HttpStatus.CONFLICT, "유저가 이미 해당 미션 그룹에 참여하고 있습니다."),
    MISSION_GROUP_NOT_FOUND(HttpStatus.NOT_FOUND, "미션 그룹을 찾을 수 없습니다."),

    // ── MissionTask ──
    MISSION_TASK_NOT_FOUND(HttpStatus.NOT_FOUND, "미션 태스크를 찾을 수 없습니다."),

    // ── Auth ──
    INVALID_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 액세스 토큰입니다."),
    TOKEN_REUSE_DETECTED(HttpStatus.UNAUTHORIZED, "재사용된 리프레시 토큰입니다. 보안 위협이 감지되었습니다."),
    PASSWORD_MISMATCH(HttpStatus.BAD_REQUEST, "현재 비밀번호가 틀렸습니다."),
    INVALID_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 리프레시 토큰입니다."),
    ACCESS_TOKEN_STILL_VALID(HttpStatus.BAD_REQUEST, "현재 액세스 토큰이 유효하므로 재발급이 허용되지 않습니다."),
    LOGIN_FAILED(HttpStatus.UNAUTHORIZED, "아이디 또는 비밀번호가 잘못되었습니다.");

    public static final String PREFIX = "[ERROR]";

    private final HttpStatus httpStatus;
    private final String rawMessage;

    @Override
    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    @Override
    public String getMessage() {
        return PREFIX + this.rawMessage;
    }
}
