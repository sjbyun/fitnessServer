<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList" %>
<%@ page import="info.MemberInfo" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 목록</title>
</head>
<script type="text/javascript">
function searchByName(){
	location.href = "./SearchMemberList?name="+encodeURI(document.getElementById("searchName").value)+"&startIdx=0&cnt=10";
}

function goInsertpage(){
	location.href = "./insertMember.jsp";
}

function goModifypage(name){
	location.href = "./update_memberinfo.jsp?phone="+phone; //encodeURI(name);
}

function deleteMember(phone){
	var param = "phone="+phone;
	var xhq = new XMLHttpRequest();
	var json = "";
	
	xhq.onreadystatechange = function(){
		if(xhq.readyState==4 && xhq.status==200){
			json = eval("("+xhq.responseText+")");
			if(json.result == true)
				alert(json.phone+"을 삭제하였습니다.");
			else
				alert(json.phone+"을 삭제하지 못했습니다.");
		}
		location.reload();
	}
	
	xhq.open("POST", "/fitnessServer/DeleteMember", true);
	xhq.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
	xhq.send(param);
}
</script>
<%
	ArrayList<MemberInfo> list = (ArrayList<MemberInfo>)request.getAttribute("memberlist");
	int pageCnt = Integer.parseInt(session.getAttribute("pageCnt").toString());
	int totalCnt = Integer.parseInt(session.getAttribute("totalCnt").toString());
	int pageNum = Integer.parseInt(session.getAttribute("pageNum").toString());
	
	int totalPage = (totalCnt / pageCnt);
	if((totalCnt % pageCnt) != 0)
		totalPage += 1;
	
	//System.out.println("jsp val - "+request.getAttribute("test").toString());
%>

<body>
<div align="left" style="position: absolute; left: 350px;">
	<button style="width: 80px; height: 30px;" id="insertBtn" onclick="goInsertpage();">회원 추가</button>
</div>
<div align="right" style="position: absolute; right: 350px;">
	<input type="text" id="searchName" size="10px" />
	<button style="width: 80px; height: 30px;" id="searchBtn" onclick="searchByName();">검색하기</button>
</div>
<table class="wrapper" cellspacing="0px" style="padding-top: 20px;" >
	<tr align="center" style="width: 100%; height: 80px;">
		<td align="center" style="width: 140px;">이름</td>
		<td align="center" style="width: 140px;">나이</td>
		<td align="center" style="width: 140px;">핸드폰 번호</td>
		<td align="center" style="width: 140px;">이메일</td>
		<td align="center" style="width: 140px;">PT</td>
		<td align="center" style="width: 140px;">계약기간</td>
		<td align="center" style="width: 140px;">락커룸 번호</td>
		<td align="center" style="width: 140px;">정보 제공 유무</td>
		<td align="center" style="width: 140px;"></td>
	</tr>
	<%
	if(list != null && list.size()!=0){
		for(int i=0; i<list.size(); i++){
	%>
		<tr>
			<td align="center" style="width: 140px;"><%=list.get(i).getName() %></td>
			<td align="center" style="width: 140px;"><%=list.get(i).getAge() %></td>
			<td align="center" style="width: 140px;"><%=list.get(i).getPhone_num() %></td>
			<td align="center" style="width: 140px;"><%=list.get(i).getEmail_addr() %></td>
			<td align="center" style="width: 140px;"><%=list.get(i).getisPT() %></td>
			<td align="center" style="width: 140px;"><%=list.get(i).getCont_term() %> 개월</td>
			<td align="center" style="width: 140px;"><%=list.get(i).getLockerroom_num() %></td>
			<td align="center" style="width: 140px;"><%=list.get(i).getIs_infoAllow() %></td>
			<td align="center" style="width: 140px;">
				<button id="modifyBtn" onclick="goModifypage('<%=list.get(i).getPhone_num() %>');" >수정</button>
				<button id="deleteBtn" onclick="deleteMember('<%=list.get(i).getPhone_num() %>');">삭제</button>
			</td>
		</tr>
		<%
			}
		%>
	</table>
	<div align="center">
	<%	if(pageNum >= 10){ %>
			<a href="/fitnessServer/ShowAllMemberList?startIdx=<%=((pageNum/pageCnt)-1)*pageCnt %>&cnt=<%=pageCnt %>">이전 페이지</a>;
	<% 
		}
		for(int i=(pageNum/pageCnt)*pageCnt; i<totalPage; i++){
			if(i < ((pageNum / pageCnt) * pageCnt + pageCnt)){
	%>
				<a href="/fitnessServer/ShowAllMemberList?startIdx=<%=i*pageCnt %>&cnt=<%=pageCnt%>"><%=i+1 %></a>
	<%
			}else{
	%>
				<a href="/fitnessServer/ShowAllMemberList?startIdx=<%=i*pageCnt %>&cnt=<%=pageCnt%>">다음 페이지</a>
	<%
				break;
			}
		}
	}
	%>
</div>
</body>
</html>