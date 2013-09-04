<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@tag description="layout" pageEncoding="UTF-8"%>
<%@attribute name="body" fragment="true" %>
<!DOCTYPE html>
<html>
	<head>
		<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
		<title>Springpad</title>
		<link rel="stylesheet" href="resources/stylesheets/style.css" />
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
	</head>
  <body>
      <jsp:doBody/>
      <script src="resources/javascripts/application.js"></script>
  </body>
</html>