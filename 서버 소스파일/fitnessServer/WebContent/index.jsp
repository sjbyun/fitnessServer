<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
function tryLogin(){
	var xhq = new XMLHttpRequest();
	var param = "loginId="+document.getElementById('loginId').value+"&password="+document.getElementById('password').value;
	
	xhq.onreadystatechange = function(){
		if(xhq.readyState==4 && xhq.status==200){
			//alert(xhq.responseText);
			var result = eval("("+xhq.responseText+")");
			if((result.result == 'false'))
				alert('로그인 실패');
			else				
				location.href = "/fitnessServer/ShowAllMemberList?startIdx=0&cnt=10";
		}
	}
	xhq.open("POST", "/fitnessServer/tryLogin", true);
	xhq.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
	xhq.send(param);
}
</script>
<body>
<div align="center">
	<div style='position: absolute; left: 38%; top: 30%;'>아이디 : </div><div style='position: absolute; left: 50%; top: 30%;'><input id='loginId' type="text" maxlength="15" /></div>
	<div style='position: absolute; left: 38%; top: 36%;'>비밀번호 : </div><div style='position: absolute; left: 50%; top: 36%;'><input id='password' type="password" maxlength="20" /></div>
</div>
<div align="right" style='position: absolute; left: 50%; top: 42%'>
	<button style='width: 80px; height: 30px;' onclick="tryLogin()">로그인</button>
</div>
</body>
</html>