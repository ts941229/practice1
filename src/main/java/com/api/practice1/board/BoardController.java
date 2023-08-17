package com.api.practice1.board;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.api.practice1.global.Util;

import lombok.RequiredArgsConstructor;
import net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.BatchAllocator.ForTotal;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

	@Autowired
	private final BoardService boardService;
	
	// 게시글 리스트 페이지
	@GetMapping("/board-list-form")
	public String boardListForm(Model model
											, @PageableDefault(page = 0, size = 5) Pageable pageable
											, @RequestParam(value = "size", required = false) Integer size
											, @RequestParam(value = "page", required = false) Integer page) {
	
		if(page == null) {page = 0;} // default 페이지
		if(size == null) {size = 5;}  // default 사이즈
		
		pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
		
		Page<Board> boardList = boardService.findAll(pageable);
		
		int pageSize = 5; // page 크기 ex) 1~5, 6~10 or 1~10, 11~20
		int nowPage = boardList.getPageable().getPageNumber();
		int startPage = Math.max((nowPage / pageSize) * pageSize + 1, 1);
		int endPage = Math.min(startPage + pageSize - 1, boardList.getTotalPages());
		int prev = startPage - pageSize;
		int next = startPage + pageSize;
		
		// startPage가 endPage보다 높게 나올경우 (잘못된 접근시 발생할 수 있음 ex) n개씩 보기 클릭시)
		if(startPage>=endPage) {startPage=Math.max(Math.min(endPage-pageSize, 1), 1);}
		
		// 데이터가 없는 경우
		if(endPage<=0) {endPage=1;}
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("size", size);
		model.addAttribute("totalPages", boardList.getTotalPages());
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("prev", prev);
		model.addAttribute("next", next);
		
		return "/board/boardList";
	}
	
	// 게시글 검색
	@PostMapping("/board-search")
	@ResponseBody
	public List<Board> boardSearch(Model model
												, @PageableDefault(page = 0, size = 10) Pageable pageable
												, @RequestParam(value = "size", required = false) Integer size
												, @RequestParam(value = "page", required = false) Integer page
												, @RequestParam(value = "searchType") String searchType
												, @RequestParam(value = "searchKeyword") String searchKeyword) {
		
		if(page == null) {page = 0;} // default 페이지
		if(size == null) {size = 5;}  // default 사이즈
		
		pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
		
		System.out.println("searchType : "+searchType);
		System.out.println("searchKeyword : "+searchKeyword);

		Page<Board> boardList = null;
		
		switch(searchType) {
			case "title" : boardList = boardService.findAllByTitleContaining(searchKeyword, pageable); break;
			case "titcont" : break;
			case "content" : break;
			case "writer" : break;
		}
		
		System.out.println("list size : "+boardList.getContent().size());
		
		int pageSize = 5; // page 크기 ex) 1~5, 6~10 or 1~10, 11~20
		int nowPage = boardList.getPageable().getPageNumber();
		int startPage = Math.max((nowPage / pageSize) * pageSize + 1, 1);
		int endPage = Math.min(startPage + pageSize - 1, boardList.getTotalPages());
		int prev = startPage - pageSize;
		int next = startPage + pageSize;
		
		// startPage가 endPage보다 높게 나올경우 (잘못된 접근시 발생할 수 있음 ex) n개씩 보기 클릭시)
		if(startPage>=endPage) {startPage=Math.max(Math.min(endPage-pageSize, 1), 1);}
		
		// 데이터가 없는 경우
		if(endPage<=0) {endPage=1;}
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("size", size);
		model.addAttribute("totalPages", boardList.getTotalPages());
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("prev", prev);
		model.addAttribute("next", next);
		
		return boardList.getContent();
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
	@GetMapping("/board-detail-form/{id}")
	public String boardDetailForm(@PathVariable("id") Long id, Model model){
		
		Board board = boardService.findById(id).get();
		model.addAttribute("board", board);
		
		return "/board/boardDetail";
	}
	
	@GetMapping("/board-edit-form/{id}")
	public String boardEditForm(@PathVariable("id") Long id, Model model) {
		
		Board board = boardService.findById(id).get();
		model.addAttribute("board", board);
		
		return "/board/boardEdit";
	}
	
	@PutMapping("/board-edit/{id}")
	public String boardEdit(@PathVariable("id") Long id, @ModelAttribute BoardDTO boardDTO) {
		
		Board pre_board = boardService.findById(id).get();
		
		Board board = Board.builder()
										.id(id)
										.board_category(boardDTO.getBoard_category())
										.board_title(boardDTO.getBoard_title())
										.board_writer(pre_board.getBoard_writer())
										.board_content(boardDTO.getBoard_content())
										.updated_date(Util.getInstance().dateFormat(new Date()))
										.board_date(pre_board.getBoard_date())
										.board_like(pre_board.getBoard_like())
										.board_hit(pre_board.getBoard_hit())
										.build();
		boardService.save(board);
		
		return "redirect:/board/board-detail-form/"+id;
	}
	
	@DeleteMapping("/board-delete/{id}")
	public String boardDelete(@PathVariable("id") Long id) {
		
		boardService.deleteById(id);
		
		return "redirect:/board/board-list-form";
	}
	
}
