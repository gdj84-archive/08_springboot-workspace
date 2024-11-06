package com.br.boot.service;

import org.springframework.stereotype.Service;

import com.br.boot.dao.MemberDao;
import com.br.boot.dto.MemberDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
	
	private final MemberDao memberDao;

	@Override
	public MemberDto selectMember(MemberDto m) {
		return memberDao.selectMember(m);
	}

	@Override
	public int insertMember(MemberDto m) {
		return memberDao.insertMember(m);
	}

	@Override
	public int selectUserIdCount(String checkId) {
		return memberDao.selectUserIdCount(checkId);
	}

	@Override
	public int updateMember(MemberDto m) {
		return memberDao.updateMember(m);
	}

	@Override
	public int deleteMember(String userId) {
		return memberDao.deleteMember(userId);
	}

	@Override
	public int updateProfileImg(MemberDto m) {
		return memberDao.updateProfileImg(m);
	}
	
}
