package main;


import model.Category;
import model.Product;
import service.InterfaceService;
import service.ServiceImplement;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        InterfaceService services = new ServiceImplement();
        /*A TRANSACTION*/
        services.transacction(
                new Product("Llanta de 32 pulgadas",350.50F,"AT-05"),
                new Category("Automotriz")
        );
        /*READ*/
        services.readProducts();
        services.readCategories();
    }
}
