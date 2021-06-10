<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー管理</title>
<link rel="stylesheet" href="/TeraChannel/css/ManagerUser.css">
<a href="/TeraChannel/MenuServlet">メニューへ戻る</a>
</head>
<body>
<h1>ユーザー管理</h1>

<select name="id">
<option value="=id">ID検索</option>
</select>

<select name="count">
<option value="=count">カウント検索</option>
</select>

<select name="update">
<option value="=update">更新順</option>
</select>

<input type="submit" name="SUBMIT" value="更新">

<table class="info">
<tr>
  <td>ユーザーID</td>
  <td>ユーザー氏名</td>
  <td>パスワード</td>
  <td>ユーザータイプ</td>
  <td>メールアドレス</td>
  <td>ドクロカウント</td>
  <td>実名化カウント</td>
</tr>
</table>


</body>
</html>