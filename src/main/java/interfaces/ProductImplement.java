package interfaces;

import model.Category;
import model.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductImplement implements InterfaceCrud<Product>{
    private List<Product> listProducts;
    private Connection connection;


    public ProductImplement() {
        listProducts = new ArrayList<>();
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void read() throws SQLException {
        var query = "SELECT id_prod, name_prod,price_prod,sku_prod, id_cat, name_cat " +
                "    FROM products AS P LEFT JOIN categories AS C ON P.cat_id = C.id_cat";
        try (
                var statement = connection.createStatement();
                var results = statement.executeQuery(query);
                ){
            while ( results.next() ) {
                    listProducts.add(
                            new Product(
                                    results.getInt(1),
                                    results.getString(2),
                                    results.getFloat(3),
                                    results.getString(4),
                                    new Category( results.getInt(5), results.getString(6) )
                            )
                    );
                };
            }
        listProducts.stream().forEach(System.out::println );
    }

        @Override
    public Product create(Product p) throws SQLException {
        var query = "UPDATE  products SET name_prod = ?, price_prod = ?, sku_prod=?, cat_id = ? WHERE id_prod = ?";
        if( p.getId() == null ){
            query = "INSERT INTO products (name_prod,price_prod,sku_prod,cat_id) VALUES (?,?,?,?)";
        }
        try(
                var statement = connection.prepareStatement( query );
                ) {

            statement.setString(1, p.getName() );
            statement.setFloat( 2, p.getPrice() );
            statement.setString(3, p.getSku());
            statement.setInt( 4, p.getCategory().getId() );

            if ( p.getId() != null ) {
                statement.setInt(5, p.getId());
            }

            statement.executeUpdate();

        }
        return p;
    }
    @Override
    public void delete(Product p) throws SQLException {
        var query = "DELETE FROM products WHERE id_prod = ?";
        try (
                var statement = connection.prepareStatement(query)
        )
        {
            statement.setInt(1,p.getId());
            statement.executeUpdate();
        }
    }
    @Override
    public Product find(Product p) throws SQLException {
        var query = "SELECT id_prod, name_prod,price_prod,sku_prod, id_cat, name_cat  " +
                "FROM products AS P JOIN categories as C ON P.cat_id = C.id_cat " +
                "WHERE id_prod = ?";
        try (
                var statement = connection.prepareStatement(query);
                ){

            statement.setInt(1, p.getId());
            var results = statement.executeQuery();
            results.next();
            p.setName( results.getString(2) );
            p.setPrice( results.getFloat(3) );
            p.setSku( results.getString(4) );
            p.setCategory( new Category(
                    results.getInt(5),
                    results.getString(6) )
            );

        }
        return p;
    }
}
