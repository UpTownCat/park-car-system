<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/5/3
  Time: 11:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
    <script type="text/javascript">
        $(function () {
            var basePath = "${basePath }";
            $("#submit").click(function () {
                var name = $("input[name=name]").val();
                var location = $("input[name=location]").val();
                var moneyPerHour = $("input[name=moneyPerHour]").val();
                var url = basePath + "/parkingplace/";
                var args = {
                    "name": name,
                    "location": location,
                    "moneyPerHour": moneyPerHour
                }
                $.post(url, args, function (data) {
                    if(data == 1) {
                        $("#parking_place_modal").modal({
                            show: false
                        })
                        common.remind("添加成功！")
                    }else {
                        common.remind("添加失败！")
                    }
                })
            })
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
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">个人中心 <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">我的资料</a></li>
                            <li><a href="#">我的车辆</a></li>
                            <li><a href="#">修改密码</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="#">停车记录</a></li>
                        </ul>
                    </li>
                </ul>
                <%--<form class="navbar-form navbar-right">--%>
                    <%--<div class="form-group">--%>
                        <%--<input type="text" class="form-control" placeholder="Search">--%>
                    <%--</div>--%>
                    <%--<button type="submit" class="btn btn-default">Submit</button>--%>
                <%--</form>--%>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>
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
                    <button type="button" class="btn btn-primary" data-dismiss="modal" id="submit">确定</button>
                </div>
            </div>

        </div>

    </div>

