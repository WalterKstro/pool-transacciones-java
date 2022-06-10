package main;


import model.Category;
import model.Product;
import connection.ConnectionDatabase;
import interfaces.CrudImplements;
import interfaces.InterfaceCrud;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        try ( var connection = ConnectionDatabase.createConnection();){
            if( connection.getAutoCommit() ) {
                connection.setAutoCommit(false);
            }

            try {
                InterfaceCrud crud = new CrudImplements();
                // UPDATE
                crud.create(new Product(5,"Mouse Ryzer",650.60f,"E-056",new Category(1)));
                // CREATE
                crud.create(new Product("Silla Gamer XRider",2700.60f,"TI-16",new Category(3)));
                // DELETE
                crud.delete( new Product(7) );
                // READ
                crud.read();

                connection.commit();
            }catch (SQLException ex){
                ex.printStackTrace();
                connection.rollback();
            }
        }
    }
}
