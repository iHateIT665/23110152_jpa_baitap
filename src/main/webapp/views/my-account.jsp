<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
    <title>Hồ sơ cá nhân</title>
</head>
<body>
    <h1 class="h3 mb-4 text-gray-800">Hồ sơ cá nhân</h1>

    <div class="row">
        <div class="col-lg-6">
            <div class="card shadow mb-4">
                <div class="card-header py-3">
                    <h6 class="m-0 font-weight-bold text-primary">Thông tin tài khoản</h6>
                </div>
                <div class="card-body">
                    
                    <c:if test="${message != null}">
                        <div class="alert alert-success">${message}</div>
                    </c:if>
                    <c:if test="${error != null}">
                        <div class="alert alert-danger">${error}</div>
                    </c:if>

                    <form action="${pageContext.request.contextPath}/my-account" method="post" enctype="multipart/form-data">
                        
                        <div class="text-center mb-4">
                            <c:if test="${user.images != null && user.images.startsWith('http')}">
                            	 <img src="${user.images}" class="img-profile rounded-circle" width="150" height="150" style="object-fit: cover;">
                            </c:if>
                            <c:if test="${user.images != null && !user.images.startsWith('http')}">
                                <img src="${pageContext.request.contextPath}/image?fname=${user.images}" 
                                     class="img-profile rounded-circle" width="150" height="150" style="object-fit: cover;">
                            </c:if>
                            <c:if test="${user.images == null}">
                                <img src="https://source.unsplash.com/QAB-WJcbgJk/60x60" class="img-profile rounded-circle" width="150">
                            </c:if>
                            
                            <div class="mt-3">
                                <label class="btn btn-sm btn-primary">
                                    <i class="fas fa-upload"></i> Chọn ảnh mới
                                    <input type="file" name="images" style="display: none;" accept="image/*" onchange="previewImage(this)">
                                </label>
                            </div>
                        </div>

                        <div class="form-group">
                            <label>Tên đăng nhập (Không thể sửa):</label>
                            <input type="text" class="form-control" value="${user.username}" disabled>
                        </div>

                        <div class="form-group">
                            <label>Email (Không thể sửa):</label>
                            <input type="text" class="form-control" value="${user.email}" disabled>
                        </div>

                        <div class="form-group">
                            <label>Họ và tên:</label>
                            <input type="text" class="form-control" name="fullname" value="${user.fullname}" required>
                        </div>

                        <div class="form-group">
                            <label>Số điện thoại:</label>
                            <input type="text" class="form-control" name="phone" value="${user.phone}">
                        </div>

                        <button type="submit" class="btn btn-success btn-block">
                            <i class="fas fa-save"></i> Lưu thay đổi
                        </button>
                    </form>

                </div>
            </div>
        </div>
    </div>

    <script>
        function previewImage(input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();
                reader.onload = function (e) {
                    // Tìm thẻ img gần nhất và thay đổi src
                    input.parentElement.parentElement.parentElement.querySelector('img').src = e.target.result;
                }
                reader.readAsDataURL(input.files[0]);
            }
        }
    </script>

</body>
</html>