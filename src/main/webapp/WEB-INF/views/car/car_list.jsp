<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/5/4
  Time: 21:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>车辆列表</title>
    <%@include file="../base.jsp" %>
    <script type="text/javascript">
        $(function () {
            var current = "${index}";
            var total = '${total}';
            var basePath = '${basePath}';
            page.init(6, current, total, "");
            page.forward(basePath + "/car/list", "#car_list_form", total);
        })
    </script>
</head>
<body>
<%@include file="../nav.jsp" %>
<div class="container" style="margin-top: -20px">
    <div class="panel-success">
        <div class="panel-heading">
            <h4>我的车库</h4>
        </div>
        <div class="panel-body">
            <form id="car_list_form">
                <input type="hidden" name="index">
                <input type="hidden" name="ownerId" value="${ownerId}">
            </form>
            <ul class="list-group" style="margin-top: -15px">
                <c:forEach items="${cars}" var="car">
                    <li class="list-group-item">
                        <a href="${basePath}/common/photo?realName=${car.photo}" style="text-decoration: none; width: 200px"
                           target="_blank">
                            <img src="${basePath}/common/photo?realName=${car.photo}" class="img-responsive"
                                 style="width: 220px" ; height="120px">
                        </a>
                        <div style="margin-left: 300px; margin-top: -50px;">
                            <h4>车牌:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${car.number}</h4>
                            <h4>品牌:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${car.branch}</h4>
                        </div>
                    </li>
                </c:forEach>
                <%--<li class="list-group-item">--%>
                <%--<img src="${basePath}/resources/images/yellow.jpg" class="img-responsive" style="width: 250px"; height="120px">--%>
                <%--<label>品牌:宝马i8</label>--%>
                <%--<label>车牌号：555</label>--%>
                <%--</li>--%>
                <%--<li class="list-group-item"><img src="${basePath}/resources/images/yellow.jpg" class="img-responsive" style="width: 250px"; height="120px"></li>--%>
                <%--<li class="list-group-item"><img src="${basePath}/resources/images/yellow.jpg" class="img-responsive" style="width: 250px"; height="120px"></li>--%>
                <%--<li class="list-group-item"><img src="${basePath}/resources/images/yellow.jpg" class="img-responsive" style="width: 250px"; height="120px"></li>--%>
                <%--<li class="list-group-item"><img src="${basePath}/resources/images/yellow.jpg" class="img-responsive" style="width: 250px"; height="120px"></li>--%>
            </ul>
            <ul class="pagination pull-right" id="pageContent" style="margin-top: -5px"></ul>
        </div>
    </div>
</div>

</body>
</html>
