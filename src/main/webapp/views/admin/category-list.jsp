<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div class="card shadow mb-4">
    <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">Quản lý danh mục</h6>
    </div>
    <div class="card-body">
        <div class="table-responsive">
            <a href="${pageContext.request.contextPath}/admin/category/add" class="btn btn-success mb-3">Thêm mới</a>
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                <thead>
                    <tr>
                        <th>STT</th>
                        <th>Hình ảnh</th>
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
                                <c:if test="${cate.images.startsWith('http')}">
                                    <img src="${cate.images}" width="80" height="80" style="object-fit: cover;">
                                </c:if>
                                <c:if test="${!cate.images.startsWith('http')}">
                                    <img src="${pageContext.request.contextPath}/image?fname=${cate.images}" width="80" height="80" style="object-fit: cover;">
                                </c:if>
                            </td>
                            <td>${cate.categoryname}</td>
                            <td>${cate.status == 1 ? "Hoạt động" : "Khóa"}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/admin/category/edit?id=${cate.categoryId}" class="btn btn-warning btn-sm">Sửa</a>
                                <a href="${pageContext.request.contextPath}/admin/category/delete?id=${cate.categoryId}" class="btn btn-danger btn-sm" onclick="return confirm('Bạn chắc chắn muốn xóa?')">Xóa</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>