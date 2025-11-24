<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="${pageContext.request.contextPath}/admin/home">
        <div class="sidebar-brand-icon rotate-n-15">
            <i class="fas fa-cogs"></i>
        </div>
        <div class="sidebar-brand-text mx-3">Admin Control</div>
    </a>

    <hr class="sidebar-divider my-0">

    <li class="nav-item active">
        <a class="nav-link" href="${pageContext.request.contextPath}/admin/home">
            <i class="fas fa-fw fa-tachometer-alt"></i>
            <span>Tổng quan</span></a>
    </li>

    <hr class="sidebar-divider">

    <div class="sidebar-heading">
        Quản lý dữ liệu
    </div>

    <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/admin/category/list">
            <i class="fas fa-fw fa-table"></i>
            <span>Quản lý Category</span>
        </a>
    </li>
    
    <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/admin/category/add">
            <i class="fas fa-fw fa-plus-square"></i>
            <span>Thêm Category</span>
        </a>
    </li>

    <hr class="sidebar-divider d-none d-md-block">

    <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
    </div>

</ul>