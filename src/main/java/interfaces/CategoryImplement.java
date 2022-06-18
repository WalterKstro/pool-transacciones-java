package interfaces;

import model.Category;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryImplement implements InterfaceCrud<Category>{
    private Connection connection;
    private List<Category> list;

    public CategoryImplement() {
        this.list = new ArrayList<>();
    }
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    @Override
    public void read() throws SQLException {
        try (
                var statement = connection.createStatement();
                var results = statement.executeQuery("SELECT * FROM categories");
                ){
            while ( results.next() ) {
                list.add(
                  new Category(results.getInt(1),results.getString(2))
                );
            }
            list.stream().forEach(System.out::println);
        }
    }

    @Override
    public Category create(Category c) throws SQLException {
        var query = "UPDATE  categories SET name_cat = ? WHERE id_cat = ?";
        if( c.getId() == null ){
            query = "INSERT INTO categories (name_cat) VALUES (?)";
        }
        try (
                var statement = connection.prepareStatement(
                        query,
                        Statement.RETURN_GENERATED_KEYS
                );
                ){
            statement.setString(1, c.getName());
            if ( c.getId() != null ) {
                statement.setInt(2, c.getId());
            }
            statement.executeUpdate();
            if( c.getId() == null ) {

                try(var rs = statement.getGeneratedKeys();){
                    if( rs.next() ) {
                        c.setId( rs.getInt(1) );
                    }
                }

            }
        }
        return c;
    }

    @Override
    public void delete(Category c) throws SQLException {
        var query = "DELETE FROM categories WHERE id_cat = ?";
        try (
                var statement = connection.prepareStatement(query)
        )
        {
            statement.setInt(1,c.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public Category find(Category c) throws SQLException {
        var query = "SELECT id_cat, name_cat FROM categories WHERE id_cat = ?";
        try (
                var statement = connection.prepareStatement(query);
        ){

            statement.setInt(1, c.getId());
            var result = statement.executeQuery();
            result.next();
            c.setId( result.getInt(1) );
            c.setName( result.getString(2) );
        }
        return c;
    }
}
