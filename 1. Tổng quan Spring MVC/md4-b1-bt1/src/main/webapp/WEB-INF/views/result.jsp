<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Kết quả</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 50px; background: #f4f4f4; }
        .container { background: white; padding: 30px; border-radius: 10px; box-shadow: 0 0 20px rgba(0,0,0,0.1); max-width: 400px; margin: 0 auto; }
        h1 { text-align: center; color: #333; margin-bottom: 30px; }
        .result { background: #e9ecef; padding: 20px; border-radius: 5px; margin: 20px 0; }
        .result-item { display: flex; justify-content: space-between; margin-bottom: 10px; padding-bottom: 10px; border-bottom: 1px solid #ccc; }
        .result-item:last-child { border-bottom: none; margin-bottom: 0; }
        .highlight { font-weight: bold; color: #28a745; font-size: 18px; }
        .back-btn { display: block; text-align: center; padding: 12px; background: #28a745; color: white; text-decoration: none; border-radius: 5px; }
        .back-btn:hover { background: #218838; }
    </style>
</head>
<body>
<div class="container">
    <h1>💰 Kết quả chuyển đổi</h1>
    <div class="result">
        <div class="result-item">
            <span>Tỉ giá:</span>
            <span>1 USD = ${rate} VNĐ</span>
        </div>
        <div class="result-item">
            <span>Số tiền USD:</span>
            <span>${usd} USD</span>
        </div>
        <div class="result-item highlight">
            <span>Số tiền VNĐ:</span>
            <span>${vnd} VNĐ</span>
        </div>
    </div>
    <a href="/" class="back-btn">Quay lại</a>
</div>
</body>
</html>