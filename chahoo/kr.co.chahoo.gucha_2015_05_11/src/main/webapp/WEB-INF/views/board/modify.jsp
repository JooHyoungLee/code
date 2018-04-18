<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ page session="false" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@include file="/common/header.jsp" %>
<script type="text/javascript">
    function modifyContent() {
        if (!checkNull(document.getElementById('title'))) {
            alert('제목을 입력해주세요');
        } else if (!checkNull(document.getElementById('content'))) {
            alert('내용을 입력해주세요');
        } else {
            //submit
            document.writeForm.submit();
        }
    }

    function moveDetail() {
        var path = "/board/${board_id}/${contentDto.content_id}/";
        location.href = path;
    }
</script>
<div class="span12" id="content" style="display: table; float:none; margin-left: auto; margin-right: auto; display: inline-block;">
    <div class="row-fluid">
        <!-- block -->
        <div class="block">
            <div class="navbar navbar-inner block-header">
                <div class="muted pull-left">
                    ${boardDto.name}
                </div>
            </div>
            <div class="block-content collapse in">
                <div class="span12">
                    <form class="form-horizontal" name="writeForm" action="/board/${board_id}/${contentDto.content_id}/modifyProcess" method="post">
                        <fieldset>
                            <legend>수정</legend>
                            <input type="hidden" id="is_file" name="is_file" value="${contentDto.is_file}" />
                            <c:if test="${board_id eq 'store'}">
                                <div class="control-group">
                                    <label class="control-label" for="content_type_list">분류</label>
                                    <div class="controls">
                                        <select id="content_type_list" name="content_type_list" class="chzn-select">
                                            <c:forEach var="item" items="${content_type_list_dto}" varStatus="counter">
                                                <option value="${item.content_type_id}">${item.content_type_name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </c:if>
                            <input type="hidden" id="content_type_id" name="content_type_id" value="" />
                            <c:if test="${contentDto.content_type_id eq 'project'}">
                                <div class="control-group">
                                    <label class="control-label" for="year_list">연도</label>
                                    <div class="controls">
                                        <select id="year_list" name="year_list" class="chzn-select">
                                            <c:forEach var="item" items="${year_list}" varStatus="counter">
                                                <option value="${item}">${item}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </c:if>
                            <input type="hidden" id="content_year" name="content_year" value="${contentDto.content_year}" />
                            <div class="control-group">
                                <label class="control-label" for="title">제목</label>
                                <div class="controls">
                                    <input type="text" class="span10" id="title" name="title" value="${contentDto.title}">
                                    <!-- p class="help-block">Start typing to activate auto complete!</p -->
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="content">내용</label>
                                <div class="controls">
                                    <textarea class="input-xlarge textarea span10" id="content" name="content" placeholder="Enter text ..." style="min-height: 300px">${contentDto.content_text}</textarea>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="fileInput">첨부파일</label>
                                <div class="controls" style="margin-top: 0px;" id="fileDiv">
                                    <button type="button" class="btn" style="margin-bottom: 10px;" id="file_btn" name="file_btn">첨부하기</button>
                                    <input type="hidden" id="fileList" name="fileList" value="" />
                                    <c:forEach var="item" items="${fileDto}" varStatus="counter">
                                        <p class="help-block" id="alitem_${item.file_id}">
                                            <span style="min-width:100px; float: left;">${item.name}</span>
                                            <span style="margin-left:30px; font-size:0.8em; float: left; min-width:50px;">${item.size_txt}</span>
                                            <span class="badge badge-important" style="margin-left:15px; cursor: pointer;" onclick="deleteAlreadyFile('${item.file_id}')">Delete</span>
                                        </p>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="form-actions">
                                <button type="button" class="btn btn-primary" onclick="modifyContent()"><i class="icon-pencil"></i> 저장</button>
                                <button type="button" class="btn" style="margin-left:5px;" onclick="moveDetail()"><i class="icon-remove-sign"></i> 취소</button>
                            </div>
                        </fieldset>
                    </form>
                    <form id="fileFrom" enctype="multipart/form-data" accept-charset="utf-8" style="display:none;">
                        <input name="attachFile" id="attachFile" type="file" />
                    </form>
                </div>
            </div>
        </div>
        <!-- /block -->
    </div>
</div>
<link href="/vendors/datepicker.css" rel="stylesheet" media="screen">
<link href="/vendors/uniform.default.css" rel="stylesheet" media="screen">
<link href="/vendors/chosen.min.css" rel="stylesheet" media="screen">
<link href="/vendors/wysiwyg/bootstrap-wysihtml5.css" rel="stylesheet" media="screen">

<script src="/vendors/jquery-1.9.1.js"></script>
<script src="/bootstrap/js/bootstrap.min.js"></script>
<script src="/vendors/jquery.uniform.min.js"></script>
<script src="/vendors/chosen.jquery.min.js"></script>
<script src="/vendors/bootstrap-datepicker.js"></script>
<script src="/vendors/wysiwyg/wysihtml5-0.3.0.js"></script>
<script src="/vendors/wysiwyg/bootstrap-wysihtml5.js"></script>
<script src="/vendors/wizard/jquery.bootstrap.wizard.min.js"></script>
<script src="/assets/scripts.js"></script>

<script>
    $(function() {
        $(".uniform_on").uniform();
        $('.textarea').wysihtml5();
    });
</script>
<script type="text/javascript">
    $(document).ready(function() {
        /* 글 분류 초기화 */
        <c:choose>
            <c:when test = "${board_id eq 'store'}" >
	            document.getElementById('content_type_id').value = "${contentDto.content_type_id}";
	        	$("#content_type_list").val("${contentDto.content_type_id}");
	        	$('#content_type_list').change(function() {
	            	document.getElementById('content_type_id').value = $("#content_type_list option:selected").val();
        		}); 
	        </c:when> 
	        <c:otherwise>
	            document.getElementById('content_type_id').value = "normal"; 
	        </c:otherwise>
        </c:choose>

        <c:if test = "${contentDto.content_type_id eq 'project'}" >
            $("#year_list").val("${contentDto.content_year}");
	        $('#year_list').change(function() {
	            document.getElementById('content_year').value = $("#year_list option:selected").val();
	        }); 
        </c:if>

        $('#attachFile').change(function() {
        	var files = document.getElementById('attachFile');
            if($(this)[0].files[0].size >= 5024000000)
            {
				alert('첨부가능한 파일의 크기는 50MB이하 입니다');            	
            }
            else
        	{
            	var data = new FormData(document.getElementById('fileFrom'));

                $.ajax({
                    url: '/file/${board_id}/uploadProcess',
                    type: "post",
                    dataType: "text",
                    data: data,
                    processData: false,
                    contentType: false,
                    success: function(data, textStatus, jqXHR) {
                        if (data != "0") {
                            var tempFileId = document.getElementById('fileList').value;
                            if (tempFileId != "") {
                                document.getElementById('fileList').value = tempFileId + '##' + data;
                            } else {
                                document.getElementById('fileList').value = data;
                            }
                            addFileContent(data)
                        } else {
                            alert('파일업로드에 실패하였습니다');
                        }
                    },
                    error: function(jqXHR, textStatus, errorThrown) {
                        alert('파일업로드에 실패하였습니다');
                    }
                });
        	}
        });
        $('#file_btn').click(function() {
            $('#attachFile').click();
        });
    });
</script>
<script type="text/javascript">
    function addFileContent(file_id) {
        var content_url = "/file/" + file_id + "/content";
        $.ajax({
            url: content_url,
            type: "get",
            dataType: "text",
            processData: false,
            contentType: false,
            success: function(data, textStatus, jqXHR) {
                var obj = eval("(" + data + ")");
                $('#fileDiv').append("<p class=\"help-block\" id=\"item_" + obj.file_id + "\"><span style=\"min-width:100px; float: left;\">" + obj.name + "</span><span style=\"margin-left:30px; font-size:0.8em; float: left; min-width:50px;\">" + obj.size + "kb</span><span class=\"badge badge-important\" style=\"margin-left:15px; cursor: pointer;\" onclick=\"deleteFile('" + obj.file_id + "')\">Delete</span></p>");
            },
            error: function(jqXHR, textStatus, errorThrown) {
                //alert('파일업로드에 실패하였습니다');
                alert('error');
            }
        });
    }

    function deleteFile(file_id) {
        var fileListText = document.getElementById('fileList').value;
        var fileText = "";
        if (fileListText.match("##")) {
            var fileList = document.getElementById('fileList').value.split('##');
            for (i = 0; i < fileList.length; i++) {
                alert(fileList[i] + " / " + file_id);
                if (fileList[i] != file_id) {
                    if (file_id && i == 0) {
                        fileText += fileList[i];
                    } else {
                        fileText += "##" + fileList[i];
                    }
                }
            }
            document.getElementById('fileList').value = fileText;
        } else {
            document.getElementById('fileList').value = "";
        }
        $('#item_' + file_id).remove();
    }

    function deleteAlreadyFile(file_id) {
        if (confirm("정말 삭제하시겠습니까??") == true) { //확인
            var path = "/file/" + file_id + "/delete";
            $.ajax({
                url: path,
                type: "get",
                processData: false,
                contentType: false,
                success: function(data, textStatus, jqXHR) {
                    $('#alitem_' + file_id).remove();
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    //alert('파일업로드에 실패하였습니다');
                    alert('error');
                }
            });
        } else { //취소
            return;
        }

    }
</script>
<script type="text/javascript">
    $(document).ready(function() {
        var board_id = "${board_id}" + "Nav";
        $('#' + board_id).addClass("active");
    });
</script>

<%@include file="/common/footer.jsp" %>