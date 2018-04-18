<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<!-- <html class="no-js"> -->
<head>
    <title>재난안전본부</title>
    <link rel="icon" href="/images/favicon.ico" type="image/x-icon" />
	<link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon" />

    <!-- Bootstrap -->
    <link href="/bootstrap/css/bootstrap.css" rel="stylesheet" media="screen">
    <link href="/bootstrap/css/bootstrap-responsive.css" rel="stylesheet" media="screen">
    <!--<link href="/bootstrap/css/non-responsive.css" rel="stylesheet" media="screen">  
    
     <link href="/bootstrap/css/non-responsive.css" rel="stylesheet" media="screen">  
    
    <link href="/vendors/easypiechart/jquery.easy-pie-chart.css" rel="stylesheet" media="screen">-->
    <link href="/assets/styles.css" rel="stylesheet" media="screen">
    <!-- <link href="/assets/DT_bootstrap.css" rel="stylesheet" media="screen"> -->
    <link href="/vendors/fullcalendar/fullcalendar.css" rel="stylesheet">
    <link href="/vendors/bootstrap-datetimepicker.css" rel="stylesheet">
     <link href="/vendors/fullcalendar/fullcalendar.print.css" rel="stylesheet" media="print">
    <!--<link rel="stylesheet" type="text/css" href="/assets/print.css" media="print" />  -->
    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <!-- <script src="/vendors/modernizr-2.6.2-respond-1.1.0.min.js"></script>  -->

    <script src="/js/commons.js"></script>
    <script src="/js/validator.js"></script>
    <script type="text/javascript">

    
    </script>
</head>

<body>
    <!-- Head Start -->
    <div class="navbar navbar-fixed-top" style="min-width:700px;">
        <div class="navbar-inner">
            <div class="container-fluid">
                <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </a>
                <a class="brand" href="/main/"><img alt="nav_logo" src="/images/nav_logo.png"></a>
                <div class="nav-collapse collapse">
                    <ul class="nav pull-right">
                        <li class="dropdown">
                            <a href="#" role="button" class="dropdown-toggle" data-toggle="dropdown"> <i class="icon-user"></i> <%=(String)request.getSession().getAttribute("memberName") %> <i class="caret"></i></a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a tabindex="-1" href="/member/profile">Profile</a>
                                </li>
                                <li class="divider"></li>
                                <li>
                                    <a tabindex="-1" href="/logout">Logout</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                    <ul class="nav">
                        <li id="noticeNav">
                            <a href="#" onclick="moveBoard('notice', 'notice')">공지사항</a>
                        </li>
                        <li id="eventNav">
                            <a href="/event/main">일정관리</a>
                        </li>
                        <li class="dropdown" id="boardNav">
                            <a href="#" data-toggle="dropdown" class="dropdown-toggle">게시판<b class="caret"></b></a>
                            <ul class="dropdown-menu" id="menu1">
                                <li>
                                    <a href="#" onclick="moveBoard('free', 'board')">자유게시판</a>
                                </li>
                                <li class="divider"></li>
                                <li>
                                    <a href="#" onclick="moveBoard('bcm', 'board')">BCM팀</a>
                                </li>
                                <li>
                                    <a href="#" onclick="moveBoard('disaster', 'board')">재난관리팀</a>
                                </li>
                                <li class="divider"></li>
                                <li>
                                    <a href="#" onclick="moveBoard('business', 'board')">사업공고</a>
                                </li>
                            </ul>
                        </li>
                        <li id="weekNav">
                            <a href="#" onclick="moveBoard('week', 'week')">주간업무</a>
                        </li>
                        <li class="dropdown" id="storeNav">
                            <a href="#" data-toggle="dropdown" class="dropdown-toggle">자료실<b class="caret"></b></a>
                            <ul class="dropdown-menu" id="menu2">
                                <li>
                                    <a href="#" onclick="moveBoard('store', '')">전체 자료실</a>
                                </li>
                                <li class="divider"></li>
                                <li>
                                    <a href="#" onclick="moveBoard('store','project')">프로젝트 자료실</a>
                                </li>
                                <li>
                                    <a href="#" onclick="moveBoard('store','technic')">기술 자료실</a>
                                </li>
                                <li class="divider"></li>
                                <li>
                                    <a href="#" onclick="moveBoard('store', 'law')">법률 자료실</a>
                                </li>
                                <li>
                                    <a href="#" onclick="moveBoard('store', 'paper')">논문 자료실</a>
                                </li>
                                <li class="divider"></li>
                                <li>
                                    <a href="#" onclick="moveBoard('store', 'form')">서식 자료실</a>
                                </li>
                                <li>
                                    <a href="#" onclick="moveBoard('store', 'normal_store')">일반 자료실</a>
                                </li>
                            </ul>
                        </li>

                    </ul>
                </div>
                <!--/.nav-collapse -->
            </div>
        </div>
    </div>
    <div class="container-fluid">
        <!-- Head End -->
        <!-- Content Start -->