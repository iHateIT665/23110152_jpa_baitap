package vn.iotstar.controller.admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.entity.Category;
import vn.iotstar.service.ICategoryService;
import vn.iotstar.service.impl.CategoryServiceImpl;

@WebServlet(urlPatterns = { "/admin/home" })
public class AdminHomeController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // Gọi Service để lấy dữ liệu
    ICategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. Lấy tất cả danh mục
        List<Category> list = cateService.findAll();
        
        // 2. Gửi sang View
        req.setAttribute("listcate", list);
        
        // 3. Hiện trang Home
        req.getRequestDispatcher("/views/admin/home.jsp").forward(req, resp);
    }
}