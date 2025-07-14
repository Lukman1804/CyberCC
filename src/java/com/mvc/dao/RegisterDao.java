package com.mvc.dao;

import com.mvc.bean.LoginBean;
import com.mvc.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RegisterDao {
    public static boolean register(LoginBean bean) {
        boolean status = false;

        try {
            Connection con = DBConnection.createConnection();

            // Step 1: Get the max clientID
            String getMaxIdQuery = "SELECT MAX(clientID) FROM users";
            PreparedStatement psMax = con.prepareStatement(getMaxIdQuery);
            ResultSet rs = psMax.executeQuery();

            int nextClientId = 1;
            if (rs.next()) {
                int currentMax = rs.getInt(1);
                nextClientId = currentMax + 1;
            }

            rs.close();
            psMax.close();

            // Step 2: Insert with the new clientID
            String sql = "INSERT INTO users (clientID, name, email, password) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, nextClientId);
            ps.setString(2, bean.getName());
            ps.setString(3, bean.getEmail());
            ps.setString(4, bean.getPassword());

            int rowsInserted = ps.executeUpdate();
            status = rowsInserted > 0;

            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }
}
