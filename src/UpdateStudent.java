import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.io.FileInputStream;
import java.util.Properties;

public class UpdateStudent {

    public static void main(String[] args) {

        try {
            Properties prop = new Properties();
            FileInputStream fis = new FileInputStream("db.properties");
            prop.load(fis);

            String url = prop.getProperty("db.url");
            String user = prop.getProperty("db.user");
            String password = prop.getProperty("db.password");

            Connection con = DriverManager.getConnection(url, user, password);

            Scanner sc = new Scanner(System.in);

            System.out.print("Enter student ID to update: ");
            int id = sc.nextInt();

            System.out.print("Enter new age: ");
            int age = sc.nextInt();

            String sql = "UPDATE student SET age = ? WHERE student_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, age);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Student updated successfully");
            } else {
                System.out.println("Student ID not found");
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
