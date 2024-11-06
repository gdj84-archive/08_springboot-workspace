package com.br.boot.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.br.boot.dto.AttachDto;
import com.br.boot.dto.BoardDto;
import com.br.boot.dto.PageInfoDto;
import com.br.boot.dto.ReplyDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class BoardDao {
	
	private final SqlSessionTemplate sqlSession;
	
	public int selectBoardListCount() {
		return sqlSession.selectOne("boardMapper.selectBoardListCount");
	}
	
	public List<BoardDto> selectBoardList(PageInfoDto pi){
		RowBounds rowBounds = new RowBounds((pi.getCurrentPage() - 1) * pi.getBoardLimit() , pi.getBoardLimit());
		return sqlSession.selectList("boardMapper.selectBoardList", null, rowBounds);
	}
	
	public int selectSearchListCount(Map<String, String> search) {
		return sqlSession.selectOne("boardMapper.selectSearchListCount", search);
	}
	
	public List<BoardDto> selectSearchList(Map<String, String> search, PageInfoDto pi){
		RowBounds rowBounds = new RowBounds((pi.getCurrentPage() - 1) * pi.getBoardLimit() , pi.getBoardLimit());
		return sqlSession.selectList("boardMapper.selectSearchList", search, rowBounds);
	}
	
	public int insertBoard(BoardDto b) {
		return sqlSession.insert("boardMapper.insertBoard", b);
	}
	
	public int insertAttach(AttachDto at) {
		return sqlSession.insert("boardMapper.insertAttach", at);
	}
	
	public BoardDto selectBoard(int boardNo) {
		return sqlSession.selectOne("boardMapper.selectBoard", boardNo);
	}
	
	public int updateIncreaseCount(int boardNo) {
		return sqlSession.update("boardMapper.updateIncreaseCount", boardNo);
	}
	
	public List<ReplyDto> selectReplyList(int boardNo){
		return sqlSession.selectList("boardMapper.selectReplyList", boardNo);
	}
	
	public int insertReply(ReplyDto r) {
		return sqlSession.insert("boardMapper.insertReply", r);
	}
	
	public int deleteBoard(int boardNo) {
		return sqlSession.update("boardMapper.deleteBoard", boardNo);
	}
	
	public List<AttachDto> selectDelAttach(String[] delFileNo){
		return sqlSession.selectList("boardMapper.selectDelAttach", delFileNo);
	}
	
	public int updateBoard(BoardDto b) {
		return sqlSession.update("boardMapper.updateBoard", b);
	}
	
	public int deleteAttach(String[] delFileNo) {
		return sqlSession.delete("boardMapper.deleteAttach", delFileNo);
	}
	
	public int deleteReplyCompletely() {
		return sqlSession.delete("boardMapper.deleteReplyCompletely");
	}
	
	
	
	
	
	
	
}
