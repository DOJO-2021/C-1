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
	<!-- 失敗処理だった場合のアラート処理 -->
		<c:if  test="${not empty fail}">
		 <script type="text/javascript">
		 window.alert("${fail}");
		 </script>
		</c:if>
	<!-- ヘッダーここから -->
	<header>
		<div class="teraco">
			<a href="/TeraChannel/ManagerMenuServlet"><img
				src="/TeraChannel/image/teraco.jpg" alt="TERACO"></a>
		</div>
		<div class="terachan">
			<a href="/TeraChannel/ManagerViewMenuServlet"><img
				src="/TeraChannel/image/keijiban.jpg" alt="てらちゃんねる"></a>
		</div>
		<div class="navi">
			<button onclick="location.href='/TeraChannel/ManagerMenuServlet'"
				class="button_menu">メニューページへ戻る</button>
		</div>
	</header>
	<!-- ヘッダーここまで -->

	<!-- メインここから -->
	<main>

		<div class="yokonarabi">
			<!-- マニュアル表示部分 -->
			<div class="rule">
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
			<c:set var="checkID" value="${user_id}"/>
			<div class="board">
				<form class="board_form" method="POST" action="/TeraChannel/ManagerViewBoardServletTest">

					<!-- formタグで表示しないパラメータをリクエストスコープに格納するためのhiddenタグ -->
					<input type="hidden" name="board_id" value="${bd.board_id}">
					<input type="hidden" name="board_update"value="${bd.board_update}">
					<input type="hidden" name="board_topic" value="${bd.board_topic}">

					<p class="postDate">投稿日時:${bd.board_update}</p>

					<c:forEach var="f" items="${userList}">

						<!-- 投稿者のIDとユーザーIDが一致していた場合 -->
						<c:if test="${f.user_id == bd.user_id}">

							<h3>${bd.board_topic} &nbsp;&nbsp; 投稿者:${f.user_name}さん</h3>

							<textarea class="board_main_input" name="board_main" rows="8"
								cols="60">${bd.board_main}</textarea>
							<div class="editDelete">
								<input class="delete" type="submit" name="submit" value="投稿:削除">
							</div>

						</c:if>

					</c:forEach>


					<!-- リアクション表示部分 -->
					<div class="reaction">
						<div>
						 <input type="hidden" id="hidden_smile" name="smile" value="${bd.board_smile}">
							<image class="smile" src="image/smile.jpg" alt="リアクション（笑顔）"></image>
							<p class="reactionCount" id="smile" name="smile">${bd.board_smile}</p>
						</div>
						<div>
							<input type="hidden" id="hidden_shock" name="shock" value="${bd.board_shock}">
							<image class="shock" src="image/shock.jpg" alt="リアクション（驚愕）"></image>
							<p class="reactionCount" id="shock" name="shock">${bd.board_shock}</p>
						</div>
						<div>
						<input type="hidden" id="hidden_tear" name="tear" value="${bd.board_tear}">
							<image class="tear" src="image/tear.jpg" alt="リアクション（感涙）"></image>
							<p class="reactionCount" id="tear" name="tear">${bd.board_tear}</p>
						</div>

					</div>
					</form>


					<!-- ここから返信欄（forEach部分） -->
					<!-- 矢印の部分はおそらく画像挿入の形 -->
					<c:forEach var="e" items="${replyList}">
						<form class="board_form" method="POST" action="/TeraChannel/ManagerViewBoardServletTest">
							<input type="hidden" name="reply_id" value="${e.reply_id}">
							<input type="hidden" name="reply_date" value="${e.reply_date}">

							<c:forEach var="f" items="${userList}">

								<!-- 投稿者のIDとユーザーIDが一致していた場合 -->
								<c:if test="${f.user_id == e.user_id}">
										<div class="flex1">
											👆${f.user_name}さん&nbsp;返信ID:${e.reply_id}
											<p class="updateDate">登録日:${e.reply_date}</p>
										</div>
											<input class="reply_input" type="text" name="reply_main" value="${e.reply_main}">
										<div class="editDelete">
											<input class="delete" type="submit" name="submit" value="返信:削除">
										</div>
								</c:if>
							</c:forEach>

						</form>
					</c:forEach>

			</div>


			<form class="board_form" method="POST" action="/TeraChannel/ManagerViewBoardServletTest">

				<input type="hidden" name="board_id"value="${bd.board_id}">
				<div class="search">
				<input class="search_input" type="text" name="search_reply" placeholder="検索内容">
				</div>
				<div class="editDelete">
				<input class="searchButton" type="submit" name="submit" value="検索">
				</div>
				<br>
			</form>

		</div>
	</main>
	<!-- メインここまで -->
</body>
</html>