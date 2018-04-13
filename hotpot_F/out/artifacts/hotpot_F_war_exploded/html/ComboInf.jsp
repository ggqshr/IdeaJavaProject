<%@ page import="Enetity.Combo" %>
<%@ page import="java.io.File" %><%--
  Created by IntelliJ IDEA.
  User: ggq
  Date: 2017/6/18
  Time: 13:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="page-header">
    <h1>修改信息</h1>
</div>
<div class="row">
    <a data-url="page/index" href="ajax.jsp#page/QueryAllCombo" id="urlB"></a>
        <%
        Combo combo = (Combo) session.getAttribute("combo");
        pageContext.setAttribute("combo", combo);
    %>
    <script type="text/javascript">
        function change() {
            var form = new FormData(document.getElementById("comboForm"));
            form.append("comboNumber", "${combo.comboNumber}");
            form.append("method", "Update");
            $.ajax(
                {
                    url: "Servlet.Combo",
                    type: "POST",
                    data: form,
                    processData: false,
                    contentType: false,
                    success: function (data) {
                        if (data === "combofound") {
                            alert("该名字的套餐已经存在，请更改");
                        } else if (data === "notloadfile") {
                            alert("没有上传图片，请上传");
                        } else {
                            alert("修改成功，请重新刷新");
                            location.replace("ajax.jsp#page/QueryAllCombo");
                        }
                    }
                }
            );
        }
    </script>
    <div class="col-xs-12">
        <form class="form-horizontal" role="form" id="comboForm">
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right">
                    套餐名
                </label>
                <div class="col-sm-8">
                    <input type="text" name="comboName" value="${combo.comboName}" id="form-field-1"
                           class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right">
                    套餐价格
                </label>
                <div class="col-sm-8">
                    <input type="text" name="comboPrice" value="${combo.comboPrice}" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right">
                    锅底
                </label>
                <div class="col-sm-8">
                    <input type="text" name="comboSoup" value="${combo.hotpotSoup}" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right">
                    蔬菜数量
                </label>
                <div class="col-sm-8">
                    <input type="text" name="vegetableNum" value="${combo.vegetableNum}" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right">
                    肉类数量
                </label>
                <div class="col-sm-8">
                    <input type="text" name="meatNum" value="${combo.meatNum}" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right">
                    面食数量
                </label>
                <div class="col-sm-8">
                    <input type="text" name="noodleNum" value="${combo.noodlesNum}" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right">
                    汤类数量
                </label>
                <div class="col-sm-8">
                    <input type="text" name="soupNum" value="${combo.soupNum}" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right">
                    套餐图片
                </label>
                <div class="col-sm-4">
                    <input name="comboPhoto" type="file" id="id-input-file-3"/>
                    <!-- /section:custom/file-input -->
                </div>
            </div>
            <div class="clearfix form-actions">
                <div class="col-md-offset-3 col-md-9">
                    <button class="btn btn-info" type="button" onclick="change()">
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