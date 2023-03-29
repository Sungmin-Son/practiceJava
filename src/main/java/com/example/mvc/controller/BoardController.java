package com.example.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mvc.domain.Board;
import com.example.mvc.repository.BoardRepository;
import com.example.mvc.service.BoardService;

/**
 * 게시판 컨트롤러
 * @author SON
 *
 */
@RestController
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	/**
	 * 목록 리턴
	 * @return
	 */
	@GetMapping
	public List<Board> getList(){
		return boardService.getList();
	}
	
	
	/**
	 * 상제 정보 리턴
	 * @param boardSeq
	 * @return
	 */
	@GetMapping("/{boardSeq}")
	public Board get(@PathVariable int boardSeq) {
		return boardService.get(boardSeq);
	}
	
	/**
	 * 등록/수정 처리
	 * @param board
	 */
	@GetMapping("/save")
	public void save(Board board) {
		boardService.save(board);
	}
	
	/**
	 * 삭제 처리
	 * @param boardSeq
	 */
	@GetMapping("/delete/{boardSeq}")
	public void delete(@PathVariable int boardSeq) {
		boardService.delete(boardSeq);
	}
}
