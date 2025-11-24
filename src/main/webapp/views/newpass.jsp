<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head><title>Đổi mật khẩu</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"></head>
<body class="d-flex justify-content-center align-items-center vh-100 bg-light">
    <div class="card p-4 shadow" style="width: 400px;">
        <h3 class="text-center text-primary">Đặt mật khẩu mới</h3>
        <c:if test="${alert != null}"><div class="alert alert-danger">${alert}</div></c:if>
        <form action="${pageContext.request.contextPath}/update-password" method="post">
            <div class="mb-3">
                <label>Mật khẩu mới:</label>
                <input type="password" name="newPassword" class="form-control" required>
            </div>
            <div class="mb-3">
                <label>Nhập lại mật khẩu:</label>
                <input type="password" name="confirmPassword" class="form-control" required>
            </div>
            <button type="submit" class="btn btn-primary w-100">Đổi mật khẩu</button>
        </form>
    </div>
</body>
</html>