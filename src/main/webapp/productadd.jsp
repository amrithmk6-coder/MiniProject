<html>
<head><title>Add Product</title></head>
<body>
<h2>Add Product</h2>

<form action="add" method="post">
    Name: <input type="text" name="productName" required><br><br>
    Category: <input type="text" name="category" required><br><br>
    Price: <input type="number" step="0.01" name="price" required><br><br>
    Quantity: <input type="number" name="quantity" required><br><br>

    <input type="submit" value="Add Product">
</form>

<br><a href="index.jsp">Back</a>
</body>
</html>