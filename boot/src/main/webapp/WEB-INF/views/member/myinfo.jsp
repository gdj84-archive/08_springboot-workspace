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
    #profileImg{
        width:250px;
        height:250px;
        border:1px solid lightgray;
        border-radius: 50%;
    }
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
        <h2 class="m-4">마이페이지</h2>
        <br>

        <div align="center">
            <img id="profileImg" src="${ contextPath }<c:out value='${ loginUser.profileURL }' default='/resources/images/defaultProfile.png' />" onclick="$('#profileImgFile').click();">
            <input type="file" class="file" id="profileImgFile" style="display:none;" accept="image/*">
        </div>
        
        <script>
        	$(document).ready(function(){
        		$("#profileImgFile").on("change", function(evt){
        				const files = evt.target.files; // FileList {File, File}
        				
        				if(files.length != 0){ // 선택된 파일이 있을 경우 => 비동기식으로 파일 전송 업로드 + db에 기록
        					
        					let formData = new FormData();
        					formData.append("uploadFile", files[0]);
        					
        					$.ajax({
        						url: '${contextPath}/member/updateProfile.do',
        						type: 'post',
        						data: formData,
        						processData: false,
        						contentType: false,
        						success: function(resData){
        							if(resData == "SUCCESS"){
        								location.reload();
        							}else{
        								alert("프로필 이미지 변경 실패");
        							}
        						}
        					})
        					
        				}
        				
        		})
        	})
        </script>

        <form action="${ contextPath }/member/update.do" method="post" id="myinfo_form">
            <div class="form-group">
                <label for="userId">* ID :</label>
                <input type="text" class="form-control" id="userId" name="userId" value="${ loginUser.userId }" readonly><br>
                
                <label for="userName">* Name :</label>
                <input type="text" class="form-control" id="userName" name="userName" value="${ loginUser.userName }"><br>
                
                <label for="email"> &nbsp; Email :</label>
                <input type="email" class="form-control" id="email" name="email" value="${ loginUser.email }"><br>
                
                <label for="phone"> &nbsp; Phone :</label>
                <input type="tel" class="form-control" id="phone" name="phone" value="${ loginUser.phone }"><br>
                
                <label for="address"> &nbsp; Address :</label>
                <input type="text" class="form-control" id="address" name="address" value="${ loginUser.address }"><br>
                
                <label for=""> &nbsp; Gender : </label> &nbsp;&nbsp;
                <input type="radio" name="gender" id="Male" value="M">
                <label for="Male">남자</label> &nbsp;&nbsp;
                <input type="radio" name="gender" id="Female" value="F">
                <label for="Female">여자</label><br>
                
                <script>
                	$(function(){
                		$("#myinfo_form :radio").filter("[value=${loginUser.gender}]").attr("checked", true);
                	})
                </script>
            </div>
            <br>
            <div class="btns" align="center">
                <button type="submit" class="btn btn-primary">수정하기</button>
                <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteForm">회원탈퇴</button>
            </div>
        </form>
      
      </div>

    </section>
    <!-- Section end -->

    <!-- Footer start -->
    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    <!-- Footer end -->
  
  </div>

  <!-- 회원탈퇴 버튼 클릭시 보여질 Modal -->
  <div class="modal" id="deleteForm">
      <div class="modal-dialog">
          <div class="modal-content">
          
              <!-- Modal Header -->
              <div class="modal-header">
              <h4 class="modal-title">회원탈퇴</h4>
              <button type="button" class="close" data-dismiss="modal">&times;</button>
              </div>
              
              <!-- Modal body -->
              <div class="modal-body" align="center">
              
                  <b>
                    탈퇴 후 복구가 불가능합니다. <br>   
                    정말로 탈퇴 하시겠습니까?
                  </b>

                  <form action="${ contextPath }/member/resign.do" method="post">
                      비밀번호 : 
                      <input type="password" name="userPwd" required>

                      <button type="submit" class="btn btn-danger">탈퇴하기</button>
                  </form>

              </div>
              
          </div>
      </div>
  </div>
  
  
	<script src="${ contextPath }/resources/js/fileValidate.js"></script>

</body>
</html>