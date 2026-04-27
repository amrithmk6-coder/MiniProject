<%
String type = request.getParameter("type");
%>

<html>
<head><title>Report Input</title></head>
<body>

<h2>Enter Criteria</h2>

<form action="report" method="post">

<input type="hidden" name="type" value="<%= type %>">

<%
if ("price".equals(type)) {
%>
    Enter Price: <input type="number" step="0.01" name="price" required>
<%
} else if ("category".equals(type)) {
%>
    Enter Category: <input type="text" name="category" required>
<%
} else if ("top".equals(type)) {
%>
    Enter Top N: <input type="number" name="topN" required>
<%
}
%>

<br><br>
<input type="submit" value="Generate Report">

</form>

</body>
</html>