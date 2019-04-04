<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<div class="pages">
				<span>显示</span>
				<select name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				
					<c:if test="${numPerPage==20}"><option value="20" selected>20</option></c:if>
					<c:if test="${numPerPage!=20}"><option value="20" >20</option></c:if>
					<c:if test="${numPerPage==50}"><option value="50" selected>50</option></c:if>
					<c:if test="${numPerPage!=50}"><option value="50" >50</option></c:if>		
					<c:if test="${numPerPage==100}"><option value="100" selected>100</option></c:if>
					<c:if test="${numPerPage!=100}"><option value="100" >100</option></c:if>
					<c:if test="${numPerPage==200}"><option value="200" selected>200</option></c:if>
					<c:if test="${numPerPage!=200}"><option value="200" >200</option></c:if>
				</select>
				<span>条，共${totalCount}条</span>
			</div>
			<!-- 分页 -->
			<div class="pagination" targetType="navTab" totalCount="${totalCount}" numPerPage="${numPerPage }" pageNumShown="10" currentPage="${pageNum }"></div>
