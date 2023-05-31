package com.ssafy.enjoyTrip.user.controller;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.ssafy.enjoyTrip.user.token.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoyTrip.user.dto.User;
import com.ssafy.enjoyTrip.user.model.service.UserService;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private JwtUtil jwtUtil;
	
	/**
	 * 로그인
	 */
	@PostMapping()
	public ResponseEntity<?> login(@RequestBody User user) throws SQLException, UnsupportedEncodingException {
		User userInfo = userService.login(user);
		if(userInfo != null){
//			60초 * 60분
			String token = jwtUtil.createToken(userInfo, 60*60);

			Map<String, String> result = new HashMap<>();
			result.put("token", token);

			return new ResponseEntity<Map<String, String>>(result, HttpStatus.OK);
		} else {

		return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}
	
	/**
	 * 회원등록
	 */
	@PostMapping("/regist")
	public ResponseEntity<?> regist(@RequestBody User user) throws SQLException {
		userService.regist(user);
		return ResponseEntity.ok().build();
	}

	/**
	 * 회원 정보 삭제
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable String id) throws SQLException {
		userService.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	/**
	 * 회원정보 수정
	 */
	@PutMapping("/info/{id}")
	public ResponseEntity<?> updateUserInfo(@RequestBody Map map) throws SQLException {
		userService.updateByCode(map);
		return ResponseEntity.ok().build();
	}
	
	/**
	 * 회원 비밀번호 수정
	 */
	@PutMapping("pwd/{id}")
	public ResponseEntity<?> updateUserPassword(@RequestBody Map map) throws SQLException {
		userService.updatepwdByCode(map);
		return ResponseEntity.ok().build();
	}

	/**
	 * 패스워드 가져오기
	 */
	@PostMapping("/getpwd")
	public ResponseEntity<?> getpwd(@RequestBody Map<String, String> map) throws SQLException {
		String pwd = userService.getPasswordById(map.get("id"));
		System.out.println(map);
		return ResponseEntity.ok(pwd);
	}

	/**
	 * 회원 등록할 때 아이디 중복체크
	 */

	/**
	 * 회원 등록할 때 이메일 중복체크
	 */
}
