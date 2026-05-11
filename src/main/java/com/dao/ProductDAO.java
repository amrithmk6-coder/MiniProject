package com.dao;

import com.model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private static final String DB_URL      = "jdbc:mysql://localhost:3306/productdb";
    private static final String DB_USER     = "root";
    private static final String DB_PASSWORD = "password";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found.", e);
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    // ─── ADD ────────────────────────────────────────────────────────────────────
    public int addProduct(Product p) {
        String sql = "INSERT INTO Products ( ProductName, Category, Price, Quantity) VALUES (?,?,?,?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql , Statement.RETURN_GENERATED_KEYS)) {
          //  ps.setInt(1, p.getProductID());
            ps.setString(1, p.getProductName());
            ps.setString(2, p.getCategory());
            ps.setDouble(3, p.getPrice());
            ps.setInt(4, p.getQuantity());
            return ps.executeUpdate() ;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    public int getNextProductID() {
    	String sql ="SELECT coalesce(max(ProductID),0) + 1 as NextID from products";
    	try (Connection con = getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql)) {
       if (rs.next()) return rs.getInt("NextID");
   } catch (SQLException e) {
       e.printStackTrace();
   }
   return 1;
}

    // ─── UPDATE ─────────────────────────────────────────────────────────────────
    public boolean updateProduct(Product p) {
        String sql = "UPDATE Products SET ProductName=?, Category=?, Price=?, Quantity=? WHERE ProductID=?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getProductName());
            ps.setString(2, p.getCategory());
            ps.setDouble(3, p.getPrice());
            ps.setInt(4, p.getQuantity());
            ps.setInt(5, p.getProductID());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ─── DELETE ─────────────────────────────────────────────────────────────────
    public boolean deleteProduct(int productID) {
        String sql = "DELETE FROM Products WHERE ProductID=?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, productID);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ─── GET ALL ─────────────────────────────────────────────────────────────────
    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Products ORDER BY ProductID";
        try (Connection con = getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // ─── GET BY ID ───────────────────────────────────────────────────────────────
    public Product getProductByID(int productID) {
        String sql = "SELECT * FROM Products WHERE ProductID=?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, productID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ─── REPORT: Price > threshold ───────────────────────────────────────────────
    public List<Product> getProductsAbovePrice(double minPrice) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Products WHERE Price > ? ORDER BY Price DESC";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, minPrice);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // ─── REPORT: By Category ────────────────────────────────────────────────────
    public List<Product> getProductsByCategory(String category) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Products WHERE Category=? ORDER BY ProductName";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, category);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // ─── REPORT: Top N by Quantity ──────────────────────────────────────────────
    public List<Product> getTopNProducts(int n) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Products ORDER BY Quantity DESC LIMIT ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, n);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // ─── Get distinct categories ─────────────────────────────────────────────────
    public List<String> getAllCategories() {
        List<String> cats = new ArrayList<>();
        String sql = "SELECT DISTINCT Category FROM Products ORDER BY Category";
        try (Connection con = getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) cats.add(rs.getString("Category"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cats;
    }

    // ─── Helper ──────────────────────────────────────────────────────────────────
    private Product mapRow(ResultSet rs) throws SQLException {
        return new Product(
            rs.getInt("ProductID"),
            rs.getString("ProductName"),
            rs.getString("Category"),
            rs.getDouble("Price"),
            rs.getInt("Quantity")
        );
    }
}

