var image = {
    /**
     * 进行图片预览
     * @param fileId 隐藏的file控件的id
     * @param loadNameId 显示文件名称的控件id
     * @param imgId 预览的控件id
     * @fileOpenId 触发的控件id
     */
    preview: function (fileId,loadNameId, imgId, fileOpenId) {
        $(fileOpenId).click(function () {
            $(fileId).click();
        })
        $(fileId).change(function() {
            //判断是否是有效文件
            var file = this.files[0];
            if (file.type !== 'image/jpeg' && file.type !== 'image/png' && file.type !== 'image/gif') {
                common.remind("不是有效的图片文件!");
                return;
            }
            //给输入框显示文件名
            var path = $(this).val();
            path = path.split('\\');
            path = path[path.length-1];
            $(loadNameId).val(path);
            //读出图片
            var reader = new FileReader();
            reader.onload = function (e) {
                var data = e.target.result;
                $(imgId).attr("src", data);
            }
            reader.readAsDataURL(file);
        });
    }
}