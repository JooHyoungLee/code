<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ page session="false" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@include file="/common/header.jsp" %>
<script type="text/javascript">
</script>
<div class="span12" id="content" style="width: 100%; padding-bottom: 60px;">
    <div class="row-fluid">
        <!-- block -->
        <div class="block">
            <div class="navbar navbar-inner block-header">
                <div class="muted pull-left" style="float:left;">일정관리 </div>
                <p style="float:left; padding-top: 10px; margin-left:10px; font-size: 0.8em; color: #555;">※ 날짜를 선택하면 일정을 입력 할 수 있습니다</p>
                <div class="pull-right"><i class="icon-print" style="margin-right:5px; cursor:pointer;" onclick="print()"></i></div>
            </div>
            <div class="block-content collapse in">
                <div class="span12" id="calendarDiv">
                    <div id='calendar'></div>
                </div>
            </div>
        </div>
        <!-- /block -->
        
    </div>
    <!-- row-fluid -->
</div>
<!-- /content -->

<style>
    #external-events {
        float: left;
        width: 150px;
        padding: 0 10px;
        border: 1px solid #ccc;
        background: #eee;
        text-align: left;
    }
    
    #external-events h4 {
        font-size: 16px;
        margin-top: 0;
        padding-top: 1em;
    }
    
    .external-event {
        /* try to mimick the look of a real event */
        
        margin: 10px 0;
        padding: 2px 4px;
        background: #3366CC;
        color: #fff;
        font-size: .85em;
        cursor: pointer;
        z-index: 99999999;
    }
    
    #external-events p {
        margin: 1.5em 0;
        font-size: 11px;
        color: #666;
    }
    
    #external-events p input {
        margin: 0;
        vertical-align: middle;
    }
</style>
<script src="/vendors/jquery-1.9.1.min.js"></script>
<script src="/vendors/jquery-ui-1.10.3.js"></script>
<script src="/bootstrap/js/bootstrap.min.js"></script>
<!-- <script src="/vendors/fullcalendar/jquery.min.js"></script>  -->
<script src="/vendors/fullcalendar/moment.min.js"></script>
<script src="/vendors/fullcalendar/fullcalendar.js"></script>
<script src="/vendors/fullcalendar/gcal.js"></script>
<script src="/vendors/fullcalendar/lang-all.js"></script>
<script src="/vendors/bootstrap-datetimepicker.js"></script>
<script src="/vendors/bootstrap-datetimepicker.kr.js"></script>
<script src="/assets/scripts.js"></script>
<!-- Print -->
<script src="http://code.jquery.com/jquery-migrate-1.0.0.js"></script>
<script src="/js/jquery.printElement.js"></script>

