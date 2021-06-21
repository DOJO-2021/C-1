<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			<div class="board">
				<form class="board_form" method="POST" action="/TeraChannel/ViewBoardServletTest">

					<!-- formタグで表示しないパラメータをリクエストスコープに格納するためのhiddenタグ -->
					<input type="hidden" name="BOARD_ID"value="${requestScope.bd.board_id}">
					<input type="hidden" name="BOARD_UPDATE"value="${requestScope.bd.board_update}">
					<p class="postDate">投稿日時:${requestScope.bd.board_update}</p>
					<input type="hidden" name="BOARD_TOPIC" value="${requestScope.bd.board_topic}">

					<h3>${requestScope.bd.board_topic}</h3>
					<input type="hidden" name="BOARD_MAIN"
						value="${requestScope.bd.board_main}">
					<!-- 投稿の出力 -->
					<p class="board_main">${requestScope.bd.board_main}</p>
					<div class="editDelete">
						<input class="edit" type="submit" name="SUBMIT" value="投稿:編集">
						<input class="delete" type="submit" name="SUBMIT" value="投稿:削除">
					</div>
					<!-- リアクション表示部分 -->
					<div class="reaction">
						<div>
						 <input type="hidden" id="hidden_smile" name="SMILE" value="${requestScope.bd.board_smile}">
							<image class="smile" onclick="reactionSmileCount()"
								src="image/smile.jpg" alt="リアクション（笑顔）"></image>
							<p class="reactionCount" id="smile" name="SMILE">${requestScope.bd.board_smile}</p>
						</div>
						<div>
							<input type="hidden" id="hidden_shock" name="SHOCK" value="${requestScope.bd.board_shock}">
							<image class="shock" onclick="reactionShockCount()"
								src="image/shock.jpg" alt="リアクション（驚愕）"></image>
							<p class="reactionCount" id="shock" name="SHOCK">${requestScope.bd.board_shock}</p>
						</div>
						<div>
						<input type="hidden" id="hidden_tear" name="TEAR" value="${requestScope.bd.board_tear}">
							<image class="tear" onclick="reactionTearCount()"
								src="image/tear.jpg" alt="リアクション（感涙）"></image>
							<p class="reactionCount" id="tear" name="TEAR">${requestScope.bd.board_tear}</p>
						</div>

					</div>
					<div class="registreaction">
						<input class="reactionRegist" type=submit name="SUBMIT"
							value="リアクション">
					</div>
					</form>
					<!-- ここから返信欄（forEach部分） -->
					<!-- 矢印の部分はおそらく画像挿入の形 -->
					<c:forEach var="e" items="${replyList}">
						<form class="board_form" method="POST" action="/TeraChannel/ViewBoardServletTest">
						<input type="hidden" name="REPLY_ID" value="${e.reply_id}">
						<input type="hidden" name="REPLY_DATE" value="${e.reply_date}">
						<input type="hidden" name="REPLY_MAIN" value="${e.reply_main}">

						<p class="updateDate">登録日:${e.reply_date}</p>

						<p class="reply" name="REPLY_MAIN">${e.reply_main}</p>

						<div class="editDelete">
							<input class="edit" type="submit" name="SUBMIT" value="返信:編集">
							<input class="delete" type="submit" name="SUBMIT" value="返信:削除">
						</div>
						</form>
					</c:forEach>


				<form class="board_form" method="POST" action="/TeraChannel/ViewBoardServletTest">
					<!-- 返信の最後の部分はtextareaで表示（forEach文の外） -->

					<input type="hidden" name="BOARD_ID" value="${requestScope.bd.board_id}">
					<input type="hidden" name="USER_ID" value="${sessionScope.USER_ID}">
					<textarea class="reply_text" name="REPLY_MAIN"
						placeholder="返信内容を入力してください" rows="4" cols="60"></textarea>
					<input class="reply_button" type="submit" name="SUBMIT" value="返信">
					<br>
				</form>
			</div>


			<form class="board_form" method="POST" action="/TeraChannel/ViewBoardServletTest">

				<input type="hidden" name="BOARD_ID"value="${requestScope.bd.board_id}">

				<input class="search" type="text" name="SEARCH_REPLY" placeholder="検索内容">
				<input class="searchButton" type="submit" name="SUBMIT" value="検索">
				<br>
			</form>

		</div>
	</main>
	<!-- メインここまで -->

	<!--  投稿内容自体を表示した後、forEach文で各返信を出力 -->
	<!--  例:<div>投稿内容 <form><forEach>返信内容</form></div>-->

	<!-- ここからjavaScript -->
	<script type="text/javascript">
		'use strict';
		let smileTF=0;
		let shockTF;
		let tearTF;
		let countSmile;
		let countShock;
		let countTear;
		//いいねアイコンの増減
		function reactionSmileCount() {

			countSmile = parseInt(document.getElementById("smile").textContent);
			//smileTF=parseInt(document.getElementById("smile").dataset.smilebool);
			if (smileTF == 0) {
				countSmile--;
				smileTF = 1;
			} else {
				countSmile++;
				smileTF = 0;
			}
			document.getElementById("smile").innerHTML =countSmile;

			//168,169行のどちらの書き方でも格納可
			//document.getElementById("hidden_smile").outerHTML = '<input type="hidden" id="hidden_smile" name="SMILE" value="'+countSmile+'">';
			document.getElementById('hidden_smile').value = countSmile;

		}

		//驚きアイコンの増減
		function reactionShockCount() {
			countShock = parseInt(document.getElementById("shock").textContent);
			//smileTF=parseInt(document.getElementById("smile").dataset.smilebool);
			if (shockTF == 0) {
				countShock--;
				shockTF = 1;
			} else {
				countShock++;
				shockTF = 0;
			}
			document.getElementById("shock").innerHTML =countShock;
			document.getElementById('hidden_shock').value = countShock;
		}
		//涙アイコンの増減
		function reactionTearCount() {
			countTear = parseInt(document.getElementById("tear").textContent);
			//smileTF=parseInt(document.getElementById("smile").dataset.smilebool);
			if (tearTF == 0) {
				countTear--;
				tearTF = 1;
			} else {
				countTear++;
				tearTF = 0;
			}
			document.getElementById("tear").innerHTML = countTear;
			document.getElementById('hidden_tear').value = countTear;
		}

	</script>
	<!-- ここまでjavaScript -->

</body>
</html>