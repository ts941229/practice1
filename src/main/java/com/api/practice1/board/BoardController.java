package com.api.practice1.board;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.api.practice1.global.Util;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

	@Autowired
	private final BoardService boardService;
	
	// 게시글 리스트 페이지
	@GetMapping("/board-list-form")
	public String boardListForm(Model model) {
		
		model.addAttribute("boardList", boardService.findAll());
		
		return "/board/boardList";
	}
	
	// 글쓰기 페이지
	@GetMapping("/board-write-form")
	public String boardWriteForm() {
		
		return "/board/boardWrite";
	}
	
	@PostMapping("/board-write")
	public String boardWrite(@ModelAttribute BoardDTO boardDTO) {
		
		Board board = Board.builder()
										.board_title(boardDTO.getBoard_title())
										.board_writer(boardDTO.getBoard_writer())
										.board_content(boardDTO.getBoard_content())
										.board_category(boardDTO.getBoard_category())
										.board_date(Util.getInstance().dateFormat(new Date()))
										.board_hit(boardDTO.getBoard_hit())
										.board_like(boardDTO.getBoard_like())
										.build();
		
		boardService.save(board);
		
		return "redirect:/board/board-list-form";
	}
	
}
