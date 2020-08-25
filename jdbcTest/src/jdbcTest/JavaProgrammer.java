package jdbcTest;

import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

public class JavaProgrammer {
    public static void main(String[] args) {
        ResourceBundle Bundle = ResourceBundle.getBundle ("className");
        String s = Bundle.getString ("className");
        try {
            JDBC jdbc = (JDBC) Class.forName (s).getConstructor ().newInstance ();
            jdbc.Connectivity ();
        } catch (ClassNotFoundException e) {
            e.printStackTrace ();
        } catch (NoSuchMethodException e) {
            e.printStackTrace ();
        } catch (IllegalAccessException e) {
            e.printStackTrace ();
        } catch (InstantiationException e) {
            e.printStackTrace ();
        } catch (InvocationTargetException e) {
            e.printStackTrace ();
        }
    }
}
