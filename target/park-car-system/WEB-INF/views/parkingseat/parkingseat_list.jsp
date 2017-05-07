<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/5/3
  Time: 18:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>停车位列表</title>
    <%@include file="../base.jsp"%>
    <script type="text/javascript" src="${basePath}/resources/js/parking.js"></script>
    <style type="text/css">
        table{
            height: 400px;
        }
        tr{
            height: 25%;
        }
        td{
            width: 20%;
        }
    </style>
    <script type="text/javascript">
        $(function () {
            var index = "${index}";
            var total = "${total}";
            var basePath = "${basePath}";
            var ownerId = 1;
            var photos = new Array();
            page.init(6, index, total, "");
            page.forward(basePath + "/parkingseat/list", "#seat_list_form", total);
            $.get(basePath + "/car/json/list", {"ownerId": ownerId}, function (data) {
                var select = $("#car_select");
                for(var i = 0; i < data.length; i++) {
                    var car = data[i];
                    select.append("<option value='" + car.id + "'>" + car.number + "</option>");
                    photos.push(car.photo);
                }
                $("#car_preview2").attr("src", basePath + "/common/photo?realName=" + photos[0]);
            })
            $(".use_seat").click(function () {
                $("#seat_id").val(this.name.substring(1));
            })

            $("#car_select").change(function () {
                var index = this.selectedIndex;
                $("#car_preview2").attr("src", basePath + "/common/photo?realName=" + photos[index]);
            })
            parking.add(basePath)
        })
    </script>
</head>
<body>
    <%@include file="../nav.jsp"%>
    <div class="container" style="margin-top: -20px">
        <div class="panel panel-success">
            <div class="panel-heading">
                <h4>停车位列表</h4>
                <form class="navbar-form navbar-right" style="margin-top: -37px" id="seat_list_form">
                    <input type="hidden" name="placeId" value="${placeId}">
                    <input type="hidden" name="index">
                    <div class="form-group">
                        <label>筛选:</label>
                        <select class="form-control">
                            <option>默认</option>
                            <option>空车位</option>
                            <option>非空车位</option>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-default">搜索</button>
                </form>
            </div>
            <div class="panel-body">
                  <table class="table table-bordered">
                      <tbody>
                           <c:forEach begin="0" end="${row }" varStatus="status">
                               <tr>
                                   <c:forEach items="${seats }" var="seat" begin="${status.index * 5}" end="${(status.index + 1) * 5 - 1 }" varStatus="status2">
                                       <td>
                                           <label>车位号:${status2.index + 1 }</label>
                                           <br>
                                           <c:if test="${seat.carId == null }">
                                               <a class="btn btn-primary use_seat" href="#parking_modal" data-toggle="modal" name="n${seat.id}">使用${seat.carId + 10}</a>
                                           </c:if>
                                           <c:if test="${seat.carId != null }">
                                               <a class="btn btn-primary disabled">已占用</a>
                                           </c:if>
                                       </td>
                                   </c:forEach>
                               </tr>
                           </c:forEach>
                      </tbody>
                  </table>
                  <ul class="pagination pull-right" id="pageContent"></ul>
            </div>
        </div>
        <div class="modal face" id="parking_modal">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">停车</h4>
                    </div>
                    <div class="modal-body">
                        <form>
                            <input type="hidden" id="seat_id">
                            <input type="hidden" id="owner_id" value="${sessionScope.carOwner.id}">
                            <div class="form-group">
                                <label class="control-label">请选择车辆</label>
                                <select class="form-control" id="car_select">
                                </select>
                            </div>
                            <img class="img-responsive" src="${basePath}/common/photo?realName=1493902316491282_s_bmw.jpg" id="car_preview2">
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary" id="parking_submit">确定</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
