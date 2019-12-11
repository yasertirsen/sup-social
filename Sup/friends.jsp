<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sup? - Friends</title>
    </head>
    <body>
      <h2>Hello <s:property value="#session.currentUserObject.username"/>! <br>
      My Friends:</h2>

        <table>
          <s:iterator value="friends" status="friendsStatus">
            <tr>
              <s:form action="friendProfile">
            	<s:if test="#friendsStatus.even == true">

                <td style="background: #CCCCCC">
                  <b><s:property value="username"/> #<s:property value="id"/></b><button type="submit" class="viewprofilebtn">View Profile</button>
                  <input type="hidden" name="user.username" value="<s:property value='username'/>">
                </td>
              </s:if>
              <s:else>
                <td>
                  <b><s:property value="username"/> #<s:property value="id"/></b><button type="submit" class="viewprofilebtn">View Profile</button>
                  <input type="hidden" name="user.username" value="<s:property value='username'/>">
                </td>
              </s:else>
              </s:form>
              <tr>
          </s:iterator>
        </table>

          <a href="<s:url action='backToProfile'/>">My profile</a></p>
      <s:form action="logOff">
        <s:submit type="button" label="Logoff"/>
      </s:form>

    </body>
</html>
