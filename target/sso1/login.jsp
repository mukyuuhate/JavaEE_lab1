<!DOCTYPE html>
<html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <title>登陆</title>
</head>
<style>
    .content{
        font-size: 14px;
        margin: 0;
        padding: 10px;
        text-align: center;
    }
    .item{
        margin: 10px;
    }
    .item input{
        width: 200px;
    }
</style>
<body>
<div class="content">
    <div class="item">
        <a href="/">返回首页</a>
    </div>
    <div class="item">
        <label for="userName">用户名：</label><input type="text" name="userName" id="userName" placeholder="请输入用户名" value="admin" autocomplete="off"/>
    </div>
    <div class="item">
        <label for="password">密码：</label><input type="password" name="password" id="password" placeholder="请输入密码" value="123" autocomplete="off"/>
    </div>
    <div class="item">
        <button type="submit" id="login">登录</button>
    </div><br>
    <%=request.getAttribute("msg")==null?"":request.getAttribute("msg")%><br>
</div>
<script type="text/javascript" src="../jquery-2.1.0.js" ></script>
<script>
    $('#login').on('click',function (e) {
        var userName=$.trim($('#userName').val());
        var password=$.trim($('#password').val());
        //$('body').append('wrong');
        $.ajax({
            type:"post",
            url:"",
            data:{
                userName:userName,
                password:password
            },
            dataType:'json',
            success:function (resp) {
                alert(resp.msg);
                if(resp.success){
                    location.href="<%=request.getAttribute("preurl")%>";
                    //$('body').append(JSON.stringify(resp.msg));
                }else{$('body').append('wrong');}

            }
        });


    })

</script>
</body>
</html>
