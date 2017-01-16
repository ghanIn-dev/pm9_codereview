<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title><s:message code="common.pageTitle"/></title>
    <link href="css/sb-admin/bootstrap.min.css" rel="stylesheet">
    <link href="css/sb-admin/metisMenu.min.css" rel="stylesheet">
    <link href="css/sb-admin/sb-admin-2.css" rel="stylesheet">
    <link href="css/sb-admin/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script src="js/jquery-2.2.3.min.js"></script>
    <script src="css/sb-admin/bootstrap.min.js"></script>
    <script src="css/sb-admin/metisMenu.min.js"></script>
    <script src="css/sb-admin/sb-admin-2.js"></script>
	<script src="js/project9.js"></script>    
<script>

function clickDir(depth, parent)  {
	
	
}

function clickFile() {
	
}
</script>    
</head>

<body>

    <div id="wrapper">

		<jsp:include page="../../common/navigation.jsp" />
		
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header"><i class="fa fa-gear fa-fw"></i> <s:message code="common.codecd"/></h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            
            <!-- /.row -->
            <div class="row">
				<div class="panel panel-default">
	                    <div class="panel-body">
	                    	<div class="row">
	                            <label class="col-lg-2"><s:message code="common.classno"/></label>
	                            <div class="col-lg-1"><c:out value="${testInfo.classno}"/></div>
	                        </div>
	                    	<div class="row">
	                            <label class="col-lg-2"><s:message code="common.codecd"/></label>
	                            <div class="col-lg-1"><c:out value="${testInfo.testcd}"/></div>
	                        </div>
	                    	<div class="row">
	                            <label class="col-lg-2"><s:message code="common.codenm"/></label>
	                            <div class="col-lg-5"><c:out value="${testInfo.testnm}"/></div>
	                        </div>
	                    </div>
	                    
	<tr>
   	 <td>첨부</td> 
    	<td>
       	 <c:forEach var="listview" items="${listview}" varStatus="status">    
            <a href="fileDownload?filename=<c:out value="${listview.filename}"/>&downname=<c:out value="${listview.realname}"/>"> 
            <c:out value="${listview.filename}"/></a> <c:out value="${listview.size2String()}"/><br/>
        </c:forEach>                    
   	 </td> 
	</tr>
	
<table class="table table-bordered">
	<thead>
        <tr>
       
        <th>FileList</th>
            
        </tr>
    </thead>
    

	<tbody>
       		 <c:forEach var="fileStr" items="${fileStr}" varStatus="status">     		
        		<tr>
        			<c:if test="${fileStr.depth eq depth and fileStr.parentId eq parentId}">
						<c:choose>
						    <c:when test="${fileStr.fileflag eq '1'}">
			        			 <td class="text-center"> <i class="fa fa-folder"></i>
			        			 <a href="adTestRead?depth=<c:out value="${fileStr.depth+1}"/>&parentId=<c:out value="${fileStr.id}"/>&classno=<c:out value="${testInfo.classno}"/>&testcd=<c:out value="${testInfo.testcd}"/>">${fileStr.name}</a>
			        			 </td>
						    </c:when>				
						    <c:when test="${fileStr.fileflag eq '0'}">
			        			<td class="text-center"> <i class="fa fa-file-text-o"></i>
			        			<a href="adTestFileView?path=<c:out value="${fileStr.filePath}"/>">${fileStr.name}</a>
			        			</td>
						    </c:when>
						</c:choose>
					</c:if>	
			   </tr>    
        	</c:forEach>                       	
	</tbody>
</table>	


	                    
                </div>
                <button class="btn btn-outline btn-primary" onclick="fn_moveToURL('adTestList')" ><s:message code="common.btnList"/></button>
                <button class="btn btn-outline btn-primary" onclick="fn_moveToURL('adTestDelete?classno=<c:out value="${testInfo.classno}"/>&testcd=<c:out value="${testInfo.testcd}"/>', '<s:message code="common.btnDelete"/>')" ><s:message code="common.btnDelete"/></button>
                <button class="btn btn-outline btn-primary" onclick="fn_moveToURL('adTestForm?classno=<c:out value="${testInfo.classno}"/>&testcd=<c:out value="${testInfo.testcd}"/>')" ><s:message code="common.btnUpdate"/></button>
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->
</body>

</html>



