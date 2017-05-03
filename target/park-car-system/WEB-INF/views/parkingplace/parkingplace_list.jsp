<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/5/3
  Time: 12:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>停车场列表</title>

    <!--样式和js -->
    <%@include file="../base.jsp"%>
    <style type="text/css">
        table{
            height: 450px;
        }
        td{
            width: 30%;
        }
    </style>
    <script type="text/javascript">
        $(function () {
            var placeIndex = "${index }";
            var placeTotal = "${total}";
            page.init(6, placeIndex, placeTotal, "");
            $(".pageItem").click(function () {
                var href = this.href;
                var index = href.substring(href.lastIndexOf("=") + 1, href.length);
                $("input[name=index]").val(index);
                $("#place_form").submit();
                return false;
            })
        })
    </script>
</head>
<body>
    <!-- 导航条 -->
    <%@include file="../nav.jsp"%>
    <div class="container" style="margin-top: -20px ; padding: 0 0">
        <div class="panel panel-success">
            <div class="panel-heading">
                <h4>停车场列表</h4>
                <form class="navbar-form navbar-right" style="margin-top: -37px" id="place_form">
                    <input type="hidden" name="index" value="${index }">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="停车场" name="name" value="${name }">
                    </div>
                    <div class="form-group">
                        <label>价格:</label>
                        <select class="form-control" name="order">
                            <c:if test="${order == 0 || order == null}">
                                <option value="0">默认</option>
                                <option value="1">价格由低到高</option>
                                <option value="2">价格由高到低</option>
                            </c:if>
                            <c:if test="${order == 1 }">
                                <option value="0">默认</option>
                                <option value="1" selected>价格由低到高</option>
                                <option value="2">价格由高到低</option>
                            </c:if>
                            <c:if test="${order == 2 }">
                                <option value="0">默认</option>
                                <option value="1">价格由低到高</option>
                                <option value="2" selected>价格由高到低</option>
                            </c:if>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-default">搜索</button>
                </form>
            </div>
            <div class="panel-body">
                <table class="table table-bordered" style="border: 0px solid #f0ad4e">
                    <tbody>
                        <c:forEach begin="0" end="${row }" varStatus="status">
                            <tr>
                                <c:forEach items="${places }" var="place" begin="${status.index * 3 }" end="${(status.index + 1) * 3 - 1}">
                                    <td>
                                        <label>${place.name }</label>
                                        <br>
                                        <label>地点: ${place.location }</label>
                                        <br>
                                        <label>价格: ${place.moneyPerHour } 元/小时</label>
                                        <br>
                                        <label>剩余车位数: ${place.availableSeat }</label>
                                        <br>
                                        <a class="btn btn-primary" href="${basePath }/parkingseat/list?placeId=${place.id }">选择车位</a>
                                    </td>
                                </c:forEach>
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
