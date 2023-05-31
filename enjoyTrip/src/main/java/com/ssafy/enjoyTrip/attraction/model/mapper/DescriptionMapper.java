package com.ssafy.enjoyTrip.attraction.model.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.enjoyTrip.attraction.dto.Description;
import org.apache.ibatis.javassist.runtime.Desc;

public interface DescriptionMapper {

	/**
	 * 전체 목록 조회
	 */
	List<Description> selectAll() throws SQLException;
	
	/**
	 * 상세 조회
	 */
	Description selectById(int content_id) throws SQLException;

	/**
	 * Description 데이터 추가
	 */
	void addDescription(Description description) throws SQLException;
}
