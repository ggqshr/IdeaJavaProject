<%--
  Created by IntelliJ IDEA.
  User: ggq
  Date: 2017/6/21
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="page-header">
    <h1>
        显示所有食物
    </h1>
    <link rel="stylesheet" href="/assets/css/colorbox.css"/>



    <a data-url="page/index" href="ajax.jsp#page/index" id="urlA"></a>

</div>
<div id="main" class="row">

    <script type="text/javascript">
        var scripts = [null, "../assets/js/jquery.colorbox-min.js", null]
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
    <div class="tabbable tabs-left">
        <ul class="nav nav-tabs" id="myTab3">
            <li class="active">
                <a data-toggle="tab" href="#meat" onclick="getCombo('meat');load()">
                    <%--<i class="pink ace-icon fa fa-f bigger-110"></i>--%>
                    肉类
                </a>
            </li>

            <li>
                <a data-toggle="tab" href="#vegetable" onclick="getCombo('vegetable');load()">
                    <%--<i class="blue ace-icon fa fa-user bigger-110"></i>--%>
                    蔬菜
                </a>
            </li>

            <li>
                <a data-toggle="tab" href="#soup" onclick="getCombo('soup');load()">
                    <%--<i class="ace-icon fa fa-rocket"></i>--%>
                    汤类
                </a>
            </li>

            <li>
                <a data-toggle="tab" href="#noodle" onclick="getCombo('noodle');load()">
                    <%--<i class="ace-icon fa fa-rocket"></i>--%>
                    面食
                </a>
            </li>
        </ul>

        <div class="tab-content" style="height: auto" >
            <div id="meat" class="tab-pane in active" >
            </div>
            <div id="vegetable" class="tab-pane in active">
            </div>
            <div id="soup" class="tab-pane in active">
            </div>
            <div id="noodle" class="tab-pane in active">
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    window.onload = getCombo("meat");
    function turn(num) {
        var url = "/Servlet.Food?method=getFood&id=" + num.toString();
        $.ajax(
            {
                url: url,
                type: "GET",
                success: function () {
                    $("#urlA").attr("href", "ajax.jsp#page/FoodInf");
                    document.getElementById("urlA").click();
                }
            }
        )
    }
    function getCombo(foodType) {

        var getFoodUrl = "/Servlet.QueryAllFood?type=" + foodType.toString();

        $.ajax(
            {
                url: getFoodUrl,
                type: "GET",
                async: false,
                success: function (data) {
                    var html = "<ul class='ace-thumbnails clearfix'>";
                    var list = eval(data);
                    for (var i = 0; i < list.length; i++) {
                        var ll = list[i];
                        var number = ll.foodNumber.toString();
                        html += "<li style='background: #e4e4e4;border: none'><a  href='../assets/images" + ll.foodPhoto + ".jpg' data-rel='colorbox'>" +
                            "<img width='150' height='150' " +
                            "alt='150x150' " +
                            "src='../assets/images" + ll.foodPhoto + ".jpg' />" +
                            "</a><div class='text-center'>" +
                            "<div class='text-center'><span class='label label-xlg label-grey arrowed-in-right arrowed-in'>" +
                            "" + ll.foodName + "</span>" +
                            "</div>" +
                            "<button class='btn btn-white btn-info btn-bold no-border'  onclick='turn(&quot;" + number + "&quot;)'>" +
                            "<i class='ace-icon fa fa-pencil align-top bigger-100'>" +
                            "</i>" +
                            "修改" +
                            "</button>" +
                            "</div></li> ";
                    }
                    html += "</ul>";
                    $("#" + foodType.toString() + "").html(html);
                }
            }
        );
        
    }
    function load() {
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
        });
    }
</script>

