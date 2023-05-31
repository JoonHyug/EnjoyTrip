package com.ssafy.enjoyTrip.board.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.enjoyTrip.board.dto.Board;
import com.ssafy.enjoyTrip.board.dto.BoardParameter;
import com.ssafy.enjoyTrip.board.model.mapper.BoardMapper;
import com.ssafy.enjoyTrip.util.PageNavigation;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private SqlSession sqlSession;

	@Autowired
	private BoardMapper boardMapper;

	@Override
	public boolean writeArticle(Board board) throws Exception {
		if(board.getSubject() == null || board.getContent() == null) {
			throw new Exception();
		}
		return sqlSession.getMapper(BoardMapper.class).writeArticle(board) == 1;
	}

	@Override
	public List<Board> listArticle(BoardParameter boardParameter) throws Exception {
		int start = boardParameter.getCurrentPage() == 0 ? 0 : (boardParameter.getCurrentPage() - 1) * boardParameter.getPerPage();
		boardParameter.setStart(start);
		return sqlSession.getMapper(BoardMapper.class).listArticle(boardParameter);
	}

	@Override
	public int getTotalCount(BoardParameter boardParameter) throws SQLException {
		return boardMapper.getTotalCount(boardParameter);
	}

	@Override
	public Board getArticle(int articleno) throws Exception {
		return sqlSession.getMapper(BoardMapper.class).getArticle(articleno);
	}
	
	@Override
	public void updateHit(int articleno) throws Exception {
		sqlSession.getMapper(BoardMapper.class).updateHit(articleno);
	}

	@Override
	@Transactional
	public boolean modifyArticle(Board board) throws Exception {
		return sqlSession.getMapper(BoardMapper.class).modifyArticle(board) == 1;
	}

	@Override
	@Transactional
	public boolean deleteArticle(int articleno) throws Exception {
		return sqlSession.getMapper(BoardMapper.class).deleteArticle(articleno) == 1;
	}

	@Override
	public List<Board> selectAll() throws SQLException {
		return boardMapper.selectAll();
	}
}