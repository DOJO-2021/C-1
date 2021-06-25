<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<!-- 各配置はcssファイル上でabsoluteで場所を指定すればよいのでタグの
順番はある程度配慮するものの確定ではない -->
<html>
<head>
<meta charset="UTF-8">
<title>TeraChannel | ViewBoard</title>
<link rel="stylesheet" href="/TeraChannel/css/ViewBoard.css">



</head>

<body>
	<!-- 失敗処理だった場合のアラート処理 -->
	<c:if test="${not empty fail}">
		<script type="text/javascript">
			window.alert("${fail}");
		</script>
	</c:if>
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
			<div class="rule">
				<h4>
					てらちゃんねる<br>利用のルール
				</h4>
				<p>
					皆さんが快適に過ごすために以下のルールを守って使用して下さい。<br>
					<br> 1.個人名の表記の有無に限らず特定個人を揶揄したような悪口を投稿したり誹謗中傷はしないでください。<br>
					相手を罵るなどの挑発的な言葉や、言葉尻をとらえるような揚げ足取り等の書き込みも、トラブルの元となりますのでやめてください。<br>
					<br>
					2.この掲示板は皆様の善意の書き込みによって成り立っていますが、掲示板に投稿された情報は必ずしも正確であるとは限りませんので、自己の責任と判断で掲示板をご利用ください。<br>
					<br> 3.安全性に疑いのあるサイトのURLの記載はお控えください。<br>
					<br>
					以上のルールが守れなかった場合、該当の投稿・返信の削除及び匿名化を解除し、実名を公表させていただく場合がありますので予めご了承ください。
				</p>
			</div>

			<!-- 投稿内容表示部分 -->
			<c:set var="checkID" value="${user_id}" />
			<div class="board">
				<form class="board_form" method="POST"
					action="/TeraChannel/ViewBoardServlet">

					<!-- formタグで表示しないパラメータをリクエストスコープに格納するためのhiddenタグ -->
					<input type="hidden" name="board_id" value="${bd.board_id}">
					<input type="hidden" name="board_update" value="${bd.board_update}">
					<input type="hidden" name="board_topic" value="${bd.board_topic}">

					<p class="postDate">投稿日時:${bd.board_update}</p>

					<!-- 投稿の出力 -->

					<c:forEach var="f" items="${userList}">

						<!-- 投稿者のIDとユーザーIDが一致していた場合 -->
						<c:if test="${f.user_id == bd.user_id}">

							<!-- その上で実名化カウントが１以上だった場合 -->
							<c:if test="${f.user_nameCount >=1}">
								<h3>${bd.board_topic}&nbsp;&nbsp; 投稿者:${f.user_name}さん</h3>
								<!-- 利用しているユーザーと投稿者が一致していたら -->
								<c:if test="${user_id == bd.user_id}">

									<textarea class="board_main_input" name="board_main" rows="8"
										cols="60">${bd.board_main}</textarea>
									<div class="editDelete">
										<input class="edit" type="submit" name="submit" value="投稿:編集">
										<input class="delete" type="submit" name="submit"
											value="投稿:削除">
									</div>

								</c:if>

								<!-- 反対の場合 -->
								<c:if test="${user_id != bd.user_id}">
									<p class="board_main">${bd.board_main}</p>
									<input type="hidden" name="board_main" value="${bd.board_main}">
								</c:if>

							</c:if>

							<!-- その上で実名化カウントが0だった場合、通常の出力を行う -->
							<c:if test="${f.user_nameCount == 0}">
								<h3>${bd.board_topic}&nbsp;&nbsp; 投稿者:匿名${bd.user_id}さん</h3>
								<!-- 利用しているユーザーと投稿者が一致していたら -->
								<c:if test="${user_id == bd.user_id}">

									<textarea class="board_main_input" name="board_main" rows="8"
										cols="60">${bd.board_main}</textarea>
									<div class="editDelete">
										<input class="edit" type="submit" name="submit" value="投稿:編集">
										<input class="delete" type="submit" name="submit"
											value="投稿:削除">
									</div>

								</c:if>

								<!-- 反対の場合 -->
								<c:if test="${user_id != bd.user_id}">

									<p class="board_main">${bd.board_main}</p>
									<input type="hidden" name="board_main" value="${bd.board_main}">

								</c:if>

							</c:if>

						</c:if>

					</c:forEach>

					<!-- リアクション表示部分 -->
					<div class="reaction">
						<div>
							<input type="hidden" id="hidden_smile" name="smile"
								value="${bd.board_smile}">
							<image class="smile" onclick="reactionSmileCount()"
								src="image/smile.jpg" alt="リアクション（笑顔）"></image>
							<p class="reactionCount" id="smile" name="smile">${bd.board_smile}</p>
						</div>
						<div>
							<input type="hidden" id="hidden_shock" name="shock"
								value="${bd.board_shock}">
							<image class="shock" onclick="reactionShockCount()"
								src="image/shock.jpg" alt="リアクション（驚愕）"></image>
							<p class="reactionCount" id="shock" name="shock">${bd.board_shock}</p>
						</div>
						<div>
							<input type="hidden" id="hidden_tear" name="tear"
								value="${bd.board_tear}">
							<image class="tear" onclick="reactionTearCount()"
								src="image/tear.jpg" alt="リアクション（感涙）"></image>
							<p class="reactionCount" id="tear" name="tear">${bd.board_tear}</p>
						</div>

					</div>
					<div class="registreaction">
						<input class="reactionRegist" type=submit name="submit"
							value="リアクション">
					</div>
				</form>


				<!-- ここから返信欄（forEach部分） -->
				<!-- 矢印の部分はおそらく画像挿入の形 -->
				<c:forEach var="e" items="${replyList}">
					<form class="board_form" method="POST"
						action="/TeraChannel/ViewBoardServlet">
						<input type="hidden" name="reply_id" value="${e.reply_id}">
						<input type="hidden" name="reply_date" value="${e.reply_date}">




						<c:forEach var="f" items="${userList}">

							<!-- 投稿者のIDとユーザーIDが一致していた場合 -->
							<c:if test="${f.user_id == e.user_id}">

								<!-- その上で実名化カウントが１以上だった場合 -->
								<c:if test="${f.user_nameCount >=1}">

									<!-- 利用者と返信者が同じだった場合 -->
									<c:if test="${user_id == e.user_id}">
										<div class="flex1">
											👆${f.user_name}さん&nbsp;返信ID:${e.reply_id}
											<p class="updateDate">登録日:${e.reply_date}</p>
										</div>
										<input class="reply_input" type="text" name="reply_main"
											value="${e.reply_main}">
										<div class="editDelete">
											<input type="hidden" name="board_id" value="${bd.board_id}">
											<input class="edit" type="submit" name="submit" value="返信:編集">
											<input class="delete" type="submit" name="submit"
												value="返信:削除">
										</div>
									</c:if>

									<!--  反対の場合 -->
									<c:if test="${user_id != e.user_id}">
										<div class="flex1">
											👆${f.user_name}さん&nbsp;返信ID:${e.reply_id}
											<p class="updateDate" style="text-align: right">登録日:${e.reply_date}</p>
										</div>
										<p class="reply" name="reply_main">${e.reply_main}</p>

										<input type="hidden" name="reply_main" value="${e.reply_main}">
									</c:if>

								</c:if>

								<!-- その上で実名化カウントが0だった場合、通常の出力を行う -->
								<c:if test="${f.user_nameCount == 0}">


									<!-- 利用者と返信者が同じだった場合 -->
									<c:if test="${user_id == e.user_id}">
										<div class="flex1">
											👆${f.user_name}さん&nbsp;返信ID:${e.reply_id}
											<p class="updateDate">登録日:${e.reply_date}</p>
										</div>
										<input class="reply_input" type="text" name="reply_main"
											value="${e.reply_main}">
										<div class="editDelete">
											<input type="hidden" name="board_id" value="${bd.board_id}">
											<input class="edit" type="submit" name="submit" value="返信:編集">
											<input class="delete" type="submit" name="submit"
												value="返信:削除">
										</div>
									</c:if>

									<!--  反対の場合 -->
									<c:if test="${user_id != e.user_id}">
										<div class="flex1">
											👆匿名${e.user_id}さん&nbsp;返信ID:${e.reply_id}
											<p class="updateDate" style="text-align: right">登録日:${e.reply_date}</p>
										</div>
										<p class="reply" name="reply_main">${e.reply_main}</p>

										<input type="hidden" name="reply_main" value="${e.reply_main}">
									</c:if>

								</c:if>

							</c:if>

						</c:forEach>

					</form>
				</c:forEach>


				<form class="board_form" method="POST"
					action="/TeraChannel/ViewBoardServlet">
					<!-- 返信の最後の部分はtextareaで表示（forEach文の外） -->

					<input type="hidden" name="board_id" value="${bd.board_id}">
					<input type="hidden" name="user_id" value="${user_id}">
					<textarea class="reply_text" name="reply_main"
						placeholder="返信内容を入力してください" rows="4" cols="60"></textarea>
					<div class="editDelete">
						<input class="reply_button" type="submit" name="submit" value="返信">
					</div>
					<br>
				</form>
			</div>


			<form class="board_form" method="POST"
				action="/TeraChannel/ViewBoardServlet">

				<input type="hidden" name="board_id" value="${bd.board_id}">
				<div class="search">
					<input class="search_input" type="text" name="search_reply"
						placeholder="検索内容">
				</div>
				<div class="editDelete">
					<input class="searchButton" type="submit" name="submit" value="検索">
				</div>
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
		let smileTF = 1;
		let shockTF = 1;
		let tearTF = 1;
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
				if (shockTF == 0 || tearTF == 0) {
					window.alert("リアクションは1つだけです。");
					return;
				}
				countSmile++;
				smileTF = 0;
			}
			document.getElementById("smile").innerHTML = countSmile;

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
				if (tearTF == 0 || smileTF == 0) {
					window.alert("リアクションは1つだけです。");
					return;
				}
				countShock++;
				shockTF = 0;
			}
			document.getElementById("shock").innerHTML = countShock;
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
				if (shockTF == 0 || smileTF == 0) {
					window.alert("リアクションは1つだけです。");
					return;
				}
				countTear++;
				tearTF = 0;
			}
			document.getElementById("tear").innerHTML = countTear;
			document.getElementById('hidden_tear').value = countTear;
		}
	</script>
	<!-- ここまでjavaScript -->
	<!-- フッターここから -->
	<footer>
		<p>
			<b>DOJO</b>
		</p>
		<p>
			Copyright(C) 2021 SEplus.Co.,Ltd. All rights reserved.<br>
			本サイトの掲載記事、写真、イラスト、問題コンテンツの無断転載を禁じます。記載されているロゴ、システム名、製品名は各社及び商標権者の登録商標あるいは商標です。
		</p>
		<br>
	</footer>
	<!-- フッターここまで -->

</body>
</html>