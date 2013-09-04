<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<t:layout>
<jsp:body>
<h2>Edit Note</h2>
<form method="POST" action="notes/${note.id}">
	<input name="id" type="hidden" value="${note.id}"></input>
	<input name="_method" type="hidden" value="PUT"></input>
	<jsp:include page="partials/fields.jsp" />
</form>
</jsp:body>
</t:layout>