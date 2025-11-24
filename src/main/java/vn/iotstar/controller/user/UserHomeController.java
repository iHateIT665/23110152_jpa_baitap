package vn.iotstar.controller.user;

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

@WebServlet(urlPatterns = { "/user/home" })
public class UserHomeController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // Gọi Service Category
    ICategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. Lấy danh sách tất cả danh mục
        List<Category> list = cateService.findAll();
        
        // 2. Gửi sang View
        req.setAttribute("listcate", list);
        
        // 3. Chuyển hướng về trang Home của User
        req.getRequestDispatcher("/views/user/home.jsp").forward(req, resp);
    }
}