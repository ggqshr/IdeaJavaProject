<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: ggq
  Date: 2017/5/29
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Calculator</title>
</head>
<body>
<jsp:useBean id="calculator" class="sss.Calculator"/>
<jsp:setProperty name="calculator" property="*"/>
<% if (calculator.validate()) {
%>
<font color="green">运算结果：
    <jsp:getProperty name="calculator" property="firstNumber"/>
    <jsp:getProperty name="calculator" property="operator"/>
    <jsp:getProperty name="calculator" property="secondNumber"/>
    = <%=calculator.calculator() %>
</font>
<%
    } else {
        HashMap<String, String> errors = calculator.getErrors();
        pageContext.setAttribute("errors", errors);
    }
%>
<form action="" method="post">
    第一个运算符:<input type="text" name="firstNumber"/>
    <font color="red">${errors.firstNumber}</font><br>
    运算符:<select name="operator" style="margin-left: 100px">
    <option value="+">+</option>
    <option value="-">-</option>
    <option value="*">*</option>
    <option value="/">/</option>
</select><br>
    第二个运算符：<input type="text" name="secondNumber"/>
    <font color="red">${errors.secondNumber}</font><br>
    <input type="submit"/>
</form>
</body>
</html>
