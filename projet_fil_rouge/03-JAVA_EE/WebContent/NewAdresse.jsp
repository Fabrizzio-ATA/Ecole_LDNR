<c:import url="/inc/header.jsp"/>
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
<c:import url="/inc/footer.jsp"/>