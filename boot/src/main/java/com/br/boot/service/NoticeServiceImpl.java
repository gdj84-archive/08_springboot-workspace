package com.br.boot.service;

import org.springframework.stereotype.Service;

import com.br.boot.dao.NoticeDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NoticeServiceImpl implements NoticeService {
	
	private final NoticeDao noticeDao;

}
