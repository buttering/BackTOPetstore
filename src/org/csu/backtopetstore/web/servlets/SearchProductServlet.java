package org.csu.backtopetstore.web.servlets;

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

@WebServlet(name = "SearchProductServlet")
public class SearchProductServlet extends HttpServlet {

    private String keyword;
    private static String SEARCH_PRODUCT = "WEB-INF/jsp/catalog/SearchProducts.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        keyword = request.getParameter("keyword");
        List<Product> productList = new ArrayList<Product>();
        CatalogService catalogService = new CatalogService();
        productList = catalogService.searchProductList(keyword);

        HttpSession session = request.getSession();
        session.setAttribute("productList",productList);

        request.getRequestDispatcher(SEARCH_PRODUCT).forward(request,response);
    }
}
