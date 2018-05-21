<%@page import="mhotel.model.Customer"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
  .title {
    background-color: #FF0000;
  }
</style>
</head>
<body>
	<div>
		<a href='<%=request.getContextPath() + "/"%>'>HOME</a>
	</div>


	<div>
		<%
			List<Customer> customerList = (List<Customer>) request.getAttribute("customers");
		%>
		<B><I>Total clienti : <%=customerList.size()%></I></B> <br> <a
			href='<%=request.getContextPath() + "/customerAdd.jsp"%>'>Adaugare
			client</a>
		<hr>
		<H2>Lista clienti hoteluri</H2>
		<table>
			<tr class="title">
				<th >Nume</th>
				<th>ID</th>
				<th>Tara</th>
				<th>Oras</th>
			</tr>
			<%
				for (Customer customer : customerList) {
			%>
			<tr >
				<td><%=customer.getName()%></td>
				<td><%=customer.getLegalIdType() + ":" + customer.getLegalId()%>
				</td>
				<td><%=customer.getAddress().getCountry()%></td>
				<td><%=customer.getAddress().getCity()%></td>
				<td><a
					href='<%=request.getContextPath() + "/hotel/checkin?customer_id=" + customer.getId()%>'>Check
						In</a></td>
			</tr>

			<%
				}
			%>
		</table>

	</div>
</body>
</html>