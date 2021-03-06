var car = {
    add: function (basePath) {
        var url = basePath + "/car/";
        $("#car_submit").click(function () {
            var number = $("input[name=car_number]").val();
            var branch = $("input[name=car_branch]").val();
            number = encodeURI(number);
            branch = encodeURI(branch);
            $.ajaxFileUpload({
                "url": url,
                fileElementId: "car_file",
                type: "POST",
                secureuri: false,
                data: {"number": number, "branch": branch},
                success: function (data, status) {
                    if(status == "success"){
                        $("#car_modal").modal('hide');
                        common.remind("操作成功！");
                    }else {
                        common.remind("操作失败！");
                    }
                }
            })
        })
    }
}