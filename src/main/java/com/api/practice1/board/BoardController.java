package com.api.practice1.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

	@Autowired
	private final BoardService boardService;
	
	@GetMapping("/board-list")
	public String boardListPage(Model model) {
		
		model.addAttribute("boardList", boardService.findAll());
		
		return "/board/boardList";
	}
	
	@PostMapping("/board-write")
	public String boardWrite(@ModelAttribute BoardDTO boardDTO) {
		
		
		
		return "";
	}
	
}
