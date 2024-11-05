<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div class="container p-3">

    <!-- Header, Nav start -->
    <jsp:include page="/WEB-INF/views/common/header.jsp" />
    <!-- Header, Nav end -->

    <!-- Section start -->
    <section class="row m-3" style="min-height: 500px">

      <div class="container border p-5 m-4 rounded">
        <h2 class="m-4">WebSocket을 이용하여 실시간으로 통신하기</h2>
        
        <p>
        	실시간으로 알림을 발생시킨다거나 채팅을 할 때 주로 websocket 사용 <br><br>
        	
        	* HTTP통신 (기본통신방식) : 비연결 통신 <br>
        	- 한번 요청 보내고 처리되면 연결 끊김 == 지속적으로 데이터를 주고받기 x <br>
        	- 지속적으로 데이터를 주고받고자 할 경우 setInterval과 ajax를 같이 사용해서
        	  주기적으로 요청을 보내 응답을 받으면 됨 (polling) => 단, 네트워크 리소스를 많이 사용해서 비효율적 <br><br>
        	  
        	* WebSocket통신 : 영구적 양방향 통신 <br>
        	- 실시간으로 연결되어있음 == 지속적으로 데이터를 주고받기 o <br><br>
        	
        	<hr>
        	
        	<ol>
        		<li>웹 소켓 관련 라이브러리 추가 : pom.xml</li>
        		<li>DispatcherServlet등록하는 구문에 비동기 작업에 대한 구문 추가 : web.xml</li>
        		<li>
        			EchoHandler 구성<br>
        			클라이언트가 서버에 연결되었을 때, 
        			서버로 데이터가 전송되었을때, 
        			클라이언트와의 연결이 끊겼을 때 
        			처리할 구문을 작성할 클래스
        		</li>
        		<li>webSocket관련 등록구문, EchoHandler 등록구문 작성 : servlet-context.xml</li>
        		<li>웹소켓 사용을 위한 화면 구성 (자바스크립트의 sockjs 이용해서 웹소켓 사용)</li>
        	</ol>
        	
        	<hr>
        	
        	<c:if test="${ not empty loginUser }">
        		<a class="btn btn-secondary" href="${ contextPath }/chat/room.do">채팅방 입장</a>
        	</c:if>
        	
        </p>
        
      
      </div>

    </section>
    <!-- Section end -->

    <!-- Footer start -->
    <jsp:include page="/WEB-INF/views/common/footer.jsp" />
    <!-- Footer end -->

  </div>
</body>
</html>