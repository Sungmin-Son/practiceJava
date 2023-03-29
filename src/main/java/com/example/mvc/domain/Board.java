package com.example.mvc.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Board {
	
	private int boardSeq;
	private String title;
	private String content;
	private Date regDate;

}
