package org.csu.backtopetstore.web.servlets;

import org.csu.backtopetstore.domain.Cart;
import org.csu.backtopetstore.domain.CartItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;

@WebServlet(name = "UpdateCartQuantitiesServlet")
public class UpdateCartQuantitiesServlet extends HttpServlet {

    private static final String VIEW_CART = "/WEB-INF/jsp/cart/Cart.jsp";
    private String workingItemId;
    private Cart cart;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        workingItemId = request.getParameter("workingItemId");
        HttpSession session = request.getSession();
        cart = (Cart) session.getAttribute("cart");
        Iterator cartItems = cart.getAllCartItems();

        while (cartItems.hasNext()){
            CartItem cartItem = (CartItem)cartItems.next();
            String itemId = cartItem.getItem().getItemId();

            try{
                int quantity = Integer.parseInt((request.getParameter(itemId)));
                cart.setQuantityByItemId(itemId,quantity);
                if(quantity <1){
                    cartItems.remove();//删除迭代器当前元素
                }
            }catch (Exception e){
                e.printStackTrace();
            }

            session.setAttribute("cart",cart);
            request.getRequestDispatcher(VIEW_CART).forward(request,response);
        }



    }
}
