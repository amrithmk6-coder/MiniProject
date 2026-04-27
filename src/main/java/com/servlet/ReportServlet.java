package com.servlet;


import java.io.IOException;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import com.dao.ProductDAO;
import com.model.Product;

public class ReportServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String type = request.getParameter("type");
        List<Product> list = null;

        if (type.equals("price")) {
            double price = Double.parseDouble(request.getParameter("price"));
            list = ProductDAO.getByPrice(price);
        } 
        else if (type.equals("category")) {
            String category = request.getParameter("category");
            list = ProductDAO.getByCategory(category);
        } 
        else if (type.equals("top")) {
            int n = Integer.parseInt(request.getParameter("topN"));
            list = ProductDAO.getTopProducts(n);
        }

        request.setAttribute("productList", list);

        RequestDispatcher rd = request.getRequestDispatcher("report_result.jsp");
        rd.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("reports.jsp");
    }
}