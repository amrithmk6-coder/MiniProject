<html>
<head><title>Update Product</title></head>
<body>
<h2>Update Product</h2>

<form action="update" method="post">
    Product ID: <input type="number" name="productID" required><br><br>
    Name: <input type="text" name="productName" required><br><br>
    Category: <input type="text" name="category" required><br><br>
    Price: <input type="number" step="0.01" name="price" required><br><br>
    Quantity: <input type="number" name="quantity" required><br><br>

    <input type="submit" value="Update Product">
</form>

<br><a href="index.jsp">Back</a>
</body>
</html>