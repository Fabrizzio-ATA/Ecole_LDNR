<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Création d'une adresse</title>
<link type="text/css" rel="stylesheet" href="inc/style.css" />
</head>
<body>
	<nav>
		<ul>
			<li><a href="<c:url value="NewCivilite.jsp"/>">> Creer une nouvelle civilite. &lt</a> | </li>
			<li><a href="<c:url value="NewAdresse.jsp"/>">> Creer une nouvelle adresse. &lt</a> | </li>
		</ul>
	</nav>
	<div>
		<form method="get" action="URLAdresse">
			<fieldset>
				<legend>Informations adresse</legend>
				
				<label for="voie">Voie <span class="requis">*</span>
				</label> 
				<input type="text" id="voie" name="voie" value="" size="64" maxlength="64" />
				<br /> 
				<label for="codePostal">Code Postal <span class="requis">*</span>
				</label> 
				<input type="text" id="codePostal" name="codePostal" value="" size="5" maxlength="5" /> 
				<br /> 
				<label for="ville">Ville <span class="requis">*</span>
				</label> 
				<input type="text" id="ville" name="ville" value="" size="20" maxlength="20" /> 
				<br />
			</fieldset>
			<input type="submit" value="Valider" /> <input type="reset"	value="Remettre à zéro" /> 
			<br />
		</form>
		<br>
			<a href="<c:url value="/index.jsp"/>">> Retour Accueil &lt </a>
	</div>
</body>
</html>