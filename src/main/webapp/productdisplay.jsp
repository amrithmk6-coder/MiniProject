<%@ page import="java.util.*, com.model.Product" %>
<html>
<head><title>Display Products</title></head>
<body>

<h2>Product List</h2>

<table border="1">
<tr>
    <th>ID</th>
    <th>Name</th>
    <th>Category</th>
    <th>Price</th>
    <th>Quantity</th>
</tr>

<%
List<Product> list = (List<Product>) request.getAttribute("productList");
if (list != null) {
    for (Product p : list) {
%>
<tr>
    <td><%= p.getProductID() %></td>
    <td><%= p.getProductName() %></td>
    <td><%= p.getCategory() %></td>
    <td><%= p.getPrice() %></td>
    <td><%= p.getQuantity() %></td>
</tr>
<%
    }
}
%>

</table>

<br><a href="index.jsp">Back</a>
</body>
</html>