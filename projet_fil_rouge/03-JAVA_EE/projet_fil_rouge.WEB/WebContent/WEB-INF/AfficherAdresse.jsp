<c:import url="/inc/header.jsp"/>	
	<%-- Affichage de la chaîne "message" transmise par la servlet --%>
	<c:choose>
		<c:when test="${ requestScope.iserror == true }">
			<p class="erreur"> 
				${ requestScope.message }
			</p>
		</c:when>
		<c:otherwise>	
			<p class="info"> 
				${ requestScope.message } 
			<p>
			<p> Adresse: </p>
			<c:out value="VOIE : ${ requestScope.adresse.voie }"></c:out>
			<br>
			<c:out value="CP   : ${ requestScope.adresse.codePostal }"></c:out>
			<br>
			<c:out value="VILLE: ${ requestScope.adresse.ville }"></c:out>
			<br>
		</c:otherwise>
	</c:choose>

	<br>
	<a href="<c:url value="/index.jsp"/>">> Retour Accueil &lt </a>	
<c:import url="/inc/footer.jsp"/>	