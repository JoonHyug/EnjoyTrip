package com.ssafy.enjoyTrip.review.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.enjoyTrip.review.dto.Review;
import com.ssafy.enjoyTrip.review.dto.ReviewParameter;
import org.apache.ibatis.jdbc.SQL;

public interface ReviewMapper {
	
	/**
	 * contentId별로 리뷰 조회 
	 */
	List<Review> selectAllByContentId(int contentId) throws SQLException;
	
	/**
	 * account_uid별로 리뷰 조회
	 */
	List<Review> selectAllByAccountUid(ReviewParameter reviewParameter) throws SQLException;
	
	/**
	 * 리뷰 등록
	 */
	void registReview(Review review) throws SQLException;
	
	/**
	 * 리뷰 수정
	 */
	void updateReview(Review review) throws SQLException;
	
	/**
	 * 리뷰 삭제
	 */
	void deleteReviewById(int id) throws SQLException;

	/**
	 * 사용자가 작성한 가장 최근3개의 리뷰를 반환
	 */
	public List<Review> recent3Review(int accountUid) throws SQLException;

	/**
	 * 사용자가 작성한 리뷰 전체 길이 반환
	 */
	public int getTotalItemsByAccountId(ReviewParameter reviewParameter) throws SQLException;

	/**
	 * contentId에 등록된 전체 리뷰 수
	 */
	public int getTotalReview(int contentId) throws SQLException;

	/**
	 * contentId에 리뷰를 작성한 accountId를 반환
	 */
	public List<Integer> getReviewedId(int contentId) throws SQLException;

	/**
	 * contentId의 작성된 전체 리뷰의 평균을 반환
	 */
	public float getAvgPoint(int contentId) throws SQLException;
}
