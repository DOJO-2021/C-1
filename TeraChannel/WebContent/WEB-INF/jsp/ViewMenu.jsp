<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TeraChannel ViewMenu</title>
<link rel="stylesheet" href="/TeraChannel/css/ViewMenu.css">
</head>
<body>

<!-- ヘッダーここから -->
	<header>
		<h1>
			<a href="/TeraChannel/MenuServlet"><img src="/TeraChannel/image/teraco.jpg"
			alt="TERACO"></a>
		</h1>
		<h2>
			<a href="/TeraChannel/ViewMenuServlet"><img src="/TeraChannel/image/keijiban.jpg"
			alt="てらちゃんねる"></a>
		</h2>
		<a href="/TeraChannel/BoardServlet">投稿</a> <a
			href="/TeraChannel/MenuServlet">メニューページへ戻る</a>
	</header>
<!-- ヘッダーここまで -->

	<form action="TeraChannel/Board" method="post">
	<input type="search" name="search" placeholder="キーワードを入力">
	<input type="submit" name="submit" value="検索">
	</form>

<div>
	<select name="reaction">
		<option value="popular">リアクションの多い順</option>
		<option value="notpopular">リアクションの少ない順</option>
	</select>
</div>
<div>
	<select name="refresh">
		<option value="newevent">新着順</option>
		<option value="oldevant">古い順</option>
	</select>
</div>
<!-- メイン -->

<main>
<div class="page">
	<div class="scroll">
		<p>タイトル トータルリアクション数： 最終更新日：</p>
	</div>
</div>

<div class ="rule">
	<h2>てらちゃんねる利用のルール</h2>
	<p>皆さんが快適に過ごすために以下のルールを守って使用して下さい。</p>
	<p>1.個人名の表記の有無に限らず特定個人を揶揄したような悪口を投稿したり誹謗中傷はしないでください。</p>
  	<p>相手を罵るなどの挑発的な言葉や、言葉尻をとらえるような揚げ足取り等の書き込みも、トラブルの元となりますのでやめてください。</p>
	<p>2.この掲示板は皆様の善意の書き込みによって成り立っていますが、掲示板に投稿された情報は必ずしも正確であるとは限りませんので、自己の責任と判断で掲示板をご利用ください。</p>
	<p>3.安全性に疑いのあるサイトのURLの記載はお控えください。</p>
	<p>以上のルールが守れなかった場合、該当の投稿・返信の削除及び匿名化を解除し、実名を公表させていただく場合がありますので予めご了承ください。</p>
</div>
</main>
<!-- メインここまで -->

</body>
</html>