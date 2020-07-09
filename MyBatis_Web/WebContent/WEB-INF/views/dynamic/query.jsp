<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
        
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- jQuery 2.2.4.min -->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<!-- 부트스트랩 3.3.2 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

</head>
<body>
<h1 align="center">전체목록</h1>
<hr>

<table class="table table-hover" >
<thead>
<tr>

<td>번호</td>
<td>아이디</td>
<td>비번</td>


</tr>
</thead>
<tbody>
<c:forEach items="${list }" var="list">
<tr>
<td>${list.NO }</td>
<td>${list.ID }</td>
<td>${list.PW }</td>

</tr>
</c:forEach>
</tbody>
</table>
</body>
</html>