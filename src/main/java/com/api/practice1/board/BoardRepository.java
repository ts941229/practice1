package com.api.practice1.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<Board, Long>{
	
	@Query("select b from Board b where b.board_title like concat('%', :keyword, '%')")
	Page<Board> findAllByTitleContaining(@Param("keyword") String keyword, Pageable pageable);
	
	@Query("select b from Board b where b.board_title like concat('%', :keyword, '%') or b.board_content like concat('%', :keyword, '%')")
	Page<Board> findAllByTitleAndContentContaining(@Param("keyword") String keyword, Pageable pageable);
	
	@Query("select b from Board b where b.board_content like concat('%', :keyword, '%')")
	Page<Board> findAllByContentContaining(@Param("keyword") String keyword, Pageable pageable);
	
	@Query("select b from Board b where b.board_writer like concat('%', :keyword, '%')")
	Page<Board> findAllByWriterContaining(@Param("keyword") String keyword, Pageable pageable);
	
	
}
