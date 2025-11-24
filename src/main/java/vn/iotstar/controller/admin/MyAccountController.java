package vn.iotstar.controller.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import vn.iotstar.entity.User;
import vn.iotstar.service.UserService;
import vn.iotstar.service.impl.UserServiceImpl;
import vn.iotstar.utils.Constant;

@WebServlet(urlPatterns = { "/my-account" })
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
    maxFileSize = 1024 * 1024 * 10,      // 10MB
    maxRequestSize = 1024 * 1024 * 50    // 50MB
)
public class MyAccountController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    UserService service = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("account");

        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        req.setAttribute("user", user);
        req.getRequestDispatcher("/views/my-account.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        System.out.println("================ BẮT ĐẦU DEBUG DATABASE ================");

        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("account");

        if (currentUser == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        
        // Lấy ID để tí nữa kiểm tra lại
        int userId = currentUser.getId();

        // 1. Kiểm tra trạng thái TRƯỚC khi update
        User userBefore = service.findById(userId);
        String imgBefore = (userBefore != null) ? userBefore.getImages() : "null";
        System.out.println("1. [TRƯỚC UPDATE] Ảnh trong DB đang là: " + imgBefore);

        String fullname = req.getParameter("fullname");
        String phone = req.getParameter("phone");
        String imageName = currentUser.getImages(); 

        try {
            Part part = req.getPart("images");
            if (part != null && part.getSize() > 0) {
                String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                int index = fileName.lastIndexOf(".");
                String ext = fileName.substring(index + 1);
                String newFileName = System.currentTimeMillis() + "." + ext;

                String uploadPath = Constant.UPLOAD_DIRECTORY;
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) uploadDir.mkdir();
                
                part.write(uploadPath + File.separator + newFileName);
                System.out.println("2. [UPLOAD] Đã lưu file vào ổ cứng: " + newFileName);
                
                imageName = newFileName;
            } else {
                System.out.println("2. [UPLOAD] Không có file mới, giữ nguyên ảnh cũ.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Cập nhật thông tin vào đối tượng
        currentUser.setFullname(fullname);
        currentUser.setPhone(phone);
        currentUser.setImages(imageName);

        try {
            // 3. Gọi lệnh Update
            service.update(currentUser);
            System.out.println("3. [EXECUTE] Đã chạy lệnh service.update()");
            
            // ==================================================================
            // 4. KIỂM CHỨNG NGAY LẬP TỨC (QUERY NGƯỢC LẠI TỪ DB)
            // ==================================================================
            
            // Gọi thẳng xuống DB lấy dữ liệu tươi mới nhất lên
            User userAfter = service.findById(userId); 
            String imgAfter = (userAfter != null) ? userAfter.getImages() : "null";
            
            System.out.println("4. [SAU UPDATE] Kết quả query lại từ DB: " + imgAfter);

            // SO SÁNH
            if (imageName != null && imageName.equals(imgAfter)) {
                System.out.println(">>> KẾT LUẬN: THÀNH CÔNG! Database đã lưu đúng tên ảnh mới.");
            } else {
                System.out.println(">>> KẾT LUẬN: THẤT BẠI! Database VẪN GIỮ GIÁ TRỊ CŨ (Hoặc Null).");
                System.out.println("    -> Mong đợi: " + imageName);
                System.out.println("    -> Thực tế trong DB: " + imgAfter);
            }
            
            // Cập nhật Session
            session.setAttribute("account", currentUser);
            req.setAttribute("message", "Cập nhật hồ sơ thành công!");
            
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Cập nhật thất bại: " + e.getMessage());
        }
        
        System.out.println("================ KẾT THÚC DEBUG ================");

        req.setAttribute("user", currentUser);
        req.getRequestDispatcher("/views/my-account.jsp").forward(req, resp);
    }
}