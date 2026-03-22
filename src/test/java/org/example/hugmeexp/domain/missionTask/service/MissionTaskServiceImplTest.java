package org.example.hugmeexp.domain.missionTask.service;

import org.example.hugmeexp.domain.mission.entity.Mission;
import org.example.hugmeexp.domain.mission.entity.UserMission;
import org.example.hugmeexp.domain.mission.exception.MissionNotFoundException;
import org.example.hugmeexp.domain.mission.exception.UserMissionNotFoundException;
import org.example.hugmeexp.domain.mission.repository.MissionRepository;
import org.example.hugmeexp.domain.mission.repository.UserMissionRepository;
import org.example.hugmeexp.domain.missionTask.dto.request.MissionTaskRequest;
import org.example.hugmeexp.domain.missionTask.dto.response.MissionTaskResponse;
import org.example.hugmeexp.domain.missionTask.dto.response.UserMissionTaskResponse;
import org.example.hugmeexp.domain.missionTask.entity.MissionTask;
import org.example.hugmeexp.domain.missionTask.entity.UserMissionTask;
import org.example.hugmeexp.domain.missionTask.enums.TaskState;
import org.example.hugmeexp.domain.missionTask.exception.MissionTaskNotFoundException;
import org.example.hugmeexp.domain.missionTask.repository.MissionTaskRepository;
import org.example.hugmeexp.domain.missionTask.repository.UserMissionTaskRepository;
import org.example.hugmeexp.domain.user.entity.User;
import org.example.hugmeexp.domain.user.exception.UserNotFoundException;
import org.example.hugmeexp.domain.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("미션 태스크 서비스 테스트")
class MissionTaskServiceImplTest {
    @Mock
    MissionTaskRepository missionTaskRepository;
    @Mock
    UserMissionTaskRepository userMissionTaskRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    UserMissionRepository userMissionRepository;
    @Mock
    MissionRepository missionRepository;
    @InjectMocks
    MissionTaskServiceImpl missionTaskService;

    private final Long SAMPLE_ID = 1L;
    private final String SAMPLE_USERNAME = "user1";

    @Test
    @DisplayName("미션 ID로 미션 태스크 목록 조회 - 성공")
    void findByMissionId_Success() {
        // Given
        Mission mission = Mission.builder().id(SAMPLE_ID).build();
        MissionTask task = MissionTask.builder().id(SAMPLE_ID).name("task").score(10).tip("tip").mission(mission).build();
        when(missionTaskRepository.findByMissionId(SAMPLE_ID)).thenReturn(List.of(task));
        // When
        List<MissionTaskResponse> result = missionTaskService.findByMissionId(SAMPLE_ID);
        // Then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getId()).isEqualTo(SAMPLE_ID);
    }

    @Test
    @DisplayName("유저명+미션ID로 유저 미션 태스크 목록 조회 - 성공")
    void findUserMissionTasksByUsernameAndMissionId_Success() {
        // Given
        User user = mock(User.class);
        Mission mission = mock(Mission.class);
        UserMission userMission = mock(UserMission.class);
        MissionTask missionTask = mock(MissionTask.class);
        UserMissionTask userMissionTask = UserMissionTask.builder()
                .id(SAMPLE_ID)
                .userMission(userMission)
                .missionTask(missionTask)
                .state(TaskState.COMPLETED)
                .build();
        when(userMission.getId()).thenReturn(SAMPLE_ID);
        when(missionTask.getId()).thenReturn(SAMPLE_ID);
        when(userRepository.findByUsername(SAMPLE_USERNAME)).thenReturn(Optional.of(user));
        when(missionRepository.findById(SAMPLE_ID)).thenReturn(Optional.of(mission));
        when(userMissionRepository.findByUserAndMission(user, mission)).thenReturn(Optional.of(userMission));
        when(userMissionTaskRepository.findByUserMission(userMission)).thenReturn(List.of(userMissionTask));
        // When
        List<UserMissionTaskResponse> result = missionTaskService.findUserMissionTasksByUsernameAndMissionId(SAMPLE_USERNAME, SAMPLE_ID);
        // Then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getId()).isEqualTo(SAMPLE_ID);
    }

