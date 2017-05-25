<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>  
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<c:out value="${ctx }" />/assets/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript">

var ctx = '<c:out value="${ctx }" />';

	$(function() {
		alert(ctx);
	});
	
 	function dosubmit(){
 		
 		var name = $("#name").val();
 		var age = $("#age").val();
 		
		$.ajax({
			   type: "POST",
			   url: ctx + "/myCat/tosaveMyCat",
			   data: "name="+name+"&age="+age,
			   success: function(msg){
			     alert( "Data Saved: " + msg.message );
			   }
			});
	} 
	
</script>
</head>
<body style="background-color: #ccc;">
<c:out value="${test }"></c:out>
<from action="${path }/myCat/tosaveMyCat" method="post">
	<input type="text" name="name" id = "name"/>
	<input type="text" name="age" id="age" />
	<input type="submit" value="提交" onclick="dosubmit()"/> 
</from>

</body>
</html>