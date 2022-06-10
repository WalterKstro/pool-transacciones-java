package interfaces;

import model.Category;
import model.Product;
import connection.ConnectionDatabase;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CrudImplements implements InterfaceCrud<Product>{
    private List<Product> listProducts;

    public CrudImplements() {
        listProducts = new ArrayList<>();
    }

    private Connection getConnection () throws SQLException {
        return ConnectionDatabase.createConnection();
    }
    @Override
    public void read() throws SQLException {
        var query = "SELECT id_prod, name_prod,price_prod,sku_prod, id_cat, name_cat " +
                "    FROM products AS P LEFT JOIN categories AS C ON P.cat_id = C.id_cat";
        try (
                var statement = getConnection().prepareStatement(query);
                var results = statement.executeQuery();
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
    public void create(Product p) throws SQLException {
        var query = "UPDATE  products SET name_prod = ?, price_prod = ?, sku_prod=?, cat_id = ? WHERE id_prod = ?";
        if( p.getId() == null ){
            query = "INSERT INTO products (name_prod,price_prod,sku_prod,cat_id) VALUES (?,?,?,?)";
        }
        try(
                var statement = getConnection().prepareStatement( query );
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
    }
    @Override
    public void delete(Product p) throws SQLException {
        var query = "DELETE FROM products WHERE id_prod = ?";
        try (
                var statement = getConnection().prepareStatement(query)
        )
        {
            statement.setInt(1,p.getId());
            statement.executeUpdate();
        }
    }
    @Override
    public Product find(Product p) {
        var query = "SELECT id_prod, name_prod,price_prod,sku_prod, id_cat, name_cat  " +
                "FROM products AS P JOIN categories as C ON P.cat_id = C.id_cat " +
                "WHERE id_prod = ?";
        return p;
    }
}
