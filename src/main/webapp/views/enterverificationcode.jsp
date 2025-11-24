<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head><title>Nhập OTP</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"></head>
<body class="d-flex justify-content-center align-items-center vh-100 bg-light">
    <div class="card p-4 shadow" style="width: 400px;">
        <h3 class="text-center text-success">Xác thực OTP</h3>
        <div class="alert alert-info">Mã đã gửi đến: <b>${sessionScope.email}</b></div>
        <c:if test="${alert != null}"><div class="alert alert-danger">${alert}</div></c:if>
        <form action="${pageContext.request.contextPath}/verify-code" method="post">
            <div class="mb-3">
                <label>Nhập mã 6 số:</label>
                <input type="text" name="authCode" class="form-control text-center fs-4" maxlength="6" required>
            </div>
            <button type="submit" class="btn btn-success w-100">Xác nhận</button>
        </form>
    </div>
</body>
</html>