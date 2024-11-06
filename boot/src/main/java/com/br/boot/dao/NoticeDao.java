package com.br.boot.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class NoticeDao {
	
	private final SqlSessionTemplate sqlSession;

}
