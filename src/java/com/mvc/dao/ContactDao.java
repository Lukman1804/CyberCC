package com.mvc.dao;

import com.mvc.bean.ContactBean;
import com.mvc.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDao {

    public static List<ContactBean> getAllContacts() {
        List<ContactBean> contacts = new ArrayList<>();
        try {
            Connection con = DBConnection.createConnection();
            String sql = "SELECT * FROM Contact";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ContactBean contact = new ContactBean();
                contact.setContactID(rs.getInt("contactID"));
                contact.setContactHeader(rs.getString("contactHeader"));
                contact.setContactDescription(rs.getString("contactDescription"));
                contact.setContactExtra(rs.getString("contactExtra"));

                contacts.add(contact);
            }

            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contacts;
    }
    
    public static boolean insertContact(ContactBean contact) {
    try (Connection con = DBConnection.createConnection()) {
        String sql = "INSERT INTO Contact (ContactHeader, ContactDescription, ContactExtra) VALUES (?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, contact.getContactHeader());
        ps.setString(2, contact.getContactDescription());
        ps.setString(3, contact.getContactExtra());
        
        return ps.executeUpdate() > 0;
        
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
    }
    
    public void deleteContactById(int id) {
    String sql = "DELETE FROM contact WHERE contactID = ?";
    try (Connection con = DBConnection.createConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, id);
        ps.executeUpdate();
        
        ps.close();
        con.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
    
    public ContactBean getContactById(int id) {
    String sql = "SELECT * FROM contact WHERE contactID = ?";
    try (Connection conn = DBConnection.createConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            ContactBean contact = new ContactBean();
            contact.setContactID(rs.getInt("contactID"));
            contact.setContactHeader(rs.getString("contactHeader"));
            contact.setContactDescription(rs.getString("contactDescription"));
            contact.setContactExtra(rs.getString("contactExtra"));
            return contact;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
    }
    
    public void updateContact(ContactBean contact) {
    String sql = "UPDATE contact SET contactHeader = ?, contactDescription = ?, contactExtra = ? WHERE contactID = ?";
    try (Connection conn = DBConnection.createConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, contact.getContactHeader());
        ps.setString(2, contact.getContactDescription());
        ps.setString(3, contact.getContactExtra());
        ps.setInt(4, contact.getContactID());
        ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
}
