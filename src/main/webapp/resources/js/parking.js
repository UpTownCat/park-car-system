var parking = {
    add: function (basePath) {
        $("#parking_submit").click(function () {
            var url = basePath + "/parking/";
            var ownerId = $("#owner_id").val();
            if(ownerId.length == 0) {
                ownerId = 1;
            }
            var seatId = $("#seat_id").val();
            var select = $("#car_select")[0];
            var index = select.selectedIndex;
            var selectValue = select.options[index].value;
            var args = {
                "carOwnerId": ownerId,
                "parkingSeatId": seatId,
                "carId": selectValue
            }
            $.post(url, args, function (data) {
                if(data == 1) {
                    $("#parking_modal").modal("hide");
                    common.remind("停车成功！");
                    $("a[name=n" + seatId + "]").addClass("disabled").text("已占用");
                }
            })
        })
    },
    leave: function () {
        $(".leave").click(function () {
            var href = this.href;
            $.post(href, {}, function (data) {
                if(data == 1){
                    $.confirm({
                        title: "提示",
                        content: "操作成功！",
                        buttons:{
                            "确定": function () {
                                window.location.reload(true);
                            }
                        }
                    })
                }
            })
            return false;
        })
    }
}