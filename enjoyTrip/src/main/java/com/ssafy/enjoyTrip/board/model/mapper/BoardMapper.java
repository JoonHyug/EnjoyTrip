package com.ssafy.enjoyTrip.board.model.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.enjoyTrip.board.dto.Board;
import com.ssafy.enjoyTrip.board.dto.BoardParameter;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface BoardMapper {
	
	public int writeArticle(Board board) throws SQLException;
	public List<Board> listArticle(BoardParameter boardParameter) throws SQLException;
	public int getTotalCount(BoardParameter boardParameter) throws SQLException;
	public Board getArticle(int articleno) throws SQLException;
	public void updateHit(int articleno) throws SQLException;
	public int modifyArticle(Board board) throws SQLException;
	public int deleteArticle(int articleno) throws SQLException;
	public List<Board> selectAll() throws SQLException;
	
}