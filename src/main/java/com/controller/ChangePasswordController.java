package com.controller;

import java.io.IOException;

import org.mindrot.jbcrypt.BCrypt;

import com.dao.UserDAO;
import com.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/change-password")
public class ChangePasswordController extends HttpServlet {
    
    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Show change password form
        request.getRequestDispatcher("/views/change-password.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // TODO: Get current user from session
        // TODO: Get form parameters (currentPassword, newPassword, confirmPassword)
        // TODO: Validate current password
        // TODO: Validate new password (length, match)
        // TODO: Hash new password
        // TODO: Update in database
        // TODO: Show success/error message
        
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");
        
        // Security check: Ensure user is logged in
        if (currentUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        
        // 1. Validate: New passwords match
        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("error", "New passwords do not match!");
            request.getRequestDispatcher("/views/change-password.jsp").forward(request, response);
            return;
        }
        
        // 2. Validate: Minimum length
        if (newPassword.length() < 8) {
            request.setAttribute("error", "New password must be at least 8 characters!");
            request.getRequestDispatcher("/views/change-password.jsp").forward(request, response);
            return;
        }

        // 3. Validate: Current password is correct
        // We fetch the user from DB again to get the most up-to-date password hash
        User dbUser = userDAO.getUserById(currentUser.getId());
        
        if (dbUser != null && BCrypt.checkpw(currentPassword, dbUser.getPassword())) {
            // 4. Hash new password
            String newHashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
            
            // 5. Update in database
            if (userDAO.updatePassword(currentUser.getId(), newHashedPassword)) {
                request.setAttribute("message", "Password changed successfully!");
            } else {
                request.setAttribute("error", "Database error occurred.");
            }
        } else {
            request.setAttribute("error", "Incorrect current password!");
        }
        
        request.getRequestDispatcher("/views/change-password.jsp").forward(request, response);
    }
}
