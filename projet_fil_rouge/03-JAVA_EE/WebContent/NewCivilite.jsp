<c:import url="/inc/header.jsp"/>
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
		<br>
			<a href="<c:url value="/index.jsp"/>">> Retour Accueil &lt </a>
<c:import url="/inc/footer.jsp"/>