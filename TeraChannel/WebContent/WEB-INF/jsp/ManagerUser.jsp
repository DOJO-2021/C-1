<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー管理</title>
<link rel="stylesheet" href="/TeraChannel/css/ManagerUser.css">
<link rel="stylesheet" href="/TeraChannel/css/Common.css">
<img src="image/teraco.jpg" alt="logo">
<h3 style="text-align:right"><a href="/TeraChannel/MenuServlet">メニューへ戻る</a></h3>
</head>

<body>
<h1>ユーザー管理</h1>


<!-- ボタン部分 -->
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



<div class="menu">

<!-- マニュアル表示部分 -->
			<div class="manual">
				<h2>～管理者マニュアル～</h2>
				<p>
本サイトは受講生の自由な情報発信と意見の交換の促進、また、
事務局員の受講者理解の補助の役割を担うサイトです。
快適に活用をできるよう、以下、マニュアルに沿った運用をお願い致します。
ーーーーーーーーーーーーーーーーーーーーーーーーー
1.挑発的な言葉や誹謗中傷、言葉尻をとらえるような揚げ足取り等の書き込み
2.安全性に疑いのあるサイトのURLの記載
上記の発言・投稿を発見した際、該当の投稿の削除、そしてそのアカウントへの警告。
当サイトは警告のカウント（ドクロカウント）を行い、３回を目安に該当アカウントへの罰則として匿名の解除をする。
この罰則は時間経過で解除される。
管理者は投稿の閲覧画面にある文字の検索機能も活用し、掲示板を管理してください。
				</p>
			</div>


<!-- テーブル部分 -->
<div class="tb">
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
<tr>
  <td></td>
  <td></td>
  <td></td>
  <td></td>
  <td></td>
  <td> <div id="disp_count">0</div>
<input type="button" value="↑" id="btn_count_up" />
<input type="button" value="リセット" id="btn_reset" />  </td>
  <td></td>
</tr>
</table>

<script>
'use strict';
//ページが読み込まれたら実行
window.onload = function() {

     // オブジェクトと変数の準備
     var count_disp = document.getElementById("disp_count");
     var count_up_btn = document.getElementById("btn_count_up");
     var reset_btn = document.getElementById("btn_reset");
     var count_value = 0;

     // カウントアップボタンクリック処理
     count_up_btn.onclick = function (){
          count_value += 1;
          count_disp.innerHTML = count_value;
     };
     // カウントアップボタンのマウスダウン処理
     count_up_btn.onmousedown = function() {
          count_up_btn.style.backgroundColor = "#00FF00";
     }
     // カウントアップボタンのマウスアップ処理
     count_up_btn.onmouseup = function() {
          count_up_btn.style.backgroundColor = "";
     }
     // リセットボタンのクリック処理
     reset_btn.onclick = function (){
          count_value = 0; count_disp.innerHTML = count_value;
     }
     // リセットボタンのマウスダウン処理
     reset_btn.onmousedown = function() {
          reset_btn.style.backgroundColor = "#00FF00";
     }
     // リセットボタンのマウスアップ処理
     reset_btn.onmouseup = function() {
          reset_btn.style.backgroundColor = "";
     }
};
</script>
</div>
</div>
</body>
<footer>
</footer>
</html>