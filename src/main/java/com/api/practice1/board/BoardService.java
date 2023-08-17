package com.api.practice1.board;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	
	@Autowired
	private final BoardRepository boardRepository;
	
	public Page<Board> findAll(Pageable pageable){
		return boardRepository.findAll(pageable);
	}
	
	public Page<Board> findAllByTitleContaining(String keyword, Pageable pageable){
		return boardRepository.findAllByTitleContaining(keyword, pageable);
	}
	
	public Page<Board> findAllByTitleAndContentContaining(String keyword, Pageable pageable){
		return boardRepository.findAllByTitleAndContentContaining(keyword, pageable);
	}
	
	public Page<Board> findAllByContentContaining(String keyword, Pageable pageable){
		return boardRepository.findAllByContentContaining(keyword, pageable);
	}
	
	public Page<Board> findAllByWriterContaining(String keyword, Pageable pageable){
		return boardRepository.findAllByWriterContaining(keyword, pageable);
	}
	
	
	public void save(Board board) {
		boardRepository.save(board);
	}
	
	public Optional<Board> findById(Long board_id) {
		return boardRepository.findById(board_id);
	}
	
	public void deleteById(Long board_id) {
		boardRepository.deleteById(board_id);
	}
	
}
