package com.ssafy.enjoyTrip.search.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoyTrip.search.dto.ContentType;
import com.ssafy.enjoyTrip.search.model.mapper.ContentTypeMapper;

@Service
public class ContentTypeServiceImpl implements ContentTypeService {
	
	@Autowired
	private ContentTypeMapper contentTypeMapper;

	@Override
	public List<ContentType> selectAll() throws SQLException {
		return contentTypeMapper.selectAll();
	}

	@Override
	public ContentType getNamebyContentId(int contentId) throws SQLException {
		return contentTypeMapper.getNamebyContentId(contentId);
	}
	
	

}
