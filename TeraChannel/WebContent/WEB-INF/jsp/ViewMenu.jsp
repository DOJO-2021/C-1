<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TeraChannel ViewMenu</title>
<link rel="stylesheet" href="/TeraChannel/css/ViewMenu.css">
<link rel="stylesheet" href="/TeraChannel/css/Common.css">

<!-- jQuery -->
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
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

	<div class=list>
		<form id="form" action="ViewMenuServlet" method="post">
			<input type="search" name="search" placeholder="キーワードを入力">
			<input type="submit"  value="検索" onclick="exec()">
		</form>
		<select id= "re-ch"  name="reaction">
			<option  action="ViewMenuServlet" name="pulldown" value="popular">リアクションの多い順</option>
			<option  action="ViewMenuServlet" name="pulldown" value="notpopular">リアクションの少ない順</option>
		</select> <select id= "ref-ch" value="ViewMenuServlet" name="refresh">
			<option  action="ViewMenuServlet" name="pulldown" value="newevent">新着順</option>
			<option  action="ViewMenuServlet" name="pulldown" value="oldevant">古い順</option>
		</select>

	</div>
	<!-- メイン -->

	<main>
		<div class="mainpage">
			<div class="rule">
				<h4>
					てらちゃんねる<br>利用のルール
				</h4>
				<p>
					皆さんが快適に過ごすために以下のルールを守って使用して下さい。<br> <br>
					1.個人名の表記の有無に限らず特定個人を揶揄したような悪口を投稿したり誹謗中傷はしないでください。<br>
					相手を罵るなどの挑発的な言葉や、言葉尻をとらえるような揚げ足取り等の書き込みも、トラブルの元となりますのでやめてください。<br>
					<br>
					2.この掲示板は皆様の善意の書き込みによって成り立っていますが、掲示板に投稿された情報は必ずしも正確であるとは限りませんので、自己の責任と判断で掲示板をご利用ください。<br>
					<br> 3.安全性に疑いのあるサイトのURLの記載はお控えください。<br> <br>
					以上のルールが守れなかった場合、該当の投稿・返信の削除及び匿名化を解除し、実名を公表させていただく場合がありますので予めご了承ください。
				</p>
			</div>
			<div class="page">
				<%--
				<div class="scroll">
					<button id="page_top" class="scrollbuttan"></button>
				  <div class="scrollbuttan" onclic="Top()"></div>
--%>

				<div id="page_scroll" class="scroll">

					<c:forEach var="b" items="${topListMain}">
						<p>
							<%--<input type="hidden" onclick="/TeraChannel/ViewBoardServlet${board_id}" value="${b.board_topic}">
							--%>
							<h8> <a href="/TeraChannel/ViewBoardServlet?${board_id}">
								${b.board_topic} </a></h8>
							<br>
							<h11>
							<pre>  みんなのリアクション数：<c:out value="${b.board_smileTotal}" />   最終更新日：<c:forEach
									var="r" items="${b.reply}">${r.reply_date}</c:forEach>
							</pre></h11>
						</p>
					</c:forEach>

					<div id="page_top" class="scrollbuttan">
						<a href="#"></a>
					</div>
					<!--  <div class="scrollbuttan" onclic="Top()"></div>	-->

				</div>
			</div>
		</div>

	</main>
	<!-- メインここまで -->
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
	<script type="text/javascript">
		//	function Top() {
		//		scrollTo(0, 0);
		//	}

		//scrollボタン
		$(function() {
			let pagetop = $('#page_top');
			//ボタン非表示
			pagetop.hide();
			//100pxのスクロールでボタン表示
			$('#page_scroll').scroll(function() {
				if ($(this).scrollTop() > 100) {
					pagetop.fadeIn();
				} else {
					pagetop.fadeOut();
				}
			});
			//ボタンを押すとスクロールして上部に戻る
			pagetop.click(function() {
				$('#page_scroll').animate({
					scrollTop : 0
				}, 500);
				return false;
			});
		});

		//検索結果表示
		function exec() {
			document.getElementById('form').submit();
		}
		//プルダウン切り替え(リアクション)
		const selected = document.getElementById('re-ch');
		selected.onchange = function() {
			location.href =select.action;
		}
		//プルダウン切り替え(新着)
		const selected = document.getElementById('ref-ch');
		selected.onchange = function() {
			location.href =select.action;
		}

	</script>
</body>
</html>