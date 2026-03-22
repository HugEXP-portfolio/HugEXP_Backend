package org.example.hugmeexp.domain.praise.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.hugmeexp.domain.praise.dto.request.PraiseRequest;
import org.example.hugmeexp.domain.praise.dto.request.PraiseSearchRequest;
import org.example.hugmeexp.domain.praise.dto.response.PraiseDetailResponse;
import org.example.hugmeexp.domain.praise.dto.response.PraiseRatioResponse;
import org.example.hugmeexp.domain.praise.dto.response.PraiseResponse;
import org.example.hugmeexp.domain.praise.dto.response.RecentPraiseSenderResponse;
import org.example.hugmeexp.domain.praise.service.PraiseService;
import org.example.hugmeexp.global.common.response.ApiResponse;
import org.example.hugmeexp.domain.user.entity.User;
import org.example.hugmeexp.global.security.CustomUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j    // 로깅 어노테이션
@RestController
@RequestMapping("/api/v1/praises")
@RequiredArgsConstructor
@Tag(name = "Praise" , description = "칭찬 관련 API")
public class PraiseController {

    private final PraiseService praiseService;

    /* 칭찬 생성 */
    @Operation(summary = "칭찬 생성", description = "새로운 칭찬을 생성합니다")
    @PostMapping
    public ResponseEntity<ApiResponse<PraiseResponse>> createPraise(
            @RequestBody @Valid PraiseRequest praiseRequestDTO,
            @AuthenticationPrincipal CustomUserDetails userDetails){

        log.info("Praise creation request : {} ", praiseRequestDTO);
        log.debug("Current logged-in user for praise creation: {}", userDetails.getUser().getUsername());

        // 로그인된 사용자 정보에서 User 꺼낸다
        User senderId = userDetails.getUser();

        PraiseResponse result = praiseService.createPraise(praiseRequestDTO,senderId );

        log.info("Praise saved successfully : {} " , result);

        ApiResponse<PraiseResponse> response = ApiResponse.success("작성 완료", result);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /* 기본 - 날짜 조회 / ME / keyword */
    // /api/v1/praises/search?startDate=OOOO-OO-OO&endDate=OOOO-OO-OO
    @Operation(summary = "날짜 기준 칭찬 게시물 조회", description = "날짜, 로그인 유저 여부(me), 키워드(keyword)로 칭찬을 조회합니다")
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<PraiseResponse>>> getDatePraises(
            @Valid @ModelAttribute PraiseSearchRequest praiseSearchRequestDTO,
            @AuthenticationPrincipal CustomUserDetails userDetails){

        LocalDate startDate = praiseSearchRequestDTO.getStartDate();
        LocalDate endDate = praiseSearchRequestDTO.getEndDate();
        boolean me = praiseSearchRequestDTO.isMe();
        String keyword = praiseSearchRequestDTO.getKeyword();

        log.info("Received praise search request: startDate={}, endDate={}", startDate, endDate);
        log.debug("Current logged-in user for date-based praise search: {}", userDetails.getUser().getUsername());

        if (startDate.isAfter(endDate)) {
            log.warn("Invalid date range in praise search: startDate={} is after endDate={}", startDate, endDate);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.success("startDate 은 endDate 보다 이후일 수 없습니다.", List.of()));
        }

        // 나에게 관련관 것만 보기 위한 조건 유저
        User currentUser = userDetails.getUser();
        List<PraiseResponse> result;

        // keyword 가 있는 경우 분기 처리
        if(StringUtils.hasText(keyword)){
            result = praiseService.searchByKeywordAndDate(startDate,endDate,currentUser,me,keyword);
        }else {
            result = praiseService.findByDateRange(startDate,endDate,currentUser,me);
        }

        log.info("Total praises found in date range: {} entries", result.size());

        ApiResponse<List<PraiseResponse>> response = ApiResponse.success("날짜 기준 조회 성공", result);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /* 칭찬 반응 좋은 칭찬글 */
    @Operation(summary = "반응 좋은 칭찬 글 조회", description = "반응 수 기준으로 상위 5개 칭찬 글 조회합니다 ")
    @GetMapping("/popular")
    public ResponseEntity<ApiResponse<List<PraiseResponse>>> getPopularPraises(
            @Valid @ModelAttribute PraiseSearchRequest praiseSearchRequestDTO){

        LocalDate startDate = praiseSearchRequestDTO.getStartDate();
        LocalDate endDate = praiseSearchRequestDTO.getEndDate();

        log.info("Received popular praise search request: startDate={}, endDate={}", startDate, endDate);

        if (startDate.isAfter(endDate)) {
            log.warn("Invalid date range: startDate is after endDate");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.success("startDate 은 endDate 보다 이후일 수 없습니다.", List.of()));
        }

        List<PraiseResponse> popularPraises = praiseService.findPopularPraises(startDate, endDate, 5);

        ApiResponse<List<PraiseResponse>> response = ApiResponse.success("반응 좋은 칭찬 글 조회 성공", popularPraises);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    /* 칭찬 칭찬 비율(한달동안 받은 칭찬 종류 각각 비율) */
    @Operation(summary = "한 달간 받은 칭찬 비율 조회", description = "로그인된 사용자가 한 달 동안 받은 칭찬을 타입별로 비율 계산합니다 ")
    @GetMapping("/me/ratio")
    public ResponseEntity<ApiResponse<List<PraiseRatioResponse>>> getPraiseRatio(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ){
        log.debug("Received praise ratio request - username : {}", userDetails.getUser().getUsername());

        Long userId = userDetails.getUser().getId();

        List<PraiseRatioResponse> praiseRatio = praiseService.getPraiseRatioForLastMonth(userId);

        log.info("Praise ratio result count: {}", praiseRatio.size());

        ApiResponse<List<PraiseRatioResponse>> response = ApiResponse.success("받은 칭찬 비율 조회 성공", praiseRatio);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    /* 칭찬 최근 칭찬 보낸 유저 조회 */
    @Operation(summary = "최근 칭찬 보낸 유저 조회 성공", description = "로그인된 사용자에게 가장 최근에 칭찬을 보낸 3명의 유저를 조회합니다. ")
    @GetMapping("/recent-senders")
    public ResponseEntity<ApiResponse<List<RecentPraiseSenderResponse>>> getRecentPraiseSenders(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ){
        log.debug("Received recent praise senders request from user : {}", userDetails.getUser().getUsername());
        Long userId = userDetails.getUser().getId();
        List<RecentPraiseSenderResponse> recentSender = praiseService.getRecentPraiseSenders(userId);

        log.info("Recent praise senders result count: {}", recentSender.size());

        ApiResponse<List<RecentPraiseSenderResponse>> response = ApiResponse.success("최근 칭찬 보낸 유저 조회 성공", recentSender);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /* 칭찬 상세 조회 */
    @Operation(summary = "칭찬 상세 조회", description = "칭찬 게시물 한 개를 조회합니다.")
    @GetMapping("/{praiseId}")
    public ResponseEntity<ApiResponse<PraiseDetailResponse>> getPraiseDetail(
            @PathVariable Long praiseId
    ){
        log.info("Received request for praise detail: praiseId={}",praiseId);

        PraiseDetailResponse praiseDetail = praiseService.getPraiseDetail(praiseId);

        ApiResponse<PraiseDetailResponse> response = ApiResponse.success("조회 완료", praiseDetail);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
