package com.ssafy.enjoyTrip.board.dto;

import lombok.Data;

@Data
public class BoardParameter {

	private int currentPage;			//현재 페이지 번호
	private int perPage;		// 페이지당 글갯수
	private int start;		// 페이지의 시작 글번호
	private String order;		// 정렬 조건
	private boolean sort;	//정렬 순서 true면 정순(asc), false면 역순(desc), 가장 최신에 작성된거 부터 보려면 역순이어야 함
	private String key;		//검색 조건(제목 검색, 작성자 검색)
	private String word;	// 검색어
	private String type;	//1:공지사항, 2:자유게시판

	public void setCurrentPage(int currentPage) {
		currentPage = currentPage == 0 ? 1 : currentPage;
		this.currentPage = currentPage;
	}

}

