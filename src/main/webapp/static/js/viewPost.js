/**
 * Created by PXC on 2017/6/27.
 */
$(function () {
    /*提示用户功能未做*/
    $("#info").click(function () {
        swal({title:"不好意思最近实在有点忙，还没做这功能，sorry啦",text:"",imageUrl:"/imgs/infoSorry.png"})
    })
    /*微信公众号扫码关注*/
    $("#we-chat-pic").click(function () {
        swal({title:"扫码关注微信公众号",text: "", imageUrl: "/imgs/weChat.jpg"});
    })
    //用户登录操作
    $("#login_sure").click(function () {
        var username = $("#username").val();
        var password = $("#password").val();

        if (username && password) {
            $("#null_err").css("display", "bone");
            $.ajax({
                url: "/user/login",
                type: "POST",
                data: {
                    "username": username,
                    "password": password
                },
                success: function (result) {
                    if (result == "login_success") {
                        $("#login_err").css("display", "none")
                        window.location.reload();
                    } else {
                        $("#null_err").css("display", "none")
                        $("#login_err").css("display", "block")
                    }
                }
            })
        } else {
            $("#login_err").css("display", "none")
            $("#null_err").css("display", "block")
        }


    })
    //验证码
    var a = Math.floor(Math.random() * 9);

    var b = Math.floor(Math.random() * 9);

    $("#captchaOperation").html(a + "+" + b + "=");
    //用户注册操作
    $("#regiser_sure").click(function () {
        if ($("#captchaOperationInput").val() == (a + b)) {
            $("#captchaOperationErr").css("display", "none");
            var reg_username = $("#reg_username").val();
            var reg_password = $("#reg_password").val();
            var email = $("#email").val();
            var selfIntroduction = $("#selfIntroduction").val();
            if (username && password && email && selfIntroduction) {
                $("#register-null").css("display", "none");
                var form = new FormData(document.getElementById("form"));
                if (!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test($("#file").val())) {
                    swal({title:"别这样亲!",text:"图片类型必须是.gif,jpeg,jpg,png中的一种",type:"warning"})

                } else {


                    $.ajax({
                        type: "POST",
                        url: "/user/register",
                        data: form,
                        cache: false,
                        processData: false,
                        contentType: false,
                        success: function (data) {
                            window.location.href = "/post";
                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                            alert(XMLHttpRequest.status);
                            alert(XMLHttpRequest.readyState);
                            alert(textStatus);
                        }
                    })
                }

            } else {
                $("#register-null").css("display", "block");
            }


        } else {
            $("#captchaOperationErr").css("display", "block");
        }

    })
    //提交评论
    $("#reply-sure").click(function () {
        var userId = $("#userHeadPic").attr("user-id");
        if (userId) {
            var replyContent = $("#reply-content").text();
            var url = window.location.href;
            var strs = new Array();
            strs = url.split("/");
            var postID = strs.pop();
            if (replyContent) {
                $.ajax({
                    type: "POST",
                    url: "/reply/create",
                    data: {
                        "postID": postID,
                        "replyContent": replyContent
                    },
                    success: function (data) {
                        if (data == "success") {
                            swal({title:"Ok！!",text:"评论成功",type:"success"})

                            window.location.reload();
                        } else {
                            swal({title:"出错了!",text:"评论失败",type:"error"})
                        }

                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        alert(XMLHttpRequest.status);
                        alert(XMLHttpRequest.readyState);
                        alert(textStatus);
                    }
                });
            } else {
                swal({title:"别神马都不讲啊!",text:"一定要输入内容哦",type:"warning"})
            }
        } else {
            swal({title:"别猴急啊亲!",text:"要先登录哟",type:"warning"})
        }

    })

    /*删除*/
    $("#delete").click(function () {


        swal(
            {
                title: "您确定要删除这个帖子吗",
                text: "删除后将无法恢复，请谨慎操作！",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "是的，我要删除！",
                cancelButtonText: "让我再考虑一下…",
                closeOnConfirm: false,
                closeOnCancel: false
            },
            function (isConfirm) {
                if (isConfirm) {
                    var this_id = $("#delete").attr("data");
                    $.ajax({
                        url:"/post/delete",
                        data:{
                            "id":this_id
                        },
                        success:function () {

                            swal({
                                    title: "删除成功！",
                                    text: "您已经永久删除了这条信息。",
                                    type: "success"
                                },
                                function () {
                                    window.location = "/post";
                                })
                        }
                    })

                }
                else {
                    swal({
                        title: "已取消",
                        text: "您取消了删除操作！",
                        type: "error"
                    })
                }
            }
        )
    })

})