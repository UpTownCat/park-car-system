var place = {
    add: function (basePath) {
        $("#place_modal_submit").click(function () {
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
                    $("#parking_place_modal").modal('hide')
                    common.remind("添加成功！")
                }else {
                    common.remind("添加失败！")
                }
            })
        });
    }
}