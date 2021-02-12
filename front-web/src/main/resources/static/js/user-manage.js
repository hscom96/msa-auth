$(function() {
  getUsersInfo(20, 0, "name", "DESC");
})


function getUsersInfo(size, page, column, direction) {
  var accessToken = localStorage.getItem('accessToken');

  $.ajax({
    url: "http://localhost:8090/api/user?page=" + page + "&size=" + size + "&sortDirection=" + direction + "&sortColumn=" + column,
    type: "GET",
    dataType: 'json',
    headers: {"Authorization": 'Bearer ' + accessToken},
    success: function(result) {
      addUsersInfoAll(result);
    }
  });
}

function addUsersInfoAll(userInfo) {
  var source = $("#user-template").html();
  var template = Handlebars.compile(source);
  var resultHTML = "";

  for (var i = 0; i < userInfo.content.length; i++) {
    resultHTML += source.replace("{name}", userInfo.content[i].name)
      .replace("{email}", userInfo.content[i].email)
      .replace("{createDt}", userInfo.content[i].createDt)
  }
  $("#userInfoTable").append(resultHTML);
}
