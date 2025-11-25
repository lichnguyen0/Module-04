<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Result</title>
  <style>
    body { font-family: Arial; margin: 40px; }
    .result { background: #e8f5e8; padding: 20px; margin: 20px 0; }
    .condiment { background: white; padding: 10px; margin: 5px 0; }
  </style>
</head>
<body>
<h1>Result</h1>

<div class="result">
  <h3>${message}</h3>
  <c:forEach var="condiment" items="${selectedCondiments}">
    <div class="condiment">✅ ${condiment}</div>
  </c:forEach>
</div>

<a href="/">← Back</a>
</body>
</html>