    @Test
    @DisplayName("유저명+미션ID로 유저 미션 태스크 목록 조회 - 유저 없음")
    void findUserMissionTasksByUsernameAndMissionId_UserNotFound() {
        // Given
        when(userRepository.findByUsername(SAMPLE_USERNAME)).thenReturn(Optional.empty());
        // When & Then
        assertThatThrownBy(() -> missionTaskService.findUserMissionTasksByUsernameAndMissionId(SAMPLE_USERNAME, SAMPLE_ID))
                .isInstanceOf(UserNotFoundException.class);
    }

    @Test
    @DisplayName("미션 태스크 추가 - 성공")
    void addMissionTask_Success() {
        // Given
        MissionTaskRequest request = MissionTaskRequest.builder().name("task").build();
        Mission mission = mock(Mission.class);
        when(mission.getId()).thenReturn(SAMPLE_ID);
        MissionTask saved = MissionTask.builder().id(SAMPLE_ID).name("task").mission(mission).build();
        when(missionRepository.findById(SAMPLE_ID)).thenReturn(Optional.of(mission));
        when(missionTaskRepository.save(any(MissionTask.class))).thenReturn(saved);
        // When
        MissionTaskResponse result = missionTaskService.addMissionTask(SAMPLE_ID, request);
        // Then
        assertThat(result.getId()).isEqualTo(SAMPLE_ID);
    }

    @Test
    @DisplayName("미션 태스크 추가 - 미션 없음")
    void addMissionTask_MissionNotFound() {
        // Given
        MissionTaskRequest request = MissionTaskRequest.builder().name("task").build();
        when(missionRepository.findById(SAMPLE_ID)).thenReturn(Optional.empty());
        // When & Then
        assertThatThrownBy(() -> missionTaskService.addMissionTask(SAMPLE_ID, request))
                .isInstanceOf(MissionNotFoundException.class);
    }

    @Test
    @DisplayName("미션 태스크 삭제 - 성공")
    void deleteMissionTask_Success() {
        // Given
        when(missionTaskRepository.existsById(SAMPLE_ID)).thenReturn(true);
        // When
        missionTaskService.deleteMissionTask(SAMPLE_ID);
        // Then
        verify(missionTaskRepository).deleteById(SAMPLE_ID);
    }

    @Test
    @DisplayName("미션 태스크 삭제 - 태스크 없음")
    void deleteMissionTask_NotFound() {
        // Given
        when(missionTaskRepository.existsById(SAMPLE_ID)).thenReturn(false);
        // When & Then
        assertThatThrownBy(() -> missionTaskService.deleteMissionTask(SAMPLE_ID))
                .isInstanceOf(MissionTaskNotFoundException.class);
    }

    @Test
    @DisplayName("미션 태스크 수정 - 성공")
    void updateMissionTask_Success() {
        // Given
        MissionTaskRequest request = MissionTaskRequest.builder().name("task").score(10).tip("tip").build();
        MissionTask entity = mock(MissionTask.class);
        when(missionTaskRepository.findById(SAMPLE_ID)).thenReturn(Optional.of(entity));
        // When
        missionTaskService.updateMissionTask(SAMPLE_ID, request);
        // Then
        verify(entity).setName("task");
        verify(entity).setScore(10);
        verify(entity).setTip("tip");
    }

    @Test
    @DisplayName("미션 태스크 수정 - 태스크 없음")
    void updateMissionTask_NotFound() {
        // Given
        MissionTaskRequest request = MissionTaskRequest.builder().name("task").build();
        when(missionTaskRepository.findById(SAMPLE_ID)).thenReturn(Optional.empty());
        // When & Then
        assertThatThrownBy(() -> missionTaskService.updateMissionTask(SAMPLE_ID, request))
                .isInstanceOf(MissionTaskNotFoundException.class);
    }

