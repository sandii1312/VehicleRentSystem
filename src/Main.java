
import java.util.*;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        while (true) {
            System.out.println("1.SignIn\n2.SignUp\n3.Exit");
            int action = scan.nextInt();
            scan.nextLine();
            server ser = new server();
            if (action == 1) {
                User temp=ser.signIn();
                if(temp.getUserId()==0){
                    continue;
                }
                String userId=String.valueOf(temp.getUserId());
                String role= temp.getRole();
                if (role.equals("admin")) {
                    System.out.println("WELCOME ADMIN");
                    while(true){
                        System.out.println("1.List all vehicle\n2.Search a Vehicle\n3.Add a vehicle\n4.Modify Vehicle Specification\n5.Vehicle Need to service\n6.logout");
                        int adminUse=scan.nextInt();
                        scan.nextLine();
                        if(adminUse==1){
                            System.out.println("***************CARS LIST********************");
                            ser.showVehicleCar();
                            System.out.println("***************BIKES LIST********************");
                            ser.showVehicleBike();
                        } else if (adminUse==2) {
                            System.out.println("1.Search Vehicle by Name\n2. search Vehicle by number\n3. Back");
                            int search= scan.nextInt();
                            scan.nextLine();
                            if(search==1){
                                System.out.println("Model name");
                                String vehicleName= scan.nextLine();
                                ser.searchByName(vehicleName);
                            } else if (search==2) {
                                System.out.println("Vehicle number");
                                String vehicleNumber= scan.nextLine();
                                ser.searchByNumber(vehicleNumber);
                            }

                        } else if (adminUse==3) {
                            int res=ser.addVehicle();
                            if(res==1){
                                System.out.println("Vehicle added Successfully");
                            }
                            else {
                                System.out.println("Something went wrong");
                            }
                        } else if (adminUse==4) {
                            System.out.println("1.Delete Vehicle\n2. back");
                            int operation= scan.nextInt();
                            scan.nextLine();
                            if(operation==1){
                                System.out.println("Vehicle Number");
                                ser.deleteVehicle(scan.nextLine());
                            }
                        } else if (adminUse==5) {
                            ser.vehicleNeedToService();
                        }else if(adminUse==6){
                            break;
                        }
                    }
                } else if(role.equals("customer")){
                    System.out.println("WELCOME USER");

                    Map<String,String> cart=new HashMap<>();

                    while(true) {
                        System.out.println("1.Booking Vehicle\n2.Return Vehicle\n3.Extend Date\n4.Previous Booking History\n5.Logout");
                        int option= scan.nextInt();
                        if(option==1) {
                            while (true) {
                                System.out.println("1.List of Vehicle\n2.search by category\n3.checkout\n4.back");
                                int customerIn = scan.nextInt();
                                scan.nextLine();
                                if (customerIn == 1) {
                                    System.out.println("1.Bikes\n2.Cars\n3.back");
                                    int type= scan.nextInt();
                                    if(type==1) {
                                        System.out.println("***************Bikes LIST********************");
                                        ser.cShowVehicleBike();
                                        System.out.println("Enter the Bike Id to Select");
                                        String no= scan.next();
                                        if(Pattern.matches("[0-9]+",no)){
                                            if(cart.get("bike")==null) {
                                                cart.put("bike", no);
                                            }
                                            else {
                                                System.out.println("Bike Limit reached. Do You want to replace previous selected bike?(YES/NO)");
                                                if(scan.next().equalsIgnoreCase("yes")){
                                                    cart.put("bike",no);
                                                }
                                            }
                                        }
                                        else {
                                            System.out.println("Invalid vehicle Number!!!");
                                        }
                                    } else if (type==2) {
                                        System.out.println("***************CARS LIST********************");
                                        ser.cShowVehicleCar();
                                        System.out.println("Enter the Car Id to Select");
                                        String no= scan.next();
                                        if(Pattern.matches("[0-9]+",no)){
                                            if(cart.get("car")==null) {
                                                cart.put("car", no);
                                            }
                                            else {
                                                System.out.println("Car Limit reached. Do You want to replace previous selected Car?(YES/NO)");
                                                if(scan.next().equalsIgnoreCase("yes")){
                                                    cart.put("car",no);
                                                }
                                            }
                                        }
                                        else {
                                            System.out.println("Invalid vehicle Number!!!");
                                        }
                                    }
                                } else if (customerIn == 2) {
                                    ser.showCategory();
                                    System.out.println("Enter the category to select :");
                                    String category = scan.nextLine();
                                    ser.listCategory(category);
                                    System.out.println("Enter the Vehicle_ID to select:");
                                    String no = scan.next();
                                    if(Pattern.matches("[0-9]+",no)) {
                                        if (category.equalsIgnoreCase("sports") || category.equalsIgnoreCase("normal")) {
                                            if(cart.get("bike")==null) {
                                                cart.put("bike", no);
                                            }
                                            else {
                                                System.out.println("Bike Limit reached. Do You want to replace previous selected bike?(YES/NO)");
                                                if(scan.next().equalsIgnoreCase("yes")){
                                                    cart.put("bike",no);
                                                }
                                            }
                                        }
                                        else {
                                            if(cart.get("car")==null) {
                                                cart.put("car", no);
                                            }
                                            else {
                                                System.out.println("Car Limit reached. Do You want to replace previous selected Car?(YES/NO)");
                                                if(scan.next().equalsIgnoreCase("yes")){
                                                    cart.put("car",no);
                                                }
                                            }
                                        }
                                    }
                                    else {
                                        System.out.println("Invalid vehicle Id");
                                    }

                                } else if (customerIn==3) {
                                    for(Map.Entry<String,String> veh:cart.entrySet()){
                                        System.out.println(veh.getKey()+" "+veh.getValue());
                                    }
                                    while (true){
                                        System.out.println("1.Book\n2.Remove Item\n3.back");
                                        int checkout= scan.nextInt();
                                        if(checkout==1){
                                            if(!cart.isEmpty()) {
                                                ser.book(cart, userId);
                                            }
                                            else {
                                                System.out.println("Your cart is empty");
                                            }
                                            break;
                                        } else if (checkout==2) {
                                            System.out.println("Bike or car?");
                                            if(scan.next().equalsIgnoreCase("bike"))
                                            cart.remove("bike");
                                            else {
                                                cart.remove("car");
                                            }
                                        }
                                        else {
                                            break;
                                        }
                                    }
                                }else if (customerIn==4) {
                                    break;
                                }
                            }
                        } else if (option==2) {
                            ser.returnVehicle(userId);

                        } else if (option==3) {
                            ser.isConsecutive(userId);

                        }else if (option==4) {
                            System.out.println("*********BOOKING HISTORY**************");
                            ser.history(userId);
                        }else if (option==5) {
                            cart.clear();
                            break;
                        }
                    }
                }
                else if(role.equals("USER NOT FOUND")){
                    System.out.println("Sign up to continue");
                }
            } else if (action == 2) {
                int status = ser.signup();
                if (status == -1) {
                    System.out.println("UNABLE to Create Account");
                } else {
                    System.out.println("Account created successfully");
                }
            } else {
                System.exit(0);
            }
        }
    }

}