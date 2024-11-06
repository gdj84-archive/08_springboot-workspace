package com.br.boot.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.br.boot.dto.MemberDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class MemberDao {
	
	private final SqlSessionTemplate sqlSession;
	
	public MemberDto selectMember(MemberDto m) {
		return sqlSession.selectOne("memberMapper.selectMember", m);
	}
	
	public int insertMember(MemberDto m) {
		return sqlSession.insert("memberMapper.insertMember", m);
	}
	
	public int selectUserIdCount(String checkId) {
		return sqlSession.selectOne("memberMapper.selectUserIdCount", checkId);
	}
	
	public int updateMember(MemberDto m) {
		return sqlSession.update("memberMapper.updateMember", m);
	}
	
	public int updateProfileImg(MemberDto m) {
		return sqlSession.update("memberMapper.updateProfileImg", m);
	}
	
	public int deleteMember(String userId) {
		return sqlSession.update("memberMapper.deleteMember", userId);
	}
	
	
	
	
	
	
	
	
	
	

}
