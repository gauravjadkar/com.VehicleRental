# Vehicle Rental System

### A simple CRUD console-based Java application that allow Vehicle booking,search Vehicle,Vehicle Register,Customer Register and Login.
------------

##  🌟Features
- ####  ✅Vehicle Booking
- #### ✅Vehicle search
- #### ✅Add Vehicle (admin panel)
- #### ✅Menu Driven
- #### ✅Customer Registeration
- #### ✅Booking history


## # 🤖 prerequisite
- ####  Postgresql (16+)
- #### JDBC
- #### Java
- #### IDE (eclipse,Intellij)

------------
## Structure
| com.VehicleRental
- |- src
  -  |-main
     -   |-java
          -    |-org.example
                   -    |-ConnectDb.java
                    -   |-Main.java
                        |-Vehicle.java
                        |-Customer.java
                        |-Rentals.java
## Dependencies
```
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>42.6.0</version>
</dependency>

```
## SQL Queries

create table vehicles
(id serial primary key,
type varchar(100) not null,
brand varchar(100),
model varchar(100),
rate_per_day numeric(7,2),
is_available date)

create table customer
(cust_id serial primary key,
cust_name varchar(100),
phone numeric(100),
license_no varchar(15))

create table Rentals
(transact_id serial primary key,
vehicle_id numeric(2),
cust_id numeric(2),
rent_date date,
return_date)

### 📷 Menu Drive (Output)
[![](https://github.com/gauravjadkar/com.VehicleRental/blob/master/src/main/resources/Screenshot%202025-07-10%20160800.png)]

## References
### Group Members
- Gaurav Jadkar
- Koushik Chilveri
### College 
- SES Polytechnic



