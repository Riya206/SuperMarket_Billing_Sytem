package com.wipro.market.dao;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wipro.market.bean.MarketBillBean;
import com.wipro.market.util.DBUtil;

public class MarketBillDAO {

    public String createRecord(MarketBillBean bean) {
        try (Connection con = DBUtil.getDBConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO MARKET_BILL_TB VALUES(?,?,?,?,?,?,?,?)");

            ps.setString(1, bean.getBillId());
            ps.setString(2, bean.getCustomerName());
            ps.setString(3, bean.getItemName());
            ps.setDate(4, new java.sql.Date(bean.getPurchaseDate().getTime()));
            ps.setInt(5, bean.getQuantity());
            ps.setDouble(6, bean.getPrice());
            ps.setDouble(7, bean.getTotalAmount());
            ps.setString(8, bean.getRemarks());

            int result = ps.executeUpdate();
            return result > 0 ? bean.getBillId() : "FAIL";

        } catch (Exception e) {
            e.printStackTrace();
            return "FAIL";
        }
    }

    public String generateBillID(String customerName, Date purchaseDate) {
        try (Connection con = DBUtil.getDBConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT MARKET_BILL_SEQ.NEXTVAL FROM DUAL")) {

            rs.next();
            int seq = rs.getInt(1);

            SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
            String datePart = f.format(purchaseDate);
            String namePart = customerName.substring(0, 2).toUpperCase();

            return datePart + namePart + seq;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean recordExists(String customerName, java.util.Date date) {
        try (Connection con = DBUtil.getDBConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM MARKET_BILL_TB WHERE CUSTOMERNAME=? AND PURCHASE_DATE=?");

            ps.setString(1, customerName);
            ps.setDate(2, new java.sql.Date(date.getTime()));

            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public MarketBillBean fetchRecord(String customerName, Date purchaseDate) {
        try (Connection con = DBUtil.getDBConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM MARKET_BILL_TB WHERE CUSTOMERNAME=? AND PURCHASE_DATE=?");

            ps.setString(1, customerName);
            ps.setDate(2, new java.sql.Date(purchaseDate.getTime()));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                MarketBillBean bean = new MarketBillBean();
                bean.setBillId(rs.getString(1));
                bean.setCustomerName(rs.getString(2));
                bean.setItemName(rs.getString(3));
                bean.setPurchaseDate(rs.getDate(4));
                bean.setQuantity(rs.getInt(5));
                bean.setPrice(rs.getDouble(6));
                bean.setTotalAmount(rs.getDouble(7));
                bean.setRemarks(rs.getString(8));
                return bean;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<MarketBillBean> fetchAllRecords() {
        List<MarketBillBean> list = new ArrayList<>();
        try (Connection con = DBUtil.getDBConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM MARKET_BILL_TB")) {

            while (rs.next()) {
                MarketBillBean bean = new MarketBillBean();
                bean.setBillId(rs.getString(1));
                bean.setCustomerName(rs.getString(2));
                bean.setItemName(rs.getString(3));
                bean.setPurchaseDate(rs.getDate(4));
                bean.setQuantity(rs.getInt(5));
                bean.setPrice(rs.getDouble(6));
                bean.setTotalAmount(rs.getDouble(7));
                bean.setRemarks(rs.getString(8));
                list.add(bean);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
