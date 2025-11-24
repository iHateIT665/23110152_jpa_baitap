package vn.iotstar.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.iotstar.service.UserService;
import vn.iotstar.service.impl.UserServiceImpl;

@WebServlet(urlPatterns = "/newpass")
public class UpdatePasswordController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newPassword = req.getParameter("newPassword");
        String confirmPassword = req.getParameter("confirmPassword");
        
        HttpSession session = req.getSession();
        String email = (String) session.getAttribute("email");

        if (newPassword.equals(confirmPassword)) {
            UserService service = new UserServiceImpl();
            boolean isUpdated = service.updatePassword(email, newPassword);
            
            if (isUpdated) {
                session.removeAttribute("otp");
                session.removeAttribute("email");
                session.removeAttribute("otpTime");
                req.setAttribute("alert", "Đổi mật khẩu thành công! Vui lòng đăng nhập.");
                req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
            } else {
                req.setAttribute("alert", "Lỗi hệ thống!");
                req.getRequestDispatcher("/views/newpass.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("alert", "Mật khẩu không khớp!");
            req.getRequestDispatcher("/views/newpass.jsp").forward(req, resp);
        }
    }
}