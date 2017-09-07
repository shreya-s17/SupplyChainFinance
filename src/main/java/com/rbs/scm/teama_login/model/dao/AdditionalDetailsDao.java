package com.rbs.scm.teama_login.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.rbs.scm.teama_login.model.beans.*;
public class AdditionalDetailsDao {

	private static ResultSet res = null;
	private static AdditionalDetails convertResToObject() throws SQLException{
		AdditionalDetails ad = null;
		if(res != null) {
			while(res.next()) {
				String username = res.getString(1);
				int contNumber = res.getInt(2);
				String postalLocation  = res.getString(3);
				String postalCity= res.getString(4);
				String postalState = res.getString(5);
				String factoryLocation = res.getString(6);
				String factoryCity = res.getString(7);
				String factoryState = res.getString(8);
				boolean is_valid = res.getBoolean(9);
				String department = res.getString(10);
				String swift=res.getString(11);
				int accNumber=res.getInt(12);
				ad = new AdditionalDetails(username,swift, accNumber,contNumber, postalLocation, postalCity, postalState, factoryLocation, factoryCity, factoryState, department, is_valid);
			}
		}
		return ad;
	}
	
	public static boolean insertIntoAdditionalDetails(AdditionalDetails add) throws SQLException {
		Connection conn = null;
		try {
			conn = SQLConnection.getConnection();
			PreparedStatement st = conn.prepareStatement("INSERT INTO \"Customer_Additional_Details\" values(?,?,?,?,?,?,?,?,?,?,?,?)");
			st.setString(1, add.getUsername());
			st.setInt(2, add.getContNumber());
			st.setString(3, add.getPostalLocation());
			st.setString(4, add.getPostalCity());
			st.setString(5, add.getPostalState());
			st.setString(6, add.getFactoryLocation());
			st.setString(7, add.getFactoryCity());
			st.setString(8, add.getFactoryState());
			st.setBoolean(9, true);
			st.setString(10, add.getDepartment());
			st.setString(11, add.getSwift());
			st.setInt(12, add.getAccNumber());
			st.executeUpdate();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return true;
	}

	public static AdditionalDetails search(String username) throws SQLException{  //username is email here
		res = null;
		Connection conn = null;
		try {
			
			conn = SQLConnection.getConnection();
			if(conn != null) {
				Statement st = conn.createStatement();
				String queryString = "select * from \"Customer_Additional_Details\" where \"Username\" ='" + username + "'";
				res = st.executeQuery(queryString);
				AdditionalDetails c = convertResToObject();
				return c;
			}
		
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}

		
}
