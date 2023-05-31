package com.ssafy.enjoyTrip.attraction.dto;

import lombok.Data;

@Data
public class RegistrationLog {
    private int id;
    private int accountUid;
    private int contentId;
    private boolean check;
}
