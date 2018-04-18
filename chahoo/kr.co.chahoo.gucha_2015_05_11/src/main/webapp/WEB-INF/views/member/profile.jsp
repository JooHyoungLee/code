<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/header.jsp" %>
<!-- Content Start -->
<div class="span9" id="content" style="display: table; float:none; margin-left: auto; margin-right: auto; display: inline-block;">
<div class="row-fluid">
    <!-- block -->
    <div class="block">
        <div class="navbar navbar-inner block-header">
            <div class="muted pull-left">회원정보</div>
        </div>
        <div class="block-content collapse in">
            
                <form class="form-horizontal" id="profileForm" name="profileForm" method="post" action="/member/profileProcess">
                    <fieldset>
                        <input type="hidden" id="cell_ck" name="cell_ck" value="1"/>
                        <input type="hidden" id="tele_ck" name="tele_ck" value="1"/>
                        <input type="hidden" id="pw_ck" name="tele_ck" value="0"/>
                        <div class="control-group">
                            <label class="control-label" for="Email">Email</label>
                            <div class="controls">
                                <p class="help-block" style="margin-top:5px;">${memberDto.email}</p>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" for="name">이름</label>
                            <div class="controls">
                                <p class="help-block" style="margin-top:5px;">${memberDto.name}</p>
                            </div>
                        </div>
                        <div class="control-group" id="passwordBtnDiv">
                            <label class="control-label" for="passwordBtn">비밀번호</label>
                            <div class="controls">
                                 <button type="button" class="btn" style="margin-top:1px;" onclick="openPassword()">변경</button>
                            </div>
                        </div>
                        <div class="control-group" id="passwordDiv" style="display:none;">
                            <label class="control-label" for="new_password">비밀번호</label>
                            <div class="controls">
                                <input class="input-xlarge" id="new_password" name="new_password" type="password" value="">
                                <p id="pw_text" class="help-block" style="display:none;"></p>
                            </div>
                        </div>
                        <div class="control-group" id="passwordCkDiv" style="display:none;">
                            <label class="control-label" for="password_ck">비밀번호 확인</label>
                            <div class="controls">
                                <input class="input-xlarge" id="password_ck" name="password_ck" type="password" value="">
                                <p id="pw_ck_text" class="help-block" style="display:none;"></p>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" for="group_select_list">소속 팀</label>
                            <div class="controls">
                                <select id="group_select_list" name="group_select_list">
                                    <c:forEach var="item" items="${group_list}" varStatus="counter">
										<option value="${item.group_id}">${item.group_name}</option>
									</c:forEach>
                                </select>
                                <input type="hidden" id="group_id" name="group_id" value="${memberDto.group_id}"/><!-- BCM팀 -->
                                <span class="help-inline">&nbsp;직위</span>
                                <select id="grade_select_list" name="grade_select_list">
                                    <option>소속 팀을 선택해주세요</option>
                                </select>
                                <input type="hidden" id="grade_id" name="grade_id" value="${memberDto.grade_id}"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" for="cell_phone">휴대전화</label>
                            <div class="controls">
                                <input class="input-xlarge" id="cell_phone" name="cell_phone" type="text" placeholder="010-xxxx-xxxx" value="${memberDto.cell_phone}">
                                <p id="cell_ck_text" class="help-block" style="display:none;"></p>
                            </div>
                        </div>
						<div class="control-group">
                            <label class="control-label" for="tele_phone">내선번호</label>
                            <div class="controls">
                                <input class="input-xlarge" id="tele_phone" name="tele_phone" type="text" placeholder="031-xxxx-xxxx" value="${memberDto.tele_phone}">
                                <p id="tele_ck_text" class="help-block" style="display:none;"></p>
                            </div>
                        </div>                                
                        <div class="form-actions">
                            <button type="button" class="btn btn-primary" onclick="profileMember()">저장</button>
                            <button type="button" class="btn" onclick="moveMain()">취소</button>
                        </div>
                    </fieldset>
                </form>
        </div>
    </div>
    <!-- Content End -->
    <!-- Footer Start -->