    @Test
    @DisplayName("유저 미션 태스크 상태 변경 - 성공(신규)")
    void changeUserMissionTaskState_New() {
        // Given
        User user = mock(User.class);
        MissionTask missionTask = mock(MissionTask.class);
        Mission mission = mock(Mission.class);
        UserMission userMission = mock(UserMission.class);
        when(userRepository.findByUsername(SAMPLE_USERNAME)).thenReturn(Optional.of(user));
        when(missionTaskRepository.findById(SAMPLE_ID)).thenReturn(Optional.of(missionTask));
        when(missionTask.getMission()).thenReturn(mission);
        when(userMissionRepository.findByUserAndMission(user, mission)).thenReturn(Optional.of(userMission));
        when(userMissionTaskRepository.findByUserMission_UserAndMissionTask(user, missionTask)).thenReturn(Optional.empty());
        // When
        missionTaskService.changeUserMissionTaskState(SAMPLE_USERNAME, SAMPLE_ID, TaskState.COMPLETED);
        // Then
        verify(userMissionTaskRepository).save(any(UserMissionTask.class));
    }

    @Test
    @DisplayName("유저 미션 태스크 상태 변경 - 성공(기존)")
    void changeUserMissionTaskState_Existing() {
        // Given
        User user = mock(User.class);
        MissionTask missionTask = mock(MissionTask.class);
        Mission mission = mock(Mission.class);
        UserMission userMission = mock(UserMission.class);
        UserMissionTask userMissionTask = mock(UserMissionTask.class);
        when(userRepository.findByUsername(SAMPLE_USERNAME)).thenReturn(Optional.of(user));
        when(missionTaskRepository.findById(SAMPLE_ID)).thenReturn(Optional.of(missionTask));
        when(missionTask.getMission()).thenReturn(mission);
        when(userMissionRepository.findByUserAndMission(user, mission)).thenReturn(Optional.of(userMission));
        when(userMissionTaskRepository.findByUserMission_UserAndMissionTask(user, missionTask)).thenReturn(Optional.of(userMissionTask));
        // When
        missionTaskService.changeUserMissionTaskState(SAMPLE_USERNAME, SAMPLE_ID, TaskState.COMPLETED);
        // Then
        verify(userMissionTask).setState(TaskState.COMPLETED);
    }

    @Test
    @DisplayName("유저 미션 태스크 상태 변경 - 유저 없음")
    void changeUserMissionTaskState_UserNotFound() {
        // Given
        when(userRepository.findByUsername(SAMPLE_USERNAME)).thenReturn(Optional.empty());
        // When & Then
        assertThatThrownBy(() -> missionTaskService.changeUserMissionTaskState(SAMPLE_USERNAME, SAMPLE_ID, TaskState.COMPLETED))
                .isInstanceOf(UserNotFoundException.class);
    }

    @Test
    @DisplayName("유저 미션 태스크 상태 변경 - 태스크 없음")
    void changeUserMissionTaskState_TaskNotFound() {
        // Given
        User user = mock(User.class);
        when(userRepository.findByUsername(SAMPLE_USERNAME)).thenReturn(Optional.of(user));
        when(missionTaskRepository.findById(SAMPLE_ID)).thenReturn(Optional.empty());
        // When & Then
        assertThatThrownBy(() -> missionTaskService.changeUserMissionTaskState(SAMPLE_USERNAME, SAMPLE_ID, TaskState.COMPLETED))
                .isInstanceOf(MissionTaskNotFoundException.class);
    }

    @Test
    @DisplayName("유저 미션 태스크 상태 변경 - 유저 미션 없음")
    void changeUserMissionTaskState_UserMissionNotFound() {
        // Given
        User user = mock(User.class);
        MissionTask missionTask = mock(MissionTask.class);
        Mission mission = mock(Mission.class);
        when(userRepository.findByUsername(SAMPLE_USERNAME)).thenReturn(Optional.of(user));
        when(missionTaskRepository.findById(SAMPLE_ID)).thenReturn(Optional.of(missionTask));
        when(missionTask.getMission()).thenReturn(mission);
        when(userMissionRepository.findByUserAndMission(user, mission)).thenReturn(Optional.empty());
        // When & Then
        assertThatThrownBy(() -> missionTaskService.changeUserMissionTaskState(SAMPLE_USERNAME, SAMPLE_ID, TaskState.COMPLETED))
                .isInstanceOf(UserMissionNotFoundException.class);
    }
}