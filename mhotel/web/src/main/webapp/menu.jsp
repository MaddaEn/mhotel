<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
body {
    background-color: linen;
}

</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body style="color:blue;">
	<%
		if (request.getUserPrincipal() != null) {
	%>
	<p style = "color: maroon;
    margin-left: 369px;">Bine ai venit :
	<%=request.getUserPrincipal().getName() %><BR>
	
	<%
		}
	%>
	</p>

<BR>
<BR>
	<div width="100%">
		<%
			if (request.isUserInRole("receptioner")) {
		%>
		<a href="customer/list" , style = "text-transform: uppercase;">Clienti</a>&nbsp;&nbsp;&nbsp;&nbsp;
		<%
			}
		%>
		<%
			if (request.isUserInRole("manager_hotel")) {
		%>
		<a href="hotel/list", style = "text-transform: uppercase;">Hoteluri</a>&nbsp;&nbsp;&nbsp;&nbsp; <a
			href="hotel/checkout", style = "text-transform: uppercase;">Check out</a>&nbsp;&nbsp;&nbsp;&nbsp;
		<%
			}
		%>
		<a href="hotel/availableRooms", style = "text-transform: uppercase;">Raport camere libere</a>&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="hotel/unavailableRooms", style = "text-transform: uppercase;">Raport camere ocupate</a>&nbsp;&nbsp;&nbsp;&nbsp;

	</div>
	<BR>
	<BR>
	<BR>
	
	<a href='logout', style = "text-transform: lowercase;">LOGOUT</a>
</body>
</html>