<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>VUE Adresse</title>
</head>
<body>
	<%-- Affichage de la chaîne "message" transmise par la servlet --%>
	<p class="info"> $ {requestScope.message }</p>
	
	<p> Adresse: </p>
	VOIE : ${ requestScope.adresse.voie }<br>
	CP   : ${ requestScope.adresse.codePostal }<br>
	VILLE: ${ requestScope.adresse.ville }<br>
	<br>
	<a href="index.html">> Retour Accueil &lt </a>	
</body>
</html>