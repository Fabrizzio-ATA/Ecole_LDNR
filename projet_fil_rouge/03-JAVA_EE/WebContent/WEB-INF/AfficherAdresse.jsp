<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>VUE Adresse</title>
<link type="text/css" rel="stylesheet" href="inc/style.css" />
</head>
<body>
	<nav>
		<ul>
			<li><a href="<c:url value="NewCivilite.jsp"/>">> Creer une nouvelle civilite. &lt</a> | </li>
			<li><a href="<c:url value="NewAdresse.jsp"/>">> Creer une nouvelle adresse. &lt</a> | </li>
		</ul>
	</nav>
	
	<%-- Affichage de la chaîne "message" transmise par la servlet --%>
	<c:choose>
		<c:when test="${ requestScope.iserror == true }">
			<p class="erreur"> 
				${ requestScope.message }
			</p>
		</c:when>
		<c:otherwise>	
			<p class="info"> 
				${ requestScope.message } 
			<p>
			<p> Adresse: </p>
			<c:out value="VOIE : ${ requestScope.adresse.voie }"></c:out>
			<br>
			<c:out value="CP   : ${ requestScope.adresse.codePostal }"></c:out>
			<br>
			<c:out value="VILLE: ${ requestScope.adresse.ville }"></c:out>
			<br>
		</c:otherwise>
	</c:choose>

	<br>
	<a href="<c:url value="/index.jsp"/>">> Retour Accueil &lt </a>	
</body>
</html>