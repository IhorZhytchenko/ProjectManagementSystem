package homework5.controller;

import homework3.entity.Company;
import homework3.entity.Customer;
import homework3.entity.Project;
import homework5.service.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EditProjectController extends HttpServlet {
    private final Service service = Service.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Company> companies = (List<Company>) this.service.getAll(Company.class);
        req.setAttribute("companies", companies);
        List<Customer> customers = (List<Customer>) this.service.getAll(Customer.class);
        req.setAttribute("customers", customers);
        Project project = this.service.<Project>getById(Project.class, Long.parseLong(req.getParameter("id")));
        req.setAttribute("project", project);
        req.getRequestDispatcher("/WEB-INF/views/EditProjectView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Project project = this.service.<Project>getById(Project.class, Long.parseLong(req.getParameter("id")));
        project.setName(req.getParameter("name"));
        project.setDescription(req.getParameter("desc"));
        project.setCost(Integer.parseInt(req.getParameter("cost")));
        Company company = this.service.<Company>getById(Company.class, Long.parseLong(req.getParameter("companyId")));
        Customer customer = this.service.<Customer>getById(Customer.class, Long.parseLong(req.getParameter("customerId")));
        project.setCompany(company);
        project.setCustomer(customer);
        this.service.<Project>update(Project.class, project);
        resp.sendRedirect(req.getContextPath() + "/projects");
    }
}
