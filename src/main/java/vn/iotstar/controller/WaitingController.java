package vn.iotstar.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.iotstar.entity.User;

@WebServlet(urlPatterns = { "/waiting" })
public class WaitingController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        
        // Kiểm tra xem có Session và có tài khoản không
        if (session != null && session.getAttribute("account") != null) {
            User u = (User) session.getAttribute("account");
            req.setAttribute("username", u.getUsername());

            int roleId = u.getRoleid();
            
            if (roleId == 1) { 
                // Role 1: ADMIN
                resp.sendRedirect(req.getContextPath() + "/admin/home");
                
            } else if (roleId == 2) { 
                // Role 2: MANAGER (Sửa lại cho đúng logic, code cũ của bạn role 1 và 2 giống nhau)
                resp.sendRedirect(req.getContextPath() + "/manager/home");
                
            } else { 
                // Role 3 (hoặc khác): USER
                resp.sendRedirect(req.getContextPath() + "/user/home");
            }
            
        } else {
            // Nếu chưa đăng nhập mà cố tình vào /waiting -> Đá về Login
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
}