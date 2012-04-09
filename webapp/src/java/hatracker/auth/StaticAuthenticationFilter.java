package hatracker.auth;

import com.google.appengine.api.datastore.Entity;
import hatracker.dao.User;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This is a filter for redirecting static (non AJAX) pages to authentication page.
 * @author pal
 */
public class StaticAuthenticationFilter implements Filter
{

    public StaticAuthenticationFilter()
    {
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException
    {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        String userId = AuthUtil.getOpenId(httpRequest);
        String thisURL = httpRequest.getRequestURI();

        if (userId == null)
        {
            String loginURL = AuthUtil.getLoginURL(thisURL);
            httpResponse.sendRedirect(loginURL);
        } else
        {
            Entity user = User.getUser(userId);
            if (user == null)
            {
                httpResponse.sendRedirect("/profile?returnUrl=" + thisURL);
            }
            else
            {
                chain.doFilter(request, response);
            }
        }
    }

    /**
     * Destroy method for this filter
     */
    @Override
    public void destroy()
    {
    }

    /**
     * Init method for this filter
     */
    @Override
    public void init(FilterConfig filterConfig)
    {
    }
}
