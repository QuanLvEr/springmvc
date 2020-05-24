<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>异步上传</title>
    <script type="application/javascript" src="/statics/js/jquery.js"></script>
    <script type="application/javascript" src="/statics/js/jquery-form.js"></script>
    <script type="application/javascript" src="/statics/js/jquery.serializejson.js"></script>
</head>
<body>
<h2>ajax异步添加同时上传图片</h2>
<form id="empForm" action="/upload_ajax" method="post" enctype="multipart/form-data">
    <table border="1" width="700px" cellspacing="1" cellpadding="1">
        <tr>
            <td>个人图像</td>
            <td>
                <input type="file" name="pic" id="pic"/>
                <!--存放上传到服务器的图片的url-->
                <input type="hidden" name="picFile" id="picFile" />
            </td>
            <td>
                <img id="img" width="192px" height="108px">
            </td>
        </tr>
        <tr>
            <td colspan="3">
                <input type="submit" name="btnAdd" id="btnAdd" value="添加员工"/>
            </td>
        </tr>
    </table>
</form>

<script type="application/javascript">
    $(function () {
        $("#pic").change(function () {
            //上传的请求
            $("#empForm").ajaxSubmit({
                url:'/upload_ajax',
                type:'post',
                dateType:'json',
                success:function (data) {
                    //回填数据到隐藏域
                    $("#picFile").val(data);
                    //回显图片
                    $("#img").attr("src","/upload/"+JSON.parse(data));
                }
            });
        });
    });
</script>
</body>
</html>
