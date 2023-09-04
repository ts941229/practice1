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
	private String title;
	
	private String writer;

	@NotBlank(message = "내용을 작성해주세요.")
	private String content;
	
	private String create_date;
	private String updated_date;
	private int hit;
	private int heart;
	
	@NotBlank(message = "카테고리를 선택해주세요.")
	private String category;
	
}
