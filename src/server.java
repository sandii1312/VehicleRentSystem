import java.sql.*;
import java.util.Scanner;

public class server{
    public Scanner scan=new Scanner(System.in);
    private Connection con;
    private Statement st;

    public server(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/vehicleRentSystem","root","Santhosh@13");
            st= con.createStatement();
        }
        catch (Exception e){
            System.out.println("CLASS NOT FOUNT");
        }
    }

    public String signIn(){
        System.out.println("Enter username or mail Id");
        String username= scan.nextLine();
        System.out.println("Enter password");
        String password= scan.nextLine();
        try {
            String query = "SELECT * FROM USER WHERE USERNAME='" + username + "';";
            ResultSet rs = st.executeQuery(query);
            if(rs.next()){
                if(rs.getString(3).equals(password)){
                    return rs.getString(4);
                }
                else {
                    System.out.println("INCORRECT PASSWORD");
                }
            }
            else {
                System.out.println("USER NOT FOUND");
                return "USER NOT FOUND";
            }
        }
        catch (Exception e){
            System.out.println("Couldn't SIGN IN!!!");
        }
        return "";

    }

    public int signup(){
        try {
            System.out.println("Enter username or mail Id");
            String username= scan.nextLine();
            System.out.println("Enter password");
            String password= scan.nextLine();
            System.out.println("Enter the Mobile number:");
            String mobile= scan.next();

            String query = "INSERT INTO user (username,password,role) Values ('" + username + "','" + password + "','customer')";
            int count = st.executeUpdate(query);

            return count;
        }
        catch (Exception e){
            return -1;
        }


    }


}
