package org.csu.backtopetstore.service;

import org.csu.backtopetstore.domain.Category;
import org.csu.backtopetstore.domain.Product;
import org.csu.backtopetstore.domain.Item;
import org.csu.backtopetstore.persistence.CategoryDAO;
import org.csu.backtopetstore.persistence.ItemDAO;
import org.csu.backtopetstore.persistence.ProductDAO;
import org.csu.backtopetstore.persistence.impl.CategoryDAOImpl;
import org.csu.backtopetstore.persistence.impl.ItemDAOImpl;
import org.csu.backtopetstore.persistence.impl.ProductDAOImpl;

import java.util.List;

public class CatalogService {
    private CategoryDAO categoryDAO;//业务逻辑层调用DAO层
    private ProductDAO productDAO;
    private ItemDAO itemDAO;
    public CatalogService(){
        categoryDAO = new CategoryDAOImpl();//在构造方法中调用DAO的实现
        productDAO = new ProductDAOImpl();
        itemDAO = new ItemDAOImpl();
    }

    public List<Category> getCategoryList() {
        return this.categoryDAO.getCategoryList();
    }

    public Category getCategory(String categoryId) {
        return this.categoryDAO.getCategory(categoryId);
    }

    public Product getProduct(String productId) {
        return this.productDAO.getProduct(productId);
    }

    public List<Product> getProductListByCategory(String categoryId) {
        return this.productDAO.getProductListByCategory(categoryId);
    }

    public List<Product> searchProductList(String keyword) {
        return this.productDAO.searchProductList("%" + keyword.toLowerCase() + "%");
    }
    public List<Item> getItemListByProduct(String productId) {
        return this.itemDAO.getItemListByProduct(productId);
    }

    public Item getItem(String itemId) {
        return this.itemDAO.getItem(itemId);
    }

    public boolean isItemInStock(String itemId) {
        return this.itemDAO.getInventoryQuantity(itemId) > 0;
    }//item是否有库存
}
