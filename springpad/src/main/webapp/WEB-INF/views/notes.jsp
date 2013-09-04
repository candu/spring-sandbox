<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<t:layout>
<jsp:body>
<h1>Your Notes</h1>
<p>
	<a class="button" href="notes/new">+ New Note</a>
</p>
<ul>
	<c:forEach items="${notes}" var="note">
		<li>
			<a class="button" href="notes/${note.id}/edit">Edit</a>
			<a class="button destroy" href="notes/${note.id}">Delete</a>
			<a href="notes/${note.id}">${note.title}</a>
		</li>
	</c:forEach>
</ul>
</jsp:body>
</t:layout>