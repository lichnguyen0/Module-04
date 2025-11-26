<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>NASA APOD Feedback</title>
  <style>
    body { font-family: Arial, sans-serif; margin: 40px; }
    .feedback-form { margin-bottom: 30px; padding: 20px; border: 1px solid #ddd; }
    .feedback-item { margin: 15px 0; padding: 15px; border: 1px solid #eee; }
    .like-btn { background: #007bff; color: white; border: none; padding: 5px 10px; cursor: pointer; }
    .rating { color: #ffc107; font-weight: bold; }
    #img-of-the-day { max-width: 800px; display: block; margin: 20px 0; border: 1px solid #ccc; }
  </style>
</head>
<body>
<h1>NASA Astronomy Picture of the Day</h1>

<img id="img-of-the-day" alt="NASA Astronomy Picture of the Day">

<div class="feedback-form">
  <h3>Leave Feedback</h3>
  <form id="feedbackForm">
    <div>
      <label>Author Name:</label>
      <input type="text" name="authorName" required>
    </div>
    <div>
      <label>Comment:</label><br>
      <textarea name="comment" rows="4" cols="50" required></textarea>
    </div>
    <div>
      <label>Rating:</label>
      <select name="rating" required>
        <option value="">Select rating</option>
        <option value="1">1 - Poor</option>
        <option value="2">2 - Fair</option>
        <option value="3">3 - Good</option>
        <option value="4">4 - Very Good</option>
        <option value="5">5 - Excellent</option>
      </select>
    </div>
    <button type="submit">Submit Feedback</button>
  </form>
</div>

<div id="feedbacks-container">
  <h3>Today's Feedbacks</h3>
  <div id="feedbacks-list"></div>
</div>

<script>
  // Load NASA APOD image - VIA PROXY to avoid CORS
  function loadNASAImage() {
    fetch('nasa-proxy')
            .then(response => {
              if (!response.ok) throw new Error('HTTP ' + response.status);
              return response.json();
            })
            .then(data => {
              console.log('✅ NASA Data received:', data);

              if (data.media_type === 'image') {
                document.getElementById('img-of-the-day').src = data.url;
                console.log('🎯 Image loaded:', data.url);
              } else if (data.media_type === 'video') {
                console.log('❌ Today is video:', data.url);
                document.getElementById('img-of-the-day').alt = 'Video: ' + data.title;
              } else if (data.error) {
                console.error('❌ NASA API Error:', data.error);
              }
            })
            .catch(error => {
              console.error('❌ Failed to load NASA data:', error);
              // Fallback: thử gọi trực tiếp (có thể vẫn bị CORS)
              loadNASAImageDirect();
            });
  }

  // Fallback: gọi trực tiếp NASA API (có thể vẫn bị CORS)
  function loadNASAImageDirect() {
    const apiKey = 'r4o7dnrcJqC9SQAWv17IV7eYGqoxfchZ7kjx7ynk';
    const query = `https://api.nasa.gov/planetary/apod?api_key=${apiKey}`;

    console.log('🔄 Trying direct NASA API...');

    let request = new XMLHttpRequest();
    request.open('GET', query);
    request.onload = function() {
      if (request.status === 200) {
        let response = JSON.parse(request.responseText);
        if (response.media_type === 'image') {
          document.getElementById('img-of-the-day').src = response.url;
          console.log('✅ Direct load successful');
        }
      }
    };
    request.send();
  }

  // Load feedbacks
  function loadFeedbacks() {
    fetch('feedback')
            .then(response => {
              if (!response.ok) throw new Error('HTTP ' + response.status);
              return response.json();
            })
            .then(feedbacks => {
              console.log('📝 Loaded feedbacks:', feedbacks.length);
              const container = document.getElementById('feedbacks-list');
              container.innerHTML = '';

              feedbacks.forEach(feedback => {
                const feedbackDiv = document.createElement('div');
                feedbackDiv.className = 'feedback-item';
                feedbackDiv.innerHTML = `
                        <strong>${feedback.authorName}</strong>
                        <span class="rating">Rating: ${feedback.rating}/5</span>
                        <p>${feedback.comment}</p>
                        <button class="like-btn" onclick="likeFeedback(${feedback.id})">
                            Like (${feedback.likeCount})
                        </button>
                    `;
                container.appendChild(feedbackDiv);
              });
            })
            .catch(error => console.error('❌ Error loading feedbacks:', error));
  }

  // Submit feedback
  document.getElementById('feedbackForm').addEventListener('submit', function(e) {
    e.preventDefault();
    const formData = new FormData(this);

    fetch('feedback', {
      method: 'POST',
      body: new URLSearchParams(formData)
    }).then(response => {
      if (response.ok) {
        console.log('✅ Feedback submitted successfully');
        this.reset();
        loadFeedbacks();
      }
    }).catch(error => console.error('❌ Error submitting feedback:', error));
  });

  // Like feedback
  function likeFeedback(feedbackId) {
    fetch('like?feedbackId=' + feedbackId, {
      method: 'POST'
    }).then(response => {
      if (response.ok) {
        console.log('✅ Liked feedback:', feedbackId);
        loadFeedbacks();
      }
    }).catch(error => console.error('❌ Error liking feedback:', error));
  }

  // Initial load
  loadNASAImage();
  loadFeedbacks();
</script>
</body>
</html>