package com.ssafy.enjoyTrip.attraction.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.enjoyTrip.attraction.dto.Description;

public interface DescriptionService {

	/**
	 * 전체 목록 조회
	 */
	List<Description> selectAll() throws SQLException;
	
	/**
	 * 상세 조회
	 */
	Description selectById(int contentId) throws SQLException;
	/**
	 * Description 데이터 추가
	 */
	void addDescription(Description description) throws SQLException;
}
