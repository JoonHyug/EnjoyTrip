package com.ssafy.enjoyTrip.review.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.enjoyTrip.review.dto.ReviewParameter;
import com.ssafy.enjoyTrip.user.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoyTrip.review.dto.Review;
import com.ssafy.enjoyTrip.review.model.mapper.ReviewMapper;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewMapper reviewMapper;

	@Autowired
	private UserService userService;

	@Override
	public List<Review> selectAllByContentId(int contentId) throws SQLException {
		return reviewMapper.selectAllByContentId(contentId);
	}

	@Override
	public List<Review> selectAllByAccountUid(ReviewParameter reviewParameter) throws SQLException {
		int start = reviewParameter.getCurrentPage() == 0 ? 0 : (reviewParameter.getCurrentPage() - 1) * reviewParameter.getPerPage();
		reviewParameter.setStart(start);
		return reviewMapper.selectAllByAccountUid(reviewParameter);
	}

	@Override
	public void registReview(Review review) throws SQLException {
		reviewMapper.registReview(review);
	}

	@Override
	public void updateReview(Review review) throws SQLException {
		reviewMapper.updateReview(review);
	}

	@Override
	public void deleteReviewById(int id) throws SQLException {
		reviewMapper.deleteReviewById(id);
	}

	@Override
	public List<Review> recent3Review(int accountUid) throws SQLException {
		return reviewMapper.recent3Review(accountUid);
	}

	@Override
	public int getTotalItemsByAccountId(ReviewParameter reviewParameter) throws SQLException {
		return reviewMapper.getTotalItemsByAccountId(reviewParameter);
	}

	@Override
	public int getTotalReview(int contentId) throws SQLException {
		return reviewMapper.getTotalReview(contentId);
	}

	@Override
	public Map<String, List<String>> getReviewedId(int contentId) throws SQLException {
		Map<String, List<String>> map = new HashMap<>();
		List<String> list = new ArrayList<>();
		List<Integer> writer = reviewMapper.getReviewedId(contentId);
		for(int uid : writer) {
			list.add(userService.getUserIdToUid(uid));
		}
		map.put("id", list);
		return map;
	}

	@Override
	public float getAvgPoint(int contentId) throws SQLException {
		return reviewMapper.getAvgPoint(contentId);
	}

}
