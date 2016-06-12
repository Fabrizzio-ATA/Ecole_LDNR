<c:import url="/inc/header.jsp"/>
		<form method="get" action="URLEnseignant">
			<fieldset>
				<legend>Informations enseignant</legend>
<!-- TODO : AJOUTER LES CHAMPS DIRECTEMENT PAR IMPORT DE JSP -->				
<!-- CHAMPS CIVILITE -->
				<h2>ENTRER LA CIVILITE:</h2>
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

<!-- CHAMPS UTILISATEUR -->
				<h2>ENTRER LES IDENTIFIANTS DE CONNEXION:</h2>
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
				
<!-- CHAMPS ADRESSE -->
				<h2>ENTRER L'ADRESSE:</h2>
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
			<input type="submit" value="Valider" /> <input type="reset" value="Remettre à zéro" /> <br />
		</form>
		<br>
	<a href="<c:url value="/index.jsp"/>">> Retour Accueil &lt </a>
<c:import url="/inc/footer.jsp"/>