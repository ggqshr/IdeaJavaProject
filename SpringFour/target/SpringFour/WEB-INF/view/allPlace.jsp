<%--
  Created by IntelliJ IDEA.
  User: ggq18
  Date: 2018/5/28
  Time: 9:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="page-header">
    <h1>青岛景点数据</h1>
</div>
<div class="row">
    <div class="col-xs-12">
        <!-- /.widget-box -->
        <div class="col-xs-6">
            <div class="widget-box transparent">
                <div class="widget-header widget-header-flat">
                    <h4 class="widget-title lighter">
                        <i class="ace-icon fa fa-signal"></i>
                        正面关键词生成的词云
                    </h4>

                    <div class="widget-toolbar">
                        <a href="#" data-action="collapse">
                            <i class="ace-icon fa fa-chevron-up"></i>
                        </a>
                    </div>
                </div>

                <div class="widget-body">
                    <div class="widget-main">
                        <a href="/image/青岛正面.png" data-rel='colorbox' id="aaaa1">
                            <img onerror="javascript:this.src='/image/logoError.png';" width='500' height='400'
                                 alt='400x500' src="/image/青岛正面.png"/>
                        </a>
                    </div><!-- /.widget-main -->
                </div><!-- /.widget-body -->
            </div>


        </div>
        <div class="col-xs-6">
            <div class="widget-box transparent">
                <div class="widget-header widget-header-flat">
                    <h4 class="widget-title lighter">
                        <i class="ace-icon fa fa-signal"></i>
                        负面关键词生成的词云
                    </h4>
                    <div class="widget-toolbar">
                        <a href="#" data-action="collapse">
                            <i class="ace-icon fa fa-chevron-up"></i>
                        </a>
                    </div>
                </div>
                <div class="widget-body">
                    <div class="widget-main">
                        <a href="/image/青岛负面.png" data-rel='colorbox' id="aaaa2">
                            <img onerror="javascript:this.src='/image/logoError.png';" width='500' height='400' src="/image/青岛负面.png"/>
                        </a>
                    </div><!-- /.widget-main -->
                </div><!-- /.widget-body -->
            </div>
        </div>
    </div>
    <div class="col-lg-12">
        <div class="col-lg-6">
            <div class="widget-box transparent">
                <div class="widget-header widget-header-flat">
                    <h4 class="widget-title lighter">
                        <i class="ace-icon fa fa-signal"></i>
                        人数的年度变化
                    </h4>

                    <div class="widget-toolbar">
                        <a href="#" data-action="collapse">
                            <i class="ace-icon fa fa-chevron-up"></i>
                        </a>
                    </div>
                </div>

                <div class="widget-body">
                    <div class="widget-main">
                        <div id="yeartable" style="height: 400px;width: 600px"></div>
                    </div><!-- /.widget-main -->
                </div><!-- /.widget-body -->
            </div><!-- /.widget-box -->
        </div>
        <div class="col-lg-6">
            <div class="widget-box transparent">
                <div class="widget-header widget-header-flat">
                    <h4 class="widget-title lighter">
                        <i class="ace-icon fa fa-signal"></i>
                        人数的年度变化
                    </h4>

                    <div class="widget-toolbar">
                        <a href="#" data-action="collapse">
                            <i class="ace-icon fa fa-chevron-up"></i>
                        </a>
                    </div>
                </div>

                <div class="widget-body">
                    <div class="widget-main">
                        <div id="sesstable" style="height: 400px;width: 600px"></div>
                    </div><!-- /.widget-main -->
                </div><!-- /.widget-body -->
            </div><!-- /.widget-box -->
        </div>
    </div>
    <div class="col-lg-12">
        <div class="col-lg-6">
            <div class="widget-box transparent">
                <div class="widget-header widget-header-flat">
                    <h4 class="widget-title lighter">
                        <i class="ace-icon fa fa-signal"></i>
                        人数的年度变化
                    </h4>

                    <div class="widget-toolbar">
                        <a href="#" data-action="collapse">
                            <i class="ace-icon fa fa-chevron-up"></i>
                        </a>
                    </div>
                </div>

                <div class="widget-body">
                    <div class="widget-main">
                        <div id="typerate" style="height: 400px;width: 600px"></div>
                    </div><!-- /.widget-main -->
                </div><!-- /.widget-body -->
            </div><!-- /.widget-box -->
        </div>
    </div>
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
    <script src="/js/echarts.js"></script>
    <script src="/dist/echarts-gl.js"></script>
    <script src="/dist/echarts-all.js"></script>
    <script src="/assets/js/jquery.colorbox-min.js"></script>
    <%--人数年度变化--%>
    <script type="text/javascript">
        // 路径配置
        require.config({
            paths: {
                echarts: '/dist'
            }
        });
        require(
            [
                'echarts',
                'echarts/chart/bar',// 使用柱状图就加载bar模块，按需加载
                'echarts/chart/line'
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('yeartable'));
                var dataStr = "${yeardata}".split("\t");
                var realData = [];
                for (i = 0; i < dataStr.length; i++) {
                    realData[i] = parseInt(dataStr[i]);
                }
                var option = {
                    title: {
                        text: '人数年度变化',
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
                        data: ['人数']
                    },
                    toolbox: {
                        show: true,
                        feature: {
                            dataZoom: {
                                yAxisIndex: 'none'
                            },
                            dataView: {readOnly: false},
                            magicType: {type: ['line', 'bar']},
                            restore: {},
                            saveAsImage: {}
                        }
                    },
                    xAxis: {
                        type: 'category',
                        boundaryGap: false,
                        data: "${yy}".split("\t")
                    },
                    yAxis: {
                        type: 'value',
                        axisLabel: {
                            formatter: '{value}'
                        }
                    },
                    series: [
                        {
                            name: '人数',
                            type: 'line',
                            data: realData,
                            markPoint: {
                                data: [
                                    {type: 'max', name: '最大值'},
                                    {type: 'min', name: '最小值'}
                                ]
                            },
                            markLine: {
                                data: [
                                    {type: 'average', name: '平均值'}
                                ]
                            }
                        },
                    ]
                };


                // 为echarts对象加载数据
                myChart.setOption(option);

            }
        );
    </script>
    <%--人数季度变化--%>
    <script type="text/javascript">
        // 路径配置
        require.config({
            paths: {
                echarts: '/dist'
            }
        });
        require(
            [
                'echarts',
                'echarts/chart/bar',// 使用柱状图就加载bar模块，按需加载
                'echarts/chart/line'
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('sesstable'));
                var dataStr = "${sessdata}".split("\t");
                var realData = [];
                for (i = 0; i < dataStr.length; i++) {
                    realData[i] = parseInt(dataStr[i]);
                }
                var option = {
                    title: {
                        text: '景点人数季度变化',
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
                        data: ['人数']
                    },
                    toolbox: {
                        show: true,
                        feature: {
                            dataZoom: {
                                yAxisIndex: 'none'
                            },
                            dataView: {readOnly: false},
                            magicType: {type: ['line', 'bar']},
                            restore: {},
                            saveAsImage: {}
                        }
                    },
                    xAxis: {
                        type: 'category',
                        boundaryGap: false,
                        data: "${sess}".split("\t")
                    },
                    yAxis: {
                        type: 'value',
                        axisLabel: {
                            formatter: '{value}'
                        }
                    },
                    series: [
                        {
                            name: '人数',
                            type: 'line',
                            data: realData,
                            markPoint: {
                                data: [
                                    {type: 'max', name: '最大值'},
                                    {type: 'min', name: '最小值'}
                                ]
                            },
                            markLine: {
                                data: [
                                    {type: 'average', name: '平均值'}
                                ]
                            }
                        },
                    ]
                };


                // 为echarts对象加载数据
                myChart.setOption(option);

            }
        );
    </script>
    <%--出行类型--%>
    <script type="text/javascript">
        // 路径配置
        require.config({
            paths: {
                echarts: '/dist'
            }
        });
        require(
            [
                'echarts',
                'echarts/chart/bar',// 使用柱状图就加载bar模块，按需加载
                'echarts/chart/line',
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('typerate'));
                var dataStr = "${typerate}".split("\t");
                var realData = [];
                for (i = 0; i < dataStr.length; i++) {
                    realData[i] = parseFloat(dataStr[i].substr(0, 4));
                }
                var option = {
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'cross',
                            crossStyle: {
                                color: '#999'
                            }
                        }
                    },
                    toolbox: {
                        feature: {}
                    },
                    legend: {
                        data: ['出行方式选择人数', '好评率']
                    },
                    xAxis: [
                        {
                            type: 'category',
                            data: "${type}".split("\t"),
                            axisPointer: {
                                type: 'shadow'
                            }
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value',
                            name: '人数',
                            axisLabel: {
                                formatter: '{value}人'
                            }
                        },
                        {
                            type: 'value',
                            name: '好评率',
                            axisLabel: {
                                formatter: '{value}%'
                            }
                        }
                    ],
                    series: [
                        {
                            name: '出行方式选择人数',
                            type: 'bar',
                            data: "${typenum}".split("\t")
                        },
                        {
                            name: '好评率',
                            type: 'line',
                            yAxisIndex: 1,
                            data: realData,
                            markPoint: {
                                data: [
                                    {type: 'max', name: '最大值'},
                                    {type: 'min', name: '最小值'}
                                ]
                            }
                        }

                    ]
                };


                // 为echarts对象加载数据
                myChart.setOption(option);

            }
        );
    </script>
    <%--加载图片--%>
    <script type="text/javascript">
        $(document).ready(function () {
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
            $("#aaaa1").colorbox(colorbox_params);
            $("#aaaa2").colorbox(colorbox_params);
            $("#cboxLoadingGraphic").html("<i class='ace-icon fa fa-spinner orange'></i>");
        });

    </script>
</div>
