<%@page import="hatracker.auth.AuthUtil"%>
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
        String userId = AuthUtil.getOpenId(request);
        String thisURL = request.getRequestURI();

        if (userId != null)
        {
            %><p>Hello, <%=userId%>!
                You can <a href=" <%=AuthUtil.getLogoutURL(thisURL)%>">
                    sign out</a>.</p>
            <%
        } else {
            %><p>Please <a href="<%=AuthUtil.getLoginURL(thisURL)%>">sign in</a>.</p>
        <%
        }
        %>
        <a href="enter/">Enter</a>
    </body>
</html>
