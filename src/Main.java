
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("1.SignIn\n2.SignUp\n3.Exit");
            int action = scan.nextInt();
            scan.nextLine();
            server ser = new server();

            if (action == 1) {
                String role = ser.signIn();
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
                            } else if (search==3) {
                                continue;
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