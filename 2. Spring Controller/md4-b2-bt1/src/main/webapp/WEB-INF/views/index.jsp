<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Sandwich Condiments</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
            background: #f5f5f5;
        }
        .container {
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            max-width: 500px;
            margin: 0 auto;
        }
        .condiment-item {
            margin: 10px 0;
            padding: 10px;
            background: #f9f9f9;
            border-radius: 5px;
        }
        .btn {
            background: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin-top: 15px;
        }
        .debug {
            background: #ffeb3b;
            padding: 10px;
            margin: 10px 0;
            border-radius: 5px;
            font-size: 14px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>🥪 ${title}</h1>

    <div class="debug">
        <strong>DEBUG:</strong><br>
        Condiments: ${condiments}<br>
        Size: ${condiments.size()}
    </div>

    <c:if test="${not empty condiments}">
        <form action="save" method="post">
            <h3>Select condiments:</h3>
            <c:forEach var="condiment" items="${condiments}">
                <div class="condiment-item">
                    <input type="checkbox" name="condiment" value="${condiment}" id="${condiment}">
                    <label for="${condiment}">${condiment}</label>
                </div>
            </c:forEach>
            <button type="submit" class="btn">Save</button>
        </form>
    </c:if>

    <c:if test="${empty condiments}">
        <div style="color: red; padding: 20px; text-align: center;">
            <h2>❌ ERROR: Controller not working!</h2>
            <p>Spring Controller is not providing data to the view.</p>
        </div>
    </c:if>
</div>
</body>
</html>