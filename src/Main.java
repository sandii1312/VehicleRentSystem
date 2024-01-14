
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