<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TeraChannel ManagerViewMenu</title>
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

		<div class=list>

		<form  action="ViewMenuServlet" method="post" id="re-ch" >
			<select  class="dropdown-select" id="reaction" name="reaction" onchange="submit(this.form)" >
				<option value="popular" ${reaction1}>リアクションの多い順</option>
				<option value="notpopular" ${reaction2}>リアクションの少ない順</option>
			</select>
		</form>&nbsp;
		<form  action="ViewMenuServlet" method="post" id="ref-ch" >
		 	<select  class="dropdown-select" id="refresh" name="refresh"  onchange="submit(this.form)">
				<option value="newevent" ${refresh1}>新着順</option>
				<option value="oldevant" ${refresh2}>古い順</option>
			</select>
		</form>

		<form  action="ViewMenuServlet" method="post" >
		<div class="search">
			<input class="search_input" type="search" name="search" placeholder="キーワードを入力">
		</div>
		<div class="editDelete">
			<input class="searchButton" type="submit" name="submit"  value="検索" >&nbsp;
		</div>
		</form>
	</div>
	<!-- メイン -->

	<main>
		<div class="mainpage">
			<div class="rule">
				<h4>管理者マニュアル</h4>
				<p>本サイトは受講生の自由な情報発信と意見の交換の促進、また、 事務局員の受講者理解の補助の役割を担うサイトです。
					快適に活用をできるよう、以下、マニュアルに沿った運用をお願い致します。<br> ーーーーーーーーーーーーーーーー<br>
					<br>1.挑発的な言葉や誹謗中傷、言葉尻をとらえるような揚げ足取り等の書き込み <br>2.安全性に疑いのあるサイトのURLの記載
					上記の発言・投稿を発見した際、該当の投稿の削除、そしてそのアカウントへの警告。<br>
					<br>当サイトは警告のカウント（ドクロカウント）を行い、３回を目安に該当アカウントへの罰則として匿名の解除をする。
					この罰則は時間経過で解除される。<br> 管理者は投稿の閲覧画面にある文字の検索機能も活用し、掲示板を管理してください。</p>
			</div>
			<div class="page">

				<div id="page_scroll" class="scroll">
					<p>${error}</p>

					<c:forEach var="b" items="${topListMain}">
						<p>

						<form action="ManagerViewMenuServlet" method="post" >
						<h10> <a href="/TeraChannel/ManagerViewBoardServlet?board_id=${b.board_id}">${b.board_topic}</a></h10>
						</form>
						<br>
						<h11><pre> みんなのリアクション数：<c:out value="${b.board_smileTotal}" />   最終更新日：<c:forEach
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



		プルダウン切り替え(リアクション)
		  const rech = document.getElementById('re-ch');
			function exe() {
		    location.href =rech.action;
		}

		プルダウン切り替え(新着)
		const refch = document.getElementById('ref-ch');
			function exec() {
			location.href =refch.action;
		}



	</script>
</body>
</html>