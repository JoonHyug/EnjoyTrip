package com.ssafy.enjoyTrip.travelPlan.model.service;

import com.ssafy.enjoyTrip.travelPlan.dto.TravelPlan;
import com.ssafy.enjoyTrip.travelPlan.dto.TravelPlanParameter;

import java.sql.SQLException;
import java.util.List;

public interface TravelPlanService {
    /**
     * 여행 계획 생성
     */
    public boolean createTravelPlan(TravelPlan travelPlan) throws SQLException;

    /**
     * 여행 계획(제목) 수정
     */
    public boolean updateTravelPlan(TravelPlan travelPlan) throws SQLException;

    /**
     * 여행 계획 삭제
     */
    public boolean deleteTravelPlan(int id) throws SQLException;

    /**
     * 여행 계획 전체 조회
     */
    public List<TravelPlan> selectAllTravelPlan(TravelPlanParameter travelPlanParameter) throws SQLException;

    /**
     * 여행 계획 전체 길이 조회
     */
    public int getTotalCount(TravelPlanParameter travelPlanParameter) throws SQLException;

    /**
     * 특정 사용자가 작성한 여행 계획 조회
     */
    public List<TravelPlan> selectAllByAccountId(String accountId) throws SQLException;

    /**
     * 조회수 증가
     */
    public void increaseHit(int id) throws SQLException;

    /**
     * travelPlanId를 통한 작성자(accountId) 조회
     */
    public String getAccountIdToTravelPlanId(int travelPlanId) throws SQLException;

    /**
     * 현재 작성중인 travelPlan의 id 반환
     */
    public int retrieveTravelPlanId(String accountId) throws SQLException;

    /**
     * id를 통한 title 반환
     */
    public String idToTitle(int id) throws SQLException;
}
