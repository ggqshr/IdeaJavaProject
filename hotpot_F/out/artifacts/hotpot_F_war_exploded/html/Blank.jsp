<%--
  Created by IntelliJ IDEA.
  User: ggq
  Date: 2017/6/16
  Time: 19:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="page-header">
    <h1>
        blank
    </h1>
</div>
<div class="row">
    <table id="sample-table-1" class="table table-striped table-bordered table-hover">
        <thead>
        <tr>
            <th>订单编号</th>
            <th>客人编号</th>
            <th class="hidden-320">订单类型</th>

            <th>
                订单内容
            </th>
            <th class="hidden-320">订单金额</th>
            <th class="hidden-320">
                <i class="ace-icon fa fa-clock-o bigger-110 hidden-480"></i>
                订单时间
            </th>
            <th class="hidden-320">操作</th>
        </tr>
        </thead>

        <tbody>
        <tr class="">
            <td><a href="#">ace.com</a></td>
            <td>$45</td>
            <td class="hidden-480">3,330</td>
            <td>Feb 12</td>
            <td class="hidden-480">
                <span class="label label-sm label-warning">Expiring</span>
            </td>
            <td class="hidden-480">
                <div class="hidden-sm hidden-xs btn-group">
                    <button class="btn btn-xs btn-danger">
                        <i class="ace-icon fa fa-trash-o bigger-120"></i>
                    </button>
                </div>
            </td>

        </tr>

        <tr class="">

            <td>
                <a href="#">base.com</a>
            </td>
            <td>$35</td>
            <td class="hidden-480">2,595</td>
            <td>Feb 18</td>

            <td class="hidden-480">
                <span class="label label-sm label-success">Registered</span>
            </td>

            <td>

            </td>
            <td class="hidden-480">
                <div class="hidden-sm hidden-xs btn-group">
                    <button class="btn btn-xs btn-danger">
                        <i class="ace-icon fa fa-trash-o bigger-120"></i>
                    </button>
                </div>
            </td>
        </tr>

        <tr class="">

            <td>
                <a href="#">max.com</a>
            </td>
            <td>$60</td>
            <td class="hidden-480">4,400</td>
            <td>Mar 11</td>

            <td class="hidden-480">
                <span class="label label-sm label-warning">Expiring</span>
            </td>

            <td>


            </td>
            <td class="hidden-480">
                <div class="hidden-sm hidden-xs btn-group">
                    <button class="btn btn-xs btn-danger">
                        <i class="ace-icon fa fa-trash-o bigger-120"></i>
                    </button>
                </div>
            </td>
        </tr>

        <tr class="">

            <td>
                <a href="#">best.com</a>
            </td>
            <td>$75</td>
            <td class="hidden-480">6,500</td>
            <td>Apr 03</td>

            <td class="hidden-480">
                <span class="label label-sm label-inverse arrowed-in">Flagged</span>
            </td>

            <td>


            </td>
            <td class="hidden-480">
                <div class="hidden-sm hidden-xs btn-group">

                    <button class="btn btn-xs btn-danger">
                        <i class="ace-icon fa fa-trash-o bigger-120"></i>
                    </button>

                </div>
            </td>
        </tr>

        <tr class="">

            <td>
                <a href="#">pro.com</a>
            </td>
            <td>$55</td>
            <td class="hidden-480">4,250</td>
            <td>Jan 21</td>

            <td class="hidden-480">
                <span class="label label-sm label-success">Registered</span>
            </td>

            <td>

            </td>
            <td class="hidden-480">
                <div class="hidden-sm hidden-xs btn-group">

                    <button class="btn btn-xs btn-danger">
                        <i class="ace-icon fa fa-trash-o bigger-120"></i>
                    </button>

                </div>
            </td>
        </tr>
        <tr></tr>
        </tbody>
    </table>
    <a href="#modal-table" role="button" class="green" data-toggle="modal"> Table Inside a Modal Box </a>
</div>
<div id="modal-table" class="modal fade" tabindex="-1" style="display: none;" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header no-padding">
                <div class="table-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        <span class="white">×</span>
                    </button>
                    Results for "Latest Registered Domains
                </div>
            </div>

            <div class="modal-body no-padding">
                <table class="table table-striped table-bordered table-hover no-margin-bottom no-border-top">
                    <thead>
                    <tr>
                        <th>Domain</th>
                        <th>Price</th>
                        <th>Clicks</th>

                        <th>
                            <i class="ace-icon fa fa-clock-o bigger-110"></i>
                            Update
                        </th>
                    </tr>
                    </thead>

                    <tbody>
                    <tr>
                        <td>
                            <a href="#">ace.com</a>
                        </td>
                        <td>$45</td>
                        <td>3,330</td>
                        <td>Feb 12</td>
                    </tr>

                    <tr>
                        <td>
                            <a href="#">base.com</a>
                        </td>
                        <td>$35</td>
                        <td>2,595</td>
                        <td>Feb 18</td>
                    </tr>

                    <tr>
                        <td>
                            <a href="#">max.com</a>
                        </td>
                        <td>$60</td>
                        <td>4,400</td>
                        <td>Mar 11</td>
                    </tr>

                    <tr>
                        <td>
                            <a href="#">best.com</a>
                        </td>
                        <td>$75</td>
                        <td>6,500</td>
                        <td>Apr 03</td>
                    </tr>

                    <tr>
                        <td>
                            <a href="#">pro.com</a>
                        </td>
                        <td>$55</td>
                        <td>4,250</td>
                        <td>Jan 21</td>
                    </tr>
                    </tbody>
                </table>
            </div>

            <div class="modal-footer no-margin-top">
                <button class="btn btn-sm btn-danger pull-left" data-dismiss="modal">
                    <i class="ace-icon fa fa-times"></i>
                    Close
                </button>

                <ul class="pagination pull-right no-margin">
                    <li class="prev disabled">
                        <a href="#">
                            <i class="ace-icon fa fa-angle-double-left"></i>
                        </a>
                    </li>

                    <li class="active">
                        <a href="#">1</a>
                    </li>

                    <li>
                        <a href="#">2</a>
                    </li>

                    <li>
                        <a href="#">3</a>
                    </li>

                    <li class="next">
                        <a href="#">
                            <i class="ace-icon fa fa-angle-double-right"></i>
                        </a>
                    </li>
                </ul>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<script type="text/javascript">
    function test() {
        $.ajax(
            {
                url: "Servlet.Combo",
                type: "GET",
                success: function (data) {
                    $("#urlA").attr("href", "ajax.jsp#page/QueryAllCombo");
                    document.getElementById("urlA").click();
                }
            }
        )
    }
</script>
<script type="text/javascript">
    //用来停止显示加载条
    var scripts = [null, "../assets/js/jquery-ui.custom.min.js", "../assets/js/jquery.ui.touch-punch.min.js", "../assets/js/date-time/moment.min.js", "../assets/js/fullcalendar.min.js", "../assets/js/bootbox.min.js", null]
    ace.load_ajax_scripts(scripts, function () {
    });
</script>

