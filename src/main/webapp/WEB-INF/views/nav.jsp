<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/5/3
  Time: 11:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
    <%@include file="base.jsp"%>
    <script type="text/javascript" src="${basePath}/resources/js/car.js"></script>
    <script type="text/javascript" src="${basePath}/resources/js/place.js"></script>
    <script type="text/javascript" src="${basePath}/resources/js/md5.js"></script>
    <script type="text/javascript" src="${basePath}/resources/js/carowner.js"></script>

    <script type="text/javascript">
        $(function () {
            var basePath = "${basePath }";
            //添加停车场
            place.add(basePath);
            //图片预览
            image.preview("#car_file", "#uploadurl", "#car_preview", "#car_open_file");
            //添加车辆
            car.add(basePath);
            //添加用户
            carowner.add(basePath);
        })
    </script>
    <nav class="navbar navbar-inverse navbar-static-top">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">停车管理系统</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#parking_place_modal" data-toggle="modal">添加停车场</a></li>
                    <li><a href="#car_modal" data-toggle="modal">添加车辆</a></li>
                    <li><a href="#car_owner_modal" data-toggle="modal">注册</a></li>
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">个人中心 <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="${basePath}/carowner/${sessionScope.owner.id}">我的资料</a></li>
                            <li><a href="${basePath}/car/list?ownerId=${sessionScope.owner.id}">我的车辆</a></li>
                            <li><a href="#">修改密码</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="#">停车记录</a></li>
                        </ul>
                    </li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>
<!--添加停车场的模态框 -->
<div class="modal fade" id="parking_place_modal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">添加停车场</h4>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <label class="control-label">名称</label>
                        <input type="text" class="form-control" name="name">
                    </div>
                    <div class="form-group">
                        <label class="control-label">地点</label>
                        <input class="form-control" id="message-text" name="location">
                    </div>
                    <div class="form-group">
                        <label class="control-label">收费标准</label>
                        <div class="input-group">
                            <input type="text" class="form-control" name="moneyPerHour">
                            <div class="input-group-addon">元/小时</div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="place_modal_submit">确定</button>
            </div>
        </div>
    </div>
</div>
<!--添加车辆的模态框 -->
<div class="modal fade" id="car_modal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">添加车辆</h4>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <label class="control-label">品牌</label>
                        <input type="text" class="form-control" name="car_branch">
                    </div>
                    <div class="form-group">
                        <label class="control-label">车牌号</label>
                        <input class="form-control" name="car_number">
                    </div>
                    <div class="form-group">
                        <label class="control-label">照片</label>
                        <input type="file" name="carPhoto" id="car_file" style="display:none">
                        <div class="input-group">
                            <input id="uploadurl" class="form-control" type="text" disabled>
                            <div class="input-group-addon">
                                <span id="car_open_file">
                                    <i class="icon-folder-open-alt"></i> 浏览
                                </span>
                            </div>
                        </div>
                    </div>
                    <img class="img-responsive" id="car_preview">
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="car_submit">确定</button>
            </div>
        </div>
    </div>
</div>

<!--用户注册 -->
<div class="modal fade" id="car_owner_modal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">注册</h4>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <label class="control-label">姓名</label>
                        <input class="form-control" id="carowner_name">
                    </div>
                    <div class="form-group">
                        <label class="control-label">手机号码</label>
                        <div class="input-group">
                            <input type="text" class="form-control" id="carowner_phone">
                            <div class="input-group-addon">
                                <span class="glyphicon glyphicon-pencil"></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">密码</label>
                        <input type="password" class="form-control" id="carowner_password">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="carowner_submit">确定</button>
            </div>
        </div>

    </div>
</div>

