<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head><title>Quên mật khẩu</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"></head>
<body class="d-flex justify-content-center align-items-center vh-100 bg-light">
    <div class="card p-4 shadow" style="width: 400px;">
        <h3 class="text-center mb-3">Khôi phục mật khẩu</h3>
        <c:if test="${alert != null}"><div class="alert alert-danger">${alert}</div></c:if>
        <form action="${pageContext.request.contextPath}/forgot-password" method="post">
            <div class="mb-3">
                <label>Nhập Email đăng ký:</label>
                <input type="email" name="email" class="form-control" required>
            </div>
            <button type="submit" class="btn btn-primary w-100">Gửi mã OTP</button>
        </form>
        <div class="text-center mt-3"><a href="${pageContext.request.contextPath}/login">Quay lại</a></div>
    </div>
</body>
</html>