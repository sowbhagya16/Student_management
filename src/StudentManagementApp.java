import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.io.FileInputStream;
import java.util.Properties;

public class StudentManagementApp {

    public static void main(String[] args) {

        try {
            // Load DB config
            Properties prop = new Properties();
            FileInputStream fis = new FileInputStream("db.properties");
            prop.load(fis);

            String url = prop.getProperty("db.url");
            String user = prop.getProperty("db.user");
            String password = prop.getProperty("db.password");

            Connection con = DriverManager.getConnection(url, user, password);

            Scanner sc = new Scanner(System.in);
            int choice;

            do {
                System.out.println("\n--- Student Management Menu ---");
                System.out.println("1. Add Student");
                System.out.println("2. View Students");
                System.out.println("3. Update Student");
                System.out.println("4. Delete Student");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");

                choice = sc.nextInt();

                switch (choice) {

                    case 1:
                        System.out.print("Enter name: ");
                        sc.nextLine(); // consume newline
                        String name = sc.nextLine();

                        System.out.print("Enter age: ");
                        int age = sc.nextInt();

                        System.out.print("Enter course ID: ");
                        int courseId = sc.nextInt();

                        String insertSql =
                                "INSERT INTO student (name, age, course_id) VALUES (?, ?, ?)";
                        PreparedStatement insertPs =
                                con.prepareStatement(insertSql);

                        insertPs.setString(1, name);
                        insertPs.setInt(2, age);
                        insertPs.setInt(3, courseId);

                        insertPs.executeUpdate();
                        System.out.println("Student added successfully");
                        break;

                    case 2:
                        String selectSql = "SELECT * FROM student";
                        PreparedStatement selectPs =
                                con.prepareStatement(selectSql);

                        ResultSet rs = selectPs.executeQuery();

                        System.out.println("\nID | Name | Age | Course");
                        System.out.println("---------------------------");

                        while (rs.next()) {
                            System.out.println(
                                    rs.getInt("student_id") + " | " +
                                    rs.getString("name") + " | " +
                                    rs.getInt("age") + " | " +
                                    rs.getInt("course_id")
                            );
                        }
                        break;

                    case 3:
                        System.out.print("Enter student ID to update: ");
                        int updateId = sc.nextInt();

                        System.out.print("Enter new age: ");
                        int newAge = sc.nextInt();

                        String updateSql =
                                "UPDATE student SET age = ? WHERE student_id = ?";
                        PreparedStatement updatePs =
                                con.prepareStatement(updateSql);

                        updatePs.setInt(1, newAge);
                        updatePs.setInt(2, updateId);

                        int updated = updatePs.executeUpdate();

                        if (updated > 0)
                            System.out.println("Student updated successfully");
                        else
                            System.out.println("Student ID not found");
                        break;

                    case 4:
                        System.out.print("Enter student ID to delete: ");
                        int deleteId = sc.nextInt();

                        String deleteSql =
                                "DELETE FROM student WHERE student_id = ?";
                        PreparedStatement deletePs =
                                con.prepareStatement(deleteSql);

                        deletePs.setInt(1, deleteId);

                        int deleted = deletePs.executeUpdate();

                        if (deleted > 0)
                            System.out.println("Student deleted successfully");
                        else
                            System.out.println("Student ID not found");
                        break;

                    case 5:
                        System.out.println("Exiting application...");
                        break;

                    default:
                        System.out.println("Invalid choice. Try again.");
                }

            } while (choice != 5);

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
