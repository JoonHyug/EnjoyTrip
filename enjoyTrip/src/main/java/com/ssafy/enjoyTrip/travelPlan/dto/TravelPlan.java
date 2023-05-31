package com.ssafy.enjoyTrip.travelPlan.dto;

import lombok.Data;

@Data
public class TravelPlan {
    private int id;
    private String accountId;
    private String title;
    private String createTime;
    private int hit;
}
