<%--
  Created by IntelliJ IDEA.
  User: ggq
  Date: 2017/6/27
  Time: 18:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="page-header">
    <h1>
        查看所有订单
    </h1>
    <a data-url="page/index" href="ajax.jsp#page/index" id="urlA"></a>
    <a id="inboxTurn" href="#modal-table" role="button" class="green" data-toggle="modal"></a>
</div>
<div class="row" id="main">

</div>
<div id="modal-table" class="modal fade" tabindex="-1" style="display: none;" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header no-padding">
                <div class="table-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        <span class="white">×</span>
                    </button>
                    订单详细信息
                </div>
            </div>

            <div id="inboxContent" class="modal-body no-padding">
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

            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<script src="/assets/js/tj.js"></script>
<script type="text/javascript">
    window.onload = getBusiness();

</script>
<script type="text/javascript">
    //用来停止显示加载条
    var scripts = [null, "../assets/js/jquery-ui.custom.min.js", "../assets/js/jquery.ui.touch-punch.min.js", "../assets/js/date-time/moment.min.js", "../assets/js/fullcalendar.min.js", "../assets/js/bootbox.min.js", null]
    ace.load_ajax_scripts(scripts, function () {
    });
</script>