package org.example.hugmeexp.domain.praise.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.hugmeexp.domain.notification.service.NotificationService;
import org.example.hugmeexp.domain.praise.dto.request.PraiseRequest;
import org.example.hugmeexp.domain.praise.dto.response.EmojiReactionGroup;
import org.example.hugmeexp.domain.praise.dto.response.PraiseDetailResponse;
import org.example.hugmeexp.domain.praise.dto.response.PraiseRatioResponse;
import org.example.hugmeexp.domain.praise.dto.response.PraiseResponse;
import org.example.hugmeexp.domain.praise.dto.response.ReactionUser;
import org.example.hugmeexp.domain.praise.dto.response.RecentPraiseSenderResponse;
import org.example.hugmeexp.domain.praise.entity.*;
import org.example.hugmeexp.domain.praise.enums.PraiseType;
import org.example.hugmeexp.domain.praise.exception.PraiseNotFoundException;
import org.example.hugmeexp.domain.praise.exception.UserNotFoundInPraiseException;
import org.example.hugmeexp.domain.praise.repository.*;
import org.example.hugmeexp.domain.user.dto.response.UserProfileResponse;
import org.example.hugmeexp.domain.user.repository.UserRepository;
import org.example.hugmeexp.domain.user.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class PraiseService {

    private final PraiseRepository praiseRepository;
    private final CommentRepository commentRepository;
    private final PraiseEmojiReactionRepository praiseEmojiReactionRepository;
    private final UserRepository userRepository;
    private final PraiseReceiverRepository praiseReceiverRepository;
    private final CommentEmojiReactionRepository commentEmojiReactionRepository;
    private final CommentService commentService;
    private final NotificationService notificationService;


    private List<EmojiReactionGroup> groupEmojiReactions(List<PraiseEmojiReaction> reactions) {
        return reactions.stream()
                .collect(Collectors.groupingBy(PraiseEmojiReaction::getEmoji))
                .entrySet().stream()
                .map(entry -> EmojiReactionGroup.from(entry.getKey(), entry.getValue()))
                .toList();
    }

    private List<UserProfileResponse> toCommentProfiles(List<PraiseComment> comments) {
        return comments.stream()
                .map(c -> UserProfileResponse.from(c.getCommentWriter()))
                .toList();
    }

    /* 칭찬 생성 */
    @Transactional
    public PraiseResponse createPraise(PraiseRequest praiseRequestDTO, User sender) {


        List<User> receiverUsers = praiseRequestDTO.getReceiverUsername().stream()
                .map(username -> userRepository.findByUsername(username)
                        .orElseThrow(UserNotFoundInPraiseException::new)).toList();

        // DTO -> Entity
        Praise praise = Praise.builder()
                .sender(sender)
                .content(praiseRequestDTO.getContent())
                .praiseType(praiseRequestDTO.getType())
                .build();

        // DB 에 저장
        Praise saved = praiseRepository.save(praise);

        // PraiseReceiver 저장
        List<PraiseReceiver> praiseReceivers = receiverUsers.stream()
                .map(receiver -> PraiseReceiver.builder()
                        .praise(saved)
                        .receiver(receiver)
                        .build())
                .toList();
        praiseReceiverRepository.saveAll(praiseReceivers);

        // 알림 전송 시 자기 자신에게 보낸 칭찬은 제외
        for (User receiver : receiverUsers) {
            if (!receiver.getId().equals(sender.getId())) {
                notificationService.sendPraiseNotification(receiver, saved.getId());
            }
        }

        List<UserProfileResponse> commentPro = Collections.emptyList();
        List<EmojiReactionGroup> emojis = Collections.emptyList();

        // Entity -> DTO
        return PraiseResponse.from(saved, praiseReceivers, 0L, emojis, commentPro);


    }

    /* 날짜 조회 + 나와 관련된 칭찬 조건 */
    public List<PraiseResponse> findByDateRange(LocalDate startDate, LocalDate endDate, User currentUser, boolean me) {

        LocalDateTime startDateTime = startDate.atStartOfDay();    // 2025-06-01 00:00:00
        LocalDateTime endDateTime = endDate.atTime(LocalTime.MAX);    // 2025-06-18 23:59:59.999
        List<Praise> praiseList;

        if(me){
            // 내가 받은 칭찬
            List<PraiseReceiver> received = praiseReceiverRepository.findByReceiverAndCreatedAtBetween(currentUser,startDateTime,endDateTime);
            // 여러 명에게 칭찬 보냈을 경우 중복되어 보이는 칭찬 중복 제거
            List<Praise> receivedPraises = received.stream().map(PraiseReceiver::getPraise).distinct().toList();

            // 내가 보낸 칭찬
            List<Praise> sent = praiseRepository.findWithSenderBySenderAndCreatedAtBetween(currentUser,startDateTime,endDateTime);

            // 둘을 합치고 중복 제거
            praiseList = Stream.concat(receivedPraises.stream(),sent.stream()).distinct().toList();

        }else {
            // 전체 칭찬 조회
            praiseList = praiseRepository.findByCreatedAtBetween(startDateTime, endDateTime);
        }

        // 칭찬 받는 사람 리스트 매핑
        Map<Long, List<PraiseReceiver>> receiverMap = praiseReceiverRepository.findByPraiseIn(praiseList).stream()
                .collect(Collectors.groupingBy(pr -> pr.getPraise().getId()));

        List<PraiseComment> allComments = commentRepository.findWithWriterByPraiseIn(praiseList);
        Map<Long, List<PraiseComment>> commentMap = allComments.stream()
                .collect(Collectors.groupingBy(comment -> comment.getPraise().getId()));

        return praiseList.stream()
                .map(praise -> {
//                    long commentCount = commentRepository.countByPraise(praise);
                    long commentCount = commentMap.getOrDefault(praise.getId(), List.of()).size();

                    List<EmojiReactionGroup> emojiGroups = groupEmojiReactions(praiseEmojiReactionRepository.findByPraise(praise));

                    List<PraiseReceiver> receivers = receiverMap.getOrDefault(praise.getId(),List.of());

                    List<UserProfileResponse> commentProfiles = toCommentProfiles(commentMap.getOrDefault(praise.getId(), List.of()));

                    return PraiseResponse.from(praise,receivers,commentCount, emojiGroups,commentProfiles);

                }).collect(Collectors.toList());
    }

    /* 날짜 조회 + 나와 관련된 칭찬 조건 + keyword 조건 */
    public List<PraiseResponse> searchByKeywordAndDate(LocalDate startDate, LocalDate endDate, User currentUser, boolean me, String keyword) {
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(LocalTime.MAX);

        List<Praise> praiseList;

        if (me) {
            List<PraiseReceiver> received = praiseReceiverRepository.findRelatedPraiseByReceiverWithKeyword(currentUser, startDateTime, endDateTime, keyword);
            List<Praise> receivedPraises = received.stream()
                    .map(PraiseReceiver::getPraise)
                    .distinct()
                    .toList();

            List<Praise> sent = praiseRepository.findMySentPraiseWithKeywordWithSender(currentUser, startDateTime, endDateTime, keyword);

            praiseList = Stream.concat(receivedPraises.stream(), sent.stream())
                    .distinct()
                    .toList();
        } else {
            praiseList = praiseRepository.findALlPraisesWithSenderBySenderOrReceiverNameContaining(startDateTime, endDateTime, keyword);
        }

        // 칭찬 받는 사람 매핑
        Map<Long, List<PraiseReceiver>> receiverMap = praiseReceiverRepository.findByPraiseIn(praiseList).stream()
                .collect(Collectors.groupingBy(pr -> pr.getPraise().getId()));

        // 댓글 정보 한 번에 가져와서 매핑
        List<PraiseComment> allComments = commentRepository.findWithWriterByPraiseIn(praiseList);
        Map<Long, List<PraiseComment>> commentMap = allComments.stream()
                .collect(Collectors.groupingBy(c -> c.getPraise().getId()));

        return praiseList.stream()
                .map(praise -> {
                    long commentCount = commentMap.getOrDefault(praise.getId(), List.of()).size();

                    List<EmojiReactionGroup> emojiGroups = groupEmojiReactions(praiseEmojiReactionRepository.findByPraise(praise));

                    List<PraiseReceiver> receivers = receiverMap.getOrDefault(praise.getId(), List.of());
                    List<UserProfileResponse> commentProfiles = toCommentProfiles(commentMap.getOrDefault(praise.getId(), List.of()));

                    return PraiseResponse.from(praise, receivers, commentCount, emojiGroups, commentProfiles);
                }).collect(Collectors.toList());
    }



    /* 칭찬 반응 좋은 칭찬글 */
    public List<PraiseResponse> findPopularPraises(LocalDate startDate, LocalDate endDate, int i) {

        LocalDateTime startDateTime = startDate.atStartOfDay();    // 2025-06-01 00:00:00
        LocalDateTime endDateTime = endDate.atTime(LocalTime.MAX);    // 2025-06-18 23:59:59.999

        // 해당 기간 내 칭찬글 전체 조회
        List<Praise> praiseList = praiseRepository.findByCreatedAtBetween(startDateTime, endDateTime);

        // 받는 사람들 매핑
        Map<Long, List<PraiseReceiver>> receiverMap = praiseReceiverRepository.findByPraiseIn(praiseList).stream()
                .collect(Collectors.groupingBy(praiseReceiver -> praiseReceiver.getPraise().getId()));

        // DTO 변환 + 이모지 반응 수 기준 정렬
        return praiseList.stream()
                .map(praise -> {
                    long commentCount = commentRepository.countByPraise(praise);

                    List<EmojiReactionGroup> emojiGroups = groupEmojiReactions(praiseEmojiReactionRepository.findByPraise(praise));

                    // 수신자 리스트
                    List<PraiseReceiver> receivers = receiverMap.getOrDefault(praise.getId(), List.of());

                    return PraiseResponse.from(praise,receivers,commentCount,emojiGroups,List.of());

                })
                .filter(praiseResponseDTO -> praiseResponseDTO.getEmojis() != null &&
                        praiseResponseDTO.getEmojis().stream().mapToInt(EmojiReactionGroup::getCount).sum()>0)

                // 이모지 반응 수 총합 기준 내림차순 정렬
                .sorted(Comparator.comparingInt((PraiseResponse p) ->
                        p.getEmojis() == null ? 0 : p.getEmojis().stream().mapToInt(EmojiReactionGroup::getCount).sum()
                ).reversed())
                .limit(i)
                .collect(Collectors.toList());

    }

    /* 칭찬 칭찬 비율(한달동안 받은 칭찬 종류 각각 비율) */
    public List<PraiseRatioResponse> getPraiseRatioForLastMonth(Long userId) {

        // 한 달 날짜 범위 설정
        LocalDateTime endDateTime = LocalDateTime.now();
        LocalDateTime startDateTime = endDateTime.minusMonths(1);

        // 칭찬 타입 별로 count
        List<Object[]> result = praiseReceiverRepository.countPraiseTypeByUserInMonth(userId,startDateTime,endDateTime);

        // 총 받은 칭찬 개수 계산
        int total = result.stream()
                .mapToInt(row ->((Long) row[1]).intValue())
                .sum();

        // 받은 칭찬이 없으면 빈 리스트 반환
        if (total == 0){
            return Collections.emptyList();
        }

        // 비율 계산 후 DTO 변환
        return result.stream()
                .map(row ->{
                    PraiseType type = (PraiseType) row[0];
                    Long count = (Long) row[1];
                    int percentage = (int) Math.round(count*100.0/total);
                    return PraiseRatioResponse.from(type,percentage);
                })
                .collect(Collectors.toList());
    }

    /* 최근 칭찬 보낸 유저 조회 */
    public List<RecentPraiseSenderResponse> getRecentPraiseSenders(Long userId) {

        List<Praise> latestPraises = praiseReceiverRepository.findLatestPraisePerSender(userId);

        // 칭찬 받은게 없을 경우
        if (latestPraises.isEmpty()) {
            log.info("No recent praises found for user: {}", userId);
            return Collections.emptyList();
        }

        // 중복 제거된 보낸 유저 3명만 추출
        return latestPraises.stream()
                .sorted(Comparator.comparing(Praise::getCreatedAt).reversed())
                .map(Praise::getSender)
                .distinct()
                .limit(3)
                .map(sender -> RecentPraiseSenderResponse.from(sender, List.of(UserProfileResponse.from(sender))))
                .collect(Collectors.toList());
    }

    /* 칭찬 상세 조회 */
    public PraiseDetailResponse getPraiseDetail(Long praiseId) {

        // 칭찬 엔티티 조회
//        Praise praise = praiseRepository.findById(praiseId).orElseThrow(() -> new PraiseNotFoundException());

        // 칭찬 작성자 정보 조회
        Praise praise = praiseRepository.findWithSenderById(praiseId).orElseThrow(PraiseNotFoundException::new);

        // 칭찬 받은 사람 리스트 조회
        List<PraiseReceiver> receiverList = praiseReceiverRepository.findByPraise(praise);

        // 댓글 목록 조회
        List<PraiseComment> commentList = commentService.getCommentsByPraise(praise);

        // 게시물 이모지 반응 가져오기
        List<EmojiReactionGroup> emojiGroups = groupEmojiReactions(praiseEmojiReactionRepository.findByPraise(praise));

        List<CommentEmojiReaction> commentReactions = commentEmojiReactionRepository.findWithReactorByPraise(praise);

        // 댓글 별 이모지 반응 수 조회
        Map<Long, Map<String, List<ReactionUser>>> commentEmojiMap = commentReactions.stream()
                .collect(Collectors.groupingBy(
                        r -> r.getComment().getId(),
                        Collectors.groupingBy(
                                CommentEmojiReaction::getEmoji,
                                Collectors.mapping(
                                        r -> ReactionUser.builder()
                                                .id(r.getReactorWriter().getId())
                                                .username(r.getReactorWriter().getUsername())
                                                .name(r.getReactorWriter().getName())
                                                .build(),
                                        Collectors.toList()
                                )
                        )
                ));

        return PraiseDetailResponse.from(praise,receiverList,commentList,emojiGroups,commentEmojiMap);
    }
}
