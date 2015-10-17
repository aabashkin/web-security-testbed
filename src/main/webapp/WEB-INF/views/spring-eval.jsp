<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html lang="en">

<body>
	Spring Expression Language Injection Test - Eval
	<spring:eval expression="${param.input}" />
	
	<spring:eval expression="param.input" var="result" />
	
</body>

</html>