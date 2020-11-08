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
<%=request.getAttribute("msg")%><br>
<%="从token中获取的id为"+request.getAttribute("userNameFromToken")%><br>
<%="此token还有"+request.getAttribute("timeRemainForToken")+"失效"%><br>
<button type="button" id="dopost">执行post方法</button><br/>
<button type="button" id="loginout">登出</button><br/>
<a  href="/">首页</a><br/>
<script type="text/javascript" src="../jquery-2.1.0.js" ></script>
<script src="../jquery.cookie-1.4.1.min.js" type="text/javascript"></script>
<script>

    $("#loginout").on("click",function(){
        $.cookie('token', '', { expires: -1 });
        location.href="./login";
    });
    $("#dopost").on("click",function(){
        $.ajax({
            type:"post",
            url:"",
            dataType:'json',
            success:function (resp) {
                alert(resp.msg);
                $('body').append(resp.msg);
            }
        })
    });
</script>
</body>
</html>
