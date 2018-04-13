<%@ page import="java.util.ArrayList" %>
<%@ page import="Enetity.Combo" %>
<%--
  Created by IntelliJ IDEA.
  User: ggq
  Date: 2017/6/16
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="page-header">
    <h1>
        删除套餐
    </h1>
    <style type="text/css">
        li {
            border: none;
        }
    </style>
    <a data-url="page/index" href="ajax.jsp#page/index" id="urlA"></a>
</div>
<div id="main" class="row">

</div>
<script src="/assets/js/jquery.gritter.min.js"></script>
<script type="text/javascript">
    //用来停止显示加载条
    var scripts = [null, "../assets/js/jquery-ui.custom.min.js", "../assets/js/jquery.ui.touch-punch.min.js", "../assets/js/date-time/moment.min.js", "../assets/js/fullcalendar.min.js", "../assets/js/bootbox.min.js", null]
    ace.load_ajax_scripts(scripts, function () {
    });
</script>
<script type="text/javascript">
    window.onload = getCombo();
    function turn(num) {
        bootbox.confirm("确定要删除么，删除后将无法恢复?", function (result) {
            if (result) {
                var url = "/Servlet.Combo?method=Delete&id=" + num.toString();
                $.ajax(
                    {
                        url: url,
                        type: "GET",
                        success: function (data) {
                            if (data === "ok") {
//                                $("#urlA").attr("href", "ajax.jsp#page/DeleteCombo");
                                getCombo();
                                $.gritter.add({
                                    text: "<div class='text-center' style='font-size: 30px;color: #ffffff'><span>删除成功</span></div>",
                                    time: 1600,
                                    speed: 100,
                                    class_name: 'gritter-info gritter-center '
                                });
                            }
                            else {
                                $.gritter.add({
                                    text: "<div class='text-center' style='font-size: 30px;color: #ffffff'><span>删除失败</span></div>",
                                    time: 1900,
                                    speed: 100,
                                    class_name: 'gritter-info gritter-center '
                                });
                                $("#urlA").attr("href", "ajax.jsp#page/DeleteCombo");
                                document.getElementById("urlA").click();
                            }
                        }
                    }
                )
            }
        });

    }
    function getCombo() {
        $("#combo").hide();
        $.ajax(
            {
                url: "/Servlet.QueryAllCombo",
                type: "GET",
                async: false,
                success: function (data) {
                    var html = "<ul class='ace-thumbnails clearfix' id='test'>";
                    var list = eval(data);
                    for (var i = 0; i < list.length; i++) {
                        var ll = list[i];
                        var number = ll.comboNumber.toString();
                        html += "<li style='background: #e4e4e4;border: none'><a href='../assets/images" + ll.comboPhoto + ".jpg' data-rel='colorbox'>" +
                            "<img width='150' height='150' " +
                            "alt='150x150' " +
                            "src='../assets/images" + ll.comboPhoto + ".jpg' />" +
                            "</a><div class='text-center'><span class='label label-xlg label-grey arrowed-in-right arrowed-in'>" +
                            "" + ll.comboName + "</span>" +
                            "</div>" +
                            "<div class='text-center'>" +
                            "<button class='btn btn-white btn-warning btn-bold' onclick='turn(&quot;" + number + "&quot;)'>" +
                            "<i class='ace-icon fa fa-trash-o bigger-120 orange'>" +
                            "</i>" +
                            "删除" +
                            "</button>" +
                            "</div></li> ";
                    }
                    html += "</ul>";
                    $("#main").html(html);
                }
            }
        );
    }
</script>
<link rel="stylesheet" href="assets/css/colorbox.css"/>
<script type="text/javascript">
    var scripts = [null, "../../assets/js/jquery.colorbox-min.js", null]
    ace.load_ajax_scripts(scripts, function () {
        //inline scripts related to this page
        jQuery(function ($) {
            var $overflow = '';
            var colorbox_params = {
                rel: 'colorbox',
                reposition: true,
                scalePhotos: true,
                scrolling: false,
                previous: '<i class="ace-icon fa fa-arrow-left"></i>',
                next: '<i class="ace-icon fa fa-arrow-right"></i>',
                close: '&times;',
                current: '{current} of {total}',
                maxWidth: '100%',
                maxHeight: '100%',
                onOpen: function () {
                    $overflow = document.body.style.overflow;
                    document.body.style.overflow = 'hidden';
                },
                onClosed: function () {
                    document.body.style.overflow = $overflow;
                },
                onComplete: function () {
                    $.colorbox.resize();
                }
            };

            $('.ace-thumbnails [data-rel="colorbox"]').colorbox(colorbox_params);
            $("#cboxLoadingGraphic").html("<i class='ace-icon fa fa-spinner orange'></i>");//let's add a custom loading icon
        })
    });
</script>
