package com.api.practice1.board;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	
	@Autowired
	private final BoardRepository boardRepository;
	
	public List<Board> findAll(){
		return boardRepository.findAll();
	}
	
	public void save(Board board) {
		boardRepository.save(board);
	}
	
	public Optional<Board> findById(Long board_id) {
		return boardRepository.findById(board_id);
	}
	
}
