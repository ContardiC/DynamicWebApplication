<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DWA - Products</title>
</head>
<body>
<%
    
    session = request.getSession(false); // false per non creare una nuova sessione se non esiste

    
    if (session != null && session.getAttribute("email") != null) {
        String email = (String) session.getAttribute("email");
%>
        <p>Benvenuto, <%= email %>!</p>
        <a href="ShowProductServlet">Mostra i prodotti</a>
<%
    } else {
%>
        <p>Devi effettuare il login.</p>
<%
    }
%>
</body>
</html>