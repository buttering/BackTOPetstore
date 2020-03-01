package org.csu.backtopetstore.web.servlets;

import org.csu.backtopetstore.domain.Category;
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

@WebServlet(name = "Servlet")
public class ViewCategoryServlet extends HttpServlet {

    private String categoryId;
    private static final String VIEW_CATEGORY= "/WEB-INF/jsp/catalog/Category.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        categoryId = request.getParameter("categoryId");//接收request带来的参数
        CatalogService catalogService = new CatalogService();//调用业务逻辑层
        Category category = catalogService.getCategory(categoryId);//获取数据
        List<Product>productList = catalogService.getProductListByCategory(categoryId);

        HttpSession session = request.getSession();//把获取的数据放到会话作用域中
        session.setAttribute("category",category);
        session.setAttribute("productList",productList);

        request.getRequestDispatcher(VIEW_CATEGORY).forward(request,response);
    }
}
