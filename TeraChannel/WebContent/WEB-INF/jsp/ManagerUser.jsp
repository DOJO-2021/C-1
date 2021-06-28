<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
		<div class="terachan">
			<a href="/TeraChannel/ManagerViewMenuServlet"><img
				src="/TeraChannel/image/keijiban.jpg" alt="てらちゃんねる"></a>
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
          <table class="btn_id">
          <tr>
            <td>
			<form method="POST" action="/TeraChannel/ManagerUserServlet">
				<input type="text" name="text" size="5"> <input class="btn"
					type="submit" name="search" value="ID検索">
			</form>
			</td>
            <td>
			<form action="ManagerUserServlet" method="post" id="docro">
				<select id="reaction" name="reaction" onchange="submit(this.form)">
					<option value="newevent" ${reaction1}>ドクロカウント：昇順</option>
					<option value="oldevant" ${reaction2}>ドクロカウント：降順</option>
				</select>
			</form>
			</td>
            <td>
			<form action="ManagerUserServlet" method="post" id="update">
				<select id="refresh" name="refresh" onchange="submit(this.form)">
					<option value="newdate" ${refresh1}>最終更新日時：昇順</option>
					<option value="olddate" ${refresh2}>最終更新日時：降順</option>
				</select>
			</form>
			</td>
          </tr>
          </table>
		</div>

		<div class="menu">

			<!-- マニュアル表示部分 -->
			<div class="manual">
				<h2>～管理者マニュアル～</h2>
				<p>
					本サイトは受講生の自由な情報発信と意見の交換の促進、また、 事務局員の受講者理解の補助の役割を担うサイトです。
					快適に活用をできるよう、以下、マニュアルに沿った運用をお願い致します。 <br> ーーーーーーーーーーーーーーーーーーーー
					<br> <br> 1.挑発的な言葉や誹謗中傷、言葉尻をとらえるような揚げ足取り等の書き込み <br>
					<br> 2.安全性に疑いのあるサイトのURLの記載
					上記の発言・投稿を発見した際、該当の投稿の削除、そしてそのアカウントへの警告。
					当サイトは警告のカウント（ドクロカウント）を行い、３回を目安に該当アカウントへの罰則として匿名の解除をする。
					この罰則は時間経過で解除される。 管理者は投稿の閲覧画面にある文字の検索機能も活用し、掲示板を管理してください。
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
						<td>最終更新日時</td>
						<td>更新ボタン</td>
					</tr>


				<c:forEach var="e" items="${userList}">
					<form method="POST" action="/TeraChannel/ManagerUserServlet">


							<tr>
								<td><p>${e.user_id}</p><input type="hidden" name="user_id" value="${e.user_id}"></td>
								<td><p>${e.user_name}</p></td>
								<td><p>${e.user_pw}</p></td>
								<td><p>${e.user_type}</p></td>
								<td><p>${e.user_mail}</p></td>
								<td>
									<div id="${e.user_id}" name="user_countDiv">${e.user_count}</div>
									<input id="hidden${e.user_id}" type="hidden"
									name="user_countHidden" value="${e.user_count}"> <input
									type="hidden" name="user_nameCountHidden"
									value="${e.user_nameCount}"> <input type="button"
									value="↑" id="btn_count_up" onclick="countPlus(${e.user_id})" />
									<input type="button" value="↓" id="btn_count_down"
									onclick="countMinus(${e.user_id})" />
								</td>
								<td><p>${e.user_nameCount}</p><input type="hidden" name="user_namecount" value="${e.user_nameCount}"></td>
								<td><p>${e.user_update}</p><input type="hidden" name="user_update" value="${e.user_update}"></td>
							    <td><input class="btn" type="submit" name="submit" value="更新"></td>
							</tr>

					</form>
				</c:forEach>
</table>

			</div>

		</div>

	</main>


	<script type="text/javascript">
		'use strict';


		// オブジェクトと変数の準備
	//	var count_disp = document.getElementById("${e.user_id}");
		//console.log(count_disp);
		function  countPlus(id) {

			var count_value = document.getElementById("hidden"+id).value;
				// カウントアップボタンクリック処理
				//3以上だったら何もしない
				if (count_value >= 3) {
					return;
				}
				else{
					//+1する
					count_value++;
					console.log(count_value);
					document.getElementById(id).innerHTML =count_value;
					document.getElementById("hidden"+id).value = count_value;

				}
			}



		function  countMinus(id) {
			// カウントダウンボタンクリック処理
			//0だったら何もしない
				var count_value = document.getElementById("hidden"+id).value;
				// カウントアップボタンクリック処理
				//3以上だったら何もしない
				if (count_value == 0) {
					return;
				}
				else{
					//+1する
					count_value--;
					console.log(count_value);
					document.getElementById(id).innerHTML =count_value;
					document.getElementById("hidden"+id).value = count_value;

				}
		}







		//ドクロカウント昇順降順
		const docro = document.getElementById('docro');
		function exec() {
		location.href =docro.action;
		}

		//更新順
		const update = document.getElementById('update');
		function exec() {
		location.href =update.action;
		}


		</script>
		<!-- メインここまで -->
	<!-- フッターここから -->
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
	<!-- フッターここまで -->
</body>
</html>