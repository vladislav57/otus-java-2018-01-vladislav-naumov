package jetty.homework12.server;

import javax.servlet.http.Cookie;
import java.util.UUID;

public class AdminSessionBean {
    private static AdminSessionBean ourInstance = new AdminSessionBean();

    public static AdminSessionBean getInstance() {
        return ourInstance;
    }

    private static Cookie authToken;

    private AdminSessionBean() {
        String cookie = "" + UUID.randomUUID();
        authToken = new Cookie("authToken", cookie);
    }

    public boolean isAuthenticated(Cookie[] cookies) {
        for(Cookie c : cookies) {
            if(c.getValue().equals(authToken.getValue()))
                return true;
        }
        return false;
    }

    public Cookie getAuthToken() {
        return authToken;
    }
}
