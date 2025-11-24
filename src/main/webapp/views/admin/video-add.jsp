<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div class="card shadow mb-4">
    <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">Thêm mới Video</h6>
    </div>
    <div class="card-body">
        
        <form action="${pageContext.request.contextPath}/admin/video/insert" method="post" enctype="multipart/form-data">
            
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label>Mã Video (ID):</label>
                        <input type="text" class="form-control" name="videoId" placeholder="Ví dụ: V001" required>
                    </div>
                    
                    <div class="form-group">
                        <label>Tiêu đề video:</label>
                        <input type="text" class="form-control" name="title" placeholder="Nhập tiêu đề..." required>
                    </div>

                    <div class="form-group">
                        <label>Thuộc danh mục:</label>
                        <select class="form-control" name="categoryId">
                            <c:forEach items="${listcate}" var="cate">
                                <option value="${cate.categoryId}">${cate.categoryname}</option>
                            </c:forEach>
                        </select>
                    </div>
                    
                    <div class="form-group">
                        <label>Trạng thái:</label>
                        <select class="form-control" name="active">
                            <option value="1">Hoạt động</option>
                            <option value="0">Tạm khóa</option>
                        </select>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="form-group">
                        <label>Poster (Ảnh bìa):</label>
                        <div class="mb-2">
                            <img id="preview" src="#" width="100%" style="display: none; max-height: 200px; object-fit: cover;" class="img-thumbnail">
                        </div>
                        <input type="file" name="poster" class="form-control-file" accept="image/*" onchange="previewImage(this)">
                    </div>

                    <div class="form-group">
                        <label>Mô tả:</label>
                        <textarea class="form-control" name="description" rows="5"></textarea>
                    </div>
                </div>
            </div>

            <hr>
            <button type="submit" class="btn btn-primary">
                <i class="fas fa-save"></i> Lưu Video
            </button>
            <a href="${pageContext.request.contextPath}/admin/video/list" class="btn btn-secondary">Quay lại</a>
        </form>
    </div>
</div>

<script>
    function previewImage(input) {
        var preview = document.getElementById('preview');
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                preview.src = e.target.result;
                preview.style.display = 'block';
            }
            reader.readAsDataURL(input.files[0]);
        }
    }
</script>