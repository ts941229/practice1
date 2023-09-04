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
	protected Board(Long id, String title, String writer, String content, String create_date, String updated_date,
			int hit, int heart, String category) {
		super();
		this.id = id;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.create_date = create_date;
		this.updated_date = updated_date;
		this.hit = hit;
		this.heart = heart;
		this.category = category;
	}
	
	@Id
	@GeneratedValue(generator = "board_seq_generator", strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	private String writer;
	private String content;
	private String create_date;
	private String updated_date;
	private int hit;
	private int heart;
	private String category;
}
