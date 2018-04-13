<%--
  Created by IntelliJ IDEA.
  User: ggq
  Date: 2017/6/22
  Time: 8:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="page-header">
    <h1>添加食物</h1>
</div>
<div class="row">
    <a data-url="page/typography" href="ajax.jsp#page/QueryAllFood" id="urlA"></a>
    <script type="text/javascript">
        function addFood() {
            var form = new FormData(document.getElementById("foodForm"));
            form.append("method", "Add");
            $.ajax(
                {
                    url: "Servlet.Food",
                    type: "POST",
                    data: form,
                    processData: false,
                    contentType: false,
                    success: function (data) {
                        if (data === "foodfound") {
                            alert("该名字的食物已经存在，请更改");
                        } else if (data === "notloadfile") {
                            alert("没有上传图片，请上传");
                        } else {
                            alert("添加成功");
                            document.getElementById("urlA").click();
                        }
                    }
                }
            );
        }
    </script>

    <div class="col-xs-12">
        <form class="form-horizontal" role="form" id="foodForm">
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right">
                    食物名
                </label>
                <div class="col-sm-8">
                    <input type="text" name="foodName" placeholder="食物姓名" id="form-field-1"
                           class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right">
                    食物价格
                </label>
                <div class="col-sm-8">
                    <input type="text" name="foodPrice" placeholder="食物价格" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right">
                    食物类型
                </label>
                <div class="radio">
                    <label><input class="ace" checked="checked" type="radio" name="foodType" value="meat">
                        <span class="lbl">肉类</span></label>
                    <label><input class="ace" type="radio" name="foodType" value="vegetable">
                        <span class="lbl">蔬菜</span></label>
                    <label><input class="ace" type="radio" name="foodType" value="soup">
                        <span class="lbl">汤类</span></label>
                    <label><input class="ace" type="radio" name="foodType" value="noodle">
                        <span class="lbl">面食</span></label>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right">
                    食物图片
                </label>
                <div class="col-sm-4">
                    <input name="foodPhoto" type="file" id="id-input-file-3"/>
                    <!-- /section:custom/file-input -->
                </div>
            </div>
            <div class="clearfix form-actions">
                <div class="col-md-offset-3 col-md-9">
                    <button class="btn btn-info" type="button" onclick="addFood();">
                        <i class="ace-icon fa fa-check bigger-110"></i>
                        提交
                    </button>

                    &nbsp; &nbsp; &nbsp;
                    <button id="goback" class="btn" type="button" onclick="JavaScript:history.go(-1)">
                        <i class="ace-icon fa fa-undo bigger-110"></i>
                        返回
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript">
    //用来停止显示加载条
    var scripts = [null, "../assets/js/jquery-ui.custom.min.js", "../assets/js/jquery.ui.touch-punch.min.js", "../assets/js/date-time/moment.min.js", "../assets/js/fullcalendar.min.js", "../assets/js/bootbox.min.js", null]
    ace.load_ajax_scripts(scripts, function () {
    });
    $('#id-input-file-3').ace_file_input({
        style: 'well',
        btn_choose: 'Drop files here or click to choose',
        btn_change: null,
        no_icon: 'ace-icon fa fa-cloud-upload',
        droppable: true,
        thumbnail: 'small'//large | fit
        //,icon_remove:null//set null, to hide remove/reset button
        /**,before_change:function(files, dropped) {
						//Check an example below
						//or examples/file-upload.html
						return true;
					}*/
        /**,before_remove : function() {
						return true;
					}*/
        ,
        preview_error: function (filename, error_code) {
            //name of the file that failed
            //error_code values
            //1 = 'FILE_LOAD_FAILED',
            //2 = 'IMAGE_LOAD_FAILED',
            //3 = 'THUMBNAIL_FAILED'
            //alert(error_code);
        }

    }).on('change', function () {
        //console.log($(this).data('ace_input_files'));
        //console.log($(this).data('ace_input_method'));
    });
</script>