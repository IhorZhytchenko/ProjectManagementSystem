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

public class EditDeveloperController extends HttpServlet {
    private final Service service = Service.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Company> companies = (List<Company>) this.service.getAll(Company.class);
        req.setAttribute("companies", companies);
        Developer developer = this.service.<Developer>getById(Developer.class, Long.parseLong(req.getParameter("id")));
        req.setAttribute("developer", developer);
        req.getRequestDispatcher("/WEB-INF/views/EditDeveloperView.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Developer developer = this.service.<Developer>getById(Developer.class, Long.parseLong(req.getParameter("id")));
        developer.setFirstName(req.getParameter("firstName"));
        developer.setLastName(req.getParameter("lastName"));
        developer.setSalary(Integer.parseInt(req.getParameter("salary")));
        developer.setAge(Integer.parseInt(req.getParameter("age")));
        Company company = this.service.<Company>getById(Company.class, Long.parseLong(req.getParameter("companyId")));
        developer.setCompany(company);
        developer.setSex(req.getParameter("sex"));
        this.service.<Developer>update(Developer.class, developer);
        resp.sendRedirect(req.getContextPath() + "/developers");

    }
}
