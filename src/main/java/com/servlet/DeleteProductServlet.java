package com.servlet;


import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import com.dao.ProductDAO;

public class DeleteProductServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("productID"));
        ProductDAO.deleteProduct(id);

        response.sendRedirect("display");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        ProductDAO.deleteProduct(id);

        response.sendRedirect("display");
    }
}