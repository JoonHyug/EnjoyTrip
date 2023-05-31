package com.ssafy.enjoyTrip.attraction.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.enjoyTrip.attraction.dto.Info;

public interface InfoMapper {

	/**
	 * 시도명으로 찾기
	 */
	List<Info> SearchBySidoGun(Map<String, Object> map) throws SQLException;
	
	/**
	 * 시도명으로 찾기 + 키워드
	 */
	List<Info> SearchBySidoGunKeyword(Map<String, Object> map) throws SQLException;
	
	/**
	 * 콘텐트id로 찾기
	 */
	Info selectByContentId(int content_id) throws SQLException;

	/**
	 * 디테일 페이지에 방문할 때 마다 조회수(readcount) 증가
	 */
	void increaseReadCount(int contentId) throws SQLException;

	/**
	 * Info 데이터 추가
	 */
	void addInfo(Info info) throws SQLException;

	/**
	 * contentId가 존재하는지 아닌지 체크
	 */
	int isContentIdExist(int contentId) throws SQLException;

	/**
	 * contentId를 통해 간단한 정보 조회
	 */
	public Info getInfo(int contentId) throws SQLException;

	/**
	 * 조회수가 가장 높은 3개만 조회
	 */
	public List<Info> getHitTop3() throws SQLException;
}
