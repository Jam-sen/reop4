package JdbcConnctionTest;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import java.sql.*;
import java.util.Scanner;

public class PraparedStatementTest {
    public static void main(String[] args) {
        //1.测试Statement和preparedStatement区别
        //Statement具有可sql注入特点
        try {
            Scanner s = new Scanner (System.in);
            System.out.println ("输入1，正序排列；输入2，倒序排列");
            int i = s.nextInt ();
            String s1 = null;
            if(i == 1){
                s1=" asc";
            }else if (i == 2){
                s1=" desc";
            }
            Connection conn = DBUtils.getConnection ();
            Statement stat = conn.createStatement ();
            //以下代码中用户输入的信息中含有的sql语句表示正序或倒序排列
            ResultSet rs = stat.executeQuery ("select ename from emp order by ename"+s1);
            while(rs.next ()){
                System.out.println (rs.getString ("ename"));
            }
//            PreparedStatement ps = conn.prepareStatement ("select ename from emp order by ename ?");
//            ps.setString (1,s1);
//            ResultSet rs1 = ps.executeQuery ();
//            while (rs1.next ()){
//                rs1.getString ("ename");
//            }
//            DBUtils.close (conn,stat,rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        }
    }
}
