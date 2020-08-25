package JdbcConnctionTest;

import java.sql.*;

public class JdbcTest {
    public static void main(String[] args) {
        try {
            Class.forName ("com.mysql.cj.jdbc.Driver");
//            DriverManager.registerDriver (new com.mysql.cj.jdbc.Driver ());
            Connection conn = DriverManager.getConnection ("jdbc:mysql://127.0.0.1:3306/bjpowernode","root","yaosen..");
            Statement stat = conn.createStatement ();
            int i = stat.executeUpdate ("insert into emp2 (empno,ename,sal)values(110,'小航',3500)");
            stat.executeUpdate ("delete from emp2 where ename is null or ename='小航'");
            System.out.println (i);
            ResultSet rs = stat.executeQuery ("select *from emp2");
            while(rs.next ()){
                System.out.print (rs.getString ("ename")+"|");
                System.out.print (rs.getString("empno")+"|");
                System.out.println (rs.getString("sal")+"|");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        } catch (ClassNotFoundException e) {
            e.printStackTrace ();
        }finally{

        }
    }
}
