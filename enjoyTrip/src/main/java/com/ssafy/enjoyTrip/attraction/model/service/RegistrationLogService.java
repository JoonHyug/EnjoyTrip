package com.ssafy.enjoyTrip.attraction.model.service;

import com.ssafy.enjoyTrip.attraction.dto.RegistrationLog;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

public interface RegistrationLogService {
    /**
     * Registration_log 등록
     */
    void addRegistrationLog(RegistrationLog registrationLog) throws SQLException;

    /**
     * 목록 요청 GROUP BY content_id
     */
    List<RegistrationLog> selectByContentId(int contentId) throws SQLException;

    /**
     * 정보 등록 완료 후 check 변환
     */
    void changeCheckToTrue(int contentId) throws SQLException;

    /**
     * 이미 등록 요청을 했는지 체크
     */
    RegistrationLog alreadyRegistration(RegistrationLog registrationLog) throws SQLException;
}
