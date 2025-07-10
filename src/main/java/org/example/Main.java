package org.example;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;





public class Main extends ConnectDb {
    public static void main(String[] args) throws SQLException {
        try {

            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
            System.out.println(e);
        }
        Connection con = Main.getConnect();
        Rentals rent = new Rentals();
        Vehicle vehicle = new Vehicle();
        Customer customer = new Customer();
        double id = 0;
        int choice = 0, ch = 0;

        Scanner sc = new Scanner(System.in);

        System.out.println("Hey,Welcome to" + "\n    Vehicle Rental System");

            System.out.println("Press:" + "\n1->Sign in" + "\n2->Sign up");
            choice = sc.nextInt();



            //If Statement for sign in
           if (choice == 1) {
                System.out.println("Enter the user id:");
                id = sc.nextDouble();

                if(id < 3000) {
                    System.out.println("Succesfully Login,");
                    do {
                        System.out.println("MENU(Press Corresponding Digit):");
                        System.out.println("1.Profile");
                        System.out.println("2.Booking");
                        System.out.println("3.Exit");
                        System.out.println("Please Enter Choice:");
                        ch = sc.nextInt();
                        switch (ch) {
                            case 1:
                                customer.View_Method(id, con);
                                break;
                            case 2:
                                System.out.println("-------------------------  Booking -----------------------------------\n");
                                vehicle.View_Method(id, 0, con);
                                System.out.println("------------------------------------------------------------");
                                System.out.println("For Booking press->1");
                                choice = sc.nextInt();
                                if (choice == 1) {
                                    rent.Booking(id, con);
                                }
                                break;
                            case 3:break;

                            default:
                                System.out.println("Please Enter Valid Choice!");
                                break;
                        }


                    }while(ch!=3);

                }

                else {
                    System.out.println("Successfully Login,");
                    do {
                        System.out.println("MENU(Press Corresponding Digit):");
                        System.out.println("1.Profile");
                        System.out.println("2.Rental History");
                        System.out.println("3.Exit");
                        System.out.println("Please Enter Choice:");
                        ch = sc.nextInt();

                        switch (ch) {
                            case 1:
                                vehicle.View_Method(id, choice, con);
                                break;
                            case 2:
                                rent.View_Method(id, con);
                                break;
                            case 3:break;
                            default:
                                System.out.println("Please Enter the Valid Choice");
                                break;
                        }

                    }while(ch!=3);

                }


            }
    //Else Statement For Sign Up
            else if (choice == 2) {
                System.out.println("Press: " + "\n 1.Vehicle Register" + "\n 2.As Customer");
                ch = sc.nextInt();

                if (ch == 1) {
                    id = vehicle.get_method(con);
                    System.out.println("Thank You! For Registering\n");
                    vehicle.View_Method(id, 1, con);
                }
                else {
                    id = customer.getMethod(con);
                    System.out.println("Successfully Registered,");
                    do {
                        System.out.println("MENU(Press Corresponding Digit):");
                        System.out.println("1.Profile");
                        System.out.println("2.Booking");
                        System.out.println("3.Exit");
                        System.out.println("Please Enter Choice:");
                        ch = sc.nextInt();
                        switch (ch) {
                            case 1:
                                customer.View_Method(id, con);
                                break;
                            case 2:
                                System.out.println("-------------------------  Booking -----------------------------------\n");
                                vehicle.View_Method(id, 0, con);
                                System.out.println("------------------------------------------------------------");
                                System.out.println("For Booking press->1");
                                choice = sc.nextInt();
                                if (choice == 1) {
                                    rent.Booking(id, con);
                                }
                                break;
                            case 3:
                                break;
                            default:
                                System.out.println("Please Enter Valid Choice!");
                                break;
                        }

                    } while (ch!=3);
                }
            }
            else{
                System.out.println("Please Choose Valid Choice.!");
            }
    }
}
