<c:import url="/inc/header.jsp"/>

	<c:if test="${empty sessionScope.sessionUtilisateur}">
		<c:redirect url="URLConnexion"/>
	</c:if>
	<c:if test="${!empty sessionScope.sessionUtilisateur}">
		<%-- Si l'utilisateur existe en session, alors on affiche son adresse
		email. --%>
		<p class="succes">Vous êtes connecté(e) avec l'adresse :
		${sessionScope.sessionUtilisateur.email}</p>
	</c:if>
			
		<ul>
			<li><a href="<c:url value="NewCivilite.jsp"/>">> Creer une nouvelle civilite</a></li>
			<li><a href="<c:url value="NewAdresse.jsp"/>">> Creer une nouvelle adresse</a></li>
		</ul>
<c:import url="/inc/footer.jsp"/>