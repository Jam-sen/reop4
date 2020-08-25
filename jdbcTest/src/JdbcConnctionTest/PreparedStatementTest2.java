package JdbcConnctionTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparedStatementTest2 {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            conn = DBUtils.getConnection ();
            conn.setAutoCommit (false);//1.关闭jdbc默认的事务自动提交

            //增 insert
            PreparedStatement ps = conn.prepareStatement ("insert into emp(empno,ename,sal)values(?,?,?)");
            ps.setInt (1,123);
            ps.setString (2,"李四");
            ps.setInt (3,2365);
            ps.executeUpdate ();
            //删 delete
            ps = conn.prepareStatement ("delete from emp where ename=?");
            ps.setString (1,"SMITH");
            ps.executeUpdate ();
            //改 update
            ps = conn.prepareStatement ("update emp set sal=5000 where empno=?");
            ps.setInt (1,7876);
            ps.executeUpdate ();
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
