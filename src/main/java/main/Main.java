package main;


import interfaces.CategoryImplement;
import model.Category;
import model.Product;
import connection.ConnectionDatabase;
import interfaces.ProductImplement;
import interfaces.InterfaceCrud;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        try ( var connection = ConnectionDatabase.getConnect();){
            if( connection.getAutoCommit() ) {
                connection.setAutoCommit(false);
            }

            try {
                InterfaceCrud product = new ProductImplement( connection );
                InterfaceCrud category = new CategoryImplement( connection );

                // CREATE
                //var cat = (Category) category.create(new Category("Higiene"));
                // DELETE
                //category.delete(new Category(3));
                // FIND
                //System.out.println(category.find(new Category(5)));

                // UPDATE
                //product.create(new Product(5,"Crema Nivea",12.60f,"B-066",new Category(7)));
                // CREATE
                product.create(new Product("Smartphone POCO",2700.60f,"TI-16",new Category(1)));
                // DELETE
                //product.delete( new Product(7) );
                // FIND
                //System.out.println(product.find(new Product(6)));


                // READ CATEGORIES AND PRODUCTS
                product.read();
                category.read();

                connection.commit();
            }catch (SQLException ex){
                ex.printStackTrace();
                connection.rollback();
            }
        }
    }
}
