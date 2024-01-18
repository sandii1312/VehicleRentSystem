import java.security.spec.ECField;
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
            System.out.println("CLASS NOT FOUND");
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
    public int addVehicle(){
        Vehicle data=new Vehicle();
        System.out.println("Brand :");
        data.setBrand(scan.nextLine());
        System.out.println("Model Name :");
        data.setModelName(scan.nextLine());
        System.out.println("category :");
        data.setCategory(scan.nextLine());
        System.out.println("Vehicle Type(car/bike) :");
        data.setType(scan.nextLine().toLowerCase());
        System.out.println("Vehicle Number Plate :");
        data.setNumberPlate(scan.nextLine().replace(" ",""));
        System.out.println("Kilometre Driven :");
        data.setKmDriven(scan.nextInt());
        if(data.getType().equals("car")){
            data.setNextServices(data.getKmDriven()+3000);
        }
        else {
            data.setNextServices(data.getKmDriven()+1500);
        }
        data.setAvailableStatus(true);
        System.out.println("Vehicle Deposit value");
        data.setVehicleDeposit(scan.nextInt());
        System.out.println("Rent per day");
        data.setRentPerDay(scan.nextInt());
        try {
            String query = "INSERT INTO VEHICLE (brand, modelName, category, type, numberPlate, kmDriven, nextService, availableStatus, vehicleDeposit, rentPerDay) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, data.getBrand());
            st.setString(2, data.getModelName());
            st.setString(3, data.getCategory());
            st.setString(4, data.getType());
            st.setString(5, data.getNumberPlate());
            st.setInt(6, data.getKmDriven());
            st.setInt(7, data.getNextServices());
            st.setBoolean(8, data.isAvailableStatus());
            st.setInt(9, data.getVehicleDeposit());
            st.setInt(10,data.getRentPerDay());

            int count = st.executeUpdate();

            return count;

        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }

    public void showVehicleCar(){
        try {
            String query = "SELECT * FROM VEHICLE WHERE TYPE = 'CAR';";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            boolean flag=false;
            System.out.println("-----------------------------------------------------------------------------------------------------------------");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3)
                        + " " + rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6)
                        + " " + rs.getString(7) + " " + rs.getString(8) + " " + rs.getString(9) + " " + rs.getString(10));
                System.out.println("-----------------------------------------------------------------------------------------------------------------");
            }
            if(!flag){
                System.out.println("No Vehicle found");
                System.out.println("-----------------------------------------------------------------------------------------------------------------");
            }
        }catch (Exception e){
            System.out.println(e.getStackTrace());
        }
    }
    public void showVehicleBike(){
        try {
            String query = "SELECT * FROM VEHICLE WHERE TYPE = 'BIKE';";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            boolean flag=false;
            System.out.println("-----------------------------------------------------------------------------------------------------------------");
            while (rs.next()) {
                flag=true;
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3)
                        + " " + rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6)
                        + " " + rs.getString(7) + " " + rs.getString(8) + " " + rs.getString(9) + " " + rs.getString(10));
                System.out.println("-----------------------------------------------------------------------------------------------------------------");
            }
            if(!flag){
                System.out.println("No Vehicle found");
                System.out.println("-----------------------------------------------------------------------------------------------------------------");
            }
        }catch (Exception e){
            System.out.println(e.getStackTrace());
        }
    }

    public void searchByName(String name) {
        try {
            String query = "SELECT * FROM vehicle where modelName='"+name+"';";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            System.out.println("-----------------------------------------------------------------------------------------------------------------");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3)
                        + " " + rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6)
                        + " " + rs.getString(7) + " " + rs.getString(8) + " " + rs.getString(9) + " " + rs.getString(10));
                System.out.println("-----------------------------------------------------------------------------------------------------------------");
            }
        }catch (Exception e){
            System.out.println(e.getStackTrace());
        }

    }
    public void searchByNumber(String num) {
        try {
            String query = "SELECT * FROM vehicle where numberPlate = '" + num + "';";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            System.out.println("-----------------------------------------------------------------------------------------------------------------");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3)
                        + " " + rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6)
                        + " " + rs.getString(7) + " " + rs.getString(8) + " " + rs.getString(9) + " " + rs.getString(10));
                System.out.println("-----------------------------------------------------------------------------------------------------------------");
            }
        }catch (Exception e){
            System.out.println(e.getStackTrace());
        }
    }

    public void vehicleNeedToService(){
        try {
            String query = "SELECT * FROM vehicle where kmDriven >= nextService";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            System.out.println("-----------------------------------------------------------------------------------------------------------------");
            if(!rs.next()) {
                System.out.println("NO vehicle need to service");
                System.out.println("-----------------------------------------------------------------------------------------------------------------");
            }
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3)
                        + " " + rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6)
                        + " " + rs.getString(7) + " " + rs.getString(8) + " " + rs.getString(9) + " " + rs.getString(10));
                System.out.println("-----------------------------------------------------------------------------------------------------------------");
            }
        }catch (Exception e){
            System.out.println(e.getStackTrace());
        }
    }

    public void deleteVehicle(String num){
        try {
            String query = "DELETE FROM vehicle WHERE numberPlate = '" + num + "';";
            Statement st = con.createStatement();
            int count = st.executeUpdate(query);
            if(count==1){
                System.out.println("Vehicle deleted successfully");
            }
            else{
                System.out.println("Vehicle not found");
            }
        }catch (Exception e){
            System.out.println("Error! Vehicle not found");
            System.out.println(e);
        }
    }
}
