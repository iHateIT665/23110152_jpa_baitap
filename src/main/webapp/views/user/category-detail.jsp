<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div class="mb-4">
    <a href="${pageContext.request.contextPath}/home" class="btn btn-secondary">
        <i class="fas fa-arrow-left"></i> Quay lại danh sách
    </a>
</div>

<div class="row justify-content-center">
    <div class="col-lg-8">
        <div class="card shadow mb-4">
            <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary">Chi tiết Danh mục</h6>
            </div>
            <div class="card-body">
                <div class="row">
                    <div class="col-md-5 text-center">
                        <div class="mb-3">
                            <c:if test="${cate.images != null && cate.images.startsWith('http')}">
                                <img src="${cate.images}" class="img-fluid rounded shadow-sm" style="max-height: 300px;">
                            </c:if>
                            <c:if test="${cate.images != null && !cate.images.startsWith('http')}">
                                <img src="${pageContext.request.contextPath}/image?fname=${cate.images}" class="img-fluid rounded shadow-sm" style="max-height: 300px;">
                            </c:if>
                            <c:if test="${cate.images == null}">
                                <div class="alert alert-secondary">Không có hình ảnh</div>
                            </c:if>
                        </div>
                    </div>
                    
                    <div class="col-md-7">
                        <h3 class="text-gray-900 font-weight-bold">${cate.categoryname}</h3>
                        <hr>
                        
                        <p><strong>Mã danh mục (Code):</strong> 
                            <span class="badge badge-info">${cate.categoryCode}</span>
                        </p>
                        
                        <p><strong>ID hệ thống:</strong> ${cate.categoryId}</p>
                        
                        <p><strong>Trạng thái:</strong> 
                            <c:if test="${cate.status == 1}">
                                <span class="badge badge-success">Đang hoạt động</span>
                            </c:if>
                            <c:if test="${cate.status != 1}">
                                <span class="badge badge-danger">Đang khóa</span>
                            </c:if>
                        </p>
                        
                        <p class="text-muted mt-4">
                            *Đây là thông tin chi tiết của danh mục. Bạn chỉ có quyền xem.
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>