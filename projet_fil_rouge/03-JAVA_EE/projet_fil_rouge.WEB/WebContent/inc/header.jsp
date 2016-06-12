<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Gestion Ecole</title>
		<link type="text/css" rel="stylesheet" href="<c:url value="ressources/css/style.css" />" />
		<c:if test="${empty sessionScope.sessionUtilisateur}">
			<link type="text/css" rel="stylesheet" href="<c:url value="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css" />" />
		</c:if>
		<c:if test="${!empty sessionScope.sessionUtilisateur}">
			<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
			<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
			<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
			<script type="text/javascript" src="<c:url value="ressources/js/apps.js" />" ></script>
		</c:if>
	</head>
	<body>
	<c:if test="${!empty sessionScope.sessionUtilisateur}">
	    <header>
	        <div class="centrer">
	            <h1>Ecole LDNR</h1>
	            <img src="<c:url value="ressources/img/logo.png" />">
	        </div>
	    </header>
	    <nav class="centrer">
	        <ul>
	            <li><a href="<c:url value="index.jsp"/>">Accueil</a></li>
	            <li><a href="<c:url value="#"/>">Classe</a></li>
	            <li><a href="<c:url value="#"/>">Eleves</a></li>
	            <li><a href="<c:url value="/URLEnseignant"/>">Enseignant</a></li>
	            <li><a href="<c:url value="menuUtilisateur.jsp"/>">Utilisateur</a></li>
	        </ul>
	        	<a href="<c:url value="/URLDeconnexion"/>">déconnexion</a>
	    </nav>
	</c:if>
	    <main>
        	<div class="centrer">
    <c:if test="${empty sessionScope.sessionUtilisateur}">
				<c:import url="/WEB-INF/inc/connexion.jsp"/>
	</c:if>