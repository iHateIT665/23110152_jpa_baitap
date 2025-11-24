<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div class="card shadow mb-4">
    <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">Quản lý Video</h6>
    </div>
    <div class="card-body">
        
        <form action="${pageContext.request.contextPath}/admin/video/search" method="get" class="form-inline mb-3 justify-content-end">
            <div class="input-group">
                <input type="text" name="keyword" class="form-control bg-light border-0 small" 
                       placeholder="Tìm kiếm video..." value="${keyword}">
                <div class="input-group-append">
                    <button class="btn btn-primary" type="button">
                        <i class="fas fa-search fa-sm"></i>
                    </button>
                </div>
            </div>
        </form>

        <a href="${pageContext.request.contextPath}/admin/video/add" class="btn btn-success mb-3">
            <i class="fas fa-plus"></i> Thêm Video Mới
        </a>

        <div class="table-responsive">
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                <thead>
                    <tr>
                        <th>Mã Video</th>
                        <th>Poster</th>
                        <th>Tiêu đề</th>
                        <th>Lượt xem</th>
                        <th>Danh mục</th> <th>Trạng thái</th>
                        <th>Hành động</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listvideo}" var="video">
                        <tr>
                            <td>${video.videoId}</td>
                            <td>
                                <c:if test="${video.poster.startsWith('http')}">
                                    <img src="${video.poster}" width="80" height="60" style="object-fit: cover;">
                                </c:if>
                                <c:if test="${!video.poster.startsWith('http')}">
                                    <img src="${pageContext.request.contextPath}/image?fname=${video.poster}" width="80" height="60" style="object-fit: cover;">
                                </c:if>
                            </td>
                            <td>${video.title}</td>
                            <td>${video.views}</td>
                            <td>
                                <span class="badge badge-info">${video.category.categoryname}</span>
                            </td>
                            <td>
                                <c:if test="${video.active == 1}"><span class="badge badge-success">Hiện</span></c:if>
                                <c:if test="${video.active == 0}"><span class="badge badge-secondary">Ẩn</span></c:if>
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/admin/video/edit?id=${video.videoId}" class="btn btn-warning btn-sm btn-circle">
                                    <i class="fas fa-edit"></i>
                                </a>
                                <a href="${pageContext.request.contextPath}/admin/video/delete?id=${video.videoId}" class="btn btn-danger btn-sm btn-circle" onclick="return confirm('Xóa video này?')">
                                    <i class="fas fa-trash"></i>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>