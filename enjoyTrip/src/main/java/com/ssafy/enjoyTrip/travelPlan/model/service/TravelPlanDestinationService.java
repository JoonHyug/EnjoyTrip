package com.ssafy.enjoyTrip.travelPlan.model.service;

import com.ssafy.enjoyTrip.travelPlan.dto.TravelPlanDestination;

import java.sql.SQLException;
import java.util.List;

public interface TravelPlanDestinationService {
    /**
     * travelPlanId를 통한 전체 조회
     */
    public List<TravelPlanDestination> selectAll(int travelPlanId) throws SQLException;

    /**
     * 일정 생성
     */
    public boolean createTravelPlanDestination(List<TravelPlanDestination> travelPlanDestinationList) throws SQLException;

    /**
     * 일정 삭제
     */
    public boolean deleteByTravelPlanId(int travelPlanId) throws SQLException;
}
