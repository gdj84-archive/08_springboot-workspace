package com.br.boot.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.br.boot.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class BoardScheduler {
	
	private final BoardService boardService;
	
	/*
	 * * Scheduler
	 *   특정날짜 | 특정시간 | 일정주기마다 뭔가의 작업을 묵시적으로 실행시켜주는 주체
	 *   즉, 별도의 액션이 없어도 알아서 특정시간이 되면 자동 실행됨
	 *   
	 * * Spring에서 Scheduler
	 * 1) 매번 묵시적으로 실행시키고자 하는 작업들을 정의할 클래스만들기 (빈 등록되야됨)
	 * 2) 해당 클래스 내에 각 작업별 메소드 작성 
	 *    ㄴ 반환형은 void
	 *    ㄴ 매개변수 없이
	 *    ㄴ @Scheduled 부여하기
	 * 3) servlet-context.xml에서 스케줄링을 사용을 위해 task 추가하기
	 * 
	 * * Cron 표현식 
	 *   작업을 실행시키고자 하는 날짜 및 시간 또는 일정주기를 지정할때 사용하는 문법
	 *   
	 * 1) 형식
	 *    초 분 시 일 월 요일 [연도]
	 *    
	 * 2) 각 자리별 표기가능한 값
	 *    초		: 0 ~ 59
	 * 	  분		: 0 ~ 59
	 * 	  시		: 0 ~ 23
	 * 	  일		: 1 ~ 31
	 * 	  월		: 0 ~ 11(JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, DEC)
	 * 	  요일 		: 0 ~ 6 (SUN, MON, TUE, WED, THR, FRI, SAT)
	 * 	  연도		: 생략 가능
	 * 
	 * 3) 각 형식에 작성가능한 키워드 
	 *    ? 	: 설정값 없을때 (일|요일)
	 * 	  * 	: 모든조건 (초|분|시|일|월)
	 * 	  L		: 마지막 (일|요일)
	 * 	  W 	: 가장가까운 평일 (일)	ex) 10W ==> 10일이 평일일경우(10일에 실행) / 10일이 토요일일경우(9일에 실행) / 10일이 일요일일경우(11일에 실행)
	 * 	  #		: 몇주째인지 (요일)		ex) 3#2 ==> 수요일2째주
	 * 	  /		: 주기 (시작시간/단위)	ex) 분 자리에 0/10 ==> 0분부터 10분 간격으로
	 *    - 	: 범위 					ex) 시 자리에 1-3  ==> 1시, 2시, 3시
	 *    ,		: 여러개 지정
	 *  
	 * 4) 예시
	 *    0 0 * * * *            : 매일 모든 시간마다
	 *    0 0 5 * * * 			 : 매일 5시 마다 
	 *    0 0 3-5 * * *			 : 매일 3,4,5시 마다 
	 *    0 0 4,5 * * * 		 : 매일 4,5시 마다 
	 *    0 1/30 3-5 * * *		 : 매일 3:01, 3:31, 4:01, 4:31, 5:01, 5:31 마다 
	 *    0 0 12 * * MON-FRI     : 평일 12시마다 
	 * 
	 */
	
	@Scheduled(cron="0 8 11 * * *") // 매일 오전 11시 8분마다 실행
	public void execute1() {
		log.debug("매일 오전 11시 8분마다 실행됨");
	}
	
	@Scheduled(cron="0 0/1 * * * *") // 1분마다 실행
	public void execute2() {
		log.debug("1분마다 매번 실행됨");
	}
	
	@Scheduled(cron="0 0 0/1 * * *") // 1시간마다 실행
	public void execute3() {
		log.debug("1시간마다 매번 실행됨");
	}
	
	// 통계 정보를 위해 매일 밤 12시에
	// 현재 게시글의 총 갯수를 로그로 기록을 남기는 스케줄러가 필요하다고 가정
	@Scheduled(cron="0 0 0 * * *") // 매일 밤 12시마다 실행
	public void execute4() {
		log.debug("현재 게시글의 총 갯수: {}", boardService.selectBoardListCount());
	}
	
	// 일요일 새벽 1시마다 현재 status가 N인 댓글을 완벽히 delete 해주는 스케줄러가 필요하다고 가정
	@Scheduled(cron="0 0 1 * * SUN")
	public void execute5() {
		int result = boardService.deleteReplyCompletely();
		log.debug("현재 완전 삭제된 댓글 갯수: {}", result);
	}
	
}
