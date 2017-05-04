<%@page isELIgnored="false" pageEncoding="utf-8" language="java" %>
<html>
<head>
    <title>测试ajax上传文件</title>
    <script type="text/javascript" src="${basePath}/resources/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="${basePath}/resources/js/ajaxfileupload.js"></script>
    <script type="text/javascript">
        $(function () {
            var basePath = "${basePath}";
//            $("button").click(function () {
//                console.log("click");
//                var name = $("input[name=name]").val();
//                $.ajaxFileUpload({
//                    url: basePath + "/hello/file",
//                    fileElementId: "photo",
//                    type: "POST",
//                    secureuri: false,
//                    data: {"name": name},
//                    success: function (data) {
//                        alert(data);
//                    }
//                })
//            })
        })

    </script>
</head>
<body>
    <form action="${basePath}/hello/file">
        <input type="file" name="file" id="photo">
        <br>
        <input type="text" name="name">
        <br>
        <button type="submit">OK</button>
    </form>
</body>
</html>
