<!DOCTYPE html>
<html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <title></title>
</head>
<body>
我是页面web1<br>
<%=request.getAttribute("msg")%><br>
<%="从token中获取的id为"+request.getAttribute("userNameFromToken")%><br>
<%="此token还有"+request.getAttribute("timeRemainForToken")+"失效"%><br>
<button type="button" id="loginout">登出</button><br/>
<script type="text/javascript" src="../jquery-2.1.0.js" ></script>
<script src="../jquery.cookie-1.4.1.min.js" type="text/javascript"></script>
<script>

    $("#loginout").on("click",function(){
        $.cookie('token', '', { expires: -1 });
        location.href="./login";
    });
</script>
</body>
</html>
