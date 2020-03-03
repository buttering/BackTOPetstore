package org.csu.backtopetstore.web.servlets;

import org.csu.backtopetstore.domain.Cart;
import org.csu.backtopetstore.domain.Item;
import org.csu.backtopetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AddItemToCartServlet")
public class AddItemToCartServlet extends HttpServlet {

    //1.处理完请求后的跳转页面
    private static final String VIEW_CART = "/WEB-INF/jsp/cart/Cart.jsp";

    //2。定义处理该请求所需要的数据
    private String workingItemId;
    private Cart cart;

    //3。是否需要调用业务逻辑层
    private CatalogService catalogService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        workingItemId = request.getParameter("workingItemId");

        HttpSession session = request.getSession();
        cart = (Cart)session.getAttribute("cart");

        if(cart == null){//第一次使用购物车
            cart = new Cart();
        }

        if(cart.containsItemId(workingItemId)){//购物车已经存在该类商品
            cart.incrementQuantityByItemId(workingItemId);
        }else{
            catalogService = new CatalogService();
            boolean isInStork = catalogService.isItemInStock(workingItemId);
            Item item = catalogService.getItem(workingItemId);
            cart.addItem(item,isInStork);
        }

        session.setAttribute("cart",cart);
        request.getRequestDispatcher(VIEW_CART).forward(request,response);
    }
}
