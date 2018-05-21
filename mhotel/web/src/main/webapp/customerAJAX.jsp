<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
  function loadCustomer() {
	  var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
		  if (this.readyState == 4 && this.status == 200) {
		    document.getElementById("demo").innerHTML = this.responseText;
		  }
	  };
	  xhttp.open("GET", "<%= request.getContextPath() %>/rs/customers/all", true);
	  xhttp.setRequestHeader("Content-type", "application/json");
	  xhttp.setRequestHeader("Accept", "application/json");

	  xhttp.send();
  }
</script>
</head>
<body>
  <div id="demo">NIMIC</div>
  <button onclick="loadCustomer()">Load customer</button>
</body>
</html>