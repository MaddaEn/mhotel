<%@page import="mhotel.model.CustomerRecord"%>
<%@page import="mhotel.model.Room"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<a href='<%=request.getContextPath() + "/"%>'>HOME</a>
	</div>
	<div>
		<%
			List<Room> freeRoomList = (List<Room>) request.getAttribute("rooms");
			List<CustomerRecord> activeCheckins = (List<CustomerRecord>) request.getAttribute("activeCheckins");
		%>

		<%
			if (activeCheckins.size() == 0) {
		%>
		NO ACTIVE CHECKINS
		<%
			} else {
		%>
		ACTIVE CHECKINS
		<ul>
			<%
				for (CustomerRecord cr : activeCheckins) {
			%>
			<li>paturi:<%=cr.getRoom().getNumberOfBeds()%>,camera: <%=cr.getRoom().getNumber()%>
				<%
					}
				%>
		</ul>

		<%
			}
		%>


		<H2>Lista camere libere</H2>
		<ol>

			<%
				for (Room room : freeRoomList) {
			%>
			<li><%=room.getHotel().getName()%> : <%=room.getHotel().getAddress().getCity()%>
				- numar: <%=room.getNumber()%> , etaj: <%=room.getFloor()%>,
				paturi: <%=room.getNumberOfBeds()%> <br>
				<form action='<%=request.getContextPath() + "/checkin"%>'
					method="post">
					<input type="hidden" name="room_id" value="<%=room.getId()%>">
					<input type="hidden" name="cust_id"
						value="<%=request.getParameter("customer_id")%>">

					<button type="submit">CHECK IN</button>
				</form></li>

			<%
				}
			%>
		</ol>
		<B><I>Total camere libere : <%=freeRoomList.size()%></I></B>
	</div>
</body>
</html>