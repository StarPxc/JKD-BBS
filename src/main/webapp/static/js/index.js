var pageIndex = 0;
var size = 5;


$(function () {

    /*提示用户功能未做*/
    $("#info").click(function () {
        swal({title:"不好意思最近实在有点忙，还没做这功能，sorry啦",text:"",imageUrl:"/imgs/infoSorry.png"})
    })
    /*微信公众号扫码关注*/
    $("#we-chat-pic").click(function () {
        swal({title:"扫码关注微信公众号",text: "", imageUrl: "/imgs/weChat.jpg"});
    })


    /**
     * 帖子总数
     * @type {*}
     */
    var postCount = $("#pageTool").attr("data");
    /**
     * 分页功能
     */

    $('#pageTool').Paging({
        pagesize: 5,
        count: postCount,
        current: $("#pagForm").attr("data"),
        callback: function (page, size, count) {
            $("#pageNum").val(page - 1)
            $("#size").val(size)
            $("#pagForm").submit();

        }

    });
    /**
     * 创建新的帖子
     */
    $("#create-new-post").click(function () {
        var userId = $("#userHeadPic").attr("user-id");
        if (userId) {
            window.location.href = "/post/createPostView";
        } else {
            swal("不能这样啊", "请先登录好吗", "warning")
        }


    })

    //验证码
    var a = Math.floor(Math.random() * 9);

    var b = Math.floor(Math.random() * 9);

    $("#captchaOperation").html(a + "+" + b + "=");

    /**
     * 注册
     */
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
                    swal("亲", "图片类型必须是.gif,jpeg,jpg,png中的一种哦", "warning")

                }
                else {
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

    /**
     * 登陆
     */
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
                        window.location.href = "/post";
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

    /* //自动完成
     $("#search-text").bind('input propertychange', function () {
         var searchTitle = $(this).val();
         $.ajax({
             type: "POST",
             url: "/post/searchTitle",
             data: {
                 "searchTitle": searchTitle
             },
             success: function (data) {
                 $(".auto-completed").empty();
                 var posts = JSON.parse(data);
                 for (var i = 0; i < posts.length; i++) {
                     var span = $("<span class='spanBlock'></span>");
                     span.text(posts[i].postTitle)
                     $(".auto-completed").append(span)
                 }




             }
         })
         $(".auto-completed").css({
             "width": $("#search-text").outerWidth(),
             "display": "block"
         })

     });*/

    /*$(document).on('click', $(".f > .spanBlock"),function () {

    });*/
    /* $("#search-button").click(function () {
         $("#search-form").submit();

     })*/


})