<%@page import="java.util.List"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>File List</title>
</head>
<body>
	<ul>
		<%
		    String context = application.getContextPath();
		    String folder = "download";

		    List<String> files = (List<String>) application.getAttribute("files");
		    if (files == null) {
		        return;
		    }

		    for (String file : files) {
		%>
			<li><a href="<%=context%>/<%=folder%>/<%=file%>"> <%=file%>
			</a></li>


		<%
		    }
		%>
	</ul>

</body>
</html>