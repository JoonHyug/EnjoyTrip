package com.ssafy.enjoyTrip.travelPlan.controller;

import com.ssafy.enjoyTrip.travelPlan.dto.TravelPlan;
import com.ssafy.enjoyTrip.travelPlan.dto.TravelPlanDestination;
import com.ssafy.enjoyTrip.travelPlan.dto.TravelPlanParameter;
import com.ssafy.enjoyTrip.travelPlan.model.service.TravelPlanDestinationService;
import com.ssafy.enjoyTrip.travelPlan.model.service.TravelPlanService;
import com.ssafy.enjoyTrip.user.model.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/travel")
@CrossOrigin("*")
@Slf4j
public class TravelPlanController {
    @Autowired
    private TravelPlanService travelPlanService;

    @Autowired
    private TravelPlanDestinationService travelPlanDestinationService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> selectAllTravelPlan(TravelPlanParameter travelPlanParameter) throws SQLException{
        return ResponseEntity.ok(travelPlanService.selectAllTravelPlan(travelPlanParameter));
    }

    @GetMapping("/totalitems")
    public ResponseEntity<?> totalItems(TravelPlanParameter travelPlanParameter) throws SQLException{
        return ResponseEntity.ok(travelPlanService.getTotalCount(travelPlanParameter));
    }

    @GetMapping("/accountId")
    public ResponseEntity<?> getAccountId(int travelPlanId) throws SQLException{
        return ResponseEntity.ok(travelPlanService.getAccountIdToTravelPlanId(travelPlanId));
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<?> selectAllByAccountId(@PathVariable String accountId) throws SQLException{
        return ResponseEntity.ok(travelPlanService.selectAllByAccountId(accountId));
    }

    @GetMapping("/id")
    public ResponseEntity<?> getId(int id) throws SQLException{
        return ResponseEntity.ok(travelPlanService.idToTitle(id));
    }

    @PostMapping
    public ResponseEntity<?> createTravelPlan(@RequestBody TravelPlan travelPlan) throws SQLException{
        if(travelPlanService.createTravelPlan(travelPlan)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(500).build();
    }

    @PutMapping
    public ResponseEntity<?> updateTravelPlan(@RequestBody TravelPlan travelPlan) throws SQLException{
        if(travelPlanService.updateTravelPlan(travelPlan)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(500).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTravelPlan(@PathVariable int id) throws SQLException{
        if(travelPlanService.deleteTravelPlan(id)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(500).build();
    }

    @GetMapping("/recent/{accountId}")
    public ResponseEntity<?> recentTravelId(@PathVariable String accountId) throws SQLException{
        return ResponseEntity.ok(travelPlanService.retrieveTravelPlanId(accountId));
    }

    @PostMapping("/plan")
    public ResponseEntity<?> createTravelPlanDestination(@RequestBody List<TravelPlanDestination> list) throws SQLException{
        if(travelPlanDestinationService.createTravelPlanDestination(list)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(500).build();
    }

    @GetMapping("/plan/{travelPlanId}")
    public ResponseEntity<?> selectAllTravelPlanDestination(@PathVariable int travelPlanId) throws SQLException{
        travelPlanService.increaseHit(travelPlanId);
        return ResponseEntity.ok(travelPlanDestinationService.selectAll(travelPlanId));
    }

    @DeleteMapping("/plan/{travelPlanId}")
    public ResponseEntity<?> deleteByTravelPlanId(@PathVariable int travelPlanId) throws SQLException{
        if(travelPlanDestinationService.deleteByTravelPlanId(travelPlanId)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(500).build();
    }



}
