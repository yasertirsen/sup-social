<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sup? - Members</title>
    </head>
    <body>
      <h2>Hello <s:property value="#session.currentUserObject.username"/>! <br>
      You can add friends below</h2>

        <table>
          <s:iterator value="users" status="usersStatus">
            <tr>
              <s:form action="friendProfile">
            	<s:if test="#usersStatus.even == true">

                <td style="background: #CCCCCC">
                  <s:property value="username"/><button type="submit" class="viewprofilebtn">View Profile</button>
                  <input type="hidden" name="user.username" value="<s:property value='username'/>">
                </td>
              </s:if>
              <s:else>
                <td>
                  <s:property value="username"/><button type="submit" class="viewprofilebtn">View Profile</button>
                  <input type="hidden" name="user.username" value="<s:property value='username'/>">
                </td>
                <!--<td><s:property value="username"/><button type="submit" class="addfriendbtn">Add Friend</button></td>-->
              </s:else>
              </s:form>
            </tr>
          </s:iterator>
          </table>
          <a href="<s:url action='backToProfile'/>">My profile</a></p>
      <s:form action="logOff">
        <s:submit type="button" label="Logoff"/>
      </s:form>
    </body>
</html>
