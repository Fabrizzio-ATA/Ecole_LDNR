<c:import url="/inc/header.jsp"/>
		<form method="get" action="URLCivilite">
			<fieldset>
				<legend>Informations civilité</legend>
				
				<label for="nom">Nom <span class="requis">*</span></label> 
				<input type="text" id="nom" name="nom" value="<c:out value="${civilite.nom}"/>" size="20" maxlength="64" />
				<span class="erreur">${form.erreurs['nom']}</span><br /> 
				<br /> 
				<label for="prenom">Prénom <span class="requis">*</span></label>
				<input type="text" id="prenom" name="prenom" value="<c:out value="${civilite.prenom}"/>" size="20" maxlength="64" /> 
				<span class="erreur">${form.erreurs['prenom']}</span><br /> 
				<br /> 
				<label for="sexe">Sexe <span class="requis">*</span></label> 
				<input type="text" id="sexe" name="sexe" value="<c:out value="${civilite.sexe}"/>" size="1" maxlength="1" />
				<span class="erreur">${form.erreurs['sexe']}</span><br /> 
				<br /> 
				<label for="dateNaissance">Date de Naissance AAAA-MM-JJ <span class="requis">*</span></label>
				<input type="text" id="dateNaissance" name="dateNaissance" value="<c:out value="${civilite.dateNaissance}"/>" size="10" maxlength="10" /> 
				<span class="erreur">${form.erreurs['dateNaissance']}</span><br /> 
				<br />
			</fieldset>
			<input type="submit" value="Valider" /> <input type="reset" value="Remettre à zéro" /> <br />
		</form>
		<br>
			<a href="<c:url value="/index.jsp"/>">> Retour Accueil &lt </a>
<c:import url="/inc/footer.jsp"/>