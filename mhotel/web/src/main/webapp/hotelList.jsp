<%@page import="mhotel.model.Room"%>
<%@page import="mhotel.model.Hotel"%>
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
  <a href='<%= request.getContextPath() + "/"  %>'>HOME</a>
</div>
   Bine ai venit : <%= request.getUserPrincipal() %><BR>

  <B>Lista Hoteluri</B><br>
  <% List<Hotel> hotelList = (List<Hotel>)request.getAttribute("hotels"); %>
  <a  href='<%= request.getContextPath() + "/hotelAdd.jsp" %>'>Adaugare hotel</a>
  <ul>
  	<%
  	  for(Hotel hotel : hotelList) {
  	%>
  
    <li><p><%= hotel.getName() %>, <%= hotel.getRating() %>&nbsp;stele,<%= hotel.getAddress().getCity() %>,
        <%= hotel.getAddress().getStreet() %></p>
        <p>Camere&nbsp;<a href='<%= request.getContextPath() + "/roomAdd.jsp?hotel_id=" + hotel.getId()  %>'>Adaugare camera</a></p>
        <ol>
          <%
            for(Room room : hotel.getRooms()) {
          %>
             <li>numar: <%= room.getNumber() %>,etaj: <%= room.getFloor() %>,paturi: <%= room.getNumberOfBeds() %>
          <%
            }
          %>
        </ol>
        
    </li>
    <%
  	  }
    %>
  </ul>
</body>
</html>