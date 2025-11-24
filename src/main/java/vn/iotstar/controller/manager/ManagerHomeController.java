package vn.iotstar.controller.manager;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.entity.Category;
import vn.iotstar.service.ICategoryService;
import vn.iotstar.service.impl.CategoryServiceImpl;

@WebServlet(urlPatterns = { "/manager/home" })
public class ManagerHomeController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    	ICategoryService cateService = new CategoryServiceImpl();
    	List<Category> list = cateService.findAll();
    	req.setAttribute("listcate", list);
    	req.getRequestDispatcher("/views/manager/home.jsp").forward(req, resp);
    }
}