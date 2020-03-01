package org.csu.backtopetstore.persistence;

import org.csu.backtopetstore.domain.Category;
import java.util.List;
//接口
public interface CategoryDAO {
    //获得所有的大类，以集合返回
    List<Category> getCategoryList();
    //通过编号获得某一个商品
    Category getCategory(String categoryId);
}
