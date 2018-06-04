package homework5.controller;


import homework3.entity.Project;
import homework5.service.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProjectsController extends HttpServlet {
    private final Service service = Service.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Project> projects = (List<Project>) this.service.getAll(Project.class);
        req.setAttribute("projects", projects);
        req.getRequestDispatcher("/WEB-INF/views/ProjectsView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Project project = this.service.<Project>getById(Project.class, Long.parseLong(req.getParameter("id")));
        this.service.<Project>delete(Project.class, project);
        resp.sendRedirect(req.getContextPath() + "/projects");
    }
}
