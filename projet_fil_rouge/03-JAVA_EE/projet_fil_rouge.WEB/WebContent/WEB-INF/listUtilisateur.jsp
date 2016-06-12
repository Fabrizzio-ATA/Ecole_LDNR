<c:import url="/inc/header.jsp"/>
	
	<h3>Liste des Utilisateurs:</h3>
	<ul>
		<c:forEach items="${ requestScope.liste }" var="utilisateur" varStatus="loop" >
		<%-- Affiche le nom du parametre --%>
		<li>Utilisateur n° ${ loop.index} :</li>
		<li>Login: 			<c:out value="${ utilisateur.email }" /></li>
		<li>Mot de passe: 	<c:out value="${ utilisateur.motdepasse }" /></li>
		<li>Role:			<c:out value="${ utilisateur.role }" /></li>
		</c:forEach>	
	</ul>
	
	<br>
	<a href="<c:url value="/index.jsp"/>">> Retour Accueil &lt </a>
<c:import url="/inc/footer.jsp"/>