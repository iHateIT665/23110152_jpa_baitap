<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<h1 class="h3 mb-2 text-gray-800">Trang chủ Manager</h1>
<p class="mb-4">Quản lý danh mục (Bạn chỉ được sửa/xóa danh mục do mình tạo).</p>

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
                        <th>Tên danh mục</th>
                        <th>Người tạo (ID)</th> <th>Hành động</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listcate}" var="cate" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>
                                <img src="${cate.images}" width="50"> 
                            </td>
                            <td>${cate.categoryname}</td>
                            <td>${cate.managerId}</td>
                            
                            <td>
                                <c:set var="myId" value="${sessionScope.account.id}" />
                                
                                <c:if test="${cate.managerId == myId}">
                                    <a href="${pageContext.request.contextPath}/manager/category/edit?id=${cate.categoryId}" class="btn btn-warning btn-sm">Sửa</a>
                                    <a href="${pageContext.request.contextPath}/manager/category/delete?id=${cate.categoryId}" class="btn btn-danger btn-sm" onclick="return confirm('Xóa nhé?')">Xóa</a>
                                </c:if>
                                
                                <c:if test="${cate.managerId != myId}">
                                    <span class="badge badge-secondary">Chỉ xem</span>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>