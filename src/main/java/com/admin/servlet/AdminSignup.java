package com.admin.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/adminregister")
public class AdminSignup extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        try {
            // Use a DataSource instead of DriverManager for better connection pooling
            // Configure your DataSource in the application server (e.g., Tomcat) or use a library like HikariCP
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "29122003");

            // Use try-with-resources to automatically close resources (Connection, PreparedStatement)
            try (PreparedStatement ps = con.prepareStatement("INSERT INTO user1 (name, email, password) VALUES (?, ?, ?)")) {
                // Use parameterized PreparedStatement to prevent SQL injection
                ps.setString(1, request.getParameter("fullname"));
                ps.setString(2, request.getParameter("email"));
                ps.setString(3, request.getParameter("password"));
                ps.execute(); // Use executeUpdate for INSERT, UPDATE, DELETE queries
            }

            // Redirect to index.jsp after successful signup
            response.sendRedirect("index.jsp");

        } catch (SQLException e) {
            // Handle exceptions appropriately (e.g., logging, sending error response)
            e.printStackTrace();
        }
    }
}
