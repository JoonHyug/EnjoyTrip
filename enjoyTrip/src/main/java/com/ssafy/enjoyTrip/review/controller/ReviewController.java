package com.ssafy.enjoyTrip.review.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.enjoyTrip.attraction.dto.Info;
import com.ssafy.enjoyTrip.attraction.model.service.InfoService;
import com.ssafy.enjoyTrip.bookmark.dto.Bookmark;
import com.ssafy.enjoyTrip.review.dto.ReviewParameter;
import com.ssafy.enjoyTrip.user.model.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoyTrip.review.dto.Review;
import com.ssafy.enjoyTrip.review.model.service.ReviewService;

import javax.swing.text.html.ListView;

@RestController
@RequestMapping("/review")
@CrossOrigin("*")
@Slf4j
public class ReviewController {

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private UserService userService;

	@Autowired
	private InfoService infoService;

	@GetMapping("/content/{contentId}")
	public ResponseEntity<?> getReviewByContentId(@PathVariable int contentId) throws SQLException {
		List<Review> reviewList = reviewService.selectAllByContentId(contentId);
		for(Review review : reviewList){
			review.setAccountId(userService.getUserIdToUid(review.getAccountUid()));
		}
		if(reviewList.size() == 0){
			return ResponseEntity.status(500).build();
		}
		return ResponseEntity.ok(reviewList);
	}

	@GetMapping("/user/{accountId}")
	public ResponseEntity<?> getReviewByAccountId(@PathVariable String accountId, ReviewParameter reviewParameter) throws SQLException {
		reviewParameter.setAccountUid(userService.getUserUidToId(accountId));
		List<Review> reviewList = reviewService.selectAllByAccountUid(reviewParameter);
		List<Map<String, Object>> data = new ArrayList<>();
		for(Review review : reviewList) {
			Info info = infoService.selectByContentId(review.getContentId());
			Map<String, Object> temp = new HashMap<>();
			temp.put("contentId", review.getContentId());
			temp.put("id", review.getId());
			temp.put("point", review.getPoint());
			temp.put("content", review.getContent());
			temp.put("createTime", review.getCreateTime());
			temp.put("title", info.getTitle());
			data.add(temp);
		}
		return ResponseEntity.ok(data);
	}

	@GetMapping("/user/totalItems/{accountId}")
	public ResponseEntity<?> getTotalItemsByAccountId(@PathVariable String accountId, ReviewParameter reviewParameter) throws SQLException {
		reviewParameter.setAccountUid(userService.getUserUidToId(accountId));
		return ResponseEntity.ok(reviewService.getTotalItemsByAccountId(reviewParameter));
	}

	@PostMapping
	public ResponseEntity<?> regist(@RequestBody Review review) throws SQLException {
		review.setAccountUid(userService.getUserUidToId(review.getAccountId()));
		System.out.println(review);
		reviewService.registReview(review);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateReview(@PathVariable int id, @RequestBody Review review) throws SQLException {
		review.setId(id);
		reviewService.updateReview(review);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable int id) throws SQLException {
		reviewService.deleteReviewById(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/recent/{accountId}")
	public ResponseEntity<?> recentReview(@PathVariable String accountId) throws SQLException{
		List<Review> reviews = reviewService.recent3Review(userService.getUserUidToId(accountId));
		List<Map<String, Object>> data = new ArrayList<>();
		for(Review review : reviews){
			Info info = infoService.selectByContentId(review.getContentId());
			Map<String, Object> temp = new HashMap<>();
			temp.put("contentId", review.getContentId());
			temp.put("id", review.getId());
			temp.put("point", review.getPoint());

			String content = "";
			if(review.getContent().length()<=40) {
				content = review.getContent();
			}
			else {
				content = review.getContent().substring(0, 37)+"...";
			}

			temp.put("content", content);
			temp.put("createTime", review.getCreateTime()) ;

			temp.put("title", info.getTitle());
			data.add(temp);
		}

		return ResponseEntity.ok(data);
	}

	@GetMapping("/totalreview/{contentId}")
	public ResponseEntity<?> getTotalReview(@PathVariable int contentId) throws SQLException{

		return ResponseEntity.ok(reviewService.getTotalReview(contentId));
	}

	@GetMapping("/reviewedid/{contentId}")
	public ResponseEntity<?> getReviewedId(@PathVariable int contentId)throws SQLException{
		return ResponseEntity.ok(reviewService.getReviewedId(contentId));
	}

	@GetMapping("/avgpoint/{contentId}")
	public ResponseEntity<?> getAvgPoint(@PathVariable int contentId)throws SQLException{
		return ResponseEntity.ok(reviewService.getAvgPoint(contentId));
	}
}
