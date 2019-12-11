<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sup? - <s:property value="#session.currentFriendUserObject.username"/></title>
        <style media="screen">
          #p1 {color: darkorange;}
          #p2 {color: blue;}
        </style>
    </head>
    <body>
      <h1><s:property value="#session.currentFriendUserObject.username"/>'s Profile</h1>
      <h3>Bio:</h3>
      <p id="p1"><s:property value="#session.currentFriendUserObject.profile.bio"/></p>

      <table>
        <s:iterator value="#session.currentFriendUserObject.profile.comments" status="commentsStatus">
          <tr>
            <s:if test="#commentsStatus.even == true">
              <td style="background: #CCCCCC">
                <p id="p2"><b><s:property value="postedByUsername"/> #<s:property value="postedById"/>: </b><s:property value="text"/></p>
              </td>
            </s:if>
            <s:else>
              <td>
                <p id="p2"><b><s:property value="postedByUsername"/> #<s:property value="postedById"/>: </b><s:property value="text"/></p>
              </td>
            </s:else>
        </tr>
        </s:iterator>
    </table>
      <p>You can comment on <s:property value="#session.currentFriendUserObject.username"/>'s profile below.</p>

	       <s:form action="postComment">
           <label for="comment"><b>Comment: </b></label>
           <input type="text" placeholder="Enter Your Comment" name="comment.text" required autocomplete="off">
           <s:submit type="button" label="Post"/>
         </s:form>

      <s:form action="addFriend">
        <s:submit type="button" label="Add Friend"/>
      </s:form>

          <a href="<s:url action='viewAllUsers'/>">Back</a></p>

         <s:form action="logOff">
           <s:submit type="button" label="Logoff"/>
         </s:form>
    </body>
</html>
