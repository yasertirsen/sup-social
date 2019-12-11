<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sup? - Home Page</title>
    </head>
    <body>
      <h2>Hello <s:property value="#session.currentUserObject.username"/>!</h2>
	       <s:form action="viewAllUsers">
           <s:submit type="button" label="Show All Users"/>
         </s:form>

         <s:form action="logOff">
           <s:submit type="button" label="Logoff"/>
         </s:form>
    </body>
</html>
