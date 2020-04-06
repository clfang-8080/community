/**
 * 提交回复
 */
function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();
    comment2target(questionId,1,content);
}

function comment2target(targetId,type,content) {
    if (!content){
        alert("未填写回复内容");
        return;
    }
    $.ajax({
        type: "POST",
        url: '/comment',
        contentType:'application/json',
        data: JSON.stringify({
            "parentId":targetId,
            "content":content,
            "type":type
        }),
        success: function (response) {
            if (response.code == 200){
                window.location.reload();//自动刷新页面，这样就能显示出回复了
                //$("#comment_section").hide();
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

function comment(e) {
    var commentId = e.getAttribute("data-id");
    var content = $("#input-"+commentId).val();
    comment2target(commentId,2,content);
}

/**
 * 展开二级评论
 */
function collapseComments(e) {
    var id = e.getAttribute("data-id");
    var comments = $("#comment-" + id);
    //回去二级评论的展开状态
    var collapse = e.getAttribute("data-collapse");
    if (collapse){//有值为true
        //折叠二级评论
        comments.removeClass("in");
        e.removeAttribute("data-collapse");
        e.classList.remove("active");
    }else{
        var subCommentContainer = $("#comment-"+id);
        if (subCommentContainer.children().length != 1){
            //展开二级评论
            comments.addClass("in");
            //标记二级评论状态
            e.setAttribute("data-collapse","in");
            e.classList.add("active");
        }else{
            $.getJSON( "/comment/"+id, function( data ) {
                $.each(data.data.reverse(), function(index , comment) {
                    var mediaLeftElemeng = $("<div/>",{
                        "class":"media-left"
                    }).append($("<img/>",{
                        "class":"media-object img-rounded",
                        "src":comment.user.avatarUrl
                    }));
                    var mediaBodyElement = $("<div/>",{
                        "class":"media-body"
                    }).append($("<h5/>",{
                        "class":"media-heading",
                        "html":comment.user.name
                    })).append($("<div/>",{
                        "html":comment.content
                    })).append($("<div/>",{
                        "class":"menu"
                    }).append($("<span/>",{
                        "class":"pull-right",
                        "html":moment(comment.gmtCreate).format('YYYY-MM-DD')
                    })));
                    var mediaElement = $("<div/>",{
                        "class":"media"
                    }).append(mediaLeftElemeng).append(mediaBodyElement);

                    var commentElement =$("<div/>",{
                        "class":"col-lg-12 col-md-12 col-sx-12 comments",
                    }).append(mediaElement);
                    subCommentContainer.prepend(commentElement);
                });
                //展开二级评论
                comments.addClass("in");
                //标记二级评论状态
                e.setAttribute("data-collapse","in");
                e.classList.add("active");
            });
        }

    }
}

//展开标签选择页
function showSelectTag() {
    $("#select-tag").show();
}

function selectTag(e) {
    var value = e.getAttribute("data-tag")
    var previous = $("#tag").val();
    if (previous.indexOf(value) == -1){//输入框中没有这个值
        if (previous){//有tag
            $("#tag").val(previous + ',' + value);
        }else {
            $("#tag").val(value);
        }
    }

}