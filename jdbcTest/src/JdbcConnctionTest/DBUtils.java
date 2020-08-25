package JdbcConnctionTest;

import java.sql.*;
import java.util.ResourceBundle;

public class DBUtils {
    private DBUtils(){

    }
    static{
        try {
            Class.forName ("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace ();
        }
    }
    static Connection getConnection() throws SQLException {
        ResourceBundle bundle = ResourceBundle.getBundle ("sqlConnection");
        return DriverManager.getConnection (bundle.getString ("url"),bundle.getString ("user"),bundle.getString ("password"));
    }
    static void close(Connection conn, Statement state, ResultSet rs){
        if (rs != null) {
            try {
                rs.close ();
            } catch (SQLException throwables) {
                throwables.printStackTrace ();
            }
        }
        if (state != null){
            try {
                state.close ();
            } catch (SQLException throwables) {
                throwables.printStackTrace ();
            }
        }
        if (conn != null) {
            try {
                conn.close ();
            } catch (SQLException throwables) {
                throwables.printStackTrace ();
            }
        }
    }
}
