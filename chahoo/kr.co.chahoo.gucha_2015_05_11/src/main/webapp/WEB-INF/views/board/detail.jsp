<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ page session="false" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@include file="/common/header.jsp" %>
<script type="text/javascript">
    function moveList() {
        var main = getParam("main");
        var path = "";
        if (main != null && main == 1) {
            path = "/board/${board_id}/list";
            location.href = path;
        } else {
            history.back(0);
            return false;
        }
    }

    function moveModify() {
        var path = "/board/${board_id}/${contentDto.content_id}/modify/";
        document.location.href = path;
        return false;
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
                    <form class="form-horizontal" method="get" action="/board/${board_id}/${contentDto.content_id}/modify/">
                        <fieldset>
                            <c:choose>
                                <c:when test="${board_id eq 'store'}">
                                    <legend style="margin-bottom: 5px;">[${contentDto.content_type_name}]&nbsp;&nbsp;${contentDto.title}</legend>
                                </c:when>
                                <c:otherwise>
                                    <legend style="margin-bottom: 5px;">${contentDto.title}</legend>
                                </c:otherwise>
                            </c:choose>
                            <div class="control-group" style="margin-top: 0px;">
                                <c:if test="${contentDto.content_type_id eq 'project'}">
                                    <span class="label label-warning" style="float:left; margin-left:15px; border-radius: 5px; padding: 6px 10px; margin-left:5px;">${contentDto.content_year}</span>
                                </c:if>
                                <p style="float:right; margin-left:15px; font-size:0.9em;">${contentDto.name}&nbsp;&nbsp;&nbsp;
                                    <fmt:formatDate value="${contentDto.reg_date}" pattern="yyyy-MM-dd HH:mm:ss" />&nbsp;&nbsp;&nbsp;조회수:${contentDto.count}
                                </p>
                            </div>
                            <div class="control-group" style="margin-top: 0px;">
                                <div class="contentDiv">
                                    <c:out value="${contentDto.content_text}" escapeXml="false" />
                                </div>
                            </div>
                            <c:if test="${contentDto.is_file > 0}">
                                <div class="control-group">
                                    <label class="control-label" for="fileInput">첨부파일</label>
                                    <div class="controls" style="margin-top: 5px;">
                                        <c:forEach var="item" items="${fileDto}" varStatus="counter">
                                            <p class="help-block">
                                                <span style="min-width:100px; float: left;">${item.name}</span>
                                                <span style="margin-left:30px; font-size:0.8em; float: left; min-width:50px;">${item.size_txt}</span>
                                                <span class="badge badge-info" style="margin-left:15px; cursor: pointer;" onclick="downloadFile('${item.file_id}')">다운로드</span>
                                            </p>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                            <div class="form-actions" style="margin-bottom: 0px; padding-left: 20px;">
                                <button type="button" class="btn btn-primary" style="margin-left:5px;" onclick="moveList()"><i class="icon-th-list" style="maring-right:2px;"></i> 목록</button>
                                <c:if test="${contentDto.member_id eq member_id}">
                                    <button class="btn btn-info" style="margin-left:5px;" onclick="moveModify()"><i class="icon-pencil icon-white"></i> 수정</button>
                                    <button type="button" class="btn btn-danger" style="margin-left:5px;" onclick="deleteAlert()"><i class="icon-remove icon-white"></i> 삭제</button>
                                </c:if>
                                <span style="margin-left:5px;">
									<script type="text/javascript" src="//developers.band.us/js/share/band-button.js?v=20150428"></script>
									<script type="text/javascript">
									new ShareBand.makeButton({"lang":"ko","type":"a","text":"${contentDto.title}","withUrl":true}  );
									</script>
								</span>
                            </div>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
        <!-- /block -->
    </div>
</div>
<iframe id="fileDownloadIframe" src="" style="display: none;"></iframe>
<link href="/vendors/datepicker.css" rel="stylesheet" media="screen">
<link href="/vendors/uniform.default.css" rel="stylesheet" media="screen">
<link href="/vendors/chosen.min.css" rel="stylesheet" media="screen">

<script src="/vendors/jquery-1.9.1.js"></script>
<script src="/bootstrap/js/bootstrap.min.js"></script>
<script src="/vendors/jquery.uniform.min.js"></script>
<script src="/vendors/chosen.jquery.min.js"></script>
<script src="/vendors/wizard/jquery.bootstrap.wizard.min.js"></script>
<script src="/assets/scripts.js"></script>

<script>
    $(function() {
        $(".uniform_on").uniform();
    });
</script>
<script type="text/javascript">
    $(document).ready(function() {

    });
</script>
<script type="text/javascript">
    function downloadFile(file_id) {
        document.getElementById("fileDownloadIframe").src = "/file/" + file_id + "/download";
    }

    function deleteAlert() {
        if (confirm("정말 삭제하시겠습니까??") == true) { //확인
            var path = "/board/${board_id}/${contentDto.content_id}/delete";
            location.href = path;
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