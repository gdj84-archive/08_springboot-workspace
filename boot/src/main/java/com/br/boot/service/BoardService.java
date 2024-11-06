package com.br.boot.service;

import java.util.List;
import java.util.Map;

import com.br.boot.dto.AttachDto;
import com.br.boot.dto.BoardDto;
import com.br.boot.dto.PageInfoDto;
import com.br.boot.dto.ReplyDto;

public interface BoardService {
	
	// 게시글 목록 조회 (페이징 처리)
	int selectBoardListCount();
	List<BoardDto> selectBoardList(PageInfoDto pi);
	
	// 게시글 검색 조회 (페이징 처리)
	int selectSearchListCount(Map<String, String> search);
	List<BoardDto> selectSearchList(Map<String, String> search, PageInfoDto pi);
	
	// 게시글 등록
	int insertBoard(BoardDto b);
	
	// 게시글 상세 - 조회수 증가
	int updateIncreaseCount(int boardNo);
	
	// 게시글 상세 - 게시글 조회 
	BoardDto selectBoard(int boardNo);
	
	// 게시글 삭제 
	int deleteBoard(int boardNo);
	
	// 게시글 수정
	List<AttachDto> selectDelAttach(String[] delFileNo);
	int updateBoard(BoardDto b, String[] delFileNo);
	
	// 댓글 목록 조회
	List<ReplyDto> selectReplyList(int boardNo);
	
	// 댓글 등록 
	int insertReply(ReplyDto r);
	
	
	// 댓글 완전삭제 (스케줄러에 의해 작동)
	int deleteReplyCompletely();
	
	

}
