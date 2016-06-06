<c:import url="/inc/header.jsp"/>
<c:if test="${!empty sessionScope.sessionUtilisateur }">
		<ul>
			<li><a href="<c:url value="NewCivilite.jsp"/>">> Creer une nouvelle civilite</a></li>
			<li><a href="<c:url value="NewAdresse.jsp"/>">> Creer une nouvelle adresse</a></li>
		</ul>
<c:import url="/inc/footer.jsp"/>
</c:if>