package homework5.controller;

import homework3.entity.Company;
import homework3.entity.Developer;
import homework5.service.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DevelopersController extends HttpServlet {
    private final Service service = Service.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Developer> developers = (List<Developer>) this.service.getAll(Developer.class);
        req.setAttribute("developers", developers);
        req.getRequestDispatcher("/WEB-INF/views/DevelopersView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Developer developer = this.service.<Developer>getById(Developer.class, Long.parseLong(req.getParameter("id")));
        this.service.<Developer>delete(Developer.class, developer);
        resp.sendRedirect(req.getContextPath() + "/developers");
    }
}
