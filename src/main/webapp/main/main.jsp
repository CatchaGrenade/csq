<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<html>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
		<!-- Google Chrome Frame也可以让IE用上Chrome的引擎: -->
		<meta name="renderer" content="webkit">
		<!--国产浏览器高速模式-->
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- 定义页面的最新版本 -->
		<meta name="description" content="网站简介" />
		<!-- 网站简介 -->
		<meta name="keywords" content="搜索关键字，以半角英文逗号隔开" />
		<title>青青</title>

		<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
		<script type="text/javascript" src="js/main.js"></script>
		<link rel="shortcut icon" href="images/favicon.ico"/>
		<link rel="bookmark" href="images/favicon.ico"/>
		<link rel="stylesheet" type="text/css" href="css/base.css">
		<link rel="stylesheet" type="text/css" href="css/iconfont.css">

		<link rel="stylesheet" type="text/css" href="/static/../layui/css/layui.css">
		<!--[if lt IE 9]>
		<script src="js/html5shiv.min.js"></script>
		<script src="js/respond.min.js"></script>
		<![endif]-->
		<script type="text/javascript" src="/static/../layui/layui.js"></script>
		<!-- 滚动条插件 -->
		<link rel="stylesheet" type="text/css" href="css/jquery.mCustomScrollbar.css">
		<script src="js/jquery-ui-1.10.4.min.js"></script>
		<script src="js/jquery.mousewheel.min.js"></script>
		<script src="js/jquery.mCustomScrollbar.min.js"></script>
		<script src="js/cframe.js"></script><!-- 仅供所有子页面使用 -->
		<!-- 公共样式 结束 -->

		<link rel="stylesheet" type="text/css" href="css/frameStyle.css">
	</head>
	<body>
	<!-- 左侧菜单 - 开始 -->
	<div class="frameMenu">
		<div class="logo">
			<img src="images/logo.png"/>
			<div class="logoText">
				<h1>青</h1>
				<p>QING</p>
			</div>
		</div>
		<div id = "menuContent" class="menu">

		</div>
	</div>
	<!-- 左侧菜单 - 结束 -->
	<div class="main">
		<!-- 头部栏 - 开始 -->
		<div class="frameTop">
			<img class="jt" src="images/top_jt.png"/>
			<div class="topMenu">
				<ul>
					<li><a href="javascript:void(0)" onclick="menuCAClick('../tgls/modify_password.html',this)"><i class="iconfont icon-yonghu1"></i>管理员</a></li>
					<li><a href="javascript:void(0)" onclick="menuCAClick('../tgls/modify_password.html',this)"><i class="iconfont icon-xiugaimima"></i>修改密码</a></li>
					<li><a href="login.jsp"><i class="iconfont icon-084tuichu"></i>注销</a></li>
				</ul>
			</div>
		</div>
		<!-- 头部栏 - 结束 -->

		<!-- 核心区域 - 开始 -->
		<div class="frameMain">
			<div class="title" id="frameMainTitle">
				<span><i class="iconfont icon-xianshiqi"></i>首页</span>
			</div>
			<div class="con">
				<iframe id="mainIframe" src="../tgls/qdAPI.html" scrolling="no"></iframe>
			</div>
		</div>
		<!-- 核心区域 - 结束 -->
	</div>
	</body>
</html>