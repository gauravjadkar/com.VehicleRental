package org.example;

import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;



public class Vehicle extends ConnectDb {

    private static double id;
    private String type=null;
    private String brand=null;
    private String model=null;
    private static long rate_per_day;
    private static String is_available;
   Scanner sc=new Scanner(System.in);


public static String getIs_available()
{

    return is_available;
}
    public static long getRate_per_day(double vehicleId) {
        try(Connection con=Vehicle.getConnect()){

            String query = "select rate_per_day from vehicles " +
                    "where id=?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setDouble(1, vehicleId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                rate_per_day =rs.getLong("rate_per_day");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return rate_per_day;
    }

    public double get_method(Connection con)
    {
        System.out.println("Enter the Vehicle Info:");
        System.out.println("Type:");
        type=sc.next();
        System.out.println("Brand:");
        brand= sc.next();
        System.out.println("Model:");
        model= sc.next();
        System.out.println("Rate Per Day:");
        rate_per_day= sc.nextLong();
        System.out.println("Available(YYYY-MM-DD):");
        is_available=sc.next();


        try
        {
            Class.forName("org.postgresql.Driver");
            String query="insert into vehicles(id,type,brand,model,rate_per_day,is_available) "+
                          "values(nextval('vehicle_id'),?,?,?,?,?) returning id";
            Date date=Date.valueOf(is_available);
            PreparedStatement pstmt=con.prepareStatement(query);

            pstmt.setString(1,type);
            pstmt.setString(2,brand);
            pstmt.setString(3,model);
            pstmt.setLong(4,rate_per_day);
            pstmt.setDate(5,date);
            pstmt.addBatch();
            pstmt.executeBatch();
            ResultSet rs=pstmt.executeQuery();

            if(rs.next())
            {
                id=rs.getDouble(1);
                System.out.println("Successfully Created Vehicle id:"+rs.getDouble(1));
            }



        } catch (Exception e) {
           // throw new RuntimeException(e);
            System.out.println("Sorry,its an Error please insert Suitable value");
        }

     return id;
    }

       public void View_Method(Double id,int flag,Connection con)
       {
            this.id=id;
           try
           {
               String query="select * from vehicles";

              if(flag==1) {
                  query = "select * from vehicles" +
                          " where id=?";

              }
               PreparedStatement stmt= con.prepareStatement(query);
              if(flag==1)
              {
                  stmt.setDouble(1,this.id);
              }
               ResultSet rs=stmt.executeQuery();
               System.out.println("Vehicle_id"+"    type"+"    brand"+"   model"+"    rate_per_day"+"    is_available");
               System.out.println("-----------------------------------------------------------------");
               while (rs.next())
               {id=rs.getDouble("id");
                 type=rs.getString("type");
                 brand=rs.getString("brand");
                 model=rs.getString("model");
                 rate_per_day=rs.getLong("rate_per_day");
                 is_available=rs.getString("is_available");

                   System.out.println(" "+id+"  |"+type+"    |"+brand+"   |"+model+"   |"+rate_per_day+"    |"+is_available);


               }
           } catch (Exception e) {
              System.out.println("Your id doesn't exist, Please Sign up");

           }
       }
   }

