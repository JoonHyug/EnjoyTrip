package com.ssafy.enjoyTrip.user.model.service;

import java.sql.SQLException;
import java.util.Map;

import com.ssafy.enjoyTrip.user.dto.User;

public interface UserService {

	/**
	 * 로그인
	 */
	User login(User loginInfo) throws SQLException;

	/**
	 * 회원 등록
	 */
	void regist(User user) throws SQLException;

	/**
	 * 회원 삭제
	 */
	void deleteById(String id) throws SQLException;

	/**
	 * 회원 정보 수정
	 */
	void updateByCode(Map<String, Object> map) throws SQLException;

	/**
	 * 비밀번호 수정
	 */
	void updatepwdByCode(Map<String,Object> map) throws SQLException;
	
	/**
	 * 비밀번호 수정시 현재 비밀번호와 , 입력한 비밀번호가 같은지 확인 하는 작업
	 */
	String passwordCheck(String id) throws SQLException;

	/**
	 * 사용자 아이디를 토대로 uid를 받아오기
	 */
	 int getUserUidToId(String id) throws SQLException;

	/**
	 * 사용자 uid를 토대로 id 받아오기
	 */
	String getUserIdToUid(int uid) throws SQLException;

	/**
	 * password 반환
	 */
	String getPasswordById(String id) throws SQLException;
}
