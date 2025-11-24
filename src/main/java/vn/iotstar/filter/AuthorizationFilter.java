package vn.iotstar.filter;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.entity.User;

// KHÔNG DÙNG @WebFilter NỮA VÌ ĐÃ KHAI BÁO TRONG WEB.XML
public class AuthorizationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Khởi tạo
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        
        // Lấy User (kiểm tra null session để tránh lỗi)
        User user = (session != null) ? (User) session.getAttribute("account") : null;

        // 1. ADMIN (Role 1)
        if (url.contains("/admin")) {
            if (user == null || user.getRoleid() != 1) {
                resp.sendRedirect(contextPath + "/login");
                return; // QUAN TRỌNG: Phải return để dừng code
            }
        }
        
        // 2. MANAGER (Role 2)
        else if (url.contains("/manager")) {
            if (user == null || user.getRoleid() != 2) {
                resp.sendRedirect(contextPath + "/login");
                return;
            }
        }
        
        // 3. USER (Bắt buộc đăng nhập)
        // Chặn các link user nhưng TRỪ link login/register/resource ra
        else if (url.contains("/user/") || url.contains("/home") || url.contains("/my-account")) {
            if (user == null) {
                resp.sendRedirect(contextPath + "/login");
                return;
            }
        }

        // CHỈ GỌI LỆNH NÀY 1 LẦN DUY NHẤT Ở CUỐI CÙNG
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Hủy
    }
}