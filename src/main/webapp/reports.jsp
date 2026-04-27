<html>
<head><title>Reports</title></head>
<body>

<h2>Select Report</h2>

<form action="reportCriteria" method="post">
    <input type="radio" name="reportType" value="price" required> Price Greater Than<br>
    <input type="radio" name="reportType" value="category"> Category Wise<br>
    <input type="radio" name="reportType" value="top"> Top N Products<br><br>

    <input type="submit" value="Next">
</form>

<br><a href="index.jsp">Back</a>

</body>
</html>