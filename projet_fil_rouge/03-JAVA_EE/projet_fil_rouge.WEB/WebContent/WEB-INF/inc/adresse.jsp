<c:import url="/inc/header.jsp"/>
		<form method="get" action="URLAdresse">
			<fieldset>
				<legend>Informations adresse</legend>
				
				<label for="voie">Voie <span class="requis">*</span>
				</label> 				
				<input type="text" id="voie" name="voie" value="<c:out value="${adresse.voie}"/>" size="20" maxlength="60" />
				<span class="erreur">${form.erreurs['voie']}</span><br /> 
				<br /> 
				<label for="codePostal">Code Postal <span class="requis">*</span>
				</label> 
				<input type="text" id="codePostal" name="codePostal" value="<c:out value="${adresse.codePostal}"/>" size="5" maxlength="5" />
				<span class="erreur">${form.erreurs['codePostal']}</span><br /> 
				<br /> 
				<label for="ville">Ville <span class="requis">*</span>
				</label> 
				<input type="text" id="ville" name="ville" value="<c:out value="${adresse.ville}"/>" size="20" maxlength="20" /> 
				<span class="erreur">${form.erreurs['ville']}</span><br /> 
				<br />
				<label for="ville">Telephone <span class="requis">*</span>
				</label>				
				<input type="text" id="telephone" name="telephone" value="<c:out value="${adresse.telephone}"/>" size="17" maxlength="17" /> 
				<span class="erreur">${form.erreurs['telephone']}</span><br /> 
				<br />
				
			</fieldset>
			<input type="submit" value="Valider" /> <input type="reset"	value="Remettre à zéro" /> 
			<br />
		</form>
		<br>
			<a href="<c:url value="/index.jsp"/>">> Retour Accueil &lt </a>
<c:import url="/inc/footer.jsp"/>