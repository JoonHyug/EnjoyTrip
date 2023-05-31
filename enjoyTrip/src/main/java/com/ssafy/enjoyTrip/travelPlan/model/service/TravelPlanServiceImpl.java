package com.ssafy.enjoyTrip.travelPlan.model.service;

import com.ssafy.enjoyTrip.travelPlan.dto.TravelPlan;
import com.ssafy.enjoyTrip.travelPlan.dto.TravelPlanParameter;
import com.ssafy.enjoyTrip.travelPlan.model.mapper.TravelPlanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class TravelPlanServiceImpl implements TravelPlanService{

    @Autowired
    private TravelPlanMapper travelPlanMapper;

    @Override
    public boolean createTravelPlan(TravelPlan travelPlan) throws SQLException {
        if(travelPlan.getAccountId() == null || travelPlan.getTitle() == null){
            return false;
        }
        return travelPlanMapper.createTravelPlan(travelPlan) == 1;
    }

    @Override
    public boolean updateTravelPlan(TravelPlan travelPlan) throws SQLException {
        if(travelPlan.getAccountId() == null || travelPlan.getTitle() == null){
            return false;
        }
        return travelPlanMapper.updateTravelPlan(travelPlan) == 1;
    }

    @Override
    public boolean deleteTravelPlan(int id) throws SQLException {
        if(id == 0){
            return false;
        }
        return travelPlanMapper.deleteTravelPlan(id) == 1;
    }

    @Override
    public List<TravelPlan> selectAllTravelPlan(TravelPlanParameter travelPlanParameter) throws SQLException {
        int start = travelPlanParameter.getCurrentPage() == 0 ? 0 : (travelPlanParameter.getCurrentPage() - 1) * travelPlanParameter.getPerPage();
        travelPlanParameter.setStart(start);
        return travelPlanMapper.selectAllTravelPlan(travelPlanParameter);
    }

    @Override
    public int getTotalCount(TravelPlanParameter travelPlanParameter) throws SQLException {
        return travelPlanMapper.getTotalCount(travelPlanParameter);
    }

    @Override
    public List<TravelPlan> selectAllByAccountId(String accountId) throws SQLException {
        return travelPlanMapper.selectAllByAccountId(accountId);
    }


    @Override
    public void increaseHit(int id) throws SQLException {
        travelPlanMapper.increaseHit(id);
    }

    @Override
    public String getAccountIdToTravelPlanId(int travelPlanId) throws SQLException {
        return travelPlanMapper.getAccountIdToTravelPlanId(travelPlanId);
    }

    @Override
    public int retrieveTravelPlanId(String accountId) throws SQLException {
        return travelPlanMapper.retrieveTravelPlanId(accountId);
    }

    @Override
    public String idToTitle(int id) throws SQLException {
        return travelPlanMapper.idToTitle(id);
    }
}
