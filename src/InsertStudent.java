import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.io.FileInputStream;
import java.util.Properties;

public class InsertStudent {

    public static void main(String[] args) {

        try {
            Properties prop = new Properties();
            FileInputStream fis = new FileInputStream("db.properties");
            prop.load(fis);

            String url = prop.getProperty("db.url");
            String user = prop.getProperty("db.user");
            String password = prop.getProperty("db.password");

            Connection con = DriverManager.getConnection(url, user, password);

            String sql = "INSERT INTO student (name, age, course_id) VALUES (?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "Sowbhagya");
            ps.setInt(2, 21);
            ps.setInt(3, 1);

            ps.executeUpdate();

            System.out.println("Student inserted successfully");

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}