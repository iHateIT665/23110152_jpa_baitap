<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div class="card shadow mb-4">
    <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">Cập nhật danh mục</h6>
    </div>
    <div class="card-body">
        <form action="${pageContext.request.contextPath}/manager/category/update" method="post" enctype="multipart/form-data">
            <input type="hidden" name="categoryid" value="${cate.categoryId}">
            
            <div class="form-group">
                <label>Mã danh mục (Code)</label>
                <input type="text" class="form-control" name="categorycode" value="${cate.categoryCode}" required>
            </div>

            <div class="form-group">
                <label>Tên danh mục</label>
                <input type="text" class="form-control" name="categoryname" value="${cate.categoryname}" required>
            </div>

            <div class="form-group">
                <label>Hình ảnh</label><br>
                <div class="mb-2">
                    <c:if test="${cate.images != null && cate.images.startsWith('http')}">
                         <img id="preview" src="${cate.images}" width="150" class="img-thumbnail">
                    </c:if>
                    <c:if test="${cate.images != null && !cate.images.startsWith('http')}">
                         <img id="preview" src="${pageContext.request.contextPath}/image?fname=${cate.images}" width="150" class="img-thumbnail">
                    </c:if>
                </div>
                
                <input type="file" name="images" class="form-control-file" accept="image/*" onchange="previewImage(this)">
            </div>

            <div class="form-group">
                <label>Trạng thái</label>
                <select name="status" class="form-control">
                    <option value="1" ${cate.status == 1 ? "selected" : ""}>Hoạt động</option>
                    <option value="0" ${cate.status == 0 ? "selected" : ""}>Khóa</option>
                </select>
            </div>

            <hr>
            <button type="submit" class="btn btn-primary">Cập nhật</button>
            <a href="${pageContext.request.contextPath}/manager/category/list" class="btn btn-secondary">Hủy</a>
        </form>
    </div>
</div>

<script>
    function previewImage(input) {
        var preview = document.getElementById('preview');
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e