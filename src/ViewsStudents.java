import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.FileInputStream;
import java.util.Properties;

public class ViewsStudents {

    public static void main(String[] args) {

        try {
            Properties prop = new Properties();
            FileInputStream fis = new FileInputStream("db.properties");
            prop.load(fis);

            String url = prop.getProperty("db.url");
            String user = prop.getProperty("db.user");
            String password = prop.getProperty("db.password");

            Connection con = DriverManager.getConnection(url, user, password);

            String sql = "SELECT * FROM student";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            System.out.println("Student List:");
            System.out.println("----------------------------");

            while (rs.next()) {
                int id = rs.getInt("student_id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                int courseId = rs.getInt("course_id");

                System.out.println(
                    id + " | " + name + " | " + age + " | " + courseId
                );
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}