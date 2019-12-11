<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sup? - Register</title>
    </head>
    <body>

           <h1>Register</h1>
           <p>Please fill in this form to create an account.</p>
           <hr>
             <s:form action="register">
           <label for="username"><b>Username</b></label>
           <input type="text" placeholder="Enter Username" name="user.username" required autocomplete="off">

           <label for="email"><b>Email</b></label>
           <input type="text" placeholder="Enter Email" name="user.email" required autocomplete="off">

           <label for="psw"><b>Password</b></label>
           <input type="password" placeholder="Enter Password" name="user.password" required autocomplete="off">
             
           <hr>

           <button type="submit" class="registerbtn">Register</button>
         </s:form>
    </body>
</html>
