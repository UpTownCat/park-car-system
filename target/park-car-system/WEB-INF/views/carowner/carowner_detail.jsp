<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/5/6
  Time: 17:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>个人资料</title>
    <%@include file="../base.jsp" %>
    <script type="text/javascript" src="${basePath}/resources/js/carowner.js"></script>
    <script type="text/javascript" src="${basePath}/resources/js/image.js"></script>
    <script type="text/javascript">
        $(function () {
            var basePath = "${basePath}";
            var href = window.location.href;
            image.preview("#photo_file", "#photo_name", "#photo_preview", "#photo_open_file")
            $("#add_balance_submit").click(function () {
//                common.remind(1)
                var balance = $("#balance").val();
//                 common.remind(balance);
                if (balance.length != 0) {
                    var args = {
                        "balance": balance,
                        "_method": "PUT"
                    };
                    var url = basePath + "/carowner/" + href.substr(href.length - 1);
                    $.post(url, args, function (data) {
                        if (data == 1) {
                            $("#add_balance_modal").modal("hide");
                            $.confirm({
                                title: "提示",
                                content: "操作成功！",
                                buttons: {
                                    "确定": function () {
                                        window.location.reload(true);
                                    }
                                }
                            })
                        } else {
                            common.remind("操作失败！");
                        }
                    })
                }
            });
            $("#change_photo_submit").click(function () {
                var src = $("#old_photo").attr("src");
                var oldPhoto = src.substr(src.lastIndexOf("=") + 1);
                $.ajaxFileUpload({
                    url: basePath + "/carowner/" + href.substr(href.length - 1),
                    fileElementId: "photo_file",
                    type: "POST",
                    secureuri: false,
                    data: {
                        "oldPhoto": oldPhoto
                    },
                    success: function (data, status) {
                        if(status == "success") {
                            $("#change_photo_modal").modal("hide");
                            $.confirm({
                                title: "提示",
                                content: "操作成功！",
                                buttons: {
                                    "确定": function () {
                                        window.location.reload(true);
                                    }
                                }
                            })
                        }else{
                            common.remind("操作失败！");
                        }
                    }
                })
            })
        })
    </script>
</head>
<body>
<%@include file="../nav.jsp" %>
<div class="container" style="margin-top: -20px">
    <div class="panel panel-success">
        <div class="panel-heading">
            <h4>个人资料</h4>
        </div>
        <div class="panel-body">
            <table class="table table-bordered">
                <tr>
                    <td>姓名</td>
                    <td>${carOwner.name}</td>
                </tr>
                <tr>
                    <td>性别</td>
                    <td>${carOwner.gender == 0 ? "男" : "女"}</td>
                </tr>
                <tr>
                    <td>电话</td>
                    <td>${carOwner.phone}</td>
                </tr>
                <tr>
                    <td>余额</td>
                    <td>
                        ${carOwner.balance}
                        <a class="btn btn-primary pull-right" href="#add_balance_modal" data-toggle="modal">充值</a>
                    </td>
                </tr>
                <tr>
                    <td>头像</td>
                    <td>
                        <img class="img-responsive" src="${basePath}/common/photo?realName=${carOwner.photo}" id="old_photo">
                        <a class="btn btn-primary pull-right" href="#change_photo_modal" data-toggle="modal">更换头像</a>
                    </td>
                </tr>
            </table>
        </div>
    </div>
    <div class="modal fade" id="add_balance_modal">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">充值</h4>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="form-group">
                            <label class="control-label">充值金额</label>
                            <input class="form-control" id="balance">
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" id="add_balance_submit">确定</button>
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" id="change_photo_modal">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">更换头像</h4>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="form-group">
                            <label class="control-label">照片</label>
                            <input type="file" name="photo" id="photo_file" style="display:none">
                            <div class="input-group">
                                <input id="photo_name" class="form-control" type="text" disabled>
                                <div class="input-group-addon">
                                <span id="photo_open_file">
                                    <i class="icon-folder-open-alt"></i> 浏览
                                </span>
                                </div>
                            </div>
                        </div>
                        <img class="img-responsive" id="photo_preview">
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" id="change_photo_submit">确定</button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
