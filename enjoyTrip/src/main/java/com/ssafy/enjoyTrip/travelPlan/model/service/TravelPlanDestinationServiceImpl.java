package com.ssafy.enjoyTrip.travelPlan.model.service;

import com.ssafy.enjoyTrip.travelPlan.dto.TravelPlanDestination;
import com.ssafy.enjoyTrip.travelPlan.model.mapper.TravelPlanDestinationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class TravelPlanDestinationServiceImpl implements TravelPlanDestinationService{

    @Autowired
    private TravelPlanDestinationMapper travelPlanDestinationMapper;

    @Override
    public List<TravelPlanDestination> selectAll(int travelPlanId) throws SQLException {
        return travelPlanDestinationMapper.selectAll(travelPlanId);
    }

    @Override
    public boolean createTravelPlanDestination(List<TravelPlanDestination> travelPlanDestinationList) throws SQLException {
        if(travelPlanDestinationList.size() == 0){
            return false;
        }
        return travelPlanDestinationMapper.createTravelPlanDestination(travelPlanDestinationList) >= 1;
    }

    @Override
    public boolean deleteByTravelPlanId(int travelPlanId) throws SQLException {
        if(travelPlanId == 0){
            return false;
        }
        return travelPlanDestinationMapper.deleteByTravelPlanId(travelPlanId) >= 1;
    }
}
