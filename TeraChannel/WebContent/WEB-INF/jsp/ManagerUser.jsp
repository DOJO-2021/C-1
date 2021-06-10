<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー管理</title>
<link rel="stylesheet" href="/TeraChannel/css/ManagerUser.css">
<img src="image/teraco.jpg" alt="logo">
<h3 style="text-align:right"><a href="/TeraChannel/MenuServlet">メニューへ戻る</a></h3>
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
</body>
</html>