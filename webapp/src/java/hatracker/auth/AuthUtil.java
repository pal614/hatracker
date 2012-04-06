package hatracker.auth;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import java.io.IOException;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Collection of static methods covering Authentication, Authorization and Accounting
 * @author pal
 */
public class AuthUtil
{
    public static Principal getUserId(HttpServletRequest request)
    {
        Principal p = request.getUserPrincipal();
        String openId = p.getName();
        return p;
    }
    
    public static void redirectToLogin(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        String thisURL = request.getRequestURI();
        UserService userService = UserServiceFactory.getUserService();
        String loginURL = userService.createLoginURL(thisURL);
        response.sendRedirect(loginURL);
    }
    
    static boolean isRegistered(String openId)
    {
        return true;
    }
}
