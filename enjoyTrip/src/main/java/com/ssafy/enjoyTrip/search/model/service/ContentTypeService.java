package com.ssafy.enjoyTrip.search.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.enjoyTrip.search.dto.ContentType;

public interface ContentTypeService {

	/**
	 * content_type_id 목록조회
	 */
	List<ContentType> selectAll() throws SQLException;
	
	/**
	 * id에 따라 name 값 가져오기
	 */
	ContentType getNamebyContentId(int contentId) throws SQLException;
}
