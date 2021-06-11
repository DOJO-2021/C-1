<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<div class="teraco">
			<a href="/TeraChannel/MenuServlet"><img
				src="/TeraChannel/image/teraco.jpg" alt="TERACO"></a>
		</div>
		<div class="terachan">
			<a href="/TeraChannel/ViewMenuServlet"><img
				src="/TeraChannel/image/keijiban.jpg" alt="てらちゃんねる"></a>
		</div>
		<div class="navi">
			<button onclick="location.href='/TeraChannel/BoardServlet'"
				class="button_board">投稿</button>
			<button onclick="location.href='/TeraChannel/MenuServlet'"
				class="button_menu">メニューページへ戻る</button>
		</div>
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
			<form class="board_form">
				<div class="board">

					<p class="postDate">投稿日時６月９日</p>

					<h3>投稿タイトル(今はhタグで代用)</h3>
					<p class="board_main">投稿内容(今はpタグで代用：今後変わる可能性あり)</p>
					<div class="editDelete">
						<input class"edit" type="button" name="editButton" value="編集">
						<input class"delete" type="button" name="deleteButton" value="削除">
					</div>
			<!-- リアクション表示部分 -->
					<div class="reaction">
						<div>
							<image class="smile" onclick="reactionSmileCount()"
								src="image/smile.jpg" alt="リアクション（笑顔）"></image>
							<p class="reactionCount" id="smile">125</p>
						</div>
						<div>
							<image class="shock" onclick="reactionShockCount()"
								src="image/shock.jpg" alt="リアクション（驚愕）"></image>
							<p class="reactionCount" id="shock">${e.m_id}125</p>
						</div>
						<div>
							<image class="tear" onclick="reactionTearCount()"
								src="image/tear.jpg" alt="リアクション（感涙）"></image>
							<p class="reactionCount" id="tear">${e.m_id}125</p>
						</div>
					</div>
					<!-- ここから返信欄（forEach部分） -->
					<!-- 矢印の部分はおそらく画像挿入の形 -->
					<p class="updateDate">${e.m_id}投稿日時６月１０日</p>
					<p class="reply">返信欄(この部分はforEach文で記載、現在はpタグで仮表現)</p>
					<div class="editDelete">
						<input class"edit" type="button" name="editButton" value="編集">
						<input class"delete" type="button" name="deleteButton" value="削除">
					</div>




					<p class="updateDate">${e.m_id}投稿日時６月１０日</p>
					<p class="reply">返信欄(この部分はforEach文で記載、現在はpタグで仮表現)</p>
					<div class="editDelete">
						<input class"edit" type="button" name="editButton" value="編集">
						<input class"delete" type="button" name="deleteButton" value="削除">
					</div>





					<p class="updateDate">${e.m_id}投稿日時６月１０日</p>
					<p class="reply">返信欄(この部分はforEach文で記載、現在はpタグで仮表現)</p>
					<div class="editDelete">
						<input class"edit" type="button" name="editButton" value="編集">
						<input class"delete" type="button" name="deleteButton" value="削除">
					</div>





					<p class="updateDate">${e.m_id}投稿日時６月１０日</p>
					<p class="reply">返信欄(この部分はforEach文で記載、現在はpタグで仮表現)</p>
					<div class="editDelete">
						<input class"edit" type="button" name="editButton" value="編集">
						<input class"delete" type="button" name="deleteButton" value="削除">
					</div>
					<!-- 返信の最後の部分はtextareaで表示（forEach文の外） -->

					<textarea class="reply_text" name="reply"
						placeholder="返信内容:最後の返信欄はform形式" rows="4" cols="60"></textarea>
					<input class="reply_button" type="button" name="replyButton"
						value="返信"> <br>
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
		let smileTF;
		let shockTF;
		let tearTF;
		let count;
		function reactionSmileCount() {
			count = parseInt(document.getElementById("smile"));
			console.log(count);
			count++;
			document.getElementById("smile").innerHTML="<p>" + count + "</p>";
			console.log(count);

		}
		function reactionShockCount() {

		}
		function reactionTearCount() {

		}
	</script>
	<!-- ここまでjavaScript -->

</body>
</html>