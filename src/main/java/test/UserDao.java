package test;

import java.sql.*;
import java.util.*;

public class UserDao {

    // Connection method
    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/cruddemodb", // your DB
                "root", // your DB username
                "siya"  // your DB password
            );
        } catch (Exception e1) {
            System.out.println(e1);
        }
        return con; // DO NOT CLOSE HERE!
    }

    // Register user
    public static int save(User u) {
        int status = 0;
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(
                "insert into users(id, name, age, city, email, password) values(?,?,?,?,?,?)"
            );
            ps.setInt(1, u.getId());
            ps.setString(2, u.getName());
            ps.setInt(3, u.getAge());
            ps.setString(4, u.getCity());
            ps.setString(5, u.getEmail());
            ps.setString(6, u.getPassword());

            status = ps.executeUpdate(); 
            con.close();
        } catch (Exception e1) {
            System.out.println(e1);
        }
        return status;
    }

    // Login
    public static boolean validate(String email, String password) {
        boolean status = false;
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(
                "select * from users where email=? and password=?"
            );
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            status = rs.next();
            con.close();
        } catch (Exception e1) {
            System.out.println(e1);
        }
        return status;
    }

    // Pagination
    public static List<User> getRecords(int start, int limit) {
        List<User> list = new ArrayList<>();
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(
                "select * from users limit ?,?"
            );
            ps.setInt(1, start);
            ps.setInt(2, limit);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setName(rs.getString("name"));
                u.setAge(rs.getInt("age"));
                u.setCity(rs.getString("city"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                list.add(u);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    // Get user by ID
    public static User getUserById(int id) {
        User u = new User();
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(
                "select * from users where id=?"
            );
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                u.setId(rs.getInt("id"));
                u.setName(rs.getString("name"));
                u.setAge(rs.getInt("age"));
                u.setCity(rs.getString("city"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
            }
            con.close();
        } catch (Exception e1) {
            System.out.println(e1);
        }
        return u;
    }

    // Update user
    public static int update(User u) {
        int status = 0;
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(
                "update users set name=?, password=?, email=?, city=?, age=? where id=?"
            );
            ps.setString(1, u.getName());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getCity());
            ps.setInt(5, u.getAge());
            ps.setInt(6, u.getId());

            status = ps.executeUpdate();
            con.close();
        } catch (Exception e1) {
            System.out.println(e1);
        }
        return status;
    }

    // Delete user
    public static int delete(int id) {
        int status = 0;
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(
                "delete from users where id=?"
            );
            ps.setInt(1, id);
            status = ps.executeUpdate();
            con.close();
        } catch (Exception e1) {
            System.out.println(e1);
        }
        return status;
    }
}
