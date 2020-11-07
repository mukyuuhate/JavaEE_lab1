<html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<body>
<h2>Hello World!</h2>
<a href="servlet/getInfo1" id="app1">应用1</a>
<a href="servlet/getInfo2" id="app2">应用2</a>
<a href="servlet/login" id="login">登录界面</a>
</body>
<script type="text/javascript" src="../jquery-2.1.0.js" ></script>
<%--<script>--%>
<%--    $("#app1").on('click',function(e){--%>
<%--        $.ajax({--%>
<%--            type:"get",--%>
<%--            dataType:"json",--%>
<%--            url:"/servlet/getInfo1",--%>
<%--            async : true,--%>
<%--            headers:{--%>
<%--                token:localStorage.getItem("token")//将token放到请求头中--%>
<%--            },--%>
<%--            success:function(resp){--%>
<%--                if(resp.success){--%>
<%--                    $('body').append(JSON.stringify(resp.data));--%>
<%--                }--%>
<%--            }--%>
<%--        });--%>

<%--    });--%>
<%--    $("#app2").on('click',function(e){--%>
<%--        $.ajax({--%>
<%--            type:"get",--%>
<%--            dataType:"json",--%>
<%--            url:"/servlet/getInfo2",--%>
<%--            headers:{--%>
<%--                token:localStorage.getItem("token")//将token放到请求头中--%>
<%--            },--%>
<%--        });--%>
<%--    });--%>

<%--</script>--%>
</html>
