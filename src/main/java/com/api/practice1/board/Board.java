package com.api.practice1.board;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(name = "board_seq_generator",
								sequenceName = "board_seq",
								allocationSize = 1,
								initialValue = 1)
public class Board {
	
	@Builder(toBuilder = true)
	protected Board(Long board_id, String board_title, String board_writer, String board_content, String board_date, String board_updated_date,
			int board_hit, int board_like, String board_category) {
		this.board_id = board_id;
		this.board_title = board_title;
		this.board_writer = board_writer;
		this.board_content = board_content;
		this.board_date = board_date;
		this.board_updated_date = board_updated_date;
		this.board_hit = board_hit;
		this.board_like = board_like;
		this.board_category = board_category;
	}
	
	@Id
	@GeneratedValue(generator = "board_seq_generator", strategy = GenerationType.IDENTITY)
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
