<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title><sitemesh:write property="title" /></title>
    
    <link href="${pageContext.request.contextPath}/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/sb-admin-2.min.css" rel="stylesheet">
    <sitemesh:write property="head" />
</head>

<body id="page-top">
    <div id="wrapper">
        
        <%@ include file="/commons/admin/left.jsp"%> 

        <div id="content-wrapper" class="d-flex flex-column">
            <div id="content">
                <%@ include file="/commons/admin/header.jsp"%> 

                <div class="container-fluid">
                    <sitemesh:write property="body" />
                </div>
            </div>
            <%@ include file="/commons/admin/footer.jsp"%>
        </div>
    </div>
    
    <script src="${pageContext.request.contextPath}/vendor/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext.request.contextPath}/vendor/jquery-easing/jquery.easing.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/sb-admin-2.min.js"></script>
</body>
</html>