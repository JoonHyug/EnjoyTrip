package com.ssafy.enjoyTrip.board.model.service;

import com.ssafy.enjoyTrip.board.dto.Board;
import com.ssafy.enjoyTrip.board.dto.BoardParameter;
import com.ssafy.enjoyTrip.util.PageNavigation;

import java.sql.SQLException;
import java.util.List;


public interface BoardService {
	public boolean writeArticle(Board board) throws Exception;
	public List<Board> listArticle(BoardParameter boardParameter) throws Exception;
	public int getTotalCount(BoardParameter boardParameter) throws SQLException;
	public Board getArticle(int articleno) throws Exception;
	public void updateHit(int articleno) throws Exception;
	public boolean modifyArticle(Board board) throws Exception;
	public boolean deleteArticle(int articleno) throws Exception;
	public List<Board> selectAll() throws SQLException;
}
