<c:import url="/inc/header.jsp"/>
	<form method="post" action="connexion">
		<fieldset>
			<legend>Connexion</legend>

			<p>Vous pouvez vous connecter via ce formulaire.</p>
			<label for="nom">Adresse email <span class="requis">*</span></label>
			<input type="email" id="email" name="email"
				value="<c:out value="${utilisateur.email}"/>" size="20"	maxlength="60" />
				 <span class="erreur">${form.erreurs['email']}</span><br />

			 <label for="motdepasse">Mot de passe <span class="requis">*</span></label> 
				<input type="password" id="motdepasse"name="motdepasse" value="" size="20" maxlength="20" />
				 <span class="erreur">${form.erreurs['motdepasse']}</span> <br />

				  <input type="submit" value="Connexion" class="sansLabel" /> <br />
			<p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
		</fieldset>
	</form>
<c:import url="/inc/footer.jsp"/>