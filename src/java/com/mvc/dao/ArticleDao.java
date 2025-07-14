package com.mvc.dao;

import com.mvc.bean.ArticleBean;
import com.mvc.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDao {

    public static boolean insertArticle(ArticleBean bean) {
        boolean status = false;
        try {
            Connection con = DBConnection.createConnection();

            // Step 1: Get max ArticleID manually
            /*Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MAX(ArticleID) FROM Article");
            int nextId = 1;
            if (rs.next()) {
                nextId = rs.getInt(1) + 1;
            }
            rs.close();
            stmt.close();*/
            // Step 2: Insert article with manual ID
            String sql = "INSERT INTO Article (ArticleTitle, ArticleDescription, ArticleDate, ArticleApproval, ClientID, AdminID) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, bean.getArticleTitle());
            ps.setString(2, bean.getArticleDescription());
            ps.setDate(3, bean.getArticleDate());
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

    /*public static List<ArticleBean> getAllArticles() {
        List<ArticleBean> list = new ArrayList<>();
        try {
            Connection con = DBConnection.createConnection();

            // Join with Client and Admin tables to get names
            String sql = "SELECT a.ArticleID, a.ArticleDescription, a.ArticleDate, a.ArticleApproval, " +
                         "c.ClientName, ad.AdminName " +
                         "FROM Article a " +
                         "JOIN Client c ON a.ClientID = c.ClientID " +
                         "JOIN Admin ad ON a.AdminID = ad.AdminID";

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ArticleBean bean = new ArticleBean();
                bean.setArticleID(rs.getInt("ArticleID"));
                bean.setArticleTitle(rs.getString("ArticleTitle"));
                bean.setArticleDescription(rs.getString("ArticleDescription"));
                bean.setArticleDate(rs.getDate("ArticleDate"));
                bean.setArticleApproval(rs.getString("ArticleApproval"));
                bean.setClientName(rs.getString("ClientName"));
                bean.setAdminName(rs.getString("AdminName"));
                list.add(bean);
            }

            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }*/
    
    public static List<ArticleBean> getAllArticles() {
        List<ArticleBean> list = new ArrayList<>();
        try {
            Connection con = DBConnection.createConnection();
            String sql = "SELECT * FROM Article";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ArticleBean bean = new ArticleBean();
                bean.setArticleID(rs.getInt("ArticleID"));
                bean.setArticleTitle(rs.getString("ArticleTitle"));
                bean.setArticleDescription(rs.getString("ArticleDescription"));
                bean.setArticleDate(rs.getDate("ArticleDate"));
                bean.setArticleApproval(rs.getString("ArticleApproval"));
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

    public static List<ArticleBean> getPendingArticles() {
        List<ArticleBean> list = new ArrayList<>();
        try {
            Connection con = DBConnection.createConnection();
            String sql = "SELECT a.ArticleID, a.ArticleTitle, a.ArticleDescription, a.ArticleDate, a.ArticleApproval, "
                    + "c.ClientName "
                    + "FROM Article a "
                    + "JOIN Client c ON a.ClientID = c.ClientID "
                    + "WHERE a.ArticleApproval = 'Pending'";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ArticleBean article = new ArticleBean();
                article.setArticleID(rs.getInt("ArticleID"));
                article.setArticleTitle(rs.getString("ArticleTitle"));
                article.setArticleDescription(rs.getString("ArticleDescription"));
                article.setArticleDate(rs.getDate("ArticleDate"));
                article.setArticleApproval(rs.getString("ArticleApproval"));
                article.setClientName(rs.getString("ClientName")); // Set ClientName instead of ID
                list.add(article);
            }

            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean updateArticleStatus(int articleID, String status, int adminID) {
        boolean updated = false;
        try {
            Connection con = DBConnection.createConnection();
            String sql = "UPDATE Article SET ArticleApproval = ?, AdminID = ? WHERE ArticleID = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, adminID);
            ps.setInt(3, articleID);

            int rows = ps.executeUpdate();
            updated = rows > 0;

            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updated;
    }

    public static boolean deleteArticle(int articleID) {
        boolean status = false;
        try {
            Connection con = DBConnection.createConnection();
            String sql = "DELETE FROM Article WHERE ArticleID = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, articleID);

            int rowsAffected = ps.executeUpdate();
            status = rowsAffected > 0;

            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public static ArticleBean getArticleById(int articleID) {
        ArticleBean article = null;
        try {
            Connection con = DBConnection.createConnection();
            String sql = "SELECT * FROM Article WHERE ArticleID = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, articleID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                article = new ArticleBean();
                article.setArticleID(rs.getInt("ArticleID"));
                article.setArticleTitle(rs.getString("ArticleTitle"));
                article.setArticleDescription(rs.getString("ArticleDescription"));
                article.setArticleDate(rs.getDate("ArticleDate"));
                article.setArticleApproval(rs.getString("ArticleApproval"));
                article.setClientID(rs.getInt("ClientID"));
                article.setAdminID(rs.getInt("AdminID"));
            }

            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return article;
    }

    public static boolean updateArticle(ArticleBean article) {
        boolean status = false;
        try {
            Connection con = DBConnection.createConnection();
            String sql = "UPDATE Article SET ArticleTitle = ?, ArticleDescription = ? WHERE ArticleID = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, article.getArticleTitle());
            ps.setString(2, article.getArticleDescription());
            ps.setInt(3, article.getArticleID());

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
