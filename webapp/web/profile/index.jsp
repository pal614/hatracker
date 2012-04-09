<%@page import="hatracker.dao.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Uesr</title>
    </head>
    <body>
<%
String returnUrl = request.getParameter("returnUrl");
String gender = request.getParameter("gender");
if (gender != null && request.getUserPrincipal() != null)
{
    long uid = User.addUser(request.getUserPrincipal().getName(), gender);
    %>
    <h1>Added user: <%=uid%></h1>
    <a href="<%=returnUrl%>">Back</a>
    <%
}
else
{
%>
        <form method="POST">
            <div>Gender: 
                <label for="">Male</label><input name="gender" type="radio" value="m"/>
                <label for="">Female</label><input name="gender" type="radio" value="f"/>
            </div>
            <%
            if (returnUrl != null)
            {
            %>
                <input type="hidden" name="returnUrl" value="<%=returnUrl%>"/>
            <%
            }
            %>
            <div><input type="submit"/></div>
        </form>
<%
}
%>
    </body>
</html>
