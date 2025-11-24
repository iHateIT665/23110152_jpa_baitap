<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div class="card shadow mb-4">
    <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">Thêm mới danh mục</h6>
    </div>
    <div class="card-body">
        
        <form action="${pageContext.request.contextPath}/admin/category/insert" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="categorycode">Mã danh mục (Code):</label>
                <input type="text" class="form-control" id="categorycode" name="categorycode" placeholder="Ví dụ: MOB, LAP, TV..." required>
            </div>

            <div class="form-group">
                <label for="categoryname">Tên danh mục:</label>
                <input type="text" class="form-control" id="categoryname" name="categoryname" placeholder="Nhập tên danh mục..." required>
            </div>
        

            <div class="form-group">
                <label>Hình ảnh đại diện:</label>
                
                <div class="mb-2">
                    <img id="preview" src="#" alt="Ảnh xem trước" width="200" class="img-thumbnail" style="display: none; object-fit: cover;">
                </div>
                
                <input type="file" name="images" class="form-control-file" accept="image/*" onchange="previewImage(this)" required>
            </div>

            <div class="form-group">
                <label>Trạng thái:</label>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="status" id="statusActive" value="1" checked>
                    <label class="form-check-label" for="statusActive">
                        Hoạt động
                    </label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="status" id="statusInactive" value="0">
                    <label class="form-check-label" for="statusInactive">
                        Khóa
                    </label>
                </div>
            </div>
            
            <hr>
            <button type="submit" class="btn btn-primary btn-icon-split">
                <span class="icon text-white-50">
                    <i class="fas fa-save"></i>
                </span>
                <span class="text">Lưu lại</span>
            </button>
            
            <a href="${pageContext.request.contextPath}/admin/category/list" class="btn btn-secondary btn-icon-split">
                <span class="icon text-white-50">
                    <i class="fas fa-arrow-left"></i>
                </span>
                <span class="text">Quay lại danh sách</span>
            </a>

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
                preview.style.display = 'block'; // Hiện ảnh lên
            }
            
            reader.readAsDataURL(input.files[0]);
        } else {
            preview.src = "#";
            preview.style.display = 'none'; // Ẩn đi nếu bỏ chọn
        }
    }
</script>