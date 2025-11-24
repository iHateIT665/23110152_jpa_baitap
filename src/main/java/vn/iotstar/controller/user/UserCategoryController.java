package vn.iotstar.controller.user;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.entity.Category;
import vn.iotstar.service.ICategoryService;
import vn.iotstar.service.impl.CategoryServiceImpl;

@WebServlet(urlPatterns = { "/category/detail" })
public class UserCategoryController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    ICategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. Lấy ID từ URL
        String idStr = req.getParameter("id");
        
        if (idStr != null) {
            int id = Integer.parseInt(idStr);
            
            // 2. Tìm danh mục trong DB
            Category cate = cateService.findById(id);
            
            // 3. Gửi sang View
            req.setAttribute("cate", cate);
            req.getRequestDispatcher("/views/user/category-detail.jsp").forward(req, resp);
        } else {
            // Nếu không có ID thì quay về trang chủ
            resp.sendRedirect(req.getContextPath() + "/home");
        }
    }
}