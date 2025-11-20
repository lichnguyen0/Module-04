// Dữ liệu cấu hình mặc định (state)
let currentConfig = {
  language: 'English',
  pageSize: '25',
  spamFilter: false,
  signature: 'Thor\nKing, Asgard'
};

// Khởi tạo form với dữ liệu mặc định
function initializeForm() {
  // Set language
  document.querySelector(`input[name="language"][value="${currentConfig.language}"]`).checked = true;

  // Set page size
  document.getElementById('pageSize').value = currentConfig.pageSize;

  // Set spam filter
  document.getElementById('spamFilter').checked = currentConfig.spamFilter;

  // Set signature
  document.getElementById('signature').value = currentConfig.signature;

  // Update display
  updateCurrentConfigDisplay();
}

// Cập nhật hiển thị cấu hình hiện tại
function updateCurrentConfigDisplay() {
  document.getElementById('currentLanguage').textContent = currentConfig.language;
  document.getElementById('currentPageSize').textContent = `${currentConfig.pageSize} emails per page`;
  document.getElementById('currentSpamFilter').textContent = currentConfig.spamFilter ? 'Enabled' : 'Disabled';
  document.getElementById('currentSignature').textContent = currentConfig.signature;
}

// Xử lý sự kiện submit form
document.getElementById('emailConfigForm').addEventListener('submit', function(e) {
  e.preventDefault();

  // Cập nhật state với dữ liệu từ form
  currentConfig.language = document.querySelector('input[name="language"]:checked').value;
  currentConfig.pageSize = document.getElementById('pageSize').value;
  currentConfig.spamFilter = document.getElementById('spamFilter').checked;
  currentConfig.signature = document.getElementById('signature').value;

  // Cập nhật hiển thị
  updateCurrentConfigDisplay();

  alert('Configuration updated successfully!');
});

// Xử lý nút Cancel
document.getElementById('cancelBtn').addEventListener('click', function() {
  // Reset form về state hiện tại
  initializeForm();
});

// Khởi tạo khi trang load
document.addEventListener('DOMContentLoaded', initializeForm);
