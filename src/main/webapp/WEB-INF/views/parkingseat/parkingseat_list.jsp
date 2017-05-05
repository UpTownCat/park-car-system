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
            page.init(6, index, total, "");
            page.forward(basePath + "/parkingseat/list", "#seat_list_form", total);
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
                                               <a class="btn btn-primary" href="">使用</a>
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
    </div>
</body>
</html>
