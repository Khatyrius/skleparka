<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="pl.skleparka.beans.User"%>

    <%
    	if (session.getAttribute("users") == null){
    %>
	<%@include file="publicHeader.jsp"%>
	<%
		}else if (((User)session.getAttribute("users")).getType().equals("customer")){
	%> 
	<%@include file="customerHeader.jsp"%>
	<%
		}else if (((User)session.getAttribute("users")).getType().equals("admin")){
	%>
	<%@include file="adminHeader.jsp" %>
	<%} %>
   