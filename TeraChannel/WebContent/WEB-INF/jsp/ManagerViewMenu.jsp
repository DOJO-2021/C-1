<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
<div class="scroll">
	<p>トータルリアクション数：</p><p>最終更新日：</p>
</div>
</main>
<!-- メインここまで -->

</body>
</html>