package com.br.boot.dto;

import java.sql.Date;

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
public class MemberDto {
	private int userNo;
	private String userId, userPwd, userName, email, gender
				 , phone, address, profileURL, status;
	private Date signupDt, modifyDt;
}
