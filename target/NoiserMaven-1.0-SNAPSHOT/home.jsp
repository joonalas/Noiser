<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Noiser Home</title>
        <link rel="stylesheet" type="text/css" href="CSS/main.css">
    </head>
    <body>
        <audio controls>
            <!--<c:forEach var="soundfile" items="${soundfiles}">
                <source src='<c:out value="${soundfile.path}"/>' type="audio/<c:out value="${soundfile.format}"/>"/>
            </c:forEach>-->
            <source src='sounds/DSBM.mp3' type="audio/mpeg">
            Your browser doesn't support audio tag...
        </audio>
        
        <div id="dataContent">loading...</div>
        
        <form action="LoginServlet" method="post">
            <input type="hidden" name="action" value="logout">
            <input type="submit" value="Logout">
        </form>
        
        <script src="script/datacontent.js"></script>
    </body>
</html>
