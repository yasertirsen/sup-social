<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sup? - <s:property value="#session.currentUserObject.username"/></title>
        <style media="screen">
          #p1 {color: darkorange;}
          #p2 {color: purple;}
        </style>
    </head>
    <body>
      <h1><s:property value="#session.currentUserObject.username"/>'s  Profile</h1>
      <h2>Welcome back <s:property value="#session.currentUserObject.username"/>!</h2>
      <h3>Bio:</h3>
      <p id="p1"><s:property value="#session.currentUserObject.profile.bio"/></p>
      <s:form action="editBioPage">
        <p><button type="submit" class="editbiobtn">Edit bio</button></p>
     </s:form>
     <s:form action="viewFriends">
       <p><button type="submit" class="viewfriendsbtn">View Firends</button></p>
    </s:form>
    <label for="comment"><b>Comments: </b></label>
     <table>
       <s:iterator value="#session.currentUserObject.profile.comments" status="commentsStatus">
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
    <p>You can comment on your profile below.</p>

       <s:form action="postCommentOnProfile">
        <label for="comment"><b>Comment: </b></label>
         <input type="text" placeholder="Enter Your Comment" name="comment.text" required autocomplete="off">
         <p><s:submit type="button" label="Post"/></p>
       </s:form>
	       <s:form action="viewAllUsers">
           <s:submit type="button" label="View All Members"/>
         </s:form>

         <s:form action="logOff">
           <s:submit type="button" label="Logoff"/>
         </s:form>
    </body>
</html>
