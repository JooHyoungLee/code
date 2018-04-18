<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html class="no-js">
<head>
    <title>재난안전본부</title>
    <link rel="icon" href="/images/favicon.ico" type="image/x-icon" />
    <link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon" />

    <!-- Bootstrap -->
    <link href="/bootstrap/css/bootstrap.css" rel="stylesheet" media="screen">
    <link href="/bootstrap/css/bootstrap-responsive.css" rel="stylesheet" media="screen">
    <link href="/vendors/easypiechart/jquery.easy-pie-chart.css" rel="stylesheet" media="screen">
    <link href="/assets/styles.css" rel="stylesheet" media="screen">
    <link href="/assets/DT_bootstrap.css" rel="stylesheet" media="screen">
    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <script src="/vendors/modernizr-2.6.2-respond-1.1.0.min.js"></script>
    <script src="/js/commons.js"></script>
    <script src="/js/validator.js"></script>
    <script type="text/javascript">
    </script>
</head>

<body>
    <!-- Head Start -->
    <div class="navbar navbar-fixed-top">
        <div class="navbar-inner">
            <div class="container-fluid">
                <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </a>
                <a class="brand" href="/"><img alt="nav_logo" src="/images/nav_logo.png"></a>
            </div>
        </div>
    </div>
    <div class="container-fluid">
        <!-- Head End -->
        <!-- Content Start -->
        <div class="row-fluid">
            <!-- block -->
            <div class="block">
                <div class="navbar navbar-inner block-header">
                    <div class="muted pull-left">&nbsp;</div>
                </div>
                <div class="block-content collapse in">
                    <div class="span12">
                        <form class="form-horizontal" id="joinForm" name="joinForm" method="post" action="/member/joinProcess">
                            <fieldset>
                                <legend>회원가입</legend>
                                <input type="hidden" id="email_ck" name="email_ck" value="0" />
                                <input type="hidden" id="name_ck" name="name_ck" value="0" />
                                <input type="hidden" id="cell_ck" name="cell_ck" value="0" />
                                <input type="hidden" id="tele_ck" name="tele_ck" value="0" />
                                <input type="hidden" id="pw_ck" name="pw_ck" value="0" />
                                <div class="control-group">
                                    <label class="control-label" for="Email"><strong>*Email</strong></label>
                                    <div class="controls">
                                        <input class="input-xlarge focused" id="email" name="email" type="text" placeholder="업무용 이메일">
                                        <p id="email_check_text" class="help-block" style="display:none;"></p>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="name"><strong>*이름</strong></label>
                                    <div class="controls">
                                        <input class="input-xlarge" id="name" name="name" type="text" value="">
                                        <p id="name_ck_text" class="help-block" style="display:none;"></p>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="password"><strong>*비밀번호</strong></label>
                                    <div class="controls">
                                        <input class="input-xlarge" id="password" name="password" type="password" value="">
                                        <p id="pw_text" class="help-block" style="display:none;"></p>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="password_ck"><strong>*비밀번호 확인</strong></label>
                                    <div class="controls">
                                        <input class="input-xlarge" id="password_ck" name="password_ck" type="password" value="">
                                        <p id="pw_ck_text" class="help-block" style="display:none;"></p>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="group_select_list"><strong>*소속 팀</strong></label>
                                    <div class="controls">
                                        <select id="group_select_list" name="group_select_list">
                                            <c:forEach var="item" items="${group_list}" varStatus="counter">
                                                <option value="${item.group_id}">${item.group_name}</option>
                                            </c:forEach>
                                        </select>
                                        <input type="hidden" id="group_id" name="group_id" value="GR001" />
                                        <!-- BCM팀 -->
                                        <span class="help-inline">&nbsp;<strong>*직위</strong></span>
                                        <select id="grade_select_list" name="grade_select_list">
                                            <option>소속 팀을 선택해주세요</option>
                                        </select>
                                        <input type="hidden" id="grade_id" name="grade_id" value="" />
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="cell_phone"><strong>*휴대전화</strong></label>
                                    <div class="controls">
                                        <input class="input-xlarge" id="cell_phone" name="cell_phone" type="text" placeholder="010-xxxx-xxxx" value="">
                                        <p id="cell_ck_text" class="help-block" style="display:none;"></p>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="tele_phone">내선번호</label>
                                    <div class="controls">
                                        <input class="input-xlarge" id="tele_phone" name="tele_phone" type="text" placeholder="031-xxxx-xxxx" value="">
                                        <p id="tele_ck_text" class="help-block" style="display:none;"></p>
                                    </div>
                                </div>

                                <div class="form-actions">
                                    <button type="button" class="btn btn-primary" onclick="joinMember()">가입</button>
                                    <button type="button" class="btn" onclick="moveLogin()">취소</button>
                                </div>
                            </fieldset>
                        </form>
                    </div>
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
        function moveLogin() {
            location.href = "/";
        }

        function joinMember() {
            var email_ck = document.getElementById('email_ck').value;
            var name_ck = document.getElementById('name_ck').value;
            var pw_ck = document.getElementById('pw_ck').value;
            var cell_ck = document.getElementById('cell_ck').value;
            var tele_ck = document.getElementById('tele_ck').value;

            var email = document.getElementById('email').value;
            var name = document.getElementById('name').value;
            var password = document.getElementById('password').value;
            var cell_phone = document.getElementById('cell_phone').value;
            var tele_phone = document.getElementById('tele_phone').value;

            if (email_ck == 0 || email == "") {
                alert('이메일을 확인해주세요');
                document.getElementById('email_ck').value = 0;
                return;
            }
            if (name_ck == 0 || name == "") {
                alert('이름을 확인해주세요');
                document.getElementById('name_ck').value = 0;
                return;
            }
            if (pw_ck == 0 || password == "") {
                alert('비밀번호를 확인해주세요');
                document.getElementById('pw_ck').value = 0;
                return;
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
            document.joinForm.submit();
        }
    </script>

    <script type="text/javascript">
        $(document).ready(function() {
            var group_init_id = document.getElementById('group_id').value;
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
            $('#password').focusout(function() {
                var password = document.getElementById('password').value;
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
                var password = document.getElementById('password').value;
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
                if (!checkPhone(tele_phone)) {
                    document.getElementById('tele_ck').value = 0;
                    $('#tele_ck_text').css('display', 'block');
                    $('#tele_ck_text').css('color', 'gray');
                    $('#tele_ck_text').html("전화번호형식이 잘못되었습니다 (031-xxxx-xxxx)");
                } else {
                    document.getElementById('tele_ck').value = 1;
                    $('#tele_ck_text').css('display', 'none');
                }
            });
        });
    </script>

    <hr>
    <footer style="height:30px;">
        <p>&copy; Chahoo Crop 2015</p>
    </footer>

    <!--/.fluid-container-->
    <!-- Footer End -->
</body>
</html>