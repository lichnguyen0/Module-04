<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Kết quả tra cứu</title>
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

        .container {
            background: white;
            border-radius: 15px;
            padding: 40px;
            box-shadow: 0 20px 40px rgba(0, 0, 0, 0.1);
            max-width: 500px;
            width: 100%;
            text-align: center;
        }

        .result-icon {
            font-size: 4em;
            margin-bottom: 20px;
        }

        .found {
            color: #28a745;
        }

        .not-found {
            color: #dc3545;
        }

        h1 {
            color: #333;
            margin-bottom: 20px;
        }

        .word-display {
            background: #f8f9fa;
            padding: 20px;
            border-radius: 10px;
            margin: 20px 0;
            border-left: 4px solid #667eea;
        }

        .english-word {
            font-size: 1.5em;
            color: #333;
            margin-bottom: 10px;
        }

        .vietnamese-meaning {
            font-size: 2em;
            color: #28a745;
            font-weight: bold;
        }

        .not-found-message {
            font-size: 1.2em;
            color: #dc3545;
            margin: 20px 0;
        }

        .btn-back {
            display: inline-block;
            padding: 12px 30px;
            background: #667eea;
            color: white;
            text-decoration: none;
            border-radius: 10px;
            font-weight: 600;
            transition: transform 0.2s;
            margin-top: 20px;
        }

        .btn-back:hover {
            transform: translateY(-2px);
            background: #5a6fd8;
        }
    </style>
</head>
<body>
<div class="container">
    <c:choose>
        <c:when test="${wordFound}">
            <div class="result-icon found">✅</div>
            <h1>Kết quả tra cứu</h1>
            <div class="word-display">
                <div class="english-word">${englishWord}</div>
                <div class="vietnamese-meaning">${vietnameseMeaning}</div>
            </div>
        </c:when>
        <c:otherwise>
            <div class="result-icon not-found">❌</div>
            <h1>Không tìm thấy từ</h1>
            <div class="not-found-message">
                Từ "<strong>${englishWord}</strong>" không có trong từ điển
            </div>
        </c:otherwise>
    </c:choose>

    <a href="/" class="btn-back">🔙 Tra cứu từ khác</a>
</div>
</body>
</html>