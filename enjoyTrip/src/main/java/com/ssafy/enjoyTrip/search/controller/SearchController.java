package com.ssafy.enjoyTrip.search.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoyTrip.search.dto.ContentType;
import com.ssafy.enjoyTrip.search.dto.Gugun;
import com.ssafy.enjoyTrip.search.dto.Sido;
import com.ssafy.enjoyTrip.search.model.service.ContentTypeService;
import com.ssafy.enjoyTrip.search.model.service.GugunService;
import com.ssafy.enjoyTrip.search.model.service.SidoService;

@RestController
@RequestMapping("/search")
@CrossOrigin("*")
public class SearchController {

	@Autowired
	private ContentTypeService contentTypeService;
	
	@Autowired
	private GugunService gugunService;
	
	@Autowired
	private SidoService sidoService;
	
	/**
	 * contentType 조회 
	 */
	@GetMapping("/content")
	public ResponseEntity<?> contentType() throws SQLException {
		List<ContentType> contentTypeList = contentTypeService.selectAll();
		return ResponseEntity.ok(contentTypeList);
	}
	
	/**
	 * 구군 조회
	 */
	@GetMapping("/gugun/{sidoCode}")
	public ResponseEntity<?> gugun(@PathVariable int sidoCode) throws SQLException {
		List<Gugun> gugunList = gugunService.selectBySidoCode(sidoCode);
		return ResponseEntity.ok(gugunList);
	}
	
	/**
	 * 시도 조회
	 */
	@GetMapping("/sido")
	public ResponseEntity<?> sido() throws SQLException {
		List<Sido> sidoList = sidoService.selectAll();
		return ResponseEntity.ok(sidoList);
	}

	@GetMapping("/content/{contentTypeId}")
	public ResponseEntity<?> contentTypeName(@PathVariable int contentTypeId) throws SQLException{
		return ResponseEntity.ok(contentTypeService.getNamebyContentId(contentTypeId));
	}
	
}
