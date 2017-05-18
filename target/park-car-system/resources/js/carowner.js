var carowner = {
    /**
     * 添加一个车主
     * @param basePath
     */
    add: function (basePath) {
        $("#carowner_submit").click(function () {
            var name = $("#carowner_name").val();
            var phone = $("#carowner_phone").val();
            var password = $("#carowner_password").val();
            var url = basePath + "/carowner/";
            var args = {
                "name": name,
                "password": password,
                "phone": phone
            }
            var prefix = location.protocol + "//" + location.host + location.port
            // common.remind(prefix);
            $.post(url, args, function (data) {
                if(data == 1) {
                    $("#car_owner_modal").modal("hide");
                    $.confirm({
                        title: "提示",
                        content: "注册成功！",
                        buttons: {
                            "确定": function () {
                                window.location.href = prefix + "/" + basePath + "/parkingplace/list"
                            }
                        }
                    })
                }
            })
        })
    },
    /**
     * 充值余额
     * @param basePath
     */
    chargeBalance: function(basePath) {
        // $("#add_balance_submit").click(function () {
        //     common.remind(1)
        //     // var balance = $("#balance").val();
        //     // common.remind(balance);
        //     // if(balance.length != 0) {
        //     //     var args = {
        //     //         "balance": balance,
        //     //         "_method": "PUT"
        //     //     };
        //     //     var href = window.location.href;
        //     //     var url = basePath + "/carowner/" + href.substr(href.length - 1);
        //     //     $.post(url, args, function (data) {
        //     //         if(data == 1) {
        //     //             common.remindAndRefalsh("操作成功！");
        //     //         }
        //     //     })
        //     // }
        // })
    }
}