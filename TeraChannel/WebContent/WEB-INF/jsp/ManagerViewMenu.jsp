<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TeraChannel ViewMenu</title>
<link rel="stylesheet" href="/TeraChannel/css/ViewMenu.css">
<link rel="stylesheet" href="/TeraChannel/css/Common.css">
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
		<form action="TeraChannel/Board" method="post">
			<input type="search" name="search" placeholder="キーワードを入力"> <input
				type="submit" name="submit" value="検索">
		</form>
		<select name="reaction">
			<option value="popular">リアクションの多い順</option>
			<option value="notpopular">リアクションの少ない順</option>
		</select> <select name="refresh">
			<option value="newevent">新着順</option>
			<option value="oldevant">古い順</option>
		</select>
	</div>
	<!-- メイン -->

	<main>
		<div class="mainpage">
			<div class="rule">
				<h4>～管理者マニュアル～</h4>
				<p>本サイトは受講生の自由な情報発信と意見の交換の促進、また、 事務局員の受講者理解の補助の役割を担うサイトです。
					快適に活用をできるよう、以下、マニュアルに沿った運用をお願い致します。<br> ーーーーーーーーーーーーーーーー<br>
					1.挑発的な言葉や誹謗中傷、言葉尻をとらえるような揚げ足取り等の書き込み 2.安全性に疑いのあるサイトのURLの記載
					上記の発言・投稿を発見した際、該当の投稿の削除、そしてそのアカウントへの警告。
					当サイトは警告のカウント（ドクロカウント）を行い、３回を目安に該当アカウントへの罰則として匿名の解除をする。
					この罰則は時間経過で解除される。 管理者は投稿の閲覧画面にある文字の検索機能も活用し、掲示板を管理してください。</p>
			</div>
			<div class="page">
				<div class="scroll">
					<p>タイトル トータルリアクション数： 最終更新日：</p>
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

</body>
</html>