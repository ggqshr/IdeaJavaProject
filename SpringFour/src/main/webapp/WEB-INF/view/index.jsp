<%@ page import="entity.all_rank_table" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.net.URLEncoder" %>
<%--
  Created by IntelliJ IDEA.
  User: ggq
  Date: 2018/5/13
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="page-header">
    <script type="text/javascript">
        $(document).ready(function () {
            $("#sample-table-2").DataTable(
                {
                    processing: true,
                    // columns: [
                    //     {data: "comment_place"},
                    //     {data: "view_rank"},
                    //     {data: "price_rank"},
                    //     {data: "interest_rank"},
                    //     {data: "total_rank"}
                    // ]
                }
            )
            // $.ajax(
            //     {
            //         url: "/getjson",
            //         type: "GET",
            //         async: false,
            //         dataType: 'json',
            //         success: function (dd) {
            //             $("#sample-table-2").DataTable(
            //                 {
            //                     processing: true,
            //                     data: dd,
            //                     columns: [
            //                         {data: "comment_place"},
            //                         {data: "view_rank"},
            //                         {data: "price_rank"},
            //                         {data: "interest_rank"},
            //                         {data: "total_rank"}
            //                     ]
            //                 }
            //             )
            //         }
            //     }
            // )
        });
    </script>
    <h1>
        显示前64名排名
    </h1>
    <a data-url="page/index" href="ajax.jsp#page/index" id="urlA"></a>
    <a id="inboxTurn" href="#modal-table" role="button" class="green" data-toggle="modal"></a>
</div>
<div class="row">
    <div class="col-xs-12">
        <div class="table-header">
            青岛市旅游景点排名
        </div>

        <!-- <div class="table-responsive"> -->

        <!-- <div class="dataTables_borderWrap"> -->
        <div>
            <table id="sample-table-2" class="table table-striped table-bordered table-hover">
                <thead>
                <tr>
                    <th>景点名称</th>
                    <th>按照景点景色排名</th>
                    <th class="hidden-480">按照性价比排名</th>

                    <th>
                        按照趣味性排名
                    </th>
                    <th class="hidden-480">总排名</th>

                </tr>
                </thead>

                <tbody class="center">
                <c:forEach items="${list}" var='ss'>
                    <tr>
                        <td>
                            <a href="ajax.jsp#page/place?p=${ss.comment_place}&view=${ss.view_rank}&price=${ss.price_rank}&interest=${ss.interest_rank}&total=${ss.total_rank}">${ss.comment_place}</a>
                        </td>

                        <td>
                                ${ss.view_rank}
                        </td>
                        <td>
                                ${ss.price_rank}
                        </td>
                        <td>
                                ${ss.interest_rank}
                        </td>
                        <td>
                                ${ss.total_rank}
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
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
</div>

