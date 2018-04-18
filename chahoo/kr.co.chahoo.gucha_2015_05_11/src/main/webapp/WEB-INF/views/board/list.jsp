<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ page session="false" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@include file="/common/header.jsp" %>
<script type="text/javascript">
//검색기능
	function search() 
	{
	    var keyword = document.getElementById("keyword").value;
	    var seltype = document.getElementById("selectType").value;
	    var cnttype = getParam('ctype');
	    if (cnttype == null) {
	        cnttype = "";
	    }
	    var year = getParam('year');
	    if (year == null) {
	        year = "";
	    }
	    <c:if test = "${boardDto.board_id eq 'store'}" >
	        cnttype = document.getElementById("content_type_list").value;
	    </c:if>
	    if (keyword == "") {
	        alert("키워드를 입력해주세요.");
	    } else {
	        var url = "/board/${boardDto.board_id}/list?stype=" + seltype + "&keyword=" + keyword + "&ctype=" + cnttype + "&year=" + year;
	        location.href = url;
	    }
	}
	
	function yearProject(year_text) 
	{
	    document.getElementById("keyword").value = "";
	    var cnttype = getParam('ctype');
	    if (cnttype == null) {
	        cnttype = "";
	    }
	    var url = '';
	    if (year_text == 'all') {
	        url = "/board/${boardDto.board_id}/list?year=&ctype=" + cnttype;
	    } else {
	        url = "/board/${boardDto.board_id}/list?year=" + year_text + "&ctype=" + cnttype;
	    }
	    location.href = url;
	}
	//페이지 이동			
	function movePage(page) 
	{
	    var keyword = document.getElementById("keyword").value;
	    var seltype = document.getElementById("selectType").value;
	    var cnttype = getParam('ctype');
	    if (cnttype == null) {
	        cnttype = "";
	    }
	    var year = getParam('year');
	    if (year == null) {
	        year = "";
	    }
	    var path = "/board/${boardDto.board_id}/list";
	    var url = path + "?page=" + page;
	    if (keyword != null && keyword != "") {
	        url += '&keyword=' + keyword;
	    }
	    if (seltype != null && seltype != "") {
	        url += '&stype=' + seltype;
	    }
	    if (cnttype != null && cnttype != "") {
	        url += '&ctype=' + cnttype;
	    }
	    if (year != null && year != "") {
	        url += '&year=' + year;
	    }
	    location.href = url;
	}
	//페이지 이동			
	function moveWritePage(page) {
	    var cnttype = getParam('ctype');
	    if (cnttype == null) {
	        cnttype = "";
	    }
	    var path = "/board/${boardDto.board_id}/write?ctype=" + cnttype;
	    location.href = path;
	}
	
	function viewContents(content_id) {
	    var content_url = "/board/${boardDto.board_id}/" + content_id + "/count";
	    $.ajax({
	        url: content_url,
	        type: "get",
	        dataType: "text",
	        processData: false,
	        contentType: false,
	        success: function(data, textStatus, jqXHR) {
	            path = "/board/${boardDto.board_id}/" + content_id + "/";
	            location.href = path;
	        },
	        error: function(jqXHR, textStatus, errorThrown) {
	            path = "/board/${boardDto.board_id}/" + content_id + "/";
	            location.href = path;
	        }
	    });
	}
