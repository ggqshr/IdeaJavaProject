<%@page language="java" import="java.util.*"
        contentType="text/html; charset=utf-8" %>
<%

    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>

    <base href="<%=basePath%>">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>Welcome - Ace Admin</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>

    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/font-awesome.min.css"/>

    <!-- text fonts -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/ace-fonts.css"/>

    <!-- ace styles -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/ace.min.css" id="main-ace-style"/>

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="/assets/css/ace-part2.min.css"/>
    <![endif]-->
    <link rel="stylesheet" href="/assets/css/ace-skins.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/ace-rtl.min.css"/>

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="/assets/css/ace-ie.min.css"/>
    <![endif]-->

    <!-- ace settings handler -->
    <script src="/assets/js/ace-extra.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/assets/DataTables-1.10.15/media/css/jquery.dataTables.css">

    <!-- jQuery -->
    <script type="text/javascript" charset="utf8" src="/assets/DataTables-1.10.15/media/js/jquery.js"></script>

    <!-- DataTables -->
    <script type="text/javascript" charset="utf8" src="/assets/DataTables-1.10.15/media/js/jquery.dataTables.js"></script>
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <script src="/assets/js/jquery-ui.js"></script>
    <!--[if lte IE 8]>
    <script src="/assets/js/html5shiv.js"></script>
    <script src="/assets/js/respond.min.js"></script>
    <![endif]-->
    <%
        String username = (String) request.getSession(false).getAttribute("username");
        if (username == null) {
//            response.sendRedirect("/login.jsp");
        }
    %>
</head>

