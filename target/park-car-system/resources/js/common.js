var common = {
    
    remind: function (content) {
        $.confirm({
            backgroundDismiss: true,
            title: "提示",
            "content": content,
            buttons: {
                "关闭": function () {
                    
                }
            }
        })
    },

    remindAndRefalsh: function (content) {
        $.confirm({
            title: "提示",
            "content": content,
            buttons: {
                "确定": function () {
                    window.location.reload(true);
                }
            }
        })
    }
}