package com.ssafy.enjoyTrip.picture.dto;

import lombok.Data;

@Data
public class Picture {
    private int id;
    private String route;
    private String type;
    private String accountId;
    private String picName;
    private String fullRoute;
    private String createTime;
}
