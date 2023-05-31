package com.ssafy.enjoyTrip.search.model.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.enjoyTrip.search.dto.Sido;

public interface SidoMapper {

	/**
	 * 시도 리스트 조회
	 */
	List<Sido> selectAll() throws SQLException;
}
