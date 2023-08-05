package com.api.practice1.board;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDTO {
	
	private Long board_id;
	private String board_title;
	private String board_writer;
	private String board_content;
	private String board_date;
	private String board_updated_date;
	private int board_hit;
	private int board_like;
	private String board_category;
	
}
