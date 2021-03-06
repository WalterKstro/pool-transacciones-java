package interfaces;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface InterfaceCrud <E> {
    void read() throws SQLException;
    E create(E e) throws SQLException;
    void delete (E e) throws SQLException;
    E find(E e) throws SQLException;
    void setConnection(Connection connection);
}