</div>
 </div>
<script src="/vendors/jquery-1.9.1.min.js"></script>
<script src="/bootstrap/js/bootstrap.min.js"></script>
<script src="/vendors/jGrowl/jquery.jgrowl.js"></script>
<script src="/assets/scripts.js"></script>

<script type="text/javascript">
    function moveMain() {
        location.href = "/main/";
    }

    function openPassword() {
        $('#passwordBtnDiv').css('display', 'none');
        $('#passwordDiv').css('display', 'block');
        $('#passwordCkDiv').css('display', 'block');
    }

    function profileMember() {
        var pw_ck = document.getElementById('pw_ck').value;
        var cell_ck = document.getElementById('cell_ck').value;
        var tele_ck = document.getElementById('tele_ck').value;

        var password = document.getElementById('new_password').value;
        var cell_phone = document.getElementById('cell_phone').value;
        var tele_phone = document.getElementById('tele_phone').value;

        if (password != "") {
            if (pw_ck == 0) {
                alert('비밀번호를 확인해주세요');
                document.getElementById('pw_ck').value = 0;
                return;
            }
        }
        if (cell_ck == 0 || cell_phone == "") {
            alert('휴대전화번호를 확인해주세요');
            document.getElementById('cell_ck').value = 0;
            return;
        }
        if (tele_phone != "" && tele_ck == 0) {
            if (confirm("내선번호를 잘못입력하셨습니다\n계속 진행하시겠습니까? \n (잘못입력된 번호는 저장되지 않습니다)") == true) { //확인
                document.getElementById('tele_phone').value = "";
            } else { //취소
                return;
            }
        }
        document.profileForm.submit();
    }
</script>

