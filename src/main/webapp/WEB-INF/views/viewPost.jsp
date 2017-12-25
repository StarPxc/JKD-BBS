<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>详情</title>
    <script src="/static/js/jquery-3.1.1.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="/static/bootstrap-3.3.7-dist/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
    <script src="/static/js/sweetalert.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="/static/js/viewPost.js" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" type="text/css" href="/static/bootstrap-3.3.7-dist/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="/static/css/viewPost.css" />
    <link rel="stylesheet" type="text/css" href="/static/css/sweetalert.css" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>

<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#example-navbar-collapse">

                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <%--  <a class="navbar-brand top-link" href="#">logo</a>
            <a class="navbar-brand top-link" href="#">网站首页</a>--%>
            <a class="navbar-brand top-link" id="we-chat-pic">果核里的图灵</a>
            <a class="navbar-brand top-link" href="/post">论坛首页</a>
        </div>
        <div class="collapse navbar-collapse" id="example-navbar-collapse">
            <ul class="nav navbar-nav navbar-right lr">



                <c:choose>

                    <c:when test="${empty user}">
                        <li><button class="btn btn-success navbar-btn" data-toggle="modal"  data-target="#login" id="login_button">登陆</button></li>
                        <li><button class="btn btn-info navbar-btn " data-toggle="modal" data-target="#register">注册</button></li>
                    </c:when>
                    <c:otherwise>
                        <li class="head-pic-index">

                            <a href="#"><img src="/imgs/${user.headPic}" class="img-circle user-head" user-id="${user.id}" id="userHeadPic"/></a>

                            <ul class="list-group rightUL">
                                <li class="list-group-item"><a href="##" id="info">信息</a></li>
                                <li class="list-group-item"><a href="/user/logout">退出</a></li>

                            </ul>
                        </li>
                    </c:otherwise>
                </c:choose>

            </ul>
        </div>
    </div>
</nav>
<div class="modal fade" id="login" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">用户登录</h4>
            </div>
            <div class="modal-body">
                <form method="post">
                    <div class="form-group">
                        <label for="username">用户名</label>
                        <input type="text" class="form-control" id="username" name="username" placeholder="Username">
                    </div>
                    <div class="form-group">
                        <label for="password">密码</label>
                        <input type="password" class="form-control" id="password" name="password" placeholder="Password">
                    </div>
                    <div class="form-group">
                        <span id="login_err" class="text-warning">用户名或者密码错误</span>
                        <span id="null_err" class="text-warning">用户名和密码不能为空</span>
                    </div>
                    <button type="button" class="btn btn-success" id="login_sure">确定</button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal">取消</button>
                </form>

            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="register" tabindex="-1" role="dialog" aria-labelledby="myModalLabe2">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabe2">用户注册</h4>
            </div>
            <div class="modal-body">
                <form id="form" enctype="multipart/form-data">
                    <div class="form-group">
                        <label>用户名</label>
                        <input name="username" id="reg_username" type="text" class="form-control" placeholder="Username">
                    </div>
                    <div class="form-group">
                        <label>密码</label>
                        <input name="password" id="reg_password" type="Password"  class="form-control" placeholder="Password">
                    </div>
                    <div class="form-group">
                        <label>邮箱</label>
                        <input name="email" type="email" class="form-control" id="email" placeholder="Email">
                    </div>
                    <div class="form-group">
                        <label >头像</label>
                        <input name="file" type="file" class="form-control" id="file">
                    </div>
                    <div class="form-group">
                        <label >个人介绍</label>
                        <textarea class="form-control" name="selfIntroduction" id="selfIntroduction">你的个人介绍</textarea>
                    </div>
                    <h5>请将如下计算结果填入文本框内：</h5>
                    <div class="form-group form-horizontal">
                        <label class="col-lg-2 control-label " id="captchaOperation"></label>
                        <div class="col-lg-10">
                            <input type="text" class="form-control " name="captcha" id="captchaOperationInput" />
                            <span class="text-danger" id="captchaOperationErr">验证码结果有误</span>
                        </div>
                    </div><br /><br /> <br />
                    <input type="button" class="btn btn-success" id="regiser_sure" value="提交" />
                    <button type="button" class="btn btn-primary" data-dismiss="modal" id="cancel">取消</button>
                    <span id="register-null" class="text-danger">字段不能为空</span>
                </form>

            </div>
        </div>
    </div>
</div>
<div class="container">
    <div class="col-md-3 ">
        <div class="panel panel-default my-panel">

            <div class="panel-body">
                <div class="row">
                    <div class="col-md-4">
                        <a href="##"><img src="/imgs/${post.user.headPic}" alt="" class="img-circle my-icon" /></a>
                    </div>
                    <div class="col-md-8">
                        <a href="##">${post.user.username}</a><span class="mybabge">作者</span><br />
                        <span>Hello, sunshine</span>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6 left1">
                        <p>${post.user.createPostCount}</p><span>发帖总数</span>
                    </div>

                    <div class="col-md-6 left1">
                        <p>${post.postViewCount}</p><span>阅读总量</span>
                    </div>
                </div>
                <div class="row">
                    <div class="zuixingdontai">
                        最新动态
                    </div>
                    <hr />
                </div>
                <c:forEach items="${userLastPosts}" var="userLastPost">
                    <div class="row">
                        <span class="my-label">1</span>
                        <a href="/post/viewPost/${userLastPost.id}">${userLastPost.postTitle}</a>
                    </div>
                </c:forEach>

            </div>
        </div>
    </div>
    <div class="col-md-8 my-right">
        <div class="row">
            <h1>${post.postTitle}</h1>

            <c:if test="${post.user.username==user.username}">
                <button class="btn btn-link" id="delete" data="${post.id}">删除此贴</button>
            </c:if>



            <span><a href="##">${post.user.username}</a>&nbsp; &nbsp;发表于<fmt:formatDate value="${post.postCreateTime}" pattern="yyyy-MM-dd"/>&nbsp;&nbsp;浏览：500</span>

        </div>
        <div class="row">
            <div class="my-txt">
                ${post.postContent}
            </div>

        </div>
        <div class="row">
            <div class="row">
                <div class="pinglun">
                    评论
                </div>

            </div>
            <div class="row">
                <div class="my-textarea" contenteditable="true" id="reply-content"></div>
                <button class="btn btn-success my-submit pull-right" id="reply-sure">提交</button>

            </div>

            <div class="row my-lastrow">
                <div class="zuiixngpinglun">最新评论</div>
                <c:forEach items="${replies}" var="reply">
                <div class="media my-media">
                    <div class="media-left">
                        <a href="#">
                            <img class="media-object" src="/imgs/${reply.user.headPic}" class="img-circle">
                        </a>
                    </div>

                    <div class="media-body">
                        <p class="replayUserName">${reply.user.username}</p>
                        <span class="response">${reply.replyContent}</span>
                    </div>

                </div>
                </c:forEach>

            </div>

        </div>
    </div>

</div>
</body>

</html>