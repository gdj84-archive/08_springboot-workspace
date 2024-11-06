package com.br.boot.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class BoardDto {
	
	private int boardNo;
	private String boardTitle;
	private String boardWriter;
	private String boardContent;
	private int count;
	private String registDt;
	private String status; 
	private int attachCount;
	
	private List<AttachDto> attachList; // has many 관계 
}








