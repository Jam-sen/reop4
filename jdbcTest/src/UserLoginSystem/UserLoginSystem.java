package UserLoginSystem;

import jdk.swing.interop.SwingInterOpUtils;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;

public class UserLoginSystem {
    public static void main(String[] args) {
        Map<String,String> map = userInput();
        login(map);
    }

    /**
     * 用户登录
     * @param map 用户输入信息
     * @return 返回用户是否登陆成功
     */
    private static void login(Map<String, String> map) {
        String accountNum = map.get ("账号");
        String passWord = map.get("密码");
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        ResourceBundle bundle = ResourceBundle.getBundle ("sqlConnection");
        try {
            //1.注册驱动
            Class.forName ("com.mysql.cj.jdbc.Driver");
            //2.获取连接对象
            conn= DriverManager.getConnection (bundle.getString ("url"),bundle.getString ("user"),bundle.getString ("password"));
            //3.获取预先编译的数据库操作对象，并将sql的框子传入（？表示占位符）
            stat = conn.prepareStatement ("select *from t_user where loginName= ? and loginPwd= ?");
            //用setString方法给占位符赋值
            stat.setString (1,accountNum);
            stat.setString (2,passWord);
            //4.执行sql语句，返回查询结果集
            rs = stat.executeQuery ();
            //查到结果表示输入正确
            if (rs.next ()){
                System.out.println ("登陆成功");
                return ;
            }
            System.out.println ("账号或密码错误，登陆失败");

        } catch (ClassNotFoundException e) {
            e.printStackTrace ();
        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        }finally{
            try {
                rs.close ();
            } catch (SQLException throwables) {
                throwables.printStackTrace ();
            }
            try {
                stat.close ();
            } catch (SQLException throwables) {
                throwables.printStackTrace ();
            }

            try {
                conn.close ();
            } catch (SQLException throwables) {
                throwables.printStackTrace ();
            }
        }
    }

    /**
     * 用户输入信息
     * @return 返回用户输入的信息
     */
    private static Map<String, String> userInput() {
        Scanner s = new Scanner (System.in);
        System.out.println ("请输入账号");
        String accountNum = s.nextLine ();
        System.out.println ("请输入密码");
        String password = s.nextLine ();
        Map<String ,String> map = new HashMap<> ();
        map.put ("账号",accountNum);
        map.put ("密码",password);
        return map;
    }
}
