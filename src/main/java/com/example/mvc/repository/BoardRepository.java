package com.example.mvc.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.mvc.domain.Board;

/**
 * Repository
 * @author SON
 *
 */
@Repository
public interface BoardRepository {

	List<Board> getList();
	
	Board get(int boardSeq);
	
	void save(Board board);
	
	void update(Board board);
	
	void delete(int boardSeq);
}
