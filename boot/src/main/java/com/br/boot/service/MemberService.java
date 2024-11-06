package com.br.boot.service;

import com.br.boot.dto.MemberDto;

public interface MemberService {
	
	// 로그인
	MemberDto selectMember(MemberDto m);
	
	// 회원가입
	int insertMember(MemberDto m);
	
	// 아이디중복체크
	int selectUserIdCount(String checkId);
	
	// 회원정보변경
	int updateMember(MemberDto m);
	
	// 회원프로필변경
	int updateProfileImg(MemberDto m);
	
	// 회원탈퇴
	int deleteMember(String userId);
	

}
