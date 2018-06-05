package jetty.homework12.view;

import jetty.homework12.server.AdminSessionBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class LoginPage extends HttpServlet {

    private String login = "admin";
    private String password = "password";

    private AdminSessionBean adminSessionBean = AdminSessionBean.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doLogin(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doLogin(req, resp);
    }

    private void doLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        if(adminSessionBean.isAuthenticated(req.getCookies()))
            resp.sendRedirect("info");

        String valid = "name=" + login + "&password=" + password;
        if(req.getQueryString() != null) {
            if (req.getQueryString().contains(valid)) {
                resp.addCookie(adminSessionBean.getAuthToken());
                resp.sendRedirect("info");
            }
        }

        showLoginPage(resp);
    }

    private void showLoginPage(HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        resp.setStatus(HttpServletResponse.SC_OK);
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        BufferedReader br = new BufferedReader(new InputStreamReader(classloader.getResourceAsStream("Login.html")));
        PrintWriter pw = resp.getWriter();
        String line = null;
        while((line = br.readLine()) != null) {
            pw.println(line);
        }
        pw.close();
        br.close();
    }
}
