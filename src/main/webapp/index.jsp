<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // Chuyển hướng ngay lập tức về trang login
    response.sendRedirect(request.getContextPath() + "/login");
%>