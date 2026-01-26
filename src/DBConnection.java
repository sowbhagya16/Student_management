import java.sql.Connection;
import java.sql.DriverManager;
import java.io.FileInputStream;
import java.util.Properties;

public class DBConnection {

    public static void main(String[] args) {

        try {
            Properties prop = new Properties();
            FileInputStream fis = new FileInputStream("db.properties");
            prop.load(fis);

            String url = prop.getProperty("db.url");
            String user = prop.getProperty("db.user");
            String password = prop.getProperty("db.password");

            Connection con = DriverManager.getConnection(url, user, password);

            System.out.println("Connected successfully");

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}