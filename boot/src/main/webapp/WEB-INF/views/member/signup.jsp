<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.smallfont {font-size: 0.8em;}
	.uncheck {display: none;} /*유효성검사 전일 경우*/
	.unusable {color: red;}  /*사용불가능일 경우*/
	.usable {color: green;}  /*사용가능일 경우*/
</style>
</head>
<body>

	<div class="container p-3">

        <!-- Header, Nav start -->
        <jsp:include page="/WEB-INF/views/common/header.jsp"/>
        <!-- Header, Nav end -->
    
        <!-- Section start -->
        <section class="row m-3" style="min-height: 500px">
    
          <div class="container border p-5 m-4 rounded">
            <h2 class="m-4">회원가입</h2>
            <br>

            <form action="${ contextPath }/member/insert.do" method="post" id="signup_form">
                <div class="form-group">
                    <label for="userId">* ID :</label>
                    <input type="text" class="form-control" id="userId" name="userId" placeholder="Please Enter ID" required>
                    <div id="idCheck_result" class="uncheck smallfont"></div>
                    <br>
                    
                    <label for="userPwd">* Password :</label>
                    <input type="password" class="form-control" id="userPwd" name="userPwd" placeholder="Please Enter Password" required>
                    <div id="pwdCheck_result" class="uncheck smallfont"></div>
                    <br>
                    
                    <label for="checkPwd">* Password Check :</label>
                    <input type="password" class="form-control" id="checkPwd" placeholder="Please Enter Password" required>
                    <div id="pwdEqualCheck_result" class="uncheck smallfont"></div>
                    <br>
                    
                    <label for="userName">* Name :</label>
                    <input type="text" class="form-control" id="userName" name="userName" placeholder="Please Enter Name" required>
                    <div id="nameCheck_result" class="uncheck smallfont"></div>
                    <br>
                    
                    <label for="email"> &nbsp; Email :</label>
                    <input type="email" class="form-control" id="email" name="email" placeholder="Please Enter Email"><br>
                    
                    <label for="phone"> &nbsp; Phone :</label>
                    <input type="tel" class="form-control" id="phone" name="phone" placeholder="Please Enter Phone (-포함)"><br>
                    
                    <label for="address"> &nbsp; Address :</label>
                    <input type="text" class="form-control" id="address" name="address" placeholder="Please Enter Address"><br>
                    
                    <label for=""> &nbsp; Gender : </label> &nbsp;&nbsp;
                    <input type="radio" name="gender" id="Male" value="M">
                    <label for="Male">남자</label> &nbsp;&nbsp;
                    <input type="radio" name="gender" id="Female" value="F">
                    <label for="Female">여자</label><br>
                    
                </div>
                <br>
                <div class="btns" align="center">
                    <button type="submit" class="btn btn-primary" disabled>회원가입</button>
                    <button type="reset" class="btn btn-danger"> 초기화</button>
                </div>
            </form>
            
            <script>
            	
            	// 각 항목의 값들이 유효한값으로 입력되면 true 변경됨 
            	let idResult = false;    
            	let pwdResult = false;
            	let pwdEqualResult = false;
            	let nameResult = false;
            	
            	// input요소들 유효성검사때마다 회원가입 버튼을 활성화 또는 비활성화 처리해주는 함수
            	function validate(){
            		if(idResult && pwdResult && pwdEqualResult && nameResult){ // 모든 값들이 다 유효한 값으로 입력됐을 경우
            			$('#signup_form :submit').removeAttr('disabled'); // 회원가입버튼 활성화
            		}else{
            			$('#signup_form :submit').attr('disabled', true); // 회원가입버튼 비활성화
            		}
            	}
            	
            	// input요소에 작성된 값이 없는지 체크해주는 함수 
            	function noValueCheck($input, $result){ // 비교할 input요소객체, 메세지출력되는 div요소객체
            		if($input.val().trim().length == 0){ // 값이 비어있을 경우 
            			$result.removeClass("usable unusable")
            						 .addClass("uncheck")
            						 .text(""); // uncheck활성화 (결과 영역 출력 숨기기)
            			return false; // 유효성 검사를 진행할 필요가 없기 때문에 false 반환 
            		}
            		return true;    // 유효성 검사를 진행하도록 true 반환
            	}
            	
            	// input요소에 작성된 값이 정규식(유효한패턴)에 만족하는지 체크해주는 함수
            	function regExpCheck($input, $result, regExp, msg1, msg2){
            		if(regExp.test($input.val())){ // 유효한값으로 잘 입력됐을 경우
            			$result.removeClass("uncheck unusable")
            					   .addClass("usable")
            					   .text(msg1);
            			return true;
            		}else{ // 유효하지 않은 값으로 입력됐을 경우
            			$result.removeClass("uncheck usable")
            						 .addClass("unusable")
            						 .text(msg2);
            		  return false;
            		}
            	}
            
            	$(function(){
            	
            		// 아이디 유효성검사
            		$("#signup_form #userId").on("keyup", function(){
            			
            			let regExp = /^[a-z\d]{5,12}$/;
            			
            			idResult = noValueCheck( $(this), $("#idCheck_result") )
            											&& regExpCheck( $(this), $("#idCheck_result")
            																						 , regExp, "사용가능한 아이디입니다."
            																						 				 , "영문, 숫자 포함 5~12자리로 작성해주세요");
            			
            			if(idResult){
            				$.ajax({
            					url: '${contextPath}/member/idcheck.do',
            					async: false,
            					data: "checkId=" + $(this).val(),
            					success: function(resData){
            						if(resData == "NNNNN"){
            							$("#idCheck_result").removeClass("uncheck usable")
            																	.addClass("unusable")
            																	.text("중복된 아이디가 존재합니다. 다시 입력해주세요.");
            							idResult = false;
            						}
            					}
            				})
            			}
            			
            			validate();
            			
            		}) // id check end
            		
            		// 비밀번호 유효성검사
            		$("#signup_form #userPwd").on("keyup", function(){
            			let regExp = /^[a-z\d!@#$%^&*]{8,15}$/i;
            			
            			pwdResult = noValueCheck( $(this), $("#pwdCheck_result") )
		            									&& regExpCheck( $(this), $("#pwdCheck_result")
		            																				 , regExp, '사용가능한 비밀번호입니다.'
		            																								 , '영문, 숫자, 특수문자 포함 8~15자리로 작성해주세요.' )
		            	validate();			
            		}) // pwd check end
            		
            		// 비밀번호확인 유효성검사
            		$("#signup_form #checkPwd").on("keyup", function(){
            			
            			let regExp = new RegExp("^" + $("#signup_form #userPwd").val() + "$");
            			
            			pwdEqualResult =  pwdResult && noValueCheck( $(this), $("#pwdEqualCheck_result") )
									            								&& regExpCheck( $(this), $("#pwdEqualCheck_result")
									            																			 , regExp, '비밀번호가 일치합니다.'
									            																						   , '비밀번호가 일치하지 않습니다.')
									validate();            			
            		
            		}) // pwd equal check end
            		
            		// 이름 유효성검사
            		$("#signup_form #userName").on("keyup", function(){
            			let regExp = /^[가-힣]{2,5}$/;
            			
            			nameResult = noValueCheck( $(this), $("#nameCheck_result") )
            												&& regExpCheck( $(this), $("#nameCheck_result")
            																			 				 , regExp , "사용가능한 이름입니다."
            																			 				 				  , "한글 2~5글자로 작성해주세요.");
            			validate();
            			
            		}) // name check end
            		
            	})
            	
            </script>
          
          </div>
    
        </section>
        <!-- Section end -->
    
        <!-- Footer start -->
        <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
        <!-- Footer end -->
    
    </div>

</body>
</html>