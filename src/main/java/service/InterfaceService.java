package service;

import model.Category;
import model.Product;

import java.sql.SQLException;

public interface InterfaceService {
    /*METHODS OF PRODUCTS*/
    void readProducts() throws SQLException;
    Product createProduct(Product p) throws SQLException;
    void deleteProduct (Product p) throws SQLException;
    Product findProduct(Product p) throws SQLException;

    /*METHODS OF CATEGORIES*/
    void readCategories() throws SQLException;
    Category createCategory(Category c) throws SQLException;
    void deleteCategory (Category c) throws SQLException;
    Category findCategory(Category c) throws SQLException;

    /*METHODS OF TRANSACTION*/
    void transacction(Product p, Category c) throws SQLException;
}
