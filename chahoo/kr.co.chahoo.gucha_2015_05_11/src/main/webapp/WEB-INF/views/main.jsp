<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ page session="false" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@include file="/common/header.jsp" %>
<style>
<!--
table tbody tr {
    cursor: unset;
}
-->
</style>
<script type="text/javascript">
    //페이지 이동			
    function moveEvnetPage() {
        var path = "/event/main";
        location.href = path;
    }
</script>

<div class="span12" id="content" style="display: table; float:none; margin-left: auto; margin-right: auto; display: inline-block;">
    <div class="row-fluid" style="margin-top: -10px;">
        <!-- block -->
        <div class="block">
            <div class="navbar navbar-inner block-header">
                <div class="muted pull-left">주간일정</div>
                <div class="pull-right">
                	<span class="badge badge-warning" style="cursor:pointer" onclick="moveEvnetPage()">View More</span>
                </div>
            </div>
            <div class="block-content collapse in">
                <div id='calendar' style="margin-top: -30px; padding-right: 5px;"></div>
            </div>
        </div>
        <!-- /block -->
    </div>
    <div class="row-fluid" style="margin-top: -10px;">
        <div class="span6">
            <!-- block -->
            <div class="block">
                <div class="navbar navbar-inner block-header">
                    <div class="muted pull-left">공지사항</div>
                    <div class="pull-right"><span class="badge badge-info" style="cursor:pointer;" onclick="moveBoard('notice', 'notice')">${noticeCount}</span></div>
                </div>
                <div class="block-content collapse in">
                    <table class="table table-striped">
                        <!-- <thead>
	                        <tr>
	                            <th style="text-align:center; font-size:12px;">제목</th>
								<th style="width:60px; text-align:center; font-size:12px;">작성자</th>
								<th style="width:80px; text-align:center; font-size:12px;">등록일</th>
	                        </tr>
	                    </thead>
	                     -->
                        <tbody>
                            <c:forEach var="item" items="${noticeList}" varStatus="counter">
                                <tr onclick="javascript:viewContents('${item.board_id}','${item.content_id}');" style="cursor: pointer;">
                                    <td style="text-align:left; padding-left:5px; font-size:14px;">
                                        <c:choose>
                                            <c:when test="${fn:length(item.title) > 35}">
                                                <c:out value="${fn:substring(item.title,0,34)}" />....
                                            </c:when>
                                            <c:otherwise>
                                                <c:out value="${item.title}" />
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td style="text-align:center; font-size: 14px; width:55px;">
                                        <c:out value="${item.name}" />
                                    </td>
                                    <td style="text-align:center; font-size: 14px; width:80px;">
                                        <fmt:formatDate value="${item.reg_date}" pattern="yyyy-MM-dd" />
                                    </td>
                                </tr>
                            </c:forEach>
                            <c:if test="${fn:length(noticeList) <= 0}">
                                <tr>
                                    <td colspan="3">등록된 공지사항이 없습니다.</td>
                                </tr>
                            </c:if>
                        </tbody>
                    </table>
                </div>
            </div>
            <!-- /block -->
        </div>
        <div class="span6">
            <!-- block -->
            <div class="block">
                <div class="navbar navbar-inner block-header">
                    <div class="muted pull-left">자료실</div>
                    	<div class="pull-right"><span class="badge badge-info" style="cursor:pointer;" onclick="moveBoard('store','store')">${storeCount}</span>
                    </div>
                </div>
                <div class="block-content collapse in">
                    <table class="table table-striped">
                        <thead>
                            <!-- 
	                        <tr>
	                        	<th style="width:60px;text-align:center; font-size:12px;">분류</th>
	                            <th style="text-align:center; font-size:12px;">제목</th>
								<th style="width:60px; text-align:center; font-size:12px;">작성자</th>
								<th style="width:80px; text-align:center; font-size:12px;">등록일</th>
	                        </tr>
	                        -->
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${storeList}" varStatus="counter">
                                <tr onclick="javascript:viewContents('${item.board_id}','${item.content_id}');" style="cursor: pointer;">
                                    <td style="text-align:center; font-size: 14px; width:60px;">${item.content_type_name}</td>
                                    <td style="text-align:left; padding-left:5px; font-size:14px;">
                                        <c:if test="${item.is_file eq 1}"><i class="icon-file" style="margin-right:5px;"></i></c:if>
                                        <c:choose>
                                            <c:when test="${fn:length(item.title) > 30}">
                                                <c:out value="${fn:substring(item.title,0,29)}" />....
                                            </c:when>
                                            <c:otherwise>
                                                <c:out value="${item.title}" />
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td style="text-align:center; font-size: 14px; width:55px;">
                                        <c:out value="${item.name}" />
                                    </td>
                                    <td style="text-align:center; font-size: 14px; width:80px;">
                                        <fmt:formatDate value="${item.reg_date}" pattern="yyyy-MM-dd" />
                                    </td>
                                </tr>
                            </c:forEach>
                            <c:if test="${fn:length(storeList) <= 0}">
                                <tr>
                                    <td colspan="4">등록된 자료가 없습니다.</td>
                                </tr>
                            </c:if>
                        </tbody>
                    </table>
                </div>
            </div>
            <!-- /block -->
        </div>
    </div>
