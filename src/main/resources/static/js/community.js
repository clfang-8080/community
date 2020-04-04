function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();
    $.ajax({
        type: "POST",
        url: '/comment',
        contentType:'application/json',
        data: JSON.stringify({
            "parentId":questionId,
            "content":content,
            "type":1
        }),
        success: function (response) {
            if (response.code == 200){
                $("#comment_section").hide();
            }else {
                if (response.code == 2003){//判断是未登录状态
                    var isAccepted = confirm(response.message);
                    if (isAccepted){//isAccepted返回的是true
                        window.open("https://github.com/login/oauth/authorize?client_id=Iv1.19c33fd30ca3617d&redirect_uri=http://localhost:8887/callback&scope=user&state=1");
                        window.localStorage.setItem("closable",true);
                    }
                }else {
                    alert(response.message);
                }
            }
            console.log(response);
        },
        dataType: 'json'
    });
}