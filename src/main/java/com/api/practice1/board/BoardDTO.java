package com.api.practice1.board;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDTO {
	
	private Long id;

	@NotBlank(message = "제목을 입력해주세요.")
	//@Size(min = 2, message = "제목은 두글자 이상 입력해주세요.")
	private String board_title;
	
	private String board_writer;

	@NotBlank(message = "내용을 작성해주세요.")
	private String board_content;
	
	private String board_date;
	private String updated_date;
	private int board_hit;
	private int board_like;
	
	@NotBlank(message = "카테고리를 선택해주세요.")
	private String board_category;
	
}
