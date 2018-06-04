package homework5.controller;

import homework3.entity.Company;
import homework5.service.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateCompanyController extends HttpServlet {
    private final Service service = Service.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/CreateCompanyView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Company company = new Company();
        company.setName(req.getParameter("name"));
        company.setCity(req.getParameter("city"));
        this.service.<Company>create(Company.class, company);
        resp.sendRedirect(req.getContextPath() + "/companies");
    }
}
