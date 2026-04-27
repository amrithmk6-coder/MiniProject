package com.servlet;


import java.io.IOException;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import com.dao.ProductDAO;
import com.model.Product;

public class DisplayProductsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Product> list = ProductDAO.getAllProducts();
        request.setAttribute("productList", list);

        RequestDispatcher rd = request.getRequestDispatcher("productdisplay.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}