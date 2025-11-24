package vn.iotstar.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.utils.Constant;

@WebServlet(urlPatterns = { "/logout" })
public class LogoutController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. Xóa Session (Quan trọng nhất)
        HttpSession session = req.getSession();
        session.removeAttribute("account"); // Xóa user khỏi session
        
        // 2. Xóa Cookie "Remember Me" (Nếu có)
        // Phải tạo một cookie có cùng tên, set thời gian sống = 0 rồi gửi lại
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (Constant.COOKIE_REMEMBER.equals(cookie.getName())) {
                    cookie.setMaxAge(0); // Hủy cookie ngay lập tức
                    cookie.setPath("/"); // Phải set Path giống lúc tạo
                    resp.addCookie(cookie);
                    break;
                }
            }
        }

        // 3. Chuyển hướng về trang Login
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}