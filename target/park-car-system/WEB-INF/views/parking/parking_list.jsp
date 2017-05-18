<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/5/7
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>停车记录列表</title>
    <%@include file="../base.jsp"%>
    <script type="text/javascript" src="${basePath}/resources/js/parking.js"></script>
    <script type="text/javascript" src="${basePath}/resources/js/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript">
        $(function () {
            var index = "${index}";
            var basePath = "${basePath}";
            var total = "${total}";
            page.init(6, index, total, "");
            parking.leave();
        })
    </script>
</head>
<body>
    <%@include file="../nav.jsp"%>
    <div class="container" style="margin-top: -20px">
        <div class="panel panel-success">
            <div class="panel-heading">
                <h4>停车记录列表</h4>
                <form class="navbar-form navbar-right" style="margin-top: -37px" id="place_list_form">
                    <input type="hidden" name="index" value="${index }">
                    <input type="hidden" name="carOwnerId" value="${carOwnerId}">
                    <div class="form-group">
                        <label>时间:</label>
                        <input type="text" class="form-control Wdate" placeholder="时间" onclick="WdatePicker()" name="date">
                    </div>
                    <div class="form-group">
                        <label>车辆:</label>
                        <select class="form-control" name="carId">
                            <option value="0">默认</option>
                            <c:forEach items="${cars}" var="car">
                                <option value="${car.id}">${car.number}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>停车费:</label>
                        <select class="form-control" name="order">
                            <c:if test="${order == 0 || order == null}">
                                <option value="0">默认</option>
                                <option value="1">由低到高</option>
                                <option value="2">由高到低</option>
                            </c:if>
                            <c:if test="${order == 1 }">
                                <option value="0">默认</option>
                                <option value="1" selected>由低到高</option>
                                <option value="2">由高到低</option>
                            </c:if>
                            <c:if test="${order == 2 }">
                                <option value="0">默认</option>
                                <option value="1">由低到高</option>
                                <option value="2" selected>由高到低</option>
                            </c:if>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-default">搜索</button>
                </form>
            </div>
            <div class="panel-body">
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <td>车牌</td>
                            <td>停车场</td>
                            <td>停车时间</td>
                            <td>结束时间</td>
                            <td>停车费</td>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${parkings}" var="parking">
                            <tr>
                                <td>
                                        ${parking.car.number}
                                </td>
                                <td>
                                        ${parking.parkingPlace.name}
                                </td>
                                <td>
                                        <fmt:formatDate value="${parking.inTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
                                </td>
                                <td>
                                    <c:if test="${parking.outTime == null}">
                                        <a class="btn btn-primary leave" href="${basePath}/parking/leave?id=${parking.id}">结束</a>
                                    </c:if>
                                    <c:if test="${parking.outTime != null}">
                                        <fmt:formatDate value="${parking.outTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
                                    </c:if>
                                </td>
                                <td>
                                        ${parking.price}
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <ul class="pagination pull-right" id="pageContent" style="margin-top: -5px"></ul>
            </div>
        </div>
    </div>
</body>
</html>
