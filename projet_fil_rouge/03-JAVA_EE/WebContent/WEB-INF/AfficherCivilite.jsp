<c:import url="/inc/header.jsp"/>
	<%-- Affichage de la chaîne "message" transmise par la servlet --%>
	<c:choose>
		<c:when test="${ requestScope.iserror == true }">
			<p class="erreur">${ requestScope.message }</p>
		</c:when>
		<c:otherwise>
			<p class="info"> 
				${ requestScope.message } 
			<p>
			<p>Civilite:</p>
			<c:out value="NOM :  ${ requestScope.civilite.nom }"></c:out>
			<br>
			<c:out value="Penom :${ requestScope.civilite.prenom }"></c:out>
			<br>
			<c:out value="Sexe : ${ requestScope.civilite.sexe }"></c:out>
			<br>
			<c:out value="Date Naissance : ${ requestScope.civilite.dateNaissance }"></c:out>
			<br>
		</c:otherwise>
	</c:choose>

	<br>
	<a href="<c:url value="/index.jsp"/>">> Retour Accueil &lt </a>
<c:import url="/inc/footer.jsp"/>