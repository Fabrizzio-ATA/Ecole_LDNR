<c:import url="/inc/header.jsp"/>
		<form method="get" action="URLUtilisateur">
			<fieldset>
				<legend>Informations utilisateur</legend>
				
				<label for="email">Email <span class="requis">*</span></label> 
				<input type="text" id="email" name="email" value="<c:out value="${utilisateur.email}"/>" size="20" maxlength="64" />
				<span class="erreur">${form.erreurs['email']}</span><br /> 
				<br /> 
				<label for="motdepasse">Mot de passe <span class="requis">*</span></label>
				<input type="text" id="motdepasse" name="motdepasse" value="<c:out value="${utilisateur.motdepasse}"/>" size="20" maxlength="64" /> 
				<span class="erreur">${form.erreurs['motdepasse']}</span><br /> 
				<br /> 
				<label for="confirmation">Confirmation <span class="requis">*</span></label>
				<input type="text" id="confirmation" name="confirmation" value="<c:out value="${utilisateur.confirmation}"/>" size="20" maxlength="64" /> 
				<span class="erreur">${form.erreurs['motdepasse']}</span><br /> 
				<br /> 
				<label for="role">Role <span class="requis">*</span></label>
				<input type="text" id="role" name="role" value="<c:out value="${utilisateur.role}"/>" size="20" maxlength="64" /> 
				<span class="erreur">${form.erreurs['role']}</span><br /> 
				<br /> 
			</fieldset>
			<input type="submit"  name="action" value="Insert" /> <input type="reset" value="Remettre à zéro" /> <br />
		</form>
		<br>
	<a href="<c:url value="/index.jsp"/>">> Retour Accueil &lt </a>
<c:import url="/inc/footer.jsp"/>