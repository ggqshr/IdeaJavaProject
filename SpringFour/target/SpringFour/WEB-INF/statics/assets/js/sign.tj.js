/**
 * Created by ggq on 2017/6/15.
 */
function checkAndSignup() {
    var Number = $("#userNumber").val();
    var userName = $("#signuserName").val();
    var Password = $("#signpassword").val();
    var Passwordag = $("#signpasswordag").val();
    var Phone = $("#Phone").val();
    if(Number===""||userName===""||Password===""||Passwordag===""||Phone==="")
    {
        showGGQ("必填项不能为空");
        return ;
    }
    if(!Number.match("^[a-zA-z][a-zA-Z0-9_]{2,9}$"))
    {
        // alert("账号格式不对，必须为数字和字母的组合，以字母开头");
        $.gritter.add({
            title: '账号格式不对',
            text: '必须为数字和字母的组合，以字母开头，长度为3-10位',
            time:1900,
            speed:100,
            class_name: 'gritter-info gritter-center gritter-light'
        });
        document.getElementById("userNumber").value="";
        $("#userNumber").focus();
        return ;
    }
    else if(!Phone.match("^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$"))
    {
        $.gritter.add({
            title: '电话号码格式不对',
            text: '电话号码格式错误，请重新输入',
            time:1900,
            speed:100,
            class_name: 'gritter-info gritter-center gritter-light'
        });
        document.getElementById("Phone").value="";
        $("#Phone").focus();
        return ;
    }
    else if(!(Password===Passwordag))
    {
        $.gritter.add({
            title: '两次输入的密码不相同',
            time:1900,
            speed:100,
            class_name: 'gritter-info gritter-center gritter-light'
        });
        $("#signpasswordag").focus();
        return ;
    }
    $.ajax(
        {
            type: "POST",
            url: "/Servlet.SignUp",
            data: $("#signForm").serialize(),
            async: false,
            dataType: "text",
            error: function (req) {
                alert('太多人注册了过会再来吧');
            },
            success: function (data) {
                if (data === "exist")
                    $.gritter.add({
                        title: '啊哦，相同账号的用户已经存在',
                        time:1900,
                        speed:100,
                        class_name: 'gritter-info gritter-center gritter-light'
                    });
                else
                    location.href = "/login.jsp";
            }
        }
    );
}