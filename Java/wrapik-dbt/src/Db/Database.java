package Db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {
    protected Connection connection;
    protected Query query;

    public Database(String db, String username, String password) throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost/"+db, username, password);
    }

    private int query(String query, Object[] params) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        if (params != null){
            int index = 1;
            for (Object param : params) {
                preparedStatement.setObject(index, param);
                index++;
            }
        }
        return preparedStatement.executeUpdate();
    }

    public int delete(String table, String condition, Object[] params) throws SQLException {
        query = new Query();
        query.delete(table).where(condition);
        return query(query.getQuery(), params);
    }

    public int select(String table, String[] collums, Object[] params, Object[] objects) throws SQLException {
        return this.select(table, collums, null, params);
    }

    public int insert(String table, Object[] params) throws SQLException {
        query = new Query();
        query.insert(table).values(params);
        return query(query.getQuery(), params);
    }

    public int update(String table, String[] collums, String condition, Object[] params) throws SQLException {
        query = new Query();
        query.update(table).where(condition);
        return query(query.getQuery(), params);
    }
}
