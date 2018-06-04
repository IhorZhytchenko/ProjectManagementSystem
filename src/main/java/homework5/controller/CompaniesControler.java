package homework5.controller;

import homework3.entity.Company;
import homework5.service.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CompaniesControler extends HttpServlet {
    private final Service service = Service.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Company> companies = (List<Company>) this.service.getAll(Company.class);
        req.setAttribute("companies", companies);
        req.getRequestDispatcher("/WEB-INF/views/CompaniesView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Company company = this.service.<Company>getById(Company.class, Long.parseLong(req.getParameter("id")));
        this.service.<Company>delete(Company.class, company);
        resp.sendRedirect(req.getContextPath() + "/companies");
    }
}
