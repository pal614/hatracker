package hatracker.auth;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;

/**
 * Collection of static methods covering Authentication, Authorization and Accounting
 * @author pal
 */
public class AuthUtil
{
    public static String getOpenId(HttpServletRequest request)
    {
        String openId = null;
        Principal p = request.getUserPrincipal();
        if (p != null)
        {
            openId = p.getName();
        }
        return openId;
    }
    
    public static String getLoginURL(String thisURL)
    {
        UserService userService = UserServiceFactory.getUserService();
        String loginURL = userService.createLoginURL(thisURL);
        return loginURL;
    }
    
    public static String getLogoutURL(String thisURL)
    {
        UserService userService = UserServiceFactory.getUserService();
        String logoutURL = userService.createLogoutURL(thisURL);
        return logoutURL;
    }

}
