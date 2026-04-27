package com.dao;


import java.sql.*;
import java.util.*;
import com.model.Product;

public class ProductDAO {

    private static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/ProductManagement",
            "root",
            "password"
        );
    }

    // ADD
    public static int addProduct(Product p) {
        int status = 0;
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO Products(ProductName, Category, Price, Quantity) VALUES (?, ?, ?, ?)"
            );
            ps.setString(1, p.getProductName());
            ps.setString(2, p.getCategory());
            ps.setDouble(3, p.getPrice());
            ps.setInt(4, p.getQuantity());

            status = ps.executeUpdate();
        } catch(Exception e) { e.printStackTrace(); }
        return status;
    }

    // UPDATE
    public static int updateProduct(Product p) {
        int status = 0;
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(
                "UPDATE Products SET ProductName=?, Category=?, Price=?, Quantity=? WHERE ProductID=?"
            );
            ps.setString(1, p.getProductName());
            ps.setString(2, p.getCategory());
            ps.setDouble(3, p.getPrice());
            ps.setInt(4, p.getQuantity());
            ps.setInt(5, p.getProductID());

            status = ps.executeUpdate();
        } catch(Exception e) { e.printStackTrace(); }
        return status;
    }

    // DELETE
    public static int deleteProduct(int id) {
        int status = 0;
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(
                "DELETE FROM Products WHERE ProductID=?"
            );
            ps.setInt(1, id);
            status = ps.executeUpdate();
        } catch(Exception e) { e.printStackTrace(); }
        return status;
    }

    // DISPLAY
    public static List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        try {
            Connection con = getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Products");

            while(rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("ProductID"));
                p.setProductName(rs.getString("ProductName"));
                p.setCategory(rs.getString("Category"));
                p.setPrice(rs.getDouble("Price"));
                p.setQuantity(rs.getInt("Quantity"));
                list.add(p);
            }
        } catch(Exception e) { e.printStackTrace(); }
        return list;
    }

    // REPORT: PRICE
    public static List<Product> getByPrice(double price) {
        List<Product> list = new ArrayList<>();
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM Products WHERE Price > ?"
            );
            ps.setDouble(1, price);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("ProductID"));
                p.setProductName(rs.getString("ProductName"));
                p.setCategory(rs.getString("Category"));
                p.setPrice(rs.getDouble("Price"));
                p.setQuantity(rs.getInt("Quantity"));
                list.add(p);
            }
        } catch(Exception e) { e.printStackTrace(); }
        return list;
    }

    // REPORT: CATEGORY
    public static List<Product> getByCategory(String category) {
        List<Product> list = new ArrayList<>();
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM Products WHERE Category=?"
            );
            ps.setString(1, category);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("ProductID"));
                p.setProductName(rs.getString("ProductName"));
                p.setCategory(rs.getString("Category"));
                p.setPrice(rs.getDouble("Price"));
                p.setQuantity(rs.getInt("Quantity"));
                list.add(p);
            }
        } catch(Exception e) { e.printStackTrace(); }
        return list;
    }

    // TOP N PRODUCTS
    public static List<Product> getTopProducts(int n) {
        List<Product> list = new ArrayList<>();
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM Products ORDER BY Quantity DESC LIMIT ?"
            );
            ps.setInt(1, n);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("ProductID"));
                p.setProductName(rs.getString("ProductName"));
                p.setCategory(rs.getString("Category"));
                p.setPrice(rs.getDouble("Price"));
                p.setQuantity(rs.getInt("Quantity"));
                list.add(p);
            }
        } catch(Exception e) { e.printStackTrace(); }
        return list;
    }
}