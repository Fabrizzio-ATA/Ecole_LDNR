<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>VUE Civilite</title>
</head>
<body>
	<p>	Civilite: </p>
	NOM :  ${ requestScope.civilite.nom }<br>
	Penom :${ requestScope.civilite.prenom }<br>
	Sexe : ${ requestScope.civilite.sexe }<br>
	Date Naissance : ${ requestScope.civilite.dateNaissance }<br>
	<br>
	<a href="index.html">> Retour Accueil &lt </a>	
</body>
</html>