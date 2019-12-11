<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sup? - Already Friends!</title>
    </head>
    <body>
      <h2>Sup <s:property value="#session.currentUser"/>?<br>
      <s:property value="#session.currentFriendUser"/> is already a friend!</h2>

        <a href="<s:url action='viewAllUsers'/>">Back</a></p>
    </body>
</html>
