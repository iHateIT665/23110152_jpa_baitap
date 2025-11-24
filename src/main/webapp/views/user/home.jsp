<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div class="d-sm-flex align-items-center justify-content-between mb-4">
	<h1 class="h3 mb-0 text-gray-800">Các danh mục</h1>
</div>

<div class="row">

	<c:forEach items="${listcate}" var="cate">
		<div class="col-xl-3 col-md-6 mb-4">
			<div class="card border-left-info shadow h-100 py-2">
				<div class="card-body">
					<div class="row no-gutters align-items-center">
						<div class="col mr-2">
							<div
								class="text-xs font-weight-bold text-info text-uppercase mb-1">
								${cate.categoryCode}</div>
							<div class="h5 mb-0 font-weight-bold text-gray-800">
								${cate.categoryname}</div>
						</div>
						<div class="col-auto">
							<c:if
								test="${cate.images != null && cate.images.startsWith('http')}">
								<img src="${cate.images}" class="rounded-circle" width="60"
									height="60" style="object-fit: cover;">
							</c:if>
							<c:if
								test="${cate.images != null && !cate.images.startsWith('http')}">
								<img
									src="${pageContext.request.contextPath}/image?fname=${cate.images}"
									class="rounded-circle" width="60" height="60"
									style="object-fit: cover;">
							</c:if>
							<c:if test="${cate.images == null}">
								<i class="fas fa-film fa-2x text-gray-300"></i>
							</c:if>
						</div>
					</div>

					<hr>
					<a
						href="${pageContext.request.contextPath}/category/detail?id=${cate.categoryId}"
						class="btn btn-info btn-sm btn-block"> <i class="fas fa-eye"></i>
						Xem chi tiết
					</a>
				</div>
			</div>
		</div>
	</c:forEach>

</div>