<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<h1 class="h3 mb-2 text-gray-800">Trang chủ Manager</h1>
<p class="mb-4">Đây là danh sách tất cả danh mục. <strong>Lưu ý:</strong> Bạn chỉ có thể Sửa/Xóa danh mục do chính mình tạo.</p>

<c:if test="${not empty error}">
    <div class="alert alert-danger">${error}</div>
</c:if>

<div class="card shadow mb-4">
    <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">Danh sách Category</h6>
    </div>
    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                <thead>
                    <tr>
                        <th>STT</th>
                        <th>Hình ảnh</th>
                        <th>Mã (Code)</th>
                        <th>Tên danh mục</th>
                        <th>Trạng thái</th>
                        <th>Hành động</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listcate}" var="cate" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>
                                <c:if test="${cate.images != null && cate.images.startsWith('http')}">
                                    <img src="${cate.images}" width="50" height="50" style="object-fit: cover;">
                                </c:if>
                                <c:if test="${cate.images != null && !cate.images.startsWith('http')}">
                                    <img src="${pageContext.request.contextPath}/image?fname=${cate.images}" width="50" height="50" style="object-fit: cover;">
                                </c:if>
                            </td>
                            <td>${cate.categoryCode}</td>
                            <td>${cate.categoryname}</td>
                            <td>
                                <c:if test="${cate.status == 1}"><span class="badge badge-success">Active</span></c:if>
                                <c:if test="${cate.status != 1}"><span class="badge badge-danger">Inactive</span></c:if>
                            </td>
                            <td>
                                <c:set var="myId" value="${sessionScope.account.id}" />

                                <c:if test="${cate.managerId == myId}">
                                    <a href="${pageContext.request.contextPath}/manager/category/edit?id=${cate.categoryId}" class="btn btn-warning btn-sm">Sửa</a>
                                    <a href="${pageContext.request.contextPath}/manager/category/delete?id=${cate.categoryId}" class="btn btn-danger btn-sm" onclick="return confirm('Bạn chắc chắn muốn xóa danh mục này chứ?')">Xóa</a>
                                </c:if>

                                <c:if test="${cate.managerId != myId}">
                                    <span class="badge badge-secondary" style="cursor: not-allowed;">
                                        <i class="fas fa-lock"></i> Không có quyền
                                    </span>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>