<script>
    //var date = new Date (2015, 0, 1);
    $(function() {
        var events;
        // Easy pie charts
        var calendar = $('#calendar').fullCalendar({
            header: {
                left: 'prev,next',
                center: 'title',
                right: 'month,basicWeek,basicDay'
            },
            lang: "ko",
            eventLimit: true,
            selectable: true,
            selectHelper: true,
            select: function(start, end, allDay) {
                $('#start').val(start.format("YYYY-MM-DD HH:mm"));
                $('#start_text').val(start.format("YYYY-MM-DD HH:mm"));

                if (start != end) {
                    $("input:checkbox[id='allDayCB']").attr("checked", false); /* by ID */
                    $('#end').val(end.format("YYYY-MM-DD HH:mm"));
                    $('#end_text').val(end.format("YYYY-MM-DD HH:mm"));
                    $('#endDiv').css('display', 'block');
                } else {
                    $('#end').val("");
                    $('#end_text').val("");
                    $('#endDiv').css('display', 'none');
                }
                $('#title').val("");
                $('#desc').val("");

                $('#newEventModalBtn').click();
                calendar.fullCalendar('unselect');
            },
            next: function(date) {
                calendar.fullCalendar('removeEvents', 'temp');
                calendar.fullCalendar('removeEventSource', events);
                events = getEventUrl(date);
                calendar.fullCalendar('addEventSource', events);
                //calendar.fullCalendar('refetchEvents');
                
                //var view = $('#calendar').fullCalendar('getView');
				//alert("The view's title is " + view.title);
                //var moment = $('#calendar').fullCalendar('getDate');
                //alert("The current date of the calendar is " + moment.format());

            },
            prev: function(date) {
                calendar.fullCalendar('removeEvents', 'temp');
                calendar.fullCalendar('removeEventSource', events);
                events = getEventUrl(date);
                calendar.fullCalendar('addEventSource', events);
                //calendar.fullCalendar('refetchEvents');
            },
            eventClick: function(calEvent, jsEvent, view) {
            	//init
            	$('#eventId').val('');
            	$('#eventDesc').html('');
            	$('#eventMemberId').val('');
                $('#eventTitle').html('');
                $('#eventRange').html('');
            	
                var memberId = "${member_id}";
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
            droppable: false, // this allows things to be dropped onto the calendar !!!
            editable: false,
            timeFormat: 'H:mm', // uppercase H for 24-hour clock
            googleCalendarApiKey: 'AIzaSyCYlwnFfpFQ91k9XIJUAw8cVfxrnNVCdxs',
            events: {
                googleCalendarId: 'blffot637do35g8hc1hf9a046s@group.calendar.google.com',
                className: 'gcal-event' // an option!
            }
        });
        $('#calendar').fullCalendar('option', 'contentHeight', 850);
        var moment = $('#calendar').fullCalendar('getDate');
        events = getEventInitUrl(moment);
        calendar.fullCalendar('addEventSource', events);
        //var koreaEvent = 'https://www.google.com/calendar/feeds/blffot637do35g8hc1hf9a046s%40group.calendar.google.com/public/basic?alt=json';
        //calendar.fullCalendar('addEventSource', koreaEvent);
    });

    function print() {
        $('#calendar').printElement({
            printMode: 'popup', //팝업설정 printMode option = popup, iframe,
            overrideElementCSS: ['/bootstrap/css/bootstrap.css', {
                href: '/bootstrap/css/bootstrap.css',
                media: 'print'
            }], //overrideElement option : boolean, css경로 
            overrideElementCSS: ['/vendors/fullcalendar/fullcalendar.css', {
                href: '/vendors/fullcalendar/fullcalendar.print.css',
                media: 'content'
            }], //overrideElement option : boolean, css경로
            pageTitle: 'Print Page', //팝업 타이틀
            leaveOpen: true //인쇄하고도 창을 띄우기(true)/안띄우기(false). Default는 false
        });

    }
</script>


<script>
    $(document).ready(function() {
        $('#eventNav').addClass("active");

        $("input:checkbox[id='allDayCB']").attr("checked", true); /* by ID */
        $('#allDayCB').click(function() {
            var ischecked = $('#allDayCB').attr('checked');
            if (ischecked) {
                $('#endDiv').css('display', 'none');
                $('#end').val("");
                $('#end_text').val("");
                $('#allDay').val("1"); //true
            } else {
                $('#endDiv').css('display', 'block');
                $('#end').val($('#start').val());
                $('#end_text').val($('#start').val());
                $('#allDay').val("0"); //false
            }
        });
    });
</script>
<a href="#newEventModal" data-toggle="modal" id="newEventModalBtn" class="btn btn-danger" style="display:none;">Alert</a>
<!-- New Event Modal Start-->
<div id="newEventModal" class="modal hide">
    <div class="modal-header">
        <button data-dismiss="modal" class="close" type="button">&times;</button>
        <h3>일정 등록</h3>
    </div>
    <div class="modal-body">
        <form class="form-horizontal" id="eventForm" name="eventForm">
            <fieldset>
                <div class="control-group">
                    <label class="control-label" for="focusedInput">시작일시</label>
                    <div class="controls input-append date form_datetime_start" data-date="" data-date-format="•yyyy-mm-dd hh:ii" data-link-field="start" style="margin-left: 20px;">
                        <input size="16" type="text" value="" readonly style="background-color: #FFF;" id="start_text">
                        <span class="add-on"><i class="icon-remove" style="margin-right:0px;"></i></span>
                        <span class="add-on"><i class="icon-calendar" style="margin-right:0px;"></i></span>
                    </div>
                    <input type="hidden" id="start" name="start" value="" />
                    <br/>
                </div>
                <div class="control-group" style="display:none;">
                    <label class="control-label" for="allDayCB">하루종일</label>
                    <div class="controls">
                        <label class="uniform">
                            <input class="uniform_on" type="checkbox" id="allDayCB" value="option1">
                            <input type="hidden" id="all_day" name="all_day" value="" />
                        </label>
                    </div>
                </div>
                <div class="control-group" id="endDiv">
                    <label class="control-label" for="focusedInput">종료일시</label>
                    <div class="controls input-append date form_datetime_end" data-date="" data-date-format="•yyyy-mm-dd hh:ii" data-link-field="end" style="margin-left: 20px;">
                        <input size="16" type="text" value="" readonly style="background-color: #FFF;" id="end_text">
                        <span class="add-on"> <i class="icon-remove" style="margin-right:0px;"></i></span>
                        <span class="add-on"> <i class="icon-calendar" style="margin-right:0px;"></i></span>
                    </div>
                    <input type="hidden" id="end" name="end" value="" />
                    <br/>
                </div>
                <div class="control-group">
                    <label class="control-label" for="focusedInput">일정</label>
                    <div class="controls">
                        <input class="input-xlarge focused" type="text" id="title" name="title" placeholder="Enter text ..." maxlength="50" style="width:350px;">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="focusedInput">내용(상세)</label>
                    <div class="controls">
                        <textarea class="input-xlarge textarea" placeholder="Enter text ..." id="desc" name="desc" style="width:350px; min-height:80px;"></textarea>
                    </div>
                </div>
            </fieldset>
        </form>
    </div>
    <div class="modal-footer">
        <a class="btn btn-primary" href="#" onclick="addEvent()">등록</a>
        <a data-dismiss="modal" class="btn" href="#">취소</a>
    </div>
</div>
<!-- New Event Modal End-->
<script type="text/javascript">
    $('.form_datetime_start').datetimepicker({
        language: 'kr',
        initialDate: null,
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        showMeridian: 1,
        linked: true,
        format: "yyyy-mm-dd hh:ii"
    });
    $('.form_datetime_end').datetimepicker({
        language: 'kr',
        initialDate: null,
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        showMeridian: 1,
        linked: true,
        format: "yyyy-mm-dd hh:ii"
    });

    function addEvent() {
        var startDate = $('#start').val();
        var endDate = $('#end').val();
        var title = $('#title').val();
        var desc = $('#desc').val();
        var allDay = true;

        if (title == "") {
            alert('일정을 입력해주세요');
            return;
        } else {
            if (compareDate(startDate, endDate)) {
                if (startDate.match('00:00')) {
                    allDay = true;
                    $('#all_day').val(1);
                } else {
                    allDay = false;
                    $('#all_day').val(2);
                }

                var formData = $("#eventForm").serialize();
                $.ajax({
                    type: "POST",
                    url: "/event/process",
                    cache: false,
                    data: formData,
                    success: function(data, textStatus, jqXHR) { 
                    	/*alert('success');*/ 
                        $('#calendar').fullCalendar('renderEvent', {
                                title: title,
                                start: startDate,
                                end: endDate,
                                allDay: allDay,
                                id: data,
                                memberId: "${member_id}"
                            },
                            true // make the event "stick"
                        );
                    },
                    error: function(jqXHR, textStatus, errorThrown) { /*alert('error');*/ }
                });
                $('#newEventModal').modal('hide');
            } else {
                alert('기간을 확인해주세요');
                return;
            }
        }
    }
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
        //alert(eventId);
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