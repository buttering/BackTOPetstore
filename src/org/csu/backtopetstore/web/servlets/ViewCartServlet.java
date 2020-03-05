package org.csu.backtopetstore.web.servlets;

import org.csu.backtopetstore.domain.Cart;

import javax.servlet.HttpConstraintElement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ViewCartServlet")
public class ViewCartServlet extends HttpServlet {

    private static final String VIEW_CART = "/WEB-INF/jsp/cart/Cart.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart)session.getAttribute("cart");
        if(cart == null){
            cart = new Cart();
            session.setAttribute("cart",cart);
        }
        request.getRequestDispatcher(VIEW_CART).forward(request,response);
    }
}
