<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sup? - <s:property value="#session.currentUserObject.username"/>'s Bio'</title>
    </head>
    <body>
      <h2>Sup <s:property value="#session.currentUserObject.username"/>?</h2>
      <h3>You can edit your bio below:</h3>
	       <s:form action="editBio">
           <p>Bio: <input type="text" name="bio" size="120" /></p>
           <s:submit type="button" label="Save changes"/>
         </s:form>

         <a href="<s:url action='backToProfile'/>">Back</a></p>
         <s:form action="logOff">
           <s:submit type="button" label="Logoff"/>
         </s:form>
    </body>
</html>
