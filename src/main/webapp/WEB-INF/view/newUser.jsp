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
        <form method="post" action="${context}/user">
            Name: <input type="text" name="name"><br>
            Email: <input type="text" name="email"><br>
            Description:
              <select multiple name="descriptions">
              <c:forEach items="${descriptions}" var="description">
                 <option value="${description}">${description}</option>
              </c:forEach>
              </select>
        <input type="submit" value="Submit">
        </form>
  </body>
</html>