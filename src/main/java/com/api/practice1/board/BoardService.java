package com.api.practice1.board;

import java.util.List;

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
	
}
