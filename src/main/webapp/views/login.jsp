<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Đăng nhập hệ thống</title>

    <link href="${pageContext.request.contextPath}/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/sb-admin-2.min.css" rel="stylesheet">
</head>

<body class="bg-gradient-primary">

    <div class="container">

        <!-- Center -->
        <div class="row justify-content-center">

            <div class="col-xl-6 col-lg-7 col-md-9">

                <div class="card o-hidden border-0 shadow-lg my-5">
                    <div class="card-body p-0">

                        <div class="row justify-content-center">
                            <!-- Form -->
                            <div class="col-lg-10">
                                <div class="p-5">
                                    
                                    <div class="text-center">
                                        <h1 class="h4 text-gray-900 mb-4">Chào mừng trở lại!</h1>
                                    </div>

                                    <c:if test="${not empty alert}">
                                        <div class="alert alert-danger" role="alert">
                                            ${alert}
                                        </div>
                                    </c:if>

                                    <form class="user" action="${pageContext.request.contextPath}/login" method="post">
                                        <div class="form-group">
                                            <input type="text" 
                                                   class="form-control form-control-user"
                                                   name="uname"
                                                   placeholder="Nhập tên tài khoản..."
                                                   value="${username}">
                                        </div>

                                        <div class="form-group">
                                            <input type="password" 
                                                   class="form-control form-control-user"
                                                   name="psw"
                                                   placeholder="Mật khẩu"
                                                   value="${password}">
                                        </div>

                                        <div class="form-group">
                                            <div class="custom-control custom-checkbox small">
                                                <input type="checkbox" 
                                                       class="custom-control-input" 
                                                       id="customCheck"
                                                       name="remember" 
                                                       ${remember ? 'checked' : ''}>
                                                <label class="custom-control-label" for="customCheck">Nhớ đăng nhập</label>
                                            </div>
                                        </div>

                                        <button type="submit" class="btn btn-primary btn-user btn-block">
                                            Đăng Nhập
                                        </button>
                                    </form>

                                    <hr>

                                    <div class="text-center">
                                        <a class="small" href="${pageContext.request.contextPath}/forgot-password">Quên mật khẩu?</a>
                                    </div>

                                    <div class="text-center">
                                        <a class="small" href="${pageContext.request.contextPath}/register">Tạo tài khoản mới!</a>
                                    </div>

                                </div>
                            </div>
                            <!-- End form -->
                        </div>

                    </div>
                </div>

            </div>

        </div>
        <!-- End Center -->

    </div>

    <script src="${pageContext.request.contextPath}/vendor/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext.request.contextPath}/vendor/jquery-easing/jquery.easing.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/sb-admin-2.min.js"></script>

</body>

</html>
