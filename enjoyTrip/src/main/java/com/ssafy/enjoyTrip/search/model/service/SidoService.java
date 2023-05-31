package com.ssafy.enjoyTrip.search.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.enjoyTrip.search.dto.Sido;

public interface SidoService {

	/**
	 * 시도 리스트 조회
	 */
	List<Sido> selectAll() throws SQLException;
}
