package service;

import database.DbConnection;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerService {
    public static Customer checkLogin(String email, String password) {
        List<Customer> customers = new ArrayList<>();
        DbConnection connectDB = DbConnection.getInstance();
        String sql = "SELECT id_user_customer, username, pass, id_status_acc, id_city, fullname, phone_customer, address " +
                "from account_customer where username = ?";
        PreparedStatement preState = connectDB.getPreparedStatement(sql);
        try {
            preState.setString(1, email);
            ResultSet rs = preState.executeQuery();
            while (rs.next()) {
                int id_customer = rs.getInt("id_user_customer");
                String email_customer = rs.getString("username");
                String password_customer = rs.getString("pass");
                int id_status_acc_customer = rs.getInt("id_status_acc");
                int id_city_customer = rs.getInt("id_city");
                String fullname_customer = rs.getString("fullname");
                String phone = rs.getString("phone_customer");
                String address = rs.getString("address");
                Customer customer = new Customer(id_customer, email_customer, password_customer, id_status_acc_customer,
                        id_city_customer, fullname_customer, phone, address);
                customers.add(customer);
            }
            if (customers.size() != 1) {
                return null;
            } else {
                Customer unique = customers.get(0);
                if (unique.getPassword().equals(password)) {
                    return unique;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connectDB.close();
        }
        return null;
    }

    public static boolean changePass(String newPass, String email) {
        DbConnection connectDb = DbConnection.getInstance();
        String sql = "UPDATE account_customer " +
                "SET pass = ?, time_change_pass = current_timestamp()" +
                " WHERE username = ?";
        PreparedStatement preState = connectDb.getPreparedStatement(sql);
        try {
            preState.setString(1, newPass);
            preState.setString(2, email);
            int update = preState.executeUpdate();
            if (update > 0) {
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            connectDb.close();
        }
        return false;
    }

    public static boolean checkExist(String email) {
        DbConnection connectDb = DbConnection.getInstance();
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT id_user_customer, username, pass, id_status_acc, id_city, fullname, phone_customer, address " +
                "from account_customer where username = ?";
        PreparedStatement preState = connectDb.getPreparedStatement(sql);
        try {
            preState.setString(1, email);
            ResultSet rs = preState.executeQuery();
            while (rs.next()) {
                int id_customer = rs.getInt("id_user_customer");
                String email_customer = rs.getString("username");
                String password_customer = rs.getString("pass");
                int id_status_acc_customer = rs.getInt("id_status_acc");
                int id_city_customer = rs.getInt("id_city");
                String fullname_customer = rs.getString("fullname");
                String phone = rs.getString("phone_customer");
                String address = rs.getString("address");
                Customer customer = new Customer(id_customer, email_customer, password_customer, id_status_acc_customer,
                        id_city_customer, fullname_customer, phone, address);
                customers.add(customer);
            }
            if (customers.size() == 0) {
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException();
        } finally {
            connectDb.close();
        }
        return false;
    }

    public static void signUp(String email, String password) {
        DbConnection connectDb = DbConnection.getInstance();
        String sql = "INSERT INTO account_customer(username, pass, id_status_acc, id_city) " +
                "VALUES(?, ?, 1, 1)";
        PreparedStatement preState = connectDb.getPreparedStatement(sql);
        try {
            preState.setString(1, email);
            preState.setString(2, password);
            preState.executeUpdate();
            System.out.println("success");
        } catch (Exception e) {
            throw new RuntimeException();
        } finally {
            connectDb.close();
        }
    }

    public static List<Customer> getRecentCustomers(int time) {
        List<Customer> customers = new ArrayList<>();
        try (PreparedStatement ps = DbConnection.getInstance().getPreparedStatement(
                "SELECT id_user_customer, fullname, sex, phone_customer, address, time_created, s.name_status_acc " +
                        "FROM account_customer a JOIN status_acc s ON a.id_status_acc = s.id_status_acc " +
                        "WHERE DATE(time_created) > (NOW() - INTERVAL ? DAY)")) {
            ps.setInt(1, time);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Customer c = new Customer();
                c.setId(rs.getInt("id_user_customer"));
                c.setSex(rs.getString("sex"));
                c.setPhone(rs.getString("phone_customer"));
                c.setAddress(rs.getString("address"));
                c.setFullname(rs.getString("fullname"));
                c.setTimeCreated(rs.getDate("time_created"));
                c.setStatus(rs.getString("name_status_acc"));
                customers.add(c);
            }
        } catch (SQLException e) {
            return new ArrayList<>();
        }
        return customers;
    }

    public static double getTransportFee(int cityId) {
        try (var ps = DbConnection.getInstance().getPreparedStatement("SELECT transport FROM city WHERE id_city = ?")) {
            ps.setInt(1, cityId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getDouble("transport");
        } catch (SQLException e) {
            return -1;
        }
    }

    public static Map<Integer, String> getCities() {
        Map<Integer, String> map = new HashMap<>();
        try (var ps = DbConnection.getInstance().getPreparedStatement("SELECT id_city, name_city FROM city")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                map.put(rs.getInt("id_city"), rs.getString("name_city"));
            }
            return map;
        } catch (SQLException e) {
            return new HashMap<>();
        }
    }

    public static int addBill(int userId, int cityId, String name, String phone, String email, String address, double price, double totalPrice) {
        try (var ps = DbConnection.getInstance()
                .getPreparedStatement("INSERT INTO bills VALUES (0,?,4,?,?,?,?,?,?,?,NOW())")) {
            ps.setInt(1, userId);
            ps.setInt(2, cityId);
            ps.setString(3, name);
            ps.setString(4, phone);
            ps.setString(5, email);
            ps.setString(6, address);
            ps.setDouble(7, price);
            ps.setDouble(8, totalPrice);
            ps.executeUpdate();
            try (var ps2 = DbConnection.getInstance().getPreparedStatement("SELECT LAST_INSERT_ID()")) {
                ResultSet rs = ps2.executeQuery();
                rs.next();
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            return -1;
        }
    }

    public static void addBillDetail(int billId, int productId, int quantity, double listedPrice, double currentPrice) {
        try (var ps = DbConnection.getInstance()
                .getPreparedStatement("INSERT INTO bill_detail VALUES (?,?,?,?,?)")) {
            ps.setInt(1, billId);
            ps.setInt(2, productId);
            ps.setInt(3, quantity);
            ps.setDouble(4, listedPrice);
            ps.setDouble(5, currentPrice);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        int id = addBill(4, 1, "John Wick", "0865744333", "john@gmail.com", "12, Honest St", 12000, 15000);
        System.out.println(id);
    }
}