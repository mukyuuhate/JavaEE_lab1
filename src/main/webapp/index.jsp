<html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<body>
<h2>Hello World!</h2>
<a href="javascript:void(0);" id="app1">应用1</a>
<a href="javascript:void(0);" id="app2">应用2</a>
</body>
<script type="text/javascript" src="../jquery-2.1.0.js" ></script>
<script>
    $("#app1").on('click',function(e){
        $.ajax({
            type:"get",
            dataType:"json",
            url:"/servlet/getInfo1",
            headers:{
                token:localStorage.getItem("token")//将token放到请求头中
            },
        });
    });
    $("#app2").on('click',function(e){
        $.ajax({
            type:"get",
            dataType:"json",
            url:"/servlet/getInfo2",
            headers:{
                token:localStorage.getItem("token")//将token放到请求头中
            },
        });
    });

</script>
</html>
