package com.ssafy.enjoyTrip.board.dto;

import lombok.Data;

@Data
public class Board {
	private int articleno;		// 글 번호
	private String accountId;		// 사용자 번호
	private String subject;		// 제목
	private String content;		// 내용
	private int hit;			// 조회수
	private String regtime;		// 작성 시간
	private int type; 			// 1:공지사항, 2:자유게시판
}