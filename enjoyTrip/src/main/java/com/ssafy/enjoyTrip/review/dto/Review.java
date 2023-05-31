package com.ssafy.enjoyTrip.review.dto;

import lombok.Data;

@Data
public class Review {
	private int id;
	private int accountUid;
	private String content;
	private float point;
	private String createTime;
	private int contentId;
	private String accountId;
}
