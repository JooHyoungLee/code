<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>재난안전본부</title>
    <link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon">
	<link rel="icon" href="/images/favicon.ico" type="image/x-icon">
    <!-- Bootstrap -->
    <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
    <link href="/assets/styles.css" rel="stylesheet" media="screen">
     <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <script src="/vendors/modernizr-2.6.2-respond-1.1.0.min.js"></script>
    <script src="/js/commons.js"></script>
    <script src="/js/validator.js"></script>
  </head>
  <body id="login" style="  margin-left: 0px; margin-top: 0px; margin-right: 0px; margin-bottom: 0px; background:url(/images/background2.jpg) no-repeat center top;">
	<div class="container">
	  <div class="row">
		<div class="Absolute-Center is-Responsive">
		  <div id="logo-container"></div>
		  <div class="col-sm-12 col-md-10 col-md-offset-1">
			<form class="form-signin" action="loginProcess" method="post" name="loginForm">
				<h2 class="form-signin-heading">Please sign in</h2>
				<input type="text" class="input-block-level" placeholder="Email address" name="id">
				<input type="password" class="input-block-level" placeholder="Password" name="pw" id="pw">
				<label class="checkbox">
				  <input type="checkbox" value="remember-me" id="idSaveCheck"> Remember me
				</label>
				<button class="btn btn-large btn-success" type="button" onclick="join()">Join</button>
				<button class="btn btn-large btn-primary" style="float: right;" type="button" onclick="login()">Login</button>
				<!-- <label class="text" style="margin-top: 10px;">
				  <p>Forgot Password?</p>
				</label> -->
			</form>
		  </div>  
		</div>    
	  </div>
	</div> <!-- /container -->
	<a href="#myAlert" data-toggle="modal" id="myAlertBtn" class="btn btn-danger" style="display:none;">Alert</a>
	<div id="myAlert" class="modal hide" style="width: 280px; top: 40%; left: 61%;">
		<div class="modal-header">
			<button data-dismiss="modal" class="close" type="button">&times;</button>
			<h3>로그인</h3>
		</div>
		<div class="modal-body">
			<p>로그인 실패</p>
		</div>
		<div class="modal-footer">
			<a data-dismiss="modal" class="btn" href="#">확인</a>
		</div>
	</div>
    <script src="/vendors/jquery-1.9.1.min.js"></script>
    <script src="/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript">
    	function login()
    	{
    		if(!checkNull(document.getElementById('id')))
    		{
    			alert('이메일을 입력해주세요');
    		}
    		else if(!checkNull(document.getElementById('pw')))
    		{
    			alert('비밀번호를 입력해주세요');
    		}
    		else
    		{
    			//submit
    			document.loginForm.submit();
    		}
    	}
    	function join()
    	{
    		location.href = "/member/join";
    	}
    </script>
    <script type="text/javascript">
	    $(document).ready(function(){
	        // 저장된 쿠키값을 가져와서 ID 칸에 넣어준다. 없으면 공백으로 들어감.
	        var alertParam = getParam("parm");
	        if(alertParam == 1)
	        {
	        	alert("로그인 실패");
	        }   
	        $("#pw").keypress(function(e){ // 체크박스에 변화가 있다면,
	        	if (e.which == 13) {/* 13 == enter key@ascii */
	        		login();
	        	}
	        });
	        
	        var userInputId = getCookie("userInputId");
	        $("input[name='id']").val(userInputId);
 
	        if($("input[name='id']").val() != ""){ // 그 전에 ID를 저장해서 처음 페이지 로딩 시, 입력 칸에 저장된 ID가 표시된 상태라면,
	            $("#idSaveCheck").attr("checked", true); // ID 저장하기를 체크 상태로 두기.
	        }
	         
	        $("#idSaveCheck").change(function(){ // 체크박스에 변화가 있다면,
	            if($("#idSaveCheck").is(":checked")){ // ID 저장하기 체크했을 때,
	                var userInputId = $("input[name='id']").val();
	                setCookie("userInputId", userInputId, 7); // 7일 동안 쿠키 보관
	            }else{ // ID 저장하기 체크 해제 시,
	                deleteCookie("userInputId");
	            }
	        });
	         
	        // ID 저장하기를 체크한 상태에서 ID를 입력하는 경우, 이럴 때도 쿠키 저장.
	        $("input[name='id']").keyup(function(){ // ID 입력 칸에 ID를 입력할 때,
	            if($("#idSaveCheck").is(":checked")){ // ID 저장하기를 체크한 상태라면,
	                var userInputId = $("input[name='id']").val();
	                setCookie("userInputId", userInputId, 7); // 7일 동안 쿠키 보관
	            }
	        });
	    });
    </script>
  </body>
</html>