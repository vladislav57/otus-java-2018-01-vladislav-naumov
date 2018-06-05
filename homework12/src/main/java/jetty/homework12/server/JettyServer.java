package jetty.homework12.server;

import jetty.homework11.CacheEngine;
import jetty.homework12.view.InfoPage;
import jetty.homework12.view.LoginPage;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class JettyServer {

    private CacheEngine cacheEngine;
    private Server server;
    private CacheEngineBean cacheEngineBean = CacheEngineBean.getInstance();

    public JettyServer(CacheEngine cachedService) {
        this.cacheEngine = cachedService;
    }

    public void start() throws Exception {
        server = new Server(8090);
        ServletContextHandler servletHandler = new ServletContextHandler(server, "/");
        servletHandler.addServlet(LoginPage.class, "/login");
        servletHandler.addServlet(InfoPage.class, "/info");
        cacheEngineBean.setCacheEngine(cacheEngine);
        server.start();
    }
}
