package com.servlet;

import com.dao.ProductDAO;
import com.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/AddProductServlet")
public class AddProductServlet extends HttpServlet {

    private final ProductDAO dao = new ProductDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
           // int    id       = Integer.parseInt(req.getParameter("productID").trim());
            String name     = req.getParameter("productName").trim();
            String category = req.getParameter("category").trim();
            double price    = Double.parseDouble(req.getParameter("price").trim());
            int    qty      = Integer.parseInt(req.getParameter("quantity").trim());

            Product p = new Product();
            p.setProductName(name);
            p.setCategory(category);
            p.setPrice(price);
            p.setQuantity(qty);
            int success = dao.addProduct(p);
if(success != -1)
{          req.setAttribute("message", " Product added successfully!");
            req.setAttribute("msgType",  "success" );}
else {
	req.setAttribute("message", " Failed to add product");
    req.setAttribute("msgType",  "error" );
}

        } catch (NumberFormatException e) {
            req.setAttribute("message", " Invalid input. Please check all fields.");
            req.setAttribute("msgType", "error");
        }
        int nextID =dao.getNextProductID();
    	req.setAttribute("nextID",nextID);
        req.getRequestDispatcher("Productadd.jsp").forward(req, resp);
      
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	int nextID =dao.getNextProductID();
    	req.setAttribute("nextID",nextID);
        req.getRequestDispatcher("Productadd.jsp").forward(req,resp);
    }
}

