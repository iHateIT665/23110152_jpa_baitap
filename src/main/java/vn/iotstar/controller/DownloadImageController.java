package vn.iotstar.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.utils.Constant;

@WebServlet(urlPatterns = "/image") 
public class DownloadImageController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileName = req.getParameter("fname");
        
        if (fileName == null || fileName.isEmpty()) {
            return;
        }

        File file = new File(Constant.UPLOAD_DIRECTORY + File.separator + fileName);
        
        if (file.exists()) {
            // Tự động đoán loại file (JPEG, PNG, WEBP...)
            String mimeType = getServletContext().getMimeType(file.getName());
            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }
            resp.setContentType(mimeType);
            
            try (FileInputStream fis = new FileInputStream(file)) {
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                while ((bytesRead = fis.read(buffer)) != -1) {
                    resp.getOutputStream().write(buffer, 0, bytesRead);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Không tìm thấy file ảnh: " + file.getAbsolutePath());
        }
    }
}