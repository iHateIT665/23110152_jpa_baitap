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
import vn.iotstar.entity.Video;
import vn.iotstar.service.ICategoryService;
import vn.iotstar.service.IVideoService;
import vn.iotstar.service.impl.CategoryServiceImpl;
import vn.iotstar.service.impl.VideoServiceImpl;
import vn.iotstar.utils.Constant;

@WebServlet(urlPatterns = { "/admin/video/list", "/admin/video/add", "/admin/video/insert",
        "/admin/video/edit", "/admin/video/update", "/admin/video/delete", "/admin/video/search" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class VideoController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    IVideoService videoService = new VideoServiceImpl();
    ICategoryService categoryService = new CategoryServiceImpl(); // Cần service này để lấy danh sách Category

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        if (url.contains("list")) {
            List<Video> list = videoService.findAll();
            req.setAttribute("listvideo", list);
            req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);
            
        } else if (url.contains("add")) {
            // Load danh sách Category để hiển thị vào <select>
            List<Category> listCate = categoryService.findAll();
            req.setAttribute("listcate", listCate);
            req.getRequestDispatcher("/views/admin/video-add.jsp").forward(req, resp);
            
        } else if (url.contains("edit")) {
            String id = req.getParameter("id");
            Video video = videoService.findById(id);
            List<Category> listCate = categoryService.findAll();
            
            req.setAttribute("video", video);
            req.setAttribute("listcate", listCate); // Gửi list Category sang để người dùng chọn lại nếu muốn
            req.getRequestDispatcher("/views/admin/video-edit.jsp").forward(req, resp);
            
        } else if (url.contains("delete")) {
            String id = req.getParameter("id");
            try {
                videoService.delete(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            resp.sendRedirect(req.getContextPath() + "/admin/video/list");
            
        } else if (url.contains("search")) {
            String keyword = req.getParameter("keyword");
            List<Video> list = videoService.findByTitle(keyword);
            req.setAttribute("listvideo", list);
            // Giữ lại keyword để hiện lại trên ô tìm kiếm
            req.setAttribute("keyword", keyword); 
            req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        if (url.contains("insert")) {
            String videoId = req.getParameter("videoId");
            String title = req.getParameter("title");
            String description = req.getParameter("description");
            int active = Integer.parseInt(req.getParameter("active"));
            int categoryId = Integer.parseInt(req.getParameter("categoryId"));

            String poster = "https://via.placeholder.com/150"; // Ảnh mặc định

            // Upload Poster
            try {
                Part part = req.getPart("poster");
                if (part.getSize() > 0) {
                    String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    int index = filename.lastIndexOf(".");
                    String ext = filename.substring(index + 1);
                    String fname = System.currentTimeMillis() + "." + ext;
                    part.write(Constant.UPLOAD_DIRECTORY + File.separator + fname);
                    poster = fname;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            Video video = new Video();
            video.setVideoId(videoId);
            video.setTitle(title);
            video.setDescription(description);
            video.setActive(active);
            video.setPoster(poster);
            video.setViews(0); // Mới tạo thì view = 0

            // Set Category cho Video (Quan hệ)
            Category category = categoryService.findById(categoryId);
            video.setCategory(category);

            videoService.insert(video);
            resp.sendRedirect(req.getContextPath() + "/admin/video/list");

        } else if (url.contains("update")) {
            String videoId = req.getParameter("videoId"); // ID không sửa được, chỉ lấy để tìm
            String title = req.getParameter("title");
            String description = req.getParameter("description");
            int active = Integer.parseInt(req.getParameter("active"));
            int categoryId = Integer.parseInt(req.getParameter("categoryId"));
            
            // Lấy Video cũ để giữ lại poster nếu không up mới
            Video oldVideo = videoService.findById(videoId);
            String poster = oldVideo.getPoster();

            try {
                Part part = req.getPart("poster");
                if (part.getSize() > 0) {
                    String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    int index = filename.lastIndexOf(".");
                    String ext = filename.substring(index + 1);
                    String fname = System.currentTimeMillis() + "." + ext;
                    part.write(Constant.UPLOAD_DIRECTORY + File.separator + fname);
                    poster = fname;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            Video video = new Video();
            video.setVideoId(videoId);
            video.setTitle(title);
            video.setDescription(description);
            video.setActive(active);
            video.setPoster(poster);
            video.setViews(oldVideo.getViews()); // Giữ nguyên lượt xem cũ

            Category category = categoryService.findById(categoryId);
            video.setCategory(category);

            videoService.update(video);
            resp.sendRedirect(req.getContextPath() + "/admin/video/list");
        }
    }
}