</script>
<div class="span12" id="content" style="display: table; float:none; margin-left: auto; margin-right: auto; display: inline-block;">
    <div class="row-fluid" style="text-align: left;">
        <div class="controls">
            <select class="selectError" id="selectType" size="1" style="width:80px; font-size:0.9em; float:left;">
                <option value="1" style="text-align:center" <c:if test="${stype == 1}">selected="selected"</c:if>>제목</option>
                <option value="2" style="text-align:center" <c:if test="${stype == 2}">selected="selected"</c:if>>내용</option>
            </select>
            <input class="formControl" id="keyword" type="text" onKeyDown="javascript:checkNext(event);" value="${keyword}" placeholder="키워드를 입력하세요." style="float:left; margin-right:10px; margin-left:10px;"></input>
            <button class="btn btn-black btn-success" type="button" onclick="javascript:search();" style="float:left; font-size:0.9em;"><i class="icon-search"></i> 검색</button>
        </div>
        <div class="controls">
            <button class="btn btn-black btn-info" type="button" onclick="javascript:moveWritePage();" style="float:right; font-size:0.9em;"><i class="icon-pencil"></i> 등 록</button>
            <c:if test="${boardDto.board_id eq 'store'}">
                <select class="selectError" id="content_type_list" size="1" style="width:100px; font-size:0.9em; float:right; margin-right:20px;">
                    <option value="all" style="text-align:center">전체</option>
                    <c:forEach var="item" items="${content_type_list}" varStatus="counter">
                        <option value="${item.content_type_id}" style="text-align:center" <c:if test="${item.content_type_id eq ctype}">selected="selected"</c:if>>${item.content_type_name}</option>
            		</c:forEach>
            	</select>
            </c:if>
        </div>
    </div>
    <div class="row-fluid" style="text-align: left;">
        <span class="label" id="all" style="cursor: pointer; border-radius: 5px; padding: 6px 10px; margin-left:5px; display:none;" onclick="yearProject('all')">All</span>
        <c:forEach var="item" items="${yearList}" varStatus="counter">
            <span class="label" id="year_${item.content_year}" style="cursor: pointer; border-radius: 5px; padding: 6px 10px; margin-left:5px;" onclick="yearProject('${item.content_year}')">${item.content_year}</span>
            <!-- <span class="badge badge-success">${item.year_count}</span>  -->
        </c:forEach>
    </div>
    <div class="row-fluid">
        <!-- block -->
        <div class="block">
            <div class="navbar navbar-inner block-header">
                <div class="muted pull-left" id="board_name_title">
                    <p style="float:left;">
                        ${boardDto.name}
                        <c:forEach var="item" items="${content_type_list}" varStatus="counter">
                            <c:if test="${item.content_type_id eq ctype}">&nbsp;-&nbsp;${item.content_type_name}</c:if>
                        </c:forEach>
                    </p>
                    <p id="content_year_title" style="float:left;"></p>
                </div>
            </div>
            <div class="block-content collapse in">
                <div class="span12">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th style="width:50px; text-align:center;">#</th>
                                <c:if test="${boardDto.board_id eq 'store'}">
                                    <th style="width:65px;text-align:center;">분류</th>
                                </c:if>
                                <th style="text-align:center;">제목</th>
                                <th style="width:55px; text-align:center;">작성자</th>
                                <th style="width:145px; text-align:center;">등록일</th>
                                <th style="width:65px; text-align:center;">조회수</th>
                            </tr>
                        </thead>
                        <tbody style="font-size: 16px;">
                            <c:forEach var="item" items="${list}" varStatus="counter">
                                <tr style="cursor:pointer;" onclick="javascript:viewContents('${item.content_id}');">
                                    <td style=" font-weight:bold; text-align:center;">${count-((counter.count-1) + ((currentPage - 1) * pageSize))}</td>
                                    <c:if test="${boardDto.board_id eq 'store'}">
                                        <td style="width:60px;text-align:center;">${item.content_type_name}</td>
                                    </c:if>
                                    <td style="text-align:left; padding-left:10px;">
                                        <c:if test="${item.is_file eq 1}"><i class="icon-file" style="margin-right:5px;"></i></c:if>
                                        <c:out value="${item.title}" />
                                    </td>
                                    <td style="text-align:center;">
                                        <c:out value="${item.name}" />
                                    </td>
                                    <td style="text-align:center;">
                                        <fmt:formatDate value="${item.reg_date}" pattern="yyyy-MM-dd HH:mm" />
                                    </td>
                                    <td style="width:55px; text-align:center;">
                                        <c:out value="${item.count}" />
                                    </td>
                                </tr>
                            </c:forEach>
                            <c:if test="${fn:length(list) <= 0}">
                                <tr>
                                    <c:choose>
                                        <c:when test="${boardDto.board_group eq 'store'}">
                                            <td colspan="6">결과가 없습니다.</td>
                                        </c:when>
                                        <c:otherwise>
                                            <td colspan="5">결과가 없습니다.</td>
                                        </c:otherwise>
                                    </c:choose>
                                </tr>
                            </c:if>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <!-- /block -->
        <div class="text-center">
            <div class="pagination">
                <c:set var="pageCount" value="${count / pageSize + (count % pageSize == 0 ? 0 : 1)}" />
                <c:set var="startPage" value="${pageGroupSize * (numPageGroup - 1) + 1}" />
                <c:set var="endPage" value="${startPage + pageGroupSize - 1}" />
                <c:if test="${endPage > pageCount}">
                    <c:set var="endPage" value="${pageCount}" />
                </c:if>
                <ul>
                    <c:if test="${numPageGroup > 1}">
                        <li onclick='javascript:movePage("${startPage-1}");'><a href="#">이전</a></li>
                    </c:if>
                    <c:forEach var="i" begin="${startPage}" end="${endPage}">
                        <c:choose>
                            <c:when test="${currentPage == i}">
                                <li class="active"><a href="#">${i}</a></li>
                            </c:when>
                            <c:otherwise>
                                <li onclick='javascript:movePage("${i}");'><a href="#">${i}</a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:if test="${numPageGroup < pageGroupCount}">
                        <li onclick='javascript:movePage("${endPage+1}");'><a href="#">다음</a></li>
                    </c:if>
                </ul>
            </div>
        </div>
    </div>
</div>
<link href="/vendors/uniform.default.css" rel="stylesheet" media="screen">
<script src="/vendors/jquery-1.9.1.js"></script>
<script src="/bootstrap/js/bootstrap.min.js"></script>
<script src="/vendors/jquery.uniform.min.js"></script>
<script src="/vendors/chosen.jquery.min.js"></script>
<script src="/vendors/wizard/jquery.bootstrap.wizard.min.js"></script>
<script src="/assets/scripts.js"></script>
<script type="text/javascript">
$(document).ready(function() {
    var board_id = "${boardDto.board_id}" + "Nav";
    $('#' + board_id).addClass("active"); 
    <c:if test = "${boardDto.board_id eq 'store'}" >
        $('#content_type_list').change(function() {
            var cnttype = document.getElementById("content_type_list").value;
            if (cnttype == "all") {
                var url = "/board/${boardDto.board_id}/list?ctype=";
                location.href = url;
            } else {
                var url = "/board/${boardDto.board_id}/list?ctype=" + cnttype;
                location.href = url;
            }
        }); 
    </c:if>
    var cnttype = getParam('ctype');
    if (cnttype != null) {
        if (cnttype == "project") {
            $('#all').css('display', '');
            $('#all').addClass("label-warning");
        }
    }
    var year = getParam('year');
    if (year != null && year != "") {
        $('#content_year_title').html('&nbsp;-&nbsp;' + year);
        $('#year_' + year).addClass("label-warning");
        $('#all').removeClass("label-warning");
    }
    $("#keyword").keypress(function(e){ // 체크박스에 변화가 있다면,
    	if (e.which == 13) {/* 13 == enter key@ascii */
    		search();
    	}
    });
});
</script>

<%@include file="/common/footer.jsp" %>