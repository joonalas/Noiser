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
        <audio id="audioplayer" controls></audio><br/>
        
        <div>
            <button class="musicNavigation" id="genreNav">Genres</button>
            <button class="musicNavigation" id="artistNav">Artists</button>
            <button class="musicNavigation" id="albumNav">Albums</button>
            <button class="musicNavigation" id="songNav">Songs</button>
        </div>
        
        <div id="dataContainer">loading...</div>
        
        <form action="LoginServlet" method="post">
            <input type="hidden" name="action" value="logout">
            <input type="submit" value="Logout">
        </form>
        
        <script src="script/datacontent.js"></script>
    </body>
</html>
