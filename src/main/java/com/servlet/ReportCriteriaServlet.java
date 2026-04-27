package com.servlet;


import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

public class ReportCriteriaServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String type = request.getParameter("reportType");

        if (type.equals("price")) {
            request.getRequestDispatcher("report_form.jsp?type=price").forward(request, response);
        } 
        else if (type.equals("category")) {
            request.getRequestDispatcher("report_form.jsp?type=category").forward(request, response);
        } 
        else if (type.equals("top")) {
            request.getRequestDispatcher("report_form.jsp?type=top").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("reports.jsp");
    }
}