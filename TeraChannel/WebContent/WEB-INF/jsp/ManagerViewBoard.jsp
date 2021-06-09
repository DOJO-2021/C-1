<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- 各配置はcssファイル上でabsoluteで場所を指定すればよいのでタグの
順番はある程度配慮するものの確定ではない -->
<html>
<head>
<meta charset="UTF-8">
<title>てらちゃんねる</title>
<link rel="stylesheet" href="/TeraChannel/css/ManagerViewBoard.css">
</head>
<body>
	<a href="ManagerMenu.jsp"><img class="logo" src=""
		alt="TERACO風メニュー画面へ"></a>
	<h1>
		てらちゃんねる（ロゴ画像を埋め込むための仮のイメージ） <a href="ManagerViewMenu.jsp"> <img
			class="board" src="" alt="管理者用掲示板メニューページへ"></a>
	</h1>
	<nav class="nav">
		<li><a href="/TeraChannel/ManagerMenuServlet">メニューページに戻る</a>
	</nav>
	<form>
		<input class="search" type="text" name="search" placeholder="検索内容">
		<input class="searchButton" type="button" name="searchButton" value="検索">
		<br>
	</form>



	<!--  投稿内容自体を表示した後、forEach文で各返信を出力 -->
	<!--  例:<div>投稿内容 <form><forEach>返信内容</form></div>-->



</body>
</html>