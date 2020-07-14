<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>부서 정보 목록</h1>
<hr>

<table>
<tr>
<th>부서번호</th>
<th>부서이름</th>
<th>위치</th>
</tr>
<c:forEach items="${deptlist }" var="dept">
<tr>
<td>${dept.deptno }</td>
<td><a href="/dept/detail?deptno=${dept.deptno }">${dept.dname }</a> </td>
<td>${dept.loc }</td>
</tr>
</c:forEach>
</table>
<br>
<a href="/dept/insert">부서 정보 삽입</a>
</body>
</html>