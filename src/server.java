import java.sql.*;
import java.time.LocalDate;
import java.util.*;

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

    public User signIn(){
        User data=new User();
        System.out.println("Enter username or mail Id");
        String username= scan.nextLine();
        System.out.println("Enter password");
        String password= scan.nextLine();
//        ArrayList<String> list=new ArrayList<>();
        try {
            String query = "SELECT * FROM USER WHERE USERNAME='" + username + "';";
            ResultSet rs = st.executeQuery(query);
            if(rs.next()){
                if(rs.getString(3).equals(password)){
                    data.setUserId(rs.getInt(1));
                    data.setUsername(rs.getString(2));
                    data.setPassword(rs.getString(3));
                    data.setRole(rs.getString(4));
//                    list.add(rs.getString(1));
//                    list.add(rs.getString(4));
                    return data;
                }
                else {
                    System.out.println("INCORRECT PASSWORD");

                }
            }
            else {
                System.out.println("USER NOT FOUND");
                return data;
            }
        }
        catch (Exception e){
            System.out.println("Couldn't SIGN IN!!!");
        }
        return data;

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


            String query1 = "SELECT user_ID FROM USER WHERE USERNAME='" + username + "';";
            ResultSet rs1 = st.executeQuery(query1);
            rs1.next();
            int userId= rs1.getInt(1);

            String query2 = "INSERT INTO CUSTOMER (user_ID,phone_Number) VALUES ("+userId+","+mobile+");";
            int count2 = st.executeUpdate(query2);
            return count;
        }
        catch (Exception e){
            System.out.println(e);
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
            String query = "SELECT * FROM VEHICLE WHERE TYPE = 'CAR' AND KMDRIVEN < NEXTSERVICE AND AVAILABLESTATUS=1; ";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            boolean flag=false;
            System.out.println("-----------------------------------------------------------------------------------------------------------------");
            while (rs.next()) {
                flag=true;
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3)
                        + " " + rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6)
                        + " " + rs.getInt(7) + " " + rs.getInt(8) + " " + rs.getBoolean(9) + " " + rs.getString(10)+ " " + rs.getInt(11));
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
            String query = "SELECT * FROM VEHICLE WHERE TYPE = 'BIKE' AND KMDRIVEN < NEXTSERVICE AND AVAILABLESTATUS=1;";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            boolean flag=false;
            System.out.println("-----------------------------------------------------------------------------------------------------------------");
            while (rs.next()) {
                flag=true;
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3)
                        + " " + rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6)
                        + " " + rs.getInt(7) + " " + rs.getInt(8) + " " + rs.getBoolean(9) + " " + rs.getString(10)+ " " + rs.getInt(11));
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
    public void cShowVehicleCar(){
        try {
            String query = "SELECT * FROM VEHICLE WHERE TYPE = 'CAR' AND KMDRIVEN < NEXTSERVICE AND AVAILABLESTATUS=1;";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            boolean flag=false;
            System.out.println("-----------------------------------------------------------------------------------------------------------------");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3)
                        + " " + rs.getString(4)  + " " + rs.getString(10)+ " " + rs.getInt(11));
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
    public void cShowVehicleBike(){
        try {
            String query = "SELECT * FROM VEHICLE WHERE TYPE = 'BIKE' AND KMDRIVEN < NEXTSERVICE AND AVAILABLESTATUS=1 ;";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            boolean flag=false;
            System.out.println("-----------------------------------------------------------------------------------------------------------------");
            while (rs.next()) {
                flag=true;
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3)
                        + " " + rs.getString(4)  + " " + rs.getString(10)+ " " + rs.getInt(11));
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
            String query = "SELECT * FROM vehicle where modelName='"+name+"' AND KMDRIVEN < NEXTSERVICE AND AVAILABLESTATUS=1;";
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
            String query = "SELECT * FROM vehicle where numberPlate = '" + num + "' AND KMDRIVEN < NEXTSERVICE AND AVAILABLESTATUS=1 ;";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            System.out.println("-----------------------------------------------------------------------------------------------------------------");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3)
                        + " " + rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6)
                        + " " + rs.getInt(7) + " " + rs.getInt(8) + " " + rs.getBoolean(9) + " " + rs.getString(10)+ " " + rs.getInt(11));
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
                        + " " + rs.getInt(7) + " " + rs.getInt(8) + " " + rs.getBoolean(9) + " " + rs.getString(10)+ " " + rs.getInt(11));
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
    public void showCategory(){
        try {
            String query = "SELECT DISTINCT category FROM vehicle where availableStatus=1";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
//                String query1 = "SELECT COUNT(CATEGORY) FROM VEHICLE WHERE CATEGORY = '"+rs.getString(1)+"';";
//                ResultSet rs1 = st.executeQuery(query1);
//                rs1.next();
                System.out.println(rs.getString(1)+" ");
//                System.out.println("test");
//                System.out.println(rs1.getInt(1));
//                System.out.println("test");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void listCategory(String category){
        try{
            String query = "SELECT vehicle_ID, brand, modelName, vehicleDeposit, rentPerDay FROM vehicle where category ='"+category+"';";
            ResultSet rs = st.executeQuery(query);
            System.out.println("Vehicle_ID\tBrand\tModel Name\tVehicle Deposit\tRent Per Day");
            while (rs.next()){
                System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getInt(4)+"\t"+rs.getInt(5));
            }
        }catch (Exception e){
            System.out.println(e);
        }

    }
    public void book(Map<String,String> cart,String userId){
        try {
//            System.out.println(cart);
            int bikeDeposit = 0;
            int carDeposit = 0;
            for (Map.Entry<String, String> map : cart.entrySet()) {
                String query = "SELECT VEHICLEDEPOSIT FROM VEHICLE WHERE VEHICLE_ID = " + map.getValue() + ";";
                ResultSet rs = st.executeQuery(query);
                rs.next();
                if (map.getKey().equalsIgnoreCase("bike")) {
                    bikeDeposit = rs.getInt(1);
                } else {
                    carDeposit = rs.getInt(1);
                }
            }
            System.out.println("Pay the deposit Amount " + (bikeDeposit + carDeposit));
            boolean payment = scan.nextBoolean();
            if (payment) {
                int kmDriven = 0;
                int phone = 0;
                for (Map.Entry<String, String> map : cart.entrySet()) {
                    int count=0;
                    String vehType = map.getKey();
                    try {
                        if (vehType.equalsIgnoreCase("bike")) {
                            String query1 = "UPDATE CUSTOMER SET BIKE_ID = " + map.getValue() + ",BIKE_DEPOSIT = " + bikeDeposit + " WHERE USER_ID =" + userId + ";";
                            count += st.executeUpdate(query1);
                        } else {
                            String query1 = "UPDATE CUSTOMER SET CAR_ID = " + map.getValue() + ",CAR_DEPOSIT = " + carDeposit + " WHERE USER_ID =" + userId + ";";
                            count += st.executeUpdate(query1);
                        }
//                        System.out.println("customer"+count);
                    }
                    catch (Exception e){
                        System.out.println(e);
                        System.out.println("Customer status is not updated");
                    }
                    try {
                        String query = "UPDATE VEHICLE SET AVAILABLESTATUS = 0 WHERE VEHICLE_ID = " + map.getValue() + ";";
                        count += st.executeUpdate(query);
//                        System.out.println("vehicle"+count);

                    }
                    catch (Exception e){
                        System.out.println(e);
                        System.out.println("vechicle status not updated");
                    }
                    String query2 = "SELECT KMDRIVEN FROM VEHICLE WHERE VEHICLE_ID =" + map.getValue() + ";";
                    ResultSet rs = st.executeQuery(query2);
                    rs.next();
                    kmDriven = rs.getInt(1);
//                        phone = rs.getInt(2);
                    try {
                        String query3 = "INSERT INTO RENTALSTATUS (USER_ID,VEHICLE_ID,STATUS,STARTKM,TYPE) VALUES (?,?,?,?,?);";
                        PreparedStatement st = con.prepareStatement(query3);
                        st.setInt(1, Integer.parseInt(userId));
//                    if(cart.get("bike")!=null)
//                        st.setInt(2,Integer.parseInt(cart.get("bike")));
//                    else
//                        st.setNull(2,Types.NULL);
//
//                    if(cart.get("car")!=null)
//                        st.setInt(3,Integer.parseInt(cart.get("car")));
//                    else
//                        st.setNull(3,Types.NULL);
                        st.setInt(2, Integer.parseInt(map.getValue()));
                        st.setString(3, "Rented");
                        st.setInt(4, kmDriven);
                        st.setString(5, vehType);
                        count += st.executeUpdate();
//                        System.out.println("rental status"+count);
                    } catch (Exception e){
                        System.out.println("rental status not Updated");
                        System.out.println(e);
                    }
//                    System.out.println(count);
                    try {

                        if (count == 3) {
                            String query4 = "SELECT BOOKING_ID FROM rentalStatus WHERE USER_ID = " + userId + " AND TYPE = '" + vehType + "';";
                            ResultSet rs4 = st.executeQuery(query4);
                            rs4.next();
                            System.out.println("Booked Successfully");
                            System.out.println("Your Booking Id for" + map.getKey() + " is #" + rs4.getInt(1));
                        } else {
                            System.out.println("Something went wrong");
                        }
                    }catch (Exception e){
                        System.out.println(e);
                    }
                }
            }
            else {
                System.out.println("Booking Failed");
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public void returnVehicle(String userId){
        try {
            String query = "SELECT BOOKING_ID,VEHICLE_ID FROM RENTALSTATUS WHERE USER_ID =" + userId + " AND STATUS = 'RENTED';";
            ResultSet rs = st.executeQuery(query);
            String bookingId = "";
            String vehId = "";
            int rentPerDay = 0;
            Map<String, Integer> map1 = new HashMap<>();
            Map<String, String> map2 = new HashMap<>();
            System.out.println("Vehicle need to return ");
            System.out.println("Booking_ID\tVehicle_ID\tModel_name\tType");
            rs.next();
            while (true) {
                bookingId = String.valueOf(rs.getInt(1));
                vehId = String.valueOf(rs.getInt(2));
                map2.put(bookingId, vehId);
                if(!rs.next()){
                    break;
                }
            }

            try{
                for(Map.Entry<String,String> data: map2.entrySet()) {
                    String query2 = "SELECT Vehicle_id,MODELNAME,type,rentperday FROM VEHICLE WHERE VEHICLE_ID = " + data.getValue() + ";";
                    ResultSet rs2 = st.executeQuery(query2);
                    while (rs2.next()) {
                        System.out.println(data.getKey() + " " + rs2.getString(1) + " " + rs2.getString(2) + " " + rs2.getString(3));
                        rentPerDay = rs2.getInt(4);
                        map1.put(data.getKey(), rentPerDay);
//                        System.out.println(map1);
                    }
                    rs2.close();
                }
            }catch (Exception e){
                System.out.println(e);
            }
            System.out.println("Select Booking_ID to return ");
            bookingId= scan.next();
            rentPerDay=map1.get(bookingId);
            System.out.println("Enter the kilometer Driven Which shows in speedometer");
            int endKm= scan.nextInt();
            System.out.println("Mention if any damage occured to car by entering one of these label:\n1.LOW,\n2.MEDIUM,\n3.HIGH,\n4.NO DAMAGE");
            scan.nextLine();
            String label= scan.next();

            String query3 = "SELECT BOOKEDDATETIME, STARTKM FROM RENTALSTATUS WHERE BOOKING_ID ="+bookingId+";";
            ResultSet rs3= st.executeQuery(query3);
            String rentedDate="";
            int startKM=0;
            if(rs3.next()){
                rentedDate = rs3.getString(1);
                rentedDate = rentedDate.substring(0,rentedDate.indexOf(' '));
                startKM = rs3.getInt(2);
            }

            String query12= "SELECT VEHICLE_ID FROM RENTALSTATUS WHERE BOOKING_ID = "+bookingId+";";
            ResultSet rs12 = st.executeQuery(query12);
            rs12.next();
            int vId= rs12.getInt(1);

            String query11 = "SELECT AVAILABLESTATUS FROM VEHICLE WHERE VEHICLE_ID = "+vId+";";
            ResultSet rs11 = st.executeQuery(query11);
            rs11.next();
            int consecutiveRent = rs11.getInt(1);

            LocalDate returndate = LocalDate.now();
            String returnedDate=returndate.toString();
            String returnStatus="";
            if(rentedDate.equals(returnedDate) || consecutiveRent==2){
                    returnStatus="GOOD";
            }
            else {
                returnStatus="LATE";
            }
            int kmDriven=Math.abs(startKM-endKm);
            int extraCharge=0;
            if(kmDriven>500){
                extraCharge=((rentPerDay*15)/100);
            }
            int fine=0;
            if(label.equalsIgnoreCase("HIGH")){
                fine+=((rentPerDay*75)/100);
            }else if(label.equalsIgnoreCase("MEDIUM")){
                fine+=((rentPerDay*50)/100);
            }else if(label.equalsIgnoreCase("LOW")){
                fine+=((rentPerDay*25)/100);
            } else {
                fine+=0;
            }

            if(returnStatus.equals("LATE")){
                fine+=((rentPerDay*15)/100);
            }

            int total=rentPerDay+fine+extraCharge;
            int count=0;

            String query4="INSERT INTO BILL (BOOKING_ID,RETURN_STATUS,DAMAGE_LABEL,KILOMETER_DRIVEN,RENTAL_CHARGE,EXTRA_CHARGE,FINE_AMOUNT,TOTAL)" +
                    "VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement st1= con.prepareStatement(query4);
            st1.setInt(1,Integer.parseInt(bookingId));
            st1.setString(2,returnStatus);
            st1.setString(3,label);
            st1.setInt(4,kmDriven);
            st1.setInt(5,rentPerDay);
            st1.setInt(6,extraCharge);
            st1.setInt(7,fine);
            st1.setInt(8,total);
            count += st1.executeUpdate();
            System.out.println(count);

            String query5 = "SELECT BILL_ID FROM BILL WHERE BOOKING_ID ="+bookingId+";";
            ResultSet rs5= st.executeQuery(query5);
            rs5.next();
            int billId=rs5.getInt(1);

            String query6 = "UPDATE RENTALSTATUS SET RETURNDATETIME = NOW(),RETURNEDSTATUS  = '"+returnStatus+"', STATUS = 'RETURNED',endKM = "+endKm+",damage = '"+label+"',BILL_ID ="+billId+",kmDriven ="+kmDriven+" WHERE BOOKING_ID = "+bookingId+";";
            count += st.executeUpdate(query6);
            System.out.println(count);
            int count1=0;
            if(count==2) {
                String query7 = "SELECT TYPE,VEHICLE_ID FROM RENTALSTATUS WHERE BOOKING_ID ="+bookingId+";";
                ResultSet rs7= st.executeQuery(query7);
                rs7.next();
                String type=rs7.getString(1);
                vId=rs7.getInt(2);
                int deposit=0;
                String query9 = "SELECT VEHICLEDEPOSIT FROM VEHICLE WHERE VEHICLE_ID ="+vId+";";
                ResultSet rs9= st.executeQuery(query9);
                rs9.next();
                deposit=rs9.getInt(1);
                if(type.equalsIgnoreCase("car")){
                    String query8 = "UPDATE CUSTOMER SET CAR_ID = NULL , CAR_DEPOSIT = NULL WHERE USER_ID ="+userId;
                    count1+= st.executeUpdate(query8);
                }else{
                    String query8 = "UPDATE CUSTOMER SET BIKE_ID = NULL , BIKE_DEPOSIT = NULL WHERE USER_ID ="+userId;
                    count1+= st.executeUpdate(query8);
                }

//                String query11 = "SELECT AVAILABLESTATUS FROM VEHICLE WHERE VEHICLE_ID = "+vId+";";
//                ResultSet rs11 = st.executeQuery(query11);
//                rs11.next();
//                int consecutiveRent = rs11.getInt(1);

                if(consecutiveRent == 2){
                    total*=2;
                }

                String query10 = "UPDATE VEHICLE SET AVAILABLESTATUS = 1 WHERE VEHICLE_ID = "+vId+";";
                count1 += st.executeUpdate(query10);

                System.out.println("Your bill amount is " + total);
                System.out.println("Balance Amount :"+(deposit-total));
                System.out.println("Your Deposit Balance Amount Will be refunded");
            }
            else{
                System.out.println("Something went wrong");
            }

        }catch (Exception e){
            System.out.println(e);
        }
    }

    public boolean isConsecutive(String userId){
        try {

            String query1 = "SELECT CAR_ID,BIKE_ID FROM CUSTOMER WHERE USER_ID = " + userId + ";";
            ResultSet rs1 = st.executeQuery(query1);
            rs1.next();
            String carId="";
            String bikeId="";
            if (rs1.getString(1)==null && rs1.getString(2) == null) {
                System.out.println(" You have not rented any vehicle");
                return false;
            } else if(rs1.getString(1) != null){
                carId = rs1.getString(1);
                System.out.printf("%-10s %-10s\n", "Vehicle", "ID");
                System.out.printf("%-10s %-10s\n", "Car", carId);
            } else if (rs1.getString(2) != null) {
                bikeId = rs1.getString(2);
                System.out.printf("%-10s %-10s\n", "Vehicle", "ID");
                System.out.printf("%-10s %-10s\n", "Bike", bikeId);
            }

            System.out.println("Enter The Vehicle Id to Extend Renting");
            String exte = scan.next();
            String query2= "SELECT AVAILABLESTATUS FROM VEHICLE WHERE VEHICLE_ID = "+exte+";";
            ResultSet rs2 = st.executeQuery(query2);
            rs2.next();
            if(rs2.getInt(1)==0){
                String query3 = "UPDATE VEHICLE SET AVAILABLESTATUS = 2 WHERE VEHICLE_ID = "+exte+";";
                int count=0;
                count += st.executeUpdate(query3);
                System.out.println(count);

                if(count==1)
                    System.out.println("Your extend of the vehicle Id "+exte+" is Successfull");
                else
                    System.out.println("Something went wrong");

            }
            else {
                System.out.println("You Already Extended. Now you can't able to extend.");
                System.out.println("If you need Vehicle You have to renturn vehicle and make another booking");
            }

        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    public void history(String userId){
        try{
            String query = "SELECT * FROM BILL";
            ResultSet rs= st.executeQuery(query);
            System.out.printf("%-8s | %-10s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %n","Bill_ID","Booking_ID","Returned_Status","Damage_Label","Kilometer_DRIVER","Rental_Charge","Extra_Charge","Fine_Amount","TOTAL");
            while (rs.next()){
                System.out.printf("%-12s",rs.getString(1));
                System.out.printf("%-13s",rs.getString(2));
                System.out.printf("%-20s",rs.getString(3));
                System.out.printf("%-20s",rs.getString(4));
                System.out.printf("%-15s",rs.getString(5));
                System.out.printf("%-23s",rs.getString(6));
                System.out.printf("%-15s",rs.getString(7));
                System.out.printf("%-15s",rs.getString(9));
                System.out.printf("%-15s %n",rs.getString(8));
            }

        }catch (Exception e){
            System.out.println(e);
        }
    }

//    public static void main(String[] args) {
//        server s=new server();
////        s.history("105");
//        s.returnVehicle("105");
////        s.isConsecutive("105");
//    }

}
