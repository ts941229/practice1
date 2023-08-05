package com.api.practice1.board;

import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	// 글상세페이지
	@GetMapping("/board-detail-form/{board_id}")
	public String boardDetailForm(@PathVariable("board_id") Long board_id, Model model){
		
		Board board = boardService.findById(board_id).get();
		model.addAttribute("board", board);
		
		return "/board/boardDetail";
	}
	
	@GetMapping("/board-edit-form/{board_id}")
	public String boardEditForm(@PathVariable("board_id") Long board_id, Model model) {
		
		Board board = boardService.findById(board_id).get();
		model.addAttribute("board", board);
		
		return "/board/boardEdit";
	}
	
	@PutMapping("/board-edit/{board_id}")
	public String boardEdit(@PathVariable("board_id") Long board_id, @ModelAttribute BoardDTO boardDTO) {
		
		Board pre_board = boardService.findById(board_id).get();
		
		Board board = Board.builder()
										.board_id(board_id)
										.board_category(boardDTO.getBoard_category())
										.board_title(boardDTO.getBoard_title())
										.board_writer(pre_board.getBoard_writer())
										.board_content(boardDTO.getBoard_content())
										.board_updated_date(Util.getInstance().dateFormat(new Date()))
										.board_date(pre_board.getBoard_date())
										.board_like(pre_board.getBoard_like())
										.board_hit(pre_board.getBoard_hit())
										.build();
		boardService.save(board);
		
		return "redirect:/board/board-detail-form/"+board_id;
	}
	
	@DeleteMapping("/board-delete/{board_id}")
	public String boardDelete(@PathVariable("board_id") Long board_id) {
		
		boardService.deleteById(board_id);
		
		return "redirect:/board/board-list-form";
	}
	
}