<body class="no-skin">
<!-- #section:basics/navbar.layout -->
<div id="navbar" class="navbar navbar-default">
    <script type="text/javascript">
        try {
            ace.settings.check('navbar', 'fixed')
        } catch (e) {
        }
    </script>

    <div class="navbar-container" id="navbar-container">
        <!-- #section:basics/sidebar.mobile.toggle -->
        <button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler">
            <span class="sr-only">Toggle sidebar</span>

            <span class="icon-bar"></span>

            <span class="icon-bar"></span>

            <span class="icon-bar"></span>
        </button>

        <!-- /section:basics/sidebar.mobile.toggle -->
        <div class="navbar-header pull-left">
            <!-- #section:basics/navbar.layout.brand -->
            <a href="/ajax.jsp" class="navbar-brand">
                <small>
                    <i class="fa fa-leaf"></i>
                    青岛旅游数据分析系统
                </small>
            </a>

            <!-- /section:basics/navbar.layout.brand -->

            <!-- #section:basics/navbar.toggle -->

            <!-- /section:basics/navbar.toggle -->
        </div>

        <!-- #section:basics/navbar.dropdown -->
        <%--<div class="navbar-buttons navbar-header pull-right" role="navigation">--%>
            <%--<ul class="nav ace-nav">--%>
                <%--&lt;%&ndash;<li class="grey">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<a data-toggle="dropdown" class="dropdown-toggle" href="#">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<i class="ace-icon fa fa-tasks"></i>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<span class="badge badge-grey">4</span>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</a>&ndash;%&gt;--%>

                <%--&lt;%&ndash;<ul class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<li class="dropdown-header">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<i class="ace-icon fa fa-check"></i>&ndash;%&gt;--%>
                <%--&lt;%&ndash;4 Tasks to complete&ndash;%&gt;--%>
                <%--&lt;%&ndash;</li>&ndash;%&gt;--%>

                <%--&lt;%&ndash;<li>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<a href="#">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<div class="clearfix">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<span class="pull-left">Software Update</span>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<span class="pull-right">65%</span>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</div>&ndash;%&gt;--%>

                <%--&lt;%&ndash;<div class="progress progress-mini">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<div style="width:65%" class="progress-bar"></div>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</a>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</li>&ndash;%&gt;--%>

                <%--&lt;%&ndash;<li>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<a href="#">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<div class="clearfix">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<span class="pull-left">Hardware Upgrade</span>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<span class="pull-right">35%</span>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</div>&ndash;%&gt;--%>

                <%--&lt;%&ndash;<div class="progress progress-mini">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<div style="width:35%" class="progress-bar progress-bar-danger"></div>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</a>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</li>&ndash;%&gt;--%>

                <%--&lt;%&ndash;<li>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<a href="#">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<div class="clearfix">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<span class="pull-left">Unit Testing</span>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<span class="pull-right">15%</span>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</div>&ndash;%&gt;--%>

                <%--&lt;%&ndash;<div class="progress progress-mini">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<div style="width:15%" class="progress-bar progress-bar-warning"></div>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</a>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</li>&ndash;%&gt;--%>

                <%--&lt;%&ndash;<li>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<a href="#">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<div class="clearfix">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<span class="pull-left">Bug Fixes</span>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<span class="pull-right">90%</span>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</div>&ndash;%&gt;--%>

                <%--&lt;%&ndash;<div class="progress progress-mini progress-striped active">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<div style="width:90%" class="progress-bar progress-bar-success"></div>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</a>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</li>&ndash;%&gt;--%>

                <%--&lt;%&ndash;<li class="dropdown-footer">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<a href="#">&ndash;%&gt;--%>
                <%--&lt;%&ndash;See tasks with details&ndash;%&gt;--%>
                <%--&lt;%&ndash;<i class="ace-icon fa fa-arrow-right"></i>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</a>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</li>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</ul>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</li>&ndash;%&gt;--%>

                <%--&lt;%&ndash;<li class="purple">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<a data-toggle="dropdown" class="dropdown-toggle" href="#">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<i class="ace-icon fa fa-bell icon-animated-bell"></i>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<span class="badge badge-important">8</span>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</a>&ndash;%&gt;--%>

                <%--&lt;%&ndash;<ul class="dropdown-menu-right dropdown-navbar navbar-pink dropdown-menu dropdown-caret dropdown-close">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<li class="dropdown-header">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<i class="ace-icon fa fa-exclamation-triangle"></i>&ndash;%&gt;--%>
                <%--&lt;%&ndash;8 Notifications&ndash;%&gt;--%>
                <%--&lt;%&ndash;</li>&ndash;%&gt;--%>

                <%--&lt;%&ndash;<li>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<a href="#">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<div class="clearfix">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<span class="pull-left">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<i class="btn btn-xs no-hover btn-pink fa fa-comment"></i>&ndash;%&gt;--%>
                <%--&lt;%&ndash;New Comments&ndash;%&gt;--%>
                <%--&lt;%&ndash;</span>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<span class="pull-right badge badge-info">+12</span>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</a>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</li>&ndash;%&gt;--%>

                <%--&lt;%&ndash;<li>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<a href="#">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<i class="btn btn-xs btn-primary fa fa-user"></i>&ndash;%&gt;--%>
                <%--&lt;%&ndash;Bob just signed up as an editor ...&ndash;%&gt;--%>
                <%--&lt;%&ndash;</a>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</li>&ndash;%&gt;--%>

                <%--&lt;%&ndash;<li>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<a href="#">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<div class="clearfix">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<span class="pull-left">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<i class="btn btn-xs no-hover btn-success fa fa-shopping-cart"></i>&ndash;%&gt;--%>
                <%--&lt;%&ndash;New Orders&ndash;%&gt;--%>
                <%--&lt;%&ndash;</span>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<span class="pull-right badge badge-success">+8</span>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</a>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</li>&ndash;%&gt;--%>

                <%--&lt;%&ndash;<li>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<a href="#">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<div class="clearfix">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<span class="pull-left">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<i class="btn btn-xs no-hover btn-info fa fa-twitter"></i>&ndash;%&gt;--%>
                <%--&lt;%&ndash;Followers&ndash;%&gt;--%>
                <%--&lt;%&ndash;</span>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<span class="pull-right badge badge-info">+11</span>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</a>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</li>&ndash;%&gt;--%>

                <%--&lt;%&ndash;<li class="dropdown-footer">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<a href="#">&ndash;%&gt;--%>
                <%--&lt;%&ndash;See all notifications&ndash;%&gt;--%>
                <%--&lt;%&ndash;<i class="ace-icon fa fa-arrow-right"></i>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</a>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</li>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</ul>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</li>&ndash;%&gt;--%>

                <%--&lt;%&ndash;<li class="green">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<a data-toggle="dropdown" class="dropdown-toggle" href="#">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<i class="ace-icon fa fa-envelope icon-animated-vertical"></i>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<span class="badge badge-success">5</span>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</a>&ndash;%&gt;--%>

                <%--&lt;%&ndash;<ul class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<li class="dropdown-header">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<i class="ace-icon fa fa-envelope-o"></i>&ndash;%&gt;--%>
                <%--&lt;%&ndash;5 Messages&ndash;%&gt;--%>
                <%--&lt;%&ndash;</li>&ndash;%&gt;--%>

                <%--&lt;%&ndash;<li class="dropdown-content">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<ul class="dropdown-menu dropdown-navbar">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<li>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<a href="#">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<img src="assets/avatars/avatar.png" class="msg-photo" alt="Alex's Avatar"/>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<span class="msg-body">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<span class="msg-title">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<span class="blue">Alex:</span>&ndash;%&gt;--%>
                <%--&lt;%&ndash;Ciao sociis natoque penatibus et auctor ...&ndash;%&gt;--%>
                <%--&lt;%&ndash;</span>&ndash;%&gt;--%>

                <%--&lt;%&ndash;<span class="msg-time">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<i class="ace-icon fa fa-clock-o"></i>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<span>a moment ago</span>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</span>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</span>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</a>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</li>&ndash;%&gt;--%>

                <%--&lt;%&ndash;<li>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<a href="#">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<img src="assets/avatars/avatar3.png" class="msg-photo"&ndash;%&gt;--%>
                <%--&lt;%&ndash;alt="Susan's Avatar"/>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<span class="msg-body">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<span class="msg-title">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<span class="blue">Susan:</span>&ndash;%&gt;--%>
                <%--&lt;%&ndash;Vestibulum id ligula porta felis euismod ...&ndash;%&gt;--%>
                <%--&lt;%&ndash;</span>&ndash;%&gt;--%>

                <%--&lt;%&ndash;<span class="msg-time">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<i class="ace-icon fa fa-clock-o"></i>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<span>20 minutes ago</span>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</span>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</span>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</a>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</li>&ndash;%&gt;--%>

                <%--&lt;%&ndash;<li>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<a href="#">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<img src="assets/avatars/avatar4.png" class="msg-photo" alt="Bob's Avatar"/>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<span class="msg-body">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<span class="msg-title">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<span class="blue">Bob:</span>&ndash;%&gt;--%>
                <%--&lt;%&ndash;Nullam quis risus eget urna mollis ornare ...&ndash;%&gt;--%>
                <%--&lt;%&ndash;</span>&ndash;%&gt;--%>

                <%--&lt;%&ndash;<span class="msg-time">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<i class="ace-icon fa fa-clock-o"></i>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<span>3:15 pm</span>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</span>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</span>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</a>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</li>&ndash;%&gt;--%>

                <%--&lt;%&ndash;<li>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<a href="#">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<img src="assets/avatars/avatar2.png" class="msg-photo" alt="Kate's Avatar"/>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<span class="msg-body">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<span class="msg-title">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<span class="blue">Kate:</span>&ndash;%&gt;--%>
                <%--&lt;%&ndash;Ciao sociis natoque eget urna mollis ornare ...&ndash;%&gt;--%>
                <%--&lt;%&ndash;</span>&ndash;%&gt;--%>

                <%--&lt;%&ndash;<span class="msg-time">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<i class="ace-icon fa fa-clock-o"></i>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<span>1:33 pm</span>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</span>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</span>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</a>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</li>&ndash;%&gt;--%>

                <%--&lt;%&ndash;<li>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<a href="#">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<img src="assets/avatars/avatar5.png" class="msg-photo" alt="Fred's Avatar"/>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<span class="msg-body">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<span class="msg-title">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<span class="blue">Fred:</span>&ndash;%&gt;--%>
                <%--&lt;%&ndash;Vestibulum id penatibus et auctor  ...&ndash;%&gt;--%>
                <%--&lt;%&ndash;</span>&ndash;%&gt;--%>

                <%--&lt;%&ndash;<span class="msg-time">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<i class="ace-icon fa fa-clock-o"></i>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<span>10:09 am</span>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</span>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</span>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</a>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</li>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</ul>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</li>&ndash;%&gt;--%>

                <%--&lt;%&ndash;<li class="dropdown-footer">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<a href="inbox.html">&ndash;%&gt;--%>
                <%--&lt;%&ndash;See all messages&ndash;%&gt;--%>
                <%--&lt;%&ndash;<i class="ace-icon fa fa-arrow-right"></i>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</a>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</li>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</ul>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</li>&ndash;%&gt;--%>

                <%--<!-- #section:basics/navbar.user_menu -->--%>
                <%--&lt;%&ndash;<li class="light-blue">&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<a data-toggle="dropdown" href="#" class="dropdown-toggle">&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<img class="nav-user-photo" src="/assets/avatars/user.jpg" alt="Jason's Photo"/>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<span class="user-info">&ndash;%&gt;--%>
									<%--&lt;%&ndash;<small>Welcome,</small>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<%=username%>&ndash;%&gt;--%>
								<%--&lt;%&ndash;</span>&ndash;%&gt;--%>

                        <%--&lt;%&ndash;<i class="ace-icon fa fa-caret-down"></i>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;</a>&ndash;%&gt;--%>

                    <%--&lt;%&ndash;<ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">&ndash;%&gt;--%>
                        <%--&lt;%&ndash;&lt;%&ndash;<li>&ndash;%&gt;&ndash;%&gt;--%>
                        <%--&lt;%&ndash;&lt;%&ndash;<a href="#">&ndash;%&gt;&ndash;%&gt;--%>
                        <%--&lt;%&ndash;&lt;%&ndash;<i class="ace-icon fa fa-cog"></i>&ndash;%&gt;&ndash;%&gt;--%>
                        <%--&lt;%&ndash;&lt;%&ndash;Settings&ndash;%&gt;&ndash;%&gt;--%>
                        <%--&lt;%&ndash;&lt;%&ndash;</a>&ndash;%&gt;&ndash;%&gt;--%>
                        <%--&lt;%&ndash;&lt;%&ndash;</li>&ndash;%&gt;&ndash;%&gt;--%>

                        <%--&lt;%&ndash;&lt;%&ndash;<li>&ndash;%&gt;&ndash;%&gt;--%>
                        <%--&lt;%&ndash;&lt;%&ndash;<a href="profile.html">&ndash;%&gt;&ndash;%&gt;--%>
                        <%--&lt;%&ndash;&lt;%&ndash;<i class="ace-icon fa fa-user"></i>&ndash;%&gt;&ndash;%&gt;--%>
                        <%--&lt;%&ndash;&lt;%&ndash;Profile&ndash;%&gt;&ndash;%&gt;--%>
                        <%--&lt;%&ndash;&lt;%&ndash;</a>&ndash;%&gt;&ndash;%&gt;--%>
                        <%--&lt;%&ndash;&lt;%&ndash;</li>&ndash;%&gt;&ndash;%&gt;--%>

                        <%--&lt;%&ndash;&lt;%&ndash;<li class="divider"></li>&ndash;%&gt;&ndash;%&gt;--%>

                        <%--&lt;%&ndash;<li>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<a href="/login.jsp">&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<i class="ace-icon fa fa-power-off"></i>&ndash;%&gt;--%>
                                <%--&lt;%&ndash;Logout&ndash;%&gt;--%>
                            <%--&lt;%&ndash;</a>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;</li>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;</ul>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</li>&ndash;%&gt;--%>

                <%--<!-- /section:basics/navbar.user_menu -->--%>
            <%--</ul>--%>
        </div>

        <!-- /section:basics/navbar.dropdown -->
    </div><!-- /.navbar-container -->
