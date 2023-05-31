package com.ssafy.enjoyTrip.search.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoyTrip.search.dto.Sido;
import com.ssafy.enjoyTrip.search.model.mapper.SidoMapper;

@Service
public class SidoServiceImpl implements SidoService {

	@Autowired
	private SidoMapper sidoMapper;
	
	@Override
	public List<Sido> selectAll() throws SQLException {
		return sidoMapper.selectAll();
	}

}
