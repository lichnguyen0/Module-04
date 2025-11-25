<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Máy Tính Cá Nhân</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 20px;
        }

        .calculator-container {
            background: white;
            border-radius: 20px;
            padding: 40px;
            box-shadow: 0 25px 50px rgba(0, 0, 0, 0.2);
            max-width: 500px;
            width: 100%;
        }

        .header {
            text-align: center;
            margin-bottom: 30px;
        }

        .calculator-icon {
            font-size: 3em;
            margin-bottom: 15px;
        }

        h1 {
            color: #333;
            margin-bottom: 10px;
            font-size: 2.2em;
        }

        .subtitle {
            color: #666;
            font-size: 1.1em;
        }

        .calculator-form {
            margin-bottom: 25px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-weight: 600;
            color: #555;
            font-size: 1.1em;
        }

        input[type="number"] {
            width: 100%;
            padding: 15px;
            border: 2px solid #ddd;
            border-radius: 10px;
            font-size: 16px;
            transition: all 0.3s ease;
        }

        input[type="number"]:focus {
            outline: none;
            border-color: #667eea;
            box-shadow: 0 0 0 3px rgba(102, 126, 234, 0.1);
        }

        .operator-buttons {
            display: grid;
            grid-template-columns: repeat(4, 1fr);
            gap: 10px;
            margin: 25px 0;
        }

        .operator-btn {
            padding: 15px;
            border: 2px solid #667eea;
            background: white;
            color: #667eea;
            border-radius: 10px;
            font-size: 1.2em;
            font-weight: bold;
            cursor: pointer;
            transition: all 0.3s ease;
        }

        .operator-btn:hover {
            background: #667eea;
            color: white;
            transform: translateY(-2px);
        }

        input[type="radio"] {
            display: none;
        }

        input[type="radio"]:checked + .operator-btn {
            background: #667eea;
            color: white;
            box-shadow: 0 5px 15px rgba(102, 126, 234, 0.4);
        }

        .btn-calculate {
            width: 100%;
            padding: 18px;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            border: none;
            border-radius: 10px;
            font-size: 1.3em;
            font-weight: bold;
            cursor: pointer;
            transition: transform 0.2s;
            margin-top: 10px;
        }

        .btn-calculate:hover {
            transform: translateY(-3px);
            box-shadow: 0 10px 25px rgba(102, 126, 234, 0.4);
        }

        .result-section {
            margin-top: 30px;
            padding: 25px;
            background: #f8f9fa;
            border-radius: 15px;
            border-left: 4px solid #28a745;
        }

        .result-title {
            color: #333;
            margin-bottom: 15px;
            font-size: 1.3em;
            font-weight: bold;
        }

        .calculation {
            font-size: 1.4em;
            color: #28a745;
            font-weight: bold;
            text-align: center;
            margin: 10px 0;
        }

        .error-message {
            background: #ffebee;
            color: #d32f2f;
            padding: 15px;
            border-radius: 10px;
            margin: 15px 0;
            border-left: 4px solid #f44336;
            font-weight: bold;
        }

        .hidden {
            display: none;
        }
    </style>
</head>
<body>
<div class="calculator-container">
    <div class="header">
        <div class="calculator-icon">🧮</div>
        <h1>Máy Tính Cá Nhân</h1>
        <p class="subtitle">Thực hiện các phép tính cơ bản</p>
    </div>

    <form action="calculate" method="post" class="calculator-form">
        <div class="form-group">
            <label for="firstOperand">Số thứ nhất:</label>
            <input type="number" id="firstOperand" name="firstOperand"
                   step="any" placeholder="Nhập số thứ nhất"
                   value="${firstOperand}" required>
        </div>

        <div class="form-group">
            <label>Phép tính:</label>
            <div class="operator-buttons">
                <input type="radio" id="add" name="operator" value="add"
                ${operator == 'add' ? 'checked' : ''} required>
                <label for="add" class="operator-btn">+</label>

                <input type="radio" id="subtract" name="operator" value="subtract"
                ${operator == 'subtract' ? 'checked' : ''}>
                <label for="subtract" class="operator-btn">−</label>

                <input type="radio" id="multiply" name="operator" value="multiply"
                ${operator == 'multiply' ? 'checked' : ''}>
                <label for="multiply" class="operator-btn">×</label>

                <input type="radio" id="divide" name="operator" value="divide"
                ${operator == 'divide' ? 'checked' : ''}>
                <label for="divide" class="operator-btn">÷</label>
            </div>
        </div>

        <div class="form-group">
            <label for="secondOperand">Số thứ hai:</label>
            <input type="number" id="secondOperand" name="secondOperand"
                   step="any" placeholder="Nhập số thứ hai"
                   value="${secondOperand}" required>
        </div>

        <button type="submit" class="btn-calculate">Tính Toán</button>
    </form>

    <c:if test="${not empty error}">
        <div class="error-message">
            ❌ ${error}
        </div>
    </c:if>

    <c:if test="${not empty result}">
        <div class="result-section">
            <div class="result-title">📊 Kết quả:</div>
            <div class="calculation">
                    ${firstOperand}
                <c:choose>
                    <c:when test="${operator == 'add'}">+</c:when>
                    <c:when test="${operator == 'subtract'}">−</c:when>
                    <c:when test="${operator == 'multiply'}">×</c:when>
                    <c:when test="${operator == 'divide'}">÷</c:when>
                </c:choose>
                    ${secondOperand} = ${result}
            </div>
            <div style="text-align: center; color: #666; margin-top: 10px;">
                Phép ${operation}
            </div>
        </div>
    </c:if>
</div>
</body>
</html>