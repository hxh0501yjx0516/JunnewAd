<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/util/taglib.jsp" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<link href="${pageContext.request.contextPath }/css/uploadify.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/uploadify/swfobject.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/uploadify/jquery.uploadify.v2.1.4.js"></script>
<title>添加文件</title> 
<script type="text/javascript">	
		var uploadCount = 0;
		var errorsCount=0;
         $(document).ready(function() {  
          var fileExt = "*.rar;*.zip";
		  var fileDesc = "文件类型rar,zip";
		  if(document.getElementById("hidFilePath").value!=""){
			fileExt = '*.doc;*.docx;*.pdf;*.rar;*.zip;*.gif;*.jpeg;*.jpg;*.png;*.ppt;*.pptx;*.csv;*.xls;*.xlsx;*.txt;*.psd';
			fileDesc = '文件类型rar,zip,doc,docx,ppt,pptx,csv,xls,xlsx,pdf,gif,jpeg,jpg,png,txt,psd';
			}
           $("#uploadify").uploadify({
                'uploader'       : 'js/uploadify/uploadify.swf',
                'script'         : 'uploadify.do?action=upload',
                  //'script'         : 'servlet/UploadifyServlet?1=1',                             
                 'scriptData'    : {'flag':'save','ajax':1},      
                 //'folder'         : $("#dirName").val(),
                'cancelImg'      : 'images/cancel.png',
                'queueID'        : 'fileQueue',
                'auto'           : false,
                'multi'          : true,
                'queueSizeLimit' : 20,
  				'simUploadLimit' : 1,
  				'fileExt'		 :fileExt,
  				'fileDesc'       :fileDesc,
                'buttonText'     : 'BROWSE',
                'removeCompleted': true,
                'onQueueFull'    :function(event,queueSizeLimit){
  					alertMsg.error("最多只能选择"+queueSizeLimit+"个文件");
                },
  				'onSelectOnce'   : function(event,data) {
  					if(data.filesReplaced>0)
                	alertMsg.error("共"+(data.filesSelected+data.filesReplaced)+"个文件,"+data.filesSelected+"个被添加,"+data.filesReplaced+"个文件重复");
    				},
    			'onComplete'     : function(event,queueId,fileObj,response,data){
    					//var code = eval("("+response+")");
    					//alert("complete:"+code.statusCode);
    					//alert("data.fileCount:"+data.fileCount);
    					},
  				'onAllComplete'  : function(event,data) {
    						uploadCount +=data.filesUploaded;
    						errorsCount +=data.errors;
    						if(errorsCount >0){
    						alertMsg.error("共上传"+uploadCount+"个文件，其中"+errorsCount +"个失败，删除后重新上传。");
    						}else{
    						alertMsg.correct("共上传"+uploadCount+"个文件，其中"+errorsCount +"个失败。");
    						}
    						$("#background,#progressBar").hide();
    				}
			});
        });
        function myCallback(form,uploadId){
        	var $form = $(form);
			if (!$form.valid()) {
				alertMsg.error("必填字段不能为空！");
				return false;
			}else{
				$("#background,#progressBar").show();
				 uploadCount = 0;
				 errorsCount=0;
					 $("#"+uploadId).uploadifySettings('scriptData',{'hidpath':$("#hidpath").val(),'dateTime':$("#dateTime").val(),'jsessionid':'${sessionScope.user.userId}'});
				 	 $("#"+uploadId).uploadifyUpload();
				
				return true;
			}
        }
        function cancelUpload(){
        	//jQuery('#uploadify').uploadifyClearQueue();
       	 	navTab.closeCurrentTab();
        }
        </script>

</head>
<body>
    <div class="page">
	<div class="pageContent">
		<form name="uploadifyForm" method="post"  class="pageForm required-validate" enctype="multipart/form-data" onsubmit="return myCallback(this);">
			<div class="pageFormContent" layoutH="30">
				<p>
				<input id="hidFilePath"  name="hidFilePath"  type="hidden" value="${filePath}" />
				<c:choose>
					<c:when test="${filePath ne ''}">
					<label>路径：</label>
					${filePath}
					<input id="hidpath"  name="hidpath"  type="hidden" value="${filePath}" />
					</c:when>
					<c:otherwise>
					<label>日期：</label>
					<input id="dateTime"  name="dateTime" class="date textInput readonly valid required" format="yyyy-MM" readonly="true"  type="text" size="30"  /><a class="inputDateButton" href="#">选择</a>
					</c:otherwise>
				</c:choose>
				</p>
				<p>
				<span style="color: red;">*上传文件如果过大则上传时间较长，请耐心等待！</span>
					</p>
				<div class="divider"></div>
				<input  type="file" name="uploadify" id="uploadify" />&nbsp;&nbsp;&nbsp;<a href="javascript:$('#uploadify').uploadifyClearQueue();">取消所有</a>
				<div id="fileQueue" class="fileQueue" style="width: 100%;"></div>
				<div class="divider"></div>
			</div>
			 <div class="formBar">
				<ul>
					<li>
						<a class="buttonActive" href="javascript:void(0);" onclick="myCallback(document.uploadifyForm,'uploadify')"><span>保存</span></a>
					</li>
					<li>
						<div class="button" ><div class="buttonContent"><button type="Button" onclick="cancelUpload();" ><div>取消</div></div></button></div></div>
					</li>
				</ul>
			</div>
		</form>
	</div>
</div>
  </body>
</html>
