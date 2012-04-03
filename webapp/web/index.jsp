<%-- 
    Document   : index
    Created on : Nov 19, 2011, 2:49:23 PM
    Author     : pal
--%>

<%@page import="java.security.Principal"%>
<%@page import="com.google.appengine.api.users.UserService"%>
<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World II!</h1>
        <%
        UserService userService = UserServiceFactory.getUserService();
        Principal p = request.getUserPrincipal();
        String thisURL = request.getRequestURI();

        if (p != null)
        {
            %><p>Hello, <%=request.getUserPrincipal().getName()%>!
                You can <a href=" <%=userService.createLogoutURL(thisURL)%>">
                    sign out</a>.</p>
            <%
        } else {
            %><p>Please <a href="<%=userService.createLoginURL(thisURL)%>">sign in</a>.</p>
        <%
        }
        %>
        <a href="enter/">Enter</a>
    </body>
</html>
