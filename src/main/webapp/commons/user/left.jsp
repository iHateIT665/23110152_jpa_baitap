<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<ul class="navbar-nav bg-gradient-info sidebar sidebar-dark accordion" id="accordionSidebar">

    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="${pageContext.request.contextPath}/home">
        <div class="sidebar-brand-icon rotate-n-15">
            <i class="fas fa-play-circle"></i>
        </div>
        <div class="sidebar-brand-text mx-3">Web</div>
    </a>

    <hr class="sidebar-divider my-0">

    <li class="nav-item active">
        <a class="nav-link" href="${pageContext.request.contextPath}/home">
            <i class="fas fa-fw fa-home"></i>
            <span>Trang chủ</span></a>
    </li>

    <hr class="sidebar-divider">

    <div class="sidebar-heading">Cá nhân</div>

    <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/my-account">
            <i class="fas fa-fw fa-user-circle"></i>
            <span>Thông tin tài khoản</span>
        </a>
    </li>

    <hr class="sidebar-divider d-none d-md-block">

    <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
    </div>

</ul>