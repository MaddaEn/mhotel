<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 <form action="room/add" method="post">
      <b>Numar camera:</b><input type="text" name="nr_camera"><br>
      <b>Etaj:</b><input type="number" name="etaj"><br>
      <b>Numar paturi:</b><input type="number" name="paturi"><br>
      <input type="hidden" name="hotel_id" value="<%= request.getParameter("hotel_id") %>">
  	  <button type="submit">Salveaza</button>
    </form>
</body>
</html>