<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TeraChannel｜board</title>
</head>
<body>
<!-- ヘッダーここから -->
	<header>
		<h1>
			<a href="/TeraChannel/MenuServlet">TERACO</a>
		</h1>
		<h2>
			<a href="/TeraChannel/ViewMenuServlet">てらちゃんねる</a>
		</h2>
		<a href="/TeraChannel/BoardServlet">投稿</a> <a
			href="/TeraChannel/MenuServlet">メニューページへ戻る</a>
	</header>
<!-- ヘッダーここまで -->
<!-- メインここから -->
	<main>
		<h2>投稿</h2>
		<p>タイトル及び内容は必須項目です。</p>
		<form method="GET" action="">
			<h3>タイトル</h3>
			<p>タイトルは内容が推測できるものをつけてください。</p>
			<input type="text" name="title" placeholder="タイトル">
			<h3>内容</h3>
			<textarea name="content"></textarea>
			<input type="submit" name="upload" value="投稿する">
		</form>
		<h3>ルール</h3>
		<p>
			皆さんが快適に過ごすために以下のルールを守って使用して下さい。<br>
			1.個人名の表記の有無に限らず特定個人を揶揄したような悪口を投稿したり誹謗中傷はしないでください。<br>
			相手を罵るなどの挑発的な言葉や、言葉尻をとらえるような揚げ足取り等の書き込みも、トラブルの元となりますのでやめてください。<br>
			2.この掲示板は皆様の善意の書き込みによって成り立っていますが、掲示板に投稿された情報は必ずしも正確であるとは限りませんので、自己の責任と判断で掲示板をご利用ください。<br>
			3.安全性に疑いのあるサイトのURLの記載はお控えください。<br>
			以上のルールが守れなかった場合、該当の投稿・返信の削除及び匿名化を解除し、実名を公表させていただく場合がありますので予めご了承ください。
		</p>
	</main>
<!-- メインここまで -->
<!-- フッターここから -->
	<footer>
	</footer>
<!-- フッターここまで -->

</body>
</html>