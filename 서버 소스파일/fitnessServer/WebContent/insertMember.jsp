<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 정보 삽입</title>
</head>
<script type="text/javascript">
function insertMember(){
	var param = "name="+document.getElementById("name").value+"&age="+document.getElementById('age').value+"&phone="+document.getElementById("phone").value+"&email="+document.getElementById("email").value+
	"&PT="+document.getElementById("PT").value+"&contterm="+document.getElementById("contterm").value+"&lockerRoomNum="+document.getElementById("lockerRoomNum").value+"&isInfoAllow="+document.getElementById("infoAllow").value;
	//alert(param);
	var xhq = new XMLHttpRequest();
	var json = "";
	
	xhq.onreadystatechange = function(){
		if(xhq.readyState==4 && xhq.status==200){
			json = eval("("+xhq.responseText+")");
			alert(xhq.responseText);
			if((json.result == 'true')){
				alert(json.name+"을 추가하였습니다.");
				location.href = "./SearchMemberList?name="+encodeURI(json.name)+"&startIdx=0&cnt=10";
				//location.href = "/fitnessServer/ShowAllMemberList?startIdx=0&cnt=10";
			}else
				alert(json.name+"을 추가하지 못했습니다.");
		}
	}
	
	xhq.open("POST", "/fitnessServer/InsertMember", true);
	xhq.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
	xhq.send(param);
}

function goBack(){
	history.go(-1);
}
</script>
<body>
<div align="center">
<!-- <form method="POST" action="/fitnessServer/InsertMember"> -->
	<table>
		<tr>
			<td>이름</td>
			<td><input type="text" id="name" /></td>
		</tr>
		<tr>
			<td>나이</td>
			<td><input type="text" id="age" /></td>
		</tr>
		<tr>
			<td>핸드폰 번호</td>
			<td><input type="text" id="phone" maxlength="11" /></td>
		</tr>
		<tr>
			<td>이메일 주소</td>
			<td><input type="text" id="email" /></td>
		</tr>
		<tr>
			<td>PT유무</td>
			<td>
				<select id="PT">
					<option value="1">예</option>
					<option value="0">아니오</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>계약 기간</td>
			<td><input type="text" id="contterm" /></td>
		</tr>
		<tr>
			<td>락커룸 번호</td>
			<td><input type="text" id="lockerRoomNum" /></td>
		</tr>
		<tr>
			<td>정보 제공 유무</td>
			<td><select id="infoAllow">
					<option value="1">예</option>
					<option value="0">아니오</option>
				</select>
			</td>
		</tr>
	</table>
	<button id='insertBtn' onclick="insertMember()">추가하기</button>
	<button id='backBtn' onclick="goBack()">취소</button>
<!-- </form> -->
</div>
</body>
</html>