package com.ssafy.enjoyTrip.attraction.dto;

import lombok.Data;

@Data
public class Info {
	private int contentId;
	private int contentTypeId;
	private String contentTypeName;
	private String title;
	private String addr1;
	private String addr2;
	private String zipcode;
	private String tel;
	private String firstImage;
	private String firstImage2;
	private int readcount;
	private int sidoCode;
	private int gugunCode;
	private Double latitude;
	private Double longitude;
	private String mlevel;
}
