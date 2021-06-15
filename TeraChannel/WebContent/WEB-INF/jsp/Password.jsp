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


			<form  method="POST" action="/TeraChannel/PasswordServlet" onsubmit="return check();">
			<table  align="center">
			    <tr>
				<th>前のパスワード</th>
				<td><input type="password" id="pass" name="password" class="form-control" ></td>
				</tr>
				<tr>
				<th>新しいパスワード</th>
				<td><input type="newpass" name="new_pass" class="form-control"></td>
				</tr>
				<th>新しいパスワードの確認</th>
				<td><input type="confirm" name="confirm" class="form-control"></td>
				</tr>
			</table>
			<p>
				<input type="submit" name="change" value="変更">
			</p>
		</form>
     </div>

<script type="text/javascript">
  document.getElementById('pass');
    function check(){

	    //前のパスワードに値が入っているか？
	  if(pass == null){
			return false;
		}
/*
		else{ // 「キャンセル」時の処理
			window.alert('パスワードを入力してださい'); // 警告ダイアログを表示
			return false; // 送信を中止
		}

   //新しいパスワードは両方一致しているか？

       if(new_pass !== confirm) {
        return false;
        window alert('パスワードが一致していません');
     }
*/
  }

}


  </script>

</body>
</html>