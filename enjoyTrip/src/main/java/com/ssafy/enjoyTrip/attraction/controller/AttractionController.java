package com.ssafy.enjoyTrip.attraction.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoyTrip.attraction.dto.Description;
import com.ssafy.enjoyTrip.attraction.dto.Info;
import com.ssafy.enjoyTrip.attraction.model.service.DescriptionService;
import com.ssafy.enjoyTrip.attraction.model.service.InfoService;
import sun.security.krb5.internal.crypto.Des;

@RestController
@RequestMapping("/attraction")
@CrossOrigin("*")
public class AttractionController {

	@Autowired
	private DescriptionService descriptionService;

	@Autowired
	private InfoService infoService;

	/**
	 * 관광지 상세정보 페이지
	 */
	@GetMapping("/{contentId}")
	public ResponseEntity<?> detailAttraction(@PathVariable int contentId) throws SQLException{
		Info info = infoService.selectByContentId(contentId);
		if(info == null){
			return ResponseEntity.status(500).build();
		}
		Description description = descriptionService.selectById(contentId);
		infoService.increaseReadCount(contentId);

		Map<String, Object> map = new HashMap<>();
		map.put("info", info);
		map.put("description", description);

		return ResponseEntity.ok(map);
	}

	/**
	 * 왼쪽탭 서치바 찾기
	 * keyword 없음
	 */
	@PostMapping
	public ResponseEntity<?> search(@RequestBody Map<String, Object> map) throws SQLException {
		List<Info> infoList;
		System.out.println(map);
		if(map.containsKey("keyword")) {
			infoList = infoService.SearchBySidoGunKeyword(map);
		} else {
			infoList = infoService.SearchBySidoGun(map);
		}
		return ResponseEntity.ok(infoList);
	}

	/**
	 * DB에 없는 데이터 DB에 삽입 요청
	 */
	@PostMapping("/add")
	public ResponseEntity<?> registDetail(@RequestBody Map<String, Object> map) throws SQLException{
		Description des = new Description();
		des.setContentId((int)map.get("contentId"));
		des.setOverview((String)map.get("overview"));
		descriptionService.addDescription(des);
		Info info = new Info();
		info.setContentId((int)map.get("contentId"));
		info.setContentTypeId((int)map.get("contentTypeId"));
		info.setAddr1((String)map.get("addr1"));
		info.setAddr2((String)map.get("addr2"));
		info.setFirstImage((String)map.get("firstImage"));
		info.setFirstImage2((String)map.get("firstImage2"));
		info.setSidoCode((int)map.get("sidoCode"));
		info.setGugunCode((int)map.get("gugunCode"));
		info.setLatitude((double)map.get("latitude"));
		info.setLongitude((double)map.get("longitude"));
		infoService.addInfo(info);
		System.out.println(map);

		return ResponseEntity.ok().build();
	}

	@GetMapping("/check/{contentId}")
	public ResponseEntity<?> isContentIdExist(@PathVariable int contentId) throws SQLException{
		return ResponseEntity.ok(infoService.isContentIdExist(contentId));
	}

	@GetMapping("/planinfo/{contentId}")
	public ResponseEntity<?> getInfo(@PathVariable int contentId) throws SQLException{
		return ResponseEntity.ok(infoService.getInfo(contentId));
	}

	@GetMapping("/hot")
	public ResponseEntity<?> getHitTop3() throws SQLException{
		return ResponseEntity.ok(infoService.getHitTop3());
	}

}
