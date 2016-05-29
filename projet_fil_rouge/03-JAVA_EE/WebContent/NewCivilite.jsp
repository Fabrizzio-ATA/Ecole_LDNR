<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Création d'une civilité</title>
<link type="text/css" rel="stylesheet" href="inc/style.css" />
</head>
<body>
	<div>
		<form method="get" action="URLCivilite">
			<fieldset>
				<legend>Informations civilité</legend>
				
				<label for="nom">Nom <span class="requis">*</span></label> 
				<input type="text" id="nom" name="nom" value="" size="64" maxlength="64" />
				<br /> 
				<label for="prenom">Prénom <span class="requis">*</span></label>
				<input type="text" id="prenom" name="prenom" value="" size="64" maxlength="64" /> 
				<br /> 
				<label for="sexe">Sexe <span class="requis">*</span></label> 
				<input type="text" id="sexe" name="sexe" value="" size="1" maxlength="1" />
				<br /> 
				<label for="dateNaissance">Date de Naissance AAAA-MM-JJ <span class="requis">*</span>
				</label>
				<input type="text" id="dateNaissance" name="dateNaissance" value="" size="10" maxlength="10" /> <br />
			</fieldset>
			<input type="submit" value="Valider" /> <input type="reset" value="Remettre à zéro" /> <br />
		</form>
	</div>
</body>
</html>