</div>

<!-- /section:basics/navbar.layout -->
<div class="main-container" id="main-container">
    <script type="text/javascript">
        try {
            ace.settings.check('main-container', 'fixed')
        } catch (e) {
        }
    </script>

    <!-- #section:basics/sidebar -->
    <div id="sidebar" class="sidebar                  responsive">
        <script type="text/javascript">
            try {
                ace.settings.check('sidebar', 'fixed')
            } catch (e) {
            }
        </script>

        <%--<div class="sidebar-shortcuts" id="sidebar-shortcuts">--%>
        <%--<img src="/assets/img/back.jpg" height="40px" width="190">--%>
        <%--&lt;%&ndash;<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">&ndash;%&gt;--%>
        <%--&lt;%&ndash;<button class="btn btn-success">&ndash;%&gt;--%>
        <%--&lt;%&ndash;<i class="ace-icon fa fa-signal"></i>&ndash;%&gt;--%>
        <%--&lt;%&ndash;</button>&ndash;%&gt;--%>

        <%--&lt;%&ndash;<button class="btn btn-info">&ndash;%&gt;--%>
        <%--&lt;%&ndash;<i class="ace-icon fa fa-pencil"></i>&ndash;%&gt;--%>
        <%--&lt;%&ndash;</button>&ndash;%&gt;--%>

        <%--&lt;%&ndash;<!-- #section:basics/sidebar.layout.shortcuts -->&ndash;%&gt;--%>
        <%--&lt;%&ndash;<button class="btn btn-warning">&ndash;%&gt;--%>
        <%--&lt;%&ndash;<i class="ace-icon fa fa-users"></i>&ndash;%&gt;--%>
        <%--&lt;%&ndash;</button>&ndash;%&gt;--%>

        <%--&lt;%&ndash;<button class="btn btn-danger">&ndash;%&gt;--%>
        <%--&lt;%&ndash;<i class="ace-icon fa fa-cogs"></i>&ndash;%&gt;--%>
        <%--&lt;%&ndash;</button>&ndash;%&gt;--%>

        <%--&lt;%&ndash;<!-- /section:basics/sidebar.layout.shortcuts -->&ndash;%&gt;--%>
        <%--&lt;%&ndash;</div>&ndash;%&gt;--%>

        <%--&lt;%&ndash;<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">&ndash;%&gt;--%>
        <%--&lt;%&ndash;<span class="btn btn-success"></span>&ndash;%&gt;--%>

        <%--&lt;%&ndash;<span class="btn btn-info"></span>&ndash;%&gt;--%>

        <%--&lt;%&ndash;<span class="btn btn-warning"></span>&ndash;%&gt;--%>

        <%--&lt;%&ndash;<span class="btn btn-danger"></span>&ndash;%&gt;--%>
        <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
        <%--</div><!-- /.sidebar-shortcuts -->--%>

        <ul class="nav nav-list">
            <li class="">
                <a data-url="page/testc" href="ajax.jsp#page/testc">
                    <i class="menu-icon fa fa-tachometer"></i>
                    <span class="menu-text"> 前64名排名 </span>
                </a>

                <b class="arrow"></b>
            </li>
            <li class="">
                <a data-url="page/allPlace" href="ajax.jsp#page/allPlace">
                    <i class="menu-icon fa fa-tachometer"></i>
                    <span class="menu-text"> 青岛景点整体数据统计 </span>
                </a>

                <b class="arrow"></b>
            </li>





            <li class="">
                <a data-url="page/searchPlace" href="ajax.jsp#page/searchPlace">
                    <i class="menu-icon fa fa-list-alt"></i>
                    <span class="menu-text"> 搜索景点 </span>
                </a>

                <b class="arrow"></b>
            </li>

            <%--<li class="">--%>
            <%--<a data-url="page/calendar" href="ajax.jsp#page/calendar">--%>
            <%--<i class="menu-icon fa fa-calendar"></i>--%>

            <%--<span class="menu-text">--%>
            <%--Calendar--%>

            <%--<!-- #section:basics/sidebar.layout.badge -->--%>
            <%--<span class="badge badge-transparent tooltip-error" title="2 Important Events">--%>
            <%--<i class="ace-icon fa fa-exclamation-triangle red bigger-130"></i>--%>
            <%--</span>--%>

            <%--<!-- /section:basics/sidebar.layout.badge -->--%>
            <%--</span>--%>
            <%--</a>--%>

            <%--<b class="arrow"></b>--%>
            <%--</li>--%>

            <%--<li class="">--%>
            <%--<a data-url="page/gallery" href="ajax.jsp#page/gallery">--%>
            <%--<i class="menu-icon fa fa-picture-o"></i>--%>
            <%--<span class="menu-text"> Gallery </span>--%>
            <%--</a>--%>

            <%--<b class="arrow"></b>--%>
            <%--</li>--%>

            <%--<li class="">--%>
            <%--<a href="#" class="dropdown-toggle">--%>
            <%--<i class="menu-icon fa fa-tag"></i>--%>
            <%--<span class="menu-text"> More Pages </span>--%>

            <%--<b class="arrow fa fa-angle-down"></b>--%>
            <%--</a>--%>

            <%--<b class="arrow"></b>--%>

            <%--<ul class="submenu">--%>
            <%--<li class="">--%>
            <%--<a data-url="page/profile" href="ajax.jsp#page/profile">--%>
            <%--<i class="menu-icon fa fa-caret-right"></i>--%>
            <%--User Profile--%>
            <%--</a>--%>

            <%--<b class="arrow"></b>--%>
            <%--</li>--%>

            <%--<li class="">--%>
            <%--<a data-url="page/inbox" href="ajax.jsp#page/inbox">--%>
            <%--<i class="menu-icon fa fa-caret-right"></i>--%>
            <%--Inbox--%>
            <%--</a>--%>

            <%--<b class="arrow"></b>--%>
            <%--</li>--%>

            <%--<li class="">--%>
            <%--<a data-url="page/pricing" href="ajax.jsp#page/pricing">--%>
            <%--<i class="menu-icon fa fa-caret-right"></i>--%>
            <%--Pricing Tables--%>
            <%--</a>--%>

            <%--<b class="arrow"></b>--%>
            <%--</li>--%>

            <%--<li class="">--%>
            <%--<a data-url="page/invoice" href="ajax.jsp#page/invoice">--%>
            <%--<i class="menu-icon fa fa-caret-right"></i>--%>
            <%--Invoice--%>
            <%--</a>--%>

            <%--<b class="arrow"></b>--%>
            <%--</li>--%>

            <%--<li class="">--%>
            <%--<a data-url="page/timeline" href="ajax.jsp#page/timeline">--%>
            <%--<i class="menu-icon fa fa-caret-right"></i>--%>
            <%--Timeline--%>
            <%--</a>--%>

            <%--<b class="arrow"></b>--%>
            <%--</li>--%>

            <%--<li class="">--%>
            <%--<a data-url="page/email" href="ajax.jsp#page/email">--%>
            <%--<i class="menu-icon fa fa-caret-right"></i>--%>
            <%--Email Templates--%>
            <%--</a>--%>

            <%--<b class="arrow"></b>--%>
            <%--</li>--%>

            <%--<li class="">--%>
            <%--<a data-url="page/login" href="login.jsp">--%>
            <%--<i class="menu-icon fa fa-caret-right"></i>--%>
            <%--Login &amp; Register--%>
            <%--</a>--%>

            <%--<b class="arrow"></b>--%>
            <%--</li>--%>
            <%--</ul>--%>
            <%--</li>--%>

            <%--<li class="">--%>
            <%--<a href="#" class="dropdown-toggle">--%>
            <%--<i class="menu-icon fa fa-file-o"></i>--%>

            <%--<span class="menu-text">--%>
            <%--Other Pages--%>

            <%--<!-- #section:basics/sidebar.layout.badge -->--%>
            <%--<span class="badge badge-primary">5</span>--%>

            <%--<!-- /section:basics/sidebar.layout.badge -->--%>
            <%--</span>--%>

            <%--<b class="arrow fa fa-angle-down"></b>--%>
            <%--</a>--%>

            <%--<b class="arrow"></b>--%>

            <%--<ul class="submenu">--%>
            <%--<li class="">--%>
            <%--<a data-url="page/faq" href="ajax.jsp#page/faq">--%>
            <%--<i class="menu-icon fa fa-caret-right"></i>--%>
            <%--FAQ--%>
            <%--</a>--%>

            <%--<b class="arrow"></b>--%>
            <%--</li>--%>

            <%--<li class="">--%>
            <%--<a data-url="page/error-404" href="ajax.jsp#page/error-404">--%>
            <%--<i class="menu-icon fa fa-caret-right"></i>--%>
            <%--Error 404--%>
            <%--</a>--%>

            <%--<b class="arrow"></b>--%>
            <%--</li>--%>

            <%--<li class="">--%>
            <%--<a data-url="page/error-500" href="ajax.jsp#page/error-500">--%>
            <%--<i class="menu-icon fa fa-caret-right"></i>--%>
            <%--Error 500--%>
            <%--</a>--%>

            <%--<b class="arrow"></b>--%>
            <%--</li>--%>

            <%--<li class="">--%>
            <%--<a data-url="page/grid" href="ajax.jsp#page/grid">--%>
            <%--<i class="menu-icon fa fa-caret-right"></i>--%>
            <%--Grid--%>
            <%--</a>--%>

            <%--<b class="arrow"></b>--%>
            <%--</li>--%>

            <%--<li class="">--%>
            <%--<a data-url="page/blank" href="ajax.jsp#page/blank">--%>
            <%--<i class="menu-icon fa fa-caret-right"></i>--%>
            <%--Blank Page--%>
            <%--</a>--%>

            <%--<b class="arrow"></b>--%>
            <%--</li>--%>
            <%--</ul>--%>
            <%--</li>--%>
        </ul><!-- /.nav-list -->

        <!-- #section:basics/sidebar.layout.minimize -->
        <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
            <i class="ace-icon fa fa-angle-double-left" data-icon1="ace-icon fa fa-angle-double-left"
               data-icon2="ace-icon fa fa-angle-double-right"></i>
        </div>

        <!-- /section:basics/sidebar.layout.minimize -->
        <script type="text/javascript">
            try {
                ace.settings.check('sidebar', 'collapsed')
            } catch (e) {
            }
        </script>
    </div>

    <!-- /section:basics/sidebar -->
    <div class="main-content">
        <!-- #section:basics/content.breadcrumbs -->
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try {
                    ace.settings.check('breadcrumbs', 'fixed')
                } catch (e) {
                }
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="ajax.jsp#page/testc">排名</a>
                </li>
            </ul><!-- /.breadcrumb -->

            <!-- #section:basics/content.searchbox -->
            <%--<div class="nav-search" id="nav-search">--%>
            <%--<form class="form-search">--%>
            <%--<span class="input-icon">--%>
            <%--<input type="text" placeholder="Search ..." class="nav-search-input"--%>
            <%--id="nav-search-input" autocomplete="off"/>--%>
            <%--<i class="ace-icon fa fa-search nav-search-icon"></i>--%>
            <%--</span>--%>
            <%--</form>--%>
            <%--</div><!-- /.nav-search -->--%>

            <!-- /section:basics/content.searchbox -->
        </div>

        <!-- /section:basics/content.breadcrumbs -->
        <div class="page-content">
            <!-- #section:settings.box -->
            <%--<div class="ace-settings-container" id="ace-settings-container">--%>
                <%--<div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">--%>
                    <%--<i class="ace-icon fa fa-cog bigger-150"></i>--%>
                <%--</div>--%>

                <%--<div class="ace-settings-box clearfix" id="ace-settings-box">--%>
                    <%--<div class="pull-left width-50">--%>
                        <%--<!-- #section:settings.skins -->--%>
                        <%--<div class="ace-settings-item">--%>
                            <%--<div class="pull-left">--%>
                                <%--<select id="skin-colorpicker" class="hide">--%>
                                    <%--<option data-skin="no-skin" value="#438EB9">#438EB9</option>--%>
                                    <%--<option data-skin="skin-1" value="#222A2D">#222A2D</option>--%>
                                    <%--<option data-skin="skin-2" value="#C6487E">#C6487E</option>--%>
                                    <%--<option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>--%>
                                <%--</select>--%>
                            <%--</div>--%>
                            <%--<span>&nbsp; Choose Skin</span>--%>
                        <%--</div>--%>

                        <%--<!-- /section:settings.skins -->--%>

                        <%--<!-- #section:settings.navbar -->--%>
                        <%--<div class="ace-settings-item">--%>
                            <%--<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-navbar"/>--%>
                            <%--<label class="lbl" for="ace-settings-navbar"> Fixed Navbar</label>--%>
                        <%--</div>--%>

                        <%--<!-- /section:settings.navbar -->--%>

                        <%--<!-- #section:settings.sidebar -->--%>
                        <%--<div class="ace-settings-item">--%>
                            <%--<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-sidebar"/>--%>
                            <%--<label class="lbl" for="ace-settings-sidebar"> Fixed Sidebar</label>--%>
                        <%--</div>--%>

                        <%--<!-- /section:settings.sidebar -->--%>

                        <%--<!-- #section:settings.breadcrumbs -->--%>
                        <%--<div class="ace-settings-item">--%>
                            <%--<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-breadcrumbs"/>--%>
                            <%--<label class="lbl" for="ace-settings-breadcrumbs"> Fixed Breadcrumbs</label>--%>
                        <%--</div>--%>

                        <%--<!-- /section:settings.breadcrumbs -->--%>

                        <%--<!-- #section:settings.rtl -->--%>
                        <%--<div class="ace-settings-item">--%>
                            <%--<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-rtl"/>--%>
                            <%--<label class="lbl" for="ace-settings-rtl"> Right To Left (rtl)</label>--%>
                        <%--</div>--%>

                        <%--<!-- /section:settings.rtl -->--%>

                        <%--<!-- #section:settings.container -->--%>
                        <%--<div class="ace-settings-item">--%>
                            <%--<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-add-container"/>--%>
                            <%--<label class="lbl" for="ace-settings-add-container">--%>
                                <%--Inside--%>
                                <%--<b>.container</b>--%>
                            <%--</label>--%>
                        <%--</div>--%>

                        <%--<!-- /section:settings.container -->--%>
                    <%--</div><!-- /.pull-left -->--%>

                    <%--<div class="pull-left width-50">--%>
                        <%--<!-- #section:basics/sidebar.options -->--%>
                        <%--<div class="ace-settings-item">--%>
                            <%--<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-hover"/>--%>
                            <%--<label class="lbl" for="ace-settings-hover"> Submenu on Hover</label>--%>
                        <%--</div>--%>

                        <%--<div class="ace-settings-item">--%>
                            <%--<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-compact"/>--%>
                            <%--<label class="lbl" for="ace-settings-compact"> Compact Sidebar</label>--%>
                        <%--</div>--%>

                        <%--<div class="ace-settings-item">--%>
                            <%--<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-highlight"/>--%>
                            <%--<label class="lbl" for="ace-settings-highlight"> Alt. Active Item</label>--%>
                        <%--</div>--%>

                        <%--<!-- /section:basics/sidebar.options -->--%>
                    <%--</div><!-- /.pull-left -->--%>
                <%--</div><!-- /.ace-settings-box -->--%>
            <%--</div><!-- /.ace-settings-container -->--%>

            <!-- /section:settings.box -->
            <div class="page-content-area" id="Content">
                <!-- ajax content goes here -->
            </div><!-- /.page-content-area -->
        </div><!-- /.page-content -->
    </div><!-- /.main-content -->

    <div class="footer">
        <div class="footer-inner">
            <!-- #section:basics/footer -->
            <div class="footer-content">
						<span class="bigger-120">
							<span class="blue bolder">GGQ</span>
							Application &copy; 2016-2017
						</span>0

                &nbsp; &nbsp;
                <%--<span class="action-buttons">--%>
                <%--<a href="#">--%>
                <%--<i class="ace-icon fa fa-twitter-square light-blue bigger-150"></i>--%>
                <%--</a>--%>

                <%--<a href="#">--%>
                <%--<i class="ace-icon fa fa-facebook-square text-primary bigger-150"></i>--%>
                <%--</a>--%>

                <%--<a href="#">--%>
                <%--<i class="ace-icon fa fa-rss-square orange bigger-150"></i>--%>
                <%--</a>--%>
                <%--</span>--%>
            </div>

            <!-- /section:basics/footer -->
        </div>
    </div>

    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
    </a>