<script type="text/javascript">
    $(document).ready(function() {
        document.getElementById('new_password').value = "";
        var group_init_id = document.getElementById('group_id').value;
        $("#group_select_list").val("${memberDto.group_id}");
        var grade_init_url = "/member/" + group_init_id + "/grade/list";
        $.ajax({
            url: grade_init_url,
            type: "get",
            dataType: "text",
            processData: false,
            contentType: false,
            success: function(data, textStatus, jqXHR) {
                $('#grade_select_list').html("");
                var obj = eval("(" + data + ")");
                var grade_id_temp = "${memberDto.grade_id}";
                $(obj).each(function(index, item) {
                    if(grade_id_temp == item.grade_id)
                    {
                    	$('#grade_select_list').append("<option value=\"" + item.grade_id + "\" selected=\"selected\">" + item.grade_name + "</option>");
                    }
                    else
                    {
                    	$('#grade_select_list').append("<option value=\"" + item.grade_id + "\">" + item.grade_name + "</option>");
                    }
                });

            },
            error: function(jqXHR, textStatus, errorThrown) {
                //alert(textStatus);
            }
        });

        $("#grade_select_list").val("${memberDto.grade_id}");

        $('#group_select_list').change(function() {
            var group_id = $("#group_select_list option:selected").val();
            document.getElementById('group_id').value = group_id;
            var grade_url = "/member/" + group_id + "/grade/list";
            $.ajax({
                url: grade_url,
                type: "get",
                dataType: "text",
                processData: false,
                contentType: false,
                success: function(data, textStatus, jqXHR) {
                    $('#grade_select_list').html("");
                    var obj = eval("(" + data + ")");
                    $(obj).each(function(index, item) {
                        $('#grade_select_list').append("<option value=\"" + item.grade_id + "\">" + item.grade_name + "</option>");
                        if (index == 0) {
                            document.getElementById('grade_id').value = item.grade_id;
                        }
                    });
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    //alert(textStatus);
                }
            });
        });
        $('#grade_select_list').change(function() {
            var grade_id = $("#grade_select_list option:selected").val();
            document.getElementById('grade_id').value = grade_id;
        });
        $('#email').focusout(function() {
            var email_text = document.getElementById('email').value;

            if (!checkEmail(email_text)) {
                document.getElementById('email_ck').value = 0;
                $('#email_check_text').css('display', 'block');
                $('#email_check_text').css('color', 'red');
                $('#email_check_text').html("잘못된 이메일입니다");
                return;
            }

            var check_email_url = "/member/" + email_text + "/check";
            $.ajax({
                url: check_email_url,
                type: "get",
                dataType: "text",
                processData: false,
                contentType: false,
                success: function(data, textStatus, jqXHR) {
                    if (data > 0) {
                        document.getElementById('email_ck').value = 0;
                        $('#email_check_text').css('display', 'block');
                        $('#email_check_text').css('color', 'red');
                        $('#email_check_text').html("사용중인 이메일입니다");
                    } else {
                        document.getElementById('email_ck').value = 1;
                        $('#email_check_text').css('display', 'block');
                        $('#email_check_text').css('color', 'gray');
                        $('#email_check_text').html("사용가능한 이메일입니다");
                    }
                },
                error: function(jqXHR, textStatus, errorThrown) {}
            });
        });
        $('#new_password').focusout(function() {
            var password = document.getElementById('new_password').value;
            if (password.length < 4) {
                document.getElementById('pw_ck').value = 0;
                $('#pw_text').css('display', 'block');
                $('#pw_text').css('color', 'red');
                $('#pw_text').html("비밀번호는 4글자 이상입니다");
            } else {
                $('#pw_text').css('display', 'none');
                document.getElementById('pw_ck').value = 0;
            }
        });
        $('#password_ck').focusout(function() {
            var password = document.getElementById('new_password').value;
            var password_ck = document.getElementById('password_ck').value;
            if (password != password_ck) {
                document.getElementById('pw_ck').value = 0;
                $('#pw_ck_text').css('display', 'block');
                $('#pw_ck_text').css('color', 'red');
                $('#pw_ck_text').html("비밀번호가 서로 다릅니다");
            } else {
                document.getElementById('pw_ck').value = 1;
                $('#pw_ck_text').css('display', 'none');
            }

        });
        $('#name').focusout(function() {
            var name = document.getElementById('name').value;
            if (name.length < 3) {
                document.getElementById('name_ck').value = 0;
                $('#name_ck_text').css('display', 'block');
                $('#name_ck_text').css('color', 'red');
                $('#name_ck_text').html("이름은 3글자 이상입니다");
            } else {
                document.getElementById('name_ck').value = 1;
                $('#name_ck_text').css('display', 'none');
            }
        });
        $('#cell_phone').focusout(function() {
            var cell_phone = document.getElementById('cell_phone').value;
            if (!checkPhone(cell_phone)) {
                document.getElementById('cell_ck').value = 0;
                $('#cell_ck_text').css('display', 'block');
                $('#cell_ck_text').css('color', 'red');
                $('#cell_ck_text').html("전화번호형식이 잘못되었습니다  (010-xxxx-xxxx)");
            } else {
                document.getElementById('cell_ck').value = 1;
                $('#cell_ck_text').css('display', 'none');
            }
        });
        $('#tele_phone').focusout(function() {
            var tele_phone = document.getElementById('tele_phone').value;
            if (tele_phone != "") {
                if (!checkPhone(tele_phone)) {
                    document.getElementById('tele_ck').value = 0;
                    $('#tele_ck_text').css('display', 'block');
                    $('#tele_ck_text').css('color', 'gray');
                    $('#tele_ck_text').html("전화번호형식이 잘못되었습니다 (031-xxxx-xxxx)");
                } else {
                    document.getElementById('tele_ck').value = 1;
                    $('#tele_ck_text').css('display', 'none');
                }
            }
        });
    });
</script> 
	
	
<%@include file="/common/footer.jsp" %>