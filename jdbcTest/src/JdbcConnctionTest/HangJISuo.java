package JdbcConnctionTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HangJISuo {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            conn = DBUtils.getConnection ();
            conn.setAutoCommit (false);
            //添加行级锁
            PreparedStatement ps = conn.prepareStatement ("select *from emp where empno in(7876,7499) for update");
            ResultSet rs = ps.executeQuery ();
            while(rs.next ()){
                System.out.println (rs.getString ("ename")+","+rs.getString ("empno"));

            }

            conn.commit ();//2.能执行到此处说明以上sql语句都执行成功，便commit提交（事务结束）
            DBUtils.close (conn,ps,null);
        } catch (SQLException throwables) {
            if (conn!=null){
                try {
                    conn.rollback ();//3.如果事务执行失败则回滚（事务结束）
                } catch (SQLException e) {
                    e.printStackTrace ();
                }
            }
            throwables.printStackTrace ();
        }
    }
}