</div><!-- /.main-container -->

<!-- basic scripts -->

<!--[if !IE]> -->
<script type="text/javascript">
    window.jQuery || document.write("<script src='/assets/js/jquery.min.js'>" + "<" + "/script>");
</script>

<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
    window.jQuery || document.write("<script src='/assets/js/jquery1x.min.js'>" + "<" + "/script>");
</script>
<![endif]-->
<script type="text/javascript">
    if ('ontouchstart' in document.documentElement) document.write("<script src='/assets/js/jquery.mobile.custom.min.js'>" + "<" + "/script>");
</script>
<script src="/assets/js/bootstrap.min.js"></script>

<!-- ace scripts -->
<script src="/assets/js/ace-elements.min.js"></script>
<script src="/assets/js/ace.min.js"></script>
<script type="text/javascript">
    //Load content via ajax
    jQuery(function ($) {
        if ('enable_ajax_content' in ace) {
            var options = {
                content_url: function (url) {
                    //this is for Ace demo only, you should change it
                    //please refer to documentation for more info
                    if (!url.match(/^page\//)) return false;
                    var path = document.location.pathname;
                    if (!path.match(/html/))
                        path = '/ajax.jsp';
                    //for Ace HTML demo version, convert ajax.jsp#page/gallery to > gallery.html and load it
                    if (path.match(/ajax\.jsp/)) /*alert(path.replace(/ajax\.jsp/, url.replace(/^page\//, '') + '.jsp'));*/console.log(path.replace(/ajax\.jsp/, url.replace(/^page\//, '') ));return path.replace(/ajax\.jsp/, url.replace(/^page\//, '') );

                    //for Ace PHP demo version convert "page/board" to "?page=board" and load it
                    //return path + "?" + url.replace(/\//, "=");
                },
                default_url: 'page/testc'//default url
            }
            ace.enable_ajax_content($, options)
        }

    })
</script>
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
</body>
</html>
