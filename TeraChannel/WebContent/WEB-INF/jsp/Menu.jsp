<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TERAKO</title>
<link rel = "stylesheet" href="css/Menu.css">
</head>
<body>

 <nav class = "nav" >
   <ul>
      <li><img src="image/teraco.jpg"></li>
      <li>HOME</li>
      <li>コース受講</li>
      <li>動画視聴</li>
      <li>講義生配信</li>
      <li>チートシート</li>

      <li><select name="menu" id="passlog"></li>
            <option value="">マイメニュー</option>
            <option value="password">パスワード変更</option>
            <option value="logout"><a href="/TeraChannel/LogoutServlet">ログアウト</a></option>
          </select>
      <li><a href="/TeraChannel/ViewBoardServlet">掲示板</a></li>
   </ul>

 </nav>

 <hr>

<h3>　　　現在のあなたの段位:段位なし</h3>
<h3>　　　メインメニュー</h3>

<hr align="left" width = "800">
  <ul>
    <li>　　　パーソナルスキルコース</li>
    <li>　　　IT関連</li>
    <li>　　　Java基礎</li>
    <li>　　　開発演習[現在開発中]</li>
  </ul>
<hr align="left" width="800">
<br>
<div style="padding: 10px; margin-bottom: 10px; border: 1px solid #333333;">
<p>　　　お知らせ</p>
	<hr width="80%" noshade>
  <ul>
  <br>
    <li>20210603の日報に1件のコメントがついてます</li>
    <li>20210602の日報に1件のコメントがついてます</li>
    <li>20210601の日報に1件のコメントがついてます</li>
    <li>20210531の日報に1件のコメントがついてます</li>
    <li>20210528の日報に1件のコメントがついてます</li>
    <li>20210527の日報に1件のコメントがついてます</li>
    <li>20210526の日報に1件のコメントがついてます</li>
    <li>20210525の日報に1件のコメントがついてます</li>
    <li>20210524の日報に1件のコメントがついてます</li>
  </ul>
</div>
</body>
 <footer>
 <br>
   <p>
      Copyright(C) 2021 SEplus.Co.,Ltd. All rights reserved.<br>
      本サイトの掲載記事、写真、イラスト、問題コンテンツの無断転載を禁じます。記載されているロゴ、システム名、製品名は各社及び商標権者の登録商標あるいは商標です。
   </p>
  </footer>
</html>