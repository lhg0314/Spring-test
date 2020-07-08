<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

<h1 align="center">사원정보</h1>


<table class="table">

<tr>

<td>사원번호</td>
<td>이름</td>
<td>직무</td>
<td>매니저</td>
<td>입사일</td>
<td>급여</td>
<td>상여금</td>
<td>부서번호</td>

</tr>


<tr>
<td>${emp.empno }</td>
<td>${emp.ename }</td>
<td>${emp.job }</td>
<td>${emp.mgr }</td>
<td>${emp.hiredate }</td>
<td>${emp.sal }</td>
<td>${emp.comm }</td>
<td>${emp.deptno }</td>
</tr>


</table>
</body>
</html>