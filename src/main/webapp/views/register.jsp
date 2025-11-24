<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng ký tài khoản</title>
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

    <style>
        body {
            background-color: #f0f2f5;
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            padding: 20px; /* Để không bị sát lề trên mobile */
        }
        .register-card {
            width: 100%;
            max-width: 500px;
            background: #fff;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.1);
            overflow: hidden; /* Để header không bị lòi ra */
        }
        .card-header-custom {
            background-color: #0099ff; /* Màu xanh dương giống ảnh mẫu */
            color: white;
            padding: 20px;
            text-align: center;
            font-size: 24px;
            font-weight: bold;
            border-bottom: none;
        }
        .card-body {
            padding: 30px;
        }
        
        /* Style cho Input Group (Icon + Input) */
        .input-group-text {
            background-color: #f8f9fa;
            border-right: none;
            color: #6c757d;
            width: 45px; /* Cố định chiều rộng icon cho thẳng hàng */
            justify-content: center;
        }
        .form-control {
            background-color: #f8f9fa;
            border-left: none;
            height: 45px; /* Input cao hơn chút cho dễ nhập */
        }
        .form-control:focus {
            background-color: #fff;
            border-color: #86b7fe;
            box-shadow: none;
        }
        /* Hiệu ứng focus: làm sáng viền cả icon */
        .input-group:focus-within .input-group-text,
        .input-group:focus-within .form-control {
            border-color: #0099ff;
            background-color: #fff;
            color: #0099ff;
        }
        
        .btn-register {
            background-color: #0099ff;
            border: none;
            padding: 12px;
            font-size: 18px;
            font-weight: 600;
            transition: all 0.3s;
        }
        .btn-register:hover {
            background-color: #0077cc;
        }
        .login-link a {
            color: #0099ff;
            font-weight: 600;
            text-decoration: none;
        }
        .login-link a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

    <div class="register-card">
        <div class="card-header-custom">
            Tạo Tài Khoản Mới
        </div>
        
        <div class="card-body">
            <c:if test="${alert != null}">
                <div class="alert alert-danger text-center">
                    <i class="fa-solid fa-triangle-exclamation"></i> ${alert}
                </div>
            </c:if>

            <form action="${pageContext.request.contextPath}/register" method="post" autocomplete="off">
                
                <div class="mb-3">
                    <div class="input-group">
                        <span class="input-group-text"><i class="fa-solid fa-user-tag"></i></span>
                        <input type="text" class="form-control" name="fullname" 
                               placeholder="Họ và tên đầy đủ" required autocomplete="off">
                    </div>
                </div>

                <div class="mb-3">
                    <div class="input-group">
                        <span class="input-group-text"><i class="fa-solid fa-envelope"></i></span>
                        <input type="email" class="form-control" name="email" 
                               placeholder="Địa chỉ Email" required autocomplete="off">
                    </div>
                </div>

                <div class="mb-3">
                    <div class="input-group">
                        <span class="input-group-text"><i class="fa-solid fa-phone"></i></span>
                        <input type="tel" class="form-control" name="phone" 
                               placeholder="Số điện thoại" required autocomplete="off">
                    </div>
                </div>

                <div class="mb-3">
                    <div class="input-group">
                        <span class="input-group-text"><i class="fa-solid fa-user"></i></span>
                        <input type="text" class="form-control" name="username" 
                               placeholder="Tên đăng nhập" required autocomplete="off">
                    </div>
                </div>

                <div class="mb-4">
                    <div class="input-group">
                        <span class="input-group-text"><i class="fa-solid fa-lock"></i></span>
                        <input type="password" class="form-control" name="password" 
                               placeholder="Mật khẩu" required autocomplete="new-password">
                    </div>
                </div>

                <div class="d-grid">
                    <button type="submit" class="btn btn-primary btn-register">Tạo tài khoản</button>
                </div>

            </form>

            <div class="text-center mt-4 login-link">
                Nếu bạn đã có tài khoản? <a href="${pageContext.request.contextPath}/login">Đăng nhập ngay</a>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>