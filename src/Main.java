import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        String adminId="admin@gmail.com";
        String adminPassword="1234";
//        ArrayList<HashMap<String,String>> customers=new ArrayList<>();
//        LinkedHashMap<String,String> c1=new LinkedHashMap<>(){
//            {
//                put("userId","sandii@gmail.com");
//                put("password","sandii");
//                put("name","santhosh kumar s");
//                put("address","karur");
//                put("phoneNumber","7092708250");
//
//            }
//        };
//        LinkedHashMap<String,String> c2=new LinkedHashMap<>(){
//            {
//                put("userId","ratish@gmail.com");
//                put("password","ratish");
//                put("name","Ratish R U");
//                put("address","coimbatore");
//                put("phoneNumber","9876543201");
//
//            }
//        };
//
//        customers.add(c1);
//        customers.add(c2);
        System.out.println("1.sign in\n2.sign up\n3.exit");
//        System.out.println(customers);
        int n=scan.nextInt();
        scan.nextLine();
        do {
            switch (n) {
                case 1: {
                    System.out.println("               SIGN IN             ");
                    System.out.println("Username:");
                    String username = scan.nextLine().toLowerCase();
                    System.out.println("Password:");
                    String password = scan.nextLine().toLowerCase();
                    if (username.equals(adminId)) {
                        if (password.equals(adminPassword)) {
                            System.out.println("Welcome Admin");
                        } else {
                            System.out.println("Incorrect Password");
                        }
                    }
//                else {
//                    boolean flag = false;
//                    for (HashMap i : customers) {
//                        if (i.get("userId").equals(username)) {
//                            if (i.get("password").equals(password)) {
//                                System.out.println("Welcome " + i.get("name"));
//                            } else {
//                                System.out.println("Login failed due to incorrect password");
//                            }
//                            flag = true;
//                        }
//                    }
//                    if (!flag) {
//                        System.out.println("User Not Found.");
//                        System.out.println("Sign up to touch with us :) smile ");
//                    }
//                }
//                break;
                    try {
                        server ser = new server();
                        User s = ser.signIn(username, password);
                        if (s != null)
                            System.out.println("Welcome " + s.username);
                        else {
                            System.out.println("sign up to connect with us");
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;

                }
                case 2: {
                    System.out.println("                  SIGN UP                ");
                    LinkedHashMap<String, String> map = new LinkedHashMap<>();
                    System.out.println("username:");
                    map.put("userId", scan.nextLine());
                    System.out.println("password:");
                    map.put("password", scan.nextLine());
                    System.out.println("name:");
                    map.put("name", scan.nextLine());
                    System.out.println("address:");
                    map.put("address", scan.nextLine());
                    System.out.println("phoneNumber:");
                    map.put("phoneNumber", scan.nextLine());
//                customers.add(map);
                    System.out.println("Signed up successfully");
                    break;
                }
                case 3: {
                    System.out.println("ThankYou Visit again");
                    System.exit(0);
                }

            }
        }while (n!=3);
    }
}