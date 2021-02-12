$(function() {
  validateSignUp();
})

function validateSignUp() {
  $("#register").click(function(event) {
    event.preventDefault();

    var name = $("#name").val();
    var email = $("#email").val();
    var password = $("#password").val();
    var cpassword = $("#cpassword").val();

    var emailRule = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
    var passwdRule = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,20}$/;
    if (name === '' || email === '' || password === '' || cpassword === '') {
      alert("회원가입 정보를 모두 입력해주세요");
    } else if (!(password).match(cpassword)) {
      alert("패스워드가 일치하지 않습니다.");
    } else if (!emailRule.test(email)) {
      alert("이메일 형식이 올바르지 않습니다.");
    } else if (!passwdRule.test(password)) {
      alert("비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.");
    } else {
      $.ajax({
        url: "http://localhost:8090/api/signup",
        data: JSON.stringify({
          "email": email,
          "name": name,
          "password": password
        }),
        contentType: "application/json",
        type: "post",
        success: function() {
          location.href = "./signup-success.html";
        },
        error: function(){
          alert("회원가입실패했습니다. 입력하신 정보를 다시 확인해주세요.");
        }
      });
    }
  });
}
