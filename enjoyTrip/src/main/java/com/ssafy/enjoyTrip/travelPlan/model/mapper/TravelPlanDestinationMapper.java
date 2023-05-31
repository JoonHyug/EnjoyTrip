package com.ssafy.enjoyTrip.travelPlan.model.mapper;

import com.ssafy.enjoyTrip.travelPlan.dto.TravelPlanDestination;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface TravelPlanDestinationMapper {
    /**
     * travelPlanId를 통한 전체 조회
     */
    public List<TravelPlanDestination> selectAll(int travelPlanId) throws SQLException;

    /**
     * 일정 생성
     */
    public int createTravelPlanDestination(List<TravelPlanDestination> travelPlanDestinationList) throws SQLException;

    /**
     * 일정 삭제
     */
    public int deleteByTravelPlanId(int travelPlanId) throws SQLException;
}
