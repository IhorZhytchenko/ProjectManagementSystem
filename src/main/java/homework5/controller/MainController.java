package homework5.controller;

import homework5.service.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainController extends HttpServlet {
    private final Service service = Service.getInstance();
    @Override
    public void destroy() {
        this.service.close();
    }

    @Override
    public void init() throws ServletException {
       this.service.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/MainView.jsp").forward(req, resp);
    }
}
