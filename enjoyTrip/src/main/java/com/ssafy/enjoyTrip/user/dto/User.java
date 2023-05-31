package com.ssafy.enjoyTrip.user.dto;

import lombok.Data;

@Data
public class User {
	private String id;
	private String password;
	private String name;
	private String email;
	private String gender;
	private int accessId;
}
