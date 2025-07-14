package com.mvc.dao;

import com.mvc.bean.ReportBean;
import com.mvc.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportDao {

    public static boolean insertReport(ReportBean bean) {
        boolean status = false;
        try {
            Connection con = DBConnection.createConnection();
            String sql = "INSERT INTO Report (ReportTitle, ReportDescription, ReportDate, ReportApproval, ClientID, AdminID) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, bean.getReportTitle());
            ps.setString(2, bean.getReportDescription());
            ps.setDate(3, bean.getReportDate());
            ps.setString(4, "Pending");
            ps.setInt(5, bean.getClientID());
            ps.setInt(6, bean.getAdminID());

            status = ps.executeUpdate() > 0;

            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status; 
    }

    public static List<ReportBean> getAllReports() {
        List<ReportBean> list = new ArrayList<>();
        try {
            Connection con = DBConnection.createConnection();
            String sql = "SELECT * FROM Report";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ReportBean bean = new ReportBean();
                bean.setReportID(rs.getInt("ReportID"));
                bean.setReportTitle(rs.getString("ReportTitle"));
                bean.setReportDescription(rs.getString("ReportDescription"));
                bean.setReportDate(rs.getDate("ReportDate"));
                bean.setReportApproval(rs.getString("ReportApproval"));
                bean.setClientID(rs.getInt("ClientID"));
                bean.setAdminID(rs.getInt("AdminID"));
                list.add(bean);
            }

            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<ReportBean> getPendingReports() {
        List<ReportBean> list = new ArrayList<>();
        try {
            Connection con = DBConnection.createConnection();
            String sql = "SELECT r.ReportID, r.ReportTitle, r.ReportDescription, r.ReportDate, r.ReportApproval, " +
                         "c.ClientName " +
                         "FROM Report r " +
                         "JOIN Client c ON r.ClientID = c.ClientID " +
                         "WHERE r.ReportApproval = 'Pending'";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ReportBean report = new ReportBean();
                report.setReportID(rs.getInt("ReportID"));
                report.setReportTitle(rs.getString("ReportTitle"));
                report.setReportDescription(rs.getString("ReportDescription"));
                report.setReportDate(rs.getDate("ReportDate"));
                report.setReportApproval(rs.getString("ReportApproval"));
                report.setClientName(rs.getString("ClientName"));
                list.add(report);
            }

            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean updateReportStatus(int reportID, String status, int adminID) {
        boolean updated = false;
        try {
            Connection con = DBConnection.createConnection();
            String sql = "UPDATE Report SET ReportApproval = ?, AdminID = ? WHERE ReportID = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, adminID);
            ps.setInt(3, reportID);

            int rows = ps.executeUpdate();
            updated = rows > 0;

            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updated;
    }

    public static boolean deleteReport(int reportID) {
        boolean status = false;
        try {
            Connection con = DBConnection.createConnection();
            String sql = "DELETE FROM Report WHERE ReportID = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, reportID);

            int rowsAffected = ps.executeUpdate();
            status = rowsAffected > 0;

            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public static ReportBean getReportById(int reportID) {
        ReportBean report = null;
        try {
            Connection con = DBConnection.createConnection();
            String sql = "SELECT * FROM Report WHERE ReportID = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, reportID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                report = new ReportBean();
                report.setReportID(rs.getInt("ReportID"));
                report.setReportTitle(rs.getString("ReportTitle"));
                report.setReportDescription(rs.getString("ReportDescription"));
                report.setReportDate(rs.getDate("ReportDate"));
                report.setReportApproval(rs.getString("ReportApproval"));
                report.setClientID(rs.getInt("ClientID"));
                report.setAdminID(rs.getInt("AdminID"));
            }

            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return report;
    }

    public static boolean updateReport(ReportBean report) {
        boolean status = false;
        try {
            Connection con = DBConnection.createConnection();
            String sql = "UPDATE Report SET ReportTitle = ?, ReportDescription = ? WHERE ReportID = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, report.getReportTitle());
            ps.setString(2, report.getReportDescription());
            ps.setInt(3, report.getReportID());

            int rows = ps.executeUpdate();
            status = rows > 0;

            ps.close(); 
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
