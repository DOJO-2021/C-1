<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー管理</title>
<link rel="stylesheet" href="/TeraChannel/css/ManagerUser.css">
<link rel="stylesheet" href="/TeraChannel/css/Common.css">
</head>

<body>

<!-- ヘッダーここから -->
		<header>
		<div class="teraco">
			<a href="/TeraChannel/ManagerMenuServlet"><img
				src="/TeraChannel/image/teraco.jpg" alt="TERACO"></a>
		</div>
		<div class="navi">
			<button onclick="location.href='/TeraChannel/ManagerMenuServlet'"
				class="button_menu">メニューページへ戻る</button>
		</div>
	</header>
	<!-- ヘッダーここまで -->

<!-- メイン -->
<main>
<h1>ユーザー管理</h1>

<!-- ボタン部分 -->
<div>

<form method="POST" action="/TeraChannel/ManagerUserServlet">
<input type="text" name="text" size="5">
<input type="submit" name="SEARCH" value="ID検索">
</form>
<!--  <select id= "re-ch"  name="reaction">
	<option  action="ManagerUserServlet" name="pulldown" value="newdokuro">ドクロカウント：昇順</option>
	<option  action="ManagerUserServlet" name="pulldown" value="olddokuro">ドクロカウント：降順</option>
</select>
<select id= "re-ch"  name="reaction">
	<option  action="ManagerUserServlet" name="pulldown" value="newevent">最終更新日時：昇順</option>
	<option  action="ManagerUserServlet" name="pulldown" value="oldevent">最終更新日時：昇順</option>
</select>
-->
<!--  保留
<form method="POST" action="/TeraChannel/ManagerUserServlet">
<input type="submit" name="1" value="昇順">
<input type="submit" name="2" value="降順">
</form>
-->

<!--  保留
<select name="update">
<option value="=update">更新順</option>
</select>
-->

</div>

<div class="menu">

<!-- マニュアル表示部分 -->
			<div class="manual">
				<h2>～管理者マニュアル～</h2>
				<p>
本サイトは受講生の自由な情報発信と意見の交換の促進、また、
事務局員の受講者理解の補助の役割を担うサイトです。
快適に活用をできるよう、以下、マニュアルに沿った運用をお願い致します。
<br>
ーーーーーーーーーーーーーーーーーーーー
<br>
<br>
1.挑発的な言葉や誹謗中傷、言葉尻をとらえるような揚げ足取り等の書き込み
<br><br>
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
</table>

<c:forEach var="e" items="${userList}" >
	<form method="POST" action="/TeraChannel/ManagerUserServlet">
<input type="submit" name="SUBMIT" value="更新">
<table class="info">
<tr>
  <td><input type="text" name="USER_ID" value="${e.user_id}"></td>
  <td><input type="text" name="USER_NAME" value="${e.user_name}"></td>
  <td><input type="text" name="USER_PW" value="${e.user_pw}"></td>
  <td><input type="text" name="USER_TYPE" value="${e.user_type}"></td>
  <td><input type="text" name="USER_MAIL" value="${e.user_mail}"></td>
  <td><!-- <input type="text" name="USER_COUNT" value="${e.user_count}"> -->
    <div id="disp_count">${e.user_count}</div>
    <input type="button" value="↑" id="btn_count_up" />
    <input type="button" value="↓" id="btn_count_down" />
    </td>
  <td><input type="text" name="USER_NAMECOUNT" value="${e.user_nameCount}"></td>
</tr>
</table>

<script>
'use strict';
//ページが読み込まれたら実行
window.onload = function() {

     // オブジェクトと変数の準備
     var count_disp = document.getElementById("disp_count");
     var count_up_btn = document.getElementById("btn_count_up");
     var count_down_btn = document.getElementById("btn_count_down");
     var count_value = 0;

     // カウントアップボタンクリック処理
     count_up_btn.onclick = function (){
    	//3以上だったら何もしない
    	if(count_value >= 3){
    		return;
    	}
    	//+1する
		 count_value += 1;
         count_disp.innerHTML = count_value;
     };

     // カウントダウンボタンクリック処理
     count_down_btn.onclick = function (){
    	 //0だったら何もしない
         if(count_value === 0){
        	 return;
         }
    	 //-1する
          count_value -= 1;
          count_disp.innerHTML = count_value;
     };
};
</script>
</form>
</c:forEach>

</div>

</div>

</main>
<!-- メインここまで -->
<!-- フッター -->
<footer>
</footer>

</body>
</html>