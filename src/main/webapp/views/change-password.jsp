<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Change Password</title>
    <style>
        body { font-family: 'Segoe UI', sans-serif; background: #f4f7f6; display: flex; justify-content: center; align-items: center; min-height: 100vh; margin: 0; }
        .container { background: white; padding: 40px; border-radius: 10px; box-shadow: 0 4px 15px rgba(0,0,0,0.1); width: 100%; max-width: 450px; }
        h2 { text-align: center; color: #333; margin-bottom: 30px; }
        .form-group { margin-bottom: 20px; }
        label { display: block; margin-bottom: 8px; color: #666; font-weight: 500; }
        input[type="password"] { width: 100%; padding: 12px; border: 1px solid #ddd; border-radius: 5px; box-sizing: border-box; transition: border 0.3s; }
        input[type="password"]:focus { border-color: #667eea; outline: none; }
        
        .btn-primary { width: 100%; padding: 12px; background: #667eea; color: white; border: none; border-radius: 5px; font-size: 16px; cursor: pointer; transition: background 0.3s; }
        .btn-primary:hover { background: #5a67d8; }
        
        .btn-secondary { display: block; text-align: center; margin-top: 15px; color: #666; text-decoration: none; font-size: 14px; }
        .btn-secondary:hover { color: #333; }

        .message { padding: 10px; border-radius: 5px; margin-bottom: 20px; text-align: center; font-size: 14px; }
        .error { background: #fee2e2; color: #b91c1c; border: 1px solid #fca5a5; }
        .success { background: #d1fae5; color: #065f46; border: 1px solid #6ee7b7; }
    </style>
</head>
<body>

<div class="container">
    <h2>🔒 Change Password</h2>

    <c:if test="${not empty error}">
        <div class="message error">${error}</div>
    </c:if>
    <c:if test="${not empty message}">
        <div class="message success">${message}</div>
    </c:if>

    <form action="change-password" method="POST">
        <div class="form-group">
            <label>Current Password</label>
            <input type="password" name="currentPassword" required>
        </div>

        <div class="form-group">
            <label>New Password (min 8 chars)</label>
            <input type="password" name="newPassword" minlength="8" required>
        </div>

        <div class="form-group">
            <label>Confirm New Password</label>
            <input type="password" name="confirmPassword" minlength="8" required>
        </div>

        <button type="submit" class="btn-primary">Update Password</button>
        <a href="dashboard" class="btn-secondary">← Back to Dashboard</a>
    </form>
</div>

</body>
</html>