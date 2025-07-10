package org.example;
import java.sql.*;
import java.util.Scanner;

public class Customer extends ConnectDb {
    private String cust_name=null;
    private String phone=null;
    private String license_no=null;
    private double id;
     Scanner sc=new Scanner(System.in);
    public double getMethod(Connection con)
    {
        try
        {
            System.out.println("Enter Your Info:");
            System.out.println("Name:");
            cust_name=sc.next();
            System.out.println("Phone No:");
            phone=sc.next();
            System.out.println("License No:");
            license_no=sc.next();
            String query="insert into Customer(cust_id,cust_name,phone_no,license_number)"+
                          "values(nextval('custom'),?,?,?) returning cust_id";
            PreparedStatement pstmt= con.prepareStatement(query);
            pstmt.setString(1,cust_name);
            pstmt.setString(2,phone);
            pstmt.setString(3,license_no);
            pstmt.addBatch();
            pstmt.executeBatch();
            ResultSet rs=pstmt.executeQuery();
            if(rs.next())
            {
             id=rs.getDouble(1);
                System.out.println("Successfully Created Customer id:"+rs.getDouble(1));
            }
        }
        catch (Exception e)
        {
            System.out.println("Sorry,its an Error please insert Suitable value");
        }

        return id;
    }

    public void View_Method(Double id,Connection con)
    {
        try
        {

            String query="select * from customer" +
                    " where cust_id=?";
            PreparedStatement pstmt=con.prepareStatement(query);
            pstmt.setDouble(1,id);
            ResultSet rs= pstmt.executeQuery();
            System.out.println("cust_id"+"    Customer_name"+"    phone_no"+"   license_no");
            System.out.println("-----------------------------------------------------------------");
            while (rs.next())
            {
                id=rs.getDouble("cust_id");
                cust_name=rs.getString("cust_name");
                phone=rs.getString("phone_no");
                license_no=rs.getString("license_number");


                System.out.println(" "+id+"  |"+cust_name+"    |"+phone+"   |"+license_no);


            }
        } catch (Exception e) {
            System.out.println("Your id doesn't exist, Please Sign up");
        }
    }


}
