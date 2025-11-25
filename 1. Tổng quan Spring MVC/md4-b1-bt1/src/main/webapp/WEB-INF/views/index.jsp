<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Currency Converter</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 50px; background: #f4f4f4; }
        .container { background: white; padding: 30px; border-radius: 10px; box-shadow: 0 0 20px rgba(0,0,0,0.1); max-width: 400px; margin: 0 auto; }
        h1 { text-align: center; color: #333; margin-bottom: 30px; }
        .form-group { margin-bottom: 20px; }
        label { display: block; margin-bottom: 8px; font-weight: bold; color: #555; }
        input { width: 100%; padding: 12px; border: 2px solid #ddd; border-radius: 5px; font-size: 16px; }
        input:focus { outline: none; border-color: #007bff; }
        button { width: 100%; padding: 12px; background: #007bff; color: white; border: none; border-radius: 5px; font-size: 16px; cursor: pointer; }
        button:hover { background: #0056b3; }
    </style>
</head>
<body>
<div class="container">
    <h1>💰 Chuyển đổi USD sang VNĐ</h1>
    <form action="convert" method="post">
        <div class="form-group">
            <label for="rate">Tỉ giá (USD to VNĐ):</label>
            <input type="number" id="rate" name="rate" value="23000" step="0.01" required>
        </div>
        <div class="form-group">
            <label for="usd">Số tiền USD:</label>
            <input type="number" id="usd" name="usd" step="0.01" placeholder="Nhập số USD" required>
        </div>
        <button type="submit">Chuyển đổi</button>
    </form>
</div>
</body>
</html>