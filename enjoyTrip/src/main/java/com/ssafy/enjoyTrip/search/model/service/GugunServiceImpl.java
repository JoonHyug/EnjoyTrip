package com.ssafy.enjoyTrip.search.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoyTrip.search.dto.Gugun;
import com.ssafy.enjoyTrip.search.model.mapper.GugunMapper;

@Service
public class GugunServiceImpl implements GugunService {

	@Autowired
	private GugunMapper gugunMapper;
	
	@Override
	public List<Gugun> selectAll() throws SQLException {
		return gugunMapper.selectAll();
	}

	@Override
	public List<Gugun> selectBySidoCode(int sidoCode) throws SQLException {
		return gugunMapper.selectBySidoCode(sidoCode);
	}

}
