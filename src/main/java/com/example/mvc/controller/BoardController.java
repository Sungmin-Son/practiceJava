package com.example.mvc.controller;

import java.util.List;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.configuration.http.BaseResponse;
import com.example.mvc.domain.Board;
import com.example.mvc.parameter.BoardParameter;
import com.example.mvc.service.BoardService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * 게시판 컨트롤러
 * @author SON
 *
 */
@Tag(name = "게시판")
@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = User.class))),
		@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = Error.class)))
})
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	
	/**
	 * 목록 리턴
	 * @return
	 */
	@GetMapping
	@Operation(summary = "게시판 목록", description = "게시판 리스트가 보여집니다.")
	@Parameter
	public BaseResponse<List<Board>> getList(){
		return new BaseResponse<List<Board>>(boardService.getList());
	}
	
	
	/**
	 * 상제 정보 리턴
	 * @param boardSeq
	 * @return
	 */
	@GetMapping("/{boardSeq}")
	@Operation(summary = "게시판 상제 정보", description = "게시판 상세정보가 보여집니다.")
	@Parameter(name = "boardSeq", description = "글 번호", example = "1")
	public BaseResponse<Board> get(@PathVariable int boardSeq) {
		return new BaseResponse<Board>(boardService.get(boardSeq));
	}
	
	/**
	 * 등록/수정 처리
	 * @param board
	 */
	@PutMapping("/save")
	@Operation(summary = "등록 및 수정", description = "신규게시물 등록 및 수정 처리를 해줍니다.")
	@Parameter(name = "boardSeq", description = "글 번호", example = "1")
	@Parameter(name = "title", description = "제목", example = "spring")
	@Parameter(name = "contents", description = "내용", example = "spring 강좌")
	public BaseResponse<Integer> save(BoardParameter parameter) {
		boardService.save(parameter);
		return new BaseResponse<Integer>(parameter.getBoardSeq());
	}
	
	/**
	 * 삭제 처리
	 * @param boardSeq
	 */
	@DeleteMapping("/delete/{boardSeq}")
	@Operation(summary = "삭제", description = "글 번호에 맞는 게시판 삭제해줍니다.")
	@Parameter(name = "boardSeq", description = "글 번호", example = "1")
	public BaseResponse<Boolean> delete(@PathVariable int boardSeq) {
		Board board = boardService.get(boardSeq);
		if(board == null) {
			return new BaseResponse<Boolean>(false);
		}
		boardService.delete(boardSeq);
		return new BaseResponse<Boolean>(true);
	}
}
