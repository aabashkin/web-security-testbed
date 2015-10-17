<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Stateful Scan Test -- PetClinic Visits</title>
</head>
<body>

<form method="POST">
<table>
	<tr>
		<th>Select</th>
		<th>Status</th>
		<th>ID</th>
		<th>Description</th>
		<th>Comment</th>
	</tr>
	<c:forEach var="visit" items="${visits }">
		<tr>
			<td><c:if test="${visit.isOpen }"><input type="radio" name="visitId" value="${visit.visitId }"></c:if> 
				<c:if test="${visit.isOpen == false }"><input type="radio" name="visitId" value="null" disabled></c:if></td>
			<td><c:if test="${visit.isOpen }">Open</c:if> 
				<c:if test="${visit.isOpen == false }">Closed</c:if></td>
			<td>${visit.visitId }</td>
			<td>${visit.description }</td>
			<td>${visit.comment }</td>
		</tr>
	</c:forEach>
</table>
<input type="text" name="comment">
<input type="submit" value="Submit">
</form>
</body>
</html>
