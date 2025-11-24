package vn.iotstar.controller.manager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.User;
import vn.iotstar.service.ICategoryService;
import vn.iotstar.service.impl.CategoryServiceImpl;
import vn.iotstar.utils.Constant;

@WebServlet(urlPatterns = { 
    "/manager/category/list", 
    "/manager/category/add", 
    "/manager/category/insert",
    "/manager/category/edit", 
    "/manager/category/update", 
    "/manager/category/delete", 
    "/manager/category/search" 
})
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class ManagerCategoryController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public ICategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        
        // 1. Kiểm tra đăng nhập & Quyền Manager
        HttpSession session = req.getSession();
        User account = (User) session.getAttribute("account");
        
        if (account == null || account.getRoleid() != 2) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // 2. Xử lý các chức năng
        if (url.contains("list")) {
            // THAY ĐỔI: Dùng findAll() để Manager thấy hết danh sách (giống Admin)
            List<Category> list = cateService.findAll();
            req.setAttribute("listcate", list);
            req.getRequestDispatcher("/views/manager/category-list.jsp").forward(req, resp);
            
        } else if (url.contains("add")) {
            req.getRequestDispatcher("/views/manager/category-add.jsp").forward(req, resp);
            
        } else if (url.contains("edit")) {
            try {
                int id = Integer.parseInt(req.getParameter("id"));
                Category category = cateService.findById(id);
                
                // BẢO MẬT: Kiểm tra quyền sở hữu
                if (category.getManagerId() != account.getId()) {
                     req.setAttribute("error", "Bạn không có quyền sửa danh mục này!");
                     
                     // Quan trọng: Phải load lại listcate thì mới hiện lại trang danh sách được
                     List<Category> list = cateService.findAll();
                     req.setAttribute("listcate", list);
                     req.getRequestDispatcher("/views/manager/category-list.jsp").forward(req, resp);
                     return;
                }
                
                req.setAttribute("cate", category);
                req.getRequestDispatcher("/views/manager/category-edit.jsp").forward(req, resp);
            } catch (Exception e) {
                resp.sendRedirect(req.getContextPath() + "/manager/category/list");
            }
            
        } else if (url.contains("delete")) {
            try {
                int id = Integer.parseInt(req.getParameter("id"));
                Category category = cateService.findById(id);

                // BẢO MẬT: Kiểm tra quyền sở hữu trước khi xóa
                if (category.getManagerId() != account.getId()) {
                    req.setAttribute("error", "Cảnh báo: Bạn không thể xóa danh mục của người khác!");
                    
                    // Load lại danh sách để hiển thị lỗi
                    List<Category> list = cateService.findAll();
                    req.setAttribute("listcate", list);
                    req.getRequestDispatcher("/views/manager/category-list.jsp").forward(req, resp);
                    return;
                }

                cateService.delete(id);
                resp.sendRedirect(req.getContextPath() + "/manager/category/list");
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        } else if (url.contains("search")) {
            String keyword = req.getParameter("keyword");
            List<Category> list = cateService.findByName(keyword); 
            req.setAttribute("listcate", list);
            req.getRequestDispatcher("/views/manager/category-list.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        
        HttpSession session = req.getSession();
        User account = (User) session.getAttribute("account");

        if (url.contains("insert")) {
            String categorycode = req.getParameter("categorycode");
            String categoryname = req.getParameter("categoryname");
            int status = Integer.parseInt(req.getParameter("status"));
            String images = "https://active.vn/wp-content/uploads/2021/12/avatar-ngau-nam-6.jpg"; // Mặc định

            try {
                Part part = req.getPart("images");
                if (part.getSize() > 0) {
                    String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    int index = filename.lastIndexOf(".");
                    String ext = filename.substring(index + 1);
                    String fname = System.currentTimeMillis() + "." + ext;
                    
                    File uploadDir = new File(Constant.UPLOAD_DIRECTORY);
                    if (!uploadDir.exists()) uploadDir.mkdir();
                    
                    part.write(Constant.UPLOAD_DIRECTORY + File.separator + fname);
                    images = fname;
                }
            } catch (Exception e) { e.printStackTrace(); }

            Category category = new Category();
            category.setCategoryCode(categorycode);
            category.setCategoryname(categoryname);
            category.setImages(images);
            category.setStatus(status);
            
            // Gán Manager ID
            if (account != null) {
                category.setManagerId(account.getId());
            }
            
            cateService.insert(category);
            resp.sendRedirect(req.getContextPath() + "/manager/category/list");

        } else if (url.contains("update")) {
            int categoryid = Integer.parseInt(req.getParameter("categoryid"));
            String categorycode = req.getParameter("categorycode");
            String categoryname = req.getParameter("categoryname");
            int status = Integer.parseInt(req.getParameter("status"));
            
            Category oldCate = cateService.findById(categoryid);
            
            // BẢO MẬT: Kiểm tra lần cuối xem có đúng là của mình không (tránh hack form)
            if (oldCate.getManagerId() != account.getId()) {
                resp.sendRedirect(req.getContextPath() + "/manager/category/list");
                return;
            }

            String images = oldCate.getImages();
            try {
                Part part = req.getPart("images");
                if (part.getSize() > 0) {
                    String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    int index = filename.lastIndexOf(".");
                    String ext = filename.substring(index + 1);
                    String fname = System.currentTimeMillis() + "." + ext;
                    
                    File uploadDir = new File(Constant.UPLOAD_DIRECTORY);
                    if (!uploadDir.exists()) uploadDir.mkdir();
                    
                    part.write(Constant.UPLOAD_DIRECTORY + File.separator + fname);
                    images = fname;
                }
            } catch (Exception e) { e.printStackTrace(); }

            Category category = new Category();
            category.setCategoryId(categoryid);
            category.setCategoryCode(categorycode);
            category.setCategoryname(categoryname);
            category.setImages(images);
            category.setStatus(status);
            
            // Giữ nguyên người tạo cũ
            category.setManagerId(oldCate.getManagerId());
            
            cateService.update(category);
            resp.sendRedirect(req.getContextPath() + "/manager/category/list");
        }
    }
}