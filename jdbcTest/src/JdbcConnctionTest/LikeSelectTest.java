package JdbcConnctionTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LikeSelectTest {
    public static void main(String[] args) {
        try {
            Connection conn = DBUtils.getConnection ();
            //利用模糊查询，查询出第二个字母是A的人名
            PreparedStatement ps = conn.prepareStatement ("select *from emp where ename like ?");
            ps.setString (1,"_A%");
            ResultSet rs = ps.executeQuery ();
            while (rs.next ()){
                System.out.println (rs.getString ("ename"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        }

    }
}
