<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Gestion Ecole</title>
		<link type="text/css" rel="stylesheet" href="<c:url value="ressources/css/style.css" />" />
		<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
		<script type="text/javascript" src="<c:url value="ressources/js/apps.js" />" ></script>
	</head>
	<body>
	    <header>
	        <div class="centrer">
	            <h1>Ecole LDNR</h1>
	            <img src="<c:url value="ressources/img/logo.png" />">
	        </div>
	    </header>
	    <nav class="centrer">
	        <ul>
	            <li><a href="<c:url value="#"/>">Acceuil</a></li>
	            <li><a href="<c:url value="#"/>">Classe</a></li>
	            <li><a href="<c:url value="#"/>">Eleves</a></li>
	            <li><a href="<c:url value="#"/>">Enseignant</a></li>
	        </ul>
	        <a href="URLDeconnexion"> <button type="button">déconnexion</button></a>
	    </nav>
	    <main>
        	<div class="centrer">
        	
       		<c:if test="${empty sessionScope.sessionUtilisateur}">
       			<%-- Si l'utilisateur n'est pas connecté on le redirige vers la page de connexion --%>
				<c:redirect url="URLConnexion"/>
			</c:if>
			<c:if test="${!empty sessionScope.sessionUtilisateur}">
				<%-- Si l'utilisateur existe en session, alors on affiche son adresse email. --%>
				<p class="succes">Vous êtes connecté(e) avec l'adresse :
				${sessionScope.sessionUtilisateur.email}</p>
			</c:if>