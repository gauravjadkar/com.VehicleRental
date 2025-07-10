package org.example;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.*;

class Calculate  extends Thread {
    Date Hire=Date.valueOf(Rentals.getRent_date());
    Date Return= Date.valueOf(Rentals.getReturn_date());
    static long price;
    public void run() {
        LocalDate h=Hire.toLocalDate();
        LocalDate r=Return.toLocalDate();
        long DaysBet = ChronoUnit.DAYS.between((Temporal) (h), (Temporal) (r));
         price = DaysBet * Vehicle.getRate_per_day(Rentals.getVehicle_id());

        try(Connection con=Rentals.getConnect()) {


            String query = "update vehicles " +
                    "set is_available=?" +
                    " where id=?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setDate(1,(java.sql.Date)Return);
            pstmt.setDouble(2, Rentals.getVehicle_id());
            pstmt.addBatch();
            pstmt.executeBatch();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
public class Rentals extends ConnectDb{
    private static double transact_id;
    private static double Vehicle_id;
    private static double cust_id;
    private static String rent_date=null;
    private static String return_date =null;
    Scanner sc=new Scanner(System.in);



    public void Booking(double cust_id,Connection con) {
        this.cust_id=cust_id;
     System.out.println("------Booking Process------");
     System.out.println("Enter the Corresponding Vehicle Id:");
     Vehicle_id=sc.nextDouble();
     System.out.println("Please Check The Availability of Vehicles\nEnter the Date of Hiring(YYYY-MM-DD):");
     rent_date= sc.next();
     System.out.println("Enter the Date of Returning(YYYY-MM-DD):");
     return_date=sc.next();




    Calculate c=new Calculate();
    c.setPriority(8);
    c.run();

        try {

            String query = "insert into rentals(transact_id,vehicle_id,cust_id,rent_date,return_date) " +
                    "values(nextval('rentals_transact'),?,?,?,?) returning transact_id";

            PreparedStatement pstmt=con.prepareStatement(query);
            pstmt.setDouble(1,Vehicle_id);
            pstmt.setDouble(2,cust_id);
            pstmt.setDate(3,c.Hire);
            pstmt.setDate(4,c.Return);
            pstmt.addBatch();
            pstmt.executeBatch();
            ResultSet rs= pstmt.executeQuery();

            if(rs.next())
            {
                System.out.println("Successfully Booked,\n Transact Id:"+rs.getDouble(1));
                System.out.println("Total Payable:"+Calculate.price);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

}
    public static double getVehicle_id() {
        return Vehicle_id;
    }

    public static String getRent_date() {
        return rent_date;
    }

    public static String getReturn_date() {
        return return_date;
    }

     public void View_Method(Double id,Connection con)
     {
        try
        {
            String query="select * from rentals" +
                        " where vehicle_id=?";
             PreparedStatement stmt=con.prepareStatement(query);
             stmt.setDouble(1,id);
            ResultSet rs= stmt.executeQuery();
            System.out.println("Vehicle_id"+" Cust_id"+" rent_date"+" return_date");
            System.out.println("-----------------------------------------------------------------");
            while (rs.next())
            {
                cust_id=(rs.getDouble("cust_id"));
                rent_date=(rs.getString("rent_date"));
                return_date=(rs.getString("return_date"));
                Vehicle_id=(rs.getDouble("vehicle_id"));


                System.out.println(Vehicle_id+" "+cust_id+" "+rent_date+" "+return_date);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
     }
}