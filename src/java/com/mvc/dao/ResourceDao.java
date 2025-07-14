package com.mvc.dao;

import com.mvc.bean.ResourceBean;
import com.mvc.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResourceDao {

    public static List<ResourceBean> getAllResources() {
        List<ResourceBean> resources = new ArrayList<>();
        try {
            Connection con = DBConnection.createConnection();
            String sql = "SELECT * FROM Resource";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ResourceBean resource = new ResourceBean();
                resource.setResourceID(rs.getInt("resourceID"));
                resource.setResourceHeader(rs.getString("resourceHeader"));
                resource.setResourceDescription(rs.getString("resourceDescription"));
                resource.setResourceLink(rs.getString("resourceLink"));

                resources.add(resource);
            }

            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resources;
    }
    
    public static boolean insertResource(ResourceBean resource) {
    try (Connection con = DBConnection.createConnection()) {
        String sql = "INSERT INTO Resource (ResourceHeader, ResourceDescription, ResourceLink) VALUES (?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, resource.getResourceHeader());
        ps.setString(2, resource.getResourceDescription());
        ps.setString(3, resource.getResourceLink());
        return ps.executeUpdate() > 0;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
    }
    
    public void deleteResourceById(int id) {
    String sql = "DELETE FROM resource WHERE resourceID = ?";
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
    
    public ResourceBean getResourceById(int id) {
    String sql = "SELECT * FROM resource WHERE resourceID = ?";
    try (Connection conn = DBConnection.createConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            ResourceBean res = new ResourceBean();
            res.setResourceID(rs.getInt("resourceID"));
            res.setResourceHeader(rs.getString("resourceHeader"));
            res.setResourceDescription(rs.getString("resourceDescription"));
            res.setResourceLink(rs.getString("resourceLink"));
            return res;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}
    
    public void updateResource(ResourceBean resource) {
    String sql = "UPDATE resource SET resourceHeader = ?, resourceDescription = ?, resourceLink = ? WHERE resourceID = ?";
    try (Connection conn = DBConnection.createConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, resource.getResourceHeader());
        ps.setString(2, resource.getResourceDescription());
        ps.setString(3, resource.getResourceLink());
        ps.setInt(4, resource.getResourceID());
        ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
}
