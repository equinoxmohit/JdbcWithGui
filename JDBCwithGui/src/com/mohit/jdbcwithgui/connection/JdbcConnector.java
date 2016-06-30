/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mohit.jdbcwithgui.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Mohit
 */
public class JdbcConnector {

    Connection conn = null;
    PreparedStatement stmnt;

    public void open() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost/database_courses", "root", "");
    }

    public PreparedStatement initStatements(String sql) throws SQLException {
        stmnt = conn.prepareStatement(sql);
        return stmnt;

    }

    public int update() throws SQLException {
        return stmnt.executeUpdate();
    }

    public ResultSet query() throws SQLException {
        return stmnt.executeQuery();

    }

    public void close() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
            conn = null;
        }
    }
}
