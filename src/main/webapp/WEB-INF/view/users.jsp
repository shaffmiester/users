<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>Sample Application</title>
  </head>
  <body>
    <h1>Users Go Here</h1>
    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
    <table>
        <tr>
          <th>Name</th>
          <th>Email</th>
          <th>Descriptions</th>
          <th>Id</th>
          <th></th>
          <th></th>
        </tr>
        <c:forEach items="${users}" var="user">
          <tr>
            <td>${user.name}</td>
            <td>${user.email}</td>
            <td>${user.descriptions}</td>
            <td>${user.id}</td>
            <td><a href="${contextPath}/editUser/${user.id}">Edit</a></td>
            <td><a href="${contextPath}/deleteUser/${user.id}">Delete</a></td>
          </tr>
        </c:forEach>
    </table>

    <h2>Filter List Based On Attributes</h2>
    <a href="${contextPath}/addUser">Add User</a><br>
<c:set var="context" value="${pageContext.request.contextPath}" />
    <form method="get" action="${context}/user">
       Description:
       <select multiple name="descriptions" selected="${user.descriptions}">
         <c:forEach items="${descriptions}" var="description">
           <option value="${description}">${description}</option>
         </c:forEach>
       </select><br>
       <input type="submit" value="Submit">
    </form>

  </body>
</html>