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
        修改用户密码
    </h1>
    <script src="/assets/js/jquery.gritter.min.js"></script>
    <script src="/assets/js/tj.js"></script>
    <a data-url="page/index" href="ajax.jsp#page/index" id="urlA"></a>
    <a id="inboxTurn" href="#modal-table" role="button" class="green" data-toggle="modal"></a>
</div>
<div class="row" id="main">
    <div class="center">
        <br><br><br> <br> <br> <br>

        <span class="input-icon">
        <i class="ace-icon fa fa-search nav-search-icon"></i>
        <input style="width:500px;" id="custNumber" class="form-control " autocomplete="off" type="text"
               placeholder="请输入用户账号">
    </span>
        <button type="button" onclick="getCustomer()" class="btn btn-info">查找</button>
    </div>
</div>
<div id="result" class="row" style="display: none">
    <div class="col-xs-12 center-block">
        <form class="form-horizontal" role="form" id="foodForm">
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right">
                    用户新密码
                </label>
                <div class="col-sm-8">
                    <input id="newpwd" type="text" name="pwd" placeholder="请输入新密码" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            <div class="clearfix form-actions">
                <div class="col-md-offset-3 col-md-9">
                    <button class="btn btn-info" type="button" onclick="changeP();">
                        <i class="ace-icon fa fa-check bigger-110"></i>
                        提交
                    </button>

                    &nbsp; &nbsp; &nbsp;
                    <button id="goback" class="btn" type="button" onclick="showAnHide()">
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
</script>