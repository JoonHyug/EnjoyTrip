package com.ssafy.enjoyTrip.board.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ssafy.enjoyTrip.board.dto.Board;
import com.ssafy.enjoyTrip.board.dto.BoardParameter;
import com.ssafy.enjoyTrip.board.model.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/board")
@CrossOrigin("*")
@Slf4j
public class BoardController {

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private BoardService boardService;

//	게시판 글작성 : 새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.
@PostMapping
public ResponseEntity<String> writeArticle(@RequestBody Board board) throws Exception {
	if (boardService.writeArticle(board)) {
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
}

	//	게시판 글목록 : 모든 게시글의 정보를 반환한다.
	@GetMapping
	public ResponseEntity<?> listArticle(BoardParameter boardParameter) throws Exception {
		List<Board> boardList = boardService.listArticle(boardParameter);
		return ResponseEntity.ok(boardList);
	}

	//boardParameter로 검색 할 전체 갯수를 반환
	@GetMapping("/totalitems")
	public ResponseEntity<?> totalItems(BoardParameter boardParameter) throws SQLException{
		int totalItems = boardService.getTotalCount(boardParameter);
		return ResponseEntity.ok(totalItems);
	}

	//	게시판 글보기 : 글번호에 해당하는 게시글의 정보를 반환한다.
	@GetMapping("/{articleno}")
	public ResponseEntity<Board> getArticle(@PathVariable("articleno") int articleno) throws Exception {
		boardService.updateHit(articleno);
		return new ResponseEntity<Board>(boardService.getArticle(articleno), HttpStatus.OK);
	}

	//	게시판 글수정 : 수정할 게시글 정보를 입력한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.
	@PutMapping
	public ResponseEntity<String> modifyArticle(@RequestBody Board board) throws Exception {

		if (boardService.modifyArticle(board)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}

	//	게시판 글삭제 : 글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.
	@DeleteMapping("/{articleno}")
	public ResponseEntity<String> deleteArticle(@PathVariable("articleno") int articleno) throws Exception {
		if (boardService.deleteArticle(articleno)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@GetMapping("/all")
	public ResponseEntity<?> selectAll() throws SQLException{
		List<Board> boardList = boardService.selectAll();
		return ResponseEntity.ok(boardList);
	}
}