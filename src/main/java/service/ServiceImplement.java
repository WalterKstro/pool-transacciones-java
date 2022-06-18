package service;

import connection.ConnectionDatabase;
import interfaces.CategoryImplement;
import interfaces.InterfaceCrud;
import interfaces.ProductImplement;
import model.Category;
import model.Product;

import java.sql.SQLException;

public class ServiceImplement implements InterfaceService{
    private InterfaceCrud<Product> repositoryProduct;
    private InterfaceCrud<Category> repositoryCategory;

    public ServiceImplement() {
        this.repositoryProduct = new ProductImplement();
        this.repositoryCategory = new CategoryImplement();
    }

    @Override
    public void readProducts() throws SQLException {
        try (var connection = ConnectionDatabase.getConnect())
        {
            repositoryProduct.setConnection( connection );
            repositoryProduct.read();
        }
    }

    @Override
    public Product createProduct(Product p) throws SQLException {
        try (var connection = ConnectionDatabase.getConnect())
        {
            repositoryProduct.setConnection( connection );
            if ( connection.getAutoCommit() ) {
                connection.setAutoCommit(false);
            }

            try{
                p =  repositoryProduct.create(p);
                connection.commit();
            }catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            }
            return p;
        }
    }

    @Override
    public void deleteProduct(Product p) throws SQLException {
        try (var connection = ConnectionDatabase.getConnect())
        {
            repositoryProduct.setConnection( connection );
            if ( connection.getAutoCommit() ) {
                connection.setAutoCommit(false);
            }

            try{
                repositoryProduct.delete(p);
                connection.commit();
            }catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            }
        }

    }

    @Override
    public Product findProduct(Product p) throws SQLException {
        try (var connection = ConnectionDatabase.getConnect())
        {
            repositoryProduct.setConnection( connection );
            return repositoryProduct.find(p);
        }
    }

    @Override
    public void readCategories() throws SQLException {
        try (var connection = ConnectionDatabase.getConnect())
        {
            repositoryCategory.setConnection( connection );
            repositoryCategory.read();
        }

    }

    @Override
    public Category createCategory(Category c) throws SQLException {
        try (var connection = ConnectionDatabase.getConnect())
        {
            repositoryCategory.setConnection( connection );

            if ( connection.getAutoCommit() ) {
                connection.setAutoCommit(false);
            }

            try{
                c =  repositoryCategory.create(c);
                connection.commit();
            }catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            }
            return c;
        }
    }

    @Override
    public void deleteCategory(Category c) throws SQLException {
        try (var connection = ConnectionDatabase.getConnect())
        {
            repositoryCategory.setConnection( connection );
            if ( connection.getAutoCommit() ) {
                connection.setAutoCommit(false);
            }

            try{
                repositoryCategory.delete(c);
                connection.commit();
            }catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            }
        }

    }

    @Override
    public Category findCategory(Category c) throws SQLException {
        try (var connection = ConnectionDatabase.getConnect())
        {
            repositoryCategory.setConnection( connection );
            return repositoryCategory.find(c);
        }
    }

    @Override
    public void transacction(Product p, Category c) throws SQLException {
        try (var connection = ConnectionDatabase.getConnect())
        {
            repositoryCategory.setConnection( connection );
            repositoryProduct.setConnection( connection );

            if( connection.getAutoCommit() ) {
                connection.setAutoCommit(false);
            }

            try {
                var newCategory = repositoryCategory.create(c);
                p.setCategory(newCategory);
                var newProduct = repositoryProduct.create(p);
                connection.commit();
            }catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            }
        }
    }
}
