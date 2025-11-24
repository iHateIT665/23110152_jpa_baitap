package vn.iotstar.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet(urlPatterns = "/enterverificationcode")
public class VerifyCodeController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String inputCode = req.getParameter("authCode");
        HttpSession session = req.getSession();
        String otp = (String) session.getAttribute("otp");
        Long otpTime = (Long) session.getAttribute("otpTime");

        if (otp == null || otpTime == null) {
            req.setAttribute("alert", "Phiên làm việc hết hạn!");
            req.getRequestDispatcher("/views/forgotpassword.jsp").forward(req, resp);
            return;
        }

        // Check hết hạn (5 phút)
        if (System.currentTimeMillis() - otpTime > 5 * 60 * 1000) {
            req.setAttribute("alert", "Mã OTP đã hết hạn!");
            req.getRequestDispatcher("/views/forgotpassword.jsp").forward(req, resp);
            return;
        }

        if (inputCode.equals(otp)) {
            req.getRequestDispatcher("/views/newpass.jsp").forward(req, resp);
        } else {
            req.setAttribute("alert", "Mã xác thực không đúng!");
            req.getRequestDispatcher("/views/enterverificationcode.jsp").forward(req, resp);
        }
    }
}