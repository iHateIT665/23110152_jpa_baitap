package vn.iotstar.controller.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.iotstar.entity.Category;
import vn.iotstar.service.ICategoryService;
import vn.iotstar.service.impl.CategoryServiceImpl;
import vn.iotstar.utils.Constant;

@WebServlet(urlPatterns = { "/admin/category/list", "/admin/category/add", "/admin/category/insert",
        "/admin/category/edit", "/admin/category/update", "/admin/category/delete", "/admin/category/search" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class CategoryController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public ICategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        if (url.contains("list")) {
            List<Category> list = cateService.findAll();
            req.setAttribute("listcate", list);
            req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);
            
        } else if (url.contains("add")) {
            req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);
            
        } else if (url.contains("edit")) {
            // Nên bọc try-catch để tránh lỗi nếu id không phải số
            try {
                int id = Integer.parseInt(req.getParameter("id"));
                Category category = cateService.findById(id);
                req.setAttribute("cate", category);
                req.getRequestDispatcher("/views/admin/category-edit.jsp").forward(req, resp);
            } catch (Exception e) {
                resp.sendRedirect(req.getContextPath() + "/admin/category/list");
            }
            
        } else if (url.contains("delete")) {
            try {
                int id = Integer.parseInt(req.getParameter("id"));
                cateService.delete(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            resp.sendRedirect(req.getContextPath() + "/admin/category/list");
            
        } else if (url.contains("search")) {
            String keyword = req.getParameter("keyword");
            List<Category> list = cateService.findByName(keyword);
            req.setAttribute("listcate", list);
            req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        if (url.contains("insert")) {
            String categorycode = req.getParameter("categorycode");
            String categoryname = req.getParameter("categoryname");
            int status = Integer.parseInt(req.getParameter("status"));
            
            String images = "https://active.vn/wp-content/uploads/2021/12/avatar-ngau-nam-6.jpg"; 

            try {
                Part part = req.getPart("images");
                if (part.getSize() > 0) {
                    String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    int index = filename.lastIndexOf(".");
                    String ext = filename.substring(index + 1);
                    String fname = System.currentTimeMillis() + "." + ext;
                    
                    // --- BỔ SUNG KIỂM TRA THƯ MỤC ---
                    File uploadDir = new File(Constant.UPLOAD_DIRECTORY);
                    if (!uploadDir.exists()) {
                        uploadDir.mkdir();
                    }
                    // --------------------------------
                    
                    part.write(Constant.UPLOAD_DIRECTORY + File.separator + fname);
                    images = fname;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            Category category = new Category();
            category.setCategoryCode(categorycode); 
            category.setCategoryname(categoryname);
            category.setImages(images);
            category.setStatus(status);
            
            cateService.insert(category);
            resp.sendRedirect(req.getContextPath() + "/admin/category/list");

        } else if (url.contains("update")) {
            int categoryid = Integer.parseInt(req.getParameter("categoryid"));
            String categorycode = req.getParameter("categorycode"); 
            String categoryname = req.getParameter("categoryname");
            int status = Integer.parseInt(req.getParameter("status"));
            
            Category oldCate = cateService.findById(categoryid);
            String images = oldCate.getImages();

            try {
                Part part = req.getPart("images");
                if (part.getSize() > 0) {
                    String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    int index = filename.lastIndexOf(".");
                    String ext = filename.substring(index + 1);
                    String fname = System.currentTimeMillis() + "." + ext;
                    
                    // --- BỔ SUNG KIỂM TRA THƯ MỤC ---
                    File uploadDir = new File(Constant.UPLOAD_DIRECTORY);
                    if (!uploadDir.exists()) {
                        uploadDir.mkdir();
                    }
                    // --------------------------------
                    
                    part.write(Constant.UPLOAD_DIRECTORY + File.separator + fname);
                    images = fname;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            Category category = new Category();
            category.setCategoryId(categoryid);
            category.setCategoryCode(categorycode);
            category.setCategoryname(categoryname);
            category.setImages(images);
            category.setStatus(status);
            
            cateService.update(category);
            resp.sendRedirect(req.getContextPath() + "/admin/category/list");
        }
    }
    
}