</div>
<script src="/vendors/jquery-1.9.1.min.js"></script>
<script src="/bootstrap/js/bootstrap.min.js"></script>
<script src="/assets/scripts.js"></script>
<script src="/vendors/fullcalendar/moment.min.js"></script>
<script src="/vendors/fullcalendar/fullcalendar.js"></script>
<script src="/vendors/fullcalendar/gcal.js"></script>
<script src="/vendors/fullcalendar/lang-all.js"></script>
<script type="text/javascript">
    $(document).ready(function() {});
</script>
<script>
    function viewContents(board_id, content_id) {
        var content_url = "/board/" + board_id + "/" + content_id + "/count";
        $.ajax({
            url: content_url,
            type: "get",
            dataType: "text",
            processData: false,
            contentType: false,
            success: function(data, textStatus, jqXHR) {
                var path = "/board/" + board_id + "/" + content_id + "/?main=1";
                location.href = path;
            },
            error: function(jqXHR, textStatus, errorThrown) {
                var path = "/board/" + board_id + "/" + content_id + "/?main=1";
                location.href = path;
            }
        });
    }
</script>
<script>
    $(function() {
        var events;
        var calendar = $('#calendar').fullCalendar({
            header: {
                left: '',
                center: '',
                right: ''
            },
            eventClick: function(calEvent, jsEvent, view) {
                var memberId = "${member_id}";
                //init
                $('#eventId').val('');
            	$('#eventDesc').html('');
            	$('#eventMemberId').val('');
                $('#eventTitle').html('');
                $('#eventRange').html('');
                
                if (calEvent.memberId == memberId) {
                    $('#eventDeleteBtn').css('display', 'block');
                } else {
                    $('#eventDeleteBtn').css('display', 'none');
                }
                $('#eventId').val(calEvent.id);
                $('#eventMemberId').val(calEvent.memberId);
                $('#eventTitle').html(calEvent.title);
                $('#eventRange').html(calEvent.start.format("YYYY-MM-DD HH:mm") + " ~ " + calEvent.end.format("YYYY-MM-DD HH:mm"));
                $('#eventDesc').html(calEvent.description);

                $('#eventViewModalBtn').click();
            },
            lang: "ko",
            defaultView: 'basicWeek',
            eventLimit: true,
            selectable: false,
            selectHelper: true,
            droppable: false, // this allows things to be dropped onto the calendar !!!
            editable: false,
            timeFormat: 'H:mm', // uppercase H for 24-hour clock
            googleCalendarApiKey: 'AIzaSyCYlwnFfpFQ91k9XIJUAw8cVfxrnNVCdxs',
            events: {
                googleCalendarId: 'blffot637do35g8hc1hf9a046s@group.calendar.google.com',
                className: 'gcal-event' // an option!
            }
        });
        $('#calendar').fullCalendar('option', 'height', 330);
        var moment = $('#calendar').fullCalendar('getDate');
        events = getEventInitUrl(moment);
        calendar.fullCalendar('addEventSource', events);
    });
</script>

<a href="#eventViewModal" data-toggle="modal" id="eventViewModalBtn" class="btn btn-primary" style="display:none;">Modal dialog</a>
<!-- Event View Modal Start -->
<div id="eventViewModal" class="modal hide" style="top: 30%;">
    <input type="hidden" id="eventId" name="evnetId" value="" />
    <input type="hidden" id="eventMemberId" name="evnetId" value="" />
    <div class="modal-header">
        <button data-dismiss="modal" class="close" type="button">&times;</button>
        <h3 id="eventTitle">Modal header</h3>
    </div>
    <div class="modal-body">
        <p id="eventRange"></p>
        <p id="eventDesc"></p><i class="icon-trash" id="eventDeleteBtn" style="float:right; cursor:pointer;" onclick="eventDelete()"></i>
    </div>
</div>
<!-- Event View Modal End -->
<script type="text/javascript">
    function eventDelete() {
        var eventId = $('#eventId').val();
        var eventMemberId = $('#eventMemberId').val();
        var deleteUrl = "/event/" + eventId + "/delete"
        $.ajax({
            type: "get",
            url: deleteUrl,
            cache: false,
            success: function(data, textStatus, jqXHR) {
                $('#eventViewModal').modal('hide');
                $('#calendar').fullCalendar('removeEvents', eventId);
            },
            error: function(jqXHR, textStatus, errorThrown) {}
        });
    }
</script>
<%@include file="/common/footer.jsp" %>