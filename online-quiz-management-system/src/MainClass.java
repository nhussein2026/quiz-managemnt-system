
import java.sql.*;
import java.io.IOException;


public class MainClass {

    public static void main(String[] args) {
    connectdb();
    }
    
     Connection con = null;

    public static void connectdb() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quizdb", "root", "Password1!");
            
            if (con != null)
                System.out.println("Your database are now connected to the database");
            
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("You are not connected with database");
        }
    }
    
    //    
//    Connection conn = null;
//
//    public static void connectdb() throws IOException {
//
//        try {
//            //db parameter
//           Connection conn =  DriverManager.getConnection("jdbc:sqlite:/C:\\Users\\naser\\OneDrive\\Documents\\NetBeansProjects\\online-quiz-management-system\\QuizSystem.SQLite");
//            System.out.println("Connection to SQLite has beem done successfully");
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        } finally {
//
//        }
//    }

}
