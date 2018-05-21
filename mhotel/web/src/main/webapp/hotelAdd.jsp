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
    <form action="hotel/add" method="post">
      <b>Nume:</b><input type="text" name="nume"><br>
      <b>Rating:</b><input type="number" name="rating"><br>
  	  <b>Adresa</b><br>
  	  <b>Tara:</b><input type="text" name="addr_country"><br>
  	  <b>Oras:</b><input type="text" name="addr_city"><br>
  	  <b>Strada:</b><input type="text" name="addr_street"><br>
  	  <b>Numar:</b><input type="text" name="addr_nbr"><br>
  	  <b>Cod postal:</b><input type="text" name="addr_cp"><br>
  	  <button type="submit">Salveaza</button>
    </form>
  </div>
</body>
</html>