<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>부서테이블 상세</h1>
<hr>
<a href="/dept/list">부서테이블 리스트</a><br><br>
<table>
<tr>
<th>부서번호</th>
<th>부서이름</th>
<th>위치</th>
</tr>
<tr>
<td>${dept.deptno }</td>
<td>${dept.dname }</td>
<td>${dept.loc }</td>
</tr>
</table>
</body>
</html>