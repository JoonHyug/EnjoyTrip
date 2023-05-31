package com.ssafy.enjoyTrip.search.model.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.enjoyTrip.search.dto.Gugun;

public interface GugunMapper {

	/**
	 * 구군 리스트 조회
	 */
	List<Gugun> selectAll() throws SQLException;
	
	/**
	 * 시도 코드로 상세 조회
	 */
	List<Gugun> selectBySidoCode(int sidoCode) throws SQLException;
}
