package org.csu.backtopetstore.persistence.impl;

import org.csu.backtopetstore.domain.Category;
import org.csu.backtopetstore.persistence.CategoryDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
//接口的实现


public class CategoryDAOImpl implements CategoryDAO{
    private static final String GET_CATEGORY_LIST = "SELECT CATID AS categoryId,NAME,DESCN AS description FROM CATEGORY";
    private static final String GET_CATEGORY = " SELECT CATID AS categoryId,NAME,DESCN AS description FROM CATEGORY WHERE CATID =?";
    @Override
    public List<Category> getCategoryList() {
        List<Category> categoryList = new ArrayList<Category>();
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_CATEGORY_LIST);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Category category = new Category();
                category.setCategoryId(resultSet.getString(1));
                category.setName(resultSet.getString(2));
                category.setDescription(resultSet.getString(3));
                categoryList.add(category);
            }
            DBUtil.closeResultSet(resultSet);//关闭结果集
            DBUtil.closePreparedStatement(preparedStatement);//关闭执行对象
            DBUtil.closeConnection(connection);//关闭连接
        }catch (Exception e){
            e.printStackTrace();
        }
        return categoryList;
    }

    @Override
    public Category getCategory(String categoryId) {
        Category category = null;
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_CATEGORY);
            preparedStatement.setString(1,categoryId);//这里把句柄中的问号替换为了categoryId
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                category = new Category();
                category.setCategoryId(resultSet.getString( 1));
                category.setName(resultSet.getString(2));
                category.setDescription(resultSet.getString(3));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return category;
    }
}
