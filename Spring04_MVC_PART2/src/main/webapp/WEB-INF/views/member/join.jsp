<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입 양식</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/common.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/member.css" />
</head>
<body>
<div class="wrap">
	<div class="memberBox">
		<div class="join">
		      <form 
			       action="<%=request.getContextPath()%>/member/joinemailcheck"
			       method="post" onsubmit="return validate();">
			       	<span class="sector-title">회원가입</span>
			        <div class="join-info">
						<p>* id</p>
						<button type="button" class="btn_id-check" onclick="idCheck()">ID 확인</button>
				  		<input type="text" name="userId" id="userId" class="join-text" size="10"/>
			  			<span id="id-check-msg" class="id-check-msg"></span>
			  		</div>
			  		<div class="join-info">
				  		<p>* password</p>
				  		<input type="password" name="password" id="userPwd" class="join-text" size="10"/>
			  		</div>
			  		<div class="join-info">
				  		<p>* email</p>
				  		<input type="text" name="email" class="join-text" size="10"/>
				  	</div>
				  	<div class="join-info">
				  		<p>* hp</p>
				  		<input type="text" name="tell" class="join-text" size="10"/>
				  	</div>
				  	<button type="submit" class="btn-join-submit">전송</button>
			</form>
		</div>
	</div>
</div>
	
	<script type="text/javascript">
	
		var ajaxFlag = false;
	   
	   function validate() {
	        var pass = document.getElementById('userPwd');
	        var regExpPw = /(?=.*\d)(?=.*[~`!@#$%\^&*()-+=])(?=.*[a-zA-Z]).{8}$/;

	        function chk(re, e, msg) {
	            if(re.test(e.value)) {                 
	                return true;          
	          }else{
	                alert(msg);
	              e.value = "";
	              e.focus();
	              //기본 이벤트 취소
	              return false;
	            }
	        }
	        
	        if(!ajaxFlag){
	           alert("아이디 중복검사를 해주세요");
	           return false;
	        }
	        
	        // 비밀번호 검사
	         // 암호는 영문자와 숫자로 8글자이상  기호문자 한개이상 8글자 이상
	        if(!chk(regExpPw, pass,'비밀번호 숫자,영어,특수문자가 하나 이상 포함, 8글자 이상 50글자 이하')){
	           return false;
	        }

	        return true;
	    }
	
	
	function idCheck(){
		var id=document.querySelector("#userId").value;
		var xhr=new XMLHttpRequest();
		  xhr.onreadystatechange =function(){
			 if(xhr.readyState ==4){
				 if(xhr.status ==200){
					 console.log('성공');
				 }
			 }
		 }
		  
		 xhr.open('POST','/member/idcheck');
		 
		
		xhr.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
		
		//http request body설정
/* 		xhr.send(null);  전송할 데이터가 있다면 파라미터에 넣어서 보내주면 된다*/
		xhr.send('userId='+id);
		//ajax통신이 끝난뒤 실행할 콜백함수 등록
		xhr.addEventListener('load',function(){
			var data=xhr.response;
			if(data !=''){
				document.querySelector('#id-check-msg').textContent = id + '는 이미 존재하는 아이디 입니다.';
				ajaxFlag=false;
			}else{
				document.querySelector('#id-check-msg').textContent = '사용 가능한 아이디 입니다.';
				ajaxFlag = true;
			}
		})
		}
	</script>
	
	
</body>
</html>
