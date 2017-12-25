<%--
  Created by IntelliJ IDEA.
  User: PXC
  Date: 2017/6/27
  Time: 9:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Title</title>
    <script src="/static/js/jquery-3.1.1.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="/static/bootstrap-3.3.7-dist/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
    <script src="/static/summernote/summernote.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="/static/js/sweetalert.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="/static/js/createPostView.js" type="text/javascript" charset="utf-8"></script>


    <link rel="stylesheet" type="text/css" href="/static/bootstrap-3.3.7-dist/css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="/static/css/createPostView.css"/>
    <link rel="stylesheet" type="text/css" href="/static/summernote/summernote.css"/>
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
            <a class="navbar-brand top-link" href="#">logo</a>
            <a class="navbar-brand top-link" href="#">网站首页</a>
            <a class="navbar-brand top-link" href="/post">论坛首页</a>

        </div>
        <div class="collapse navbar-collapse" id="example-navbar-collapse">
            <ul class="nav navbar-nav navbar-right lr">

                <form class="navbar-form navbar-left myform">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Search">
                    </div>
                    <button type="submit" class="btn btn-primary">搜索</button>
                </form>

                <li class="head-pic-index">

                    <a href="#"><img src="/imgs/${user.headPic}" class="img-circle user-head"/></a>

                    <ul class="list-group rightUL">
                        <li class="list-group-item"><a href="/user/info">信息</a></li>
                        <li class="list-group-item"><a href="/user/logout">退出</a></li>

                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="modal fade" id="login" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
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
                        <input type="password" class="form-control" id="password" name="password"
                               placeholder="Password">
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
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabe2">用户注册</h4>
            </div>
            <div class="modal-body">
                <form id="form" enctype="multipart/form-data">
                    <div class="form-group">
                        <label>用户名</label>
                        <input name="username" id="reg_username" type="text" class="form-control"
                               placeholder="Username">
                    </div>
                    <div class="form-group">
                        <label>密码</label>
                        <input name="password" id="reg_password" type="Password" class="form-control"
                               placeholder="Password">
                    </div>
                    <div class="form-group">
                        <label>邮箱</label>
                        <input name="email" type="email" class="form-control" id="email" placeholder="Email">
                    </div>
                    <div class="form-group">
                        <label>头像</label>
                        <input name="file" type="file" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>个人介绍</label>
                        <textarea class="form-control" name="selfIntroduction" id="selfIntroduction">你的个人介绍</textarea>
                    </div>
                    <h5>请将如下计算结果填入文本框内：</h5>
                    <div class="form-group form-horizontal">
                        <label class="col-lg-2 control-label " id="captchaOperation"></label>
                        <div class="col-lg-10">
                            <input type="text" class="form-control " name="captcha" id="captchaOperationInput"/>
                            <span class="text-danger" id="captchaOperationErr">验证码结果有误</span>
                        </div>
                    </div>
                    <br/><br/> <br/>
                    <input type="button" class="btn btn-success" id="regiser_sure" value="提交"/>
                    <button type="button" class="btn btn-primary" data-dismiss="modal" id="cancel">取消</button>
                    <span id="register-null" class="text-danger">字段不能为空</span>
                </form>

            </div>
        </div>
    </div>
</div>

<div class="container">

    <form class="form-horizontal post-title">
        <div class="form-group">
            <label for="postTitle" class="pull-left title-label" >标题</label>
            <div class="col-sm-10">
                <input type="text" name="postTitle" id="postTitle" class="form-control">
            </div>
        </div>
    </form>

    <div class="edit">
        <div id="summernote"></div>
        <button id="createPost" class="btn btn-primary pull-right">提交</button>
    </div>

</div>

</body>
</html>
