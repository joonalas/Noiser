<%-- 
    Document   : login
    Created on : Mar 12, 2019, 1:09:36 AM
    Author     : joonalas
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Login</h1>
        <form action="LoginServlet" method="post">
            Username:<br/>
            <input type="text" name="username"><br/>
            Password:<br/>
            <input type="password" name="password"><br/><br/>
            <input type="hidden" name="action" value="login">
            <input type="submit" value="Submit">
        </form>
    </body>
</html>
