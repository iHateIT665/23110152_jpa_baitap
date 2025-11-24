package vn.iotstar.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.iotstar.entity.User;
import vn.iotstar.service.UserService;
import vn.iotstar.service.impl.UserServiceImpl;
import vn.iotstar.utils.Email;

@WebServlet(urlPatterns = "/forgotpassword")
public class ForgotPasswordController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/forgotpassword.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        UserService service = new UserServiceImpl();
        User user = service.findByEmail(email);

        if (user == null) {
            req.setAttribute("alert", "Email không tồn tại trong hệ thống!");
            req.getRequestDispatcher("/views/forgotpassword.jsp").forward(req, resp);
            return;
        }

        // Tạo mã OTP
        Email emailUtil = new Email();
        String otp = emailUtil.getRandom();

        // Gửi mail
        if (emailUtil.sendEmail(user, otp)) {
            HttpSession session = req.getSession();
            session.setAttribute("otp", otp);
            session.setAttribute("email", email);
            session.setAttribute("otpTime", System.currentTimeMillis());

            // Chuyển sang trang nhập mã
            req.getRequestDispatcher("/views/enterverificationcode.jsp").forward(req, resp);
        } else {
            req.setAttribute("alert", "Lỗi gửi mail! Vui lòng thử lại.");
            req.getRequestDispatcher("/views/forgotpassword.jsp").forward(req, resp);
        }
    }
}