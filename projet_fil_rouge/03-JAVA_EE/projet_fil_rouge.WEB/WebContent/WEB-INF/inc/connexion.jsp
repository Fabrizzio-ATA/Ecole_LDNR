				<div class="conn_container">
					<h1>Gestion Ecole</h1>
					<p>Connectez vous pour acceder à votre espace</p>
					<form action="URLConnexion" method="post" class="form form--login">
							<c:choose>
								 <c:when test="${(!empty form.erreurs['email']) && (empty form.erreurs['motdepasse'])}"><p class="erreur">le ${form.erreurs['email']} est invalide</p></c:when>
								 <c:when test="${!empty form.erreurs['motdepasse'] && (empty form.erreurs['email'])}"><p class="erreur">le ${form.erreurs['motdepasse']} est invalide</p></c:when>
								 <c:when test="${(!empty form.erreurs['email']) && (!empty form.erreurs['motdepasse'])}"><p class="erreur">le ${form.erreurs['email']} et le ${form.erreurs['motdepasse']} sont invalide</p></c:when>
								 <c:otherwise></c:otherwise>
							</c:choose>
					        <div class="form__field">
					          <label class="fa fa-user" for="login__username"><span class="hidden">Nom d'utilisateur</span></label>
					          <input id="login__username" type="text" name="email" value="<c:out value="${utilisateur.email}"/>" class="form__input" placeholder="Nom d'utilisateur" required>
					        </div>
							
							
					        <div class="form__field">
					          <label class="fa fa-lock" for="login__password"><span class="hidden">Mot de Passe</span></label>
					          <input id="login__password" name="motdepasse" type="password" class="form__input" placeholder="Mot de Passe" required>
					        </div>
					
					        <div class="form__field">
					          <input type="submit" value="Se Connecter">
					        </div>
					</form>
				</div>
			</div>
		</main>
	</body>
</html>