package org.csu.backtopetstore.web.servlets;

import org.csu.backtopetstore.domain.Item;
import org.csu.backtopetstore.domain.Product;
import org.csu.backtopetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "VIewItemServlet")
public class ViewItemServlet extends HttpServlet {

    private String itemId;
    private String productId;
    private static final String VIEW_ITEM = "/WEB-INF/jsp/catalog/Item.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        itemId = request.getParameter("itemId");
        CatalogService catalogService = new CatalogService();
        Item item = catalogService.getItem(itemId);
        Product product = catalogService.getProduct(item.getProductId());

        HttpSession session = request.getSession();
        session.setAttribute("item",item);
        session.setAttribute("product",product);

        request.getRequestDispatcher(VIEW_ITEM).forward(request,response);
    }
}
