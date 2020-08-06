<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<input type="text" id="query">
<button onclick="kakaoBookApi()">카카오 api통신</button>
<script type="text/javascript">

	function kakaoBookApi(){
		var xhr=new XMLHttpRequest();
		var query=document.querySelector("#query").value;
		
		xhr.open('GET','https://dapi.kakao.com/v3/search/book?query='+query);
		
		xhr.setRequestHeader("Content-Type",
		"application/x-www-form-urlencoded");
		xhr.setRequestHeader('Authorization','KakaoAK 080def2ff1d974a61f0d965696d7e674');
		//통신 시작
		xhr.send();
		xhr.addEventListener('load',function(){
			
			var data=xhr.response;
			console.log(data)
			sendKakaoData(data);
		})
	}
	
	function sendKakaoData(data){
		//url: jacksoncore 
		//method : post
		var xhr=new XMLHttpRequest();
		xhr.open('POST','jacksoncore');
		
		//헤더설정
		//content -type: application/json
		//request message body에 있는 데이터가 
		//json형식의 데이터임을 header에 명시한다
		xhr.setRequestHeader("Content-Type","application/json");
		xhr.send(data);
		
		
	}

</script>
</body>
</html>