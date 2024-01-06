import java.sql.*;

public class server{
    public static Connection con= null;
//    public void connect() throws Exception{
    static {
    String url = "jdbc:mysql://localhost:3306/vehiclerentsystem";
    String ur = "root";
    String pw = "Santhosh@13";
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, ur, pw);
    }
    catch (Exception e){
        System.out.println(e);
    }
//    }
    }

    public User signIn(String username,String password) throws Exception {

//        String query = "select password from customer where username = "+username;
        String query="SELECT * FROM CUSTOMER";
        Statement st= con.createStatement();
        ResultSet rs= st.executeQuery(query);
        if(rs.next()){
            User s=new User();
            s.username=username;
            s.password=password;
            System.out.println(s.password);
            return s;
        }
        else {
            System.out.println("Account not found");
            return null;
        }



    }


}
