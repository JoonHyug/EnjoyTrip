package com.ssafy.enjoyTrip.attraction.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoyTrip.attraction.dto.Description;
import com.ssafy.enjoyTrip.attraction.model.mapper.DescriptionMapper;

@Service
public class DescriptionServiceImpl implements DescriptionService {

	@Autowired
	private DescriptionMapper descriptionMapper;
	
	@Override
	public List<Description> selectAll() throws SQLException {
		return descriptionMapper.selectAll();
	}

	@Override
	public Description selectById(int contentId) throws SQLException {
		return descriptionMapper.selectById(contentId);
	}

	@Override
	public void addDescription(Description description) throws SQLException {
		descriptionMapper.addDescription(description);
	}

}
