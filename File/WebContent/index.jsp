<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 注意：
	可以只写一个servlet页面。其中多写几个方法，用doGet调用
	 -->

<!-- 简单的下载 -->
	<a href="FileServlet?option=download&fileName=index.jsp">下载D:/hello.jsp</a>
	
<!-- 简单的上传 -->
<%--上传表单必须是post请求方式,必须有enctype="multipart/form-data" --%>
<form action="FileServlet?option=upload" method="post" enctype="multipart/form-data">
	<input type="file" value="请选择文件"/>
	<input type="submit" value="上传"/>
</form>
</body>
</html>