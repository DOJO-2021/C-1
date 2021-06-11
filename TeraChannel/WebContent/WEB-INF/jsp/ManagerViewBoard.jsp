<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- 各配置はcssファイル上でabsoluteで場所を指定すればよいのでタグの
順番はある程度配慮するものの確定ではない -->
<html>
<head>
<meta charset="UTF-8">
<title>てらちゃんねる</title>
<link rel="stylesheet" href="/TeraChannel/css/ViewBoard.css">
</head>
<body>
	<!-- ヘッダーここから -->
	<header>
		<a href="Menu.jsp"><img class="logo" src="image/teraco.jpg"
			alt="TERACO風メニュー画面へ"></a>
		<h1>
			<a href="ViewMenu.jsp"> <img class="board_image"
				src="image/keijiban.jpg" alt="管理者用掲示板メニューページへ"></a>
		</h1>
		<nav class="nav">
			<a href="/TeraChannel/BoardServlet">投稿</a> <a
				href="/TeraChannel/MenuServlet">メニューページに戻る</a>
		</nav>
	</header>
	<!-- ヘッダーここまで -->
	<!-- メインここから -->
	<main>

		<div class="yokonarabi">
			<!-- マニュアル表示部分 -->
			<div class="manual">
				<h2>ルール</h2>
				<p>
					皆さんが快適に過ごすために以下のルールを守って使用して下さい。<br>
					1.個人名の表記の有無に限らず特定個人を揶揄したような悪口を投稿したり誹謗中傷はしないでください。<br>
					相手を罵るなどの挑発的な言葉や、言葉尻をとらえるような揚げ足取り等の書き込みも、トラブルの元となりますのでやめてください。<br>
					2.この掲示板は皆様の善意の書き込みによって成り立っていますが、掲示板に投稿された情報は必ずしも正確であるとは限りませんので、自己の責任と判断で掲示板をご利用ください。<br>
					3.安全性に疑いのあるサイトのURLの記載はお控えください。<br>
					以上のルールが守れなかった場合、該当の投稿・返信の削除及び匿名化を解除し、実名を公表させていただく場合がありますので予めご了承ください。
				</p>
			</div>

			<!-- 投稿内容表示部分 -->
			<form>
				<div class="board">
					<h3>投稿タイトル(今はhタグで代用)</h3>
					<p class="board_main">投稿内容(今はpタグで代用：今後変わる可能性あり)</p>
					<br>
					<div class="reaction">
						<div class="smileCount">
							<image class="smile" onclick="reactionCount()"
								src="image/smile.jpg" alt="リアクション（笑顔）"></image>
						</div>
						<div class="shockCount">
							<image class="shock" onclick="reactionCount()"
								src="image/shock.jpg" alt="リアクション（驚愕）"></image>
						</div>
						<div class="tearCount">
							<image class="tear" onclick="reactionCount()"
								src="image/tear.jpg" alt="リアクション（感涙）"></image>
						</div>
					</div>
					<!-- ここから返信欄（forEach部分） -->
					<!-- 矢印の部分はおそらく画像挿入の形 -->
					<p class="reply">返信欄(この部分はforEach文で記載、現在はpタグで代用)</p>
					<!-- 返信の最後の部分はtextareaで表示（forEach文の外） -->

					<textarea class="reply_text" name="reply"
						placeholder="返信内容:最後の返信欄はform形式" rows="4" cols="60"></textarea>
					<input class="reply_button" type="button" name="replyButton"
						value="検索"> <br>
				</div>
			</form>

			<form>
				<input class="search" type="text" name="search" placeholder="検索内容">
				<input class="searchButton" type="button" name="searchButton"
					value="検索"> <br>
			</form>

		</div>
	</main>
	<!-- メインここまで -->

	<!--  投稿内容自体を表示した後、forEach文で各返信を出力 -->
	<!--  例:<div>投稿内容 <form><forEach>返信内容</form></div>-->

<!-- ここからjavaScript -->
	<script>
		'use strict';
		function reactionCount() {

		}
	</script>
<!-- ここまでjavaScript -->

</body>
</html>