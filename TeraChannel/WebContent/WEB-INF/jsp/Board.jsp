<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TeraChannel｜board</title>
<link rel="stylesheet" href="/TeraChannel/css/Board.css">
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
		<div class="main">
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
			<div class="board">
				<h2>投稿</h2>
				<p>タイトル及び内容は必須項目です。</p>
				<form method="POST" action="/TeraChannel/BoardServlet"
					onsubmit="return check();">
					<h3>タイトル</h3>
					<p>タイトルは内容が推測できるものをつけてください。</p>
					<input type="text" name="title" placeholder="タイトル" class="title"
						id="title" value="">
					<h3>内容</h3>
					<textarea name="content" class="content" id="content"></textarea>
					<br> <input type="submit" name="upload" value="投稿する"
						class="upload">
				</form>
			</div>
		</div>

	</main>
	<!-- メインここまで -->
	<script type="text/javascript">
	<!-- 投稿ボタン押したときに確認のconfirm表示 -->
		function check() {
			if (document.getElementById("title").value === ""
					|| document.getElementById("content").value === "") {
				alert("タイトルと内容は必須入力です。");
				return false;
			} else if (window.confirm('投稿してもよろしいですか？')) {
				return true;
			} else {
				return false;
			}
		}
	</script>
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