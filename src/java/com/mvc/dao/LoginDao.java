package com.mvc.dao;

import java.sql.*;
import com.mvc.bean.LoginBean;
import com.mvc.util.DBConnection;

public class LoginDao {

    public LoginBean authenticateUser(LoginBean loginBean) {
        String email = loginBean.getEmail();
        String password = loginBean.getPassword();
        String role = loginBean.getRole(); // "client" or "admin"

        String sql = "";

        if ("client".equalsIgnoreCase(role)) {
            sql = "SELECT * FROM CLIENT WHERE clientEmail = ? AND clientPassword = ?";
        } else if ("admin".equalsIgnoreCase(role)) {
            sql = "SELECT * FROM ADMIN WHERE adminEmail = ? AND adminPassword = ?";
        } else {
            return null; // invalid role
        }

        try (Connection con = DBConnection.createConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                LoginBean user = new LoginBean();
                user.setRole(role);

                if ("client".equalsIgnoreCase(role)) {
                    user.setID(rs.getInt("clientID"));
                    user.setName(rs.getString("clientName"));
                    user.setEmail(rs.getString("clientEmail"));
                    user.setPassword(rs.getString("clientPassword"));
                } else {
                    user.setID(rs.getInt("adminID"));
                    user.setName(rs.getString("adminName"));
                    user.setEmail(rs.getString("adminEmail"));
                    user.setPassword(rs.getString("adminPassword"));
                }

                return user;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null; // login failed
    }
}
