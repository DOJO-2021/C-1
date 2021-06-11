<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>TERAKO Password</title>
<link rel="stylesheet" href="css/Password.css">
</head>


<body>
	<div class=div>
		<img src="image/teraco.jpg">

		<h2>パスワード変更</h2>

		<p>新しいパスワードを入力してください


		<form method="POST" action="/TeraChannel/LoginServlet">
			<table  align="center">
			    <tr>
				<th>前のパスワード</th>
				<td><input type="oldpass" name="old"></td>
				</tr>
				<tr>
				<th>新しいパスワード</th>
				<td><input type="newpass" name="new"></td>
				</tr>
			</table>
			<p>
				<input type="submit" name="change" value="変更">
			</p>
		</form>
     </div>
</body>
</html>