<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Từ điển Anh - Việt</title>
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

        h1 {
            color: #333;
            margin-bottom: 10px;
            font-size: 2.5em;
        }

        .subtitle {
            color: #666;
            margin-bottom: 30px;
            font-size: 1.1em;
        }

        .dictionary-icon {
            font-size: 3em;
            margin-bottom: 20px;
            color: #667eea;
        }

        .form-group {
            margin-bottom: 25px;
            text-align: left;
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-weight: 600;
            color: #555;
            font-size: 1.1em;
        }

        input[type="text"] {
            width: 100%;
            padding: 15px;
            border: 2px solid #ddd;
            border-radius: 10px;
            font-size: 16px;
            transition: all 0.3s ease;
        }

        input[type="text"]:focus {
            outline: none;
            border-color: #667eea;
            box-shadow: 0 0 0 3px rgba(102, 126, 234, 0.1);
        }

        .btn-search {
            width: 100%;
            padding: 15px;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            border: none;
            border-radius: 10px;
            font-size: 18px;
            font-weight: 600;
            cursor: pointer;
            transition: transform 0.2s;
        }

        .btn-search:hover {
            transform: translateY(-2px);
        }

        .word-list {
            margin-top: 30px;
            padding: 20px;
            background: #f8f9fa;
            border-radius: 10px;
            text-align: left;
        }

        .word-list h3 {
            color: #333;
            margin-bottom: 15px;
        }

        .word-list ul {
            columns: 2;
            list-style-type: none;
        }

        .word-list li {
            padding: 5px 0;
            color: #666;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="dictionary-icon">📚</div>
    <h1>Từ Điển Anh - Việt</h1>
    <p class="subtitle">Tra cứu từ tiếng Anh sang tiếng Việt</p>

    <form action="search" method="post">
        <div class="form-group">
            <label for="englishWord">Nhập từ tiếng Anh:</label>
            <input type="text" id="englishWord" name="englishWord"
                   placeholder="Ví dụ: hello, computer, book..." required>
        </div>
        <button type="submit" class="btn-search">🔍 Tra cứu</button>
    </form>

    <div class="word-list">
        <h3>Một số từ có sẵn:</h3>
        <ul>
            <li>hello</li>
            <li>computer</li>
            <li>book</li>
            <li>water</li>
            <li>sun</li>
            <li>moon</li>
            <li>house</li>
            <li>school</li>
            <li>student</li>
            <li>teacher</li>
            <li>love</li>
            <li>friend</li>
        </ul>
    </div>
</div>
</body>
</html>