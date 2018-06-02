/**
 * Created by ggq on 2017/6/12.
 */
function showAnHide() {
    $("#main").show();
    $("#result").hide();
}
function tj() {
    window.alert('11111');
}
function close() {
    alert("@@@@");
    $("#combo").hide();
    $("#main").show();
}
function getFoodCheck(number,type,name) {
    if(type==="combo")
    {
        $("#inboxContent").html("<br><br><br><span style=' float:left; padding:5px 250px;'>套餐名称</span><br><hr>" +
            "<br><span style=' float:left; padding:5px 250px;'>"+name+"</span><br><br><br>");
        document.getElementById("inboxTurn").click();
        return ;
    }
    $.ajax(
        {
            url: "/Servlet.FoodCheck?id="+number,
            type: "GET",
            async: false,
            success: function (data) {
                if(data.length===2)
                {
                    $("#inboxContent").html("<br><br><br><span style=' float:left; padding:5px 250px;'>无订单信息</span><br><br><br>");
                    document.getElementById("inboxTurn").click();
                    return ;
                }
                var html = "<table id='sample-table-1' class='table table-striped table-bordered table-hover'>" +
                    "<thead>" +
                    "<tr>" +
                    "<th>食物名称</th>" +
                    "<th>客人编号</th>" +
                    "<th class='hidden-320'>食物数量</th>" +
                    "<th>" +
                    "食物价格" +
                    "</th>" +
                    "</thead><tbody>";
                var list = eval(data);
                for (var i = 0; i < list.length; i++) {
                    var ll = list[i];
                    // var number = ll.businessNumber.toString();
                    html += "<tr class=''>" +
                        "<td>" + ll.foodName + "</td>" +
                        "<td>"+ll.customerNumber+"</td>" +
                        "<td class='hidden-480'>" + ll.foodNum+ "</td>" +
                        "<td>￥" + ll.checkPrice + "</td>";
                }
                html += "</tbody></table>";
                $("#inboxContent").html(html);
                document.getElementById("inboxTurn").click();
            }
        }
    );
}
function getSpBusiness(cust) {
    $.ajax(
        {
            url: "/Servlet.QueryAllBusiness?method=cust&id="+cust,
            type: "GET",
            async: false,
            success: function (data) {
                if(data.length===2)
                {
                    $.gritter.add({
                        text: "<div class='text-center' style='font-size: 30px;color: #ffffff'><span>无此人的订单</span></div>",
                        time: 1900,
                        speed: 100,
                        class_name: 'gritter-info gritter-center '
                    });
                    return ;
                }
                var html = "<table id='sample-table-1' class='table table-striped table-bordered table-hover'>" +
                    "<thead>" +
                    "<tr>" +
                    "<th>订单编号</th>" +
                    "<th>客人编号</th>" +
                    "<th class='hidden-320'>订单类型</th>" +
                    "<th>" +
                    "订单内容" +
                    "</th>" +
                    "<th class='hidden-320'>订单金额</th>" +
                    " <th class='hidden-320'>" +
                    "<i class='ace-icon fa fa-clock-o bigger-110 hidden-480'></i>" +
                    "订单时间" +
                    "</th>" +
                    "<th class='hidden-320'>操作</th>" +
                    " </tr>" +
                    "</thead><tbody>";
                var list = eval(data);
                for (var i = 0; i < list.length; i++) {
                    var ll = list[i];
                    var number = ll.businessNumber.toString();
                    html += "<tr class=''>" +
                        "<td>" + number + "</td>" +
                        "<td>" + ll.customerNumber + "</td>" +
                        "<td class='hidden-480'>" + ll.businessType + "</td>" +
                        "<td><button class='btn btn-xs btn-info' type='button' " +
                        "onclick='getFoodCheck(&quot;" + number + "&quot;,&quot;" + ll.businessType + "&quot;,&quot;" + ll.comboName + "&quot;)'>" +
                        "<i class='ace-icon fa fa-pencil bigger-120'></i>" +
                        "点击查看" +
                        "</button> </td>" +
                        "<td>￥" + ll.businessMoney + "</td>" +
                        "<td>" + ll.businessDate + "</td>" +
                        "<td class='hidden-480'>" +
                        "<div class='hidden-sm hidden-xs btn-group'>" +
                        "<button class='btn btn-xs btn-danger' type='button' onclick='deleteBusiness(&quot;" + number + "&quot;)'>" +
                        "<i class='ace-icon fa fa-trash-o bigger-120'></i>" +
                        "</button>" +
                        "</div>" +
                        "</td>";
                }
                html += "</tbody></table>" +
                    "<div class='center'><button class='btn' type='button' onclick='showAnHide()'>" +
                    "<i class='ace-icon fa fa-undo bigger-110'></i>返回</button></div>";
                $("#result").html(html);
                $("#main").hide();
                $("#result").show();
            }
        }
    );
}
function getBusinessAscust() {
        var customerNumber = document.getElementById("custNumber").value;
        if(customerNumber.length===0)
        {
            $.gritter.add({
                text: "<div class='text-center' style='font-size: 30px;color: #ffffff'><span>输入不能为空</span></div>",
                time: 1900,
                speed: 100,
                class_name: 'gritter-info gritter-center '
            });
            return ;
        }
        getSpBusiness(customerNumber);

}
function getCustomer() {
    var customerNumber = document.getElementById("custNumber").value;
    if(customerNumber.length===0)
    {
        $.gritter.add({
            text: "<div class='text-center' style='font-size: 30px;color: #ffffff'><span>输入不能为空</span></div>",
            time: 1900,
            speed: 100,
            class_name: 'gritter-info gritter-center '
        });
        return ;
    }
    $.ajax(
        {
            url: "/Servlet.Customer?method=get&id="+customerNumber,
            type: "GET",
            async: false,
            success:function (data) {
                if(data==="NULL")
                {
                    $.gritter.add({
                        text: "<div class='text-center' style='font-size: 30px;color: #ffffff'><span>查无此人</span></div>",
                        time: 1900,
                        speed: 100,
                        class_name: 'gritter-info gritter-center '
                    });
                    return ;
                }
                $("#main").hide();
                $("#result").show();
            }
        }
    )
}
function changeP() {
    var newpwd = document.getElementById("newpwd").value;
    var customerNumber = document.getElementById("custNumber").value;
    console.log(newpwd);
    if(newpwd.length===0)
    {
        $.gritter.add({
            text: "<div class='text-center' style='font-size: 30px;color: #ffffff'><span>输入不能为空</span></div>",
            time: 1900,
            speed: 100,
            class_name: 'gritter-info gritter-center '
        });
        return ;
    }
    $.ajax(
        {
            url: "/Servlet.Customer?method=updata&id="+customerNumber+"&newpwd="+newpwd,
            type: "GET",
            async: false,
            success:function (data) {
                    $.gritter.add({
                        text: "<div class='text-center' style='font-size: 30px;color: #ffffff'><span>修改成功</span></div>",
                        time: 1900,
                        speed: 100,
                        class_name: 'gritter-info gritter-center '
                    });
                $("#main").show();
                $("#result").hide();
            }
        }
    )
}
function getBusiness() {
    $.ajax(
        {
            url: "/Servlet.QueryAllBusiness?method=get",
            type: "GET",
            async: false,
            success: function (data) {
                var html = "<table id='sample-table-1' class='table table-striped table-bordered table-hover'>" +
                    "<thead>" +
                    "<tr>" +
                    "<th>订单编号</th>" +
                    "<th>客人编号</th>" +
                    "<th class='hidden-320'>订单类型</th>" +
                    "<th>" +
                    "订单内容" +
                    "</th>" +
                    "<th class='hidden-320'>订单金额</th>" +
                    " <th class='hidden-320'>" +
                    "<i class='ace-icon fa fa-clock-o bigger-110 hidden-480'></i>" +
                    "订单时间" +
                    "</th>" +
                    " </tr>" +
                    "</thead><tbody>";
                var list = eval(data);
                for (var i = 0; i < list.length; i++) {
                    var ll = list[i];
                    var number = ll.businessNumber.toString();
                    html += "<tr class=''>" +
                        "<td>" + number + "</td>" +
                        "<td>" + ll.customerNumber + "</td>" +
                        "<td class='hidden-480'>" + ll.businessType + "</td>" +
                        "<td><button class='btn btn-xs btn-info' type='button' " +
                        "onclick='getFoodCheck(&quot;" + number + "&quot;,&quot;" + ll.businessType + "&quot;,&quot;" + ll.comboName + "&quot;)'>" +
                        "<i class='ace-icon fa fa-pencil bigger-120'></i>" +
                        "点击查看" +
                        "</button> </td>" +
                        "<td>￥" + ll.businessMoney + "</td>" +
                        "<td>" + ll.businessDate + "</td>" ;
                }
                html += "</tbody></table>";
                $("#main").html(html);
            }
        }
    );
}