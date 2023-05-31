package com.ssafy.enjoyTrip.user.model.service;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.enjoyTrip.user.dto.User;
import com.ssafy.enjoyTrip.user.model.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User login(User loginInfo) throws SQLException {
		return userMapper.login(loginInfo);
	}

	@Override
	@Transactional
	public void regist(User user) throws SQLException {
		userMapper.regist(user);
	}

	@Override
	@Transactional
	public void deleteById(String id) throws SQLException {
		userMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void updateByCode(Map<String, Object> map) throws SQLException {
		userMapper.updateByCode(map);
	}

	@Override
	@Transactional
	// map id : id , newpassword : newpassword
	public void updatepwdByCode(Map<String, Object> map) throws SQLException {
		userMapper.updatepwdByCode(map);
	}

	@Override
	public String passwordCheck(String id) throws SQLException {
		return userMapper.passwordCheck(id);
	}

	@Override
	public String getUserIdToUid(int uid) throws SQLException {
		return userMapper.getUserIdToUid(uid);
	}

	@Override
	public int getUserUidToId(String id) throws SQLException {
		return userMapper.getUserUidToId(id);
	}

	@Override
	public String getPasswordById(String id) throws SQLException {
		return userMapper.getPasswordById(id);
	}

}
