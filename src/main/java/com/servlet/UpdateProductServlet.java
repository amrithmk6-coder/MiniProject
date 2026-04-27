package com.servlet;


import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import com.dao.ProductDAO;
import com.model.Product;

public class UpdateProductServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("productID"));
        String name = request.getParameter("productName");
        String category = request.getParameter("category");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        Product p = new Product();
        p.setProductID(id);
        p.setProductName(name);
        p.setCategory(category);
        p.setPrice(price);
        p.setQuantity(quantity);

        ProductDAO.updateProduct(p);

        response.sendRedirect("display");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("productupdate.jsp");
    }
}