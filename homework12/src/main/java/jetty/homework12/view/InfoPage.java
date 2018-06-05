package jetty.homework12.view;

import jetty.homework11.CacheEngine;
import jetty.homework12.server.AdminSessionBean;
import jetty.homework12.server.CacheEngineBean;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class InfoPage extends HttpServlet {

    private CacheEngineBean cacheEngineBean = CacheEngineBean.getInstance();
    private AdminSessionBean adminSessionBean = AdminSessionBean.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if(adminSessionBean.isAuthenticated(req.getCookies())) {
            showInfoPage(resp);
        } else {
            resp.sendRedirect("login");
        }
    }

    private void showInfoPage(HttpServletResponse resp) throws IOException {
        CacheEngine cacheEngine = cacheEngineBean.getCacheEngine();

        resp.setContentType("text/html");
        resp.setStatus(HttpServletResponse.SC_OK);
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        BufferedReader br = new BufferedReader(new InputStreamReader(classloader.getResourceAsStream("Info.html")));
        PrintWriter pw = resp.getWriter();
        String line = null;
        while((line = br.readLine()) != null) {
            line = line.replaceAll("cacheHit", "" + cacheEngine.getHitCount());
            line = line.replaceAll("cacheMiss", "" + cacheEngine.getMissCount());
            pw.println(line);
        }
        pw.close();
        br.close();
    }
}
