package vn.iotstar.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.iotstar.entity.User;
import vn.iotstar.service.UserService;
import vn.iotstar.service.impl.UserServiceImpl;
import vn.iotstar.utils.Constant;

@WebServlet(urlPatterns = { "/login" })
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    UserService service = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Nếu đã đăng nhập rồi thì đẩy thẳng sang trang Waiting để nó tự chia luồng
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("account") != null) {
            resp.sendRedirect(req.getContextPath() + "/waiting");
            return;
        }

        // Xử lý Cookie để điền sẵn vào form (nếu có)
        String username = "";
        String password = "";
        boolean remember = false;
        
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (Constant.COOKIE_REMEMBER.equals(cookie.getName())) {
                    username = cookie.getValue();
                    remember = true;
                }
                if (Constant.COOKIE_PASSWORD.equals(cookie.getName())) {
                    password = cookie.getValue();
                }
            }
        }

        req.setAttribute("username", username);
        req.setAttribute("password", password);
        req.setAttribute("remember", remember);
        req.getRequestDispatcher(Constant.Path.LOGIN).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String username = req.getParameter("uname");
        String password = req.getParameter("psw");
        String rememberParam = req.getParameter("remember");
        boolean isRememberMe = "on".equals(rememberParam);

        User user = service.login(username, password);

        if (user != null) {
            // 1. Lưu Session
            HttpSession session = req.getSession(true);
            session.setAttribute("account", user);

            // 2. Lưu Cookie
            handleCookie(resp, username, password, isRememberMe);

            // 3. Chuyển hướng sang Waiting (Để Waiting lo việc phân quyền)
            resp.sendRedirect(req.getContextPath() + "/waiting");
            
        } else {
            req.setAttribute("alert", "Tài khoản hoặc mật khẩu không đúng!");
            req.setAttribute("username", username); 
            req.getRequestDispatcher(Constant.Path.LOGIN).forward(req, resp);
        }
    }

    private void handleCookie(HttpServletResponse resp, String username, String password, boolean remember) {
        if (remember) {
            Cookie cookieUser = new Cookie(Constant.COOKIE_REMEMBER, username);
            Cookie cookiePass = new Cookie(Constant.COOKIE_PASSWORD, password);
            cookieUser.setPath("/");
            cookiePass.setPath("/");
            cookieUser.setMaxAge(7 * 24 * 60 * 60);
            cookiePass.setMaxAge(7 * 24 * 60 * 60);
            resp.addCookie(cookieUser);
            resp.addCookie(cookiePass);
        } else {
            Cookie cookieUser = new Cookie(Constant.COOKIE_REMEMBER, "");
            cookieUser.setPath("/");
            cookieUser.setMaxAge(0);
            Cookie cookiePass = new Cookie(Constant.COOKIE_PASSWORD, "");
            cookiePass.setPath("/");
            cookiePass.setMaxAge(0);
            resp.addCookie(cookieUser);
            resp.addCookie(cookiePass);
        }
    }
}