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
import java.util.List;

@WebServlet(name = "ViewServlet")
public class ViewProductServlet extends HttpServlet {

    private String productId;
    private static final String VIEW_PRODUCT = "/WEB-INF/jsp/catalog/Product.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        productId = request.getParameter("productId");
        CatalogService catalogService = new CatalogService();
        Product product = catalogService.getProduct(productId);
        List<Item> itemList = catalogService.getItemListByProduct(productId);

        HttpSession session = request.getSession();
        session.setAttribute("product",product);
        session.setAttribute("itemList",itemList);

        request.getRequestDispatcher(VIEW_PRODUCT).forward(request,response);
    }
}
