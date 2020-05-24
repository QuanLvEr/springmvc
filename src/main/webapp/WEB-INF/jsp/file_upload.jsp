<%--
  Created by IntelliJ IDEA.
  User: 王宁
  Date: 2020/4/27
  Time: 19:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>同步上传</title>
</head>
<body>
<form action="/upload" method="post" enctype="multipart/form-data">
    <input type="file" name="file" value="请选择文件"/> <br>
    <input type="submit" value="上传">
</form>
</body>
</html>
