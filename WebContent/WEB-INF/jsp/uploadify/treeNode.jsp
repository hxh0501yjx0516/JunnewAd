<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
   	<c:forEach var="childNode" items="${parentNode}" varStatus="s">
   			<c:if test="${fn:length(childNode.subDir)<=0}">
   			<!-- 文件-->
			<li><a href="javascript:void(0);">${childNode.name}</a></li>
   			</c:if> 
   		<c:if test="${fn:length(childNode.subDir)>0}">
   		<li>
   		<a href="uploadify.do?action=dir&filePath=${childNode.path}"  target="ajax" rel="rightContent">${childNode.name}</a>
   		<ul>
   		<c:set var="parentNode" value="${childNode.subDir}" scope="request"/>
    	<c:import url="treeNode.jsp"/>
   		</ul>
		</li>
   		</c:if>
   		
   	</c:forEach>
