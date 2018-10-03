package phoenix;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class connPhoenix {
    private static String driver = "org.apache.phoenix.jdbc.PhoenixDriver";

    public static void main(String[] args) throws SQLException {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Statement stmt = null;
        ResultSet rs = null;

        Connection con = DriverManager.getConnection("jdbc:phoenix:namenode:2181");
        stmt = con.createStatement();
        String sql = "select * from users";
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.print("id:"+rs.getString("id"));
            System.out.println(",name:"+rs.getString("name"));
        }
        stmt.close();
        con.close();
    }
}