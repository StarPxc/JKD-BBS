<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>we are family</title>
    <script src="/static/js/jquery-3.1.1.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="/static/bootstrap-3.3.7-dist/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
    <script src="/static/js/paging.js" type="text/javascript" charset="utf-8"></script>
    <script src="/static/js/sweetalert.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="/static/js/index.js" type="text/javascript" charset="utf-8"></script>

    <link rel="stylesheet" type="text/css" href="/static/bootstrap-3.3.7-dist/css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="/static/css/index.css" />
    <link rel="stylesheet" type="text/css" href="/static/css/paging.css" />
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

                <form class="navbar-form navbar-left myform" id="search-form" method="post" action="/post/search">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Search" id="search-text" name="searchTitle">
                    </div>
                    <button type="button" class="btn btn-primary" id="search-button">搜索</button>

                    <div class="auto-completed">

                    </div>

                </form>
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
    <div class="col-md-8">
        <ul class="list-group">
            <a href="##" class="list-group-item active">
                今日更新
            </a>
            <c:forEach items="${posts}" var="post">
                <li class="list-group-item">
                    <dl>

                        <dt class="mydt"><a href="/post/viewPost/${post.id}">${post.postTitle}</a><span class="info">----${post.user.username} &nbsp;<fmt:formatDate value="${post.postCreateTime}" pattern="yyyy-MM-dd"/></span></dt>
                        <dd>
                            <span class="postContent">${post.postContent}</span>

                            <div class="pull-right comment">
                                <a href="/post/viewPost/${post.id}">${post.postReplyCount}&nbsp;</a><span class="glyphicon glyphicon-comment"></span>&nbsp;
                                <a href="/post/viewPost/${post.id}">${post.postViewCount}&nbsp;</a><span class="glyphicon glyphicon-eye-open"></span>
                            </div>
                        </dd>
                    </dl>
                </li>
            </c:forEach>


        </ul>
       <div id="pageTool" data="${postCount}"></div>
        <form action="/post" id="pagForm" data="${currentPage}">
            <input type="hidden" id="pageNum" name="pageNum">
            <input type="hidden" id="size" name="size">
        </form>

    </div>

    <div class="col-md-4 ">
        <div class="panel panel-danger new">

            <div class="panel-body">
                <button class="btn btn-info btn-lg " id="create-new-post"><span class="glyphicon glyphicon-edit"></span>&nbsp;发布新帖</button>
            </div>

        </div>
        <div class="panel panel-success ">
            <div class="panel-heading">
                <h3 class="panel-title">热门文章</h3>
            </div>
            <div class="panel-body">
                <c:forEach items="${hotPosts}" var="hotPost">
                <div class="media">
                    <div class="media-left">
                        <a href="#">
                            <img class="media-object replyHeadPic" src="/imgs/${hotPost.user.headPic}">
                        </a>
                    </div>
                    <div class="media-body">
                        <h4 class="media-heading hot-post"><a href="/post/viewPost/${hotPost.id}">${hotPost.postTitle}</a></h4>

                        <span class="">浏览：${hotPost.postViewCount}</span>
                    </div>
                </div>
                </c:forEach>
            </div>


        </div>
        <div class="panel panel-danger ">
            <div class="panel-heading">
                <h3 class="panel-title">最新回复</h3>
            </div>
            <div class="panel-body">
                <c:forEach items="${replies}" var="reply">
                <div class="media">
                    <div class="media-left">
                        <a href="#">
                            <img class="media-object replyHeadPic" src="/imgs/${reply.user.headPic}">
                        </a>
                    </div>
                    <div class="media-body">
                        <a href="/post/viewPost/${reply.post.id}"><h4 class="media-heading">${reply.post.postTitle}</h4></a>

                        <span class="response">#${reply.user.username}:${reply.replyContent}</span>
                    </div>
                </div>
                </c:forEach>
            </div>

        </div>
    </div>
</div>

</body>

</html>