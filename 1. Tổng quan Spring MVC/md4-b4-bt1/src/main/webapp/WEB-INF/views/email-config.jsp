<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
  <title>Email Configuration</title>
  <style>
    * {
      margin: 0;
      padding: 0;
      box-sizing: border-box;
      font-family: Arial, sans-serif;
    }

    body {
      background-color: #f0f2f5;
      padding: 20px;
    }

    .container {
      max-width: 600px;
      margin: 0 auto;
      background: white;
      border-radius: 8px;
      box-shadow: 0 2px 10px rgba(0,0,0,0.1);
      padding: 20px;
    }

    h1 {
      color: #333;
      margin-bottom: 20px;
      font-size: 24px;
    }

    h2 {
      color: #555;
      margin: 25px 0 15px 0;
      font-size: 18px;
      border-bottom: 1px solid #eee;
      padding-bottom: 8px;
    }

    .form-group {
      margin-bottom: 20px;
    }

    .language-options {
      margin: 10px 0;
    }

    .language-options label {
      display: block;
      margin: 8px 0;
      cursor: pointer;
    }

    .page-size {
      margin: 15px 0;
    }

    .page-size select {
      padding: 8px 12px;
      border: 1px solid #ddd;
      border-radius: 4px;
      width: 200px;
    }

    .spam-filter {
      margin: 15px 0;
    }

    .spam-filter label {
      display: flex;
      align-items: center;
      cursor: pointer;
    }

    .signature textarea {
      width: 100%;
      height: 80px;
      padding: 10px;
      border: 1px solid #ddd;
      border-radius: 4px;
      margin-top: 8px;
      resize: vertical;
    }

    .button-group {
      margin-top: 25px;
      display: flex;
      gap: 10px;
    }

    button {
      padding: 10px 20px;
      border: none;
      border-radius: 4px;
      cursor: pointer;
      font-size: 14px;
    }

    .update-btn {
      background: #007bff;
      color: white;
    }

    .update-btn:hover {
      background: #0056b3;
    }

    .cancel-btn {
      background: #6c757d;
      color: white;
    }

    .cancel-btn:hover {
      background: #545b62;
    }

    .current-config {
      margin-top: 30px;
      padding: 15px;
      background: #f8f9fa;
      border-radius: 6px;
      border-left: 4px solid #007bff;
    }

    .current-config h3 {
      color: #333;
      margin-bottom: 10px;
    }

    .config-item {
      margin: 5px 0;
    }

    .success-message {
      background: #d4edda;
      color: #155724;
      padding: 12px;
      border-radius: 4px;
      margin-bottom: 20px;
      text-align: center;
    }
  </style>
</head>
<body>
<div class="container">
  <h1>Settings</h1>

  <c:if test="${param.success != null}">
    <div class="success-message">
      Configuration updated successfully!
    </div>
  </c:if>

  <form:form method="post" action="/config" modelAttribute="emailConfig">
    <h2>Languages</h2>
    <div class="form-group">
      <div class="language-options">
        <label>
          <form:radiobutton path="language" value="English"/> English
        </label>
        <label>
          <form:radiobutton path="language" value="Vietnamese"/> Vietnamese
        </label>
        <label>
          <form:radiobutton path="language" value="Japanese"/> Japanese
        </label>
        <label>
          <form:radiobutton path="language" value="Chinese"/> Chinese
        </label>
      </div>
    </div>

    <h2>Page Size</h2>
    <div class="form-group">
      <div class="page-size">
        Show
        <form:select path="pageSize">
          <form:option value="5">5 emails per page</form:option>
          <form:option value="10">10 emails per page</form:option>
          <form:option value="15">15 emails per page</form:option>
          <form:option value="25">25 emails per page</form:option>
          <form:option value="50">50 emails per page</form:option>
          <form:option value="100">100 emails per page</form:option>
        </form:select>
      </div>
    </div>

    <h2>Spam filter:</h2>
    <div class="form-group">
      <div class="spam-filter">
        <label>
          <form:checkbox path="spamFilter"/> Enable spam filter
        </label>
      </div>
    </div>

    <h2>Signature:</h2>
    <div class="form-group">
      <div class="signature">
        <form:textarea path="signature" placeholder="Enter your signature"/>
      </div>
    </div>

    <div class="button-group">
      <button type="button" class="cancel-btn" onclick="resetForm()">Cancel</button>
      <button type="submit" class="update-btn">Update</button>
    </div>
  </form:form>

  <div class="current-config">
    <h3>Current Configuration</h3>
    <div class="config-item"><strong>Language:</strong> ${emailConfig.language}</div>
    <div class="config-item"><strong>Page Size:</strong> ${emailConfig.pageSize} emails per page</div>
    <div class="config-item"><strong>Spam Filter:</strong> ${emailConfig.spamFilter ? 'Enabled' : 'Disabled'}</div>
    <div class="config-item"><strong>Signature:</strong> <pre>${emailConfig.signature}</pre></div>
  </div>
</div>

<script>
  function resetForm() {
    document.querySelector('form').reset();
  }

  // Hiển thị thông báo success khi redirect từ POST
  if(window.location.search.includes('success=true')) {
    const successMsg = document.createElement('div');
    successMsg.className = 'success-message';
    successMsg.textContent = 'Configuration updated successfully!';
    document.querySelector('.container').insertBefore(successMsg, document.querySelector('form'));

    // Remove after 3 seconds
    setTimeout(() => {
      successMsg.remove();
    }, 3000);
  }
</script>
</body>
</html>