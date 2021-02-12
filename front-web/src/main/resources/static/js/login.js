$(function() {
  login();
})

function login() {
  $("#signin").click(function(event) {
    event.preventDefault();
    var email = $("#inputEmail").val();
    var password = $("#inputPassword").val();

    $.ajax({
      url: "http://localhost:8090/api/login",
      data: JSON.stringify({
        "email": email,
        "password": password
      }),
      contentType: "application/json",
      type: "post",
      dataType: "json",
      success: function(data) {
        localStorage.setItem('accessToken', data.access_token);
        localStorage.setItem('refreshToken', data.refresh_token);
        location.href = "./main.html";
      },
      error: function() {
        alert("로그인에 실패했습니다. 입력하신 정보를 다시 확인해주세요.");
      }
    });
  });
}
