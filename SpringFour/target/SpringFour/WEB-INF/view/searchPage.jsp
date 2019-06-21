<%--
  Created by IntelliJ IDEA.
  User: ggq18
  Date: 2018/5/28
  Time: 10:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="page-header">

    <h1>按照景点名搜索景点</h1>
</div>
<div class="row">
    <div class="center">
        <br><br><br> <br> <br> <br>

        <span class="input-icon">
        <i class="ace-icon fa fa-search nav-search-icon"></i>
        <input style="width:500px;" id="custNumber" class="form-control " autocomplete="off" type="text"
               placeholder="请输入景点名">
    </span>
        <button type="button" onclick="getPlace()" class="btn btn-info">查找</button>
    </div>
</div>
<div class="row">
    <%--停止加载进度条--%>
    <script type="text/javascript">
        //用来停止显示加载条
        var scripts = [null, "/assets/js/jquery-ui.custom.min.js", "/assets/js/jquery.ui.touch-punch.min.js", "/assets/js/date-time/moment.min.js", "/assets/js/fullcalendar.min.js", "/assets/js/bootbox.min.js", null]
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
						//Check an Controller below
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
    <%--提交--%>
    <script type="text/javascript">
        function getPlace() {
            var place = document.getElementById("custNumber").value;
            $.ajax(  //ajax提交数据
                {
                    url:"/getPlace?place="+place,
                    type:"GET",
                    success:function (data) {
                        $("#Content").html(data);
                    }
                }
            )
        }
        $("body").keydown(function() { //回车提交
            if (event.keyCode === "13") {
                getPlace();
            }
        });
    </script>
</div>

