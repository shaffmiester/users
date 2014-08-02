<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>Sample Application</title>
  </head>
  <body>
  <c:set var="context" value="${pageContext.request.contextPath}" />
    <h1>Add A New User</h1>
        <form method="post" action="${context}/user/${user.id}">
            Name: <input type="text" name="name" value="${user.name}"><br>
            Email: <input type="text" name="email" value="${user.email}"><br>
            Descriptions: <input type="submit" value="Submit"><br>
               <c:forEach items="${descriptions}" var="description">
               <option value="${description}">${description}</option>
               </c:forEach>
        </form>
  </body>
</html>