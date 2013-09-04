<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<t:layout>
<jsp:body>
<h1>${note.title}</h1>
<ul>
	<li>
		<a class="button" href="notes">Index</a>
		<a class="button" href="notes/${note.id}/edit">Edit</a>
		<a class="button destroy" href="notes/${note.id}">Delete</a>
	</li>
</ul>
<p>${note.data}</p>
</jsp:body>
</t:layout>