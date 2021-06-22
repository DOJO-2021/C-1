<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TERAKO</title>
<link rel="stylesheet" href="/TeraChannel/css/Login.css">
<img src="image//teraco.jpg" alt="logo">
<hr>
</head>
<body>
	<div id="form">
		<p class="form-title">ログイン</p>
		<hr>
		<form method="POST" action="/TeraChannel/ManagerLoginServlet">
			<table>
				<tr>
					<th><p class="mail">メールアドレス</th>
					<td><input type="email" name="mail" />
						</p></td>
				</tr>
				<tr>
					<th><p class="pass">パスワード</th>
					<td><input type="password" name="pass" />
						</p></td>
				</tr>
			</table>
			<div class="narande">
				<p class="submit">
					<input type="submit" class="btn" value="ログイン" />
				</p>
				<a href="" class="wasureta">パスワードを忘れた方はこちら</a>
			</div>
		</form>
		<div class="errorColor">
		<c:out value="${errorMessage}" />
		</div>
		<div class="taikin">
		<c:out value="${taikinMessage }" />
		</div>
	</div>

</body>
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

</html>